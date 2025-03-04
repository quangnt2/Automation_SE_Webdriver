package com.example.testCase;

import com.example.service.loginSevice;
import com.example.setUpDriver.setUpDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class loginTestCase extends setUpDriver {
    private loginSevice service;

    @BeforeMethod
    public void initService() {
        service = new loginSevice(getDriver());
    }

    @DataProvider(name = "loginAccounts")
    public Object[][] loginAccounts() {
        return new Object[][]{
                {"TAX"},
                {"lienpt"},
                {"trangpt"},
                {"DucDV"},
                {"AM"},
                {"DGM"},
                {"MG"},
                {"PIC"}
        };
    }

    @Test(priority = 1, dataProvider = "loginAccounts")
    public void TC_LOGIN_001_loginSuccessWithDefaultPassword(String username) {
        service.loginWithDefaultPassword(username);
    }
}
