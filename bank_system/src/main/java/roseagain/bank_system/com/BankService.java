package roseagain.bank_system.com;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BankService {

    public void createAccount(User user, String accountNumber){ // Тут все понятненько


        if(uniqueAccountNumber.contains(accountNumber)){
            throw new IllegalArgumentException("Аккаунт с таким номером аккаунта уже был создан!");
        }

        BankAccount account = new BankAccount(accountNumber,user);
        user.addAccount(account);
        uniqueAccountNumber.add(accountNumber);
    }

    private final Set<String> uniqueAccountNumber = new HashSet<>();


    public void transfer (BankAccount source, BankAccount target, BigDecimal amount) {

        if (source == null || target == null) {
            throw new IllegalArgumentException("Банковские аккаунты не могут быть null!");
        }

        if(source == target){
            throw new IllegalArgumentException("Нельзя перевести деньги самому себе!");
        }

        // Наша транзакция в этом примере не атомарна. То есть при какой-то ошибке во время выполнения транзакции
        // у нас может быть списание без зачисления к примеру. Я почекал - есть способы это реализовать на уровне Java Core
        // но там много логики, в условиях вроде не было ничего - решил так оставить, но при желании можно прикрутить

        source.debit(amount);
        target.credit(amount);
        Transaction transaction = new Transaction(amount,source,target,TransactionType.TRANSFER);
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
