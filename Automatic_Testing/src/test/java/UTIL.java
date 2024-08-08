import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UTIL {
    public static final String Base_URL="http://live.techpanda.org";
    public static final String First_Name="Abdelrahman";
    public static final String Last_Name="Tester";
    public static final String email="purdei7gno@gufum.com";
    public static final String Pass="123456";


    public static void takeSnapShot(WebDriver webdriver) throws Exception{
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        //
        String timeStamp;
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String fileWithPath ="test-output\\screenshots\\"+timeStamp+".png";
        //
        File DestFile=new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);

        String image_as_webElement = "<img src='"+"screenshots\\"+timeStamp+".png"+"' alt='screenshot' style=\"width:500px;height:500px;\" />";
        Reporter.log(image_as_webElement);
    }
}
