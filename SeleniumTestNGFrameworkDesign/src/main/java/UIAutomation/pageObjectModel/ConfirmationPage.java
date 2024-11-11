package UIAutomation.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import UIAutomation.abstractComponenets.AbstractComponenet;

public class ConfirmationPage extends AbstractComponenet {

	WebDriver driver=null;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	    PageFactory.initElements(driver, this);	

	}
	
	@FindBy(css=".hero-primary")
	  WebElement confirmationMessage;

	public String getConirmationMessage() {
		return confirmationMessage.getText();
	}
	
	
	//String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	// Assert.assertEquals(" Thankyou for the order. ", confirmMessage);
	//Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


}
