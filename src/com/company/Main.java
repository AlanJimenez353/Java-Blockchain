package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Sistema sistem=new Sistema();
        int option=1;
        Scanner scan=new Scanner(System.in);

        while (option != 0){

            System.out.println("MENU \n  1- Realizar Transaccion \n  2- Validar Transacciones \n  3- Mi perfil\n  4- Validar todas las Transacciones \n  5- Historial de transacciones");
            option=scan.nextInt();
            switch (option){
                case 1:
                    sistem.userOperationMakeTransaction();
                    break;
                case 2:
                    sistem.userOperationsValidateTransactions();
                    break;
                case 3:
                    sistem.userOperationsShowProfile();
                    break;
                case 4:
                    sistem.validateAllTransactions();
                    break;
                case 5:
                    sistem.userOperationsShowTransactionHistory();

            }
        }
    }
}

