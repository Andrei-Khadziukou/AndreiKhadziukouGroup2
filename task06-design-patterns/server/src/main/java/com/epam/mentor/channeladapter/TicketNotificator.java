package com.epam.mentor.channeladapter;

import com.epam.mentor.domain.Ticket;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TicketNotificator {

    private static InetSocketAddress socketAddress = new InetSocketAddress("localhost", 25252);

    public void sendNotification(Ticket ticket) {
        try (Socket socket = new Socket()) {
            socket.connect(socketAddress);
            String message = ticket.toString();
            try (OutputStream stream = socket.getOutputStream()) {
                stream.write(message.getBytes());
                stream.flush();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
