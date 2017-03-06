package server;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args)
    {
        try(ServerSocket sServer = new ServerSocket(4000);
            Socket cn = sServer.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(cn.getInputStream()));
            BufferedOutputStream bos = new BufferedOutputStream(cn.getOutputStream());)
        {
            String[] broj = br.readLine().split(" ");
//            System.out.println(Arrays.toString(broj));
//            while(broj != null && !broj.equals(""))
            Double rez = Double.valueOf(broj[0]) + Double.valueOf(broj[1]);
            bos.write(rez.toString().getBytes());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
