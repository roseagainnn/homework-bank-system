package roseagain.bank_system.com;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    public Transaction(BigDecimal amount, LocalDateTime date, BankAccount sourceAccount, BankAccount targetAccount, String type) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.date = date;
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.type = type;
    }
// Создал 2 конструктора для создания транзакций ( для перевода, снятия и пополнения )
    public Transaction(BigDecimal amount, LocalDateTime date, String type) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.date = date;
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

    private String id;
    private BigDecimal amount;
    private LocalDateTime date;
    private BankAccount sourceAccount;
    private BankAccount targetAccount;
    private String type;

}
