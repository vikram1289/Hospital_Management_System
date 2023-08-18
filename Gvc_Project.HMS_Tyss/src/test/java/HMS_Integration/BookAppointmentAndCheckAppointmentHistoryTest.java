package HMS_Integration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import GenericUtilities.ExcelUtility;
import GenericUtilities.FileUtility;
import GenericUtilities.IPathConstants;
import GenericUtilities.WebDriverUtility;
import ObjectRepository.AppointmentHistoryPage;
import ObjectRepository.BookAppointmentPage;
import ObjectRepository.DomainPage;
import ObjectRepository.HomePage;
import ObjectRepository.PatientDashboardPage;
import ObjectRepository.PatientLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;



public class BookAppointmentAndCheckAppointmentHistoryTest {
public static void main(String[] args) throws Exception {
	
	FileUtility fLib = new FileUtility();
	WebDriverUtility wLib = new WebDriverUtility();
	ExcelUtility eLib = new ExcelUtility();
	
	String browser = fLib.readPropertyFile("browser");
	String url = fLib.readPropertyFile("Domainurl");
	String username = fLib.readPropertyFile("patientUN");
	String password = fLib.readPropertyFile("patientPwd");
	
	//initialize web driver
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
	//Dashboard
	PatientDashboardPage pdPage = new PatientDashboardPage(driver);
	pdPage.clickOnBookAppointment();
	//Book Appointment Page
	BookAppointmentPage baPage = new BookAppointmentPage(driver);
	String specialization = eLib.getValue(IPathConstants.appointment, 0, 1);
	String doctor = eLib.getValue(IPathConstants.appointment, 1, 1);
	String date = eLib.getValue(IPathConstants.appointment, 2, 1);
	String time = eLib.getValue(IPathConstants.appointment, 3, 1);
	String actualResult = baPage.bookAppointment(driver, specialization, doctor, date, time);
	String expectedResult = eLib.getValue(IPathConstants.appointment, 6, 1);
	if (actualResult.contains(expectedResult)) {
		System.out.println("Pass: Appointment booked successfully");
	} else {
		System.out.println("Fail: Appointment not booked");
	}
	//Check Appointment History
	pdPage.clickOnAppointmentHistory();
	//validate TC
	AppointmentHistoryPage apPage = new AppointmentHistoryPage(driver);
	apPage.verifyAppointment(driver, doctor, date, time);
	driver.quit();
}
}
