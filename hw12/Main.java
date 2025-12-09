import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть номер файлу (1-14): ");
        int fileNumber = scanner.nextInt();


        if (fileNumber < 1 || fileNumber > 14) {
            System.out.println("Неправильний номер файлу (має бути від 1 до 14).");
            return;
        }

        String fileName = String.format("input%02d.txt", fileNumber);
        System.out.println("Читаємо файл: " + fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            // 1. Тип студента
            String typeLine = br.readLine().trim();
            StudentType type = switch (typeLine) {
                case "humanitarian" -> StudentType.HUMANITARIAN;
                case "natural" -> StudentType.NATURAL;
                default -> StudentType.MIXED;
            };

            // 2. Кредити, які треба для диплому
            int requiredCredits = Integer.parseInt(br.readLine().trim());

            // 3. Початкові гроші
            int money = Integer.parseInt(br.readLine().trim());

            Student student = new Student(type, requiredCredits, money);

            // 4. Дії з файлу
            String line;
            while ((line = br.readLine()) != null) {
                Action a = ActionParser.parse(line);
                a.apply(student);

                if (student.isExpelled()) {
                    System.out.println("Не отримає диплом (відрахований)");
                    return;
                }
            }

            if (student.hasDiploma()) {
                System.out.println("Отримає диплом");
            } else {
                System.out.println("Не отримає диплом");
            }
        }
    }
}
