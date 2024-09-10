/**
 * La classe LibreriaGUI gestisce l'interfaccia grafica per visualizzare e creare librerie dell'utente.
 * Consente di visualizzare le librerie esistenti, creare nuove librerie e tornare alla schermata principale.
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

public class LibreriaGUI implements ActionListener{
    
    Utente u;
    JFrame frameL;

    /**
     * Costruttore di default che crea una finestra vuota.
     */
    public LibreriaGUI(){
        JFrame f = new JFrame();
    }

    /**
     * Costruttore che crea l'interfaccia grafica per gestire le librerie di un utente.
     *
     * @param utente L'utente per il quale visualizzare e gestire le librerie.
     */
    public LibreriaGUI (Utente utente){
        u = utente;

        frameL = new JFrame("Librerie");
		frameL.setSize(990, 540);
		frameL.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameL.setLayout(new FlowLayout(FlowLayout.CENTER));
        

        JButton homeL = new JButton("Home");
        JButton creazione = new JButton("Crea Libreria");
        creazione.addActionListener(this);
        JPanel panel = new JPanel();
        homeL.addActionListener(this);

        ArrayList<Libreria> librerie = leggiLibrerie(u);

        panel.add(homeL, BorderLayout.WEST);
        panel.add(creazione, BorderLayout.EAST);
        frameL.add(panel);
        
        JButton bottone;
        for(Libreria l : librerie){
            bottone = new JButton(l.getTitolo());
            bottone.addActionListener(this);
            frameL.add(bottone);
        }        
        
        frameL.setVisible(true);
    } 
    
    /**
     * Gestisce gli eventi di azione, come la creazione di una libreria, il ritorno alla home, 
     * e la visualizzazione di una libreria selezionata.
     *
     * @param e L'evento di azione che si è verificato.
     */
    @Override
    public void actionPerformed(ActionEvent e){
		JButton pulsante = (JButton)e.getSource();
        if(pulsante.getText() == "Crea Libreria"){
            CreaLibreria cl = new CreaLibreria(u);
            frameL.dispose();
        }else if(pulsante.getText() == "Home"){
            if(u.getRegistrato()){
                GUI home = new GUI(u);
                frameL.dispose();
            }else{
                GUI home = new GUI();
                frameL.dispose();
            }
        }else{
            VisualizzaLibreria libreriaV = new VisualizzaLibreria(pulsante.getText(), u);
            frameL.dispose();
        }
	}

    /**
     * Legge le librerie associate a un determinato utente da un file di dati.
     *
     * @param utente L'utente di cui leggere le librerie.
     * @return Una lista di oggetti Libreria associati all'utente.
     */
    public ArrayList<Libreria> leggiLibrerie(Utente utente){
        String filePath = new File("Librerie.dati.csv").getAbsolutePath();
		boolean isFirstLine = true;
        ArrayList<Libreria> arg = new ArrayList<Libreria>();
        Libro[] libri;
				
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}

				String[] columns = line.split(";");
				if(columns[0].equals(utente.getId())){
                    libri = new Libro[columns.length-2];
                    for(int i=0; i<(columns.length-2); i++){
                        libri[i] = trovaLibroTitolo(columns[i+2]);
                    }
					arg.add(new Libreria(columns[1], libri));
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        return arg; 
    }

    /**
     * Cerca un libro per titolo leggendo da un file di dati.
     *
     * @param titolo Il titolo del libro da cercare.
     * @return Un oggetto Libro se trovato, altrimenti null.
     */
    public Libro trovaLibroTitolo (String titolo){
        String filePath = new File("Libri.dati.csv").getAbsolutePath();
        boolean isFirstLine = true;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] columns = line.split(";");
                String isbn = columns[0];
                String title = columns[1];
                String author = columns[2];
                String annoFileStr = columns[3].replace("\"", "");
                String editore = columns[4];
                Libro cercato;
                if(title.equals(titolo)){
                    cercato = new Libro(columns[1], columns[2], annoFileStr, columns[4]);
                    return cercato;
                }
            }
        } catch (IOException z) {
            z.printStackTrace();
        }
        return null;
    }
}
