package Practice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProKabaddiTeamDetails {
public static void main(String[] args) {
	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--remote-allow-origins=*");
	WebDriver driver = new ChromeDriver(options);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://www.prokabaddi.com/standings");
	String teamName = "Jaipur Pink Panthers";
//	List<WebElement> teamDetails = driver.findElements(By.xpath("//div[@class='table-row qualifier'] | //div[@class='table-row']"));
//	for (int i=0; i< teamDetails.size(); i++) {
//		System.out.print(teamDetails.get(i).getText());
//		
//	}
	List<WebElement> team = driver.findElements(By.xpath("//p[text()='"+teamName+"']/ancestor :: div[@class='row-head']/div"));
	for (WebElement teamData : team) {
		System.out.print(teamData.getText()+"---");
	}
	
}
}
