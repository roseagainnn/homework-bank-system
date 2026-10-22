package roseagain.bank_system.com;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User(String name) {
       this.id = UUID.randomUUID().toString();
       this.name = name;
    }

    private String id;
    private String name;
    private List<BankAccount> bankAccounts = new ArrayList<>();

    public void addAccount(BankAccount account){

        if(account == null){
            throw new IllegalArgumentException("Такого аккаунта не существует!");
        }

        bankAccounts.add(account);
    }

    public List<BankAccount> getAccounts(){
        return List.copyOf(bankAccounts);
    }

}
