

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateDriver {

	static WebDriver driver;
	
	// 1
	public static WebDriver getDriver() {
		driver = new ChromeDriver();
		//driver = new EdgeDriver();
		driver.get("http://cookbook.seleniumacademy.com/Alerts.html ");
		return driver;
	}
}
