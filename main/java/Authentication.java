import java.io.*;
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
            run1run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

//        try {
//
////            Thread.currentThread().stop();
//        } catch (IOException | SQLException | ClassNotFoundException e) {
////            throw new RuntimeException(e);
//            e.printStackTrace();
//        }
    }
    public void run1run() throws IOException, SQLException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

        User userIn = new User();
        userIn = (User)ois.readObject();
        User user = new User();
        user = (User)userIn;
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/polki", "postgres", "postgres");
        String SQL = "select user_name, user_pass from users where user_name=? and user_pass=?;";
        PreparedStatement prepSt = con.prepareStatement(SQL);
        prepSt.setString(1, user.name);
        prepSt.setString(2, user.pass);
        ResultSet rs = prepSt.executeQuery();
        System.out.println(user.name + "     " + user.pass);

        if (rs.next()) {
            System.out.println("user    " + user.name + "      authorized");
            bw.write("Authorization succedeed");
            bw.flush();
            new Clientconn(sock, user.name, con);
        } else {
            System.out.println("Неправильный логин или пароль");
        }
    }
}
