package abstractComponenets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponenet {

  WebDriver driver;	
  public AbstractComponenet(WebDriver driver) {
	this.driver=driver;
	}

  public void waitElementToBeAppear(By findBy){	
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
  }


  public void waitElementToBeDissAppear(WebElement element){	
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.invisibilityOf(element));
  }

}
