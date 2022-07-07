package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameAssignment {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//Switch to first frame
		driver.switchTo().frame("frame1");
		driver.findElement(By.xpath("//b[@id='topic']/following::input")).sendKeys("Selenium");
		//Switch to third frame
		driver.switchTo().frame("frame3");
		driver.findElement(By.id("a")).click();
		//Switch back to fist frame
		driver.switchTo().defaultContent();
		//Switch to third frame
		WebElement f1 = driver.findElement(By.xpath("//iframe[@id='frame2']"));
		driver.switchTo().frame(f1);
		WebElement find1 = driver.findElement(By.id("animals"));
		Select s = new Select(find1);
		s.selectByIndex(1);
		driver.switchTo().parentFrame();
		
		
		

	}

}
