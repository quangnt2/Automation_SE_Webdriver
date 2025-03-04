package com.example.service;

import com.example.element.loginElement;
import com.example.element.profileElement;
import com.example.setUpDriver.commonResources;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class profileService {
    private static final String PROFILE_URL = "https://vnhr.vn/profile?lang=vi";
    private static final String VALID_EMAIL = "Quangnt09176@gmail.com";
    private static final String VALID_PASSWORD = "Quangnt2";

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final commonResources commonResources;
    private final profileElement profileElement;
    private final loginElement loginElement;

    public profileService(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.commonResources = new commonResources(driver);
        this.profileElement = new profileElement();
        this.loginElement = new loginElement();
    }

    public void openProfileAfterLogin() {
        loginSevice loginSevice = new loginSevice(driver);
        loginSevice.openLoginPage();
        loginSevice.loginAccount(VALID_EMAIL, VALID_PASSWORD);
        driver.get(PROFILE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileElement.profileForm));
    }

    public void TC_PROFILE_001_openProfileSuccessfully() {
        openProfileAfterLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("/profile"));
        Assert.assertTrue(getText(profileElement.greetingTitle).contains("Xin chào"));
        Assert.assertTrue(isVisible(profileElement.activePersonalTab), "Tab Thông tin cá nhân chưa active.");
    }

    public void TC_PROFILE_002_profileMenuTabsDisplayed() {
        openProfileAfterLogin();
        List<String> actualTabs = driver.findElements(profileElement.menuTabs)
                .stream()
                .map(element -> element.getText().trim())
                .filter(text -> !text.isBlank())
                .collect(Collectors.toList());
        List<String> expectedTabs = Arrays.asList(
                "Thông tin cá nhân",
                "Thông tin doanh nghiệp",
                "Lời mời doanh nghiệp",
                "Sự kiện đặt vé",
                "Mã giảm giá",
                "Lịch sử đơn hàng",
                "Danh sách tài liệu đã tải",
                "Thông báo",
                "Đăng xuất"
        );

        for (String expectedTab : expectedTabs) {
            Assert.assertTrue(actualTabs.contains(expectedTab), "Thiếu tab Profile: " + expectedTab);
        }
    }

    public void TC_PROFILE_003_accountFieldsDisplayedAndPrefilled() {
        openProfileAfterLogin();
        assertInputHasValue(profileElement.fullName, "Họ và tên");
        assertInputHasValue(profileElement.phoneNumber, "Số điện thoại");
        assertInputHasValue(profileElement.email, "Email đăng nhập");
        assertInputHasValue(profileElement.dateOfBirth, "Ngày sinh");
        assertInputHasValue(profileElement.address, "Địa chỉ");
        Assert.assertFalse(getSelectedText(profileElement.gender).isBlank(), "Giới tính chưa có dữ liệu.");
    }

    public void TC_PROFILE_004_readonlyEmailAndPassword() {
        openProfileAfterLogin();
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(profileElement.email));
        WebElement password = driver.findElement(profileElement.password);

        Assert.assertEquals(email.getAttribute("readonly"), "true", "Email đăng nhập phải readonly.");
        Assert.assertEquals(password.getAttribute("readonly"), "true", "Mật khẩu đăng nhập phải readonly.");
        Assert.assertEquals(password.getAttribute("type"), "password", "Mật khẩu phải được che.");
        Assert.assertTrue(isVisible(profileElement.lockIcon), "Không thấy icon khóa mật khẩu.");
    }

    public void TC_PROFILE_005_profileSectionsDisplayed() {
        openProfileAfterLogin();
        assertVisible(profileElement.sectionTitle("Tài khoản của tôi"), "Không thấy section Tài khoản của tôi.");
        assertVisible(profileElement.sectionTitle("Thông tin cá nhân"), "Không thấy section Thông tin cá nhân.");
        assertVisible(profileElement.sectionTitle("Thông tin doanh nghiệp"), "Không thấy section Thông tin doanh nghiệp.");
        assertVisible(profileElement.sectionTitle("Về VNHR"), "Không thấy section Về VNHR.");

        assertInputHasValue(profileElement.workProvince, "Nơi công tác chính");
        assertInputHasValue(profileElement.yearsOfExperience, "Số năm kinh nghiệm");
        assertInputHasValue(profileElement.companyName, "Tên công ty");
        Assert.assertFalse(getSelectedText(profileElement.positionLevel).isBlank(), "Vị trí cấp bậc chưa có dữ liệu.");
        Assert.assertFalse(getSelectedText(profileElement.knowVnhrFrom).isBlank(), "Nguồn biết đến VNHR chưa có dữ liệu.");
        Assert.assertFalse(getSelectedText(profileElement.reasonJoiningVnhr).isBlank(), "Lý do tham gia VNHR chưa có dữ liệu.");
    }

    public void TC_PROFILE_006_requiredFullNameValidation() {
        openProfileAfterLogin();
        String originalFullName = getValue(profileElement.fullName);

        setText(profileElement.fullName, "");
        clickUpdate();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(profileElement.fullNameError, "Họ và tên không được để trống"));
        Assert.assertTrue(getText(profileElement.fullNameError).contains("Họ và tên không được để trống"));

        setText(profileElement.fullName, originalFullName);
    }

    public void TC_PROFILE_007_invalidPhoneValidation() {
        openProfileAfterLogin();
        String originalPhone = getValue(profileElement.phoneNumber);

        setText(profileElement.phoneNumber, "abc");
        clickUpdate();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(profileElement.phoneNumberError, "Số điện thoại không hợp lệ"));
        Assert.assertTrue(getText(profileElement.phoneNumberError).contains("Số điện thoại không hợp lệ"));

        setText(profileElement.phoneNumber, originalPhone);
    }

    public void TC_PROFILE_008_extendMembershipNavigation() {
        openProfileAfterLogin();
        commonResources.clickButton(profileElement.extendButton);
        wait.until(ExpectedConditions.urlContains("/profile/join-membership"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/profile/join-membership"));
    }

    public void TC_PROFILE_009_logoutFromProfile() {
        openProfileAfterLogin();
        commonResources.clickButton(profileElement.logoutTab);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginElement.btnLogout));
        Assert.assertFalse(isVisible(loginElement.btnLogout), "Sau logout vẫn còn thấy trạng thái đăng nhập.");
    }

    private void clickUpdate() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(profileElement.updateButton));
        scrollIntoView(button);
        button.click();
    }

    private void setText(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        scrollIntoView(element);
        element.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);
    }

    private void assertInputHasValue(By locator, String fieldName) {
        Assert.assertFalse(getValue(locator).isBlank(), fieldName + " chưa có dữ liệu.");
    }

    private String getValue(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute("value").trim();
    }

    private String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
    }

    private String getSelectedText(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return new Select(element).getFirstSelectedOption().getText().trim();
    }

    private void assertVisible(By locator, String message) {
        Assert.assertTrue(isVisible(locator), message);
    }

    private boolean isVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception ignored) {
            return false;
        }
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
}
