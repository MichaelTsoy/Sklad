package src.main.mainServer;
import src.main.HelpClasses.User;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class Authentication implements Runnable {
    Socket clientSocket;
    String name;
    Authentication(Socket clientSocket, String name) {
        this.clientSocket = clientSocket;
        this.name = name;
        new Thread(this).start();
    }
    @Override
    public void run() {
        try {
            //эти ресурсы будем передавать в основной поток работы клиента, и там их и закроем
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            runAuthentication(bufferedWriter, bufferedReader, objectInputStream, objectOutputStream, clientSocket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void runAuthentication(BufferedWriter bufferedWriter, BufferedReader bufferedReader, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream, Socket clientSocket) {
        String status = "";
        do {
            try {
                String authcommand = bufferedReader.readLine();
                if (authcommand == "login") {
                    status = loginChecker(objectInputStream);
                } else if (authcommand == "register") {

                } else if (authcommand == "exit") {
                    clientSocket.close();
                    break;
                }
            } catch (IOException | ClassNotFoundException e) {
//                throw new RuntimeException(e);
                e.printStackTrace();
            }
        } while (status != "succedeed");

    }
    String loginChecker(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        User user = (User)(objectInputStream.readObject());
        return "asd";
    }
}
