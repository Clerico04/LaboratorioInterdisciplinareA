import java.io.*;

public class BookRecommender{
    public static void main(String[] args){

    }

    private static void registrazione(){
        File utentiRegistrati = new File(UtentiRegistrati.dati.txt)
        FileWriter fileout = new FileWriter(utentiRegistrati);
        BufferedWriter bw = BufferedWriter(fileout);
        System.out.print("Inserisci il nome (vai a capo per terminare):\n");
        String nome = Input.readLine();
        bw.write(nome + " ");
        System.out.print("Inserisci il cognome (vai a capo per terminare): \n");
        String cognome = Input.readLine();
        bw.write(cognome + " ");
        System.out.print("Inserisci il codice fiscale (vai a capo per terminare): \n");
        String cf = Input.readLine();
        bw.write(cf + " ");
        System.out.print("Inserisci il email (vai a capo per terminare): \n");
        String email = Input.readLine();
        bw.write(email + " ");
        System.out.print("Inserisci il nome utente (vai a capo per terminare): \n");
        String id = Input.readLine();
        bw.write(id + " ");
        System.out.print("Inserisci la tua password (vai a capo per terminare): \n");
        String password = Input.readLine();
        bw.write(password + " ");
        bw.flush();
        bw.close();
    }
}