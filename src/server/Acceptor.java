package server;

import javafx.application.Platform;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Suvo on 14-May-16.
 */
public class Acceptor implements  Runnable {
    ServerSocket serverSocket;
    ServerMain serverMain;
    Thread t;


    public Acceptor(ServerSocket serverSocket, ServerMain serverMain) {
        this.serverSocket = serverSocket;
        this.serverMain = serverMain;
        t=new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (true){
            try {
                Socket socket=serverSocket.accept();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        serverMain.addClient(socket);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
