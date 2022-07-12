package week4.day2;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBus {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.redbus.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//Enter From -Madiwala Bangalore
		driver.findElement(By.id("src")).sendKeys("Madiwala Bangalore");
		driver.findElement(By.xpath("//li[@select-id='results[0]']")).click();
		//Enter To Koyambedu Chennai
		driver.findElement(By.id("dest")).sendKeys("Koyambedu Chennai");
		driver.findElement(By.xpath("//li[@select-id='results[0]']")).click();
		//Select the Date 10-Jun-2022
		driver.findElement(By.id("onward_cal")).click();
		WebElement tab = driver.findElement(By.tagName("table"));
		 int cols = tab.findElements(By.tagName("th")).size();
		 int rows = tab.findElements(By.tagName("tr")).size();
		 String monthtxt = driver.findElement(By.xpath("//td[@class='monthTitle']")).getText();
		if (monthtxt.equals("July 2022"))
		{
			for (int i=2;i<rows;i++)
			{
				for (int j=1;j<=cols;j++)
				{
					WebElement ele = driver.findElement(By.xpath("//table//tr["+(i+1)+"]//td["+j+"]"));
					String day = ele.getText();
					if (day.equals("10"))
					{
						ele.click();
					}
				}
			}
		}
		//Click Search buses
		driver.findElement(By.id("search_btn")).click();
		//Click After 6pm under Departure time
		driver.findElement(By.xpath("//label[@for='dtAfter 6 pm']")).click();
		//Click Sleeper under Bus types
		driver.findElement(By.xpath("//label[@title='SLEEPER']")).click();
		//Select the Primo
		driver.findElement(By.xpath("//img[@class='primo-filter-icon']")).click();
		//Get the number of buses found
		List<WebElement> lst2 = driver.findElements(By.xpath("//div[@class='fare d-block']/span"));
		System.out.println("No of Buses: "+lst2.size());
		//Get the Bus fare and sort them in ascending order
		Set<String> s = new TreeSet<>();
		for (int i=0; i<lst2.size();i++)
		{
			System.out.println(lst2.get(i).getText());
			s.add(lst2.get(i).getText());
		}
		System.out.println(s);
		//Close the application
		driver.close();
	}

}
