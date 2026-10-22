package roseagain.bank_system.com;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создаем пользователя
        User user = new User ("John Doe");

        // Создаем сервис
        BankService bankService = new BankService();

        // Создаем счета
        bankService.createAccount(user);
        bankService.createAccount(user);

        // Получаем счета пользователя
        List<BankAccount> accounts = user.getAccounts();
        BankAccount acc1 = accounts.get(0);
        BankAccount acc2 = accounts.get(1);

        // Пополняем первый счет
        acc1.deposit(new BigDecimal("1000"));

        // Переводим средства между счетами
        bankService.transfer(acc1, acc2, new BigDecimal("500"));

        // Выводим балансы
        System.out.println("Balance" +acc1.getAccountNumber() + acc1.getBalance());
        System.out.println("Balance" +acc2.getAccountNumber() + acc2.getBalance());

        // Выводим историю транзакций
        System.out.println("Transaction history for ACC123:");
        bankService.getTransactionHistory(acc1).forEach(System.out::println);
    }
}
