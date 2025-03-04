package com.example.testCase;

import com.example.service.profileService;
import com.example.setUpDriver.setUpDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class profileTestCase extends setUpDriver {
    private profileService service;

    @BeforeMethod
    public void initService() {
        service = new profileService(getDriver());
    }

    @Test(priority = 1)
    public void TC_PROFILE_001_openProfileSuccessfully() {
        service.TC_PROFILE_001_openProfileSuccessfully();
    }

    @Test(priority = 2)
    public void TC_PROFILE_002_profileMenuTabsDisplayed() {
        service.TC_PROFILE_002_profileMenuTabsDisplayed();
    }

    @Test(priority = 3)
    public void TC_PROFILE_003_accountFieldsDisplayedAndPrefilled() {
        service.TC_PROFILE_003_accountFieldsDisplayedAndPrefilled();
    }

    @Test(priority = 4)
    public void TC_PROFILE_004_readonlyEmailAndPassword() {
        service.TC_PROFILE_004_readonlyEmailAndPassword();
    }

    @Test(priority = 5)
    public void TC_PROFILE_005_profileSectionsDisplayed() {
        service.TC_PROFILE_005_profileSectionsDisplayed();
    }

    @Test(priority = 6)
    public void TC_PROFILE_006_requiredFullNameValidation() {
        service.TC_PROFILE_006_requiredFullNameValidation();
    }

    @Test(priority = 7)
    public void TC_PROFILE_007_invalidPhoneValidation() {
        service.TC_PROFILE_007_invalidPhoneValidation();
    }

    @Test(priority = 8)
    public void TC_PROFILE_008_extendMembershipNavigation() {
        service.TC_PROFILE_008_extendMembershipNavigation();
    }

    @Test(priority = 9)
    public void TC_PROFILE_009_logoutFromProfile() {
        service.TC_PROFILE_009_logoutFromProfile();
    }
}
