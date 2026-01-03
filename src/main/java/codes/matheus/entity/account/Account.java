package codes.matheus.entity.account;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Account {

    // Objects

    @Range(from = 0, to = Long.MAX_VALUE)
    private final long id;
    @Range(from = 0, to = Long.MAX_VALUE)
    private double balance;
    private final @NotNull Client client;

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

    // Methods

    @Range(from = 0, to = Long.MAX_VALUE)
    public abstract double withdraw(@Range(from = 0, to = Long.MAX_VALUE) double amount);

    @Range(from = 0, to = Long.MAX_VALUE)
    public abstract double deposit(@Range(from = 0, to = Long.MAX_VALUE) double amount);

    @Range(from = 0, to = Long.MAX_VALUE)
    public abstract double transfer(@NotNull Account receiver, @Range(from = 0, to = Long.MAX_VALUE) double amount);

    // Classes

    private static final class GeneratedID {
        private static final @NotNull AtomicLong id = new AtomicLong(0);

        @Range(from = 0, to = Long.MAX_VALUE)
        private static long getId() {
            return id.incrementAndGet();
        }
    }
}
