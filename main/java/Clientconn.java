import java.io.*;
import java.net.Socket;
import java.sql.*;

public class Clientconn implements Runnable {
//    Socket sock;
    String name;
    Connection con;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    BufferedReader br;
    BufferedWriter bw;
    public Clientconn(ObjectInputStream ois, ObjectOutputStream oos, BufferedReader br, BufferedWriter bw, String name, Connection con) {
        this.ois = ois;
        this.oos = oos;
        this.br = br;
        this.bw = bw;
        this.name = name;
        this.con = con;
        (new Thread(this, name)).start();
    }

    @Override
    public void run() {
        try {
            runcli();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void runcli() throws IOException {
        System.out.println("-----------" + Thread.currentThread().getName().toString());
        System.out.println("clientconn new thread   " + name);
//        ObjectOutputStream objout = new ObjectOutputStream(sock.getOutputStream());
//        ObjectInputStream objin = new ObjectInputStream(sock.getInputStream());
//        BufferedReader bfr = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        System.out.println("------------------qwe");
//        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(), "cp1251"));
        try {
            System.out.println("-------------------qwe");
            succeesAuth(bw);
            actionListener(br, ois, oos, con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.currentThread().stop();
    }
    public void succeesAuth(BufferedWriter bw) throws IOException{
        System.out.println("ПОТОК КЛИЕНТА");
//            Tovar tov = new Tovar();
//            tov.setName("товар");
//            objout.writeObject(tov);
//            objout.flush();
        String str = "Authorization succeeded";
        bw.newLine();
        bw.write(str, 0, str.length() );
        bw.flush();
    }
    public void actionListener(BufferedReader br, ObjectInputStream ois, ObjectOutputStream oos, Connection con) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
        System.out.println("------------------listener");
        String query;
        do {
            query = "";
            try {
                query = br.readLine();
                System.out.println(query);
                if (query.equals("create")) {
                    System.out.println("команда принята");
                    FunctionalActivities.create(ois, con);
//            } else if (query == "update") {
//                FunctionalActivities.update();
//            } else if (query == "delete") {
//                FunctionalActivities.delete();
//            } else if (query == "search") {
//                FunctionalActivities.search();
//            }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(2000);
        } while (query != "exit");

//        bfr.close();
        System.out.println("выход из цикла");
    }
}
