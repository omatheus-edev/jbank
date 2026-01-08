package codes.matheus.entity.account;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public final class Accounts implements Iterable<@NotNull Account> {
    private final @NotNull List<@NotNull Account> accounts = new ArrayList<>();

    public boolean add(@NotNull Account account) {
        return accounts.add(account);
    }

    public boolean remove(@NotNull Account account) {
        return accounts.remove(account);
    }

    public boolean remove(@Range(from = 0, to = Long.MAX_VALUE) long id) {
        return accounts.removeIf(account -> account.getId() == id);
    }

    public @NotNull Stream<@NotNull Account> stream() {
        return accounts.stream();
    }

    @Override
    public @NotNull Iterator<@NotNull Account> iterator() {
        return accounts.iterator();
    }
}
