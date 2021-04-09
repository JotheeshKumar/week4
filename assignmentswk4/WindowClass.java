package assignmentswk4;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowClass {

	public static void main(String[] args) {
		WebDriverManager.chromiumdriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		//get the main handle details
		String mainHandle = driver.getWindowHandle();
		//2. Type "one plus 7 pro mobiles" in Search Box and Enter
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("one plus 7 pro obiles");
		//3. Print the price of the first resulting mobile
		String price = driver.findElement(By.xpath("(//div[contains(@class,'a-row a-size-base')])[1]")).getText();
		System.out.println("1st item price: "+price);
		//4. Click on the Mobile (First resulting) image
		driver.findElement(By.xpath("(//img[@class='s-image'])[1]")).click();
		//get all windows details
		Set<String> handles =  driver.getWindowHandles();
		   for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(mainHandle))
		          {
		          driver.switchTo().window(windowHandle);
		          String ratings = driver.findElement(By.xpath("(//span[@id='acrCustomerReviewText'])[1]")).getText();
		          driver.findElement(By.id("add-to-cart-button")).click();
		          String addedToCart = driver.findElement(By.xpath("(//h4[@class='a-alert-heading'])[3]")).getText();
		          String check = "Added to Cart";
		          if(addedToCart.equals(check)) {
		        	  driver.close(); //closing child window
		          }
		        
		         driver.switchTo().window(mainHandle); //cntrl to parent window
		          }
		       }
	}

}
