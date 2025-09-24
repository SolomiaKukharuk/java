import java.util.*;
public class Main {
    public static void main(String[] args) {
        //b02_07();
        //b02_15();
        //b02_17g();
        }
    public static void b02_07(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть кількість елементів: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        int sum = 0;
        System.out.println("Введіть елементи масиву:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        double avg = (double) sum / n;
        System.out.println("Дисперсія (відхилення):");
        for (int i = 0; i < n; i++) {
            int deviation = (int) Math.abs(arr[i] - avg);
            System.out.print(deviation + " ");
        }
    }
    public static void b02_15(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть число: ");
        short n = sc.nextShort();
        int zeroBits = 0;
        for (int i = 0; i < 16; i++) {
            int bit = (n >> i) & 1;
            if (bit == 0) {
                zeroBits++;
            }
        }

        System.out.println("Кількість нульових бітів: " + zeroBits);
    }
    public static double sinhSeries(double x, double eps) {
        double term = x;
        double sum = 0;
        int k = 0;

        while (Math.abs(term) > eps) {
            sum += term;
            k++;
            term = term * x * x / ((2 * k) * (2 * k + 1));
        }
        return sum;
    }

    public static void b02_17g() {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);
        System.out.print("Введіть число: ");
        double x = sc.nextDouble();
        double eps = 1e-5;

        double result = sinhSeries(x, eps);
        System.out.println("sinh(" + x + ") ≈ " + result);
        System.out.println("Math.sinh(" + x + ") = " + Math.sinh(x));
    }
}
