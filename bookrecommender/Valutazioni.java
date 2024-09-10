/**
 * La classe Valutazioni fornisce un'interfaccia utente per permettere agli utenti di valutare un libro in base a diversi criteri.
 * Gli utenti possono inserire voti e giudizi per lo stile, il contenuto, la gradevolezza, l'originalità e l'edizione del libro,
 * e il voto finale viene calcolato automaticamente come media degli altri voti.
 * 
 * @author LucaClerici756176CO
 * @author AlessandroMonaci757003CO
 * @version 1.0
 */

package bookrecommender;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.util.*;
import java.lang.*;

public class Valutazioni implements ActionListener{

    JFrame frame;
    Utente u;
    Libro l;
    JTextField stileVoto = new JTextField("Inserisci voto per lo stile (da 1 a 5)", 25);
    JTextField stileGiudizio = new JTextField("Inserisci giudizio per lo stile (massimo 256 caratteri)", 25);
    JTextField contenutoVoto = new JTextField("Inserisci voto per il contenuto (da 1 a 5)", 25);
    JTextField contenutoGiudizio = new JTextField("Inserisci giudizio per il contenuto (massimo 256 caratteri)", 25);
    JTextField gradevolezzaVoto = new JTextField("Inserisci voto per la gradevolezza (da 1 a 5)", 25);
    JTextField gradevolezzaGiudizio = new JTextField("Inserisci giudizio per la gradevolezza (massimo 256 caratteri)", 25);
    JTextField originalitaVoto = new JTextField("Inserisci voto per l'orignalità (da 1 a 5)", 25);
    JTextField originalitaGiudizio = new JTextField("Inserisci giudizio per l'originalità (massimo 256 caratteri)", 25);
    JTextField edizioneVoto = new JTextField("Inserisci voto per l'edizione (da 1 a 5)", 25);
    JTextField edizioneGiudizio = new JTextField("Inserisci giudizio per l'edizione (massimo 256 caratteri)", 25);
    JTextField votoFinal = new JTextField("Il voto finale verrà calcolato come media degli altri voti", 25);
    JTextField giudizioFinale = new JTextField("Inserisci un giudizio complessivo per questo libro (massimo 256 caratteri)", 25);

    /**
     * Costruttore di default che inizializza la finestra delle valutazioni senza parametri.
     */
    public Valutazioni(){
        frame = new JFrame();
    }

    /**
     * Costruttore che inizializza la finestra delle valutazioni per un libro specifico e un utente specifico.
     *
     * @param libro Il libro da valutare.
     * @param utente L'utente che sta effettuando la valutazione.
     */
    public Valutazioni(Libro libro, Utente utente){
        u = utente;
        l=libro;

        frame = new JFrame("Valuta Libro");
		frame.setSize(1920, 1080);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        

        JButton home = new JButton("Home");
        JPanel panel = new JPanel();
        home.addActionListener(this);

        JPanel voti = new JPanel();
        voti.setLayout(new GridLayout(6, 2));
        voti.add(stileVoto);
        voti.add(stileGiudizio);
        voti.add(contenutoVoto);
        voti.add(contenutoGiudizio);
        voti.add(gradevolezzaVoto);
        voti.add(gradevolezzaGiudizio);
        voti.add(originalitaVoto);
        voti.add(originalitaGiudizio);
        voti.add(edizioneVoto);
        voti.add(edizioneGiudizio);
        voti.add(votoFinal);
        voti.add(giudizioFinale);

        JButton conferma = new JButton();
        conferma.addActionListener(this);

        frame.add(voti,  BorderLayout.CENTER);
        frame.add(conferma,  BorderLayout.SOUTH);
        panel.add(home, BorderLayout.WEST);
        frame.add(panel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    /**
     * Gestisce gli eventi generati dai pulsanti. Se viene cliccato il pulsante "Home", viene mostrata la schermata principale.
     * Altrimenti, i dati di valutazione vengono raccolti e salvati.
     *
     * @param e L'evento generato dal pulsante cliccato.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
		JButton pulsante = (JButton)e.getSource();
        if(pulsante.getText() == "Home"){
                GUI gui = new GUI(u);
                frame.dispose();
		}else{
            String sVoto = stileVoto.getText();
            String stileG = stileGiudizio.getText();
            String contenutoV = contenutoVoto.getText();
            String contenutoG = contenutoGiudizio.getText();
            String gradevolezzaV = gradevolezzaVoto.getText(); 
            String gradevolezzaG = gradevolezzaGiudizio.getText();
            String originalitaV = originalitaVoto.getText();
            String originalitaG = originalitaGiudizio.getText();
            String edizioneV =edizioneVoto.getText();
            String edizioneG = edizioneGiudizio.getText();
            String votoFinaleGiudizio = giudizioFinale.getText();

            if((isNumeric(sVoto))  && ((Integer.parseInt(sVoto) >= 1) && (Integer.parseInt(sVoto) <= 5)) &&  (isNumeric(contenutoV))  && ((Integer.parseInt(contenutoV) >= 1) && (Integer.parseInt(contenutoV) <= 5)) && (isNumeric(gradevolezzaV))  && ((Integer.parseInt(gradevolezzaV) >= 1) && (Integer.parseInt(gradevolezzaV) <= 5)) && (isNumeric(originalitaV))  && ((Integer.parseInt(originalitaV) >= 1) && (Integer.parseInt(originalitaV) <= 5)) && (isNumeric(edizioneV))  && ((Integer.parseInt(edizioneV) >= 1) && (Integer.parseInt(edizioneV) <= 5))){
                   int votoFinale = Math.round((Integer.parseInt(sVoto) +  Integer.parseInt(contenutoV) + Integer.parseInt(gradevolezzaV) + Integer.parseInt(originalitaV) + Integer.parseInt(edizioneV))/5); 
                   inserisciValutazioni(sVoto, stileG, contenutoV, contenutoG, gradevolezzaV, gradevolezzaG, originalitaV, originalitaG, edizioneV, edizioneG, Integer.toString(votoFinale), votoFinaleGiudizio);
                   GUI gui = new GUI(u);
                   frame.dispose();
            }else{
                JLabel errore = new JLabel();
                errore.setFont(new Font("Arial", Font.BOLD, 15));
                errore.setText("I voti di ogni categoria devono essere un numero tra 1 e 5, inseriscili correttamente per continuare");
                frame.add(errore);
            }
	    }
    }

    /**
     * Verifica se una stringa rappresenta un numero intero valido.
     *
     * @param str La stringa da verificare.
     * @return true se la stringa è un numero intero, altrimenti false.
     */
    public static boolean isNumeric(String str) { 
        try {
            Integer.parseInt(str);
            return true;
        }   catch(NumberFormatException e){
             return false;
        }
    }

    /**
     * Inserisce le valutazioni del libro nel file delle valutazioni.
     *
     * @param votoS Voto per lo stile.
     * @param giudizioS Giudizio per lo stile.
     * @param votoC Voto per il contenuto.
     * @param giudizioC Giudizio per il contenuto.
     * @param votoG Voto per la gradevolezza.
     * @param giudizioG Giudizio per la gradevolezza.
     * @param votoO Voto per l'originalità.
     * @param giudizioO Giudizio per l'originalità.
     * @param votoE Voto per l'edizione.
     * @param giudizioE Giudizio per l'edizione.
     * @param votoF Voto finale calcolato come media.
     * @param giudizioF Giudizio complessivo per il libro.
     */
    public void inserisciValutazioni(String votoS, String giudizioS, String votoC, String giudizioC, String votoG, String giudizioG, String votoO, String giudizioO, String votoE, String giudizioE, String votoF, String giudizioF){
                File utentiRegistrati = new File("../data/ValutazioniLibri.dati.csv");
                try{
                    FileWriter fileout = new FileWriter(utentiRegistrati, true);
                    BufferedWriter bw = new BufferedWriter(fileout);
                    bw.newLine();
                    bw.write(l.getTitolo()+";");
                    bw.write(votoS + ";");
                    if(giudizioS.equals("")){
                        bw.write(" " + ";");
                    }else{
                        bw.write(giudizioS + ";");
                    }
                    bw.write(votoC + ";");
                    if(giudizioC.equals("")){
                        bw.write(" " + ";");
                    }else{
                        bw.write(giudizioC + ";");                     
                    }
                    bw.write(votoG + ";");
                    if(giudizioG.equals("")){
                        bw.write(" " + ";");
                    }else{
                        bw.write(giudizioG + ";");
                    }
                    bw.write(votoO + ";");
                    if(giudizioO.equals("")){
                        bw.write(" " + ";");
                    }else{
                        bw.write(giudizioO + ";");
                    }
                    bw.write(votoE + ";");
                    if(giudizioE.equals("")){
                        bw.write(" " + ";");
                    }else{
                        bw.write(giudizioE + ";");
                    }
                    bw.write(votoF + ";");
                    if(giudizioF.equals("")){
                        bw.write(" " + ";");
                    }else{
                        bw.write(giudizioF+";");
                    }
                    bw.flush();
                    bw.close();
                }catch(IOException z){
                    z.printStackTrace();
                }
    }
}