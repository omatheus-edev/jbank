package codes.matheus.entity.account;

import codes.matheus.entity.client.Client;
import codes.matheus.entity.transaction.Deposit;
import codes.matheus.entity.transaction.Transaction;
import codes.matheus.entity.transaction.Withdrawal;
import codes.matheus.exception.TransactionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class SavingsAccount extends Account implements Profitable {
    @Range(from = 0, to = Long.MAX_VALUE)
    private final double YIELD_RATE = 0.005;

    public SavingsAccount(@NotNull Client client) {
        super(client);
    }

    @Override
    public @Range(from = 0, to = Long.MAX_VALUE) double getYieldValue() {
        return YIELD_RATE;
    }

    @Override
    public void applyYield() throws TransactionException {
        double yieldAmount = getBalance() * YIELD_RATE;
        if (yieldAmount > 0) {
            deposit(yieldAmount);
        }
    }

    @Override
    public @NotNull Withdrawal withdraw(@Range(from = 0, to = Long.MAX_VALUE) double amount) throws TransactionException {
        if (amount <= 0 || amount > this.getBalance()) {
            throw new TransactionException("The amount is zero or less than customer balance");
        }
        @NotNull Withdrawal withdrawal = Transaction.withdrawal(this, amount);
        if (getHistory().contains(withdrawal)) {
            throw new TransactionException("This account already has this transaction");
        }
        this.setBalance(withdrawal.calculate(this));
        getHistory().add(withdrawal);
        return withdrawal;
    }

    @Override
    public @NotNull Deposit deposit(@Range(from = 0, to = Long.MAX_VALUE) double amount) throws TransactionException {
        if (amount <= 0) {
            throw new TransactionException("The amount is zero or less than customer balance");
        }
        @NotNull Deposit deposit = Transaction.deposit(this, amount);
        if (getHistory().contains(deposit)) {
            throw new TransactionException("This account already has this transaction");
        }
        this.setBalance(deposit.calculate(this));
        getHistory().add(deposit);
        return deposit;
    }
}
