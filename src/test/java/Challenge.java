import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class Challenge {

    private static AndroidDriver<MobileElement> driver;

    //static AppiumDriver driver;
    public static void main(String[] args) {
        try {
            OpenChallenge();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delay() throws InterruptedException {
        Thread.sleep(1000);
    }

    public static void OpenChallenge() throws Exception {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("devicename", "sridhar");
        cap.setCapability("udid", "2d14e0709804");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformversion", "7.0");
        cap.setCapability("appPackage", "com.example.challenge");
        cap.setCapability("appActivity", "com.example.challenge.MainActivity");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(url, cap);
        System.out.println("App Started");
        System.out.println("Wifi Enabled:" + driver.getConnection().isWiFiEnabled() + " , Mobile Date Enabled: " + driver.getConnection().isDataEnabled());
        MobileElement product, addreview, sendkeys, rateproduct, rate, save;

        if (driver.getConnection().isWiFiEnabled() || driver.getConnection().isDataEnabled()) {
            int rating;
            int commentId;
            String[] comments = {"Testing Comment 1", "Testing Comment 2", "Testing Comment 3", "Testing Comment 4", "Testing Comment 5"};

            int numberOfItems = 22;

            for (int i = 1; i <= numberOfItems; i++) {
                rating = ThreadLocalRandom.current().nextInt(0, 5);
                commentId = ThreadLocalRandom.current().nextInt(0, 4);
                delay();
                product = (MobileElement) driver.findElementsByClassName("android.widget.ImageView").get(1);
                product.click();
                System.out.println("Product clicked " + i);
                delay();
                addreview = (MobileElement) driver.findElement(By.id("com.example.challenge:id/addReview"));
                addreview.click();
                System.out.println("review clicked");
                delay();
                sendkeys = (MobileElement) driver.findElement(By.id("com.example.challenge:id/reviewDetails"));
                sendkeys.sendKeys(comments[commentId]);
                System.out.println("text entered");
                delay();
                rateproduct = (MobileElement) driver.findElement(By.id("com.example.challenge:id/reviewNumber"));
                rateproduct.click();
                System.out.println("review button clicked");
                delay();
                rate = (MobileElement) driver.findElementsByClassName("android.widget.CheckedTextView").get(rating);
                rate.click();
                System.out.println("rating selected");
                delay();
                save = (MobileElement) driver.findElement(By.id("com.example.challenge:id/saveReview"));
                save.click();
                System.out.println("review saved");
                delay();
                driver.navigate().back();
                System.out.println("products screen displayed");

                TouchAction touchAction = new TouchAction(driver);
                touchAction.press(PointOption.point(988, 850))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                        .moveTo(PointOption.point(988, 230))
                        .release();
                touchAction.perform();
                delay();
            }
            System.out.println("Tested Passed Successfully");
            driver.quit();
        } else {
            System.out.println("Blank Screen displayed");
        }
    }
}
