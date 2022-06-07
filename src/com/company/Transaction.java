package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
<<<<<<< HEAD
import java.util.Random;
=======
import java.util.Map;
>>>>>>> 3e59c33cdcbbb15d277b9428130ccd42ff3137d6

public class Transaction implements Serializable {
    private int id;

    private HashMap<Usuario, Boolean> validators;
    private int amount;
    private Wallet sender;
    private Wallet recieber;
    private ValidationState state;

<<<<<<< HEAD
    public Transaction(Wallet recieber,Wallet sender,List<Usuario>validators,int amount) {
=======
    public Transaction(Wallet recieber,Wallet sender,HashMap<Usuario, Boolean>validators,Double amount) {
>>>>>>> 3e59c33cdcbbb15d277b9428130ccd42ff3137d6
        this.recieber = recieber;
        this.sender=sender;
        this.recieber=recieber;
        this.amount=amount;
        setValidators(validators);
        this.state = ValidationState.PENDING;
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
<<<<<<< HEAD
    public void setValidators(List<Usuario> validators) {
        for (Usuario e:validators){
            this.validators.put(e,false);
=======

    public void setValidators(HashMap<Usuario, Boolean> validators) {
        for (Map.Entry<Usuario, Boolean> entrada  : validators.entrySet()){
            this.validators.put(entrada.getKey(),false);
>>>>>>> 3e59c33cdcbbb15d277b9428130ccd42ff3137d6
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

<<<<<<< HEAD
    //METHODS
=======
    public ValidationState getState() {
        return state;
    }

    public void setState(ValidationState state) {
        this.state = state;
    }
>>>>>>> 3e59c33cdcbbb15d277b9428130ccd42ff3137d6

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
