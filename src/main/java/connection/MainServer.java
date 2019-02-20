package connection;

import model.Track;
import model.TrackXML;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServer {

    private static final Logger logger = Logger.getLogger(MainServer.class);

    public static void main(String[] args) {
        logger.info("Server started");
        try {
            ServerSocket serverSocket = new ServerSocket(0066);
            ExecutorService executor = Executors.newCachedThreadPool();
            Thread mainThread = Thread.currentThread();
            while (!mainThread.isInterrupted()) {
                executor.submit(new Server(serverSocket.accept()));
                System.out.println("something");
            }
            executor.shutdownNow();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*TrackXML trackXML = new TrackXML(new File("target/tracksToSend"));
        Random random = new Random();
        for (int i = 1; i < 51; i++) {
            trackXML.add(new Track("Best song" + i, new LinkedList<>(Arrays.asList(new String[] {"Andrey" + i})),
                    Optional.of("Album" + i), 60 + random.nextInt(1800 - 60 + 1),
                    new LinkedList<>(Arrays.asList(UUID.randomUUID()))));
        }*/
    }

}
