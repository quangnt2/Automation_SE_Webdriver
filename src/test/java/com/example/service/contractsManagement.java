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
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Fail.fail;

public class contractsManagement {
    private static WebDriver driver;
    private WebDriverWait wait;
    private commonResources resources;
    private saleManagementElement saleManagementElement;

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
        String count = queryData.queryCountContracts(query);
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
}