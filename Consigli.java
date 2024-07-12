import java.io.*;

public class Consigli{
    private NodoLibro radice;

    public Consigli(){
        radice = null;
    }

    private void inserisciConsiglio(Nodo nodoCorrente; String consiglio, String titoloLibro){
        Nodo controVento = nodoCorrente;
        boolean primaVolta = true;
        while((controVento.destro!=null) || (primaVolta)){   
            primaVolta = false;
            if(controVento.destro==null){
                Nodo nuovo = new NodoConsiglio(consiglio, null);
                controVento.setDestro(nuovo); 
                break;
            }else{
                controVento = controVento.destro;
                if(controVento.titolo.equals(consiglio)){
                    controVento.setNumeroConsigli(controVento.nConsigli++);
                    break;
            }
            }
        }   
    }
}