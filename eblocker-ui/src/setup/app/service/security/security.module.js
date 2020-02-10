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
import DeviceService from '../devices/device.service';
import SecurityService from './security.service.js';

// export default function Security() {
//     'ngInject';
//     'use strict';
//
//     angular.module('eblocker.security', [])
//         .factory('device', device)
//         .factory('security', SecurityService)
//         .factory('localTimestamp', LocalTimestamp)
//         .factory('onlineTime', OnlineTime)
//         .factory('pause', Pause)
//         .factory('userProfile', UserProfile)
//         .factory('sslService', SslService);
// }

(function() {
    'use strict';

    angular.module('eblocker.security', [])
        .factory('DeviceService', DeviceService)
        .factory('security', SecurityService);
})();