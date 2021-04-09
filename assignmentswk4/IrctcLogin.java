package assignmentswk4;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IrctcLogin {

	public static void main(String[] args) {
		WebDriverManager.chromiumdriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.irctc.co.in/");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		// Alert page okay
		driver.findElement(By.xpath("//span[text()='Alert']/following::button[@class='btn btn-primary']")).click();
		// click flights
		String mainWindow = driver.getWindowHandle();
		driver.findElement(By.linkText("FLIGHTS")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		String chk = "Air Ticket Booking | Book Flight Tickets | Cheap Air Fare - IRCTC Air";
		for (String string : windowHandles) {
			if (!string.equals(mainWindow)) {
				driver.switchTo().window(string);
				if (chk.equals(driver.getTitle())) {
					String flightsirctccoin = driver
							.findElement(By.xpath("(//a[@href='mailto:flights@irctc.co.in'])[1]")).getText();
					System.out.println("mailID: " + flightsirctccoin);
				}

			}
		}
	}

}
