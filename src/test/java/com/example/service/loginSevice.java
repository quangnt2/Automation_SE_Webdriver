package com.example.service;

import com.example.element.loginElement;
import com.example.setUpDriver.commonResources;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class loginSevice {
    public static final String LOGIN_URL = "http://14.232.153.142:9920/account/login";
    public static final String DEFAULT_PASSWORD = "123";

    public WebDriver driver;
    private final WebDriverWait wait;
    private final commonResources commonResources;
    private final loginElement loginElement;

    public loginSevice(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        commonResources = new commonResources(driver);
        loginElement = new loginElement();
    }

    public void loginWithDefaultPassword(String username) {
        openLoginPage();
        loginWithoutOpeningPage(username);
    }

    public void loginAccount(String user, String pass) {
        fillLoginForm(user, pass);
        submitLoginForm();
        waitUntilLoggedIn(user);
    }

    private void waitUntilLoggedIn(String username) {
        boolean loggedIn = wait.until(driver -> hasAuthToken()
                || isElementVisible(loginElement.loggedInMarkers, 1)
                || isRedirectedOutOfLoginPage());

        Assert.assertTrue(loggedIn, String.format(
                "Đăng nhập không thành công với tài khoản %s. Lỗi hiển thị: %s",
                username, getErrorText()
        ));
    }

    public void loginWithoutOpeningPage(String username) {
        fillLoginForm(username, DEFAULT_PASSWORD);
        submitLoginForm();
        waitUntilLoggedIn(username);
    }

    public void openLoginPage() {
        driver.manage().deleteAllCookies();
        driver.get(LOGIN_URL);
        clearBrowserStorage();
        driver.get(LOGIN_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginElement.user));
    }

    private void fillLoginForm(String user, String pass) {
        commonResources.setText(loginElement.user, user);
        commonResources.setText(loginElement.pass, pass);
    }

    private void submitLoginForm() {
        commonResources.clickButton(loginElement.submit);
    }

    private boolean isRedirectedOutOfLoginPage() {
        return !driver.getCurrentUrl().contains("/account/login")
                && !isElementVisible(loginElement.user, 1);
    }

    private boolean hasAuthToken() {
        if (!(driver instanceof JavascriptExecutor)) {
            return false;
        }

        Object value = ((JavascriptExecutor) driver).executeScript(
                "return ["
                        + "window.localStorage.getItem('Abp.AuthToken'),"
                        + "window.localStorage.getItem('accessToken'),"
                        + "window.localStorage.getItem('authToken'),"
                        + "window.localStorage.getItem('encryptedAccessToken'),"
                        + "window.sessionStorage.getItem('Abp.AuthToken'),"
                        + "window.sessionStorage.getItem('accessToken'),"
                        + "document.cookie"
                        + "].filter(Boolean).join('|');"
        );

        String tokenText = value == null ? "" : value.toString();
        return tokenText.contains("Abp.AuthToken")
                || tokenText.toLowerCase().contains("accesstoken")
                || tokenText.toLowerCase().contains("authtoken")
                || tokenText.toLowerCase().contains("encryptedaccesstoken")
                || tokenText.length() > 40 && !driver.getCurrentUrl().contains("/account/login");
    }

    private void clearBrowserStorage() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.localStorage.clear(); window.sessionStorage.clear();");
        } catch (Exception ignored) {
        }
    }

    private String getErrorText() {
        StringBuilder errorText = new StringBuilder();
        for (WebElement element : driver.findElements(loginElement.allErrors)) {
            if (element.isDisplayed() && !element.getText().isBlank()) {
                errorText.append(element.getText()).append(" ");
            }
        }
        return errorText.toString().trim();
    }

    private boolean isElementVisible(By locator, int seconds) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException | NoSuchElementException ignored) {
            return false;
        }
    }
}
