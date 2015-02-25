package com.epam.mentor.runner;


import java.io.DataInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClentRunner {

    private static InetSocketAddress socketAddress = new InetSocketAddress("localhost", 25253);

    public static void main(String[] args) {
        System.out.println("--- client started ---");
        while (true) {
            try (Socket socket = new Socket()) {
                socket.connect(socketAddress);
                String message = new DataInputStream(socket.getInputStream()).readUTF();
                System.out.println("Received: " + message);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
