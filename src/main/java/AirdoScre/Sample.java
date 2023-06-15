package src.main.java.AirdoScre;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import java.lang.Thread;
import java.io.IOException;

class Sample{

    private static int SEARCH_DATE_NUM = 10;

    public static void main(String[] args) throws IOException, InterruptedException{

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get(AirdoDomain.SCRE_URL);

        Thread.sleep(AirdoDomain.WAIT_TIME);  // Let the user actually see something!
        
        // CSVファイルの記述開始
        WriteCsv writeCsv = new WriteCsv();

        // 検索サイトの選択実装
        AirdoDomain.airdoSearchScreen(driver);
        
        for (int i = 0; i < SEARCH_DATE_NUM ;i++) {
            Thread.sleep(AirdoDomain.WAIT_TIME);
            AirdoDomain.airdoCheckVacantSheet(driver, writeCsv);
        }
        
        writeCsv.finishExportCsv();
        driver.quit();
    }
}