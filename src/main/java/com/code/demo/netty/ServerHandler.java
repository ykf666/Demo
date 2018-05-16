package com.code.demo.netty;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author yan.kefei
 * @date 2018/5/15 15:30
 */
public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String threadName = Thread.currentThread().getName();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            while (socket.isConnected()) {
                String req = dis.readUTF();
                System.out.println("[" + threadName + "]: " + req + " from " + socket.getRemoteSocketAddress());
                if ("close".equalsIgnoreCase(req)) {
                    dos.writeUTF("close");
                    break;
                } else {
                    dos.writeUTF("Hello");
                }
            }
            dos.close();
            dis.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
