package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginCheck {

	public static void main(String[] args) {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		WebElement Username = driver.findElement(By.name("username"));
		Username.sendKeys("Admin");
		WebElement Password = driver.findElement(By.name("password"));
		Password.sendKeys("admin123");
		
		WebElement login = driver.findElement(By.xpath("//button[@type='submit']"));
		login.submit();
		
		System.out.println(driver.getCurrentUrl());

		String pageTitle = driver.getTitle();
		if(pageTitle.equals("OrangeHRM")) {
			System.out.println("Login Success");
	}else {
		System.out.println("Login unsuccessfull");
	}
		
	
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.linkText("Add Employee")).click();
		driver.findElement(By.name("firstName")).sendKeys("Ankit");
		driver.findElement(By.name("lastName")).sendKeys("Singh");
		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
		WebElement Details = driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']"));
		String text = Details.getText();
		Assert.assertEquals( "Personal Details",text);
		/*System.out.println(Details.getText());
		if(Details.isDisplayed())
		{
			System.out.println("Personal Details is visible");
		}
		else {
			System.out.println("Personal Details is not visible");
		}*/
		
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
		driver.findElement(By.linkText("Logout")).click();
		driver.quit();
		
	}

}
