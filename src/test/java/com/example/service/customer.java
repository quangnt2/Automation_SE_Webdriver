package com.example.service;

import com.example.element.customerElement;
import com.example.setUpDriver.commonResources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Fail.fail;

public class customer {
    private static WebDriver driver;
    private WebDriverWait wait;
    private customerElement element;
    public loginSevice loginSevice;
    private commonResources commonResources;

    public customer(WebDriver driver) {
        this.driver = driver;
        element = new customerElement();
        loginSevice = new loginSevice(driver);
        commonResources = new commonResources(driver);
        wait = new WebDriverWait(driver, Duration.ofMinutes(20000));
    }

    public void navigateToCustomer() throws InterruptedException {
        String checkUrl = "http://103.138.113.158:9912/account/login";
        String url = driver.getCurrentUrl();
        if (url.equals(checkUrl)) {
            loginSevice.EmployeeLogin("admin", "123qwe");
        }
        driver.get("http://103.138.113.158:9912/app/customer-management/customer-list");
        Thread.sleep(5000);
    }

    public void selectPotential() throws InterruptedException {
        commonResources.clickButton(element.potential);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
        WebElement table = driver.findElement(By.tagName("table"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> colum = rows.get(i).findElements(By.tagName("td"));
            int sis = colum.size();
//            if (colum.size() > 1) {
//                String SDT = colum.get(2).getText();
            System.out.printf(String.valueOf(sis));
        }




}

public void selectCustomer() throws InterruptedException {
    commonResources.clickButton(element.customer);
}
//
//
//    public void verifySupplierListPageFeatures() {
//        String[] listButton = {"Xuất excel", "Nhập từ excel", "Tạo mới", "Bộ lọc"};
//        List<WebElement> elementList = driver.findElements(By.tagName("button"));
//        for (int i = 0; i <= listButton.length; i++) {
//            WebElement element = elementList.get(i);
//            String getName = element.getText();
//            Assert.assertEquals(listButton[i], getName);
//        }
//    }
//
//    public void verifySupplierLabelInTable() {
//        String[] labels = {"Mã nhà cung cấp", "Tên nhà cung cấp", "Nhóm nhà cung cấp", "Điểm đánh giá chung", "Ngày đánh giá gần nhất", "Thao tác"};
//        List<WebElement> actualColumnNames = driver.findElements(By.xpath(".//thead/tr/th"));
//        List<String> listData = new ArrayList<>();
//        for (WebElement webElement : actualColumnNames) {
//            listData.add(webElement.getText().trim());
//        }
//        Assert.assertEquals(labels.length, actualColumnNames.size(), "Number of columns does not match!");
//        for (int i = 0; i < labels.length; i++) {
//            Assert.assertEquals(labels[i], listData.get(i));
//        }
//    }
//
//    public void verifySupplierCountByAccount() {
//        WebElement element = driver.findElement(By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[2]/app-supplier-list[1]/div[1]/div[1]/div[1]/div[1]/div[2]"));
//        String getText = element.getText();
//        String[] regex = getText.split("\\s");
//        String getIndex = regex[1];
//    }


}
