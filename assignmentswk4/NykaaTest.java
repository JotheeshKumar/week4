package assignmentswk4;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

public class NykaaTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.nykaa.com/");
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Perfumes", Keys.ENTER);
		WebElement result = driver.findElement(By.xpath("//div[@class='page-title-search-result']"));
		System.out.println("In the current Page- " + gtxt(result, 5, driver));
		List<WebElement> perfumeName = driver
				.findElements(By.xpath("//div[@class='m-content__product-list__title']/..//h2/span"));
		System.out.println("Total Number of Perfume Name: " + perfumeName.size());
		System.out.println("Perfume list:");
		for (int i = 0; i < perfumeName.size(); i++) {
			String PN = perfumeName.get(i).getText();
			System.out.println("Item " + i + ": " + PN);
		}

		List<WebElement> perfumeprice = driver
				.findElements(By.xpath("//span[@class='post-card__content-price-offer']"));
		System.out.println("Total Number of Perfume Price available: " + perfumeprice.size());
		for (int i = 0; i < perfumeprice.size(); i++) {
			String PP = perfumeprice.get(i).getText();
			PP = PP.replaceAll("[^0-9]", "");
			System.out.println("Perfume Price of " + i + "th perfume: " + PP);

		}

		List<WebElement> perfumeprice1 = driver
				.findElements(By.xpath("//span[@class='post-card__content-price-offer']"));
		int a = 0;
		WebElement ele = null;
		for (int i = 0; i < perfumeprice.size(); i++) {
			String PP = perfumeprice.get(i).getText();
			PP = PP.replaceAll("[^0-9]", "");
			int maxValue = Integer.parseInt(PP);
			if (maxValue > a) {
				a = maxValue;
				int val = i + 1;
				ele = driver.findElement(By.xpath("(//span[@class='post-card__content-price-offer'])[" + val + "]"));

			}

		}
		System.out.println("Highest price: " + a);
		visbl(ele, 5, driver, "", 1);
		Set<String> allWindow = driver.getWindowHandles();
		List<String> l = new ArrayList<String>(allWindow);
		driver.switchTo().window(l.get(1));
		WebElement addToBag = driver.findElement(By.xpath(
				"(//button[@class='combo-add-to-btn prdt-des-btn js--toggle-sbag nk-btn nk-btn-rnd atc-simple m-content__product-list__cart-btn  '])[1]"));
		addToBag.click();
		WebElement bag = driver.findElement(By.xpath("(//div[@class='add-to-bag-message']/div)[1]"));
		System.out.println("message after adding to bag: " + gtxt(bag, 5, driver));
		WebElement tooltip = driver.findElement(By.xpath("//div[@class='cursor-hand arrowup-tooltip']"));
		visbl(tooltip, 5, driver, "", 1);
		Thread.sleep(3000);
		WebElement grandTotal = driver.findElement(By.xpath("//div[@class='first-col']//div[@class='value']"));
		String text = gtxt(grandTotal, 5, driver);
		text = text.replaceAll("[^0-9]", "");
		System.out.println("Grand Total: " + text);
		System.out.println("Grand Total: " + text);
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();

		WebElement fullbg = driver.findElement(By.xpath("//button[@class='btn full big']"));
		visbl(fullbg, 5, driver, "", 1);
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Joe");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("jotheehkumar.m@gmail.com");
		driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys("9944456076");
		driver.findElement(By.xpath("//input[@name='pinCode']")).sendKeys("624619");
		driver.findElement(By.xpath("//textarea[contains(@class,'textarea-control pr')]")).sendKeys("oddanchatram");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		try {
			WebElement pay = driver.findElement(By.xpath("//button[@type='submit']"));
			visbl(pay, 5, driver, "", 1);
		} catch (Exception e) {
			System.out.println("exception:------:" + e);
		}
		WebElement findElement = driver.findElement(By.xpath("(//span[@class='field-error'])[1]"));
		WebElement findElement2 = driver.findElement(By.xpath("(//span[@class='field-error'])[2]"));
		WebElement findElement3 = driver.findElement(By.xpath("(//span[@class='field-error'])[2]"));
		System.out.println(
				"If the card number not entered the following message is displayed: " + gtxt(findElement, 5, driver));
		System.out.println("If the card number expiry year not entered the following message is displayed: "
				+ gtxt(findElement2, 5, driver));
		System.out.println("If the card number CVV not entered the following message is displayed: "
				+ gtxt(findElement3, 5, driver));
		Thread.sleep(3000);
		driver.close();
	}

	// click & send after the element is available
	public static void visbl(WebElement elem1, int timeout, WebDriver driver, String val, int choice) {
		if (new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(elem1)).isDisplayed()) {
			switch (choice) {
			case 1:
				elem1.click();
				break;
			case 2:
				elem1.sendKeys(val);
				break;
			}
		}
	}
	public static String gtxt(WebElement elem1, int timeout, WebDriver driver) {
		String st = "";
		if (new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(elem1)).isDisplayed()) {
			st = elem1.getText();

		}
		return st;

	}

}