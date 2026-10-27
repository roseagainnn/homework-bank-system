package roseagain.bank_system.com;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

   BankAccount(String accountNumber, User owner) {

        if(accountNumber == null || accountNumber.isBlank() || owner == null ){
            throw new IllegalArgumentException("При создании нового банковского аккаунта передан null / пустой объект!");
        }

        this.accountNumber = accountNumber;
        this.owner = owner;
    }

    void debit(BigDecimal amount){ // Два новых метода! Ну собственно как ты и сказал - debit + credit сделал и вынес
        // валидацию в них!) Очень компактно и элегантно!)

        validateAmount(amount);
        validateBankAccountNotMoney(balance,amount);

        this.balance = balance.subtract(amount);

    }

    void credit(BigDecimal amount){

        validateAmount(amount);
        this.balance = balance.add(amount);
    }


    public void deposit(BigDecimal amount) {

        this.credit(amount);
        addTransaction(new Transaction(amount,TransactionType.DEPOSIT));
    }

    public void withdraw(BigDecimal amount) { // тут все в целом по аналогии
        this.debit(amount);
        addTransaction(new Transaction(amount,TransactionType.WITHDRAWAL));
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
