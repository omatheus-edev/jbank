package codes.matheus.entity.account;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.concurrent.atomic.AtomicLong;

public abstract class Account {

    // Objects

    @Range(from = 0, to = Long.MAX_VALUE)
    private final long id;

    @Range(from = 0, to = Long.MAX_VALUE)
    private double balance;

    // Constructor

    public Account() {
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
