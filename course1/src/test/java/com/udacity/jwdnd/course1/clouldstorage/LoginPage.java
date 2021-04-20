package com.udacity.jwdnd.course1.clouldstorage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(id="inputUsername")
	private WebElement usernameField;

    @FindBy(id="inputPassword")
    private WebElement passwordField;

    @FindBy(id="loginbutton")
    private WebElement submitButton;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password) {
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.submitButton.click();
    }
}


