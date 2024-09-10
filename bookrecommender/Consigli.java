/**
 * La classe Consigli rappresenta un'interfaccia per consigliare libri.
 * Permette all'utente di selezionare fino a tre libri consigliati
 * da una libreria esistente e salvare queste informazioni in un file CSV.
 * Implementa l'interfaccia ActionListener per gestire gli eventi dell'interfaccia utente.
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

public class Consigli implements ActionListener{

     /** L'utente corrente */
    Utente u;

    /** Combo box per selezionare i primi tre libri consigliati */
    JComboBox box1;
    JComboBox box2;
    JComboBox box3;

     /** Libreria da cui vengono selezionati i libri consigliati */
    Libreria libreria; 

    /** Finestra principale dell'interfaccia utente */
    JFrame frame;

    /** Il libro attualmente selezionato per il consiglio */
    Libro l;

    /**
    * Costruttore predefinito che crea un frame vuoto.
    */
    public Consigli(){
        JFrame frame = new JFrame();
    }


    /**
     * Costruttore che inizializza l'interfaccia per consigliare libri.
     * 
     * @param libro    Il libro selezionato
     * @param bookshelf Libreria di riferimento
     * @param utente   L'utente corrente
     */
    public Consigli(Libro libro, Libreria bookshelf, Utente utente){
        u = utente;
        l = libro;
        libreria=bookshelf;

        frame = new JFrame("Consigli Libro");
		frame.setSize(990, 540);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel informazioni = new JLabel();
        informazioni.setFont(new Font("Arial", Font.PLAIN, 20));
        informazioni.setText("3 Libri consigliati");
        frame.add(informazioni);

        JButton conferma = new JButton("Conferma");
        JButton homeV = new JButton("Home");
        JPanel homeVPanel = new JPanel();
        homeV.addActionListener(this);
        conferma.addActionListener(this);
        homeVPanel.add(homeV, BorderLayout.NORTH);

        JPanel combini = new JPanel();
        box1 = new JComboBox();
        box2 = new JComboBox();
        box3 = new JComboBox();
        combini.add(box1);
        combini.add(box2);
        combini.add(box3);

        riempiCombo(box1);
        riempiCombo(box2);
        riempiCombo(box3);
        
        frame.add(homeVPanel);
        frame.add(combini);
        frame.add(conferma);

        frame.setVisible(true);
    }

    /**
     * Metodo obbligatorio per implementare l'interfaccia ActionListener.
     * Gestisce gli eventi dei pulsanti nell'interfaccia utente.
     * 
     * @param e L'evento che è stato generato
     */
    @Override
    public void actionPerformed(ActionEvent e){
		JButton pulsante = (JButton)e.getSource();  
        if(pulsante.getText() == "Home"){
            if(u.getRegistrato()){
                GUI gui = new GUI(u);
                frame.dispose();
            }else{
                GUI gui = new GUI();
                frame.dispose();
            }
        }else if(pulsante.getText() == "Conferma"){
            try{
                inserisciConsigli(new String[]{(String)box1.getSelectedItem() , (String)box2.getSelectedItem(), (String)box3.getSelectedItem()});
                GUI g = new GUI(u);
                frame.dispose();
            }catch(IOException z){
                z.printStackTrace();
            }
        }       
    }
    
    /**
     * Riempie una combo box con i titoli dei libri disponibili nella libreria,
     * escludendo il titolo del libro attualmente selezionato.
     * 
     * @param boxino La combo box da riempire
     */
    public void riempiCombo(JComboBox boxino){
        Libro[] elementi = libreria.getElencoLibri();
        boxino.addItem(" ");
        for(Libro t:elementi){
            if(!(t.getTitolo().equals(l.getTitolo()))){
                boxino.addItem(t.getTitolo());
            }  
        }
    }

    /**
     * Inserisce i libri consigliati in un file CSV chiamato "ConsigliLibri.dati.csv".
     * Verifica che i consigli selezionati siano validi (non vuoti e non duplicati)
     * e scrive le informazioni nel file.
     * 
     * @param advice Array di stringhe contenente i libri consigliati
     * @throws IOException Se si verifica un errore durante la scrittura nel file
     */
    public void inserisciConsigli(String[] advice) throws IOException{
        File utentiRegistrati = new File("../data/ConsigliLibri.dati.csv");
        try{
            FileWriter fileout = new FileWriter(utentiRegistrati, true);
            BufferedWriter bw = new BufferedWriter(fileout);
            String s1 = advice[0];
            String s2 = advice[1];
            String s3 = advice[2];
            bw.newLine();
            bw.write(l.getTitolo() + ";");
            if((!(s1.equals(" "))) && (!(s1.equals(s2))) && (!(s1.equals(s3)))){
                bw.write(s1 + ";");
            }else{
                bw.write("Nessun consiglio" + ";");
            }
            if((!(s2.equals(" "))) && (!(s2.equals(s1))) && (!(s2.equals(s3)))){
                bw.write(s2 + ";");
            }else{
                bw.write("Nessun consiglio" + ";");
            } 
            if((!(s3.equals(" "))) && (!(s3.equals(s2))) && (!(s3.equals(s1)))){
                bw.write(s3);
            }else{
                bw.write("Nessun consiglio");
            }   
            bw.flush();
            bw.close();
        }catch(IOException z){
            z.printStackTrace();
        }
    }  
}