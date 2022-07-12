package week4.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleFrame {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/");
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//a[text()='Selectable']")).click();
		driver.switchTo().frame(0);

		WebElement findElement = driver.findElement(By.xpath("//li[text()='Item 3']"));
		findElement.click();
	//	boolean selected = findElement.isSelected();
		System.out.println( "Item3 is selected ");

		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[text()='Download']")).click();
		String title = driver.getTitle();
		System.out.println(title);
	}

}
