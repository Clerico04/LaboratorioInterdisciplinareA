import java.io.*;

public class NodoConsiglio extends Nodo{
    private String titolo;
    private int nConsigli;
    private NodoConsiglio destro;

    public NodoConsiglio(String titolo, NodoConsiglio destro){
        this.titolo = titolo;
        this.nConsigli = 0;
        this.destro = destro;
    }

    private void setTitolo(String titolo){
        this.titolo = titolo;
    }

    private void setNumeroConsigli(int nConsigli){
        this.nConsigli = nConsigli;
    }

    private void setDestro(NodoConsiglio destro){
        this.destro = destro;
    }
}