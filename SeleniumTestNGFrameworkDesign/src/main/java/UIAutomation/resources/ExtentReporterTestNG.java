package UIAutomation.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterTestNG {

	//ExtentReports extentReport; 
	
	public static ExtentReports reportConfig() {
	String path = System.getProperty("user.dir")+"\\reports\\index.html";
	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	
	reporter.config().setDocumentTitle("Automtion Testing Report");
	reporter.config().setReportName("Test Summary HTML Extent Report");
	//reporter.config().getJs().describeConstable();
	
	ExtentReports extentReport = new ExtentReports();
	
	extentReport.attachReporter(reporter);
	extentReport.setSystemInfo("Automtion Test Engineer", "Ashraf Khan");
	//extentReport.addTestRunnerOutput("Tsting System Logs");
	
	return extentReport;
	}
}
