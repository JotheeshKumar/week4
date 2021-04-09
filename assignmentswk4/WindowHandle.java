package assignmentswk4;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandle { 
public static void main(String[] args) throws Exception {
	WebDriverManager.chromiumdriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	ChromeDriver driver = new ChromeDriver(options);
	driver.manage().window().maximize();
	driver.get("http://www.leafground.com/pages/Window.html");
	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();

Set<String>s=driver.getWindowHandles();
List<String> aList = new ArrayList<String>(s);
//pages available
for (int i=0;i<aList.size();i++) {
System.out.println(	"page-"+i+" ="+driver.switchTo().window(aList.get(i)).getTitle());
}
//switching control to last page
String lastHandle = aList.get(aList.size()-1);
driver.switchTo().window(lastHandle);
String title = driver.getTitle();
System.out.println("control to last page & title: "+title);

}
}
