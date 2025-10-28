import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskUtils {

    public static String replaceDates(String text) {
        String regex = "\\b\\d{2}\\.\\d{2}\\.\\d{4}\\b|_+\\._+\\._+";
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return text.replaceAll(regex, currentDate);
    }

    public static String findPhones(String text) {
        String regex = "(\\+?\\d{1,3}[\\s-]?)?(\\(?\\d{2,3}\\)?[\\s-]?)?\\d{3}[\\s-]?\\d{2}[\\s-]?\\d{2}";
        Matcher m = Pattern.compile(regex).matcher(text);
        StringBuilder sb = new StringBuilder();
        while (m.find()) sb.append(m.group()).append("\n");
        return sb.length() > 0 ? sb.toString() : "Номери не знайдено";
    }
    public static boolean checkExpression(String expr) {
        expr = expr.replaceAll("\\s+", "");
        String regex = "^[+-]?\\d+(?:[+\\-*/]\\d+)*$";
        return expr.matches(regex);
    }
}
