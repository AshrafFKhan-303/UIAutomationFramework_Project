package pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sun.tools.javac.util.List;

import abstractComponent.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
    
	WebDriver driver = null;
	
	public ProductCatalog(WebDriver driver) {
	    super(driver);	
		this.driver = driver;
	    PageFactory.initElements(driver, this);	
	}
	
	/*
	 * java.util.List<WebElement> products =
	 * driver.findElements(By.cssSelector(".col-lg-4")); WebElement prod =
	 * products.stream().filter(product->
	 * product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(item)).
	 * findFirst().orElse(null);
	 * prod.findElement(By.cssSelector("button:last-of-type")).click();
	 */
	
// Page Factory Concept by FindBy Annoation	
	@FindBy(css =".col-lg-4")
	 List<WebElement> products;
	
	@FindBy(css = "button:last-of-type")
	WebElement cartButton;
	
	
	By productsBy = By.cssSelector(".col-lg-4");
  
	public List<WebElement> getProducts() {
		waitForElementToBeAppear(productsBy);
		return products;
	}
	

	/*
	 * WebElement prod = products.stream().filter(product->
	 * product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(item)).
	 * findFirst().orElse(null);
	 * prod.findElement(By.cssSelector("button:last-of-type")).click();
	 */
	
	By addToCartButton = By.cssSelector("button:last-of-type");
	
	By toastMessage = By.cssSelector("#toast-container");
	
	public WebElement getProductByName(String productName) {
		WebElement actualProduct = getProducts().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
	    return actualProduct;
	}
	
	public void addProductToCart(WebElement actualProduct) {
		actualProduct.findElement(addToCartButton).click();
		waitForElementToBeAppear(toastMessage);
		
	}
	
	
}
   	
