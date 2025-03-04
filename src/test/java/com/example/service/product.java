package com.example.service;

import com.example.element.productElement;
import com.example.setUpDriver.commonResources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Fail.fail;

public class product {
    private static WebDriver driver;
    private WebDriverWait wait;
    private productElement element;
    public loginSevice loginSevice;
    private commonResources commonResources;

    public product(WebDriver driver) {
        this.driver = driver;
        element = new productElement();
        loginSevice = new loginSevice(driver);
        commonResources = new commonResources(driver);
        wait = new WebDriverWait(driver, Duration.ofMinutes(20000));
    }

    public void createProductUrl() throws InterruptedException {
        driver.get("");
    }
    public void kiemtracactruongbatbuoc(){
        commonResources.setText(element.itemCode,"");
    }
}
