import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class b11_11 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine().toLowerCase().trim();
        String url = "https://www.timeanddate.com/weather/ukraine/" + city + "/ext";

        Document doc = Jsoup.connect(url).get();
        Elements rows = doc.select("table#wt-ext tbody tr");

        for (Element row : rows) {
            String date = row.select("th").text();
            Elements temps = row.select("td");

            if (temps.size() >= 2) {
                String max = temps.get(1).text();
                String min = temps.get(2).text();
                System.out.println(date + ": max " + max + ", min " + min);
            }
        }
    }
}
