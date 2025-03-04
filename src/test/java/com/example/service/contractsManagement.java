package com.example.service;

import com.example.element.saleManagementElement;
import com.example.queryData.queryData;
import com.example.setUpDriver.commonResources;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static com.example.queryData.queryData.contractsListColum;
import static com.example.queryData.queryData.queryCountContracts;
import static org.assertj.core.api.Fail.fail;

public class contractsManagement {
    private static WebDriver driver;
    private WebDriverWait wait;
    private commonResources resources;
    private static saleManagementElement saleManagementElement;
    private static queryData query;

    public contractsManagement(WebDriver driver) {
        this.driver = driver;
        resources = new commonResources(driver);
        saleManagementElement = new saleManagementElement();
        query = new queryData();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickMenuContract() {
        resources.clickButton(saleManagementElement.saleManagementMenu);
        resources.clickButton(saleManagementElement.contract);
        try {
            driver.get("http://tnm30test.tringhiatech.vn:9902/app/sale-management/contract/list");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Danh sách hợp đồng bán hàng')]")));
        } catch (NoSuchElementException e) {
            fail("Không truy cập được vào màn hình ");
        }
    }

    public void checkTitleContract() {
        driver.get("http://tnm30test.tringhiatech.vn:9902/app/sale-management/contract/list");
        WebElement element = driver.findElement(By.xpath("//h5[contains(text(),'Danh sách hợp đồng bán hàng')]"));
        String title = element.getText();
        String expected = "Danh sách hợp đồng bán hàng";
        Assert.assertEquals(title, expected);
    }

    public void ContractResults() throws SQLException, InterruptedException {
        String NumberRecord = splitCountNumber("/html[1]/body[1]/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/app-contract-list[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/p-treetable[1]/div[1]/p-paginator[1]/div[1]/span[1]");
        String query = "select count(\"Id\") from \"Contracts\"";
        String count = queryCountContracts(query);
        System.out.println("query:" + NumberRecord + "count:" + count);
        Assert.assertEquals(NumberRecord, count);
    }

    public static String splitCountNumber(String elm) throws InterruptedException {
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.xpath(elm));
        String getText = element.getText();
        String[] str = getText.split("\\s");
        String numberRecord = str[6];
        return numberRecord;
    }

    public void search() throws SQLException {
        Random ran = new Random();
        List<String> list = query.contractRecord();
        if (list.isEmpty()) {
            int index = ran.nextInt(list.size());
            String getDataRandom = list.get(index);
            resources.setText(saleManagementElement.searchContracts, getDataRandom);
            WebElement element = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/app-contract-list[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/p-treetable[1]/div[1]/div[1]"));
            List<WebElement> rows = element.findElements(By.tagName("tr"));
            String data = "";
            for (WebElement row : rows) {
                List<WebElement> cell = row.findElements(By.xpath(".//td[3]"));
                for (WebElement w : cell) {
                    data += w.getText();
                    System.out.println(data);
                }
            }
        } else {
            System.out.println("Xử lý nếu danh sách trống");
        }
    }

    // ------------------------------------------DASHBOARD-----------------------------------------------------------
    public void CheckDateFormatTimeVI() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, 'ngày 'dd' tháng 'MM' năm' yyyy", new Locale("VI", "VN"));
        String date = dateFormat.format(new Date());
        WebElement element = driver.findElement(saleManagementElement.dateDashboard);
        String getText = element.getText();
        Assert.assertEquals(date, getText);
    }

    public void totalNumberOfContracts() throws SQLException {
        WebElement element = driver.findElement(saleManagementElement.totalNumberOfContracts);
        String getText = element.getText();
        String[] str = getText.split("\\s");
        String getNumberOfContracts = str[5];
        String query = "select count(*) from \"Contracts\"";
        String countContractInDatabase = queryCountContracts(query);
        Assert.assertEquals(getNumberOfContracts, countContractInDatabase);
    }

    public void SearchContractsNumber() throws SQLException {
        String queryContractsNumber = "select \"ContractNumber\" from \"Contracts\"";
        String contractsNumber = randomData(queryContractsNumber);
        resources.setText(saleManagementElement.searchContracts, contractsNumber);
        WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[3]/span[1]"));
        String getText = element.getText();
        Assert.assertEquals(contractsNumber, getText);
    }

    public void SearchContractsName() throws SQLException {
        String queryContractsNumber = "select \"ContractName\" from \"Contracts\"";
        String contractsNumber = randomData(queryContractsNumber);
        resources.setText(saleManagementElement.searchContracts, contractsNumber);
        WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/span[1]"));
        String getText = element.getText();
        Assert.assertEquals(contractsNumber, getText);
    }

    public void SearchCustomer() throws SQLException {
        String queryContractsNumber = "select DISTINCT (C.\"Name\")\n" +
                "from \"Contracts\" AS CT\n" +
                "         JOIN \"Customers\" AS C\n" +
                "              ON CT.\"CustomerId\" = C.\"Id\"";
        String contractsNumber = randomData(queryContractsNumber);
        resources.setText(saleManagementElement.searchContracts, contractsNumber);
        WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[5]/span[1]"));
        String getText = element.getText();
        Assert.assertEquals(contractsNumber, getText);
    }

    public void SearchEmployee() throws SQLException {
        String sql = "select  concat(A.\"Surname\",' ',A.\"Name\") as FullName\n" +
                "from \"Contracts\" as C\n" +
                "         join \"Employees\" As E on C.\"SalespersonId\" = E.\"Id\"\n" +
                "         join \"AbpUsers\" as A on E.\"UserId\" = A.\"Id\";";
        String dataRandom = randomData(sql);
        resources.setText(saleManagementElement.searchContracts, dataRandom);
        WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[7]/span[1]"));
        String getText = element.getText();
        Assert.assertEquals(dataRandom, getText);

    }

    public static String randomData(String sql) throws SQLException {
        List<String> listData = contractsListColum(sql);
        Random ran = new Random();
        int index = ran.nextInt(listData.size());
        String data = listData.get(index);
        return data;
    }

    public void FVContracts() {
        resources.clickButton(saleManagementElement.createContracts);
        WebElement element = driver.findElement(saleManagementElement.dropListCustomer);
        List<WebElement> elements = element.findElements(saleManagementElement.dataInDropList);
        if (elements.isEmpty()) {
            List<String> list = new ArrayList<>();
            for (WebElement data : elements) {
                String name = data.getText();
                list.add(name);
            }
            if (list.isEmpty()) {
                Random ran = new Random();
                int index = ran.nextInt(list.size());
                String getCustomer = list.get(index);
                resources.setText(saleManagementElement.searchCustomer, getCustomer);
            }
        }
    }
}