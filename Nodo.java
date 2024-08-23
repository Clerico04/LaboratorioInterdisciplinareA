import java.io.*;

public class Nodo{
    private Libro libro;
    private int nConsigli;
    private Nodo destro;
    private Nodo sinistro;

    public Nodo(Libro libro, Nodo destro, Nodo sinistro){
        this.libro = libro;
        this.nConsigli = 0;
        this.destro = destro;
        this.sinistro = sinistro;
        
    }

    public Nodo(){
        this.libro = null;
        this.nConsigli=null;
        this.destro = null;
        this.sinistro = null;
    
        private void setLibro(Libro libro){
            this.libro = libro;
        }

        private void setNumeroConsigli(int nConsigli){
            this.nConsigli = nConsigli;
        }

        private void setDestro(Nodo destro){
            this.destro = destro;
        }

        private void setSinistro(Nodo sinistro){    
        this.sisnistro = sinistro;
        }
    }
}