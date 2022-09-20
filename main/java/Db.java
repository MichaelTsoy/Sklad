//import java.sql.*;
//
//public class Db implements Runnable {
//    public static Connection con;
//    Db() {
//        System.out.println("con");
////        Thread t = new Thread(this ,"Db_conn");
//        (new Thread(this ,"Db_conn")).start();
//
//    }
////    public static Tovar Create() {
////
////    }
////
////    public static Tovar Update() {
////
////    }
////
////    public static Tovar Delete() {
////
////    }
////
////    public static Tovar Query() {
////
//
//    @Override
//    public void run() {
//        System.out.println("DB");
//        System.out.println("run");
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/polki", "postgres", "postgres");
//             Statement stmt = con.createStatement();
//            ResultSet res = stmt.executeQuery("select object.object_name, object.object_amount, cep.cep_id, shkaf.shkaf_id from object left join cep on object.cep_id = cep.cep_id left join shkaf on cep.shkaf_id = shkaf.shkaf_id where shkaf.shkaf_id = 1 and cep.cep_id = 4;")) {
//
//            while (true) {
//                try {
//                    if (!res.next()) break;
//                    String name = res.getString("object_name");
//                    int amount = res.getInt("object_amount");
//                    int cep = res.getInt("cep_id");
//                    int shkaf = res.getInt("shkaf_id");
//                    //            System.out.println(res.getInt(1) + ": " + res.getString(2) + " : ");
//                    System.out.println(name + "      Количество :  " + amount + "  Цепь : " + cep + "    Шкаф   " + shkaf);
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
//
//
