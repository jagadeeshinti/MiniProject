

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class MiniProject {

	static String simpleAlertText;
	static String confirmText;
	
	// 2 - screenshot
	public static void takeScreenshot(String s) throws HeadlessException, AWTException, IOException {
		BufferedImage sc = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(sc, "jpg", new File("C:\\Users\\2318874\\eclipse-workspace\\seleniumjagadeesh\\src\\test\\java\\miniproject\\screenshots\\"+s+".jpg"));
	}
	
	// 3 - alertbox - simple
	public static void alertSimple(WebDriver driver) throws InterruptedException, HeadlessException, AWTException, IOException {
		driver.findElement(By.id("simple")).click();
		Thread.sleep(500);
		takeScreenshot("screenshot-1");
		Alert a = driver.switchTo().alert();
		simpleAlertText = a.getText();
		System.out.println(a.getText());
		Thread.sleep(2000);
		
		a.accept();
	}
	
	// 4 - Check the message displayed on Alert box is "Hello! I am an alert box!". 
	public static boolean checkSimpleAlert(String message) {
		if(simpleAlertText.equals(message)) {
			return true;
		}
		return false;
	}
	
	
	// 5 - alertbox - confirm
	public static void alertConfirm(WebDriver driver) throws InterruptedException, HeadlessException, AWTException, IOException {
		WebElement x= driver.findElement(By.id("confirm"));
		driver.switchTo().activeElement();
		x.click();
		Thread.sleep(500);
		takeScreenshot("screenshot-2");
		Alert b = driver.switchTo().alert();
		confirmText = b.getText();
    	System.out.println(b.getText());
    	Thread.sleep(2000);
    	
		b.dismiss();
	}
	
	// 6 - Check the message displayed on Alert box is "Hello! I am an alert box!". 
		public static boolean checkConfirmAlert(String message) {
			if(confirmText.equals(message)) {
				return true;
			}
			return false;
		}
	
	// 7 - alertbox - prompt
	public static void alertPrompt(WebDriver driver, String text) throws HeadlessException, AWTException, IOException, InterruptedException {
		WebElement y= driver.findElement(By.id("prompt"));
		driver.switchTo().activeElement();
		y.click();
		Thread.sleep(500);
		takeScreenshot("screenshot-3");
		Alert g= driver.switchTo().alert();
		Thread.sleep(5000);
		
		g.sendKeys(text);
		g.accept();
		Thread.sleep(2000);
	}
	
	// 8
	public static void finalText(WebDriver driver) throws HeadlessException, AWTException, IOException {
		takeScreenshot("screenshot-4");
	}
	
	
	
	
	public static void main(String[] args) throws HeadlessException, InterruptedException, AWTException, IOException {
		// TODO Auto-generated method stub
		
		// 1
		WebDriver driver = CreateDriver.getDriver();
		
		driver.manage().window().maximize();
		
		FileInputStream file = new FileInputStream("C:\\Users\\2318874\\eclipse-workspace\\seleniumjagadeesh\\src\\test\\java\\miniproject\\MiniProjectData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		// 3
		alertSimple(driver);
		Thread.sleep(500);
		
		// 4
		XSSFRow row1 = sheet.getRow(0);
		XSSFCell cell = row1.getCell(0);
		String text = cell.toString();
		System.out.println("Simple alert box check = "+checkSimpleAlert(text));
		
		
		// 5
		alertConfirm(driver);
		Thread.sleep(1000);
		
		// 6
		XSSFRow row2 = sheet.getRow(1);
		XSSFCell cell2 = row2.getCell(0);
		String text2 = cell2.toString();
		System.out.println("Confirm alert box check = "+checkConfirmAlert(text2));
		
		// 7
		XSSFRow row3 = sheet.getRow(2);
		XSSFCell cell3 = row3.getCell(0);
		String text3 = cell3.toString();
		alertPrompt(driver,text3);
		Thread.sleep(4000);
		
		// 8
		finalText(driver);
		
		System.out.println(text3);
		
		driver.quit();
		
		

	}

}
