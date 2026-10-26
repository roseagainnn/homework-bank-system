package roseagain.bank_system.com;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    public User(String id,String name) {

        if(id == null || id.isBlank() || name == null || name.isBlank()){
            throw new IllegalArgumentException("При создании нового пользователя передан null / пустой объект!");
        }

       this.id = id;
       this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<BankAccount> getBankAccounts() {
        return List.copyOf(bankAccounts);
    }

    private final String id;
    private String name;
    private final List<BankAccount> bankAccounts = new ArrayList<>();

    public void addAccount(BankAccount account){

        if(account == null){
            throw new IllegalArgumentException("Такого аккаунта не существует!");
        }

        if (bankAccounts.contains(account)){
            throw new IllegalArgumentException("Вы пытаетесь добавить одинаковый аккаунт!");

        }

        if(account.getOwner() == this) {
            bankAccounts.add(account);
        }
    }

    public List<BankAccount> getAccounts(){
        return List.copyOf(bankAccounts);
    }

}
