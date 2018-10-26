package com.code.demo.netty;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yan.kefei
 * @date 2018/5/15 14:07
 */
public class ServerBio {

    public static void main(String[] args) {
        int clientNo = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("端口号" + serverSocket.getLocalPort()+", 等待连接...");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerBIOHandler(socket), "thread-" + clientNo).start();
                clientNo++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}