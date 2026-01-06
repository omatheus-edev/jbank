package codes.matheus.entity.account;

import codes.matheus.exception.TransactionException;
import org.jetbrains.annotations.Range;

public interface Profitable {
    @Range(from = 0, to = Long.MAX_VALUE)
    double getYieldValue();

    void applyYield() throws TransactionException;
}
