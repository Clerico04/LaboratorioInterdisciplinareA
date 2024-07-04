import java.io.*;

class Utente{

    private String nome;
    private String cognome;
    private String cf;
    private String email;
    private String id;
    private String password;

    public Utente(String nome, String cognome, String cf, String email, String id, String password){
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.email = email;
        this.id = id;
        this.password = password;
    }

    public Utente(){
        this.nome = "Guest";
        this.cognome = "";
        this.cf = "";
        this.email = "";
        this.id = "";
        this.password = "";
    }
}