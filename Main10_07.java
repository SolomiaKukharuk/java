// Server.java
import java.io.*;
import java.net.*;
import java.util.*;

public class Main10_07 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Сервер запущено...");

        // Зчитуємо файл у пам’ять
        List<String> lines = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader("F.txt"))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                lines.add(line);
            }
        }

        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String word;
            while ((word = in.readLine()) != null) {
                List<Integer> resultLines = new ArrayList<>();
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).contains(word)) {
                        resultLines.add(i + 1); // Нумерація з 1
                    }
                }
                out.println(resultLines.toString());
            }

            socket.close();
        }
    }
}
