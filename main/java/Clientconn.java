import java.io.*;
import java.net.Socket;
import java.sql.*;

public class Clientconn implements Runnable {
    Socket sock;
    String name;
    Connection con;

    public Clientconn(Socket sock, String name, Connection con) {
        this.sock = sock;
        this.name = name;
        this.con = con;
        (new Thread(this, name)).start();
    }

    @Override
    public void run() {
        try {
            rurun();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void rurun() throws IOException {
        System.out.println("clientconn new thread   " + name);
        ObjectOutputStream objout = new ObjectOutputStream(sock.getOutputStream());
        BufferedReader bfr = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
        try {
            System.out.println("ПОТОК КЛИЕНТА");
//            Tovar asd = new Tovar();
//            asd.setName("товар");
//            objout.writeObject(asd);
//            objout.flush();
            bfw.newLine();
            bfw.write("Authorization succeeded");
            bfw.flush();
            System.out.println("print");
        } catch (Exception e) {
            e.printStackTrace();


        }
    }
}
