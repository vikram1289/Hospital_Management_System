package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(GenericUtilities.ListnersImplementationClass.class)
public class TestNGListnersClassTest {

	@Test
	public void listners()
	{
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver=new FirefoxDriver();
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//div[@class='_1psGvi SLyWEo']")).click();
		driver.findElement(By.xpath("//div[.='✕']")).click();
		driver.findElement(By.name("king")).click();
	}
}
