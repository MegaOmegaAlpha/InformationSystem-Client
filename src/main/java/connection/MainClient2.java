package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MainClient2 {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 0066);
            while (true) {
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                String answer = (String) objectInputStream.readObject();
                System.out.println(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
