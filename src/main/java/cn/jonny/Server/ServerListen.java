package cn.jonny.Server;

import cn.jonny.GlobalStaticVariable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;


public class ServerListen implements Runnable{
    private Map<String,Socket> map= new HashMap<String, Socket>();

    private ServerSocket serverSocket;

    @Resource
    private ServerReport serverReport;
    private Socket socket;
    public Map<String, Socket> getMap() {
        return map;
    }

    public Socket getSocket() {
        return socket;
    }

    private static Logger logger = LogManager.getLogger(ServerListen.class);
    PrintWriter out=null;
    BufferedReader in= null;

    public ServerListen(int port) throws IOException {
        //create a server socket,bound to the specified prot
        serverSocket = new ServerSocket(port);
        System.out.println("waiting for connected....");
    }
    public void run()
    {
        try {
            while (true) {
                //get a socket,bind to the client
                socket  = serverSocket.accept();
                //get client string and save in server
                map.put(socket.getRemoteSocketAddress().toString().substring(1),socket);
                logger.info(socket.getRemoteSocketAddress().toString()+":上线");
                System.out.println("connection has build...");

//                ServerReport sr = new ServerReport();
                serverReport.init(socket,map);
                Thread t = new Thread(serverReport);
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

}
