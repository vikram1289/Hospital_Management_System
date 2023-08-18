package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KayakFirstFlight {
public static void main(String[] args) {
	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--remote-allow-origins=*");
	WebDriver driver = new ChromeDriver(options);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://www.kayak.co.in/");
	driver.findElement(By.xpath("//div[@class='BCcW BCcW-mod-multi-value']//div[@aria-label='Remove']")).click();
	driver.findElement(By.xpath("//input[@placeholder='From?']")).sendKeys("Bangalore");
	driver.findElement(By.xpath("//div[@role='listitem']")).sendKeys("Mangalore");
	driver.findElement(By.xpath("//span[@aria-label='Start date calendar input']")).click();
	driver.findElement(By.xpath("//div[@aria-label='Saturday 5 August, 2023']")).click();
	driver.findElement(By.xpath("//span[@aria-label='End date calendar input']")).click();
	driver.findElement(By.xpath("//div[@aria-label='Monday 7 August, 2023']")).click();
	driver.findElement(By.xpath("//button[@aria-label='Search']")).click();
	
	
}
}
