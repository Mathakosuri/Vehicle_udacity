 package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {
	   
	    private final WebDriver driver;
	 	@FindBy(id = "inputFirstName")
	    private WebElement firstNameField;

	 	@FindBy(id = "inputLastName")
	    private WebElement lastNameField;

	 	@FindBy(id = "inputUsername")
	    private WebElement usernameField;

	 	@FindBy(id = "inputPassword")
	    private WebElement passwordField;

	     @FindBy(id = "submit")
	    private WebElement submitButton;
	     
	   //  @FindBy(id = "success-msg")
	     @FindBy(xpath = "//*[@id=\"success-msg\"]")
	     private WebElement success;

	    public SignupPage(WebDriver driver) {
	    	
	    	this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    public void signup(String firstName, String lastName, String username, String password) {
	        this.firstNameField.sendKeys(firstName);
	        this.lastNameField.sendKeys(lastName);
	        this.usernameField.sendKeys(username);
	        this.passwordField.sendKeys(password);
	        this.submitButton.click();
	    }
	    
	    
	    public String getsignupSuccessText() {
	          WebDriverWait wait_modal = new WebDriverWait(driver, 40);
	          String  signupSuccessText=  wait_modal.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("success-msg")))).getText();
	              System.out.println(signupSuccessText);
	    	  return  signupSuccessText;

}
	    
	    
}
	    
	    
	    
	    
