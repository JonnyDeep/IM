package cn.jonny.Server;

import cn.jonny.utils.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class ServerReport implements Runnable{

    private static Logger logger = LogManager.getLogger(ServerReport.class);

    private Gson gson = new Gson();
    private Socket socket;
    PrintWriter out=null;
    BufferedReader io= null;
    private Map<String,Socket> map=null;
    public ServerReport(Socket socket,Map<String,Socket> map) throws IOException
    {
        System.out.println("host create communicatioon thread");
        this.socket = socket;
        io = new BufferedReader(
                new InputStreamReader(
                        this.socket.getInputStream(),"UTF-8"));
        out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(this.socket.getOutputStream(),"UTF-8")),true);
        this.map = map;
    }

    private void sendMessage(String msg,PrintWriter out) throws IOException{

        out.write(msg);
        out.flush();
        logger.info("message send success");
    }

    private void getMessage(char[] buffer,BufferedReader in) throws IOException {
        Message message = null;
        while (io.read(buffer)!=-1) {
//            System.out.println("get message from client:");
//
//            System.out.println("get over");

            logger.info("get message from client");

            //must trim() or gson cannot resolve the string and form the right object
            message = gson.fromJson(String.valueOf(buffer).trim(),Message.class);
            logger.info(message!=null?message:"null");

            break;
        }
        logger.info("------------------------------------------------");
        
        if(message!=null&&map!=null){
//            iterator the map
            map.forEach((k,v)->logger.info("key:value="+k+":"+v));
//            logger.info(message.getSendIP());
//            logger.info(message.getMsg());
            logger.info("receive ip:"+message.getRecvIP());
            if(map.containsKey(message.getRecvIP())){
                map.get(message.getRecvIP()).getOutputStream().write(gson.toJson(message).getBytes());
                map.get(message.getRecvIP()).getOutputStream().flush();
                logger.info("send successed");
            }else{
                logger.info("send object is offline");
            }
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
