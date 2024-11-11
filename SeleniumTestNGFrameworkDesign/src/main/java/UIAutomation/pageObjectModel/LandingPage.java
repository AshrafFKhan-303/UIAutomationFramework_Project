package UIAutomation.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UIAutomation.abstractComponenets.AbstractComponenet;

public class LandingPage extends AbstractComponenet{
    
	WebDriver driver = null;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	    PageFactory.initElements(driver, this);	
	}
	
	/*
	 * WebElement userEmail = driver.findElement(By.id("userEmail"));
	 * driver.findElement(By.id("userPassword")).sendKeys("Arahul@123#");
	 * driver.findElement(By.id("login")).click();
	 */	

	@FindBy(id="userPassword")
	 WebElement usePassword;
  
	@FindBy(id="userEmail")
	 WebElement userEmail;

	@FindBy(id="login")
	 WebElement login;
 
	//Customized CSS Selector
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public productPage loginApplication(String usermail,String userPassword) {
	
		userEmail.sendKeys(usermail);	
		usePassword.sendKeys(userPassword);	
		login.click();
		productPage productPage = new productPage(driver);
		return productPage;
	}
	
	public void goTo() {
	  driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		
		waitElementToBeAppear(errorMessage);
	   return errorMessage.getText();
	}
}
   	
