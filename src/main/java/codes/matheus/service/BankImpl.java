package codes.matheus.service;

import codes.matheus.entity.account.Account;
import codes.matheus.entity.account.Accounts;
import codes.matheus.entity.account.Profitable;
import codes.matheus.entity.transaction.Deposit;
import codes.matheus.entity.transaction.Transaction;
import codes.matheus.entity.transaction.Transference;
import codes.matheus.entity.transaction.Withdrawal;
import codes.matheus.exception.TransactionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class BankImpl implements Bank {
    private final @NotNull Accounts accounts;
    private final @NotNull Set<@NotNull Transaction> transactions;

    public BankImpl() {
        this.accounts = new Accounts();
        this.transactions = new LinkedHashSet<>();
    }

    public @NotNull Set<@NotNull Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public @NotNull Accounts getAccounts() {
        return accounts;
    }

    @Override
    public @NotNull Set<@NotNull Transference> getAllTransference() {
        return transactions.stream().filter(transaction -> transaction instanceof Transference)
                    .map(transaction -> (Transference) transaction).collect(Collectors.toSet());
    }

    public @NotNull Optional<@NotNull Account> findAccount(@Range(from = 0, to = Long.MAX_VALUE) long id) {
        return accounts.stream().filter(account -> account.getId() == id).findFirst();
    }

    @Override
    public @NotNull Deposit deposit(@NotNull Account account, @Range(from = 0, to = Long.MAX_VALUE) double value) throws TransactionException {
        @NotNull Deposit transaction = account.deposit(value);
        transactions.add(transaction);
        return transaction;
    }

    @Override
    public @NotNull Withdrawal withdrawal(@NotNull Account account, @Range(from = 0, to = Long.MAX_VALUE) double value) throws TransactionException {
        @NotNull Withdrawal transaction = account.withdraw(value);
        transactions.add(transaction);
        return transaction;
    }

    @Override
    public @NotNull Transference transfer(@NotNull Account origin, @NotNull Account target, @Range(from = 0, to = Long.MAX_VALUE) double amount) throws TransactionException {
        if (origin.equals(target)) {
            throw new TransactionException("You cannot make a transfer to yourself");
        } if (amount <= 0) {
            throw new TransactionException("Amount insufficient");
        } if (amount > origin.getBalance()) {
            throw new TransactionException("Balance insufficient");
        }

        @NotNull Transference transaction = Transaction.transference(origin, target, amount);
        origin.setBalance(transaction);
        target.setBalance(transaction);
        transactions.add(transaction);
        return transaction;
    }

    @Override
    public void applyYield(@NotNull Account account) {
        if (account instanceof Profitable profitable) {
            try {
                profitable.applyYield();
            } catch (TransactionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void applyYieldAll() {
        accounts.stream().filter(account -> account instanceof Profitable)
                .map(account -> (Profitable) account).forEach(profitable -> {
                    try {
                        profitable.applyYield();
                    } catch (TransactionException e) {
                        e.printStackTrace();
                    }
                });
    }
}
