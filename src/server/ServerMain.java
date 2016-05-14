package server;

import clinet.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suvo on 14-May-16.
 */
public class ServerMain extends Application{
    Button st;
    TextArea clients;
    TextArea msg;
    List<Write> writes=new ArrayList<>();
    ServerSocket socket;
    ServerMain serverMain;

    @Override
    public void start(Stage primaryStage) throws Exception {
        st=new Button("Start");
        clients=new TextArea();
        msg=new TextArea();
        serverMain=this;
        VBox box=new VBox();
        box.getChildren().addAll(st,clients,msg);
        Scene scene=new Scene(box,400,300);
        primaryStage.setScene(scene);
        primaryStage.show();
        st.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    InetAddress inetAddress=InetAddress.getLocalHost();
                    System.out.println(inetAddress.toString());
                    socket=new ServerSocket(6000);
                    new Acceptor(socket,serverMain);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



    }


    public void addClient(Socket socket){
        Write write=new Write(socket);
        clients.setText(clients.getText()+"\n"+socket.toString());
        new Receiver(socket,this);
        writes.add(write);
    }

    public void sendMSG(String str){
        msg.setText(msg.getText()+"\n"+str);
        for(Write w:writes) w.writeStr(str);
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
