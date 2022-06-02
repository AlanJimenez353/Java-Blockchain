package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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



}
