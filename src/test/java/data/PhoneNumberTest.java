package data;

import codes.matheus.entity.client.data.PhoneNumber;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class PhoneNumberTest {
    private final @NotNull PhoneNumber.DDD dddSP = PhoneNumber.DDD.SP_12;
    private final @NotNull PhoneNumber.DDD dddRJ = PhoneNumber.DDD.RJ_21;

    @Test
    void validatePhoneNumberTest() {
        @NotNull PhoneNumber phone = new PhoneNumber(dddRJ, "987654321");
        @NotNull PhoneNumber phone1 = new PhoneNumber(dddSP, "98877 6655 ");

        assertEquals(dddRJ, phone.getDdd());
        assertEquals("987654321", phone.getNumber());
        assertEquals("(21) 98765-4321", phone.getDigits());
        assertEquals("(21) 98765-4321", phone.toString());

        assertEquals("988776655", phone1.getNumber());
        assertEquals("(12) 98877-6655", phone1.getDigits());
    }
}
