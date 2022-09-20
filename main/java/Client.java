//import com.google.gson.*;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.Map;
//import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 1925);
             ObjectOutputStream wr = new ObjectOutputStream(socket.getOutputStream());
//             ObjectInputStream objin = new ObjectInputStream(socket.getInputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "cp1251"));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())))//;
//             ObjectInputStream in = new ObjectInputStream(socket.getInputStream()))
        {
            Scanner scan = new Scanner(System.in);
            String name = scan.nextLine();
            String pass = scan.nextLine();
            auth(wr, br, name, pass);
            System.out.println("Connected to server");
//            String str;
            System.out.println("----------------client print end");
            commandLine(bw, wr);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
    private static void auth(ObjectOutputStream wr, BufferedReader br, String name, String pass) throws IOException, InterruptedException{
        System.out.println("------------------AUTH ");
        User usr = new User();
        usr.name = name;
        usr.pass = pass;
        wr.writeObject(usr);
        wr.flush();
//        Thread.sleep(1000);
        try {
            String str = br.readLine();
            System.out.println("------------client print2");
            if (str != null) {
                System.out.println("--------------client print3");
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void commandLine(BufferedWriter bw, ObjectOutputStream oos) throws IOException, InterruptedException{
        System.out.println("Введите команду");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        scan.close();
        bw.write(str);
        bw.newLine();
        bw.flush();
//        bw.close();
        Thread.sleep(1000);
        System.out.println("команда отправлена");
        User user = new User();
        user.name = "Michael";
        user.pass = "Tsoy";
        oos.writeObject(user);
        oos.flush();
        oos.close();
        System.out.println("обьект отправлен");

    }
}

