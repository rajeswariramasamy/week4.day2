package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceEdit {

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
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='slds-button']")).click();
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//p[text()='Dashboards']")));
		a.build().perform();
		driver.findElement(By.xpath("//p[text()='Dashboards']")).click();
		//Search the Dashboard 'Salesforce Automation by Your Name'
		driver.findElement(By.xpath("//input[@class='search-text-field slds-input input uiInput uiInputText uiInput--default uiInput--input']")).sendKeys("Automation by Rajeswari");
		//Click on the Dropdown icon and Select Edit
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//button[@class='slds-button slds-button_icon-border slds-button_icon-x-small'])[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Edit']/parent::a")).click();
		//Handle the frame
		WebElement f1 = driver.findElement(By.xpath("//iframe[@title='dashboard']"));
		driver.switchTo().frame(f1);
		//Click on the Edit Dashboard Properties icon
		driver.findElement(By.xpath("//button[@title='Edit Dashboard Properties']")).click();
		//Enter Description as 'SalesForce' and click on save.
		WebElement des = driver.findElement(By.xpath("//input[@id='dashboardDescriptionInput']"));
		des.clear();
		des.sendKeys("SalesForce");
		//Click on Done and  Click on save in the popup window displayed.
		driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
		//Verify the Description.
		driver.findElement(By.xpath("//button[@title='Edit Dashboard Properties']")).click();
		String description = driver.findElement(By.xpath("//input[@id='dashboardDescriptionInput']")).getText();
		System.out.println(description);
		if (description.equals("SalesForce"))
		{
			System.out.println("Description is correct");
		}
		else
		{
			System.out.println("Description is not correct");
		}
		driver.findElement(By.xpath("//button[@id='cancelBtn']")).click();
	//	driver.close();
	}

}
