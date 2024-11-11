package UIAutomation.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UIAutomation.abstractComponenets.AbstractComponenet;

public class OrderPage extends AbstractComponenet{

	WebDriver driver = null;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	    PageFactory.initElements(driver, this); 
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement contryTextBox;
	
	By countryList = By.cssSelector(".ta-results");
	
	@FindBy(xpath = "//button[contains(@class, 'ta-item')][2]")
	WebElement selectContry;
	
	@FindBy(css=".action__submit")
	WebElement submit;

	
	public void selectCountry(String country) throws Exception {	  
		Actions action = new Actions(driver);
		action.sendKeys(contryTextBox, country).build().perform();
	    waitElementToBeAppear(countryList);
	    selectContry.click();
	    Thread.sleep(500);
	}
	
	public ConfirmationPage submitOrder() {
		
		submit.click();
	    return new ConfirmationPage(driver);
	}
	
}
