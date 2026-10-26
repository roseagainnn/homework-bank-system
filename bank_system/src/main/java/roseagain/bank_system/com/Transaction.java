package roseagain.bank_system.com;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    public Transaction(String id,BigDecimal amount,BankAccount sourceAccount, BankAccount targetAccount, TransactionType type) {

        if(id == null || id.isBlank() || amount == null || sourceAccount == null || targetAccount == null || type == null ){
            throw  new IllegalArgumentException("При создании транзакции передан null / пустой объект!");
        }

        this.id = id;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.type = type;
    }
// Создал 2 конструктора для создания транзакций ( для перевода, снятия и пополнения )
    public Transaction(String id, BigDecimal amount, TransactionType type) {

        if(id == null || id.isBlank() || amount == null || type == null){
            throw new IllegalArgumentException("При создании транзакции передан null / пустой объект");
        }

        this.id = id;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.type = type;
    }

    @Override
    public String toString() { // Ну чисто чтоб в мейне было отображение
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", sourceAccount=" + sourceAccount +
                ", targetAccount=" + targetAccount +
                ", type='" + type + '\'' +
                '}';
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public BankAccount getSourceAccount() {
        return sourceAccount;
    }

    public BankAccount getTargetAccount() {
        return targetAccount;
    }

    private final String id;
    private final BigDecimal amount;
    private final LocalDateTime date;
    private BankAccount sourceAccount;
    private BankAccount targetAccount;

    public TransactionType getType() {
        return type;
    }

    private final TransactionType type;

}
