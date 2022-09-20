import javax.swing.plaf.TableHeaderUI;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    int port;
    Socket sock;
    String name;
    Server(int port, String name) {
        this.name = name;
        this.port = port;
        System.out.println("создан поток сетевого сервера");
        (new Thread(this, name)).start();
    }
    @Override
    public void run() {
        try {
            runserv(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void runserv(int port) throws IOException {
//        System.out.println(Thread.currentThread().getName().toString());
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket sock = serverSocket.accept();
            if (sock != null){
                new Authentication(sock, "Auth" + Thread.currentThread().getName().toString());
            }
        }
    }

}