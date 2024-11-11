package UIAutomation.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UIAutomation.abstractComponenets.AbstractComponenet;

public class CartPage extends AbstractComponenet{

	WebDriver driver=null;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	    PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartItemList;
	
	@FindBy(css=" .subtotal button")
	  WebElement checkOutButton;

	
	public boolean verifyCartItemByName(String productName) {
		boolean match = cartItemList.stream().anyMatch(items -> items.getText().equalsIgnoreCase(productName));
		return match;
	}

	public OrderPage goToCheckOut() {
		checkOutButton.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
				
	}
}
