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
import 'angular-mocks';

describe('App settings; Main component', function() { // jshint ignore: line
    beforeEach(angular.mock.module('template.settings.app'));
    beforeEach(angular.mock.module('eblocker.adminconsole'));

    let ctrl, $componentController, mockRegistrationService, mockUserProfile, mockPostRegistrationInformation,
        mockSetupInfo, mockStateService;

    let mockLocale = {
        language: 'en'
    };

    mockRegistrationService = {
        getRegistrationInfo: function() {
            return {
                isRegistered: true,
                licenseAboutToExpire: false
            };
        },
        hasProductKey: function() {
            return true;
        }
    };

    mockSetupInfo = {
        setupRequired: false
    };

    mockUserProfile = {
        hidden: false,
        standard: true,
        controlmodeTime: true
    };

    mockPostRegistrationInformation = {
        content: {
            dummy: true
        }
    };

    mockStateService = {
        getStates: function () { return []; }
    };

    beforeEach(angular.mock.module(function($provide) {
        $provide.value('locale', mockLocale);
        $provide.value('RegistrationService', mockRegistrationService);
        $provide.value('userProfile', mockUserProfile);
        $provide.value('states', []);
        $provide.value('postRegistrationInformation', mockPostRegistrationInformation);
        $provide.value('setupInfo', mockSetupInfo);
        $provide.value('StateService', mockStateService);
    }));

    beforeEach(inject(function($rootScope, _$componentController_) {
        $componentController = _$componentController_;
        ctrl = $componentController('mainComponent', {}, {});
    }));

    describe('initially', function() {
        it('should create a controller instance', function() {
            expect(angular.isDefined(ctrl)).toBe(true);
        });
    });
});
