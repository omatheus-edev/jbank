package codes.matheus.entity.account;

import codes.matheus.entity.account.data.CPF;
import codes.matheus.entity.account.data.PhoneNumber;
import org.jetbrains.annotations.NotNull;

public interface Client {
    @NotNull String getName();

    @NotNull CPF getCpf();

    @NotNull String getEmail();

    @NotNull PhoneNumber getNumber();

    @NotNull Accounts getAccounts();
}
