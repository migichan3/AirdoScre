package src.main.java.AirdoScre;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;

public class AirdoDomain {

    public static final String SCRE_URL = "https://www.airdo.jp/ap/rsv/book/search.html?adol=ja&adom=1&adob=0";

    public static final int WAIT_TIME = 3000;

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

    public static void airdoSearchScreen(WebDriver driver){
        
        // String roundTripPath = "//label[@class='radio']/input[@name='roundtrip'][@id='" + ROUND_TRIP + "'][@type='radio']";
        // roundTrip = driver.findElements(By.xpath(roundTripPath))[1];

        String roundTripPath = "//label[@class='radio']";
        List<WebElement> roundTripElements = driver.findElements(By.xpath(roundTripPath));
        roundTrip = roundTripElements.get(ROUND_TRIP_NUMBER);
        roundTrip.click();

        System.out.println("ラジオボタン入力OK");

        placeDepartureSelectElement = driver.findElement(By.className("place-departure"));
        Select select = new Select(placeDepartureSelectElement);
        select.selectByValue(DAPARTURE_PLACE);

        System.out.println("出発の選択OK");
        
        placeArrivalSelectElement = driver.findElement(By.className("place-arrival"));
        select = new Select(placeArrivalSelectElement);
        select.selectByValue(ARRIVAL_PLACE);

        System.out.println("到着の選択OK");
        
        searchScreenBtn = driver.findElement(By.xpath("//button[contains(@class, 'btn btn-yellow')]"));
        searchScreenBtn.click();

        System.out.println("ボタンクリック完了！");
    }

}
