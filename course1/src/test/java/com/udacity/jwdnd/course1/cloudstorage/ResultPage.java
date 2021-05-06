 package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;

public class ResultPage {
	// fields:
    @FindBy(tagName = "a")
    private WebElement backHomeLink;
    @FindBy(xpath = "/html/body/div/div/h1")
    private WebElement resultSuccess;
    
    
    private final WebDriver driver;
    // constructor:
    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // methods:
    public void clickHereBtn() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.backHomeLink);
    }
    
    public String resultStatus() {
    	System.out.println(resultSuccess.getText());
    	return resultSuccess.getText();
    }
}
