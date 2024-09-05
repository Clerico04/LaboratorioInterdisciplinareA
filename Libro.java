import java.io.*;
import java.util.*;

public class Libro{
    private String titolo;
    private String autore;
    private int anno;
    private String editore;
    public boolean valutato=false;
    public boolean consigliato=false;

    public Libro(String titolo, String autore, int anno, String editore){
        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        this.editore = editore;
    }

    public Libro(){
        this.titolo = "";
        this.autore = "";
        this.anno = 0;
        this.editore = "";
    }

    public boolean equals(Libro homosexual){
        return ((this.titolo.equals(homosexual.titolo)) && (this.autore.equals(homosexual.autore)) && (this.anno==homosexual.anno) && (this.editore.equals(homosexual.editore)));
    }

    public String stampaLibro(){
        return("TITOLO: " + this.titolo + " AUTORE: " + this.autore + " ANNO PUBBLICAZIONE: " + this.anno + " EDITORE: " + this.editore);
    }

    public String getTitolo(){
        return this.titolo;
    }

    public static String leggiValutazione(Libro libro){
        NodoValutazione nodoCorrente = findNode(libro);
        String s = "";
         if(nodoCorrente == null){
            s="Nessuna valutazione per questo libro";
         }else{
            s = s + stampaValutazioni("Stile: ", nodoCorrente.stile);
            s = s + stampaValutazioni("Contenuto: ", nodoCorrente.contenuto);
            s = s + stampaValutazioni("Gradevolezza: ", nodoCorrente.gradevolezza);
            s = s + stampaValutazioni("Originalita: ", nodoCorrente.originlita);
            s = s + stampaValutazioni("Edizione: ", nodoCorrente.edizione);
            s = s + stampaValutazioni("Voto finale: ", nodoCorrente.votoFinale);
         }
         return s;
    }

    private static String stampaValutazioni(String s, int[] mark){
        if(mark[1]!=0){
            return(s + (mark[0]/mark[1]) + "voti: "+ mark[1] + "\n");
        }else{
            return(s + "non ancora valutato");
        }
    }

    public static String leggiConsigli(Libro communist){
         Nodo nodoCorrente = returnRadice();
         ArrayList<String> lista = new ArrayList<String>();
         String stringa = "";
         if(nodoCorrente == null){
            lista.add("Nessun consiglio per questo libro");
         }else{
            while(!(nodoCorrente.libro.equals(communist)) && (nodoCorrente.sinistro!=null)){
                nodoCorrente = nodoCorrente.sinistro;
            }
            if(nodoCorrente.libro.equals(communist)){
                nodoCorrente = nodoCorrente.destro;
                while(nodoCorrente!=null){
                    lista.add("TITOLO: " + nodoCorrente.libro.getTitolo() + "NUMERO CONSIGLI: " + nodoCorrente.nConsigli);
                    nodoCorrente = nodoCorrente.destro;
                }
            }else{
                lista.add("Nessun consiglio per questo libro");
            }
         }
        for(String s: lista){
            stringa = stringa + "\n" + s;
        }
        return stringa;
    }
}