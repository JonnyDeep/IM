package cn.jonny.client;

import cn.jonny.GlobalStaticVariable;
import com.sun.javafx.iio.gif.GIFImageLoader2;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class client extends Thread  {
    private Socket socket;
    private String msg;
    public client(String hostIP,int port,String name) throws IOException {
        socket = new Socket(hostIP,port);
        System.out.println("client:"+socket.getInetAddress()+"\n"+socket.getLocalPort()+"\n "+socket.getLocalAddress()+"\n"+socket.getLocalSocketAddress()+"\n"+socket.getLocalAddress().getHostAddress());
        msg = name;
        System.out.println("connect to host");
    }

    public void communicate (String msg) {
        BufferedReader in = null;
        PrintWriter out = null;
        BufferedReader consoleRead = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);



            System.out.println("send message to host:"+msg);

            out.print(msg);
            out.flush();
            char[] buffer = new char[100];
            while (in.read(buffer)!=-1) {
                System.out.println("received from host:" );
                System.out.println(buffer);
                consoleRead = new BufferedReader(new InputStreamReader(System.in));
                msg = consoleRead.readLine();
                System.out.println("send message to host:"+msg);
                out.print(msg);
                out.flush();
            }
            System.out.println("over");
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
                out.close();
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }



    }

    public void run(){
        for(int i = 0;i<100;i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            communicate(msg);
        }
    }

    public static void main(String[] args) {

        try {
            new client(GlobalStaticVariable.HOST_IP,GlobalStaticVariable.PORT,"client").start();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        List<Thread> ls = new ArrayList<Thread>();
//        try {
//          for(int i=0;i<20;i++)
//          {
//              client c = new client(GlobalStaticVariable.HOST_IP,GlobalStaticVariable.PORT,"client"+i);
//              try {
//                  TimeUnit.SECONDS.sleep(2);
//              } catch (InterruptedException e) {
//                  e.printStackTrace();
//              }
//              ls.add(c);
//
//          }
//
//            for (Thread t: ls
//                 ) {
//                t.start();
//            }
//        } catch (IOException e) {
//
//
//            e.printStackTrace();
//        }


    }
}
