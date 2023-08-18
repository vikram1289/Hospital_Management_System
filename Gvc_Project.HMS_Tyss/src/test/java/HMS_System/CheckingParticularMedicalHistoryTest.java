package HMS_System;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import GenericUtilities.ExcelUtility;
import GenericUtilities.FileUtility;
import GenericUtilities.IPathConstants;
import GenericUtilities.WebDriverUtility;
import ObjectRepository.DomainPage;
import ObjectRepository.HomePage;
import ObjectRepository.MedicalHistoryPage;
import ObjectRepository.PatientDashboardPage;
import ObjectRepository.PatientLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;



public class CheckingParticularMedicalHistoryTest {
public static void main(String[] args) throws Exception {

	FileUtility fLib = new FileUtility();
	WebDriverUtility wLib = new WebDriverUtility();
	ExcelUtility eLib = new ExcelUtility();
	
	String browser = fLib.readPropertyFile("browser");
	String url = fLib.readPropertyFile("Domainurl");
	String username = fLib.readPropertyFile("patientUN");
	String password = fLib.readPropertyFile("patientPwd");
	
	//initialize webdriver
	
	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--remote-allow-origins=*");
	
	
	WebDriver driver = null;
	if (browser.equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver(options);
	}else if(browser.equalsIgnoreCase("edge")) {
		driver = new EdgeDriver();
	}
	//Precondition
	wLib.maximize(driver);
	wLib.pageLoadTimeout(driver);
	driver.get(url);
	//domain
	DomainPage dPage = new DomainPage(driver);
	dPage.selectDomain(driver);
	//Home page
	HomePage hPage = new HomePage(driver);
	hPage.selectPatient();
	//Login page
	PatientLoginPage plPage = new PatientLoginPage(driver);
	plPage.patientLogin(username, password);
	//Medical History
	PatientDashboardPage pdPage = new PatientDashboardPage(driver);
	pdPage.clickOnmedicalHistory();
	//Medical History list
	String serialNo = eLib.getValue(IPathConstants.appointment, 10, 1);
	MedicalHistoryPage mhPage = new MedicalHistoryPage(driver);
	String actualResult = mhPage.viewMedicalHistory(driver, serialNo);
	//Detail view of Medical history and Validate TC
	String expectedResult = eLib.getValue(IPathConstants.patientMedicalHistory, 0, 1);
	if(actualResult.equalsIgnoreCase(expectedResult)) {
		System.out.println("Pass: Medical History page is displaying");
	}else {
		System.out.println("Fail: Medical History page is not displaying");
	}
	driver.quit();
}
}
