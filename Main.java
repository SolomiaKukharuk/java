import java.util.*;
public class Main {
    public static void main(String[] args) {
            //b03_07();
            b03_15();
    }
    private static void b03_07() {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);
        System.out.print("Введіть кількість кіл: ");
        int n = sc.nextInt();

        Circle[] circles = new Circle[n];
        for (int i = 0; i < n; i++) {
            System.out.printf("Коло %d: введіть x, y, r -> ", i+1);
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            double r = sc.nextDouble();
            circles[i] = new Circle(x, y, r);
        }

        List<int[]> pairs = Circle.findIntersectingPairs(circles);
        System.out.println("\nПари кіл, що перетинаються (за індексами):");
        for (int[] p : pairs) {
            System.out.println(Arrays.toString(p));
        }

        Circle max = Circle.maxAreaCircle(circles);
        System.out.printf("\nНайбільше коло: центр(%.2f, %.2f), r=%.2f, площа=%.4f\n",
                max.getX(), max.getY(), max.getR(), max.area());
    }
    private static void b03_15() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Скільки автомобілів ви введете? ");
        int n = Integer.parseInt(sc.nextLine().trim());

        Car[] cars = new Car[n];
        for (int i = 0; i < n; i++) {
            System.out.println("\nАвто #" + (i + 1));
            System.out.print("Марка: ");
            String brand = sc.nextLine().trim();

            System.out.print("Модель: ");
            String model = sc.nextLine().trim();

            System.out.print("Рік випуску (наприклад 2015): ");
            int year = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Колір: ");
            String color = sc.nextLine().trim();

            cars[i] = new Car(brand, model, year, color);
        }


        System.out.print("\nВведіть марку для фільтра (п. a): ");
        String brandFilter = sc.nextLine().trim();
        Car[] byBrand = Car.filterByBrand(cars, brandFilter);

        System.out.println("\n=== Автомобілі марки \"" + brandFilter + "\" (відсортовано за роком) ===");
        printArray(byBrand);


        System.out.print("\nВведіть модель для фільтра (п. b): ");
        String modelFilter = sc.nextLine().trim();

        System.out.print("Більше скількох років? n = ");
        int nYears = Integer.parseInt(sc.nextLine().trim());

        Car[] byModelAge = Car.filterByModelAndAge(cars, modelFilter, nYears);

        System.out.println("\n=== Авто моделі \"" + modelFilter + "\", що старші за " + nYears + " років ===");
        printArray(byModelAge);
    }

    private static void printArray(Car[] arr) {
        if (arr.length == 0) {
            System.out.println("(нічого не знайдено)");
        } else {
            for (Car c : arr) System.out.println(c);
        }
    }


}
