package roseagain.bank_system.com;

import java.math.BigDecimal;

public class BankSystemValidator {


    public static void validateAmount(BigDecimal amount){

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Вы ввели некорректное значения для снятия средств!");
        }

    }

    public static void validateBankAccountNotMoney(BigDecimal balance, BigDecimal amount){

        if (balance.compareTo(amount) < 0) {
            throw new NoMoneyException("На счету недостаточно средств!");
        }


    }

}
