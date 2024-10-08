import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.time.Duration;


public class Day2 {

    public static WebDriver driver;

    @BeforeTest
    void Setup(){
        // WebDriverManager.chromedriver().setup();
        // Define ChromeDriver options
        ChromeOptions options=new ChromeOptions();

        // Define argument
        options.addArguments("headless");
        options.addArguments("no-sandbox");

        // Define and initiate the chrome driver
        driver = new ChromeDriver(options);

        // Implicit wait timeout for 20seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

    }

    @Test
    void Verify_scenario2() throws Exception {
        Reporter.log("Open Base URL");
        driver.get(UTIL.Base_URL);

        Reporter.log("click on Mobile Menu");
        driver.findElement(By.linkText("MOBILE")).click();

        Reporter.log("Get cost of sony Xperia mobile from the list of all mobiles");
        String price_1 = driver.findElement(By.cssSelector("span[id='product-price-1'] span[class='price']")).getText();

        Reporter.log("Click on mobile Xperia mobile");
        driver.findElement(By.cssSelector("#product-collection-image-1")).click();

        Reporter.log("Get cost of sony Xperia mobile from detail page");
        String price_2 = driver.findElement(By.cssSelector(".price")).getText();

        Reporter.log("Compare the 2 cost values");
        if(price_1.equals(price_2)){
            Reporter.log("Equal values");

        }else{
            Reporter.log("Not Equal values");
            Assert.fail();
        }

    }


    @AfterTest
    void Finish(){
        driver.quit();
    }

}

