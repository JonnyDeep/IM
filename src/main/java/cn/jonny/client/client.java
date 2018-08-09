package cn.jonny.client;

import cn.jonny.GlobalStaticVariable;
import cn.jonny.utils.Message;
import com.google.gson.Gson;
import com.sun.javafx.iio.gif.GIFImageLoader2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class client extends Thread  {
    private Socket socket;
    private String msg;
    private static Gson gson = new Gson();
    private static Logger logger = LogManager.getLogger(client.class);
    public client(String hostIP,int port,String name) throws IOException {
        socket = new Socket(hostIP,port);
        msg = name;
        System.out.println("connect to host");
    }

    public client(String hostIP,int port,int localPort) throws IOException {
        socket = new Socket(hostIP,port,null,localPort);
        System.out.println("connect to host");
    }

    public client(String hostIP,int port) throws IOException {
        socket = new Socket(hostIP,port);
        System.out.println("connect to host");
    }
    public void communicate (String msg) {
        BufferedReader in = null;
        PrintWriter out = null;
        BufferedReader consoleRead = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8")),true);



            System.out.println(socket.getLocalPort()+" send message to host:"+msg);

            out.print(msg);
            out.flush();
            char[] buffer = new char[100];
            while (in.read(buffer)!=-1) {
                System.out.println(socket.getLocalPort()+" received from host:" );
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
//            Message message = new Message();
//            message.setMsg("Hello,Iam clinet A");
//            message.setSendIP("127.0.0.1:8090");
//            message.setRecvIP("127.0.0.1:8088");
//            String json = new Gson().toJson(message);
//
////            new client(GlobalStaticVariable.HOST_IP, GlobalStaticVariable.PORT, "client").start();
//            client clientA =  new client(GlobalStaticVariable.HOST_IP, GlobalStaticVariable.PORT,8090);
//
//            TimeUnit.SECONDS.sleep(20);
//            clientA.communicate(gson.toJson(message));
            Message message1 = new Message();
            message1.setMsg("Hello,Iam clinet B");
            message1.setSendIP("127.0.0.1:8088");
            message1.setRecvIP("127.0.0.1:8090");
            client clientB = new client(GlobalStaticVariable.HOST_IP, GlobalStaticVariable.PORT,8088);


            clientB.communicate(gson.toJson(message1));
            TimeUnit.SECONDS.sleep(20);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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
