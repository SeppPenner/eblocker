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
package org.eblocker.server.common.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LedSettings {
    private final boolean hardwareAvailable;
    private final float brightness;

    @JsonCreator
    public LedSettings(
            @JsonProperty("hardwareAvailable") boolean hardwareAvailable,
            @JsonProperty("brightness") float brightness) {
        this.hardwareAvailable = hardwareAvailable;
        this.brightness = brightness;
    }

    public boolean isHardwareAvailable() {
        return hardwareAvailable;
    }

    public float getBrightness() {
        return brightness;
    }
}
