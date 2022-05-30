package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Sistema {

    private List<Usuario>listaUsers=new ArrayList<>();


///MANEJO ARCHIVOS

    private String USER_PATH;
    private String USUARIOS_PATH;
    private String TRANSACTIONS_TO_VALIDATE_PATH;


    public Sistema() {

        setPaths();
        this.listaUsers=cargarUsuariosDeArchivo();
    }


    public void primero() throws IOException {
        Usuario uno=new Usuario("Alan","alan@gmail","password","41079103");
        Usuario dos=new Usuario("Nico","nico@gmail","password2","31079103");
        Usuario tres=new Usuario("Santi","santi@gmail","password3","41459103");

        AgregarUsuarioAarchivo(uno);
        AgregarUsuarioAarchivo(dos);
        AgregarUsuarioAarchivo(tres);
        EliminarUsuarioArchivo(tres);


    }

    //  Setea los paths de las carpetas donde se guardara el archivo de Usuarios
    private void setPaths()    {
        this.USER_PATH=System.getProperty("user.dir");
        this.USUARIOS_PATH=""+USER_PATH+"\\users";
        this.TRANSACTIONS_TO_VALIDATE_PATH=""+USER_PATH+"\\transactionsToValidate";
    }


    //MANEJO ARCHIVO USUARIOS
    //Agregar Validaciones
    public ArrayList<Usuario> cargarUsuariosDeArchivo(){
        ObjectMapper mapper=new ObjectMapper();
        ArrayList<Usuario>lista=new ArrayList<>();
        File file=new File(USUARIOS_PATH);

        if(file.isDirectory())
        {
            System.out.println("asd");
            File files[]=file.listFiles();
            for(int i=0;i<files.length;i++)
            {
                try {
                    Usuario p=mapper.readValue(files[i],Usuario.class);
                    lista.add(p);
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return lista;
    }
    public void AgregarUsuarioAarchivo(Usuario user) throws IOException {

        File newUser=new File(USUARIOS_PATH+"\\"+user.getDni()+".json");
        ObjectMapper mapper=new ObjectMapper();
        boolean comp=false;
        boolean comp2=false;
        mapper.writeValue(newUser, user);
    }
    private void EliminarUsuarioArchivo(Usuario user){
        ObjectMapper mapper=new ObjectMapper();
        File file=new File(USUARIOS_PATH);
        Usuario s=new Usuario();
        if(file.isDirectory())
        {
            File files[]=file.listFiles();
            for(int i=0;i<files.length;i++)
            {
                try {
                    s=mapper.readValue(files[i],Usuario.class);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                if (s.getDni().compareTo(user.getDni())==0)
                {
                    files[i].delete();
                }
            }
        }

    }

    public void mostrarListaUsuarios(){
        for (Usuario e : listaUsers){
            System.out.println(e.toString());
            System.out.println("\n");
        }
    }

    //MANEJO ARCHIVO Transacciones
    //AgregarValidaciones

    public void agregarTransaccionArchivo(Transaction transaction) throws IOException {
        File newTransaction=new File(TRANSACTIONS_TO_VALIDATE_PATH+"\\"+transaction.getId()+".json");
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(newTransaction, transaction);
    }


//----------------Validacion ingreso por teclado--------


    public String ingresarEmail() {
        String emailNuevo;
        boolean inspector;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("Email: ");
            emailNuevo = scan.nextLine();
            inspector = validEmail(emailNuevo);
        } while (!inspector);

        return emailNuevo;
    }

    public String ingresarPassword() {
        String password;
        boolean inspector;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println(" Password : ");
            password = scan.nextLine();
            inspector = validPassword(password);
        } while (!inspector);

        return password;
    }

    private boolean validPassword(String password) {
        final int MAX = 8;
        final int MIN_Uppercase = 1;
        final int MIN_Lowercase = 1;
        final int NUM_Digits = 0;
        int uppercaseCounter = 0;
        int lowercaseCounter = 0;
        int digitCounter = 0;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c))
                uppercaseCounter++;
            else if (Character.isLowerCase(c))
                lowercaseCounter++;
            else if (Character.isDigit(c))
                digitCounter++;
        }

        if (password.length() >= MAX && uppercaseCounter >= MIN_Uppercase && lowercaseCounter >= MIN_Lowercase && digitCounter >= NUM_Digits) {
            System.out.println("Valid Password");
            return true;
        } else {
            System.out.println(" Su contraseña no contiene lo siguiente:");
            if (password.length() < MAX)
                System.out.println(" [ Al menos 8 carácteres ]");
            if (lowercaseCounter < MIN_Lowercase)
                System.out.println(" [ Letras minúsculas ]");
            if (uppercaseCounter < MIN_Uppercase)
                System.out.println(" [ Letras mayúsculas ]");
            if (digitCounter < NUM_Digits)
                System.out.println(" [ Número mínimo de dígitos numéricos ]");

            return false;
        }
    }

    private boolean validEmail(String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);

        if (mather.find()) {
            System.out.println("El email ingresado es válido.");
            return true;
        } else {
            System.out.println("El email ingresado es inválido.");
            return false;
        }
    }


}
