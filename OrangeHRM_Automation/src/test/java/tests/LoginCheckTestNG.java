package tests;

import java.time.Duration;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class LoginCheckTestNG {
	
	public String baseURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public WebDriver driver;
	
	@BeforeTest
	public void setup() {
		System.out.println("Before test executed");
		driver=new ChromeDriver();
		//maximise window
		driver.manage().window().maximize();
		//open URL
		driver.get(baseURL);
		//universal wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
		
		@Test(priority = 2,enabled = false)
		public void loginTest()
		{
			WebElement Username = driver.findElement(By.name("username"));
			Username.sendKeys("Admin");
			WebElement Password = driver.findElement(By.name("password"));
			Password.sendKeys("admin123");
			WebElement login = driver.findElement(By.xpath("//button[@type='submit']"));
			login.submit();
			
			//verify
			String pageTitle = driver.getTitle();
			
			logout();
			Assert.assertEquals("OrangeHRM", pageTitle);
			}
		
		
		@Test(priority = 3)
		public void AddEmployee() {
			logiN();
			driver.findElement(By.xpath("//span[text()='PIM']")).click();
			driver.findElement(By.linkText("Add Employee")).click();
			driver.findElement(By.name("firstName")).sendKeys("Ankit");
			driver.findElement(By.name("lastName")).sendKeys("Singh");
			driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
			WebElement Details = driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']"));
			String text = Details.getText();
			logout();
			Assert.assertEquals( "Personal Details",text);
			
		}
		
		
		@Test(priority=4)
		public void searchEmployeebyName()
		{
			logiN();
			driver.findElement(By.xpath("//span[text()='PIM']")).click();
			driver.findElement(By.linkText("Add Employee")).click();
			//Select employee list
			driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
			driver.findElements(By.tagName("input")).get(1).sendKeys("Ankit");
			driver.findElements(By.tagName("input")).get(2).sendKeys("0409");
			driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
			
			//verify
			List<WebElement> element = driver.findElements(By.xpath("//span[@class='oxd-text oxd-text--span']"));
			
			for(int i=0; i<element.size();i++)
			{
				   System.out.println("At index" +i+ "text is:"+element.get(i).getText());  
			}
			logout();
		}
		
		
		@Test(priority = 1,enabled = false)
		public void loginFail()
		{
			WebElement Username = driver.findElement(By.name("username"));
			Username.sendKeys("Admin");
			WebElement Password = driver.findElement(By.name("password"));
			Password.sendKeys("admin");
			WebElement login = driver.findElement(By.xpath("//button[@type='submit']"));
			login.submit();
			
			//verify
			String Errormessage = "Invalid credentials";
			String error = driver.findElement(By.xpath("//p[normalize-space()='Invalid credentials']")).getText();
			Assert.assertEquals(Errormessage, error);
		}
			public void logiN() {
				
				WebElement Username = driver.findElement(By.name("username"));
				Username.sendKeys("Admin");
				WebElement Password = driver.findElement(By.name("password"));
				Password.sendKeys("admin123");
				WebElement login = driver.findElement(By.xpath("//button[@type='submit']"));
				login.submit();
			}
			public void logout() {
				driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
				
				//driver.findElement(By.linkText("Logout")).click();
				
			List<WebElement> Logout1 = driver.findElements(By.xpath("//a[@class='oxd-userdropdown-link']"));
				for(int i=0; i<Logout1.size();i++)
				{
					System.out.println(i+":"+Logout1.get(i).getText());
				}
				Logout1.get(3).click();
			}
		@AfterTest
		public void teardown() throws InterruptedException {
			Thread.sleep(10000);
			//logout();
			driver.quit();
			
		}
		
	}


