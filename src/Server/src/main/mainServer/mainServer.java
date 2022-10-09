package src.main.mainServer;

import src.main.mainServer.NetServer;

import java.io.*;

public class mainServer {
    static final int PORT = 19925;
    public static void main(String[] args) throws IOException {
        NetServer serv = new NetServer(PORT, "Net");
        try {
            serv.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("мейн заверешен");
//        Db dbconn =
//        new Db();
        System.out.println(Thread.currentThread().getName().toString());
    }
}
