package roseagain.bank_system.com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private BankAccount account;
    private User user;

    @BeforeEach // Здесь по аналогии выносим эти 2 поля для, ибо используем для всех тестов
    void setUp(){
        user = new User("AC-18","Nikita");
        account = new BankAccount(user.getId(),user);
    }


    // в тестах удалил user.addAccount(account), ибо у меня итак он теперь связывается выше в bankAccount!!

    @Test
    void addAccount_shouldAddAccount_WhenAccountIsValid() { // Проверяем валидность добавления - тут оч похоже на проверку из BankAccountTest
        assertEquals(1,user.getAccounts().size());
        assertEquals(account,user.getAccounts().get(0));
    }

    @Test
    void addAccount_shouldThrowException_whenAccountIsNull(){
        assertThrows(IllegalArgumentException.class, () -> user.addAccount(null));

    }

    @Test
    void shouldReturnUnmodifiableList() { // Проверяем идемпотентность нашего списка ( ибо он ListOf в User )


        List<BankAccount> accounts = user.getAccounts(); // Передаем этот счет в список
        assertEquals(1,accounts.size()); // Проверяем, что в списке есть 1 элемент ( этот самый )


        User newUser = new User("OSAS","Oleg");
        assertThrows(UnsupportedOperationException.class, () -> accounts.add(new BankAccount("AS-12",newUser)));
        // Пытаемся добавить новый счет в этот список - получаем ошибку

    }
}