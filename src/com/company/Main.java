package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Sistema sistem=new Sistema();
        sistem.MenuLogin();
    }
}


/*
Un menor de edad no puede crear una cuenta.
No se puede buscar por nombre ya que pueden existir varios Usuarios con el mismo nombre.
Cada Usuario tiene un ID , puede ser autoincremental.
 */