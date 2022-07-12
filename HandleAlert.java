package week4.day2;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleAlert {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/Alert.html");
		driver.manage().window().maximize();

		// Click the button to display a alert box.
		driver.findElement(By.xpath("//*[@onclick='normalAlert()']")).click();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		System.out.println(text);
		alert.dismiss();

		// Click the button to display a confirm box.

		driver.findElement(By.xpath("//button[text()='Confirm Box']")).click();
		Alert alert2 = driver.switchTo().alert();
		String text2 = alert2.getText();
		System.out.println(text2);
		alert2.accept();
		WebElement findElement = driver.findElement(By.xpath("//p[@id='result']"));
		String att = findElement.getText();
		if (att.equals("You pressed OK!")) {
			System.out.println("Alert handled successfully");
		}

		// Click the button to display a prompt box.

		driver.findElement(By.xpath("//*[@onclick='confirmPrompt()']")).click();
		Alert alert1 = driver.switchTo().alert();
		String text1 = alert1.getText();
		System.out.println(text1);
		alert1.sendKeys("Fatima");
		alert1.accept();
		WebElement findElement1 = driver.findElement(By.xpath("//p[@id='result1']"));
		String att1 = findElement1.getText();
		if (att1.contains("Fatima")) {
			System.out.println("Alert handled successfully");
		}

	}

}
