package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		//Launch URL
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys("Demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		//Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();
		//Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();
		//Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();
		//Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		//Click on Widget of From Contact
		String currwindow = driver.getWindowHandle();
		System.out.println(currwindow);
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();
		//Click on First Resulting Contact
		Set<String> allwindow = driver.getWindowHandles();
		List<String> lst = new ArrayList<>(allwindow);
		driver.switchTo().window(lst.get(1));
		String currwindow1 = driver.getWindowHandle();
		System.out.println(currwindow1);
		driver.findElement(By.xpath("(//a[@class='linktext'])[1]")).click();
		//Click on Widget of To Contact
		driver.switchTo().window(lst.get(0));
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		//Click on Second Resulting Contact
		Set<String> allwindow1 = driver.getWindowHandles();
		List<String> lst1 = new ArrayList<>(allwindow1);
		driver.switchTo().window(lst1.get(1));
		driver.findElement(By.xpath("(//a[@class='linktext'])[6]")).click();
		//Click on Merge button using Xpath Locator
		driver.switchTo().window(lst.get(0));
		driver.findElement(By.className("buttonDangerous")).click();
		//Accept the Alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		//Verify the title of the page
		String title = driver.getTitle();
		if (title.equals("View Contact | opentaps CRM"))
		{
			System.out.println("Title Matches :"+title);
		}
		else
		{
			System.out.println("Title mismatch :"+title);
		}
		

	}

}
