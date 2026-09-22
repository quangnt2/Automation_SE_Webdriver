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
    public void loginAccount() throws SQLException, InterruptedException {
        contractsManagement = new contractsManagement(getDriver());
        loginAdminAccount();
        contractsManagement.clickMenuContract();
        contractsManagement.ContractResults();
        contractsManagement.search();
    }
}
