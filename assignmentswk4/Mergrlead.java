package assignmentswk4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Mergrlead {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		WebElement lead = driver.findElement(By.linkText("Leads"));
		visbl(lead, 2, driver);
		WebElement mLead = driver.findElement(By.xpath("//ul[@class='shortcuts']//a[text()='Merge Leads']"));
		Set<String> allWindow = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(allWindow);
		visbl(mLead, 2, driver);
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();
		/*
		 * switchWin(driver, 0, list);
		 * driver.findElement(By.xpath("//input[@name='id']")).sendKeys("20");
		 * driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
		 * 
		 * WebElement id1 = driver.findElement(By.xpath("(//a[@class='linktext'])[1]"));
		 * visbl(id1, 2, driver);
		 * driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		 * driver.findElement(By.xpath("//input[@name='id']")).sendKeys("20");
		 * 
		 * // Click Find Leads button
		 * driver.findElement(By.xpath("//button[@class='x-btn-text']")).click();
		 */
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		Set allWindowtwo = driver.getWindowHandles();
		List<String> ltwo = new ArrayList<String>(allWindowtwo);
		driver.switchTo().window(ltwo.get(1));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@name='id']")).sendKeys("20");
		driver.findElement(By.xpath("//button[@class='x-btn-text']")).click();
		Thread.sleep(3000);
		WebElement tab1 = driver.findElement(By.xpath("(//table[@class='x-grid3-row-table'])[1]//tr[1]/td[1]//a"));
		visbl(tab1, 30, driver);
		driver.switchTo().window(ltwo.get(0));
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		driver.switchTo().alert().accept();
		WebElement ele1 = driver.findElement(By.xpath("//a[text()='Find Leads']"));
		visbl(ele1, 30, driver);
		WebElement tab2 = driver.findElement(By.xpath("//input[@name='id']"));
		snd(tab2, 20, driver, "10124");
		WebElement tab3 = driver.findElement(By.xpath("//button[text()='Find Leads']"));
		visbl(tab3, 30, driver);
		Thread.sleep(3000);
		System.out.println("textMsg: " + driver.findElement(By.xpath("//div[@class='x-paging-info']")).getText());
		driver.close();
	}

	public static void visbl(WebElement elem1, int timeout, WebDriver driver) {
		if (new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(elem1)).isDisplayed()) {
			elem1.click();
		}
	}

	public static void snd(WebElement elem1, int timeout, WebDriver driver, String val) {
		if (new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(elem1)).isDisplayed()) {
			elem1.sendKeys(val);
		}
	}

}
