package cn.jonny.utils;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sendIP;
    private String RecvIP;
    private String msg;

    public String getSendIP() {
        return sendIP;
    }

    public void setSendIP(String sendIP) {
        this.sendIP = sendIP;
    }

    public String getRecvIP() {
        return RecvIP;
    }

    public void setRecvIP(String recvIP) {
        RecvIP = recvIP;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public String toString() {
        return "Message{" +
                "sendIP='" + sendIP + '\'' +
                ", RecvIP='" + RecvIP + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
