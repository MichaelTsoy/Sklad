import javax.swing.plaf.TableHeaderUI;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    int port;
    Socket sock;
    Server(int port) {
        this.port = port;
        System.out.println("создан поток сетевого сервера");
        (new Thread(this, "netServer")).start();
    }
    @Override
    public void run() {
        try {
            runrun(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void runrun(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket sock = serverSocket.accept();
            if (sock != null){
                new Authentication(sock);
            }
        }
    }

}