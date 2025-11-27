import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class b11_01 {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.timeanddate.com/worldclock/ukraine/kyiv").get();
        Element timeElement = doc.selectFirst("span#ct");
        String webTimeStr = timeElement.text();
        String timeText = timeElement.text().trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");
        LocalTime serverTime = LocalTime.parse(timeText, formatter);

        LocalTime webTime = LocalTime.parse(webTimeStr, formatter);
        LocalTime localTime = LocalTime.now();


        System.out.println("Час з сайту:    " + webTime);
        System.out.println("Локальний час:  " + localTime);
        System.out.println("Час збігається: " + (localTime.getHour() == webTime.getHour() && localTime.getMinute() == webTime.getMinute()));
    }
}
