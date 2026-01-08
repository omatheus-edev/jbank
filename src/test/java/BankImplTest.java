import codes.matheus.entity.account.Account;
import codes.matheus.entity.account.CheckingAccount;
import codes.matheus.entity.account.SavingsAccount;
import codes.matheus.entity.client.Client;
import codes.matheus.entity.client.ClientImpl;
import codes.matheus.entity.client.data.CPF;
import codes.matheus.entity.client.data.PhoneNumber;
import codes.matheus.entity.transaction.Deposit;
import codes.matheus.entity.transaction.Transaction;
import codes.matheus.entity.transaction.Transference;
import codes.matheus.entity.transaction.Withdrawal;
import codes.matheus.exception.TransactionException;
import codes.matheus.service.BankImpl;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public final class BankImplTest {
    private final @NotNull BankImpl bank = new BankImpl();
    private @NotNull Client client1;
    private @NotNull Client client2;
    private @NotNull SavingsAccount savingsAccount;
    private @NotNull CheckingAccount checkingAccount1;
    private @NotNull CheckingAccount checkingAccount2;

    @BeforeEach
    void setUp() throws TransactionException {
        client1 = new ClientImpl("matheus", new CPF("529.982.247-25"), "matng@email.com", new PhoneNumber(PhoneNumber.DDD.CE_88, "992671907"));
        client2 = new ClientImpl("daniel", new CPF("109.342.073-13"), "dnm_x@email.com", new PhoneNumber(PhoneNumber.DDD.RJ_22, "981844382"));

        savingsAccount = new SavingsAccount(client1);
        checkingAccount1 = new CheckingAccount(client1);
        checkingAccount2 = new CheckingAccount(client2);

        bank.getAccounts().add(savingsAccount);
        bank.getAccounts().add(checkingAccount1);
        bank.getAccounts().add(checkingAccount2);

        bank.deposit(checkingAccount1, 1000.0);
        bank.deposit(savingsAccount, 500.0);
        bank.deposit(checkingAccount2, 700.0);
    }

    @Test
    void deposit() throws TransactionException {
        double initialBalance = checkingAccount1.getBalance();
        @NotNull Deposit deposit = bank.deposit(checkingAccount1, 300);

        assertEquals(initialBalance + 300, deposit.getOrigin().getBalance());
        assertTrue(bank.getTransactions().contains(deposit));
        assertTrue(checkingAccount1.getHistory().contains(deposit));
    }

    @Test
    void withdrawal() throws TransactionException {
        double initialBalance = checkingAccount1.getBalance();

        @NotNull Withdrawal withdrawal = bank.withdrawal(checkingAccount1, 200);


        assertEquals(initialBalance - 200.0, checkingAccount1.getBalance());
        assertTrue(bank.getTransactions().contains(withdrawal));
        assertThrows(TransactionException.class, () ->
                bank.withdrawal(checkingAccount1, initialBalance + 1));
    }

    @Test
    void transference() throws TransactionException {
        double originInitial = checkingAccount1.getBalance();
        double targetInitial = checkingAccount2.getBalance();

        System.out.println(checkingAccount1.getBalance());
        @NotNull Transference transference = bank.transfer(checkingAccount1, checkingAccount2, 100.0);

        assertEquals(originInitial - 100.0, checkingAccount1.getBalance());
        assertEquals(targetInitial + 100.0, checkingAccount2.getBalance());

        assertTrue(bank.getTransactions().contains(transference));
        assertTrue(checkingAccount1.getHistory().contains(transference));
        assertTrue(checkingAccount2.getHistory().contains(transference));
        assertEquals(1, bank.getAllTransference().size());

        assertThrows(TransactionException.class, () -> bank.transfer(checkingAccount1, checkingAccount1, 200));
        assertThrows(TransactionException.class, () -> bank.transfer(checkingAccount1, checkingAccount2, -50.0));
        assertThrows(TransactionException.class, () -> bank.transfer(checkingAccount1, checkingAccount2, checkingAccount1.getBalance() + 1));
    }

    @Test
    void applyYield() {
        double initialBalance = savingsAccount.getBalance();
        double expectedYield = initialBalance * 0.005;

        bank.applyYield(savingsAccount);

        assertEquals(initialBalance + expectedYield, savingsAccount.getBalance(), 0.001);
    }

    @Test
    void applyYieldAll() {
        double savingsInitial = savingsAccount.getBalance();
        double checkingInitial = checkingAccount1.getBalance();

        bank.applyYieldAll();

        assertTrue(savingsAccount.getBalance() > savingsInitial);
        assertEquals(checkingInitial, checkingAccount1.getBalance());
    }

    @Test
    void findAccount() {
        @NotNull Optional<@NotNull Account> optionalAccount = bank.findAccount(checkingAccount1.getId());
        assertTrue(optionalAccount.isPresent());
        assertEquals(checkingAccount1, optionalAccount.get());

        assertTrue(bank.findAccount(999999L).isEmpty());
    }
}
