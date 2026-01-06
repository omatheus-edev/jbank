package codes.matheus.entity.transaction;

import codes.matheus.entity.account.Account;
import codes.matheus.exception.TransactionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class Transference extends Transaction {
    Transference(@NotNull Account origin, @NotNull Account target, @Range(from = 0, to = Long.MAX_VALUE) double value) {
        super(origin, target, Type.TRANSFERENCE, value);
    }

    @Override
    public double calculate(@NotNull Account account) throws TransactionException {
        if (account.equals(getOrigin())) {
            return account.getBalance() - getValue();
        } else if (account.equals(getTarget())) {
            return account.getBalance() + getValue();
        }

        throw new TransactionException("This account is unable to make a transfer");
    }
}
