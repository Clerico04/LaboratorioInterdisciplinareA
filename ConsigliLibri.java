import java.io.*;

public class Consigli{
    private Nodo radice;

    public Consigli(){
        radice = null;
    }


    private Nodo returnRadice(){
        return radice;
    }

    private void inserisciConsiglio(Nodo nodoCorrente; Libro consiglio){
        if(nodoCorrente.destro == null){
            nodoCorrente.destro=new Nodo (consiglio, null, null);
            nodoCorrente.destro.setNumeroConsigli(1);
        }else{
            while((nodoCorrente.destro!=null) & !(nodoCorrente.libro.equals(consiglio))){ 
                    nodoCorrente = nodoCorrente.destro; 
                }
                if(nodoCorrente.libro.equals(consiglio)){
                    nodoCorrente.setNumeroConsigli(nConsigli++);
                }else{
                    nodoCorrente.destro = new NodoConsiglio(consiglio, null);
                    nodoCorrente.destro.setNumeroConsigli(1);
                }
                }
            }

            
            private Nodo scorriAlbero(Libro nuovo){
                if(radice==null){
                    radice = new Nodo(nuovo,null,null);
                }else{
                    Nodo i = radice;
                    while(!(i.libro.equals(nuovo)) && (i.sinistro!=null)){
                        i = i.sinistro;
                    }
                    if(i.libro.equals(nuovo)){
                        return i;
                    }else{
                         i.sinistro = new Nodo(libro, null, null);
                        return i.sinistro;
                    }
                }
            }

            private static Nodo trovaNodo(Libro ricercato){
                if(radice==null){
                    return null;
                }else{
                    Nodo i = radice;
                    while(!(i.libro.equals(ricercato)) && (i.sinistro!=null)){
                        i = i.sinistro;
                    }
                    if(i.libro.equals(ricercato)){
                        return i;
                    }else{
                        return null;
                    }
                }
            } 
} 