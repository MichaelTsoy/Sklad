import java.net.ServerSocket;

public class Main {
    static final int PORT = 1925;
    public static void main(String[] args) throws Exception {
        new Server(PORT, "Net");
        System.out.println("мейн заверешен");
//        Db dbconn =
//        new Db();
        System.out.println(Thread.currentThread().getName().toString());
    }
}
