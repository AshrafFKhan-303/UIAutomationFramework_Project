package UIAutomationTesting.TestCases;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import UIAutomation.pageObjectModel.CartPage;
import UIAutomation.pageObjectModel.ConfirmationPage;
import UIAutomation.pageObjectModel.LandingPage;
import UIAutomation.pageObjectModel.OrderHistoryPage;
import UIAutomation.pageObjectModel.OrderPage;
import UIAutomation.pageObjectModel.productPage;
import UIAutomationTesting.testComponenets.BaseTestDriversSetUp;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandalonTest extends BaseTestDriversSetUp{

	//String item = "ZARA COAT 3";
	private static final String Iterate = null;
 
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> inputs) throws Exception {

//---- By Object Class --- Data Driven		
   //public void submitOrder(String email, String password, String productName) throws Exception {
  //productPage productPage = landingPage.loginApplication(email, password);
		 
//---- By HashMap Class Object --- Data Driven		
		productPage productPage = landingPage.loginApplication(inputs.get("email"), inputs.get("password"));
   //Product Page POM
		//productPage productPage = new productPage(driver);
		 List<WebElement> products = productPage.getProductList();
		 productPage.getProductByName(inputs.get("productName"));
		 productPage.addProductToCart(inputs.get("productName"));
		 CartPage cartPage = productPage.goToCartPage(); 
	  //Cart Item Check Out Page	
		//CartPage cartPage = new CartPage(driver);
		 Boolean match = cartPage.verifyCartItemByName(inputs.get("productName"));
		 Assert.assertTrue(match); 
	//Order Page	 
		 OrderPage orderPage = cartPage.goToCheckOut();
		 orderPage.selectCountry("India");
		 //ConfirmationPage confirmationPage = orderPage.submitOrder();
	//Confirmation Page	 
//		 String confirmMessage = confirmationPage.getConirmationMessage();
//		 Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() throws Exception {

		 String item = "ADIDAS ORIGINAL";
		 productPage productPage = landingPage.loginApplication("ashrafkhan78625@yahoo.com", "Arahul@123#");
		 OrderHistoryPage ordersHistoryPage = productPage.goToOrderPage();
	     Assert.assertTrue(ordersHistoryPage.verifyOderDisplay(item));
	}
	
//----- Data Driven Testing by HashMap Class Object key nd value	
//	@DataProvider
//	public Object[][] getData(){
//		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "ashrafkhan78625@yahoo.com");
//		map.put("password", "Arahul@123#");
//		map.put("productName", "ZARA COAT 3");
//	
//		HashMap<String,String> map01 = new HashMap<String,String>();
//		map01.put("email", "ashrafkhan786@yahoo.com");
//		map01.put("password", "Arahul@123#");
//		map01.put("productName", "ADIDAS ORIGINAL");
//	
//		return new Object[][] {{map},{map01}};
//	}

	//----- Data Driven Testing by JSON File and red by file utils - HashMap Class Object key nd value	
		@DataProvider
		public Object[][] getData() throws IOException{
		
			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//UIAutomationTesting//data//PurchaseOrder.json");
			return new Object[][] {{data.get(0)},{data.get(1)}};
		}

	
//----- Data Driven Test by Object Class Object properties ---------
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"ashrafkhan78625@yahoo.com","Arahul@123#", "ZARA COAT 3"},{"ashrafkhan78625@yahoo.com","Ahul@123#","ZARA COAT 3"}};
//	}
		 
		 
		
//			WebDriverManager.chromedriver().setup();
//			WebDriver driver = new ChromeDriver();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			driver.manage().window().maximize();

	     	//LandingPage landingPage = new LandingPage(driver);
//		//Landing Page POM
	     	//landingPage.goTo("https://rahulshettyacademy.com/client");
			
		/*
		 * driver.findElement(By.id("userEmail")).sendKeys("ashrafkhan78625@yahoo.com");
		 * driver.findElement(By.id("userPassword")).sendKeys("Arahul@123#");
		 * driver.findElement(By.id("login")).click();
		 */

		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".col-lg-4")));

		/*
		 * java.util.List<WebElement> products =
		 * driver.findElements(By.cssSelector(".col-lg-4")); WebElement prod =
		 * products.stream() .filter(product ->
		 * product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(item))
		 * .findFirst().orElse(null);
		 * prod.findElement(By.cssSelector("button:last-of-type")).click();
		 * 
		 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
		 * "#toast-container")));
		 * 
		 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
		 * ".ng-animating")));
		 * 
		 * driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		 */
		
		
////		
//		java.util.List<WebElement> cartItem = driver.findElements(By.cssSelector(".cartSection h3"));
//		boolean flag = cartItem.stream().anyMatch(items -> items.getText().equalsIgnoreCase(item));
//		Assert.assertTrue(flag);
//
//		driver.findElement(By.cssSelector(" .subtotal button")).click();

		/*
		 * driver.findElement(By.cssSelector(".form-group input")).sendKeys("India");
		 * java.util.List<WebElement> countries =
		 * driver.findElements(By.cssSelector(".ta-results.list-group.ng-star-inserted")
		 * ); WebElement = countries.stream().filter(country->country.findElement(By.
		 * cssSelector("button span i")).getText().equalsIgnoreCase("India"));
		 */

		
		  //Actions action = new Actions(driver);
		  
		 //action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		 

		// driver.findElement(By.cssSelector("[placeholder='Select
		// Country']")).sendKeys("India");

//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
//
//		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
//		Thread.sleep(500);

		 //JavascriptExecutor js = (JavascriptExecutor) driver;
		 //js.executeScript("window.scrollTo(0, document.body.scrollHveight)");
		
		//action.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).perform();
		
		//driver.findElement(By.cssSelector(".action__submit")).click();

		//String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		// Assert.assertEquals(" Thankyou for the order. ", confirmMessage);
		//Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		//driver.close();

 }

