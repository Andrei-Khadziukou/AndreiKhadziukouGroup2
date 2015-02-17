package com.epam.mentor.aggregator;

import com.epam.mentor.domain.Constants;
import com.epam.mentor.domain.Ticket;
import com.epam.mentor.repository.RepositoryFactory;
import com.epam.mentor.repository.TicketRepository;

import java.util.Arrays;
import java.util.List;

public class SeatsMap {
    private String seansId;
    private short[][] map = new short[Constants.MAX_ROWS][Constants.MAX_SEATS];

    private TicketRepository repository = RepositoryFactory.getRepository();

    public SeatsMap(String seansId) {
        this.seansId = seansId;
    }

    public int getFreeSeats() {
        int occupied = 0;
        for (short[] row : map) {
            for (short seat : row) {
                occupied += seat;
            }
        }
        return Constants.MAX_ROWS * Constants.MAX_SEATS - occupied;
    }

    public void refresh() {
        map = new short[Constants.MAX_ROWS][Constants.MAX_SEATS];
        List<Ticket> all = repository.getAll(seansId);
        for (Ticket ticket : all) {
            occupySeat(ticket.getRow(), ticket.getSeat());
        }
    }

    private void occupySeat(int row, int seat) {
        map[row - 1][seat - 1] = 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (short[] row : map) {
            sb.append(Arrays.toString(row)).append('\n');
        }
        return sb.toString();
    }
}
