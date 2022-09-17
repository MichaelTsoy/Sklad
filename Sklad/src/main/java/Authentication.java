import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;

public class Authentication implements Runnable {
    Socket sock;
    Authentication(Socket sock) {
        this.sock = sock;
        new Thread(this).start();
    }
    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
            User userIn = (User)ois.readObject();
            User user = new User();
            user = (User)userIn;

            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/polki", "postgres", "postgres");
            String SQL = "select user_name, user_pass from users where user_name=? and user_pass=?;";
            PreparedStatement prepSt = con.prepareStatement(SQL);
            prepSt.setString(1, user.name);
            prepSt.setString(2, user.pass);
            ResultSet rs = prepSt.executeQuery();
//            String un = null;
//            String up = null;
//            while (rs.next()) {
//                un = rs.getString("user_name");
//                up = rs.getString("user_pass");
//            }
//            System.out.println(un +"     " + up);

            System.out.println(user.name + "     " + user.pass);
//            String un = rs.getString("user_pass");
//            String up = rs.getString("user_name");
            if (rs.next()) {
                System.out.println("user    " + user.name + "      authorized");
                new Clientconn(sock, user.name, con);
//                Tovar asd = new Tovar();
//                asd.Name = "seme4ki";
//                oos.writeObject(asd);
            } else {
                System.out.println("Неправильный логин или пароль");
            }
//            Thread.currentThread().stop();
        } catch (IOException | SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
    }
}
