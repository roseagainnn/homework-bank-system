package roseagain.bank_system.com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest { // Самый веселый класс с тестами))

    private BankService service;

    @BeforeEach // Вынес сервис в отдельный сетап для удобства
    void setUp() {
        service = new BankService();
    }

    @Test
    void createAccount_WhenAccountIsValid() { // Ну и погнал тестить методы
        User user = new User("Nikita");

        service.createAccount(user);
        assertEquals(1,user.getAccounts().size());

    }

    @Test
    void createAccount_shouldThrowException_whenAccountIsNull(){
        assertThrows(IllegalArgumentException.class, () -> service.createAccount(null));
    }


    @Test
    void transfer_shouldTransferMoney_whenInputIsValid(){ // Тут чисто всю логику описываю перевода, если деньги есть

        User user = new User("Nikita");
        User user1 = new User("Anton");

        BankAccount source = new BankAccount(user);
        BankAccount target = new BankAccount(user1);

        source.setBalance(new BigDecimal("500"));
        target.setBalance(new BigDecimal("100"));

        service.transfer(source,target,new BigDecimal("300"));

        assertEquals(0, source.getBalance().compareTo(new BigDecimal("200"))); // Ну и что ожидаю получить после перевода
        assertEquals(0, target.getBalance().compareTo(new BigDecimal("400")));

        assertEquals(1, source.getTransactions().size()); // Тут допом проверяю, что транзакции действительно прошли
        assertEquals(1, target.getTransactions().size());

    }

    @Test
    void transfer_shouldThrowException_whenFundsAreInsufficient(){
        User user = new User("Nikita");
        User user1 = new User("Anton");

        BankAccount source = new BankAccount(user);
        BankAccount target = new BankAccount(user1);

        source.setBalance(new BigDecimal("500"));
        target.setBalance(new BigDecimal("100"));


        assertThrows(IllegalArgumentException.class,() -> service.transfer(source,target,new BigDecimal("600")));
        // Ловлю ошибочку отрицательного перевода!
    }

    @Test
    void transfer_shouldThrowException_whenAccountsAreSame(){
        User user = new User("Nikita");

        BankAccount source = new BankAccount(user);
        BankAccount target = source;
        source.setBalance(new BigDecimal("500"));

        assertThrows(IllegalArgumentException.class,() -> service.transfer(source,target,new BigDecimal("500")));
        // А тут ловлю ошибочку, чтобы счета не были одинаковые!!

    }

    // Дальше проверяю null-кейсы, amount 0 , amount отрицательные значения!

    @Test
    void transfer_shouldThrowException_whenAmountIsNull(){
        User user = new User("Nikita");
        User user1 = new User("Anton");

        BankAccount source = new BankAccount(user);
        BankAccount target = new BankAccount(user1);

        assertThrows(IllegalArgumentException.class,() -> service.transfer(source,target,null));

    }

    @Test
    void transfer_shouldThrowException_whenAmountIsZero(){
        User user = new User("Nikita");
        User user1 = new User("Anton");

        BankAccount source = new BankAccount(user);
        BankAccount target = new BankAccount(user1);

        assertThrows(IllegalArgumentException.class,() -> service.transfer(source,target,BigDecimal.ZERO));

    }

    @Test
    void transfer_shouldThrowException_whenAmountIsNegative(){
        User user = new User("Nikita");
        User user1 = new User("Anton");

        BankAccount source = new BankAccount(user);
        BankAccount target = new BankAccount(user1);

        assertThrows(IllegalArgumentException.class,() -> service.transfer(source,target,new BigDecimal("-100")));
        // Взял за проверку на негатив -100 чисто для примера, чтобы с параметризованными тестами не возится!

    }

    // Ну опять же - дальше пытаюсь передать null счета

    @Test
    void transfer_shouldThrowException_whenSourceIsNull(){
        User user1 = new User("Anton");

        BankAccount target = new BankAccount(user1);

        assertThrows(IllegalArgumentException.class,() -> service.transfer(null,target,new BigDecimal("300")));
    }

    @Test
    void transfer_shouldThrowException_whenTargetIsNull(){
        User user = new User("Nikita");

        BankAccount source = new BankAccount(user);

        assertThrows(IllegalArgumentException.class,() -> service.transfer(source,null,new BigDecimal("300")));
    }


    @Test
    void getTransactionHistory_shouldReturnTransactionHistory_whenAccountIsValid(){ // Проверочка, записывается ли транзакция

        User user = new User("Nikita");
        BankAccount account = new BankAccount(user);
        account.deposit(new BigDecimal("1000"));

        assertEquals(1, service.getTransactionHistory(account).size());
    }

    @Test
    void getTransactionHistory_shouldThrowException_whenAccountIsNull(){
        assertThrows(IllegalArgumentException.class,() -> service.getTransactionHistory(null));

    }

    @Test
    void getTotalBalance_shouldReturnTotalBalance_whenUserHasMultipleAccounts(){ // Ну а здесь посмотрим работает ли баланс корректно

        User user = new User("Nikita");

        BankAccount account1 = new BankAccount(user);
        BankAccount account2 = new BankAccount(user);

        user.addAccount(account1);
        user.addAccount(account2);

        account1.deposit(new BigDecimal("1000"));
        account2.deposit(new BigDecimal("1000"));


        assertEquals(0,service.getTotalBalance(user).compareTo(new BigDecimal("2000")));

    }

    @Test
    void getTotalBalance_shouldReturnZero_whenUserHasNoAccounts (){ // Возвращаем ноль если нет счетов
        User user = new User("Nikita");
        assertEquals(BigDecimal.ZERO,service.getTotalBalance(user));
    }

    @Test // ну и на null user проверочка!
    void getTotalBalance_shouldThrowException_whenUserIsNull(){
        assertThrows(IllegalArgumentException.class, () -> service.getTotalBalance(null));

    }
}

// Полюбил я тесты писать короче)) На самом деле, в этом классе я даже с нейроночкой немножко пописал, в плане я ставил задачу ей:
// '' Вот у меня есть такой метод, дай список основных тест-кейсов " - она мне давала название и дальше я шел ручками писать
// Ну и если были вопросы - гуглил / спрашивал ее норм или нет, но башкой своей думал кнчн