package com.example.testCase;

import com.example.service.loginSevice;
import com.example.setUpDriver.setUpDriver;

public class loginPermission extends setUpDriver {
    String url = "http://tnm30test.tringhiatech.vn:9902/";
    public void loginAdminAccount() {
        loginSevice service = new loginSevice(getDriver());
        service.redirectURL(url);
        service.loginAccount("admin", "123");
    }
    public void customerLogin() throws InterruptedException {
        loginSevice service = new loginSevice(getDriver());
        service.redirectURL(url);
        service.CustomerLogin("098230981293", "1");
    }
    public void employeeLogin() throws InterruptedException {
        loginSevice service = new loginSevice(getDriver());
        service.redirectURL(url);
        service.EmployeeLogin("sale", "1");
    }
}
