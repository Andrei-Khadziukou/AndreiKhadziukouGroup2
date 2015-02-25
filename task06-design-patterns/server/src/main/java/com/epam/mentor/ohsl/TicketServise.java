package com.epam.mentor.ohsl;

import com.epam.mentor.acl.TicketValidator;
import com.epam.mentor.aggregator.SeatsMap;
import com.epam.mentor.channeladapter.TicketNotificator;
import com.epam.mentor.domain.Ticket;

import java.math.BigDecimal;
import java.util.Date;

public class TicketServise implements ITicketService {

    private TicketNotificator notificator = new TicketNotificator();

    @Override
    public void sellOne(String seansId, BigDecimal cost, int row, int seat) {
        Ticket ticket = new Ticket();
        ticket.setSeansId(seansId);
        ticket.setCost(cost);
        ticket.setRow(row);
        ticket.setSeat(seat);
        ticket.setTime(new Date().getTime());
        TicketValidator.validate(ticket);
        ticket.save();
        System.out.println("Sold: " + ticket);
        notificator.sendNotification(ticket);
    }

    @Override
    public String seeSetsMap(String seansId) {
        SeatsMap map = new SeatsMap(seansId);
        map.refresh();
        return map.toString();
    }

    @Override
    public int getFreeSeats(String seansId) {
        SeatsMap map = new SeatsMap(seansId);
        map.refresh();
        return map.getFreeSeats();
    }
}
