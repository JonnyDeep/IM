package cn.jonny.Server;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerReport implements Runnable{

    private Socket socket;
    PrintWriter out=null;
    BufferedReader io= null;
    public ServerReport(Socket socket) throws IOException
    {
        System.out.println("host create communicatioon thread");
        this.socket = socket;
        io = new BufferedReader(
                new InputStreamReader(
                        this.socket.getInputStream()));
        out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(this.socket.getOutputStream())),true);
    }

    private void sendMessage(String msg,PrintWriter out) throws IOException{

        out.write(msg);
        out.flush();
    }

    private void getMessage(char[] buffer,BufferedReader in) throws IOException {
        while (io.read(buffer)!=-1) {
            System.out.println("get message from client:");
            System.out.println(buffer);
            System.out.println("get over");
            break;
        }
    }


    public void run() {
        System.out.println("begin communication");
        BufferedReader consoleReader = null;
        try {
            while(true)
            {
                char[] buffer = new char[100];

                getMessage(buffer, io);
                System.out.println("------------------------------------------------");
                consoleReader = new BufferedReader(new InputStreamReader(System.in));
                String msg = consoleReader.readLine();
                sendMessage(msg, out);

            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                this.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public  void close () throws IOException
    {
        if(io!=null)
        {
            io.close();
        }

        if(out!=null){
            out.close();
        }

    }
}
