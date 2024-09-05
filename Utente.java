import java.io.*;

public class Utente{

    private String nome;
    private String cognome;
    private String cf;
    private String email;
    private String id;
    private String password;
    private boolean registrato;

    public Utente(String nome, String cognome, String cf, String email, String id, String password){
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.email = email;
        this.id = id;
        this.password = password;
        this.registrato = true;
    }

    public Utente(){
        this.nome = "Guest";
        this.cognome = "";
        this.cf = "";
        this.email = "";
        this.id = "";
        this.password = "";
        this.registrato = false;
    }

    public  boolean getRegistrato(){
        return this.registrato;
    }

    private  String getId(){
        return this.id;
    }
}
