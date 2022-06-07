package com.company;

import java.io.Serializable;

public class Wallet implements Serializable {

    private int utnCoins=100;
    private int money=0;
    private int ownerReference;


    public Wallet(int ownerReference ) {
        this.ownerReference=ownerReference;
    }

    public Wallet(){

    }


    //GETTERS AND SETTERS
    public int getUtnCoins() {
        return utnCoins;
    }
    public void setUtnCoins(int utnCoins) {
        this.utnCoins = utnCoins;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "utnCoins=" + utnCoins +
                ", money=" + money +
                ", ownerReference=" + ownerReference +
                '}';
    }
}
