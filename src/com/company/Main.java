package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Sistema sistem = new Sistema();
        int option = 1;
        Scanner scan = new Scanner(System.in);
        boolean confirmacion = false;


        while (option != 0) {
            sistem.opcionesMenuLogin();
            option = scan.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("[LOGIN]");
                        do {
                            confirmacion = sistem.login();
                            if (confirmacion == false) {
                                System.out.println("¿Usted quiere intentar conectarse otra vez?");
                                System.out.println("1:Si.");
                                System.out.println("2:No.");
                                switch (sistem.ingresarOpcion()) {
                                    case 1: {
                                        confirmacion = false;
                                    }
                                    case 2: {
                                        confirmacion = true;
                                        sistem.setActiveUser(null);
                                        break;
                                    }
                                    default: {
                                        System.out.println("[Opcion incorrecta, rehubicado al menu de login]");
                                        confirmacion = false;
                                    }
                                }
                            }
                        } while (confirmacion == false);
                    break;
                }
                case 2: {
                    System.out.println("[REGIST]");
                    sistem.RegistroUsuario();
                    break;
                }


            }
        }
    }
}


              /*
                case 3: {

                    switch (option) {
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

                        default:
                            System.out.println("Opcion incorrecta");
                            break;
                    }
                    break;
                }


            switch (option){
                case 1:
                    sistem.userOperationsShowProfile();
                    break;
                case 2:
                    sistem.userOperationsValidateTransactions();
                    break;
                case 3:
                    sistem.userOperationMakeTransaction();
                    break;
                case 4:
                    sistem.validateAllTransactions();
                    break;
                case 5:
                    sistem.userOperationsShowTransactionHistory();

            }
        }
    }
                     */



