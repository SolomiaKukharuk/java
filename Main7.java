import java.io.*;
import java.util.*;

public class Main7 {

    //B07.01
    public static void b07_01(String fileF, String fileG, double a) throws IOException {

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileF))) {
            double[] nums = {1.2, 5.5, -3.4, 8.1, 2.7, 9.9, 0.0};
            for (double x : nums) dos.writeDouble(x);
        }

        List<Double> list = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileF))) {
            while (dis.available() > 0) list.add(dis.readDouble());
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileG))) {
            for (double x : list)
                if (x > a) dos.writeDouble(x);
        }

        System.out.println("Файл G (x > " + a + "):");
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileG))) {
            while (dis.available() > 0)
                System.out.print(dis.readDouble() + " ");
        }
        System.out.println("\n--- Завдання B07.01 виконано ---");
    }

    // B07.02
    public static void b07_02(String fileToys, String fileFiltered, int childAge) throws IOException {

        List<Toy> toys = Arrays.asList(
                new Toy("М'яч", 120.5, 3, 10),
                new Toy("Лялька", 200.0, 5, 12),
                new Toy("Конструктор", 350.0, 6, 14),
                new Toy("Пірамідка", 90.0, 1, 4)
        );

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileToys))) {
            for (Toy t : toys) oos.writeObject(t);
        }

        List<Toy> suitable = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileToys))) {
            while (true) {
                try {
                    Toy t = (Toy) ois.readObject();
                    if (t.isSuitableForAge(childAge)) suitable.add(t);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileFiltered))) {
            for (Toy t : suitable) oos.writeObject(t);
        }

        System.out.println("Іграшки для віку " + childAge + ":");
        for (Toy t : suitable) System.out.println("  " + t);
        System.out.println("--- Завдання B07.02 виконано ---");
    }

    // ===== Меню =====
    public static void main(String[] args) {
        //b07_02();
        //b07_01();
        }
    }