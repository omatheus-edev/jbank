package codes.matheus.entity.account;

import codes.matheus.entity.client.Client;
import codes.matheus.entity.transaction.Deposit;
import codes.matheus.entity.transaction.Transaction;
import codes.matheus.entity.transaction.Withdrawal;
import codes.matheus.exception.TransactionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public abstract sealed class Account permits SavingsAccount, CheckingAccount {

    // Objects

    @Range(from = 0, to = Long.MAX_VALUE)
    private final long id;
    @Range(from = 0, to = Long.MAX_VALUE)
    private double balance;
    private final @NotNull Client client;
    private final @NotNull Set<@NotNull Transaction> history = new LinkedHashSet<>();

    // Constructor

    public Account(@NotNull Client client) {
        this.client = client;
        this.id = GeneratedID.getId();
        this.balance = 0;
    }

    // Getters

    @Range(from = 0, to = Long.MAX_VALUE)
    public final long getId() {
        return id;
    }

    @Range(from = 0, to = Long.MAX_VALUE)
    public double getBalance() {
        return balance;
    }

    public @NotNull Client getClient() {
        return client;
    }

    public @NotNull Set<@NotNull Transaction> getHistory() {
        return history;
    }

    // Setter

    public void setBalance(@NotNull Transaction transaction) throws TransactionException {
        if (history.contains(transaction)) {
            throw new TransactionException("This account already has this transaction");
        } else {
            this.balance = transaction.calculate(this);
            this.history.add(transaction);
        }
    }

    // Methods

    public abstract @NotNull Withdrawal withdraw(@Range(from = 0, to = Long.MAX_VALUE) double amount) throws TransactionException;

    public abstract @NotNull Deposit deposit(@Range(from = 0, to = Long.MAX_VALUE) double amount) throws TransactionException;

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Classes

    private static final class GeneratedID {
        private static final @NotNull AtomicLong id = new AtomicLong(0);

        @Range(from = 0, to = Long.MAX_VALUE)
        private static long getId() {
            return id.incrementAndGet();
        }
    }
}
