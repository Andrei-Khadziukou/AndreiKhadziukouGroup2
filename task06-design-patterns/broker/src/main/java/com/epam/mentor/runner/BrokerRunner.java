package com.epam.mentor.runner;

import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BrokerRunner {

    private static Queue<String> messageQueue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws Exception {
        System.out.println("--- broker started ---");
        new Thread(new Receiver()).start();
        new Thread(new Sender()).start();
    }

    private static class Receiver implements Runnable {
        @Override
        public void run() {
            System.out.println("--- receiver started ---");
            try (ServerSocket socket = new ServerSocket(25252)) {
                while (true) {
                    String message = IOUtils.toString(socket.accept().getInputStream());
                    messageQueue.add(message);
                    System.out.println("Received: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Sender implements Runnable {
        @Override
        public void run() {
            System.out.println("--- sender started ---");
            try (ServerSocket socket = new ServerSocket(25253)) {
                while (true) {
                    if (!messageQueue.isEmpty()) {
                        OutputStream outputStream = socket.accept().getOutputStream();
                        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                        String message = messageQueue.poll();
                        dataOutputStream.writeUTF(message);
                        dataOutputStream.flush();
                        System.out.println("Sent: " + message);
                    }
                    Thread.sleep(2500);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
