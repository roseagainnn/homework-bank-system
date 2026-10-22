package roseagain.bank_system.com;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BankService {

    public void createAccount(User user){ // Тут все понятненько
        if(user == null) {
            throw new IllegalArgumentException("Такого пользователя не существует!");
        }
        user.addAccount(new BankAccount(user));
    }


    public void transfer (BankAccount source, BankAccount target, BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Вы ввели некорректное значения для выполнения транзакции!");
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException("Банковские аккаунты не могут быть null!");
        }

        if(source == target){
            throw new IllegalArgumentException("Нельзя перевести деньги самому себе!");
        }

        if(source.getBalance().compareTo(amount)<0){
            throw new IllegalArgumentException("Недостаточно средств для перевода!");
        }

        LocalDateTime current = LocalDateTime.now();

        // Вообщем наша транзакция в этом примере не атомарна. То есть при какой-то ошибке во время выполнения транзакции
        // у нас может быть списание без зачисления к примеру. Я почекал - есть способы это реализовать на уровне Java Core
        // но там много логики, в условиях вроде не было ничего - решил так оставить, но при желании можно прикрутить

        source.setBalance(source.getBalance().subtract(amount));
        target.setBalance(target.getBalance().add(amount));
        Transaction transaction = new Transaction(amount,current,source,target,"TRANSFER");
        target.addTransaction(transaction);
        source.addTransaction(transaction);


    }

    public List<Transaction> getTransactionHistory(BankAccount account){ // Тут вроде базово все
        if(account == null){
            throw new IllegalArgumentException("Такого аккаунта не существует!");
        }
        return account.getTransactions();
    }

    public BigDecimal getTotalBalance(User user){

        if(user == null){
            throw new IllegalArgumentException("Такого пользователя не существует!");
        }

        BigDecimal current = BigDecimal.ZERO;
        List<BankAccount> bankAccounts = user.getAccounts();

        for (BankAccount account : bankAccounts){
            current =  current.add(account.getBalance());
        }

        return current;

    }

}
