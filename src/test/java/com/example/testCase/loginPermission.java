package com.example.testCase;

import com.example.service.loginSevice;
import com.example.setUpDriver.setUpDriver;

public class loginPermission extends setUpDriver {
    private loginSevice sevice;
    public void loginAdminAccount() {
        sevice = new loginSevice(getDriver());
        sevice.redirectURL("http://tnm30test.tringhiatech.vn:9902/");
        sevice.loginAccount("admin", "123");
    }
}
