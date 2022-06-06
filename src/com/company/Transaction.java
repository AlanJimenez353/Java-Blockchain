package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transaction implements Serializable {
    private int id;

    private HashMap<Usuario, Boolean> validators;
    private Double amount;
    private Wallet sender;
    private Wallet recieber;
    private ValidationState state;

    public Transaction(Wallet recieber,Wallet sender,HashMap<Usuario, Boolean>validators,Double amount) {
        this.recieber = recieber;
        this.sender=sender;
        this.recieber=recieber;
        this.amount=amount;
        setValidators(validators);
        this.state = ValidationState.PENDING;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Usuario, Boolean> getValidators() {
        return validators;
    }

    public void setValidators(HashMap<Usuario, Boolean> validators) {
        for (Map.Entry<Usuario, Boolean> entrada  : validators.entrySet()){
            this.validators.put(entrada.getKey(),false);
        }
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    public ValidationState getState() {
        return state;
    }

    public void setState(ValidationState state) {
        this.state = state;
    }

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
