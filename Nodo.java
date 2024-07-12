import java.io.*;

public class Nodo{
    private String titolo;
    private Nodo destro;
    private Nodo sinistro;

    public Nodo(String titolo, Nodo destro, Nodo sinistro){
        this.titolo = titolo;
        this.destro = destro;
        this.sinistro = sinistro;
    }

    public Nodo(){
        this.titolo = "";
        this.destro = null;
        this.sinistro = null;
    }
}