import java.io.*;

public class NodoLibro extends Nodo{
    private String titolo;
    private NodoConsiglio destro;
    private NodoLibro sinistro;

    public NodoLibro(String titolo, NodoLibro sinistro, NodoConsiglio destro){
        this.titolo = titolo;
        this.destro = destro;
        this.sinistro = sinistro;
    }

    private void setTitolo(String titolo){
        this.titolo = titolo;
    }

    private void setDestro(NodoConsiglio destro){
        this.destro = destro;
    }

    private void setSinistro(NodoLibro sinistro){
        this.sisnistro = sinistro;
    }
}