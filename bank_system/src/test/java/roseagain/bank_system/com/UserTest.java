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


    @Test
    void addAccount_shouldThrowException_whenAccountBelongsToAnotherUser() { // Добавляем проверочку на случай добавления
        // счет другого пользователя

        // добавляем user1 нового
        User user1 = new User("AO2","Oleg");
        BankAccount account1 = new BankAccount("AX22",user1);

        // пытаемся прокинуть этот счет в юзера из сетапа
        assertThrows(IllegalArgumentException.class, () -> user.addAccount(account1));
    }


    @Test
    void addAccount_shouldThrowException_whenAccountAlreadyAdded() {// Проверочка на повторное добавление того же объекта в счет

        user.addAccount(account);
        assertThrows(IllegalArgumentException.class, () -> user.addAccount(account)); // пытаемсмя этого же пользователя
        // добавить в этот же счет

        assertEquals(1,user.getAccounts().size()); // ну смотрим, что пользователь не пришел и счет из сетапа все еще
        // имеет 1 элемент

    }




        @Test
    void addAccount_shouldAddAccount_WhenAccountIsValid() { // Проверяем валидность добавления - тут оч похоже на проверку из BankAccountTest
        user.addAccount(account);

        assertEquals(1,user.getAccounts().size());
        assertEquals(account,user.getAccounts().get(0));
    }

    @Test
    void addAccount_shouldThrowException_whenAccountIsNull(){
        assertThrows(IllegalArgumentException.class, () -> user.addAccount(null));

    }

    @Test
    void shouldReturnUnmodifiableList() { // Проверяем идемпотентность нашего списка ( ибо он ListOf в User )
        user.addAccount(account);

        List<BankAccount> accounts = user.getAccounts(); // Передаем этот счет в список
        assertEquals(1,accounts.size()); // Проверяем, что в списке есть 1 элемент ( этот самый )

        User newUser = new User("OSAS","Oleg");
        assertThrows(UnsupportedOperationException.class, () -> accounts.add(new BankAccount("AS-12",newUser)));
        // Пытаемся добавить новый счет в этот список - получаем ошибку

    }
}