package com.epam.mentor.runner;

import com.epam.mentor.ohsl.ITicketService;
import com.epam.mentor.ohsl.TicketServise;

import java.math.BigDecimal;

public class ServerRunner {
    public static void main(String[] args) {
        ITicketService service = new TicketServise();
        service.sellOne("zu", BigDecimal.ZERO, 3, 3);
        service.sellOne("zu", BigDecimal.ZERO, 3, 4);
        service.sellOne("zu", BigDecimal.ZERO, 3, 5);
        System.out.println("free seats = " + service.getFreeSeats("zu"));
        System.out.println("seats map: \n" + service.seeSetsMap("zu"));
        System.out.println();
    }

}
