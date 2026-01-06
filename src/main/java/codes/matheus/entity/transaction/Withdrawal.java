package codes.matheus.entity.transaction;

import codes.matheus.entity.account.Account;
import codes.matheus.exception.TransactionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class Withdrawal extends Transaction {
    Withdrawal(@NotNull Account origin, @Range(from = 0, to = Long.MAX_VALUE) double value) {
        super(origin, null, Type.WITHDRAWAL, value);
    }

    @Override
    public double calculate(@NotNull Account account) throws TransactionException {
        if (!account.equals(getOrigin())) {
            throw new TransactionException("You cannot make a withdrawal into another account");
        }

        return account.getBalance() - getValue();
    }
}
