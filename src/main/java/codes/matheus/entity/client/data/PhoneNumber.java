package codes.matheus.entity.client.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Objects;

public final class PhoneNumber {

    // Objects

    private final @NotNull String digits;
    private final @NotNull DDD ddd;
    private final @NotNull String number;

    // Constructor

    public PhoneNumber(@NotNull DDD ddd, @NotNull String number) {
        @NotNull String numberCleaned = number.replaceAll("\\D", "");

        if (numberCleaned.length() != 9 || numberCleaned.charAt(0) != '9') {
            throw new IllegalArgumentException("The phone number is invalid");
        }

        this.ddd = ddd;
        this.number = numberCleaned;
        this.digits = format(ddd, numberCleaned);
    }

    // Getters

    public @NotNull String getDigits() {
        return digits;
    }

    public @NotNull DDD getDdd() {
        return ddd;
    }

    public @NotNull String getNumber() {
        return number;
    }

    // Method

    private @NotNull String format(@NotNull DDD ddd, @NotNull String number) {
        @NotNull StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            builder.append(number.charAt(i));
        }
        builder.append("-");
        for (int i = 5; i < 9; i++) {
            builder.append(number.charAt(i));
        }

        return "(" + ddd.getCode() + ") " + builder;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(digits, that.digits);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(digits);
    }

    @Override
    public String toString() {
        return digits;
    }

    // Classes

    public enum DDD {
        SP_11(State.SAO_PAULO, 11),
        SP_12(State.SAO_PAULO, 12),
        SP_13(State.SAO_PAULO, 13),
        SP_14(State.SAO_PAULO, 14),
        SP_15(State.SAO_PAULO, 15),
        SP_16(State.SAO_PAULO, 16),
        SP_17(State.SAO_PAULO, 17),
        SP_18(State.SAO_PAULO, 18),
        SP_19(State.SAO_PAULO, 19),

        RJ_21(State.RIO_DE_JANEIRO, 21),
        RJ_22(State.RIO_DE_JANEIRO, 22),
        RJ_24(State.RIO_DE_JANEIRO, 24),

        ES_27(State.ESPIRITO_SANTO, 27),
        ES_28(State.ESPIRITO_SANTO, 28),

        MG_31(State.MINAS_GERAIS, 31),
        MG_32(State.MINAS_GERAIS, 32),
        MG_33(State.MINAS_GERAIS, 33),
        MG_34(State.MINAS_GERAIS, 34),
        MG_35(State.MINAS_GERAIS, 35),
        MG_37(State.MINAS_GERAIS, 37),
        MG_38(State.MINAS_GERAIS, 38),

        PR_41(State.PARANA, 41),
        PR_42(State.PARANA, 42),
        PR_43(State.PARANA, 43),
        PR_44(State.PARANA, 44),
        PR_45(State.PARANA, 45),
        PR_46(State.PARANA, 46),

        SC_47(State.SANTA_CATARINA, 47),
        SC_48(State.SANTA_CATARINA, 48),
        SC_49(State.SANTA_CATARINA, 49),

        RS_51(State.RIO_GRANDE_DO_SUL, 51),
        RS_53(State.RIO_GRANDE_DO_SUL, 53),
        RS_54(State.RIO_GRANDE_DO_SUL, 54),
        RS_55(State.RIO_GRANDE_DO_SUL, 55),

        DF_61(State.DISTRITO_FEDERAL, 61),

        GO_62(State.GOIAS, 62),
        GO_64(State.GOIAS, 64),

        TO_63(State.TOCANTINS, 63),

        MT_65(State.MATO_GROSSO, 65),
        MT_66(State.MATO_GROSSO, 66),

        MS_67(State.MATO_GROSSO_DO_SUL, 67),

        AC_68(State.ACRE, 68),

        RO_69(State.RONDONIA, 69),

        BA_71(State.BAHIA, 71),
        BA_73(State.BAHIA, 73),
        BA_74(State.BAHIA, 74),
        BA_75(State.BAHIA, 75),
        BA_77(State.BAHIA, 77),

        SE_79(State.SERGIPE, 79),

        PE_81(State.PERNAMBUCO, 81),
        PE_87(State.PERNAMBUCO, 87),

        AL_82(State.ALAGOAS, 82),

        PB_83(State.PARAIBA, 83),

        RN_84(State.RIO_GRANDE_DO_NORTE, 84),

        CE_85(State.CEARA, 85),
        CE_88(State.CEARA, 88),

        PI_86(State.PIAUI, 86),
        PI_89(State.PIAUI, 89),

        MA_98(State.MARANHAO, 98),
        MA_99(State.MARANHAO, 99),

        PA_91(State.PARA, 91),
        PA_93(State.PARA, 93),
        PA_94(State.PARA, 94),

        AP_96(State.AMAPA, 96),

        AM_92(State.AMAZONAS, 92),
        AM_97(State.AMAZONAS, 97),

        RR_95(State.RORAIMA, 95);

        private final @NotNull State state;
        @Range(from = 1, to = 99)
        private final int code;

        DDD(@NotNull State state, @Range(from = 1, to = 99) int code) {
            this.state = state;
            this.code = code;
        }

        public @NotNull State getState() {
            return state;
        }

        public int getCode() {
            return code;
        }

        public enum State {
            SAO_PAULO("SP"),
            RIO_DE_JANEIRO("RJ"),
            ESPIRITO_SANTO("ES"),
            MINAS_GERAIS("MG"),
            PARANA("PR"),
            SANTA_CATARINA("SC"),
            RIO_GRANDE_DO_SUL("RS"),
            DISTRITO_FEDERAL("DF"),
            GOIAS("GO"),
            TOCANTINS("TO"),
            MATO_GROSSO("MT"),
            MATO_GROSSO_DO_SUL("MS"),
            ACRE("AC"),
            RONDONIA("RO"),
            BAHIA("BA"),
            SERGIPE("SE"),
            PERNAMBUCO("PE"),
            ALAGOAS("AL"),
            PARAIBA("PB"),
            RIO_GRANDE_DO_NORTE("RN"),
            CEARA("CE"),
            PIAUI("PI"),
            PARA("PA"),
            AMAZONAS("AM"),
            RORAIMA("RR"),
            AMAPA("AP"),
            MARANHAO("MA");

            private final @NotNull String value;

            State(@NotNull String value) {
                this.value = value;
            }

            public @NotNull String getValue() {
                return value;
            }
        }
    }
}
