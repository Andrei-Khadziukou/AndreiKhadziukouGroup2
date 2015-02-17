package com.epam.mentor.repository;

public class RepositoryFactory {
    private static TicketRepository repository = new TicketRepository();

    public static TicketRepository getRepository() {
        return repository;
    }

}
