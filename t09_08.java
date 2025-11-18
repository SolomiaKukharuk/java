import java.util.*;
import java.util.concurrent.*;

public class t09_08{

    // глобальні параметри
    static int N;     // кухарі
    static int T1, T2; // час приходу клієнтів
    static int T3, T4; // час приготування
    static int T5;     // поріг очікування

    static Semaphore cooks; // семафор кухарів
    static Random rnd = new Random();

    static int waitedTooLong = 0; // рахуємо клієнтів, що чекали довше T5
    static int totalClients = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("N = кількість кухарів:");
        N = sc.nextInt();
        cooks = new Semaphore(N);

        System.out.println("T1, T2 (інтервал приходу):");
        T1 = sc.nextInt();
        T2 = sc.nextInt();

        System.out.println("T3, T4 (інтервал готування):");
        T3 = sc.nextInt();
        T4 = sc.nextInt();

        System.out.println("T5 (поріг очікування):");
        T5 = sc.nextInt();

        System.out.println("Скільки клієнтів змоделювати?");
        totalClients = sc.nextInt();

        List<Thread> clients = new ArrayList<>();

        for (int i = 0; i < totalClients; i++) {
            // Чекаємо прихід наступного клієнта
            int arrivalDelay = random(T1, T2);

            try {
                Thread.sleep(arrivalDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread client = new Client(i + 1);
            client.start();
            clients.add(client);
        }

        // Чекаємо завершення всіх потоків
        for (Thread c : clients) {
            try {
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Результат
        System.out.println("\n===== РЕЗУЛЬТАТ =====");
        System.out.println("Всього клієнтів: " + totalClients);
        System.out.println("Чекали більше ніж T5 = " + waitedTooLong);
    }

    private static int random(int a, int b) {
        return a + rnd.nextInt(b - a + 1);
    }

    // ---------------- КЛАС КЛІЄНТА --------------------

    static class Client extends Thread {
        int id;

        Client(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            long tStart = System.currentTimeMillis();

            System.out.println("Клієнт " + id + " прийшов.");

            try {
                // клієнт намагається зайняти кухаря
                cooks.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long tEnd = System.currentTimeMillis();
            long waited = tEnd - tStart;

            if (waited > T5) {
                synchronized (t09_08.class) {
                    waitedTooLong++;
                }
            }

            System.out.println("Клієнт " + id + " отримав кухаря. Чекав: " + waited + " мс.");

            // час готування
            try {
                int cookTime = random(T3, T4);
                Thread.sleep(cookTime);
                System.out.println("Клієнт " + id + " страва готова за " + cookTime + " мс.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cooks.release(); // кухар звільнений
        }
    }
}

