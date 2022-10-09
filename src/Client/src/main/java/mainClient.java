package src.main.java;

import java.io.*;
import java.net.Socket;

public class mainClient {
    public static void main(String[] args) throws IOException {


        Socket socket = new Socket("127.0.0.1", 1299);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
//        String str = "Hello"
//        byte[] data = hexStringtoByteArray()
        out.writeUTF("Hello");
        out.flush();
//        System.out.println(in.readUTF());
//        out.writeUTF("asd");
    }
}
