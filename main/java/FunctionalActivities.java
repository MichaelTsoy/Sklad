import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FunctionalActivities {
//    Socket sock;
//    Connection con;
//    User input;
    public void FunctionalActivities() {
//        Socket sock, Connection con
//        this.sock = sock;
//        this.con = con;
    }
    public static void create(ObjectInputStream objin, Connection con) throws IOException, ClassNotFoundException, SQLException {
        User tov = new User();
        String sql = "insert into users(user_name, user_pass) values (?, ?);";
        tov = (User)objin.readObject();
        System.out.println("обьект прочитан");
        User userin = new User();
        userin = (User)tov;
        System.out.println(userin.name + userin.pass);
        PreparedStatement prepst = con.prepareStatement(sql);
        prepst.setString(1, userin.name);
        prepst.setString(2, userin.pass);
        ResultSet rs = prepst.executeQuery();
        System.out.println("создан обьект");
//        tov.name = name;
//        tov.pass = pass;
//        wr.writeObject(usr);
//        wr.flush();
    }
//    public static void update(Socket sock, Connection con) {
//
//    }
//    public static void delete(Socket sock, Connection con) {
//
//    }
//    public static void search(Socket sock, Connection con) {
//
//    }
}
