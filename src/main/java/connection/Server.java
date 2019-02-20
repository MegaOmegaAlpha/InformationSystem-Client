package connection;

import controller.Message;
import controller.MessageHandler;
import model.Genre;
import model.Track;
import model.TrackXML;
//import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class Server implements Runnable {

    private Socket socket;
//    private static final Logger logger = Logger.getLogger(Server.class);

    public Server(Socket socket) {
        this.socket = socket;
        System.out.println("Client connected");
//        logger.info("Client connected");
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream;
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            while (!Thread.currentThread().isInterrupted()) {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();
                System.out.println(message.actionID);
                MessageHandler messageHandler = new MessageHandler();
                messageHandler.handle(message);
            }
        } catch (SocketException e) {
            try {
                socket.close();
            } catch (IOException e1) {
//                logger.error("IOException while socket closing");
            }
        } catch (IOException e) {
//            logger.error("IOException in 'run'");
        } catch (ClassNotFoundException e) {
//            logger.error("ClassNotFoundException in 'run'");
        }
    }
}
