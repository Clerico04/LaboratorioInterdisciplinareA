/**
 * La classe CreaLibreria rappresenta un'interfaccia utente per la creazione di una nuova libreria.
 * Permette all'utente di inserire un nome per la libreria e di salvare queste informazioni
 * in un file CSV. Implementa l'interfaccia ActionListener per gestire gli eventi dell'interfaccia utente.
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

public class CreaLibreria implements ActionListener{

    /** L'utente corrente */
    Utente u;

    /** Campo di testo per inserire il nome della libreria */
    JTextField nomeLibreria;

    /** Finestra principale dell'interfaccia utente */
    JFrame frameL;

    /**
     * Costruttore predefinito che crea un frame vuoto.
     */
    public CreaLibreria(){
        JFrame frame = new JFrame();
    }

    /**
     * Costruttore che inizializza l'interfaccia per la creazione di una nuova libreria.
     *
     * @param utente L'utente corrente
     */
    public CreaLibreria(Utente utente){
        u = utente;
        
        frameL = new JFrame("Crea Libreria");
		frameL.setSize(1920, 1080);
		frameL.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameL.setLayout(new FlowLayout(FlowLayout.CENTER));
        

        JButton homeL = new JButton("Home");
        JPanel panel = new JPanel();
        homeL.addActionListener(this);

        nomeLibreria = new JTextField("nomeLibreria",20);
        JButton creazione = new JButton("Invio");  
        creazione.addActionListener(this); 
        
        panel.add(homeL, BorderLayout.WEST);
        panel.add(creazione, BorderLayout.EAST);
        frameL.add(nomeLibreria, BorderLayout.CENTER);
        frameL.add(panel, BorderLayout.NORTH);

        frameL.setVisible(true);
    }

    /**
     * Metodo obbligatorio per implementare l'interfaccia ActionListener.
     * Gestisce gli eventi dei pulsanti nell'interfaccia utente.
     *
     * @param e L'evento che è stato generato
     */
    @Override
    public void actionPerformed(ActionEvent e) {
		JButton pulsante = (JButton)e.getSource();
        if(pulsante.getText() == "Home"){
            if(u.getRegistrato()){
                GUI gui = new GUI(u);
                frameL.dispose();
            }else{
                GUI gui = new GUI();
                frameL.dispose();
            }
        }else{
            if(!(nomeLibreria.getText().equals(""))){
                try{
                    File librerieTxt = new File("Librerie.dati.csv");
                    FileWriter fileout = new FileWriter(librerieTxt, true);
                    BufferedWriter bw = new BufferedWriter(fileout);
                    bw.newLine();
                    bw.write(u.getId() + ";");
                    bw.write(nomeLibreria.getText() + ";");
                    bw.flush();
                    bw.close();
                    LibreriaGUI lg = new LibreriaGUI(u);  
                    frameL.dispose();
                }catch(IOException z){
                    z.printStackTrace();
                }
                LibreriaGUI lg = new LibreriaGUI(u);
                frameL.dispose();        
            }

        }
	}
}