/**
 * La classe Utente rappresenta un utente del sistema. Fornisce informazioni
 * personali dell'utente come nome, cognome, codice fiscale, email, ID e password,
 * e gestisce lo stato di registrazione dell'utente.
 * 
 * @author LucaClerici756176CO
 * @author AlessandroMonaci757003CO
 * @version 1.0
 */

package bookrecommender;
import java.io.*;

public class Utente{

    private String nome;
    private String cognome;
    private String cf;
    private String email;
    private String id;
    private String password;
    private boolean registrato;

    /**
     * Costruttore che inizializza un utente con tutti i dettagli forniti e imposta lo stato di registrazione su true.
     *
     * @param nome Il nome dell'utente.
     * @param cognome Il cognome dell'utente.
     * @param cf Il codice fiscale dell'utente.
     * @param email L'email dell'utente.
     * @param id L'ID dell'utente.
     * @param password La password dell'utente.
     */
    public Utente(String nome, String cognome, String cf, String email, String id, String password){
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.email = email;
        this.id = id;
        this.password = password;
        this.registrato = true;
    }


    /**
     * Costruttore di default che inizializza un utente come ospite (non registrato).
     */
    public Utente(){
        this.nome = "Guest";
        this.cognome = "";
        this.cf = "";
        this.email = "";
        this.id = "";
        this.password = "";
        this.registrato = false;
    }

    /**
     * Restituisce lo stato di registrazione dell'utente.
     *
     * @return true se l'utente è registrato, false altrimenti.
     */
    public  boolean getRegistrato(){
        return this.registrato;
    }

    /**
     * Restituisce l'ID dell'utente.
     *
     * @return L'ID dell'utente.
     */
    public  String getId(){
        return this.id;
    }
}
