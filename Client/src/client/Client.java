package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        Frame f = new Frame();
        LayoutManager layOut = new FlowLayout();
        f.setLayout(layOut);
        f.setSize(300, 300);

        TextField tf = new TextField();
        f.add(tf);


        TextField tf1 = new TextField();
        f.add(tf1);

        Label l = new Label("REZULTAT");
        f.add(l);

        Button b = new Button("SABIRANJE");
        f.add(b);

        b.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
               
                try(Socket cn = new Socket("localhost", 4000);
                    BufferedReader br = new BufferedReader(new InputStreamReader(cn.getInputStream()));
                    BufferedOutputStream bos = new BufferedOutputStream(cn.getOutputStream());)
                {
                    bos.write((tf.getText() + " " + tf1.getText() + "\r\n").getBytes()); 
                    bos.flush();
                    
                    l.setText(br.readLine());

                }
                catch(IOException exception)
                {
                    System.out.println(exception.getMessage());
                }
            }
           
        });
        
       f.setVisible(true); 

        
        f.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }
        });
    }
}
