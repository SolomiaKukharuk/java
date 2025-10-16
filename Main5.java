import java.util.*;
import java.util.stream.Collectors;

public class Main5 {

    public static void main(String[] args) {
       b05_01();
       b05_02();
       b05_09();
    }


    private static boolean isValidNoNestedParentheses(String s) {
        int depth = 0;
        int maxDepth = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                depth++;
                maxDepth = Math.max(maxDepth, depth);
                if (depth > 1) return false;
            } else if (ch == ')') {
                depth--;
                if (depth < 0) return false;
            }
        }
        return depth == 0 && maxDepth >= 0;
    }
    public static void b05_01(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть рядок для обробки дужок: ");
        String s1 = sc.nextLine();

        if (isValidNoNestedParentheses(s1)) {
            String removed = s1.replaceAll("\\([^()]*\\)", "");
            System.out.println("Дужки коректні (без вкладень). Після видалення: ");
            System.out.println(removed);
        } else {
            System.out.println("ПОМИЛКА: дужки розставлені некоректно (непара або є вкладені дужки).");
        }
    }
    public static void b05_02(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть рядок з ЛИШЕ цифр і літер: ");
        String s2 = sc.nextLine();

        if (!s2.matches("[A-Za-z0-9]*")) {
            System.out.println("Рядок містить символи, відмінні від літер і цифр.");
        } else {
            boolean a = propertyA(s2);
            boolean b = propertyB(s2);
            boolean c = propertyC(s2);

            System.out.println("a) починається з ненульової цифри, далі тільки літери, і їх кількість = цій цифрі: " + a);
            System.out.println("b) рівно одна цифра, її значення = довжині рядка: " + b);
            System.out.println("c) сума всіх цифр дорівнює довжині рядка: " + c);
        }
    }
    // a)
    private static boolean propertyA(String s) {
        if (s.isEmpty()) return false;
        char first = s.charAt(0);
        if (!Character.isDigit(first) || first == '0') return false;
        int k = first - '0';
        String tail = s.substring(1);
        return tail.matches("[A-Za-z]+") && tail.length() == k;
    }

    // b)
    private static boolean propertyB(String s) {
        int countDigits = 0;
        int value = -1;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                countDigits++;
                value = ch - '0';
                if (countDigits > 1) return false;
            } else if (!Character.isLetter(ch)) {
                return false;
            }
        }
        return countDigits == 1 && value == s.length();
    }

    // c)
    private static boolean propertyC(String s) {
        int sum = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) sum += ch - '0';
            else if (!Character.isLetter(ch)) return false;
        }
        return sum == s.length();
    }
    public static void b05_09(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Скільки країн ви введете? ");
        int n = Integer.parseInt(sc.nextLine().trim());

        List<Country> countries = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            System.out.println("\nКраїна #" + i);
            System.out.print("Назва: ");
            String name = sc.nextLine().trim();

            System.out.print("Площа (число): ");
            double area = Double.parseDouble(sc.nextLine().trim().replace(",", "."));

            System.out.print("Континент/регіон: ");
            String continent = sc.nextLine().trim();

            countries.add(new Country(name, area, continent));
        }

        if (countries.isEmpty()) {
            System.out.println("Дані не введені. Завершення.");
            return;
        }

        //(a)
        System.out.print("\n(a) Введіть граничну площу: ");
        double limit = Double.parseDouble(sc.nextLine().trim().replace(",", "."));

        List<Country> filtered = countries.stream()
                .filter(c -> c.getArea() <= limit)
                .sorted(Comparator.comparingDouble(Country::getArea))
                .collect(Collectors.toList());

        System.out.println("\nКраїни з площею ≤ " + limit + ":");
        if (filtered.isEmpty()) System.out.println("— немає —");
        else filtered.forEach(System.out::println);

        //(b)
        System.out.println("\n(b) Кількість країн кожного континенту:");
        Map<String, Long> counts = countries.stream()
                .collect(Collectors.groupingBy(Country::getContinent, TreeMap::new, Collectors.counting()));
        counts.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
