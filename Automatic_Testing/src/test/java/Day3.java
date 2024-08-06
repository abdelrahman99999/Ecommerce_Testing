import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.time.Duration;


public class Day3 {

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
    void Verify_scenario3() throws Exception {
        Reporter.log("Open Base URL");
        driver.get(UTIL.Base_URL);


        Reporter.log("click on Mobile Menu");
        driver.findElement(By.linkText("MOBILE")).click();

        Reporter.log("Click Add to Cart for sony Xperia mobile");
        driver.findElement(By.xpath("//li[3]//div[1]//div[3]//button[1]//span[1]//span[1]")).click();

        Reporter.log("Edit quantity for the item to be 1000");
        driver.findElement(By.cssSelector("input[title='Qty']")).sendKeys("1000");

        Reporter.log("click update button");
        driver.findElement(By.cssSelector("button[title='Update']")).click();

        String errorMessage = driver.findElement(By.cssSelector("li[class='error-msg'] ul li span")).getText();

        Reporter.log("Check error message");
        if(errorMessage.equals("Some of the products cannot be ordered in requested quantity.")){
            Reporter.log("error message shown with correct message");

        }else{
            Reporter.log("No error message shown with correct message");
            Assert.fail();
        }
        Reporter.log("Empty Cart");
        driver.findElement(By.cssSelector("button[id='empty_cart_button']")).click();

        Reporter.log("check empty cart message");
        String m = driver.findElement(By.cssSelector("div[class='page-title'] h1")).getText();

        if(m.equals("SHOPPING CART IS EMPTY")){
            Reporter.log("message shown with correct message");

        }else{
            Reporter.log("No message shown with correct message");
            Assert.fail();
        }

    }


    @AfterTest
    void Finish(){
        driver.quit();
    }

}

