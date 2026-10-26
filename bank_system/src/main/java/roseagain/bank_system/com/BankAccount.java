package roseagain.bank_system.com;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static roseagain.bank_system.com.BankSystemValidator.validateAmount;
import static roseagain.bank_system.com.BankSystemValidator.validateBankAccountNotMoney;

public class BankAccount {

    private final String accountNumber;
    private BigDecimal balance = BigDecimal.ZERO;

    public User getOwner() {
        return owner;
    }

    private final User owner;
    private final List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return List.copyOf(transactions);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BankAccount(String accountNumber, User owner) {

        if(accountNumber == null || accountNumber.isBlank() || owner == null ){
            throw new IllegalArgumentException("При создании нового банковского аккаунта передан null / пустой объект!");
        }

        this.accountNumber = accountNumber;
        this.owner = owner;
        owner.addAccount(this);
    }

    public void deposit(BigDecimal amount) {

        validateAmount(amount);
        this.balance = balance.add(amount);
        addTransaction(new Transaction("ASD923",amount,TransactionType.DEPOSIT)); // ну и трекаем транзакцию в наш списочек
    }

    public void withdraw(BigDecimal amount) { // тут все в целом по аналогии

        validateAmount(amount);
        validateBankAccountNotMoney(balance,amount);

        this.balance = balance.subtract(amount);
        addTransaction(new Transaction("AS-102",amount,TransactionType.WITHDRAWAL));
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
