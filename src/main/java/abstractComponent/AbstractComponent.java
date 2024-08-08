package abstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	WebDriver driver = null;

   public AbstractComponent(WebDriver driver){
	   this.driver = driver;
   }
   public void waitForElementToBeAppear(By findBy) {
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	   java.util.List<WebElement> products = driver.findElements(findBy);	
   }
	
	
}
