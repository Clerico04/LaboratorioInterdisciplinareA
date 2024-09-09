import java.io.*;
import java.util.*;

public class Libro{
    private String titolo;
    private String autore;
    private String anno;
    private String editore;

    public Libro(String titolo, String autore, String anno, String editore){
        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        this.editore = editore;
    }

    public Libro(){
        this.titolo = "";
        this.autore = "";
        this.anno = "";
        this.editore = "";
    }

    public boolean equals(Libro homosexual){
        return ((this.titolo.equals(homosexual.titolo)) && (this.autore.equals(homosexual.autore)) && (this.anno.equals(homosexual.anno)) && (this.editore.equals(homosexual.editore)));
    }

    public String stampaLibro(){
        return("TITOLO: " + this.titolo + " AUTORE: " + this.autore + " ANNO PUBBLICAZIONE: " + this.anno + " EDITORE: " + this.editore);
    }

    public String getTitolo(){
        return this.titolo;
    }

    public static String leggiValutazione(Libro libro){
        String filePath = new File("ValutazioniLibri.dati.csv").getAbsolutePath();
        boolean isFirstLine = true;
        ArrayList<String> noteStile = new ArrayList<String>();
        ArrayList<String> noteContenuto = new ArrayList<String>();
        ArrayList<String> noteGradevolezza = new ArrayList<String>();
        ArrayList<String> noteOriginalita = new ArrayList<String>();
        ArrayList<String> noteEdizione = new ArrayList<String>();
        ArrayList<String> noteVotoFinale = new ArrayList<String>();
        int conta = 0;
        int contaS = 0;
        int contaC = 0;
        int contaG = 0;
        int contaO = 0;
        int contaE = 0;
        int contaVF = 0;
        String stampa = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] columns = line.split(";");
                String title = columns[0];
                int stileV = Integer.parseInt(columns[1]);
                String stileG = columns[2];
                int contenutoV = Integer.parseInt(columns[3]);
                String contenutoG = columns[4];
                int gradevolezzaV = Integer.parseInt(columns[5]);
                String gradevolezzaG = columns[6];
                int originalitaV = Integer.parseInt(columns[7]);
                String originalitaG = columns[8];
                int edizioneV = Integer.parseInt(columns[9]);
                String edizioneG = columns[10];
                int finalV = Integer.parseInt(columns[11]);
                String finalG = columns[12];
                

                if(title.equals(libro.getTitolo())){
                    conta++;
                    contaS += stileV;
                    contaC += contenutoV;
                    contaG += gradevolezzaV;
                    contaO += originalitaV;
                    contaE += edizioneV;
                    contaVF += finalV;
                    noteStile.add(stileG);
                    noteContenuto.add(contenutoG);
                    noteGradevolezza.add(gradevolezzaG);
                    noteOriginalita.add(originalitaG);
                    noteEdizione.add(edizioneG);
                    noteVotoFinale.add(finalG);
                }
            }
            if(conta!=0){
                contaS = contaS/conta;
                contaC = contaC/conta;
                contaG = contaG/conta;
                contaO = contaO/conta;
                contaE = contaE/conta;
                contaVF = contaVF/conta;
                stampa = "Libro valutato " + conta + " volte" +"\nStile: " +  contaS + "Contenuto: " + contaC + "Gradevolezza: " + contaG + "Originalita: " + contaO + "Edizione: " + contaE + "Voto Finale" + contaVF +"/n";
                stampa += "Note Stile: " +  leggiList(noteStile) + "Note Contenuto: " + leggiList(noteContenuto) + "Note Gradevolezza: " + leggiList(noteGradevolezza) + "Note Originalita: " + leggiList(noteOriginalita) + "Note Edizione: " + leggiList(noteEdizione) + "Note Voto Finale" + leggiList(noteVotoFinale) + "\n";
            }else{
                stampa = "Libro non ancora valutato, sii il primo! " + "/n";
            }
                return stampa;
        } catch (IOException z) {
            z.printStackTrace();
        }
        return "Libro non ancora valutato, sii il primo! " + "/n";
    }

    public static String leggiList(ArrayList<String> ar){
        String unita="";
        for(String s:ar){
            unita += s + " / ";
        }
        return unita;
    }

    public static String leggiConsigli(Libro libro){
        String filePath = new File("ConsigliLibri.dati.csv").getAbsolutePath();
        boolean isFirstLine = true;
        ArrayList<Integer> conta = new ArrayList<Integer>();
        ArrayList<String> advice = new ArrayList<String>();
        String stampa="";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                String[] columns = line.split(";");
                String title = columns[0];
                String c1 = columns[1];
                String c2 = columns[2];
                String c3 = columns[3];
                int temp;
                Integer temporanea;

                if(title.equals(libro.getTitolo())){
                    if(!(c1.equals("Nessun consiglio"))){
                        if(!(advice.contains(c1))){
                            temp = conta.get(advice.indexOf(c1));
                            temp++;
                            temporanea = conta.set(advice.indexOf(c1), temp);
                        }else{
                            advice.add(c1);
                            conta.add(advice.indexOf(c1), 1);
                        }    
                    }

                    if(!(c2.equals("Nessun consiglio"))){
                        if(!(advice.contains(c2))){
                            temp = conta.get(advice.indexOf(c2));
                            temp++;
                            temporanea = conta.set(advice.indexOf(c2), temp);
                        }else{
                            advice.add(c2);
                            conta.add(advice.indexOf(c2), 1);
                        }    
                    }

                    if(!(c3.equals("Nessun consiglio"))){
                        if(!(advice.contains(c3))){
                            temp = conta.get(advice.indexOf(c3));
                            temp++;
                            temporanea = conta.set(advice.indexOf(c3), temp);
                        }else{
                            advice.add(c3);
                            conta.add(advice.indexOf(c3), 1);
                        }    
                    }
                    
                }
            }
            if(!(advice.isEmpty())){
                for(String s: advice){
                    stampa += s + "consigliato " + conta.get(advice.indexOf(s)) + " volte" + "\t";
                }
            }else{
                stampa = "Ancora nessun consiglio per questo libro";
            }
            return stampa;
        } catch (IOException z) {
            z.printStackTrace();
        }
        return "Ancora nessun consiglio per questo libro";
    }
}

