package codes.matheus.exception;

import org.jetbrains.annotations.NotNull;

public final class AccountException extends Exception {
    public AccountException(@NotNull String message) {
        super(message);
    }
}
