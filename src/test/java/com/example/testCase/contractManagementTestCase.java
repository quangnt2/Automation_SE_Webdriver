package com.example.testCase;

import com.example.service.contractsManagement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class contractManagementTestCase extends loginPermission {
    private contractsManagement contractsManagement;

    @BeforeMethod
    public void beforeMethod() {
        contractsManagement = new contractsManagement(getDriver());
    }
    @Test(priority = 1)
    public void loginAccount() {
        contractsManagement = new contractsManagement(getDriver());
        loginAdminAccount();
    }
    @Test(priority = 2)
    public void clickMenuContract() {
        contractsManagement.clickMenuContract();
    }
    @Test(priority = 3)
    public void ContractResults() throws SQLException, InterruptedException {
        contractsManagement.ContractResults();
    }
    @Test(priority = 4)
    public void search() throws SQLException {
        contractsManagement.search();
    }
}
