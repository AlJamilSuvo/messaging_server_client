package clinet;

import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by Suvo on 14-May-16.
 */
public class Receiver implements Runnable {
    Socket soc;
    DataInputStream din;
    Thread t;
    ClientMain clientMain;

    public Receiver(Socket soc,ClientMain clientMain) {
        this.soc = soc;
        this.clientMain=clientMain;
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
                        clientMain.addMSG(str);
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
