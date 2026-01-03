package codes.matheus.entity.account;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public interface Client {
    @NotNull String getName();

    @NotNull String getCpf();

    @NotNull String getEmail();

    @Range(from = 0, to = Integer.MAX_VALUE)
    int getNumber();

    @NotNull Accounts getAccounts();
}
