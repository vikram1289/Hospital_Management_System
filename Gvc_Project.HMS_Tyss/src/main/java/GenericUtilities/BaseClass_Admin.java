package GenericUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ObjectRepository.AdminDashboardPage;
import ObjectRepository.AdminLoginPage;
import ObjectRepository.DomainPage;
import ObjectRepository.HomePage;


public class BaseClass_Admin {

	public DatabaseUtility dLib = new DatabaseUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public FileUtility fLib = new FileUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public WebDriver driver;
	public static WebDriver sDriver;
	
	@BeforeSuite(enabled = false, alwaysRun = true)
	public void beforeSuite() throws Throwable {
		dLib.registerDriver();
		System.out.println("DataBase Connection Established");
	}
	
	@AfterSuite(enabled = false, alwaysRun = true)
	public void afterSuit() throws Throwable {
		dLib.closeDbConnection();
		System.out.println("Disconnected from database");
	}
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws Throwable {
		String browser = fLib.readPropertyFile("browser");
		String url = fLib.readPropertyFile("Domainurl");
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		sDriver = driver;
		wLib.maximize(driver);
		wLib.pageLoadTimeout(driver);
		driver.get(url);
		DomainPage dPage = new DomainPage(driver);
		dPage.selectDomain(driver);
		HomePage hPage = new HomePage(driver);
		hPage.selectAdmin();
		System.out.println("Browser successfully opened");
	}
	
	@AfterClass	(alwaysRun = true)
	public void afterClass() {
		driver.quit();
		System.out.println("Browser successfully closed");
	}
	
	@BeforeMethod(enabled = true, alwaysRun = true)
	public void beforeMethod() throws Throwable {
		String username = fLib.readPropertyFile("adminUN");
		String password = fLib.readPropertyFile("adminPwd");
		AdminLoginPage alPage = new AdminLoginPage(driver);
		alPage.adminLogin(username, password);
		System.out.println("Successfully Logged in as Doctor");
	}
	
	@AfterMethod(enabled = true, alwaysRun = true)
	public void afterMethod() {
		AdminDashboardPage adPage = new AdminDashboardPage(driver);
		adPage.logout();;
		System.out.println("Successfully Logged out from Doctor");
	}
}
