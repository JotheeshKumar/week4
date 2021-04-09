package assignmentswk4;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PepperfryTest {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.get("https://www.pepperfry.com/");
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[@id='reg_login_box']//a[@class='popup-close'])[1]")));
		driver.findElement(By.xpath("(//div[@id='reg_login_box']//a[@class='popup-close'])[1]")).click();
		driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
		driver.findElement(By.id("webklipper-publisher-widget-container-notification-close-div")).click();
		driver.switchTo().defaultContent();
		Actions a = new Actions(driver);
		WebElement furniture = driver.findElement(By.xpath("//div[@id='menu_wrapper']//a[text()='Furniture']"));
		prfm(furniture, 7, driver, a, 3);
		WebElement chair = driver.findElement(By.xpath("//a[text()='Office Chairs']"));
		visbl(chair, 7, driver);
		WebElement executiveChair = driver
				.findElement(By.xpath("//ul[@class='clip-main-cat-wrpr pf-center pf-padding-0 pf"
						+ "-margin-10 clip-main-wrap-cat center-xs']//img[@alt='Executive Chairs']"));
		prfm(executiveChair, 7, driver, a, 1);
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"(//ul[@class='clip__filter-dimension']//div[@class='clip__filter-dimension-input-holder']/input[@type='number'])[1]"))
				.clear();
		driver.findElement(By.xpath(
				"(//ul[@class='clip__filter-dimension']//div[@class='clip__filter-dimension-input-holder']/input[@type='number'])[1]"))
				.sendKeys("50");
		driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
		driver.findElement(By.xpath("//span[@class='wewidgeticon we_close icon-large']")).click();
		driver.switchTo().defaultContent();
		WebElement room = driver
				.findElement(By.xpath("//a[@data-productname='Galician High Back Executive Chair in Black Colour']"));
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript("arguments[0].click();", room);
		WebElement bRoom = driver.findElement(By.xpath("//a[text()='Bedroom']"));
		prfm(bRoom, 7, driver, a, 2);
		WebElement tabls = driver.findElement(By.linkText("Study Tables"));
		visbl(tabls, 7, driver);
		WebElement spaceWood = driver.findElement(By.xpath("//input[@id='brandsnameSpacewood']/../input"));
		Thread.sleep(2000);
		javascript.executeScript("arguments[0].click();", spaceWood);
		WebElement price = driver.findElement(By.xpath("//input[@id='price7000-8000']"));
		javascript.executeScript("arguments[0].click();", price);
		WebElement sosCarter = driver.findElement(
				By.xpath("//a[@data-productname='SOS Carter Study Table in Lorraine walnut & silver grey Finish']"));
		javascript.executeScript("arguments[0].click();", sosCarter);
		WebElement totalItems = driver.findElement(By.xpath("(//span[@class='header-nav-cnt count_alert'])[1]"));
		Thread.sleep(2000);
		System.out.println("Items in wishlist: " + totalItems.getText());
		WebElement navigateToWishlist = driver.findElement(By.xpath("//a[@class='wishlist_bar']"));
		visbl(navigateToWishlist, 7, driver);
		WebElement cart = driver.findElement(By.xpath(
				"(//div[@class='mini_cart capitalize active']//div[@class='item_details_holder']//a[@data-tooltip='Add to Cart'])[1]"));
		visbl(cart, 7, driver);

		WebElement prcd = driver.findElement(By.xpath("//a[text()='Proceed to pay securely ']"));
		visbl(prcd, 7, driver);
		try {
			WebElement pin = driver.findElement(By.xpath("//input[@id='pin_code']"));
			snd(pin, 7, driver, "624629");
		} catch (StaleElementReferenceException e) {
			System.out.println("exception: " + e);
		}
		WebElement chck = driver.findElement(By.xpath("//input[@id='pin_check']"));
		visbl(chck, 7, driver);
		WebElement ordr = driver.findElement(By.xpath("(//a[text()='PLACE ORDER'])[1]"));
		visbl(ordr, 7, driver);
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("Pepperfry order amount.png");
		FileUtils.copyFile(source, destination);

		driver.close();
	}

	// click after the element is available
	public static void visbl(WebElement elem1, int timeout, WebDriver driver) {
		if (new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(elem1)).isDisplayed()) {
			elem1.click();
		}
	}

//sending values 
	public static void snd(WebElement elem1, int timeout, WebDriver driver, String val) {
		if (new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(elem1)).isDisplayed()) {
			elem1.sendKeys(val);
		}
	}

	// try using switch case
	public static void prfm(WebElement elem1, int timeout, WebDriver driver, Actions a, int choice) {
		if (new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(elem1)).isDisplayed()) {
			switch (choice) {
			case 1:
				a.click(elem1).build().perform();
				break;
			case 2:
				a.moveToElement(elem1).perform();
				break;
			case 3:
				a.moveToElement(elem1).build().perform();
				break;// --

			}
		}
	}
}