import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.time.Duration;


public class Day2 {

    public static WebDriver driver;

    @BeforeTest
    void Setup(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        // Implicit wait timeout for 20seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

    }

    @Test
    void Verify_scenario() throws Exception {
        Reporter.log("Open Base URL");
        driver.get(UTIL.Base_URL);

        String text_in_page = driver.findElement(By.cssSelector("h2:nth-child(1)")).getText();

        Reporter.log("Compare Titles");
        Assert.assertEquals(text_in_page,"THIS IS DEMO SITE FOR   ");

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

