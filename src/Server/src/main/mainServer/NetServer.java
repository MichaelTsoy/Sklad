package src.main.mainServer;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetServer implements Runnable {
    Thread t;
    int port;
    Socket socket;
    String name;
    NetServer(int port, String name) {
        this.name = name;
        this.port = port;
        this.t = new Thread(this, name);
        System.out.println("создан поток сетевого сервера");
        t.start();
    }
    @Override
    public void run() {
        try {
            System.out.println("run");
            netListener(port);
        } catch (IOException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
    }
    public void netListener(int port) throws IOException {
        System.out.println("netlistener");
//        System.out.println(Thread.currentThread().getName().toString());
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            if (clientSocket != null){
                new Clientconnection(clientSocket, "Auth" + Thread.currentThread().getName());
                System.out.println("client started");
            }
        }
    }
}
