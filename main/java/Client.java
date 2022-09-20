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

             ObjectOutputStream wr = new ObjectOutputStream(socket.getOutputStream());
//             ObjectInputStream objin = new ObjectInputStream(socket.getInputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())))//;
//             ObjectInputStream in = new ObjectInputStream(socket.getInputStream()))
        {
            auth(wr, br);
            System.out.println("Connected to server");
//            String str;
            System.out.println("client print1");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    private static void auth(ObjectOutputStream wr, BufferedReader br) throws IOException, InterruptedException{
        User usr = new User();
        usr.name = "Robert";
        usr.pass = "456";
        wr.writeObject(usr);
        wr.flush();
        Thread.sleep(1000);
        try {
            String str = br.readLine();
            System.out.println("client print2");
            if (str != null) {
                System.out.println("client print3");
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

