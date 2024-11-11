package UIAutomation.pageObjectModel;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import UIAutomation.abstractComponenets.AbstractComponenet;

public class productPage extends AbstractComponenet {

	WebDriver driver = null;
	
	public productPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	    PageFactory.initElements(driver, this); 
	}
	

	@FindBy(css=".col-lg-4")
	List<WebElement> products;
	
	By listOfProductsApper = By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector("button:last-of-type");
	
	By toastMessage = By.cssSelector("#toast-container");
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	public List<WebElement> getProductList() {
		waitElementToBeAppear(listOfProductsApper);
//		java.util.List<WebElement> products;
//		products = driver.findElements(By.cssSelector(".col-lg-4"));
//	    return (List<WebElement>) products;
	    return products;
	}
	
	public WebElement getProductByName(String productName) {

		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		
		prod.findElement(addToCart).click();
		
		//Elemet to apper
		waitElementToBeAppear(toastMessage);
		
		//Elemet tbe disapper
		waitElementToBeDissAppear(spinner);
		
	}
	
}
