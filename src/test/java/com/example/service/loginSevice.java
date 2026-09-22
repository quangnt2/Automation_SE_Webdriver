package com.example.service;

import com.example.element.loginElement;
import com.example.setUpDriver.commonResources;
import com.example.setUpDriver.takeScreenshotService;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static org.testng.Assert.fail;

public class loginSevice {
    public WebDriver driver;
    private WebDriverWait wait;
    private commonResources commonResources;
    private loginElement loginElement;
    private takeScreenshotService screenshotService;

    public loginSevice(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        commonResources = new commonResources(driver);
        loginElement = new loginElement();
        screenshotService = new takeScreenshotService();
    }

    public void redirectURL(String URL) {
        driver.get(URL);
        //visibilityOfElementLocated chờ phần tử xuất hiện
        //invisibilityOfElementLocated chờ phần tử bị ẩn
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("userNameOrEmailAddress")));
    }

    public void loginAccountActive(String user, String pass) {
        commonResources.setText(loginElement.user, user);
        commonResources.setText(loginElement.pass, pass);
        commonResources.clickButton(loginElement.submit);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginElement.btnProfile));
            commonResources.clickButton(loginElement.btnProfile);
            commonResources.clickButton(loginElement.btnLogout);
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(loginElement.user));
            }catch (NoSuchElementException e){
                fail("Đăng xuất không thành công");
            }
        } catch (NoSuchElementException e) {
            fail("Không đăng nhập thành coong");
        }
    }

    public void checkStatusLoginAccount(String user, String pass) throws InterruptedException {
        commonResources.setText(loginElement.user, user);
        commonResources.setText(loginElement.pass, pass);
        commonResources.clickButton(loginElement.submit);
        Thread.sleep(3000);
        String url = driver.getCurrentUrl();
        String expectUrl = "http://tnm30test.tringhiatech.vn:9902/account/login";
        if (!url.equals(expectUrl)) {
            fail("Lỗi ");
        }
        if (url.equals(expectUrl)) {
            try {
                WebElement webElement = driver.findElement(loginElement.popup);
                WebElement element = webElement.findElement(By.id("swal2-title"));
                String getText = element.getText();
                String messagePassEn = "Invalid user name or password";
                String messagePassVI = "Sai tên truy cập hoặc mật khẩu";
                if (getText.equals(messagePassVI) || getText.equals(messagePassEn)) {
                    commonResources.clickButton(loginElement.config);
                }
                if (getText.contains("is not active and can not log in.") || getText.contains("không hoạt động và không thể đăng nhập.")) {
                    commonResources.clickButton(loginElement.config);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void loginAccount(String user, String pass) {
        commonResources.setText(loginElement.user, user);
        commonResources.setText(loginElement.pass, pass);
        commonResources.clickButton(loginElement.submit);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginElement.btnProfile));
            commonResources.clickButton(loginElement.language);
            commonResources.clickButton(loginElement.getLanguage);
            Thread.sleep(3000);
        } catch (NoSuchElementException e) {
            fail("Không đăng nhập thành coong");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
