import java.io.*;
import java.util.ArrayList;

public class NodoValutazione{
    private Libro libro;
    private int stile;
    ArrayList<String> noteStile = new ArrayList<String>();
    private int contenuto;
    ArrayList<String> noteContenuto = new ArrayList<String>();
    private int gradevolezza;
    ArrayList<String> noteGradevolezza = new ArrayList<String>();
    private int originalita;
    ArrayList<String> noteOriginalita = new ArrayList<String>();
    private int edizione;
    ArrayList<String> noteEdizione = new ArrayList<String>();
    private int votoFinale;
    ArrayList<String> noteVotoFinale = new ArrayList<String>();
    private int nValutazioni;
    
    private NodoValutazione destro;
    private NodoValutazione sinistro;

    public NodoValutazione(Libro libro, int stile, int contenuto, int gradevolezza, int originalita, int edizione, int votoFinale, Nodo destro, Nodo sinistro){
        this.libro = libro;
        this.stile = stile;
        this.contenuto = contenuto;
        this.gradevolezza = gradevolezza;
        this.originalita = originalita;
        this.edizione = edizione;
        this.votoFinale = votoFinale;
        this.nValutazioni = 0;
        this.destro = destro;
        this.sinistro = sinistro;
    }

    public Nodo(){
        this.libro = null;
        this.stile = 0;
        this.contenuto = 0;
        this.gradevolezza = 0;
        this.originalita = 0;
        this.edizione = 0;
        this.votoFinale = 0;
        this.nValutazioni = 0;
        this.destro = null;
        this.sinistro = null;
    }

    private void setNumeroValutazioni(int nValutazioni){
        this.nConsigli = nValutazioni;
    }


    private void setDestro(NodoValutazione destro){
        this.destro = destro;
    }

    private void setSinistro(NodoValutazione sinistro){
        this.sisnistro = sinistro;
    }
}