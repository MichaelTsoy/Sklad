package src.main.mainServer;

import src.main.HelpClasses.User;

import java.io.*;
import java.net.Socket;
import java.sql.*;

public class Clientconnection implements Runnable {
    Socket clientSocket;
    String name;
    Clientconnection(Socket clientSocket, String name) {
        this.clientSocket = clientSocket;
        this.name = name;
        (new Thread(this, name)).start();
    }
    @Override
    public void run() {
        System.out.println("Clientconn");
        try (
            //эти ресурсы будем передавать в основной поток работы клиента, и там их и закроем
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream())
            ) {
            String status = "";
            status = authenticationListener(bufferedWriter, bufferedReader, objectInputStream, objectOutputStream);
            while (true) {
                if (status.equals("успешно")) {
                    status = "";
//                workingClientProcess();
                } else if (status.equals("выйти") | !clientSocket.isConnected()) {
                    System.out.println("закрытие клиентского соединения");
                    break;
                }
            }
        } catch (IOException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                Thread.currentThread().interrupt();
            } catch (IOException e) {
//                throw new RuntimeException(e);
                e.printStackTrace();
            }
        }

    }
    public String authenticationListener(BufferedWriter bufferedWriter, BufferedReader bufferedReader, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        System.out.println("AuthenticationListener");
        String status = "";
        do {
            try {
                String authcommand = bufferedReader.readLine();
                if (authcommand.equals("войти")) {
                    status = loginChecker(objectInputStream, bufferedWriter, bufferedReader);
                } else if (authcommand.equals("зарегистрироваться")) {
//                  register();
                } else if (status.equals("Неверный логин или пароль")) {
                    authcommand = "";
                    continue;
                } else if (status.equals("назад")) {
                    authcommand = "";
                    continue;
                } else if (authcommand.equals("выйти") | !clientSocket.isConnected()) {
//                    clientSocket.close();
                    status = "выйти";
                    break;
                } else {
                    bufferedWriter.write("Некорректная команда");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            } catch (IOException e) {
//                throw new RuntimeException(e);
                e.printStackTrace();
            }
        } while (!status.equals("успешно"));
        return status;
    }
    String loginChecker(ObjectInputStream objectInputStream, BufferedWriter bufferedWriter, BufferedReader bufferedReader){
        System.out.println("loginChecker");
        String status = "";
        do {
            try {
                String command = bufferedReader.readLine();
                if (command.equals("отправить")) {
                    User userin;
                    userin = (User) (objectInputStream.readObject());
                    User user = new User();
                    user.setName(userin.getName());
                    user.setPass(userin.getPass());
                    status = dbLoginRequest(user.getName(), user.getPass());
                    if (status.equals("успешно")) {
                        bufferedWriter.write("Авторизация прошла успешно");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } else if (status.equals("Неверный логин или пароль")){
                        bufferedWriter.write("Неверный логин или пароль");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        break;
                    }
                } else if (command.equals("назад")) {
                    status = "назад";
                    break;
                } else {
                    bufferedWriter.write("Некорректная команда");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } while (!status.equals("успешно"));
        return status;
    }
    public String dbLoginRequest(String name, String pass) {
        System.out.println("dbloginrequest");
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/polki", "postgres", "postgres");
            String SQL = "select user_name, user_pass from users where user_name=? and user_pass=?;";
            PreparedStatement prepSt = con.prepareStatement(SQL);
            prepSt.setString(1, name);
            prepSt.setString(2, pass);
            ResultSet rs = prepSt.executeQuery();
            if (rs.next()) {
                return "успешно";
            } else {
                return "Неверный логин или пароль";
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
