package src.main.java.AirdoScre;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.lang.Thread;
import java.io.IOException;

class Sample{
    public static void main(String[] args) throws IOException, InterruptedException{

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get(AridoDomain.SCRE_URL);

        Thread.sleep(AridoDomain.WAIT_TIME);  // Let the user actually see something!

        
        driver.quit();
    }
}