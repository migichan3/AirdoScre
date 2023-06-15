package src.main.java.AirdoScre;

import java.util.List;
import java.lang.Thread;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class AirdoDomain{

    public static final String SCRE_URL = "https://www.airdo.jp/ap/rsv/book/search.html?adol=ja&adom=1&adob=0";

    public static final int WAIT_TIME = 1000;
 
    public static final int TIME_FORM_TO_NUMBER = 0;

    public static final int NUM_VACANT_NUMBER = 1;

    public static WebElement roundTrip;

    // 片道の場合。往復は基本利用しないが、もし往復にする場合はサイトをチェック
    public static final String ROUND_TRIP = "roundtrip2";

    // 基本的には１（片道）。往復の場合は0を選択する
    public static final int ROUND_TRIP_NUMBER = 1;

    public static WebElement placeDepartureSelectElement;

    // 行きの行き先。（例えば、帯広だったら、OBO）
    public static final String DAPARTURE_PLACE = "OBO";

    public static WebElement placeArrivalSelectElement;

    public static WebElement searchScreenBtn;

    // 行きの行き先。（例えば、帯広だったら、OBO）
    public static final String ARRIVAL_PLACE = "HND";

    public static WebElement nextVacantSheetBtn;

    public static WebElement changePosValBtn;

    public static WebElement onewayValBtn;

    public static WebElement airplaneTable;

    public static boolean hasAirplaneTable;

    public static void airdoSearchScreen(WebDriver driver){

        String roundTripPath = "//label[@class='radio']";
        List<WebElement> roundTripElements = driver.findElements(By.xpath(roundTripPath));
        roundTrip = roundTripElements.get(ROUND_TRIP_NUMBER);
        roundTrip.click();

        placeDepartureSelectElement = driver.findElement(By.className("place-departure"));
        Select select = new Select(placeDepartureSelectElement);
        select.selectByValue(DAPARTURE_PLACE);
        
        placeArrivalSelectElement = driver.findElement(By.className("place-arrival"));
        select = new Select(placeArrivalSelectElement);
        select.selectByValue(ARRIVAL_PLACE);

        searchScreenBtn = driver.findElement(By.xpath("//button[contains(@class, 'btn btn-yellow')]"));
        searchScreenBtn.click();

    }

    public static void airdoCheckVacantSheet(WebDriver driver, WriteCsv writeCsv){

        String nowSearchDate;
        String timeFromTo;
        String numVacant;

        nowSearchDate = driver.findElement(By.xpath("//span[contains(@class, 'calendar-title first-route')]")).getAttribute("data-date");
        System.out.println(String.format("\n\n日付：%s\n\n", nowSearchDate));
        
        changePosValBtn = driver.findElement(By.xpath("//a[contains(@data-tab, 't3')]"));
        changePosValBtn.click();

        hasAirplaneTable = !(driver.findElements(By.xpath("//a[contains(@data-tab, 't5')]")).isEmpty());

        if (hasAirplaneTable){

            onewayValBtn = driver.findElement(By.xpath("//a[contains(@data-tab, 't5')]"));

            List<WebElement> airplaneTable = driver.findElements(By.xpath("//div[contains(@class, 'tab-content current')]/ul[contains(@class, 'list-calendar')]/li"));

            for (WebElement cellAirplane : airplaneTable) {
                
                List<WebElement> cellUnit = cellAirplane.findElements(By.xpath(".//div[contains(@class, 'cell')]"));
                timeFromTo = cellUnit.get(TIME_FORM_TO_NUMBER).findElement(By.xpath(".//span[contains(@class, 'time')]")).getAttribute("textContent");
                numVacant = cellUnit.get(NUM_VACANT_NUMBER).findElement(By.xpath(".//span[contains(@class, 'mark')]")).getAttribute("textContent");
               
                System.out.println(String.format("時間：「%s」", timeFromTo));
                System.out.println(String.format("空き状況：「%s」", numVacant));
                
                // 書き込み
                writeCsv.exportCsv(nowSearchDate, timeFromTo, numVacant);
            }
        }

        nextVacantSheetBtn = driver.findElement(By.xpath("//a[contains(@class, 'link-next next')]"));
        nextVacantSheetBtn.click();
    }

}
