package com.web.programming.java;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例题3： 客户端发送文件到服务端，服务端将文件保存到本地。 并返回"发送成功"给客户端
 */
public class TCPTest3 {
    @Test
    public void client(){
        Socket socket = null;
        OutputStream outputStream = null;
        FileInputStream fi = null;
        InputStream serverInputStream = null;
        ByteArrayOutputStream baos = null;
        try{
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet, 8899);
            outputStream = socket.getOutputStream();
            System.out.println("start upload file");
            fi = new FileInputStream(new File("lib/test.txt"));
            byte[] buffer = new byte[20];
            int len;
            while((len = fi.read(buffer)) != -1){
                outputStream.write(buffer, 0, len);
            }
            socket.shutdownOutput(); //如果不shutDown的话，就会导致server在read的时候一直等待新的read，shutDown相当于一个信号告诉对方没有新的输出了
            System.out.println("file transmission completed");
            serverInputStream = socket.getInputStream();
            baos = new ByteArrayOutputStream();
            buffer = new byte[1024];
            while ((len = serverInputStream.read(buffer)) != -1){
                baos.write(buffer, 0, len);
            }
            System.out.println(baos.toString());
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if ( outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(baos != null ) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(serverInputStream != null){
                try {
                    serverInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if( socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    public void server() throws InterruptedException {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        FileOutputStream fo = null;
        InputStream is = null;
        OutputStream ackOutputStream = null;
        try{
            serverSocket = new ServerSocket(8899);
            clientSocket = serverSocket.accept();
            fo = new FileOutputStream("lib/received_file.txt");
            is = clientSocket.getInputStream();
            System.out.println("downloading file");
            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer)) != -1){
                fo.write(buffer,0,len);
            }
            System.out.println("file downloaded successfully");
            ackOutputStream = clientSocket.getOutputStream();
            ackOutputStream.write("file received !!!".getBytes());
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if ( fo != null) {
                try {
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ackOutputStream != null){
                try {
                    ackOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (clientSocket != null){
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if ( serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
