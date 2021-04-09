package assignmentswk4;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		// Launch the browser
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(" https://www.snapdeal.com/");
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		WebElement mensFashion = driver.findElement(By.xpath("//ul[@class='nav smallNav']/li[7]/a/span"));
		Actions main = new Actions(driver);
		main.moveToElement(mensFashion).build().perform();
		WebElement sportsShoes = driver.findElement(By.xpath("(//div[@id='category6Data']//p[2]/a/span)[1]"));
		main.moveToElement(sportsShoes).click().perform();
		WebElement count = driver.findElement(By.xpath("//h1[@class='category-name']/span"));
		System.out.println("============-------------------============");
		String sportshoesNum = count.getText();
		sportshoesNum = sportshoesNum.replace("(", "");
		sportshoesNum = sportshoesNum.replace("Items)", "");
		System.out.println("Sports shoes count: " + sportshoesNum);
		driver.findElement(
				By.xpath("(//ul[@class='child-cat-wrapper  cat-wrapper'])[1]/li//div[text()='Training Shoes']"))
				.click();
		Thread.sleep(5000);
		WebElement findElement2 = driver.findElement(By.xpath("//div[@class='sorting-sec animBounce']"));
		visbl(findElement2, 20, driver);
		WebElement findElement = driver.findElement(By.xpath("//ul[@class='sort-value']/li[2]"));
		visbl(findElement, 20, driver);
		WebElement xpath = driver.findElement( By.xpath("//div[@class='product-desc-rating ']//div//span[2]"));
		visbl(xpath, 20, driver);
		List<WebElement> sorted = driver.findElements(By.xpath("//div[@class='product-desc-rating ']//div//span[2]"));
		System.out.println("Total training shoes present in the page: " + sorted.size());
		WebElement firstPrice = driver.findElement(By.xpath("(//div[@class='product-desc-rating ']//div//span[2])[1]"));
		String firstPriceText = firstPrice.getText();
		firstPriceText = firstPriceText.replaceAll("[^0-9]", "").replaceAll("\\s", "");
		int firstValue = Integer.parseInt(firstPriceText);

		driver.findElement(By.xpath("//input[@class='sd-input js-searchable-box']")).click();
		WebElement puma = driver.findElement(By.xpath("//div[@class='brand-filter-columns']//input[@id='Brand-Puma']"));
		javascript.executeScript("arguments[0].click();", puma);
		WebElement applyButton = driver.findElement(By.xpath("//div[text()='APPLY']"));
		javascript.executeScript("arguments[0].click();", applyButton);
		try {
			WebElement xpath2 = driver.findElement(  By.xpath("//span[@class='lfloat product-price']"));
			visbl(xpath2, 20, driver);
		} catch (Exception e) {
			System.out.println("exception: "+e);
		}
		List<WebElement> sortedPuma = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		System.out.println("Total Puma shoes present in the page: " + sortedPuma.size());
		WebElement firstPricePuma = driver.findElement(By.xpath("(//span[@class='lfloat product-price'])[1]"));
		String firstPumaPriceText = firstPricePuma.getText();
		firstPumaPriceText = firstPumaPriceText.replaceAll("[^0-9]", "").replaceAll("\\s", "");
		int pumaFirstValue = Integer.parseInt(firstPumaPriceText);
		System.out.println("puma_1st_price: "+ pumaFirstValue);
try
{
		for (int i = 0; i < sortedPuma.size(); i++) {

			String textPuma = sortedPuma.get(i).getText();
			textPuma = textPuma.replaceAll("[^0-9]", "").replaceAll("\\s", "");
			int pumaText1 = Integer.parseInt(textPuma);
			if (pumaText1 < firstValue) {
				System.out.println("check if not sorted correctly: " + i);
			} else {
				System.out.println(pumaText1 + " = " + i);
			}
		}
}
catch(Exception e)
{
	System.out.println("exception: "+e);
}
		WebElement pumaTraining = driver.findElement(By.xpath("(//img[@title='Puma Blue Training Shoes'])[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(pumaTraining).perform();
		WebElement QuickView = driver
				.findElement(By.xpath("((//a[contains(@href,'puma-blue-training-shoes')])[1]/..//div)[3]"));
		javascript.executeScript("arguments[0].click();", QuickView);
		System.out.println("Cost of PUMA Training shoe: " + driver
				.findElement(By.xpath("(//div[@class='product-price pdp-e-i-PAY-l clearfix']/span)[1]")).getText());
		System.out.println("Discount percentage of PUMA Training shoe: " + driver
				.findElement(By.xpath("(//div[@class='product-price pdp-e-i-PAY-l clearfix']/span)[2]")).getText());
		File s1 = driver.getScreenshotAs(OutputType.FILE);
		File d1 = new File("image.png");
		FileUtils.copyFile(s1, d1);
		driver.findElement(By.xpath("//div[@class='close close1 marR10']/i")).click();
		driver.close();

	}
	public static void visbl(WebElement elem1, int timeout, WebDriver driver) {
		if (new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(elem1)).isDisplayed()) {
			elem1.click();
		}
	}
}