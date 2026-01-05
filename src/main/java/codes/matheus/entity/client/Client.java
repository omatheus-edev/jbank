package codes.matheus.entity.client;

import codes.matheus.entity.account.Accounts;
import codes.matheus.entity.client.data.CPF;
import codes.matheus.entity.client.data.PhoneNumber;
import org.jetbrains.annotations.NotNull;

public interface Client {
    @NotNull String getName();

    @NotNull CPF getCpf();

    @NotNull String getEmail();

    @NotNull PhoneNumber getNumber();

    @NotNull Accounts getAccounts();
}
