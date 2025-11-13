import java.io.*;
import java.util.*;

public class Main8{

    public static void main(String[] args) {
        b08_01();
        b08_02();
        b08_03();
        b08_04();
    }

    //b08_01
    private static void b08_01() {
        System.out.println("Введіть число:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Stack<Integer> stack = new Stack<>();

        while (n > 0) {
            stack.push(n % 10);
            n /= 10;
        }
        System.out.print("Результат: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
        System.out.println();
    }


    //b08_02
    private static void b08_02() {
        System.out.println("Введіть назву файлу (наприклад input.txt):");
        Scanner sc = new Scanner(System.in);
        String filename = sc.nextLine();

        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу!");
            return;
        }

        Collections.sort(lines);

        System.out.println("Відсортовані рядки:");
        lines.forEach(System.out::println);
    }


    // b08_03
    private static void b08_03() {
        System.out.println("Введіть шлях до каталогу:");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();

        File root = new File(path);

        if (!root.exists()) {
            System.out.println("Каталог не існує!");
            return;
        }

        List<String> result = new ArrayList<>();
        listFiles(root, result);

        System.out.println("Всі елементи каталогу:");
        result.forEach(System.out::println);
    }

    private static void listFiles(File dir, List<String> list) {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File f : files) {
            list.add(f.getAbsolutePath());
            if (f.isDirectory()) {
                listFiles(f, list);
            }
        }
    }


    //b08_04
    private static void b08_04() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть степінь першого многочлена:");
        int k1 = sc.nextInt();
        HashMap<Integer, Integer> p1 = new HashMap<>();
        System.out.println("Введіть коефіцієнти від старшого до молодшого:");
        for (int i = k1; i >= 0; i--) {
            p1.put(i, sc.nextInt());
        }

        System.out.println("Введіть степінь другого многочлена:");
        int k2 = sc.nextInt();
        HashMap<Integer, Integer> p2 = new HashMap<>();
        System.out.println("Введіть коефіцієнти від старшого до молодшого:");
        for (int i = k2; i >= 0; i--) {
            p2.put(i, sc.nextInt());
        }

        HashMap<Integer, Integer> sum = new HashMap<>(p1);

        for (var e : p2.entrySet()) {
            sum.put(e.getKey(),
                    sum.getOrDefault(e.getKey(), 0) + e.getValue());
        }

        System.out.println("Результат (степінь = коефіцієнт):");
        for (var e : sum.entrySet()) {
            System.out.println("x^" + e.getKey() + " : " + e.getValue());
        }
    }
}
