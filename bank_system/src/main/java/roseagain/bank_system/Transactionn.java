package roseagain.bank_system;

import roseagain.bank_system.com.BankAccount;
import roseagain.bank_system.com.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transactionn(String id, BigDecimal amount, LocalDateTime date, BankAccount sourceAccount,
                          BankAccount targetAccount, TransactionType type) {


    // Короче я почекал - record реально имбуля для таких таких классов-контейнеров, но я чуть оставил эту идею и мне
    // Немножко не хватило времени допилить его в проект
    // Но попытку решил оставить!

    public Transactionn{

        if (id == null || id.isBlank() || amount == null || type == null) {
            throw new IllegalArgumentException("При создании транзакции передан null / пустой объект!");
        }

        if (date == null) {
            date = LocalDateTime.now();
        }

    }

    public Transactionn (String id, BigDecimal amount, TransactionType type) {
        this(id, amount, null, null, null, type);
    }
}
