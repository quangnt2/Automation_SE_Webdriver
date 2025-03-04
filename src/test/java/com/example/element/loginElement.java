package com.example.element;

import org.openqa.selenium.By;

public class loginElement {
    public By user = By.name("userNameOrEmailAddress");
    public By pass = By.name("password");
    public By submit = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/ng-component[1]/div[1]/form[1]/div[4]");
    public By btnProfile = By.id("kt_quick_user_toggle");
    public By btnLogout = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]/div[1]/div[2]/div[1]/user-menu[1]/div[1]/div[2]/div[5]/a[1]");
    public By popup = By.xpath("//body/div[1]/div[1]");
    public By config = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[6]/button[1]");
    public By swal2 = By.xpath("//body/div[1]/div[1]");
    public By language = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]/div[1]/div[2]/div[1]/language-switch-dropdown[1]/div[1]/div[1]");
    public By getLanguage = By.xpath("//body/app-root[1]/ng-component[1]/div[1]/default-layout[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]/div[1]/div[2]/div[1]/language-switch-dropdown[1]/div[2]/div[11]/a[1]");
}
