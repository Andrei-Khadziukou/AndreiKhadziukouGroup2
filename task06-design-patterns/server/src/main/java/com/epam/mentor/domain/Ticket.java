package com.epam.mentor.domain;

import com.epam.mentor.repository.RepositoryFactory;
import com.epam.mentor.repository.TicketRepository;

import java.math.BigDecimal;
import java.util.Date;

public class Ticket extends Entity {
    private BigDecimal cost;
    private String seansId;
    private int row;
    private int seat;
    private long time;

    private TicketRepository repository;

    public Ticket() {
        repository = RepositoryFactory.getRepository();
        super.setRepository(repository);
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getSeansId() {
        return seansId;
    }

    public void setSeansId(String seansId) {
        this.seansId = seansId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (row != ticket.row) return false;
        if (seat != ticket.seat) return false;
        if (seansId != null ? !seansId.equals(ticket.seansId) : ticket.seansId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (seansId != null ? seansId.hashCode() : 0);
        result = 31 * result + row;
        result = 31 * result + seat;
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + getId() +
                ", cost=" + cost +
                ", seansId='" + seansId + '\'' +
                ", row=" + row +
                ", seat=" + seat +
                ", time=" + new Date(time) +
                '}';
    }
}
