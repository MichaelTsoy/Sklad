package src.main.HelpClasses;
import java.io.Serializable;
public class User implements Serializable {
    String name;
    String pass;
    public void User() {
    }
    public void User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}