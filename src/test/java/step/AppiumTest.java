package step;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AppiumTest {

    private static AndroidDriver driver;

    @BeforeMethod
    public void setup() {
        DesiredCapabilities caps = getDesiredCapabilities();
        try {
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AndroidDriver(url, caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Uygulamanın başlatılmasını bekle
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Uygulamayı aç
        openApp(driver);
    }

    private static DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel 7 Pro");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "10.0");
        caps.setCapability("appPackage", "com.BasicSDKDemo");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("noReset", "false");
        return caps;
    }

    @Test
    // Uygulamayı açan metot
    public static void openApp(AndroidDriver driver) {
        // Uygulama açılırken yapılacak işlemler buraya yazılır
        System.out.println("Uygulama başlatılıyor..");
    }

    @Test(dependsOnMethods = {"openApp"})
    // Oturum açma testi
    public void loginTest() {
        // Giriş ekranına geçiş yap
        WebElement accountAddressField = driver.findElement(By.id("accountAddressFieldID")); // Account adresi alanı ID'si
        WebElement usernameField = driver.findElement(By.id("usernameFieldID")); // Kullanıcı adı alanı ID'si
        WebElement passwordField = driver.findElement(By.id("passwordFieldID")); // Şifre alanı ID'si
        WebElement restIPField = driver.findElement(By.id("restIPFieldID")); // Rest IP alanı ID'si
        WebElement restPortField = driver.findElement(By.id("restPortFieldID")); // Rest Port alanı ID'si
        WebElement ICETimeoutField = driver.findElement(By.id("ICETimeoutFieldID")); // ICE Timeout alanı ID'si
        WebElement loginButton = driver.findElement(By.id("loginButtonID")); // Giriş butonu ID'si

        // Bilgileri gir
        accountAddressField.sendKeys("accountAddress");
        usernameField.sendKeys("username");
        passwordField.sendKeys("password");
        restIPField.sendKeys("restIP");
        restPortField.sendKeys("restPort");
        ICETimeoutField.sendKeys("ICETimeout");

        // Giriş butonuna tıkla
        loginButton.click();

    }

    private static void loadCredentials() {
        Properties prop = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream("test.properties");
            prop.load(inputStream);
            String accountAddress = prop.getProperty("accountAddress");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            String restIP = prop.getProperty("restIP");
            String restPort = prop.getProperty("restPort");
            String ICETimeout = prop.getProperty("ICETimeout");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
