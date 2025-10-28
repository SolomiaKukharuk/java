import java.util.Scanner;

public class Main6 {
    public static void main(String[] args) {
        b06_01();
        b06_02();
        b06_03();
    }

    public static void b06_01() {
        Scanner sc = new Scanner(System.in);
        String text1 = sc.nextLine();
        String result1 = TaskUtils.replaceDates(text1);
        System.out.println(result1);
    }
    public static void b06_02() {
        Scanner sc = new Scanner(System.in);
        String text2 = sc.nextLine();
        String result2 = TaskUtils.findPhones(text2);
        System.out.println(result2);
    }
    public static void b06_03() {
        Scanner sc = new Scanner(System.in);
        String expr = sc.nextLine();
        System.out.println(TaskUtils.checkExpression(expr) ? "Синтаксично правильний" : "Синтаксично неправильний");
    }
}
