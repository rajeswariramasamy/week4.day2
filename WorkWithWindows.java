package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkWithWindows {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//Click button to open home page in New Window
		driver.findElement(By.id("home")).click();
		//Find the number of opened windows
		driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();
		System.out.println(driver.getWindowHandles().size());
		//Close all except this window
		driver.findElement(By.xpath("//button[contains(text(),'Do not close me ')]")).click();
		Set<String> window = driver.getWindowHandles();
		System.out.println(window);
		List<String> lst = new ArrayList<>(window);
		int size = lst.size();
		for (int i=1;i<size;i++)
		{
			WebDriver window2 = driver.switchTo().window(lst.get(i));
			window2.close();
		}
		driver.switchTo().window(lst.get(0));
		System.out.println(driver.getWindowHandle());
		//Wait for 2 new Windows to open
		driver.findElement(By.xpath("//button[@onclick='openWindowsWithWait();']")).click();
		Thread.sleep(5000);
		Set<String> windowHandles1 = driver.getWindowHandles();
		System.out.println(windowHandles1.size());


	}

}
