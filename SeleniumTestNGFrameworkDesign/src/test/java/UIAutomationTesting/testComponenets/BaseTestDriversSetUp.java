package UIAutomationTesting.testComponenets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import UIAutomation.pageObjectModel.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestDriversSetUp {

	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initilizeDriver() throws IOException {
		
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/UIAutomation/resources/GlobalData.properties");
		
		prop.load(fis);
		
		//Applicable only for local properties variables 
		//String browserName = prop.getProperty("browser");
		
		//Applicable for both BY Maven cmd -Dbrowser='' if not proveded take value from global property file
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
	
		if(browserName.equalsIgnoreCase("chrome")) {
		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
 
	  return driver; 
	}
	
	//Take ScreenShot Utility
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		
		//type cast driver to takescreenshot interface
		TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
		
		//Took Screen shot nd place in temp file
		File source = takeScreenshot.getScreenshotAs(OutputType.FILE);
		
		//Created folder and created .png file
		File path = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
	
		//Copy Screen shot into respective file .png
		FileUtils.copyFile(source, path);
		
		//return Screen shot .png file
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws Exception {
		
		driver = initilizeDriver();
		landingPage = new LandingPage(driver);
		
		//Landing Page POM
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
	     
		//Read json to String
		String jsonContent	= FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
	
		//Convert String to HashMap - by Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data =  mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
			
		});
		
		//now in data object have {{map, map}}
		return data;
	}


}
 