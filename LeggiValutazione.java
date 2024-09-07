import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.io.*;

public class LeggiValutazione implements ActionListener{

    JFrame frame;
    Libro libro;
    JLabel val;
    JLabel not;
    
    public LeggiValutazione(){
        frame = new JFrame("Prospetto valutazioni");
    }

    public LeggiValutazione(Libro l){
        libro=l;
        frame = new JFrame("Prospetto valutazioni");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        JButton home = new JButton("Home");
        home.addActionListener(this);

        val = new JLabel();
        frame.add(val);

        not = new JLabel();
        frame.add(not);

        frame.add(home);
        frame.setVisible(true);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GUI gui = new GUI();
    }

    public void getValutazioni(){
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
                String stampa = "";
                

                if(title.equals(libro.getTitolo())){
                    conta++;
                    contaS += stileV;
                    contaC += contenutoV;
                    contaG += gredevolezzaV;
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
            if(libro.getValutato()){
                contaS = contaS/conta;
                contaC = contaC/conta;
                contaG = contaG/conta;
                contaO = contaO/conta;
                contaE = contaE/conta;
                contaVF = contaVF/conta;
                stampa = "Stile: " +  contaS + "Contenuto: " + contaC + "Gradevolezza: " + contaG + "Originalita: " + contaO + "Edizione: " + contaE + "Voto Finale" + contaVF;
                val.setText(stampa);
            }else{
                val.setText("Questo libro non ha ancora ricevuto valutazioni");
            }
                stampa = "Note Stile: " +  leggiList(noteStile) + "Note Contenuto: " + leggiList(noteContenuto) + "Note Gradevolezza: " + leggiList(noteGradevolezza) + "Note Originalita: " + leggiList(noteOriginalita) + "Note Edizione: " + leggiList(noteEdizione) + "Note Voto Finale" + leggiList(noteVotoFinale);
                not.setText(stampa);
        } catch (IOException z) {
            z.printStackTrace();
        }
    }

    public String leggiList(ArrayList<String> ar){
        String unita="";
        for(String s:ar){
            unita += s + " / ";
        }
        return unita;
    }
}