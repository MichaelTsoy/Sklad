//import com.google.gson.*;

import java.io.*;
import java.net.Socket;
//import java.util.ArrayList;
//import java.util.Map;
//import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 1925);

//             BufferedWriter streamWriter = new BufferedWriter(
//                     new OutputStreamWriter(
//                             socket.getOutputStream()));
//             BufferedReader reader =
//                     new BufferedReader(
//                             new InputStreamReader(
//                                     socket.getInputStream()));

             ObjectOutputStream wr = new ObjectOutputStream(socket.getOutputStream()))
        {
            System.out.println("Connected to server");
            Tovar tov = new Tovar();
            tov.Name = "Drel";
            wr.writeObject(tov);
            wr.flush();
//            wr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
