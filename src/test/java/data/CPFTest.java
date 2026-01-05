package data;

import codes.matheus.entity.account.data.CPF;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class CPFTest {
    private CPFTest() {
    }

    @Test
    void validateCPFTest() {
        @NotNull String cpf = "52998224725";
        @NotNull String cpfFormated = "529.982.247-25";

        assertTrue(CPF.validate(cpf));
        assertTrue(CPF.validate(cpfFormated));

        assertFalse(CPF.validate("11111111111"));
        assertFalse(CPF.validate("52998224726"));
        assertFalse(CPF.validate("529982247257"));
    }

    @Test
    void instanceCPFTest() {
        @NotNull CPF cpf = new CPF("529.982.247-25");
        @NotNull CPF cpf1 = new CPF("52998224725");
        @NotNull CPF cpf2 = new CPF("529.982.247-25");

        assertEquals("529.982.247-25", cpf.getFormated());
        assertEquals("52998224725", cpf.toString());
        assertEquals("529.982.247-25", CPF.format("52998224725"));
        assertThrows(IllegalArgumentException.class, () -> CPF.format("11111111111"));
        assertEquals(cpf1, cpf2);
        assertEquals(cpf1.hashCode(), cpf2.hashCode());
    }
}
