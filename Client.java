//client.java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть слово для пошуку (або 'exit' для виходу):");
        while (true) {
            String word = scanner.nextLine();
            if (word.equalsIgnoreCase("exit")) break;
            out.println(word);
            String response = in.readLine();
            System.out.println("Слово зустрічається в рядках: " + response);
        }

        socket.close();
    }
}

