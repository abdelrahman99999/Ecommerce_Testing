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


public class Day5 {

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
    void Verify_scenario5() throws Exception {
        Reporter.log("Open Base URL");
        driver.get(UTIL.Base_URL);

        Reporter.log("Click on my account link");
        driver.findElement(By.linkText("MY ACCOUNT")).click();
        Thread.sleep(2000);

        Reporter.log("switching to new window");
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        Reporter.log("Click Create an Account link");
        driver.findElement(By.linkText("CREATE AN ACCOUNT")).click();
        Thread.sleep(2000);

        Reporter.log("fill New User information");
        driver.findElement(By.id("firstname")).clear();
        driver.findElement(By.id("firstname")).sendKeys(UTIL.First_Name);
        driver.findElement(By.id("lastname")).clear();
        driver.findElement(By.id("lastname")).sendKeys(UTIL.Last_Name);
        driver.findElement(By.id("email_address")).clear();
        driver.findElement(By.id("email_address")).sendKeys(UTIL.email);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(UTIL.Pass);
        driver.findElement(By.id("confirmation")).clear();
        driver.findElement(By.id("confirmation")).sendKeys(UTIL.Pass);

        Reporter.log("Click Register");
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        Thread.sleep(2000);


        Reporter.log("Verify Registration is done. Expected account registration done.");
        String vWelcome = ("WELCOME, " + UTIL.First_Name.toUpperCase()+ " " + UTIL.Last_Name.toUpperCase() + "!");
        String tWelcome = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[1]/div/p")).getText();
        System.out.println("tWelcome = "+tWelcome);

        try {
            Assert.assertEquals(tWelcome, vWelcome);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Reporter.log("Go to TV menu");
        driver.findElement(By.xpath(".//*[@id='nav']/ol/li[2]/a")).click();
        Thread.sleep(2000);


        Reporter.log("Add product in your wish list - use product - LG LCD");
        driver.findElement(By.xpath("//li/a[@class='link-wishlist']")).click();

        Reporter.log("Click SHARE WISHLIST");
        driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();


        Reporter.log("In next page enter Email and a message and click SHARE WISHLIST");
        driver.findElement(By.id("email_address")).clear();
        driver.findElement(By.id("email_address")).sendKeys("support@guru99.com");
        driver.findElement(By.id("message")).clear();
        driver.findElement(By.id("message")).sendKeys("Hey Mary!! this LCD TV looks ok, check it out !!.. cheers .. Berry");

        driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();

        Reporter.log("Check wishlist is shared. Expected wishlist shared successfully.");
        String vWishList = "Your Wishlist has been shared.";
        String wishList = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span")).getText();
        System.out.println("wishList = "+wishList);
        try {
            Assert.assertEquals(vWishList, wishList);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @AfterTest
    void Finish(){
        driver.quit();
    }

}

