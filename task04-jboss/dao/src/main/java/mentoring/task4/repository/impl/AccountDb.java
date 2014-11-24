package mentoring.task4.repository.impl;

import mentoring.task4.domain.Account;
import mentoring.task4.domain.Deposit;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@Singleton
public class AccountDb extends AbstractDb<Account> {
    @PostConstruct
    public void init() {
        create(generateAccount("user acc", "q"));
        create(generateAccount("another acc", "w"));
    }

    public Account findByUser(String userId) {
        Collection<Account> all = findAll();
        for (Account account : all) {
            if (account.getUserId().equalsIgnoreCase(userId)) {
                return account;
            }
        }
        return null;
    }

    private Account generateAccount(String name, String user) {
        Account account = new Account();
        account.setId(UUID.randomUUID().toString());
        account.setPassp(UUID.randomUUID().toString().substring(24));
        account.setName(name);
        account.setUserId(user);
        account.setDeposits(generateDeposits());
        return account;
    }

    private List<Deposit> generateDeposits() {
        List<Deposit> deposits = new LinkedList<>();
        deposits.add(new Deposit("USD", "50"));
        deposits.add(new Deposit("EUR", "30"));
        return deposits;
    }
}
