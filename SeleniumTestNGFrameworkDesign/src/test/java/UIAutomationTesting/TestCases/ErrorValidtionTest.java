package UIAutomationTesting.TestCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import UIAutomation.pageObjectModel.CartPage;
import UIAutomation.pageObjectModel.productPage;
import UIAutomationTesting.testComponenets.BaseTestDriversSetUp;
import UIAutomationTesting.testComponenets.IRetryListener;

public class ErrorValidtionTest extends BaseTestDriversSetUp{

	private static final String Iterate = null;

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=IRetryListener.class)
	public void loginErrorValidation() throws Exception {

		 landingPage.loginApplication("ashrafkhan78625yahoo.com", "Arahul@123#");
		 Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidtion() throws Exception {

		 String item = "ZARA COAT 3";
		 productPage productPage = landingPage.loginApplication("ashrafkhan78625@yahoo.com", "Arahul@123#");
		 
		 //Product Page POM
		 List<WebElement> products = productPage.getProductList();
		 productPage.getProductByName(item);
		 productPage.addProductToCart(item);
		 CartPage cartPage = productPage.goToCartPage(); 
	 
		 //Cart Item Check Out Page	
		 Boolean match = cartPage.verifyCartItemByName(" ORIGINAL");
		 Assert.assertFalse(match);
}
	
	
	
}
