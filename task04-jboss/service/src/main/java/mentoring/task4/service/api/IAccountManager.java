package mentoring.task4.service.api;

import mentoring.task4.domain.Account;
import mentoring.task4.domain.Deposit;

import java.math.BigDecimal;
import java.util.Collection;

public interface IAccountManager {
    void add(String passp, String userId, String name, String phone, String addr);

    Account get(String id);

    void update(String id, String passp, String userId, String name, String phone, String addr);

    void remove(String id);

    Collection<Account> findAll();

    Account findByUser(String userId);

    void doExchange(Account account, String from, String to, BigDecimal summ);

    Deposit findDeposit(Account account, String from);
}
