import java.io.Serializable;

public class Obj implements Serializable {
    String name;
    int age;
    boolean wow;
    public void Obj(String name, int age, boolean wow) {
        this.name = name;
        this.age = age;
        this.wow = wow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isWow() {
        return wow;
    }

    public void setWow(boolean wow) {
        this.wow = wow;
    }
}
