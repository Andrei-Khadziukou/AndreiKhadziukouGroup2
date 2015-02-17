package com.epam.mentor.ohsl;

import java.math.BigDecimal;

public interface ITicketService {
    void sellOne(String seansId, BigDecimal cost, int row, int seat);

    String seeSetsMap(String seansId);

    int getFreeSeats(String seansId);
}
