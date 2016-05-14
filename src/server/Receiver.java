package server;

import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Suvo on 14-May-16.
 */
public class Receiver implements Runnable {
    Socket soc;
    DataInputStream din;
    Thread t;
    ServerMain serverMain;

    public Receiver(Socket soc,ServerMain serverMain) {
        this.soc = soc;
        this.serverMain=serverMain;
        try {
            din=new DataInputStream(soc.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        t=new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        while(true){
            try {
                String str=din.readUTF();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        serverMain.sendMSG(str);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
