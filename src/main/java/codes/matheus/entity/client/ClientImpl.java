package codes.matheus.entity.client;

import codes.matheus.entity.account.Accounts;
import codes.matheus.entity.client.data.CPF;
import codes.matheus.entity.client.data.PhoneNumber;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class ClientImpl implements Client {
    private final @NotNull String name;
    private final @NotNull CPF cpf;
    private final @NotNull String email;
    private final @NotNull PhoneNumber number;
    private final @NotNull Accounts accounts;

    public ClientImpl(@NotNull String name, @NotNull CPF cpf, @NotNull String email, @NotNull PhoneNumber number) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.number = number;
        this.accounts = new Accounts();
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull CPF getCpf() {
        return cpf;
    }

    @Override
    public @NotNull String getEmail() {
        return email;
    }

    @Override
    public @NotNull PhoneNumber getNumber() {
        return number;
    }

    @Override
    public @NotNull Accounts getAccounts() {
        return accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClientImpl client = (ClientImpl) o;
        return Objects.equals(cpf, client.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
