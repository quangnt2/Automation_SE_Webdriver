package com.example.setUpDriver;

import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.FileNotFoundException;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class setUpDriver {
    public static WebDriver driver = null;
    @BeforeClass
    public void SetupDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        if (driver != null) {
            setAllureEnvironment(driver);
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
    //lấy thông tin version của trình duyệt
    public String version(WebDriver driver) {
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        String browserVersion = capabilities.getBrowserVersion();
        return browserVersion;
    }
    // lấy thong tin tên trình duyệt
    public String browser(WebDriver driver) {
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = capabilities.getBrowserName();
        return browserName;
    }
    // ghi thông tin os,ten và phiên bản driver vào báo cáo allure
    public void setAllureEnvironment(WebDriver driver) {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", browser(driver))
                        .put("Version", version(driver))
                        .put("OS", System.getProperty("os.name"))
                        .build(),
                System.getProperty("user.dir") + "/file - results/");
    }
    @AfterMethod
    public void afterMethod(ITestResult testResult) throws FileNotFoundException, InterruptedException {
        Thread.sleep(1000);
        takeScreenshotService screenshotService = new takeScreenshotService();
        screenshotService.takeScreenshotServiceI(testResult);
    }
}
