import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Day1 {

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
    void Verify_sorting() throws Exception {
        Reporter.log("Open Base URL");
        driver.get(UTIL.Base_URL);

        String text_in_page = driver.findElement(By.cssSelector("h2:nth-child(1)")).getText();

        Reporter.log("Compare Titles");
        Assert.assertEquals(text_in_page,"THIS IS DEMO SITE FOR   ");

        Reporter.log("click on Mobile Menu");
        driver.findElement(By.linkText("MOBILE")).click();

        text_in_page = driver.findElement(By.cssSelector("div[class='page-title category-title'] h1")).getText();

        Reporter.log("Compare Titles");
        Assert.assertEquals(text_in_page,"MOBILE");

        Reporter.log("select type of Sort by Name");
        Select sortTypes = new Select(driver.findElement(By.xpath("//select[@title=\"Sort By\"]")));
        sortTypes.selectByVisibleText("Name");
        Reporter.log("Taking Screenshot");
        UTIL.takeSnapShot(driver);

        Reporter.log("check if the items are sorted by name or not");
        List<WebElement>items = driver.findElements(By.xpath("//h2[@class=\"product-name\"]/a"));
        List<String>items_name = new ArrayList<String>();
        int size=items.size();
        for (int i=0;i<size;i++){
            items_name.add(items.get(i).getText());
        }
        List<String> newList = new ArrayList<String>(items_name);
        Collections.sort(newList);
        for (int i=0;i<size;i++){
            if(newList.get(i)!=items_name.get(i)){
                Assert.fail("Items are not sorted by name");
            }
        }

    }

    @AfterTest
    void Finish(){
        driver.quit();
    }

}
