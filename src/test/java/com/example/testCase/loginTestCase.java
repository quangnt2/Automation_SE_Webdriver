package com.example.testCase;

import com.example.element.customerElement;
import com.example.service.contractsManagement;
import com.example.service.loginSevice;
import com.example.service.customer;
import com.example.setUpDriver.setUpDriver;
import org.testng.annotations.Test;

public class loginTestCase extends setUpDriver {
    private loginSevice service;
    private customer customer;
    private contractsManagement contractsManagement;


    @Test(priority = 0)
    public void login() throws InterruptedException {
        service = new loginSevice(getDriver());
        contractsManagement = new contractsManagement(getDriver());
        customer = new customer(getDriver());
        service.redirectURL("http://103.138.113.158:9912/");
        customer.navigateToCustomer();
        customer.selectPotential();
    }
    @Test(priority = 0)
    public void login1() throws InterruptedException {
        customer.navigateToCustomer();
    }

    @Test(priority = 1)
    public void loginAccountActive() throws InterruptedException {
        service.EmployeeLogin("admin", "123qwe");
        customer.navigateToCustomer();
    }

//    @Test(priority = 2)
//    public void EmptyUsername() throws InterruptedException {
//        service.EmptyUserNamePassword("", "12");
//    }
//
//    @Test(priority = 3)
//    public void EmptyPassword() throws InterruptedException {
//        service.EmptyUserNamePassword("admin", "");
//    }
//
//    @Test(priority = 3)
//    public void loginAccountInActive() throws InterruptedException {
//        service.checkStatusLoginAccount("02", "123");
//    }
//    @Test(priority = 4)
//    public void IncorrectPassword() throws InterruptedException {
//        service.checkStatusLoginAccount("admin","2345");
//    }
//    @Test(priority = 4)
//    public void IncorrectUserName() throws InterruptedException {
//        service.checkStatusLoginAccount("usxk","123");
//    }
}
