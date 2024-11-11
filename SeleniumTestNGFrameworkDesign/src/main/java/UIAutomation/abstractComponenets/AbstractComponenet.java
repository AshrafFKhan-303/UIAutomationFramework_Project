package UIAutomation.abstractComponenets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import UIAutomation.pageObjectModel.CartPage;
import UIAutomation.pageObjectModel.OrderHistoryPage;

public class AbstractComponenet {

  WebDriver driver;	
  public AbstractComponenet(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}

  @FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;
  

  @FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
  

	public CartPage goToCartPage() {

		cartButton.click();
		CartPage cartPage =new CartPage(driver);
		return cartPage;
	}
	
	public OrderHistoryPage goToOrderPage() {
		orderHeader.click();
		OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
		return orderHistoryPage;
	}
  
  public void waitElementToBeAppear(By findBy){	
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
  }
  
  public void waitElementToBeAppear(WebElement element){	
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.visibilityOf(element));
  }


  public void waitElementToBeDissAppear(WebElement element){	
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.invisibilityOf(element));
  }

}
