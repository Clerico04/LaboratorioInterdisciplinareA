import java.io.*;

public class Libro{
    private String titolo;
    private String autore;
    private int anno;
    private String editore;
    private int votoFinale;
    public boolean valutato=false;
    public boolean consigliato=false;

    public Libro(String titolo, String autore, int anno, String editore){
        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        this.editore = editore;
        this.votoFinale = leggiValutazione(titolo)[0];
    }

    public Libro(){
        this.titolo = "";
        this.autore = "";
        this.anno = 0;
        this.editore = "";
    }

    private boolean equals(Libro homosexual){
        return ((this.titolo.equals(homosexual.titolo)) && (this.autore.equals(homosexual.autore)) && (this.anno==homosexual.anno) && (this.editore.equals(homosexual.editore)));
    }

    private static Int[] leggiValutazione(String titolo){
        int[] valutazioni = new int[6];

        for(int i = 0; i < 6; i++){
            valutazioni[i] = 0;
        }

        if(!valutato){
            return valutazioni;
        }else{
            String filePath = new File("Valutazione.dati.csv").getAbsolutePath();
            boolean isFirstLine = true;
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line = "";
                while((line = br.readLine()) != null){
                    if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                    }

                    String[] columns = line.split(";");
                    String titoloLibro = columns[0];
                    valutazioni[1] = Integer.parseInt(columns[1]);
                    valutazioni[2] = Integer.parseInt(columns[2]);
                    valutazioni[3] = Integer.parseInt(columns[3]);
                    valutazioni[4] = Integer.parseInt(columns[4]);
                    valutazioni[5] = Integer.parseInt(columns[5]);
                    valutazioni[0] = Integer.parseInt(columns[6]);

                    if(titoloLibro.equals(titolo)){
                        return valutazioni;
                    }

                } 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String[] leggiConsigli(String titolo){
         Nodo nodoCorrente = returnRadice();
         if(nodoCorrente == null){
            System.out.println("Nessun consiglio per questo libro");
         }else{
            while(!(nodoCorrente.titolo.equals(titolo)) && (nodoCorrente.sinistro!=null)){
                nodoCorrente = nodoCorrente.sinistro;
            }
            if(nodoCorrente.titolo.equals(titolo)){
                System.out.println("Libri consigliati in base alla tua lettura:");
                while(nodoCorrente!=null){
                    System.out.println("TITOLO: " + nodoCorrente.titolo + "NUMERO CONSIGLI: " + nodoCorrente.nConsigli);
                }
            }else{
                System.out.println("Nessun consiglio per questo libro");
            }
         }
    }
}