import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.*;

public class Clientconn implements Runnable {
    Socket sock;
    String name;
    Connection con;

    public Clientconn(Socket sock, String name, Connection con) throws IOException {
//        BufferedWriter writer = new BufferedWriter(
//                new OutputStreamWriter(
//                        sock.getOutputStream()));
//        System.out.println("client conn    " + client);
//        writer.write("Connection accepted");
        this.sock = sock;
        this.name = name;
        this.con = con;
//        Thread thread = new Thread(this);
//        thread.start();
        (new Thread(this, name)).start();
    }

    @Override
    public void run() {
        System.out.println("clientconn new thread   " + name);
//        while (true) {
//            Socket socket = sock;

            try {
                ObjectInputStream objinp = new ObjectInputStream(sock.getInputStream());
                Tovar tovar = new Tovar();
                Tovar tov = (Tovar)objinp.readObject();
                tovar = (Tovar)tov;
                System.out.println(tovar.getName());
                System.out.println(tovar.getAmount());
                //            con.createStatement();
                //            ResultSet rs = con.createStatement().executeQuery("Select * from users");
                //            rs.next();
                //            String n = rs.getString("user_name");
                //            String p = rs.getString("user_pass");
                //            rs.next();
                //            String n1 = rs.getString("user_name");
                //            String p1 = rs.getString("user_pass");
                //            System.out.println(n + "   " + p);
                //            System.out.println(n1 + "   " + p1);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
//        }
    }
}
