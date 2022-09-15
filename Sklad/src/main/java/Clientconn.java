import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Clientconn implements Runnable{
    Socket sock;
    public Clientconn(Socket sock) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        sock.getOutputStream()));
//        System.out.println("client conn    " + client);
//        writer.write("Connection accepted");
        this.sock = sock;
        Thread thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        System.out.println("clientconn new thread");
        try {
//            Socket sock = serverSocket.accept());

            ObjectInputStream objinp = new ObjectInputStream(sock.getInputStream());
//            Statement asd = Db.con.createStatement();

            Object y = (Tovar) objinp.readObject();
            Tovar tov = new Tovar();
//            System.out.println("middle");
            tov = (Tovar) y;
            //                tov.getName();
            System.out.println(tov.getName());
            Thread.sleep(3000);
//            this.
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        }
    }
//    public static void
}
