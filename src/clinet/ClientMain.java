package clinet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.*;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Suvo on 14-May-16.
 */
public class ClientMain extends Application{


    TextField name;
    Button con;
    TextArea msg;
    TextArea ques;
    TextField sendMsg;
    Button send;
    Socket soc;
    ClientMain clientMain;
    Receiver receiver;
    Write write;
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root=new VBox();
        name=new TextField();
        con=new Button("Connect");
        msg=new TextArea();
        clientMain=this;
        sendMsg=new TextField();
        send=new Button("Send");
        root.getChildren().addAll(name,con,msg,sendMsg,send);
        //root.getChildren().remove(ques);
        Scene scene=new Scene(root,400,300);
        primaryStage.setScene(scene);
        primaryStage.show();

        con.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    soc=new Socket("192.168.0.107",6000);
                    new Receiver(soc,clientMain);
                    write=new Write(soc);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String str=name.getText()+" : "+sendMsg.getText();
                write.writeStr(str);
            }
        });




    }

    public void addMSG(String str){
        msg.setText(msg.getText()+"\n"+str);
    }


    public static void main(String[] args) {
        Application.launch();
    }
}
