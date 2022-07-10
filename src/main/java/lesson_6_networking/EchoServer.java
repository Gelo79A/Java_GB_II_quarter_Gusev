package lesson_6_networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) {
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(8089)){
            System.out.println("Сегвер ожидает подключения ...");
            socket = serverSocket.accept(); //ждём подключения БЛОКИРУЕМСЯ
            System.out.println("Клиент подключился");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String massage = dataInputStream.readUTF();
                if (massage.equals("/end")) {
                    break;
                }
                dataOutputStream.writeUTF("Echo" + massage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } //finaly {serverSocket.close()}
    }
}
