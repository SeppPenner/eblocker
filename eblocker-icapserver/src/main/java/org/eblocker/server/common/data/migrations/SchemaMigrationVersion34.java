/*
 * Copyright 2020 eBlocker Open Source UG (haftungsbeschraenkt)
 *
 * Licensed under the EUPL, Version 1.2 or - as soon they will be
 * approved by the European Commission - subsequent versions of the EUPL
 * (the "License"); You may not use this work except in compliance with
 * the License. You may obtain a copy of the License at:
 *
 *   https://joinup.ec.europa.eu/page/eupl-text-11-12
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.eblocker.server.common.data.migrations;

import org.eblocker.server.common.data.DataSource;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.Set;
import java.util.function.Function;

public class SchemaMigrationVersion34 implements SchemaMigration {
    private static final Logger log = LoggerFactory.getLogger(SchemaMigrationVersion34.class);

    private final DataSource dataSource;
    private final JedisPool jedisPool;
    private final ObjectMapper objectMapper;

    @Inject
    public SchemaMigrationVersion34(DataSource dataSource, JedisPool jedisPool, ObjectMapper objectMapper) {
        this.dataSource = dataSource;
        this.jedisPool = jedisPool;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getSourceVersion() {
        return "33";
    }

    @Override
    public String getTargetVersion() {
        return "34";
    }

    @Override
    public void migrate() {
        try (Jedis jedis = jedisPool.getResource()) {
            update(jedis, "ParentalControlFilterMetaData:[0-9]*", this::updateMetaData);
        }
        dataSource.setVersion("34");
    }

    private void update(Jedis jedis, String keyPattern, Function<String, String> updateFunction) {
        Set<String> keys = jedis.keys(keyPattern);
        for(String key : keys) {
            String json = jedis.get(key);
            String updatedJson = updateFunction.apply(json);
            if (!json.equals(updatedJson)) {
                jedis.set(key, updatedJson);
            }
        }
    }

    private String updateMetaData(String json) {
        try {
            ObjectNode metaDataNode = (ObjectNode) objectMapper.readTree(json);
            JsonNode categoryNode = metaDataNode.get("category");
            if (categoryNode == null || categoryNode.isNull()) {
                metaDataNode.put("category", "PARENTAL_CONTROL");
                return objectMapper.writeValueAsString(metaDataNode);
            }
            return json;

        } catch (IOException e) {
            log.error("failed to update {}", json, e);
            return json;
        }
    }
}
