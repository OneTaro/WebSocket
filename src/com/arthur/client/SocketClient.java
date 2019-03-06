package com.arthur.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * socket客户端
 *
 * @author wangss
 * @create 2019-03-06 23:27
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        // 要连接的服务器IP地址和端口
        String host = "127.0.0.1";
        int port = 55533;
        // 与服务器建立连接
        Socket socket = new Socket(host, port);
        // 建立连接后获取输出流
        OutputStream outputStream = socket.getOutputStream();
        String message = "Hello server, 我向你发送了一条信息";
        outputStream.write(message.getBytes("UTF-8"));
        // 通过shutdownOutput高速服务器已经发送完数据，后续只能接收数据
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, len, "UTF-8"));
        }
        System.out.println("get message from server: " + sb);

        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
