package HMS_Smoke;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import GenericUtilities.ExcelUtility;
import GenericUtilities.FileUtility;
import GenericUtilities.IPathConstants;
import GenericUtilities.JavaUtility;
import GenericUtilities.WebDriverUtility;
import ObjectRepository.DomainPage;
import ObjectRepository.HomePage;
import ObjectRepository.PatientLoginPage;
import ObjectRepository.PatientRegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;



public class UserCreateAccountTest {
public static void main(String[] args) throws Exception {

	FileUtility fLib = new FileUtility();
	JavaUtility jLib = new JavaUtility();
	ExcelUtility eLib = new ExcelUtility();
	WebDriverUtility wLib = new WebDriverUtility();
	
	String browser = fLib.readPropertyFile("browser");
	String url = fLib.readPropertyFile("Domainurl");
	
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
	//Login Page
	PatientLoginPage plPage = new PatientLoginPage(driver);
	plPage.clickOnCreateAccount();
	//Patient Registration
	//Test Data
	int randomNum = jLib.createRandomNumber();
	
	String name = eLib.getValue(IPathConstants.registerSheet, 1, 0)+randomNum;
	String address = eLib.getValue(IPathConstants.registerSheet, 1, 1);
	String city = eLib.getValue(IPathConstants.registerSheet, 1, 2);
	String email = name+eLib.getValue(IPathConstants.registerSheet, 1, 3);
	String password = eLib.getValue(IPathConstants.registerSheet, 1, 4)+randomNum;
	
	//Fill Form
	PatientRegistrationPage prPage = new PatientRegistrationPage(driver);
	String actualResult = prPage.registerUser(driver, name, address, city, email, password);
	//Validate TC
	int row=1, cell=5;
	String expectedResult = eLib.getValue(IPathConstants.registerSheet, row, cell);
	if (actualResult.contains(expectedResult)) {
	//	int lastRow = eLib.lastRowNumber(IPathConstants.registerSheet);
		eLib.writeData(IPathConstants.writeRegister, 0, 0, name);
		eLib.writeData(IPathConstants.writeRegister, 0, 1, email);
		eLib.writeData(IPathConstants.writeRegister, 0, 2, password);
		System.out.println("Pass: Successfully Registered");
	} else {
		System.out.println("Fail: couldn't Registered");
	}
	//post condition
	driver.quit();
}
}
