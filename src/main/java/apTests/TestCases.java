
package apTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//Selenium Imports
import java.util.logging.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
///
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;


public class TestCases {
    RemoteWebDriver driver;

    public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);


        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.google.com");
        driver.get("https://www.amazon.in/");
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.toLowerCase().contains("amazon")) {
            System.out.println("PASS: URL contains 'amazon'");
        } else {
            System.out.println("FAIL: URL does not contain 'amazon'");
        }

        System.out.println("end Test case: testCase01");
    }

  

    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");

        driver.get("https://www.amazon.in/");

        // Handle captcha manually if it appears
        Thread.sleep(20000);

        // Locate search bar and search
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("laptop");

        searchBox.submit();

       // Thread.sleep(15000);

        // Verify results contain "laptop"
        //List<WebElement> results = driver.findElements(By.cssSelector("span.a-size-medium"));

      //  List<WebElement> results = driver.findElements(By.xpath("//span[contains(@class,'a-size-medium')]"));      
      //  List<WebElement> results = driver.findElements(By.xpath("//h2//span"));   
      WebDriverWait wait = new WebDriverWait(driver, 20);
      List<WebElement> results = wait.until(
        ExpectedConditions.presenceOfAllElementsLocatedBy(
            //By.xpath("//*[contains(text(),'Laptop') or contains(text(),'laptop')]")
            By.xpath("//a//h2//span")
        )
    );

       
         boolean found = false;
        for (WebElement item : results) {
            String text = item.getText();
            
            if (text!=null && text.toLowerCase().contains("laptop")) {
                found = true;
                break;
            }
        }



        if (found) {
            System.out.println("PASS: Search results contain 'laptop'");
        } else {
            System.out.println("FAIL: Search results do not contain 'laptop'");
        }

        System.out.println("End Test case: testCase02");
    }

    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
    
        driver.get("https://www.amazon.in/");
    
        Thread.sleep(20000);
    
        // Click Electronics (may change based on UI)
        WebElement electronics = driver.findElement(By.linkText("Electronics"));
        electronics.click();
    
        Thread.sleep(3000);
    
        String currentUrl = driver.getCurrentUrl();
    
        if (currentUrl.toLowerCase().contains("electronics")) {
            System.out.println("PASS: Navigated to Electronics page");
        } else {
            System.out.println("FAIL: Navigation failed");
        }
    
        System.out.println("End Test case: testCase03");
    }

}

///html/body/div[1]/div[1]/div[1]/div[1]/div/span[1]/div[1]/div
//*[@id="2e988828-d69f-485d-b61e-b344cf6b2f7f"]/div/div/div/div/span/div/div/div/div[2]/div/div/div[1]/a/h2
//*[@id="e00e1405-af2d-422a-b9f9-48bb23573634"]/div/div/div/div/span/div/div/div/div[2]/div/div/div[1]/a/h2
////a/h2/span/text()