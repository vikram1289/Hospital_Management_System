package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Calendarixigo {
	public static void main(String[] args) {
		String departureDate = "25 Jul, Tue";
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.ixigo.com/");
		driver.findElement(By.xpath("(//input[@placeholder='Enter city or airport'])[2]")).sendKeys("Delhi");
		driver.findElement(By.xpath("//input[@placeholder='Depart']")).sendKeys(departureDate);
		driver.findElement(By.xpath("//div[text()='August 2023']/../descendant :: td[@data-date='05082023']")).click();	
		driver.findElement(By.xpath("//button[text()='Search']")).click();
	}
}
