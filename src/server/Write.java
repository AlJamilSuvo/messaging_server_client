package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Suvo on 14-May-16.
 */
public class Write {
    Socket socket;
    DataOutputStream dout;

    public Write(Socket socket) {
        this.socket = socket;
        try {
            dout=new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeStr(String str){
        try {
            dout.writeUTF(str);
            dout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
