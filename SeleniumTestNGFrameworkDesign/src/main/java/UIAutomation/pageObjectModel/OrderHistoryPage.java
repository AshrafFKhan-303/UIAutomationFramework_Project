package UIAutomation.pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UIAutomation.abstractComponenets.AbstractComponenet;

public class OrderHistoryPage extends AbstractComponenet{


WebDriver driver=null;
	
	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	    PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderItemList;
	
	@FindBy(css=" .subtotal button")
	  WebElement checkOutButton;

	
	public boolean verifyOderDisplay(String productName) {
		boolean match = orderItemList.stream().anyMatch(items -> items.getText().equalsIgnoreCase(productName));
		return match;
	}

}
