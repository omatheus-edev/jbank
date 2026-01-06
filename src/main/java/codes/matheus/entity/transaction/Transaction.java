package codes.matheus.entity.transaction;

import codes.matheus.entity.account.Account;
import codes.matheus.exception.TransactionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public abstract sealed class Transaction permits Deposit, Withdrawal, Transference {

    @SuppressWarnings("unchecked")
    public  static <T extends Transaction> @NotNull T create(@NotNull Account origin, @Nullable Account target, @NotNull Type type, @Range(from = 0, to = Long.MAX_VALUE) double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Transaction value must be greater than zero");
        }
        return (T) type.create(origin, target, value);
    }

    public static @NotNull Withdrawal withdrawal(@NotNull Account origin, @Range(from = 0, to = Long.MAX_VALUE) double value) {
        return create(origin, null, Type.WITHDRAWAL, value);
    }

    public static @NotNull Deposit deposit(@NotNull Account origin, @Range(from = 0, to = Long.MAX_VALUE) double value) {
        return create(origin, null, Type.DEPOSIT, value);
    }

    public static @NotNull Transference transference(@NotNull Account origin, @NotNull Account target, @Range(from = 0, to = Long.MAX_VALUE) double value) {
        return create(origin, target, Type.TRANSFERENCE, value);
    }

    @Range(from = 0, to = Long.MAX_VALUE)
    private final long id;
    private final @NotNull Account origin;
    private final @Nullable Account target;
    private final @NotNull Type type;
    private final @NotNull OffsetDateTime time;
    @Range(from = 0, to = Long.MAX_VALUE)
    private final double value;

    Transaction(@NotNull Account origin, @Nullable Account target, @NotNull Type type, @Range(from = 0, to = Long.MAX_VALUE) double value) {
        this.id = GeneratedID.getId();
        this.origin = origin;
        this.target = target;
        this.type = type;
        this.value = value;
        this.time = OffsetDateTime.now();
    }

    public @NotNull Account getOrigin() {
        return origin;
    }

    @Range(from = 0, to = Long.MAX_VALUE)
    public long getId() {
        return id;
    }

    public @Nullable Account getTarget() {
        return target;
    }

    public @NotNull Type getType() {
        return type;
    }

    public @NotNull OffsetDateTime getTime() {
        return time;
    }

    @Range(from = 0, to = Long.MAX_VALUE)
    public double getValue() {
        return value;
    }

    public abstract double calculate(@NotNull Account account) throws TransactionException;

    @Override
    public String toString() {
        return "Transaction{" +
                ", origin=" + origin +
                ", target=" + target +
                ", type=" + type +
                ", time=" + time +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public enum Type {
        DEPOSIT {
            @Override
            @NotNull Transaction create(@NotNull Account origin, @Nullable Account target, @Range(from = 0, to = Long.MAX_VALUE) double value) {
                return new Deposit(origin, value);
            }
        },
        WITHDRAWAL {
            @Override
            @NotNull Transaction create(@NotNull Account origin, @Nullable Account target, @Range(from = 0, to = Long.MAX_VALUE) double value) {
                return new Withdrawal(origin, value);
            }
        },
        TRANSFERENCE {
            @Override
            @NotNull Transaction create(@NotNull Account origin, @Nullable Account target, @Range(from = 0, to = Long.MAX_VALUE) double value) {
                return new Transference(origin, Objects.requireNonNull(target), value);
            }
        };

        abstract @NotNull Transaction create(@NotNull Account origin, @Nullable Account target, double val);
    }

    private static final class GeneratedID {
        private static final @NotNull AtomicLong id = new AtomicLong(0);

        @Range(from = 0, to = Long.MAX_VALUE)
        private static long getId() {
            return id.incrementAndGet();
        }
    }
}
