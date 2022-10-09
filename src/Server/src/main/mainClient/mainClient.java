package src.main.mainClient;

import org.postgresql.gss.GSSOutputStream;
import org.w3c.dom.ls.LSOutput;
import src.main.HelpClasses.User;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class mainClient {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1", 19925);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String status;
            do {
                System.out.println("Выберите действие:  войти, зарегистрироваться или выйти");
                String status1 = "";
                Scanner scan = new Scanner(System.in);
                status = scan.nextLine();
                if (status.equals("войти")) {
                    System.out.println("войти");
                    bufferedWriter.write(status);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    status1 = enterListener(objectOutputStream, bufferedWriter, bufferedReader, status1);
                } else if(status.equals("зарегистрироваться")){
                    registerListener();
                } else if (status1.equals("Авторизация прошла успешно")) {
                    clientWorkerProcess();
                } else if(status1.equals("выйти")) {
                    status = status1;
                }
            } while(status.equals("выйти"));
            socket.close();
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String enterListener(ObjectOutputStream objectOutputStream, BufferedWriter bufferedWriter, BufferedReader bufferedReader, String status1) throws IOException {
        String enter = "";
        String param = "";
        Scanner scan = new Scanner(System.in);
        User user = new User();
        while(true) {
            enter = scan.nextLine();
            if (enter.equals("логин")) {
                System.out.println("Введите ваш логин");
                user.setName(scan.nextLine());
                System.out.println("логин введен");
            }else if(enter.equals("пароль")) {
                System.out.println("Введите ваш пароль");
                user.setPass(scan.nextLine());
                System.out.println("пароль введен");
            }else if (enter.equals("отправить")){
                try {
                    if (!user.getName().equals("") & !user.getPass().equals("")){
                        bufferedWriter.write("отправить");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        objectOutputStream.writeObject(user);
                        objectOutputStream.flush();
                        System.out.println("данные отправлены");
                    } else {
                        System.out.println("Данные запонены некорректно, попробуйте еще");
                    }
                    status1 = bufferedReader.readLine();
                    if (status1.equals("успешно")) {
                        System.out.println(status1);
                        return status1;
                    } else if(status1.equals("Неверный логин или пароль")) {
                        System.out.println("Неверный логин или пароль");
                        return "";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (enter.equals("назад")) {
                return enter;
            }else if(enter.equals("выйти")){
                return enter;
            }

        }
    }
    public static void registerListener() {

    }
    public static void clientWorkerProcess() {

    }
}
