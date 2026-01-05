package codes.matheus.entity.client.data;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class CPF implements CharSequence {

    // Static initializers

    public static @NotNull String format(@NotNull String string) {
        if (!validate(string)) {
            throw new IllegalArgumentException("Cannot format invalid CPF");
        }

        @NotNull String cpf = string.replace(".", "")
                        .replace("-", "")
                        .replace(" ", "")
                        .replace("/", "")
                        .trim();

        return  cpf.substring(0, 3) + "." +
                cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" +
                cpf.substring(9, 11);
    }

    public static boolean validate(@NotNull String string) {
        @NotNull String cpf = string.replace(".", "")
                .replace("-", "")
                .replace(" ", "")
                .replace("/", "")
                .trim();

        if (!cpf.matches("^([0-9]{11})$")) {
            return false;
        }
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        return computeFirstVerifier(cpf) && computeSecondVerifier(cpf);
    }

    private static boolean computeFirstVerifier(@NotNull String cpf) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cpf.charAt(i));
            sum += digit * (10 - i);
        }

        int remainder = (sum * 10) % 11;
        int expected = (remainder == 10 || remainder == 11) ? 0 : remainder;

        int actual = Character.getNumericValue(cpf.charAt(9));
        return expected == actual;
    }

    private static boolean computeSecondVerifier(@NotNull String cpf) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(cpf.charAt(i));
            sum += digit * (11 - i);
        }

        int remainder = sum % 11;
        int expected = (remainder < 2) ? 0 : (11 - remainder);

        int actual = Character.getNumericValue(cpf.charAt(10));
        return expected == actual;
    }

    // Object

    private final @NotNull String string;

    // Constructor

    public CPF(@NotNull String string) {
        if (!validate(string)) {
            throw new IllegalArgumentException("The string Cpf is invalid");
        }
        this.string = string.replace(".", "")
                .replace("-", "")
                .replace(" ", "")
                .replace("/", "")
                .trim();
    }

    // Getter

    public @NotNull String getFormated() {
        return format(string);
    }

    // Implementations

    @Override
    public int length() {
        return string.length();
    }

    @Override
    public char charAt(int i) {
        return string.charAt(i);
    }

    @Override
    public @NotNull CharSequence subSequence(int i, int i1) {
        return string.subSequence(i, i1);
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CPF cpf = (CPF) o;
        return Objects.equals(string, cpf.string);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(string);
    }

    @Override
    public @NotNull String toString() {
        return string;
    }
}
