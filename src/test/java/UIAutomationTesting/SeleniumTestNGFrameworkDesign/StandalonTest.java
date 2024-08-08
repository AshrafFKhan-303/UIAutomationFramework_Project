package UIAutomationTesting.SeleniumTestNGFrameworkDesign;

import java.awt.Desktop.Action;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sun.tools.javac.util.List;

import abstractComponent.AbstractComponent;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjectModel.LandingPage;
import pageObjectModel.ProductCatalog;

public class StandalonTest{

	private static final String Iterate = null;

	public static void main(String[] args) {
		
		String item = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		LandingPage landingPage = new LandingPage(driver);
		
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	    //driver.get("https://rahulshettyacademy.com/client");
		
		driver.manage().window().fullscreen();
		
		landingPage.goTo();
		landingPage.loginApplication("ashrafkhan78625@yahoo.com","Arahul@123#");
		
		/*
		 * driver.findElement(By.id("userEmail")).sendKeys("ashrafkhan78625@yahoo.com");
		 * driver.findElement(By.id("userPassword")).sendKeys("Arahul@123#");
		 * driver.findElement(By.id("login")).click();
		 */
		
		
		ProductCatalog productCatalogPage = new ProductCatalog(driver);
		List<WebElement> products = productCatalogPage.getProducts();
		

		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 * 
		 * //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
		 * ".col-lg-4")));
		 * 
		 * java.util.List<WebElement> products =
		 * driver.findElements(By.cssSelector(".col-lg-4"));
		 */
		
		
		
		/*
		 * WebElement prod = products.stream().filter(product->
		 * product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(item)).
		 * findFirst().orElse(null);
		 * prod.findElement(By.cssSelector("button:last-of-type")).click();
		 */
		
		WebElement actualProduct = productCatalogPage.getProductByName(item);
		
		productCatalogPage.addProductToCart(actualProduct);
		
		
		
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
		 * "#toast-container")));
		 * 
		 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
		 * ".ng-animating")));
		 */
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        java.util.List<WebElement> cartItem = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean flag = cartItem.stream().anyMatch(items->items.getText().equalsIgnoreCase(item));
        Assert.assertTrue(flag);
        
        driver.findElement(By.cssSelector(" .subtotal button")).click();
        
		/*
		 * driver.findElement(By.cssSelector(".form-group input")).sendKeys("India");
		 * java.util.List<WebElement> countries =
		 * driver.findElements(By.cssSelector(".ta-results.list-group.ng-star-inserted")
		 * ); WebElement = countries.stream().filter(country->country.findElement(By.
		 * cssSelector("button span i")).getText().equalsIgnoreCase("India"));
		 */        
        
		/*
		 * Actions action = new Actions(driver); action.sendKeys(driver.findElement(By.
		 * cssSelector("[placeholder='Select Country']")), "India");
		 */
        
        driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");
        
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ta-results")));
        
        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
        
        driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
        
        String confirmMessage  = driver.findElement(By.cssSelector(".hero-primary")).getText();
        //Assert.assertEquals(" Thankyou for the order. ", confirmMessage);
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        
        driver.close();
        
        
        
        
        
	}

}
