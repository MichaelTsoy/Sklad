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

             ObjectOutputStream wr = new ObjectOutputStream(socket.getOutputStream()))//;
//             ObjectInputStream in = new ObjectInputStream(socket.getInputStream()))
        {
            System.out.println("Connected to server");
            User tov = new User();
            tov.name = "Albert";
            tov.pass = "123";
            wr.writeObject(tov);
            wr.flush();
            Tovar asd = new Tovar();
            asd.setName("tovar1");
            asd.setAmount(2);
            wr.writeObject(asd);
            wr.flush();
            wr.close();
//            in.readObject()

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
