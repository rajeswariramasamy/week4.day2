package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// Click Brands and Search L'Oreal Paris
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//a[text()='brands']")));
		a.build().perform();
		WebElement w1 = driver.findElement(By.xpath("//input[@id='brandSearchBox']"));
		w1.sendKeys("L'Oreal Paris");
		// Click L'Oreal Paris
		driver.findElement(By.xpath("//div[@id='list_L']/following-sibling::div/a")).click();
		// Click sort By and select customer top rated
		driver.findElement(By.xpath("//span[@class='sort-name']")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		// Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		// Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		// check whether the Filter is applied with Shampoo
		List<WebElement> filters = driver.findElements(By.xpath("//span[@class='filter-value']"));
		Boolean f = false;
		for (int i = 0; i < filters.size(); i++) {
			String text = filters.get(i).getText();
			if (text.equals("Shampoo")) {
				f = true;
			}
		}
		if (f == true) {
			System.out.println("Shampoo filter is applied");
		} else {
			System.out.println("Shampoo filter is not applied");
		}
		// Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//img[contains(@alt,'Oreal Paris Colour Protect Shampoo')]")).click();
		// GO to the new window and select size as 175ml
		Set<String> window1 = driver.getWindowHandles();
		System.out.println(window1);
		System.out.println(driver.getWindowHandle());
		List<String> lst = new ArrayList<>(window1);
		driver.switchTo().window(lst.get(1));
		System.out.println(driver.getWindowHandle());
		WebElement sel1 = driver.findElement(By.tagName("select"));
		Select s = new Select(sel1);
		s.selectByVisibleText("175ml");
		//Print the MRP of the product
		System.out.println(driver.findElement(By.xpath("(//span[@class='css-1jczs19'])[1]")).getText());
		//Click on ADD to BAG
		driver.findElement(By.xpath("(//button[@class=' css-12z4fj0'])[1]")).click();
		//Go to Shopping Bag
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@class='css-acpm4k']"));
		driver.switchTo().frame(frame1);
		//Print the Grand Total amount
		String text1 = driver.findElement(By.xpath("//span[text()='Grand Total']/following-sibling::div")).getText();
		String repstring = text1.replaceAll("\\D","");
		System.out.println(repstring);
		//Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		//Click on Continue as Guest
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		//Check if this grand total is the same in step 14
		String price1 = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div/span")).getText();
		if (repstring.equals(price1))
		{
			System.out.println("Price is matching");
		}
		File screenshot1 = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\user\\eclipse-workspace\\MavenProject\\src\\main\\java\\screenshot\\SS002.png");
		FileUtils.copyFile(screenshot1, dest);
		driver.quit();
	}
}
