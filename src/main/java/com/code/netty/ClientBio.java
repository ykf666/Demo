package com.code.netty;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author yan.kefei
 * @date 2018/5/16 11:19
 */
public class ClientBio {

    public static void main(String[] args) {
        String serverName = "127.0.0.1";
        int port = 8080;
        try {
            Socket client = new Socket(serverName, port);
            System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            DataInputStream dis = new DataInputStream(client.getInputStream());
            while (true) {
                if (client.isConnected()) {
                    System.out.print("请输入:");
                    String req = new BufferedReader(new InputStreamReader(System.in)).readLine();
                    dos.writeUTF(req);

                    String res = dis.readUTF();
                    System.out.println("服务器响应： " + res);
                    if ("close".equalsIgnoreCase(res)) {
                        break;
                    }
                }
            }
            // 关闭资源
            dis.close();
            dos.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
