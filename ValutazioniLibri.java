import java.io.*;

public class Valutazionilibri{
    private static NodoValutazione radice;

    public Consigli(){
        radice = null;
    }


    private NodoValutazione returnRadice(){
        return radice;
    }

    private NodoValutazione scrollTree(Libro pedro){
                if(radice==null){
                    radice = new NodoValutazione(pedro, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, null,null);
                }else{
                    NodoValutazione i = radice;
                    while(!(i.libro.equals(pedro)) && (i.sinistro!=null)){
                        i = i.sinistro;
                    }
                    if(i.libro.equals(pedro)){
                        return i;
                    }else{
                         i.sinistro = new NodoValutazione(pedro, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, null, null);
                        return i.sinistro;
                    }
                }
            }

    private void scriviNote(nodoValutazione voto, String[] notePlus){
        for(int i=0; i<notePlus.length;i++){
                if(notePlus[i].length>0){
                    switch(i){
                        case 0:
                            voto.noteStile.add(notePlus[i]);
                            break;
                        case 1:
                            voto.noteContenuto.add(notePlus[i])
                            break;
                        case 2:
                            voto.noteGradevolezza.add(notePlus[i]);
                            break;
                        case 3:
                            voto.noteOriginalita.add(notePlus[i]);
                            break;
                        case 4:
                            voto.noteEdizione.add(notePlus[i]);
                            break;
                        case 5:
                            voto.noteVotoFinale.add(notePlus[i]);
                            break;
                    }
                }
        }
    }

    private void inserisciValutazioni(NodoValutazione nodoCorrente, int[] stile, int[] contenuto, int[] gradevolezza, int[] originalita, int[] edizione, int[] votoFinale, String[] notePlus){
        if(nodoCorrente.destro == null){
            nodoCorrente.destro=new NodoValutazione (null, stile, contenuto, gradevolezza, originalita, edizione, votoFinale, null, null);
            scriviNote(nodoCorrente.destro, notePlus);
        }else{
            NodoValutazione voto = nodoCorrente.destro;
            voto.stile[0] += stile[0];
            voto.stile[1]++;
            voto.contenuto[0] += contenuto[0];
            voto.contenuto[1]++;
            voto.gradevolezza[0] += gradevolezza[0];
            voto.grdevolezza[1]++;
            voto.originalita[0] += originalita[0];
            voto.originalita[1]++;
            voto.edizione[0] += edizione[0];
            voto.edizione[1]++;
            voto.votoFinale[0] += votoFinale[0];
            voto.votoFinale[1]++;
            scriviNote(voto, notePlus);
        }
    }

    private static Nodo findNode(Libro ricercato){
                if(radice==null){
                    return null;
                }else{
                    NodoValutazione i = radice;
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