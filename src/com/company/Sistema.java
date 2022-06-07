package com.company;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Sistema {

    private List<Usuario>listaUsers=new ArrayList<>();
    private HashMap<String,Usuario>mapaUsuarios=new HashMap<>();


///MANEJO ARCHIVOS

    private String USER_PATH;
    private String USUARIOS_PATH;
    private String TRANSACTIONS_TO_VALIDATE_PATH;


    public Sistema() throws IOException {

        setPaths();
        //this.listaUsers=cargarUsuariosDeArchivo();
        this.mapaUsuarios=cargarMapaUsuariosDeArchivo();
    }

    //GETTERS AND SETTERS
    public HashMap<String, Usuario> getMapaUsuarios() {
        return mapaUsuarios;
    }
    public void setMapaUsuarios(HashMap<String, Usuario> mapaUsuarios) {
        this.mapaUsuarios = mapaUsuarios;
    }
    //  Setea los paths de las carpetas donde se guardara el archivo de Usuarios
    private void setPaths()    {
        this.USER_PATH=System.getProperty("user.dir");
        this.USUARIOS_PATH=""+USER_PATH+"\\users";
        this.TRANSACTIONS_TO_VALIDATE_PATH=""+USER_PATH+"\\transactionsToValidate";
    }



    //METODOS
    public void primero() throws IOException {
        Usuario uno=new Usuario("Alan","alan@gmail","password","41079103");
        Usuario dos=new Usuario("Nico","nico@gmail","password2","31079103");
        Usuario tres=new Usuario("Santi","santi@gmail","password3","41459103");

        AgregarUsuarioAarchivo(uno);
        AgregarUsuarioAarchivo(dos);
        AgregarUsuarioAarchivo(tres);
        EliminarUsuarioArchivo(tres);


    }
    public void segundo() throws IOException {
    Usuario uno=new Usuario("Alan","alan@gmail","password","41079103");
    Usuario dos=new Usuario("Nico","nico@gmail","password2","31079103");
    Usuario tres=new Usuario("Santi","santi@gmail","password3","41459103");

    mapaUsuarios.put(uno.getDni(),uno);
    mapaUsuarios.put(dos.getDni(),dos);
    mapaUsuarios.put(tres.getDni(),tres);
    crearHashMapArchivo(mapaUsuarios);
    deleteUserFromHashMap(uno);
    this.mapaUsuarios=cargarMapaUsuariosDeArchivo();
    System.out.println(mapaUsuarios);


}




    //MANEJO ARCHIVO USUARIOS
    //Agregar Validaciones
    public ArrayList<Usuario> cargarUsuariosDeArchivo(){
        ObjectMapper mapper=new ObjectMapper();
        ArrayList<Usuario>lista=new ArrayList<>();
        File file=new File(USUARIOS_PATH);

        if(file.isDirectory())
        {
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

    //MANEJO HASHMAP USUARIOS

    public void crearHashMapArchivo(HashMap<String,Usuario>map) throws IOException {
        File mapPath=new File(USUARIOS_PATH+"\\HashMapUsuarios"+".json");
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(mapPath, map);
    }
    public HashMap<String,Usuario> cargarMapaUsuariosDeArchivo() throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        File file=new File(USUARIOS_PATH+"\\HashMapUsuarios.json");
        TypeReference<HashMap<String,Usuario>> typeRef
                = new TypeReference<HashMap<String,Usuario>>() {};

        HashMap<String,Usuario> o = mapper.readValue(file, typeRef);
        return o;

    }
    public void addUserToHashMapFile(Usuario a) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        File file=new File(USUARIOS_PATH+"\\HashMapUsuarios.json");
        TypeReference<HashMap<String,Usuario>> typeRef
                = new TypeReference<HashMap<String,Usuario>>() {};
        file.delete();
        this.mapaUsuarios.put(a.getDni(),a);
        crearHashMapArchivo(mapaUsuarios);

    }
    public void deleteUserFromHashMap(Usuario a) throws IOException {
        mapaUsuarios.remove(a.getDni());
        File file=new File(USUARIOS_PATH+"\\HashMapUsuarios.json");
        TypeReference<HashMap<String,Usuario>> typeRef
                = new TypeReference<HashMap<String,Usuario>>() {};
        file.delete();
        crearHashMapArchivo(mapaUsuarios);

    }
    public void mostrarHashMapUsuarios(){
        for (HashMap.Entry<String, Usuario> entry : mapaUsuarios.entrySet()) {
            System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
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
        String emailNuevo="Error";
        boolean inspector;
        Scanner scan = new Scanner(System.in);

        try {
            do {
                System.out.println("Email: ");
                emailNuevo = scan.nextLine();
                inspector = validEmail(emailNuevo);
            } while (!inspector);
        }catch (Exception e)
        {
            System.out.println("Error fatal en el ingreso del email.");
        }

        return emailNuevo;
    }

    public String ingresarPassword() {
        String password="Error";
        boolean inspector;
        Scanner scan = new Scanner(System.in);

        try {
            do {
                System.out.println(" Password : ");
                password = scan.nextLine();
                inspector = validPassword(password);
            } while (!inspector);
        }catch (Exception e)
        {
            System.out.println("Error fatal en el ingreso de la password.");
        }

        return password;
    }

    public String ingresarDNI() {
        String dni="Error";
        boolean inspector;
        Scanner scan = new Scanner(System.in);

        try {
            do {
                System.out.println(" DNI : ");
                dni = scan.nextLine();
                inspector = validDni(dni);
            } while (!inspector);
        }catch (Exception e)
        {
            System.out.println("Error fatal en el ingreso del DNI.");
        }

        return dni;
    }

    public String ingresarUserName() {
        String userName="Error";
        boolean inspector;
        Scanner scan = new Scanner(System.in);

        try {
            do {
                System.out.println(" Username : ");
                userName = scan.nextLine();
                inspector = validUsername(userName);
            } while (!inspector);
        }catch (Exception e)
        {
            System.out.println("Error fatal al ingresar el nombre de usuario.");
        }
        return userName;
    }

    private boolean validPassword(String password) {
        final int MAX = 8;
        final int MIN_Uppercase = 1;
        final int MIN_Lowercase = 1;
        final int NUM_Digits = 0;
        int uppercaseCounter = 0;
        int lowercaseCounter = 0;
        int digitCounter = 0;

        if (password != null) {
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
        } else {
            return false;
        }
    }

    private boolean validEmail(String email) {
        if (email != null) {
            Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            Matcher mather = pattern.matcher(email);

            if (mather.find()) {
                System.out.println("El email ingresado es válido.");
                return true;
            } else {
                System.out.println("El email ingresado es inválido.");
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean validDni(String dni) {
        int i, j = 0;
        String numero = ""; // Es el numero que se comprueba uno a uno por si hay alguna letra entre los 8 primeros digitos
        String[] unoNueve = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        for (i = 0; i < dni.length() - 1; i++) {
            numero = dni.substring(i, i + 1);

            for (j = 0; j < unoNueve.length; j++) {
                if (numero.equals(unoNueve[j])) {
                    numero += unoNueve[j];
                }
            }
        }

        if (dni.length() != 8) {
            System.out.println("El DNI ingresado es invalido.");
            return false;
        } else {
            System.out.println("El DNI ingresado es valido.");
            return true;
        }
    }

    public static boolean validUsername(String name) {
        String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
        boolean specialchar = false;
        boolean numero = false;
        if (name.length() > 4 && name.length() < 15) {
            for (int i = 0; i < name.length(); i++) {
                if (specialChars.contains(String.valueOf(name.charAt(i)))) {
                    specialchar = true;
                } else if (Character.isDigit(Integer.valueOf(name.charAt(i)))) {
                    numero = true;
                }
            }

            if (specialchar && numero) {
                System.out.println("El nombre " + name + " es invalido");
                return false;
            } else {
                System.out.println("El nombre " + name + " es valido");
                return true;
            }
        } else {
            System.out.println("El nombre " + name + " no tiene caracteres suficientes.");
            return false;
        }
    }


//-----------------Opciones de Menu------------------//

//---------Menu de prueba sin desarrollo------------//

    private void MenuLogin() {
        System.out.println("   [ Blockchain - V1 ]");
        System.out.println(" 1 - Iniciar seccion.");
        System.out.println(" 2 - Registrarse.. ");
    }

    private void MenuPrincipal(String nombreUser) {
        System.out.println("      [ Blockchain - V1 ]");
        System.out.println(" [ Bienvenido "+nombreUser+" ]");
        System.out.println(" 1 - Deposito");
        System.out.println(" 2 - Transferencia");
        System.out.println(" 3 - Exit");
    }




    /////


    public void tercero() {
        Usuario uno = new Usuario("Alan", "alan@gmail", "password", "41079103", new Wallet());
        Usuario dos = new Usuario("Nico", "nico@gmail", "password2", "31079103", new Wallet());
        Usuario tres = new Usuario("Santi", "santi@gmail", "password3", "41459103", new Wallet());
        Usuario cuatro = new Usuario("Masi", "masi@gmail", "password4", "41459103", new Wallet());


        System.out.println(" Prueba N°1: Mostrar el mapa");
        mapWallet.put(uno.getWallet().getCodigoSeguridad(), uno.getWallet().getUtnCoin());
        mapWallet.put(dos.getWallet().getCodigoSeguridad(), dos.getWallet().getUtnCoin());
        mapWallet.put(tres.getWallet().getCodigoSeguridad(), tres.getWallet().getUtnCoin());
        mapWallet.put(cuatro.getWallet().getCodigoSeguridad(), cuatro.getWallet().getUtnCoin());

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Muestro el usuario al que vamos a Transferir en el ejemplo siguiente: ");
        System.out.println(cuatro.toString());
        System.out.println(cuatro.getWallet().toString());
        System.out.println("-----------------------------------------------------------------------------------");
        int i = 0;

        for (Map.Entry wallet : mapWallet.entrySet()) {
            System.out.println(" Mapa N°" + i + ": " + wallet.toString());
            i++;
        }
        System.out.println("-----------------------------------------------------------------------------------");

        i = 0;
        for (Map.Entry wallet : mapWallet.entrySet()) {
            System.out.println(" Mapa N°" + i + ": " + wallet.toString());
            ///Si wallet es igual a el usuario cuatro, entonces ingresa.
            if (wallet.getKey().equals(cuatro.getWallet().getCodigoSeguridad())) {
                ///Todos los sout son para entender la logica/estructura del tp
                ///Lo hice de esta manera porque me parecio mas logico que un usuario tenga un atributo billetera en vez de un atributo transferencias.
                ///las transferencias se generan en la clase transferencias y se guardan en la BlockChain en un hashmap<key=User,Valor=transferencia>

                System.out.println("Soy el usuario " + tres.getNombre() + " hoy tengo que pagar una cuota de $50. *logea la cuenta* ");
                System.out.println("*Sistema* Genera Menu de opciones...");
                System.out.println("*Usuario* elige la opcion Transferir");
                System.out.println("*Clase transferir* Ingresa el NroCuenta a la que vas a tranferir: ");
                System.out.println("*Usuario* ingresa: 1234");
                System.out.println("*Sistema* Nro de Cuenta encontrada con exito.");
                ///Crear funcion para retornar el USUARIO o el UUID para la funcion transferir.

                System.out.println("*Menu Transferencia* " + tres.getNombre() + " ya podes ingresar el monto a transferir: (Scanner) ");
                BigDecimal cuota = new BigDecimal(50);
                System.out.println("*Usuario* : 50 ");
                System.out.println("*Sistema* Confirmar transaccion (Si/No): *Usuario* Si.");
                boolean respuesta = tres.getWallet().transferencia(tres.getWallet(), cuatro.getWallet(), cuota);
                mapWallet.replace(cuatro.getWallet().getCodigoSeguridad(), cuatro.getWallet().getUtnCoin(), cuatro.getWallet().getUtnCoin().add(cuota));
                System.out.println(wallet.toString());
                System.out.println("La transferencia es: " + respuesta);
                /// *Clase transferencia* Genera boleta y la guarda en BlockChain, la clase BlockChain tiene las funciones que son para buscar y mostrar.  nadie tiene acceso, no se puede borrar ni editar
            }
            i++;
        }
        System.out.println("-----------------------------------------------------------------------------------");

        i = 0;
        for (Map.Entry wallet : mapWallet.entrySet()) {
            System.out.println(" Mapa N°" + i + ": " + wallet.toString());
            i++;
        }
    }



}
