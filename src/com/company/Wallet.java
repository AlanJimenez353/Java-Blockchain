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
=======
import java.math.BigDecimal;
import java.util.UUID;

public class Wallet {


    private UUID codigoSeguridad;
    private BigDecimal utnCoin=new BigDecimal(100);
    // Se usa BigDecimal para lo que son operaciones financieras.
    // Es algo a implementar en proyectos relacionados a cuestiones financieras o blockchain.
    // Garantiza más precision, asi disminuimos la posibilidad de perder decimales.

    ///Basic
    public Wallet(UUID codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    ///Auto
    public Wallet(){
        this.codigoSeguridad=UUID.randomUUID();
    }

    public UUID getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(UUID codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public BigDecimal getUtnCoin() {
        return utnCoin;
    }

    public void setUtnCoin(BigDecimal utnCoin) {
        this.utnCoin = utnCoin;
    }


    @Override
    public String toString() {
        return "Wallet{" +
                "codigoSeguridad=" + codigoSeguridad +
                ", utnCoin=" + utnCoin +
                '}';
    }

    public boolean ingreso(Wallet receptor,BigDecimal ingreso) {
        boolean ingresoCorrecto = true;
        try {
            if (ingreso.intValue() > 0) {
                ingresoCorrecto = false;
            } else {
                receptor.setUtnCoin(receptor.getUtnCoin().add(ingreso));
            }
        }catch (ArithmeticException arithmeticException)
        {
            System.out.println("Error aritmetico en el ingreso de utnCoin$");
        }
        return ingresoCorrecto;
    }

    //método reintegro
    public boolean reintegro(Wallet emisor,BigDecimal n) {
        boolean reintegroCorrecto = true;
        try {
            if (n.intValue() < 0) {
                reintegroCorrecto = false;
            } else if (emisor.getUtnCoin().equals(n)) {
                emisor.setUtnCoin(emisor.getUtnCoin().subtract(n));
            } else {
                reintegroCorrecto = false;
            }
        }catch(ArithmeticException arithmeticException)
        {
            System.out.println("Error aritmetico en el ingreso de utnCoin$");
        }
        return reintegroCorrecto;
    }

    public boolean transferencia(Wallet emisor, Wallet receptor, BigDecimal n) {
        boolean correcto = true;
        try{
            if (n.intValue() < 0) {
                correcto = false;
            } else if (emisor.getUtnCoin().intValue()>=n.intValue()) {
                emisor.reintegro(emisor,n);
                emisor.ingreso(receptor,n);
            } else {
                correcto = false;
            }
        }catch (ArithmeticException arithmeticException)
        {
            System.out.println("Error aritmetico en el ingreso de utnCoin$");
        }
        return correcto;
    }


>>>>>>> 3e59c33cdcbbb15d277b9428130ccd42ff3137d6
}




