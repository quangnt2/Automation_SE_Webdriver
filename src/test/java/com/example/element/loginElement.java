package com.example.element;

import org.openqa.selenium.By;

public class loginElement {
    public By loginForm = By.cssSelector("form, app-login, app-account, account-login");
    public By user = By.cssSelector("input[name='userNameOrEmailAddress'], input[name='UsernameOrEmailAddress'], input#UserName, input[type='text']");
    public By pass = By.cssSelector("input[name='password'], input[name='Password'], input#Password, input[type='password']");
    public By submit = By.cssSelector("button[type='submit'], input[type='submit']");
    public By loading = By.cssSelector(".root-initial-loader, .overlay, .spinner-border, .fa-spin");
    public By allErrors = By.cssSelector(".validation-summary-errors, validation-messages, .invalid-feedback, .alert, .text-danger, .swal2-html-container");
    public By loggedInMarkers = By.cssSelector(
            "a[href*='logout'], button[title*='Logout'], app-sidebar, .app-sidebar, .aside, .aside-menu, .header-user, .user-menu, [data-username]"
    );
    public By btnLogout = loggedInMarkers;
}
