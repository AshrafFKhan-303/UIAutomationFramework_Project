package pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    
	WebDriver driver = null;
	
	public LandingPage(WebDriver driver) {
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
 
	public void loginApplication(String usermail,String userPassword) {
		userEmail.sendKeys(usermail);	
		usePassword.sendKeys(userPassword);	
		login.click();
	}
	
	public void goTo() {
	  driver.get("https://rahulshettyacademy.com/client");
	}
}
   	
