package com.epam.mentor.repository;

import com.epam.mentor.domain.Ticket;

import java.util.LinkedList;
import java.util.List;

public class TicketRepository extends AbstractDb<Ticket> {
    public List<Ticket> getAll(String seansId) {
        LinkedList<Ticket> tickets = new LinkedList<>();
        for (Ticket t : table.values()) {
            if (t.getSeansId().equalsIgnoreCase(seansId)) {
                tickets.add(t);
            }
        }
        return tickets;
    }

}
