import java.io.*;

public class Valutazionilibri{
    private NodoValutazione radice;

    public Consigli(){
        radice = null;
    }


    private NodoValutazione returnRadice(){
        return radice;
    }

    private NodoValutazione scorriAlbero(Libro pedro){
                if(radice==null){
                    radice = new NodoValutazione(pedro, 0, 0, 0, 0, 0, 0, null,null);
                }else{
                    NodoValutazione i = radice;
                    while(!(i.libro.equals(pedro)) && (i.sinistro!=null)){
                        i = i.sinistro;
                    }
                    if(i.libro.equals(pedro)){
                        return i;
                    }else{
                         i.sinistro = new NodoValutazione(pedro, 0, 0, 0, 0, 0, 0, null, null);
                        return i.sinistro;
                    }
                }
            }

    private void scriviNote(nodoValutazione communist, String[] notePlus){
        for(int i=0; i<notePlus.length;i++){
                if(notePlus[i].length>0){
                    switch(i){
                        case 0:
                            communist.noteStile.add(notePlus[i]);
                            break;
                        case 1:
                            communist.noteContenuto.add(notePlus[i])
                            break;
                        case 2:
                            communist.noteGradevolezza.add(notePlus[i]);
                            break;
                        case 3:
                            communist.noteOriginalita.add(notePlus[i]);
                            break;
                        case 4:
                            communist.noteEdizione.add(notePlus[i]);
                            break;
                        case 5:
                            communist.noteVotoFinale.add(notePlus[i]);
                            break;
                    }
                }
        }
    }

    private void inserisciValutazioni(NodoValutazione nodoCorrente, int stile, int contenuto, int gradevolezza, int originalita, int edizione, int votoFinale, String[] notePlus){
        if(nodoCorrente.destro == null){
            nodoCorrente.destro=new NodoValutazione (stile, contenuto, gradevolezza, originalita, edizione, votoFinale, null, null);
            nodoCorrente.destro.setNumeroValutazioni(1);
            scriviNote(nodoCorrente.destro, notePlus);
        }else{
            NodoValutazione homosexual = nodoCorrente.destro;
            homosexual.stile += stile;
            homosexual.contenuto += contenuto;
            homosexual.gradevolezza += gradevolezza;
            homosexual.originalita += originalita;
            homosexual.edizione += edizione;
            homosexual.votoFinale += votoFinale;
            homosexual.setNumeroValutazioni(++nValutazioni);
            scriviNote(homosexual, notePlus);
        }
    }
}