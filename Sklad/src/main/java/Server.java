import javax.swing.plaf.TableHeaderUI;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    int port;
    public Server(int port) throws IOException {
        this.port = port;
        Thread t = new Thread(this, "netServer");
        System.out.println("создан поток сетевого сервера");
        t.start();
    }
    public void Client (Socket sock) {

    }
    @Override
    public void run() {

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("server socket created");
            while (true) {
                try {
                        Clientconn clientconn = new Clientconn(serverSocket.accept());
                    } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}