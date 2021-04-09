package assignmentswk4;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		driver.get("https://dev68594.service-now.com/");
		WebElement elem0 = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(elem0);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.id("sysverb_login")).click();
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident");
		WebElement elem1 = driver.findElement(By.xpath("(//ul[@class='sn-widget-list_v2 sn-widget-list_dense collapse in'])[3]//div[text()='All']"));
		visbl(elem1,20,driver);
		driver.switchTo().frame("gsft_main");
		WebElement elem2 = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
		visbl(elem2,20,driver);
		WebElement caller = driver.findElement(By.xpath("//input[@id='sys_display.incident.caller_id']"));
	    Actions a = new Actions(driver);
	    a.doubleClick(caller).perform();
		Thread.sleep(3000);
		WebElement elem3 = driver.findElement(By.xpath("//input[@id='sys_display.incident.caller_id']"));
		elem3.sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER);
		driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys("Description for service Now");
		String incidentNumber=driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		System.out.println("Inc. No: "+ incidentNumber);
		driver.findElement(By.xpath("//button[@id='sysverb_insert']")).click();
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(incidentNumber);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(Keys.ENTER);
		String text= driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		if(text.equals(incidentNumber))
			System.out.println("Incident Created successfully");
		else
		{
			System.out.println("Incident not Created");
		}
	}
	public static void visbl(WebElement elem1, int timeout, WebDriver driver) {
		if (new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(elem1)).isDisplayed()) {
			elem1.click();
		}
	}
	
}