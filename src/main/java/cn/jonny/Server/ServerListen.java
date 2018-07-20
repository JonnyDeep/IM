package cn.jonny.Server;

import cn.jonny.GlobalStaticVariable;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

public class ServerListen implements Runnable{
    private Map<String,Socket> map= new HashMap<String, Socket>();
    private ServerSocket serverSocket;
    PrintWriter out=null;
    BufferedReader in= null;
    public ServerListen(int port) throws IOException {
        //create a server socket,bound to the specified prot
        serverSocket = new ServerSocket(port);
        System.out.println("server:"+serverSocket.getInetAddress()+" "+serverSocket.getLocalPort()+" "+ serverSocket.getLocalSocketAddress());
        System.out.println("waiting for connected....");
    }
    public void run()
    {
        try {
            while (true) {
                //get a socket,bind to the client
                Socket socket = serverSocket.accept();

                System.out.println("connection has build...");

                ServerReport sr = new ServerReport(socket);
                Thread t = new Thread(sr);
                t.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ServerListen s = null;
        try {
            s = new ServerListen(GlobalStaticVariable.PORT);
            Thread listen = new Thread(s);
            listen.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
