import java.io.Serializable;
public class User implements Serializable {
    String name;
    String pass;
    public void User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
}