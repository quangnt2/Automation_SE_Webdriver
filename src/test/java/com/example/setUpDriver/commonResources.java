package com.example.setUpDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

public class commonResources {
    public WebDriver driver;
    public WebDriverWait wait;

    public commonResources(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMinutes(20));
    }

    public void setText(By element, String text) {
        Scroll(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }
    public void clickButton(By element) {
        Scroll(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }
    public void Scroll(By element) {
        WebElement scroll = driver.findElement(element);
        if (element != null) {
            Actions actions = new Actions(driver);
            actions.moveToElement(scroll);
            int Offset = scroll.getSize().getHeight() / -2;
            actions.moveByOffset(0, Offset);
            actions.perform();
        } else {
            System.out.println("Không tim thấy phần tử");
        }
    }
    public String randomEmail() {
        String email = UUID.randomUUID().toString();
        email = email.substring(0, Math.min(email.length(), 4)) + "@gmail.com";
        return email;
    }
    public String randomPhone() {
        Random random = new Random();
        String[] networkCodes = {"032", "033", "034", "035", "036", "037", "038", "039", "070", "079", "077", "076", "078",
                "083", "084", "085", "081", "082",
                "086", "088", "089"};
        String ranDomNetworkCodes = networkCodes[random.nextInt(networkCodes.length)];
        String ranDomNumber = String.format("%05d", random.nextInt(9999));
        return ranDomNetworkCodes + ranDomNumber;
    }


    public static String randomAge() {
        Random random = new Random();
        int min = 1;
        int max = 100;
        String randomAges = String.valueOf(random.nextInt(max - min + 1));
        return randomAges;
    }

    public static String randomFullName() {
        Random random = new Random();
        String[] names = {"Nam", "Hoa", "Lan", "An", "Tùng", "Dương", "Minh", "Phương", "Anh", "Ngọc", "Huệ", "Ngọc", "Ánh", "Mai", "Đào", "Hà"};
        String[] surName = {"Nguyễn Văn", "Trần Thị", "Lê Minh", "Phạm Hồng", "Hoàng Quang", "Vũ Thị", "Đặng Văn", "Mai Thị", "Lý Văn", "Trịnh Thị"};
        String RandomNames = names[random.nextInt(names.length)];
        String RandomsurName = surName[random.nextInt(surName.length)];
        return RandomsurName + " " + RandomNames;
    }

    public static String randomId() {
        Random random = new Random();
        String randomId = String.valueOf(random.nextInt(100));
        return randomId;
    }

}
