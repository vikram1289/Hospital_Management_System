package Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNG_OpenChromeTest {
WebDriver driver = null;
	
@Parameters("browser")
	@BeforeClass
	public void config_BC(String browser) {
	
	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--remote-allow-origins=*");
	
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(options);
		}else if(browser.equalsIgnoreCase("edge")) {
	          driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
	}
	@AfterClass
	public void config_AC() {
		driver.quit();
	}
	
	@Test
	public void testScript() {
		System.out.println("Hello");
	}
}
