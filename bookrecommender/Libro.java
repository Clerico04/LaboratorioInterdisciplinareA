/**
 * La classe Libro rappresenta un libro con informazioni come titolo, autore, anno di pubblicazione ed editore.
 * Fornisce metodi per confrontare libri, stampare informazioni, leggere valutazioni e consigli da file esterni.
 * 
 * @author LucaClerici756176CO
 * @author AlessandroMonaci757003CO
 * @version 1.0
 */

package bookrecommender;
import java.io.*;
import java.util.*;

public class Libro{
    private String titolo;
    private String autore;
    private String anno;
    private String editore;

    /**
     * Costruttore che crea un oggetto Libro con le informazioni specificate.
     *
     * @param titolo  Il titolo del libro.
     * @param autore  L'autore del libro.
     * @param anno    L'anno di pubblicazione del libro.
     * @param editore L'editore del libro.
     */
    public Libro(String titolo, String autore, String anno, String editore){
        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        this.editore = editore;
    }

    /**
     * Costruttore di default che crea un oggetto Libro con informazioni vuote.
     */
    public Libro(){
        this.titolo = "";
        this.autore = "";
        this.anno = "";
        this.editore = "";
    }

    /**
     * Confronta questo libro con un altro libro per verificare se sono uguali.
     *
     * @param altro Il libro da confrontare.
     * @return true se i libri hanno lo stesso titolo, autore, anno ed editore; false altrimenti.
     */
    public boolean equals(Libro homosexual){
        return ((this.titolo.equals(homosexual.titolo)) && (this.autore.equals(homosexual.autore)) && (this.anno.equals(homosexual.anno)) && (this.editore.equals(homosexual.editore)));
    }

    /**
     * Restituisce una stringa che rappresenta le informazioni del libro.
     *
     * @return Una stringa contenente il titolo, l'autore, l'anno di pubblicazione e l'editore del libro.
     */
    public String stampaLibro(){
        return("TITOLO: " + this.titolo + " AUTORE: " + this.autore + " ANNO PUBBLICAZIONE: " + this.anno + " EDITORE: " + this.editore);
    }

    /**
     * Restituisce il titolo del libro.
     *
     * @return Il titolo del libro.
     */
    public String getTitolo(){
        return this.titolo;
    }

    /**
     * Legge le valutazioni del libro da un file esterno e restituisce un riassunto delle valutazioni.
     *
     * @param libro Il libro di cui leggere le valutazioni.
     * @return Una stringa che rappresenta le valutazioni e i commenti sul libro.
     */
    public static String leggiValutazione(Libro libro){
        String filePath = new File("ValutazioniLibri.dati.csv").getAbsolutePath();
        boolean isFirstLine = true;
        ArrayList<String> noteStile = new ArrayList<String>();
        ArrayList<String> noteContenuto = new ArrayList<String>();
        ArrayList<String> noteGradevolezza = new ArrayList<String>();
        ArrayList<String> noteOriginalita = new ArrayList<String>();
        ArrayList<String> noteEdizione = new ArrayList<String>();
        ArrayList<String> noteVotoFinale = new ArrayList<String>();
        int conta = 0;
        int contaS = 0;
        int contaC = 0;
        int contaG = 0;
        int contaO = 0;
        int contaE = 0;
        int contaVF = 0;
        String stampa = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] columns = line.split(";");
                String title = columns[0];
                int stileV = Integer.parseInt(columns[1]);
                String stileG = columns[2];
                int contenutoV = Integer.parseInt(columns[3]);
                String contenutoG = columns[4];
                int gradevolezzaV = Integer.parseInt(columns[5]);
                String gradevolezzaG = columns[6];
                int originalitaV = Integer.parseInt(columns[7]);
                String originalitaG = columns[8];
                int edizioneV = Integer.parseInt(columns[9]);
                String edizioneG = columns[10];
                int finalV = Integer.parseInt(columns[11]);
                String finalG = columns[12];
                

                if(title.equals(libro.getTitolo())){
                    conta++;
                    contaS += stileV;
                    contaC += contenutoV;
                    contaG += gradevolezzaV;
                    contaO += originalitaV;
                    contaE += edizioneV;
                    contaVF += finalV;
                    noteStile.add(stileG);
                    noteContenuto.add(contenutoG);
                    noteGradevolezza.add(gradevolezzaG);
                    noteOriginalita.add(originalitaG);
                    noteEdizione.add(edizioneG);
                    noteVotoFinale.add(finalG);
                }
            }
            if(conta!=0){
                contaS = contaS/conta;
                contaC = contaC/conta;
                contaG = contaG/conta;
                contaO = contaO/conta;
                contaE = contaE/conta;
                contaVF = contaVF/conta;
                stampa = "Libro valutato " + conta + " volte" +"\nStile: " +  contaS + " Contenuto: " + contaC + " Gradevolezza: " + contaG + " Originalita: " + contaO + " Edizione: " + contaE + " Voto Finale: " + contaVF ;
                stampa += " Note Stile: " +  leggiList(noteStile) + "Note Contenuto: " + leggiList(noteContenuto) + "Note Gradevolezza: " + leggiList(noteGradevolezza) + "Note Originalita: " + leggiList(noteOriginalita) + "Note Edizione: " + leggiList(noteEdizione) + "Note Voto Finale" + leggiList(noteVotoFinale) + " ";
            }else{
                stampa = "Libro non ancora valutato, sii il primo! " + " ";
            }
                return stampa;
        } catch (IOException z) {
            z.printStackTrace();
        }
        return "Libro non ancora valutato, sii il primo! " + "/n";
    }

    /**
     * Converte una lista di commenti in una stringa formattata.
     *
     * @param ar La lista di commenti.
     * @return Una stringa contenente tutti i commenti concatenati.
     */
    public static String leggiList(ArrayList<String> ar){
        String unita="";
        for(String s:ar){
            unita += s + " / ";
        }
        return unita;
    }

    /**
     * Legge i consigli sul libro da un file esterno e restituisce un riassunto dei consigli.
     *
     * @param libro Il libro di cui leggere i consigli.
     * @return Una stringa che rappresenta i consigli sul libro.
     */
    public static String leggiConsigli(Libro libro){
        String filePath = new File("ConsigliLibri.dati.csv").getAbsolutePath();
        boolean isFirstLine = true;
        ArrayList<Integer> conta = new ArrayList<Integer>();
        ArrayList<String> advice = new ArrayList<String>();
        String stampa="";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                String[] columns = line.split(";");
                String title = columns[0];
                String c1 = columns[1];
                String c2 = columns[2];
                String c3 = columns[3];
                int temp;
                Integer temporanea;

                if(title.equals(libro.getTitolo())){
                    if(!(c1.equals("Nessun consiglio"))){
                        if(advice.contains(c1)){
                            int index = advice.indexOf(c1);
                            temp = conta.get(index);
                            temp++;
                            conta.set(index, temp);
                        }else{
                            advice.add(c1);
                            conta.add(1);
                        }    
                    }

                    if(!(c2.equals("Nessun consiglio"))){
                        if(advice.contains(c2)){
                            int index = advice.indexOf(c2);
                            temp = conta.get(index);
                            temp++;
                            conta.set(index, temp);
                        }else{
                            advice.add(c2);
                            conta.add(1);
                        }    
                    }

                    if(!(c3.equals("Nessun consiglio"))){
                        if(advice.contains(c3)){
                            int index = advice.indexOf(c3);
                            temp = conta.get(index);
                            temp++;
                            conta.set(index, temp);
                        }else{
                            advice.add(c3);
                            conta.add(1);
                        }    
                    }
                    
                }
            }
            if(!(advice.isEmpty())){
                for(String s: advice){
                    stampa += s + "consigliato " + conta.get(advice.indexOf(s)) + " volte" + "\t";
                }
            }else{
                stampa = "Ancora nessun consiglio per questo libro";
            }
            return stampa;
        } catch (IOException z) {
            z.printStackTrace();
        }
        return "Ancora nessun consiglio per questo libro";
    }
}

