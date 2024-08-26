package com.example.setUpDriver;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class takeScreenshotService {
    public void takeScreenshotServiceI(ITestResult testResult) throws FileNotFoundException {
        SimpleDateFormat dateFormatTimeVI = new SimpleDateFormat("EEEE_yyyy_MM_dd_HH_mm");
        SimpleDateFormat dateFormatTimeEN = new SimpleDateFormat("EEEE_yyyy_MM_dd");
        String dateVI = dateFormatTimeVI.format(new Date());
        String dateEN = dateFormatTimeEN.format(new Date());
        WebDriver driver = setUpDriver.getDriver();
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File source = takesScreenshot.getScreenshotAs(OutputType.FILE); // đầu ra là dạng file
            String fileName = testResult.getName() + "_" + dateVI + ".png"; // tên file với định dạng là png
            String filePath = "./screenshots/" + dateEN + "/" + fileName; // đuơng dẫn lưu file
            try {
                FileUtils.forceMkdir(new File("./screenshots/"));
                FileUtils.copyFile(source, new File(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Allure.addAttachment(fileName, new FileInputStream(filePath));
        }
    }
}
