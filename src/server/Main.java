package server;

import server.server.TCPForwarder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Daniel on 2016/11/07.
 */
public class Main
{
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(7683);
            serverSocket.setSoTimeout(300000);
            Socket connection = serverSocket.accept();
            TCPForwarder forward = new TCPForwarder(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}