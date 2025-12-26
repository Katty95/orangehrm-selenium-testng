package tests;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SmokeLoginTest {

	public static void main(String[] args) {
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	WebElement Username = driver.findElement(By.name("username"));
	Username.sendKeys("Admin");
	WebElement Password = driver.findElement(By.name("password"));
	Password.sendKeys("admin123");
	
	WebElement Login = driver.findElement(By.xpath("//button[@type='submit']"));
	Login.click();
	
	System.out.println(driver.getTitle());
	System.out.println(driver.getCurrentUrl());
	
	WebElement dashboard = driver.findElement(By.linkText("Dashboard"));
	System.out.println(dashboard.getText());
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='oxd-icon bi-stopwatch']"))).click();
	WebElement Attendence = driver.findElement(By.xpath("//h6[text()='Attendance']"));
	System.out.println(Attendence.getText());
	
	/*Date date=new Date();
	SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
	sim.format(date);
	Calendar cal = sim.getCalendar();
	cal.add(Calendar.DAY_OF_MONTH,34);
	String reqDate = sim.format(cal.getTime());
	
WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(20));
	
	WebElement dateEnter = wait1.until(ExpectedConditions.refreshed
	(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='yyyy-mm-dd']"))));
	
	dateEnter.clear();
	dateEnter.sendKeys(reqDate);
	WebElement inputTime = driver.findElement(By.cssSelector("input[placeholder='hh:mm']"));
	inputTime.clear();
	inputTime.sendKeys("09:40 AM");
	
	WebElement text = driver.findElement(By.xpath("//textarea[@placeholder='Type here']"));
	text.sendKeys("PunchIN");
	WebElement IN = driver.findElement(By.xpath("//button[@type='submit']"));
	IN.click();*/
	
	// Date
	SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DAY_OF_MONTH, 0);
	String reqDate = sim.format(cal.getTime());
	WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(20));
	WebElement dateEnter = wait1.until(ExpectedConditions.refreshed(
	    ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//input[@placeholder='yyyy-mm-dd']")
	    )
	));
	dateEnter.clear();
	dateEnter.sendKeys(reqDate);

	// Time
	SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
	Calendar timeCal = Calendar.getInstance();
	timeCal.add(Calendar.HOUR, 0);
	String futureTime = timeFormat.format(timeCal.getTime());

	WebElement inputTime = driver.findElement(By.cssSelector("input[placeholder='hh:mm']"));
	inputTime.clear();
	inputTime.sendKeys(futureTime);
	WebElement text = driver.findElement(By.xpath("//textarea[@placeholder='Type here']"));
	text.sendKeys("PunchIN");
	WebElement IN = driver.findElement(By.xpath("//button[@type='submit']"));
	IN.click();
	
	
	
	}

}
