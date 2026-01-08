package codes.matheus.service;

import codes.matheus.entity.account.Account;
import codes.matheus.entity.account.Accounts;
import codes.matheus.entity.account.CheckingAccount;
import codes.matheus.entity.account.SavingsAccount;
import codes.matheus.entity.client.Client;
import codes.matheus.entity.transaction.Deposit;
import codes.matheus.entity.transaction.Transference;
import codes.matheus.entity.transaction.Withdrawal;
import codes.matheus.exception.TransactionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Set;

public interface Bank {
    default @NotNull CheckingAccount createCheckingAccount(@NotNull Client client) {
        @NotNull CheckingAccount acc = new CheckingAccount(client);
        getAccounts().add(acc);
        return acc;
    }

    default @NotNull SavingsAccount createSavingsAccount(@NotNull Client client) {
        @NotNull SavingsAccount acc = new SavingsAccount(client);
        getAccounts().add(acc);
        return acc;
    }

    @NotNull Accounts getAccounts();

    @NotNull Set<@NotNull Transference> getTransfers();

    @NotNull Deposit deposit(@NotNull Account account, @Range(from = 0, to = Long.MAX_VALUE) double value) throws TransactionException;

    @NotNull Withdrawal withdraw(@NotNull Account account, @Range(from = 0, to = Long.MAX_VALUE) double value) throws TransactionException;

    @NotNull Transference transfer(@NotNull Account origin, @NotNull Account target, @Range(from = 0, to = Long.MAX_VALUE) double amount) throws TransactionException;

    void applyYield(@NotNull Account account);

    void applyYieldAll();
}
