package codes.matheus.entity.account;

import codes.matheus.entity.client.Client;
import codes.matheus.entity.transaction.Deposit;
import codes.matheus.entity.transaction.Transaction;
import codes.matheus.entity.transaction.Withdrawal;
import codes.matheus.exception.TransactionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class CheckingAccount extends Account {
    public CheckingAccount(@NotNull Client client) {
        super(client);
    }

    @Override
    public @NotNull Withdrawal withdraw(@Range(from = 0, to = Long.MAX_VALUE) double amount) throws TransactionException {
        if (amount <= 0 || amount > this.getBalance()) {
            throw new TransactionException("The amount is zero or less than customer balance");
        }
        @NotNull Withdrawal withdrawal = Transaction.withdrawal(this, amount);
        this.setBalance(withdrawal);
        return withdrawal;
    }

    @Override
    public @NotNull Deposit deposit(@Range(from = 0, to = Long.MAX_VALUE) double amount) throws TransactionException {
        if (amount <= 0) {
            throw new TransactionException("The amount is zero or less than customer balance");
        }
        @NotNull Deposit deposit = Transaction.deposit(this, amount);
        this.setBalance(deposit);
        return deposit;
    }
}
