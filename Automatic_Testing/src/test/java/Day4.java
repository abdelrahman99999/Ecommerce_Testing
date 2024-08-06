import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Day4 {

    public static WebDriver driver;

    @BeforeTest
    void Setup(){

        ChromeOptions options=new ChromeOptions();

        options.addArguments("headless");
        options.addArguments("no-sandbox");
        driver = new ChromeDriver(options);

        // Implicit wait timeout for 20seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

    }

    @Test
    void Verify_scenario4() throws Exception {
        Reporter.log("Open Base URL");
        driver.get(UTIL.Base_URL);


        Reporter.log("click on Mobile Menu");
        driver.findElement(By.linkText("MOBILE")).click();



        Reporter.log("Add to compare(Iphone)");
        driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
        String message = driver.findElement(By.cssSelector("li[class='success-msg'] ul li span")).getText();
        if(message.equals("The product IPhone has been added to comparison list.")){
            Reporter.log("message shown with correct message");

        }else{
            Reporter.log("No message shown with correct message");
            Assert.fail();
        }

        Reporter.log("Add to compare(Sony xperia)");
        driver.findElement(By.xpath("//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
         message = driver.findElement(By.cssSelector("li[class='success-msg'] ul li span")).getText();
        if(message.equals("The product Sony Xperia has been added to comparison list.")){
            Reporter.log("message shown with correct message");

        }else{
            Reporter.log("No message shown with correct message");
            Assert.fail();
        }

        Reporter.log("Click on compare button");
        driver.findElement(By.xpath("//button[@title='Compare']")).click();

        String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();

        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        Reporter.log("switch to popup window");
        driver.switchTo().window(subWindowHandler); // switch to popup window

        String title = driver.findElement(By.tagName("h1")).getText();
        if(title.equals("COMPARE PRODUCTS")){
            Reporter.log("title shown with correctly");

        }else{
            Reporter.log("No title shown correctly");
            Assert.fail();
        }

        driver.findElement(By.xpath("//span[contains(text(),'Close Window')]")).click();
    }


    @AfterTest
    void Finish(){
        driver.quit();
    }

}

