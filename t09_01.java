import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class t09_01{

    // Спеціальний маркер завершення
    private static final String POISON = "__EOF__";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введіть ім'я вхідного файлу F:");
        String inputFile = sc.nextLine();

        System.out.println("Введіть ім'я вихідного файлу для потоку 2:");
        String outFile1 = sc.nextLine();

        System.out.println("Введіть ім'я вихідного файлу для потоку 3:");
        String outFile2 = sc.nextLine();

        System.out.println("Введіть T1 (мс) — інтервал читання рядка з файлу:");
        long T1 = sc.nextLong();

        System.out.println("Введіть T2 (мс) — час обробки рядка потоком 2:");
        long T2 = sc.nextLong();

        System.out.println("Введіть T3 (мс) — час обробки рядка потоком 3:");
        long T3 = sc.nextLong();

        // Спільна черга для всіх потоків
        LineQueue queue = new LineQueue(10); // буфер з максимально 10 рядками

        Thread producer = new Producer(queue, inputFile, T1);
        Thread consumer1 = new Consumer(queue, outFile1, T2);
        Thread consumer2 = new Consumer(queue, outFile2, T3);

        producer.start();
        consumer1.start();
        consumer2.start();

        try {
            producer.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Готово.");
    }

    // ----------------- Потокобезпечна черга -----------------
    static class LineQueue {
        private final Queue<String> buffer = new LinkedList<>();
        private final int capacity;

        public LineQueue(int capacity) {
            this.capacity = capacity;
        }

        public synchronized void put(String line) throws InterruptedException {
            while (buffer.size() == capacity) {
                wait();
            }
            buffer.add(line);
            notifyAll();
        }

        public synchronized String take() throws InterruptedException {
            while (buffer.isEmpty()) {
                wait();
            }
            String res = buffer.remove();
            notifyAll();
            return res;
        }
    }

    // ----------------- Потік-читач файлу -----------------
    static class Producer extends Thread {
        private final LineQueue queue;
        private final String filename;
        private final long delay;

        public Producer(LineQueue queue, String filename, long delay) {
            this.queue = queue;
            this.filename = filename;
            this.delay = delay;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Thread.sleep(delay);      // T1
                    queue.put(line);          // покласти рядок у чергу
                }
                // надсилаємо по одному "отруйному" елементу для кожного споживача
                queue.put(POISON);
                queue.put(POISON);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // ----------------- Потік-споживач -----------------
    static class Consumer extends Thread {
        private final LineQueue queue;
        private final String outFile;
        private final long delay;

        public Consumer(LineQueue queue, String outFile, long delay) {
            this.queue = queue;
            this.outFile = outFile;
            this.delay = delay;
        }

        @Override
        public void run() {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {
                while (true) {
                    String line = queue.take();   // взяти рядок з черги

                    if (POISON.equals(line)) {
                        // повертаємо POISON назад у чергу для іншого споживача (якщо потрібно)
                        queue.put(POISON);
                        break;
                    }

                    Thread.sleep(delay);          // T2 або T3
                    bw.write(line);
                    bw.newLine();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
