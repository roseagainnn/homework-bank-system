package roseagain.bank_system.com;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankAccount {

    private String accountNumber;
    private BigDecimal balance = BigDecimal.ZERO;
    private User owner;
    private List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return List.copyOf(transactions);
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance; // Здесь решил обойтись пока что setBalance - это чуть ломает инкапсуляцию
        // в идеале ваще какой-нибудь метод иметь, чтобы через отдельный метод обращаться к балансу. Но для этой задачки решил оставить
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BankAccount(User owner) {
        this.accountNumber = UUID.randomUUID().toString(); // В этой задачке решил использовать этот генератор для уникальности
        // наших идентификаторов
        this.owner = owner;
    }

    public void deposit(BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Вы ввели некорректное значения для внесения средств!");
        }

        // Отдельно отмечу, что для этой задачки я всю валидацию писал чисто в формате if(... проверка ... )
        // Не стал заморачиваться с отдельным сервисом и писал чисто в методах, но думаю еще дойдем до этого

        LocalDateTime current = LocalDateTime.now(); // Чтобы как-то трекать время выполнения транзакции
        // Я решил взять метод.now - ну вроде логишно
        this.balance = balance.add(amount);
        addTransaction(new Transaction(amount,current,"DEPOSIT")); // ну и трекаем транзакцию в наш списочек
    }

    public void withdraw(BigDecimal amount) { // тут все в целом по аналогии

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Вы ввели некорректное значения для снятия средств!");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("На счету недостаточно средств!");
        }

        LocalDateTime current = LocalDateTime.now();
        this.balance = balance.subtract(amount);
        addTransaction(new Transaction(amount,current,"WITHDRAWAL"));
    }

    public BigDecimal getBalance(){
        return balance;
    }

    public void addTransaction(Transaction transaction){
        if(transaction == null){
            throw new IllegalArgumentException("Транзакция не может быть null! ");
        }
        transactions.add(transaction);
    }

}
