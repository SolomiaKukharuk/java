import java.util.*;
//b04_07
public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введіть тип потягу: ");
        String trainType = sc.nextLine();
        PassengerTrain train = new PassengerTrain(trainType);

        System.out.print("Скільки вагонів у потязі? ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("\nВагон #" + (i + 1));
            System.out.print("Назва вагона: ");
            String name = sc.nextLine();

            System.out.print("Кількість пасажирів: ");
            int passengers = Integer.parseInt(sc.nextLine());

            System.out.print("Місткість багажу (кг): ");
            int baggage = Integer.parseInt(sc.nextLine());

            System.out.print("Рівень комфортності (1–10): ");
            int comfort = Integer.parseInt(sc.nextLine());

            train.addCarriage(new Carriage(name, passengers, baggage, comfort));
        }

        train.showTrainInfo();

        train.sortByComfort();
        System.out.println("\nВідсортовано за комфортністю (спадання)");
        train.showTrainInfo();

        System.out.print("\nВведіть мінімальну кількість пасажирів: ");
        int min = Integer.parseInt(sc.nextLine());
        System.out.print("Введіть максимальну кількість пасажирів: ");
        int max = Integer.parseInt(sc.nextLine());

        List<Carriage> found = train.findByPassengerRange(min, max);
        System.out.println("\nВагони, що відповідають діапазону [" + min + "; " + max + "]:");
        if (found.isEmpty()) {
            System.out.println("Немає вагонів у цьому діапазоні.");
        } else {
            for (Carriage c : found) System.out.println(c);
        }
    }
}