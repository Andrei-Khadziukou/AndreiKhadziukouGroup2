package com.epam.mentor.acl;

import com.epam.mentor.domain.BusinessException;
import com.epam.mentor.domain.Constants;
import com.epam.mentor.domain.Ticket;

import java.math.BigDecimal;

public class TicketValidator {

    public static void validate(Ticket ticket) {
        if (ticket == null) throw new NullPointerException();
        if (ticket.getSeansId() == null) throw new BusinessException("Wrong seans id");
        if (ticket.getTime() == 0) throw new BusinessException("Wrong time");
        if (ticket.getRow() < 1 || ticket.getRow() > Constants.MAX_ROWS)
            throw new BusinessException("Wrong row number");
        if (ticket.getSeat() < 1 || ticket.getSeat() > Constants.MAX_SEATS)
            throw new BusinessException("Wrong seat number");
        if (ticket.getCost() == null || ticket.getCost().compareTo(BigDecimal.ZERO) < 0)
            throw new BusinessException("Wrong cost");
    }
}
