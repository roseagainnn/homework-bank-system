package roseagain.bank_system.com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private BankAccount account;

    @BeforeEach // Вынес объявления переменных в отдельный setUp, ибо они мне во всех методах нужны!
    void setUp(){
        User user = new User("AC-18","Nikita");
        account = new BankAccount(user.getId(),user);
    }

    @Test
    void deposit_shouldIncreaseBalance_whenAmountIsPositive() { // Проверяем успешное пополнение deposit
        BigDecimal amount = new BigDecimal("500");
        account.deposit(amount);

        assertEquals(0,new BigDecimal("500").compareTo(account.getBalance()));
    }

    @Test
    void deposit_shouldThrowException_whenAmountIsZero(){ // Проверочка на 0
        assertThrows(IllegalArgumentException.class, () -> account.deposit(BigDecimal.ZERO));

    }

    @Test
    void deposit_shouldThrowException_whenAmountIsNull() { // Проверочка на null
        assertThrows(IllegalArgumentException.class, () -> account.deposit(null));
    }

    @Test
    void deposit_shouldThrowException_whenAmountIsNegative(){  // Проверочка на отрицательное значение!
        assertThrows(IllegalArgumentException.class, () -> account.deposit(new BigDecimal("-1000")));
    }


    @Test
    void withdraw_shouldDecreaseBalance_whenFundsAreSufficient() { // По аналогии идем проверять withdraw
        account.deposit(new BigDecimal("500")); // Для примера накинул через setBalance - опять же метод такой себе, но пока пойдет
        account.withdraw(new BigDecimal("250"));

        assertThrows(NoMoneyException.class, () -> account.withdraw(new BigDecimal("1000"))); // Тутава проверку
        // на исключение запихал сюда же, ибо уже объявил все необходимые new BigDecimal и не хотел копипастить код!
        assertEquals(0,new BigDecimal("250").compareTo(account.getBalance()));
    }

    @Test
    void withdraw_shouldThrowException_whenFundsAreInsufficient(){
        assertThrows(NoMoneyException.class, () -> account.withdraw(new BigDecimal("100")));}

    @Test
    void addTransaction_shouldAddTransaction_whenTransactionIsValid_andThrowException() {
        Transaction transaction = new Transaction("ASCD201",new BigDecimal("1000"),TransactionType.TRANSFER);
        account.addTransaction(transaction);

        assertEquals(1,account.getTransactions().size()); // Смотрим, что транзакция именно 1!
        assertEquals(transaction,account.getTransactions().get(0)); // Смотрим что в 1-м индексе лежит именно транзакция!
        assertEquals(TransactionType.TRANSFER, account.getTransactions().get(0).getType()); // Добавил 1 проверочку
        // на соответствие транзакции типа TRANSFER к собственно трансферу!
    }

    @Test
    void addTransaction_shouldThrowException_whenTransactionIsNull(){
        assertThrows(IllegalArgumentException.class, () -> account.addTransaction(null)); // Тут важно, ибо у нас
        // ArrayList может хранить null, поэтому проверяем именно добавляемый элемент, а не конкретный элемент коллекции
    }
}