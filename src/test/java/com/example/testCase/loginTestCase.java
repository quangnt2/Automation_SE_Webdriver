package com.example.testCase;

import com.example.service.contractsManagement;
import com.example.service.loginSevice;
import com.example.setUpDriver.setUpDriver;
import org.testng.annotations.Test;

public class loginTestCase extends setUpDriver {
    private loginSevice sevice;
    private contractsManagement contractsManagement;

    @Test(priority = 0)
    public void login() {
        sevice = new loginSevice(getDriver());
        contractsManagement = new contractsManagement(getDriver());
        sevice.redirectURL("http://tnm30test.tringhiatech.vn:9902/");
    }

    @Test(priority = 1)
    public void loginAccountActive() throws InterruptedException {
        sevice.loginAccountActive("NTQ01", "123");
    }

    @Test(priority = 2)
    public void wrongLoginAccountPassword() throws InterruptedException {
        sevice.checkStatusLoginAccount("NTQ01", "12");
    }

    @Test(priority = 3)
    public void loginAccountInActive() throws InterruptedException {
        sevice.checkStatusLoginAccount("02", "123");
    }


}
