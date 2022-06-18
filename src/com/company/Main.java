package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Sistema sistem=new Sistema();
        int option=1;
        Scanner scan=new Scanner(System.in);

        while (option != 0){

            System.out.println("MENU \n  1- Realizar Transaccion \n  2- Validar Transacciones \n  3- Mi perfil\n  4- Validar todas las Transacciones");
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
                case 4: sistem.validateAllTransactions();

            }
        }
    }
}


/*
Un menor de edad no puede crear una cuenta.
No se puede buscar por nombre ya que pueden existir varios Usuarios con el mismo nombre.
Cada Usuario tiene un ID , puede ser autoincremental.
 */