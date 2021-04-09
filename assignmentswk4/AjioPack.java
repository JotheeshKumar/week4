package assignmentswk4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AjioPack {

	public static void main(String[] args) throws InterruptedException {
		//1) Go to the URL https://www.ajio.com/shop/sale 
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver = new ChromeDriver(); 
		driver.get("https://www.ajio.com/shop/sale ");
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	
		//2) Enter Bags in the Search field and Select Women Handbags 
		driver.findElement(By.xpath("//input[@role='combobox']")).sendKeys("bags", Keys.ENTER);
		driver.findElement(By.xpath("//div[@class='facet-linkhead']//following::label[@for='Women']")).click();
		
		//3) Click on five grid and Select SORT BY as "What's New"
		driver.findElement(By.xpath("//div[@class='five-grid']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='filter-dropdown']/select/option[1]")).click();
		driver.findElement(By.xpath("//div[@class='filter-dropdown']/select/option[4]")).click();
		
		//4) Click Price on the side menu and Enter Price Range Min as 2000 and Max as 5000
		driver.findElement(By.xpath("(//div[@class='facet-head '])[1]")).click();
		driver.findElement(By.xpath("//input[@name='minPrice']")).sendKeys("3000");
		driver.findElement(By.xpath("//input[@name='maxPrice']")).sendKeys("5000");
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		
		//5) Click on the first product 
		driver.findElement(By.xpath("(//a[@class='rilrtl-products-list__link']/div/div)[1]")).click();
		Set<String> newsetwin = driver.getWindowHandles();
		List<String> newlistwin = new ArrayList<String>(newsetwin);
		driver.switchTo().window(newlistwin.get(1));
		
		//6) Get the actual price, coupon code and discount price
		Thread.sleep(1000);
		System.out.println("The cost of the bag is : " +driver.findElement(By.xpath("//div[@class='prod-sp']")).getText().replaceAll("\\D", ""));
		System.out.println("The discount price of the bag is : " +driver.findElement(By.xpath("//div[@class='promo-discounted-price']/span")).getText().replaceAll("\\D", ""));
		String coupon = driver.findElement(By.xpath("//div[@class='promo-title']")).getText();
		String[] Split=coupon.split("Use Code");
		String finalString= (Split[Split.length-1]).replaceAll("[^a-zA-Z]", "");
		System.out.println("The coupon code for the bag is : " +finalString);
		
		//7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
		driver.findElement(By.xpath("//span[text()='Enter Pin-code To Know Estimated Delivery Date']")).click();
		driver.findElement(By.xpath("//input[@name='pincode']")).sendKeys("560043");
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		
		//8) Click on Other Informations under Product Details and Print the Customer Care details
		driver.findElement(By.xpath("//div[@class='other-info-toggle']")).click();
		System.out.println("The customer care details is : " +driver.findElement(By.xpath("//div[text()='Customer Care Address']/following::div[2]")).getText());
		
		//9) Click on ADD TO BAG and then GO TO BAG 
		driver.findElement(By.xpath("//div[@class='pdp-addtocart-button']")).click();
		driver.findElement(By.xpath("//div[text()='PROCEED TO BAG']")).click();
		
		//10) Check the Order Total before apply coupon 
		System.out.println("The total amount before shipping is : " +driver.findElement(By.xpath("//section[@id='orderTotal']/span[2]")).getText());
		
		//11) Enter Coupon Code and Click Apply 
		driver.findElement(By.id("couponCodeInput")).sendKeys("special");
		driver.findElement(By.xpath("//button[text()='Apply']")).click(); //promo is applied successfully
		driver.findElement(By.xpath("//span[@class='ic-delete navigation-icon']")).click();
		
		//12) Print the warning message if the product is not eligible discount
		driver.findElement(By.id("couponCodeInput")).clear();
		driver.findElement(By.id("couponCodeInput")).sendKeys("1");
		driver.findElement(By.xpath("//button[text()='Apply']")).click();
		System.out.println("The warning message is : " +driver.findElement(By.xpath("//span[@class='voucher-error error-message info']")).getText());
		
		//12(a) Verify the bill amount is matching with the discount price or not 
		driver.findElement(By.xpath("//span[@class='ic-delete delete-voucher']")).click();
		driver.findElement(By.id("couponCodeInput")).sendKeys("special");
		driver.findElement(By.xpath("//button[text()='Apply']")).click();
		Thread.sleep(3000);
		String cost=driver.findElement(By.xpath("//span[@class='price-value bold-font']")).getText();
		cost = cost.replaceAll("\\D", "");
		List<Integer> number = new ArrayList<Integer>();
		number.add(Integer.parseInt(cost));
		//System.out.println(number);
		String cost1=driver.findElement(By.xpath("//div[@class='net-price best-price-strip']")).getText();
		cost1 = cost1.replaceAll("\\D", "");
		List<Integer> number1 = new ArrayList<Integer>();
		number1.add(Integer.parseInt(cost));
		//System.out.println(number1);	
		if(number1.equals(number))
		{
			System.out.println("The bill amount is matching successfully with the discount price");
		}
		
		//13) Click on Delete and Delete the item from Bag 
		driver.findElement(By.xpath("//div[@class='delete-btn']")).click();
		driver.findElement(By.xpath("//div[text()='DELETE']")).click();
		
		System.out.println("The final page information is : " +driver.findElement(By.xpath("//div[@class='empty-cart']/p")).getText());
		

	}
	
}