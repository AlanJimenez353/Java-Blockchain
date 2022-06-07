package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Transaction implements Serializable {
    private int id;

    private HashMap<Usuario, Boolean> validators;
    private int amount;
    private Wallet sender;
    private Wallet recieber;

    public Transaction(Wallet recieber,Wallet sender,List<Usuario>validators,int amount) {
        this.recieber = recieber;
        this.sender=sender;
        this.recieber=recieber;
        this.amount=amount;
        setValidators(validators);
    }

    //GETTERS AND SETTERS
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public HashMap<Usuario, Boolean> getValidators() {
        return validators;
    }
    public void setValidators(List<Usuario> validators) {
        for (Usuario e:validators){
            this.validators.put(e,false);
        }
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public Wallet getSender() {
        return sender;
    }
    public void setSender(Wallet sender) {
        this.sender = sender;
    }
    public Wallet getRecieber() {
        return recieber;
    }
    public void setRecieber(Wallet recieber) {
        this.recieber = recieber;
    }

    //METHODS

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", validators=" + validators +
                ", amount=" + amount +
                ", sender=" + sender +
                ", recieber=" + recieber +
                '}';
    }
}
