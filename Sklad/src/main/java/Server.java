import javax.swing.plaf.TableHeaderUI;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    int port;
    Server(int port) {
        this.port = port;
//        Thread t = new Thread(this, "netServer");
        System.out.println("создан поток сетевого сервера");
        (new Thread(this, "netServer")).start();
    }
    @Override
    public void run() {
//        ServerSocket serverSocket = null;
        try {
            ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            try {
//            Class.forName("org.postgresql.Driver");

//            Socket asd = serverSocket.accept();
                new Authentication(serverSocket.accept());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}