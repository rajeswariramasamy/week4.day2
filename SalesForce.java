package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForce {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Enter username
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password@123");
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		//Click on the toggle menu button from the left corner
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		//Click View All and click Dashboards from App Launcher
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		Thread.sleep(1000);
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//p[text()='Dashboards']")));
		a.build().perform();
		driver.findElement(By.xpath("//p[text()='Dashboards']")).click();
		//Click on the New Dashboard option 
		driver.findElement(By.xpath("//div[@title='New Dashboard']")).click();
		//Handle the frame
		WebElement f1 = driver.findElement(By.xpath("//iframe[@title='dashboard']"));
		driver.switchTo().frame(f1);
		//Enter Name as 'Salesforce Automation by Your Name ' and Click on Create.
		driver.findElement(By.id("dashboardNameInput")).sendKeys("Salesforce Automation by Rajeswari");
		driver.findElement(By.id("submitBtn")).click();
		//Click on Save and Verify Dashboard name.
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		String name1 = driver.findElement(By.xpath("//span[@class='slds-form-element__static slds-grid slds-grid_align-spread']")).getText();
		if (name1.contains("Salesforce Automation by Rajeswari"))
		{
			System.out.println("Dashboard name Verifyed");
		}
		driver.switchTo().parentFrame();
		driver.close();

	}

}
