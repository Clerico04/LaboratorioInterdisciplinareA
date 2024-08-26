jhimport java.io.*;
import java.util.ArrayList;

public class NodoValutazione{
    private Libro libro;
    private int[] stile = new int[2];
    ArrayList<String> noteStile = new ArrayList<String>();
    private int[] contenuto = new int[2];
    ArrayList<String> noteContenuto = new ArrayList<String>();
    private int[] gradevolezza = new int[2];
    ArrayList<String> noteGradevolezza = new ArrayList<String>();
    private int[] originalita = new int[2];
    ArrayList<String> noteOriginalita = new ArrayList<String>();
    private int[] edizione = new int[2];
    ArrayList<String> noteEdizione = new ArrayList<String>();
    private int[] votoFinale = new int[2];
    ArrayList<String> noteVotoFinale = new ArrayList<String>();
    
    private NodoValutazione destro;
    private NodoValutazione sinistro;

    public NodoValutazione(Libro libro, int[] stile, int[] contenuto, int[] gradevolezza, int[] originalita, int[] edizione, int[] votoFinale, Nodo destro, Nodo sinistro){
        for(int i = 0; i<2; i++){
            this.stile[i] = stile[i];
            this.contenuto[i] = contenuto[i];
            this.gradevolezza[i] = gradevolezza[i];
            this.originalita[i] = originalita[i];
            this.edizione[i] = edizione[i];
            this.votoFinale[i] = votoFinale[i];
        }
        this.libro= libro;
        this.destro = destro;
        this.sinistro = sinistro;
    }

    public Nodo(){
        this.libro = null;
        this.stile = null;
        this.contenuto = null;
        this.gradevolezza = null;
        this.originalita = null;
        this.edizione = null;
        this.votoFinale = null;
        this.nValutazioni = null;
        this.destro = null;
        this.sinistro = null;
    }

    
    private void setDestro(NodoValutazione destro){
        this.destro = destro;
    }

    private void setSinistro(NodoValutazione sinistro){
        this.sisnistro = sinistro;
    }
}