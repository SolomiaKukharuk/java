import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //b01_01();
        //b01_02(args);
        //b01_03(args);
        //b01_04();
        b01_05(args);
    }
    public static void b01_01() {
        System.out.print("Введіть ім'я: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine().trim();
        System.out.println("Привіт, " + name + "!");

    }
    public static void b01_02(String[] args){

        System.out.println("Аргументи у зворотному порядку:");
        for (int i = args.length - 1; i >= 0; i--) {
            System.out.println(args[i]);
        }

    }
    public static void b01_03(String[] args){
        long product = 1;
        for (String arg : args) {
            try {
                int num = Integer.parseInt(arg);
                product *= num;
            } catch (NumberFormatException e) {
                System.out.println("Аргумент \"" + arg + "\" не є цілим числом.");
                return;
            }
        }

        System.out.println("Добуток = " + product);
    }

    public static void b01_04(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть три цілі числа:");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        double geometricMean = Math.cbrt((double) a * b * c);
        System.out.printf("Середнє геометричне = %.4f\n", geometricMean);

    }
    public static void b01_05(String[] args){
        int N, M;

        if (args.length >= 2) {
            try {
                N = Integer.parseInt(args[0]);
                M = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Аргументи мають бути цілими числами.");
                return;
            }
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Введіть число N (кількість): ");
            N = sc.nextInt();
            System.out.print("Введіть число M (максимум): ");
            M = sc.nextInt();
        }

        if (N <= 0 || M <= 0) {
            System.out.println("Числа N та M мають бути додатні.");
            return;
        }

        System.out.println("Випадкові числа:");
        for (int i = 0; i < N; i++) {
            int rand = (int) (Math.random() * (M + 1));  // [0; M] включно
            System.out.println(rand);
        }
    }
}