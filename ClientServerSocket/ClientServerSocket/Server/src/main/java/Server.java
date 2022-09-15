import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        while (true)
            try (ServerSocket server = new ServerSocket(8000)) {
                System.out.println("working");
                try (Socket socket = server.accept();
                     BufferedWriter writer = new BufferedWriter(
                             new OutputStreamWriter(
                                     socket.getOutputStream()));
                     BufferedReader reader =
                             new BufferedReader(
                                     new InputStreamReader(
                                             socket.getInputStream()));
                                     ObjectInputStream qwe = new ObjectInputStream(socket.getInputStream())
                ) {
                    System.out.println("started");
//                    String request = reader.readLine();
//                    System.out.println(request);
//                    streamWriter.write("hello" + request);

                    Object y = (Obj)qwe.readObject();
                    Obj g = new Obj();
                    g = (Obj)y;
                    System.out.println(g.name);
                    System.out.println(g.age);
                    System.out.println(g.wow);



//                    while (request != null) {
//                        System.out.println(request);
//                        streamWriter.write("hello              " + request);
//                        streamWriter.newLine();
//                        streamWriter.flush();
                    String request = reader.readLine();
                    System.out.println(request);
//                        JsonElement json = JsonParser.parseString(request);
//                        JsonObject file = json.getAsJsonObject();
//                        String gorod = file.get("gorod").getAsString();
//                        String name = file.get("name").getAsString();
//                        System.out.println(gorod + "    " name);
//                    }

//                    streamWriter.newLine();
                    writer.flush();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
//        ServerSocket server = new ServerSocket(8000);
//        System.out.println("working");
//        Socket socket = server.accept();
//        System.out.println("started");

//        OutputStream stream = socket.getOutputStream();
//        BufferedOutputStream writer = new BufferedOutputStream(stream);
//        OutputStreamWriter streamWriter = new OutputStreamWriter(writer);
//        streamWriter.write("hello");
//        streamWriter.flush();
//        streamWriter.close();


//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//        writer.write("asd");
//        writer.flush();
//        writer.close();
//        socket.close();
//        server.close();
}
