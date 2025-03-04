package com.example.element;

import org.openqa.selenium.By;

public class profileElement {
    public By profileForm = By.id("signup-form");
    public By greetingTitle = By.cssSelector(".signup-title");
    public By activePersonalTab = By.cssSelector(".link-menu-top.active-link[href='/profile']");
    public By menuTabs = By.cssSelector(".menuTopProfile .link-menu-top");
    public By logoutTab = By.cssSelector(".menuTopProfile a[href*='/site/logout']");

    public By fullName = By.id("abpuser-fullname");
    public By phoneNumber = By.id("abpuser-phonenumber");
    public By email = By.id("abpuser-emailaddress");
    public By password = By.id("abpuser-passwordchange");
    public By dateOfBirth = By.id("abpuser-dateofbirth");
    public By gender = By.id("abpuser-gender");
    public By address = By.id("abpuser-address");
    public By fullNameError = By.cssSelector(".field-abpuser-fullname .invalid-feedback");
    public By phoneNumberError = By.cssSelector(".field-abpuser-phonenumber .invalid-feedback");
    public By lockIcon = By.cssSelector(".field-abpuser-passwordchange .edit-password");

    public By workProvince = By.id("abpuser-wishworkingprovinceid");
    public By yearsOfExperience = By.id("abpuser-yearsofexperience");
    public By isWorkingHrNo = By.cssSelector("input[name='AbpUser[IsWorkingHr]'][value='0']");
    public By yearsOfExperienceHr = By.id("abpuser-yearsofexperiencehr");
    public By education = By.id("abpuser-highestlevelofeducation");
    public By specialty = By.id("abpuser-specialtyid");
    public By tradeOpportunityYes = By.cssSelector("input[name='AbpUser[IsTradeOpportunities]'][value='1']");

    public By isWorkingBusinessNo = By.cssSelector("input[name='AbpUser[IsWorkingBusiness]'][value='0']");
    public By companyName = By.id("abpuser-companyname");
    public By positionLevel = By.id("abpuser-positionlevel");
    public By companySize = By.id("abpuser-companysize");
    public By hrDepartmentSize = By.id("abpuser-hrdepartmentsize");
    public By career = By.id("abpuser-array_career");
    public By organizationType = By.id("abpuser-organizationtype");

    public By knowVnhrFrom = By.id("abpuser-knowvnhrfromid");
    public By reasonJoiningVnhr = By.id("abpuser-reasonjoiningvnhrid");

    public By updateButton = By.cssSelector("button.btn-submit[type='submit']");
    public By extendButton = By.cssSelector("button.extendProfile");
    public By sectionTitle(String title) {
        return By.xpath("//span[normalize-space()='" + title + "']");
    }
}
