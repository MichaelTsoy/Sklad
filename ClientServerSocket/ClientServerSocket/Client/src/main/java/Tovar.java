import java.io.Serializable;

public class Tovar implements Serializable {
    String Name;
    int Amount;
    int Shkaf_id;
    int Polka_id;
    int Box_id;
    int Cep_id;

    public Tovar() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getShkaf_id() {
        return Shkaf_id;
    }

    public void setShkaf_id(int shkaf_id) {
        Shkaf_id = shkaf_id;
    }

    public int getPolka_id() {
        return Polka_id;
    }

    public void setPolka_id(int polka_id) {
        Polka_id = polka_id;
    }

    public int getBox_id() {
        return Box_id;
    }

    public void setBox_id(int box_id) {
        Box_id = box_id;
    }

    public int getCep_id() {
        return Cep_id;
    }

    public void setCep_id(int cep_id) {
        Cep_id = cep_id;
    }
}