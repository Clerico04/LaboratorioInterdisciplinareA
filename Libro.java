import java.io.*;

public class Libro{
    private String titolo;
    private String autore;
    private int anno;
    private String editore;
    private int votoFinale;
    public boolean valutato=false;
    public boolean consigliato=false;

    public Libro(String titolo, String autore, int anno, String editore){
        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        this.editore = editore;
        this.votoFinale = leggiValutazione(titolo)[0];
    }

    public Libro(){
        this.titolo = "";
        this.autore = "";
        this.anno = 0;
        this.editore = "";
    }

    private boolean equals(Libro homosexual){
        return ((this.titolo.equals(homosexual.titolo)) && (this.autore.equals(homosexual.autore)) && (this.anno==homosexual.anno) && (this.editore.equals(homosexual.editore)));
    }

    private String stampaLibro(){
        return("TITOLO: " + this.titolo + " AUTORE: " + this.autore + " ANNO PUBBLICAZIONE: " + this.anno + " EDITORE: " + this.editore);
    }

    private static void leggiValutazione(Libro libro){
        NodoValutazione nodoCorrente = findNode(libro);
         if(nodoCorrente == null){
            System.out.println("Nessuna valutazione per questo libro");
         }else{
            stampaValutazioni("Stile: ", nodoCorrente.stile);
            stampaValutazioni("Contenuto: ", nodoCorrente.contenuto);
            stampaValutazioni("Gradevolezza: ", nodoCorrente.gradevolezza);
            stampaValutazioni("Originalita: ", nodoCorrente.originlita);
            stampaValutazioni("Edizione: ", nodoCorrente.edizione);
            stampaValutazioni("Voto finale: ", nodoCorrente.votoFinale);
         }
    }

    private static void stampaValutazioni(String s, int[] mark){
        if(mark[1]!=0){
            System.out.println(s + (mark[0]/mark[1]) + "voti: "+ mark[1]);
        }else{
            System.out.println(s + "non ancora valutato");
        }
    }

    private static void leggiConsigli(Libro communist){
         Nodo nodoCorrente = returnRadice();
         if(nodoCorrente == null){
            System.out.println("Nessun consiglio per questo libro");
         }else{
            while(!(nodoCorrente.titolo.equals(titolo)) && (nodoCorrente.sinistro!=null)){
                nodoCorrente = nodoCorrente.sinistro;
            }
            if(nodoCorrente.titolo.equals(titolo)){
                System.out.println("Libri consigliati in base alla tua lettura:");
                while(nodoCorrente!=null){
                    System.out.println("TITOLO: " + nodoCorrente.titolo + "NUMERO CONSIGLI: " + nodoCorrente.nConsigli);
                }
            }else{
                System.out.println("Nessun consiglio per questo libro");
            }
         }
    }
}