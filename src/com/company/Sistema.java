package com.company;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Sistema {

    private List<Usuario>listaUsers=new ArrayList<>();
    private HashMap<String,Usuario>mapaUsuarios=new HashMap<>();
    private Usuario activeUser;
    private List<String>documentKeys;
//-------------------------------------------Constructor ---------------------------------------------------------------
    public Sistema() throws IOException {

        setPaths();
        //this.listaUsers=cargarUsuariosDeArchivo();
        this.mapaUsuarios=cargarMapaUsuariosDeArchivo();
        //login();
    }
//-----------------------------------------MANEJO ARCHIVOS--------------------------------------------------------------

    private String USER_PATH;
    private String USUARIOS_PATH;
    private String TRANSACTIONS_TO_VALIDATE_PATH;

    //SISTEMA DEBERIA RECIBIR LA DATA DEL INGRESO POR TECLADO DEL USUARIO Y LUEGO HACER LAS VALIDACIONES. LUEGO SI PASA LAS VALIDACIONES SETEAR EL ACTIVE USER CON UN SetActiveUser

    //PRUEBAS
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
        //deleteUserFromHashMap(uno);
        //this.mapaUsuarios=cargarMapaUsuariosDeArchivo();
        System.out.println(mapaUsuarios);


    }
    public void tercero() throws IOException {
        Usuario uno=new Usuario("Alan","alan@gmail","password","41079103");
        Usuario dos=new Usuario("Nico","nico@gmail","password2","31079103");
        Usuario tres=new Usuario("Santi","fgdf@gmail","password3","41459103");
        Usuario cuatro=new Usuario("fef","dfg@gmail","password4","1231231234");
        Usuario cinco=new Usuario("sasd","dfg@gmail","password5","546645645");
        Usuario seis=new Usuario("asease","dfgdf@gmail","password6","345345345");

        mapaUsuarios.put(cuatro.getDni(),uno);
        mapaUsuarios.put(dos.getDni(),dos);
        mapaUsuarios.put(tres.getDni(),tres);
        mapaUsuarios.put(uno.getDni(),uno);
        mapaUsuarios.put(cinco.getDni(),dos);
        mapaUsuarios.put(seis.getDni(),tres);
        crearHashMapArchivo(mapaUsuarios);
        this.activeUser=uno;
        setDocumentKeys();
        System.out.println(documentKeys);
        generateNewTransaction(tres,25);

    }


//-----------------------------------------------GETTERS AND SETTERS----------------------------------------------------

    /*
   Setea los paths de las carpetas donde se guardara el archivo de Usuarios
   */
    private void setPaths()    {
        this.USER_PATH=System.getProperty("user.dir");
        this.USUARIOS_PATH=""+USER_PATH+"\\users";
        this.TRANSACTIONS_TO_VALIDATE_PATH=""+USER_PATH+"\\transactionsToValidate";
    }
    public Usuario getActiveUser() {

        return activeUser;
    }
    public void setActiveUser(Usuario activeUser) {

        this.activeUser = activeUser;
    }
    public HashMap<String, Usuario> getMapaUsuarios() {

        return mapaUsuarios;
    }
    public void setMapaUsuarios(HashMap<String, Usuario> mapaUsuarios) {

        this.mapaUsuarios = mapaUsuarios;
    }
    public List<String> getDocumentKeys() {
        return documentKeys;
    }
    public void setDocumentKeys() {
        Iterator iter=this.mapaUsuarios.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry e=(Map.Entry)iter.next();
            System.out.println(e.getKey());
            documentKeys.add((String) e.getKey());
        }
    }
//--------------------------------------------MANEJO ARCHIVO USUARIOS---------------------------------------------------

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

//-------------------------------------------MANEJO HASHMAP USUARIOS----------------------------------------------------

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


//-----------------------------------------MANEJO ARCHIVO Transacciones-------------------------------------------------

    public void addTransactionToArchive(Transaction transaction) throws IOException {
        File newTransaction=new File(TRANSACTIONS_TO_VALIDATE_PATH+"\\"+transaction.getId()+".json");
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(newTransaction, transaction);
    }


///----------------------------------------------TRANSACTIONS-----------------------------------------------------------

    private void generateNewTransaction(Usuario recieber,int amount) throws IOException {
        Transaction newTransaction=new Transaction(recieber.getWallet(),activeUser.getWallet(),generateTransactionValidators(),amount);
        addTransactionToArchive(newTransaction);

    }
    private List generateTransactionValidators(){
        //ACA TAMBIEN TENEMOS QUE RECIBIR EL USUARIO QUE CREA LA TRANSACCION Y EL USUARIO QUE LA RECIBE Y COMPROBAR QUE NO SEAN UNO DE LOS VALIDADORES

        ArrayList<Integer> numbers = new ArrayList<>();
        int validatorsQuantity=3;
        int i=0;

        /*
        Generamos 3 numeros random y los agregamos a la lista,se comprobamos que no se no se agreguen repetidos
        */
        while  (i<validatorsQuantity){
            boolean comp=false;
            Random numAleatorio=new Random();
            System.out.println(mapaUsuarios.size());
            int randomNumber= numAleatorio.nextInt(mapaUsuarios.size())+1;
            System.out.println(randomNumber);

            for(int e:numbers){
                if(randomNumber==e){
                    comp=true;
                }
            }
            if(comp==false){
                numbers.add(randomNumber);
                System.out.println("asd");
                i++;
            }
        }
        /*
         Cremos lista de usuarios y le pasamos a la transaccion que recibimos por parametro la lista con los usuarios seleccionados de forma random
        * */
        List<Usuario>validators=new ArrayList<>();
        for (Integer f:numbers){
            validators.add(mapaUsuarios.get(documentKeys.get(f)));
        }
        System.out.println(validators);
        return validators;
    }


//-----------------------------------------Opciones de Login----------------------------------------------------------//

    public void login(){
        /*
        pido docu
        pido psw
        por teclado
        valido con el hashmap de usurios que la data sea correta
        comparo doc con keys del hashmap si es igual a la key tomo el value (hashmap = key-value)
        si todo es correcto
        activeUser=e.value


        * */
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


// --------------------------------------Validacion ingreso por teclado-------------------------------------------------//

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


//----------------------------------------Menu de prueba sin desarrollo-------------------------------------------------//

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

}
