package test;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstPage {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		//Launch Amazon.in website
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000,TimeUnit.MICROSECONDS);
		
		//Navigate and search for samsung 
		WebElement searchButton = driver.findElement(By.id("twotabsearchtextbox"));	
		searchButton.sendKeys("samsung");
		
		//Click on search button
		WebElement submitBtn = driver.findElement(By.id("nav-search-submit-button"));	
		submitBtn.click();
		
		//Find all products and their prices
	    List<WebElement> AllProduct = driver.findElements(By.xpath("//div[@class='a-section']//span[starts-with(text(),'Samsung ')]"));
	    List<WebElement> AllPrice = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']//span[@class='a-price-whole']"));
	    
	    for( int i=0;i<AllProduct.size();i++) {
	    	System.out.println(AllProduct.get(i).getText()+ " " + AllPrice.get(i).getText());
	    }
	    
	    //Get the first product title in the list
	    String firstProdTitle = AllProduct.get(0).getText();
	    
	    //Get parent window handler
	    String ParentWin = driver.getWindowHandle();
	    
	    //Click first product
	    AllProduct.get(0).click();
	    
	    //Switch window handlers
	    Set<String> allwins = driver.getWindowHandles();
		for (String win : allwins) {
			if(!win.equals(ParentWin)) {
				driver.switchTo().window(win);
			}
		}
	    
		//Get the product title
	    WebElement prodTitle = driver.findElement(By.id("productTitle"));	
		String product = prodTitle.getText();
		
		//Validating titles are the same
		if(product.equals(firstProdTitle)) {
			System.out.println("Title is the same");
		}else {
			System.out.println("Title is not the same");
		}
	    
		driver.quit();   
	}
}
