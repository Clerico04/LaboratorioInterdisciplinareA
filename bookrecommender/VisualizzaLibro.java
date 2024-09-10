/**
 * La classe VisualizzaLibro fornisce un'interfaccia utente per visualizzare i dettagli di un libro specifico.
 * Gli utenti possono visualizzare informazioni generali sul libro, i dettagli, aggiungere il libro alla libreria, 
 * inserire consigli e valutazioni, e navigare verso la schermata principale.
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

public class VisualizzaLibro implements ActionListener{

    Utente u;
    Libro libro;
    JLabel det;
    Libreria lib;
    JFrame frameV;
    JLabel noRegistrato = new JLabel();
    JTextArea textArea;

    /**
     * Costruttore di default che inizializza una finestra di visualizzazione del libro senza parametri.
     */
    public VisualizzaLibro(){
        JFrame frame = new JFrame();
    }

    /**
     * Costruttore che inizializza la finestra per visualizzare le informazioni di un libro specifico per un utente specifico.
     *
     * @param utente L'utente che sta visualizzando il libro.
     * @param l Il libro da visualizzare.
     */
    public VisualizzaLibro(Utente utente, Libro l){
        u = utente;
        det = new JLabel();
        libro = l;
        noRegistrato = new JLabel();

        textArea = new JTextArea(10, 43);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        textArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);

        frameV = new JFrame("Cerca Libro");
		frameV.setSize(1920, 1080);
		frameV.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameV.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel informazioni = new JLabel();
        informazioni.setFont(new Font("Arial", Font.PLAIN, 20));
        informazioni.setText(libro.stampaLibro());
        frameV.add(informazioni);

        JButton dettagli = new JButton("Dettagli");
        JButton homeV = new JButton("Home");
        JPanel homeVPanel = new JPanel();
        homeV.addActionListener(this);
        dettagli.addActionListener(this);
        homeVPanel.add(homeV, BorderLayout.EAST);
        
        frameV.add(dettagli); 
        frameV.add(homeVPanel);
        frameV.add(det);
        frameV.add(noRegistrato);  
        frameV.add(scrollPane); 

        frameV.setVisible(true);
    }

    /**
     * Costruttore che inizializza la finestra per visualizzare un libro e permette anche di gestire la libreria.
     *
     * @param utente L'utente che sta visualizzando il libro.
     * @param l Il libro da visualizzare.
     * @param libreria La libreria a cui appartiene il libro.
     */
    public VisualizzaLibro(Utente utente, Libro l, Libreria libreria){
        u = utente;
        det = new JLabel();
        libro = l;
        lib = libreria;

        textArea = new JTextArea(10, 45);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        textArea.setFont(new Font("Arial", Font.PLAIN, 14));


        JScrollPane scrollPane = new JScrollPane(textArea);

        frameV = new JFrame("Visualizza Libro");
		frameV.setSize(1920, 1080);
		frameV.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameV.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel informazioni = new JLabel();
        informazioni.setFont(new Font("Arial", Font.PLAIN, 20));
        informazioni.setText(libro.stampaLibro());
        frameV.add(informazioni);

        JButton dettagli = new JButton("Dettagli");
        JButton homeV = new JButton("Home");
        JButton aggiungi = new JButton("Aggiungi alla libreria");
        aggiungi.addActionListener(this);
        JButton consigli = new JButton("Inserisci consigli");
        consigli.addActionListener(this);
        JButton valutazioni = new JButton("Inserisci valutazioni");
        valutazioni.addActionListener(this);
        JPanel homeVPanel = new JPanel();
        homeV.addActionListener(this);
        dettagli.addActionListener(this);
        
        homeVPanel.add(homeV, BorderLayout.WEST);
        homeVPanel.add(valutazioni, BorderLayout.CENTER);
        homeVPanel.add(consigli, BorderLayout.EAST);
        homeVPanel.add(aggiungi, BorderLayout.SOUTH);
        
        frameV.add(dettagli); 
        frameV.add(homeVPanel);
        /*frameV.add(det); */
        frameV.add(scrollPane); 

        frameV.setVisible(true);
    }

    /**
     * Gestisce gli eventi generati dai pulsanti. Permette di visualizzare dettagli aggiuntivi, tornare alla schermata principale, 
     * inserire consigli o valutazioni, e aggiungere il libro alla libreria.
     *
     * @param e L'evento generato dal pulsante cliccato.
     */
    @Override
    public void actionPerformed(ActionEvent e){
		JButton pulsante = (JButton)e.getSource();  
        if(pulsante.getText().equals("Dettagli")){
            textArea.setText(Libro.leggiValutazione(libro) + " CONSIGLI: " + Libro.leggiConsigli(libro));
            frameV.validate();
            frameV.repaint();
        }else if(pulsante.getText().equals("Home")){
            if(u.getRegistrato()){
                GUI g = new GUI(u); 
                frameV.dispose();           
            }else{
                GUI g = new GUI();
                frameV.dispose();
            }
        }else if(pulsante.getText().equals("Inserisci consigli")){
            if(u.getRegistrato()){
                Consigli c = new Consigli(libro,lib,u);
                frameV.dispose();
            }else{
                noRegistrato.setFont(new Font("Arial", Font.PLAIN, 15));
                noRegistrato.setText("Solo gli utenti registrati possono inserire valutazioni");
                frameV.validate();
            } 
        }else if (pulsante.getText().equals("Inserisci valutazioni")){
            if(u.getRegistrato()){
                Valutazioni v = new Valutazioni(libro, u);
                frameV.dispose();
            }else{
                noRegistrato.setFont(new Font("Arial", Font.PLAIN, 15));
                noRegistrato.setText("Solo gli utenti registrati possono inserire valutazioni");
                frameV.validate();
            }
        }else{
            if(!(existence(libro.getTitolo()))){
                modificaLibreria("Librerie.dati.csv", new String[]{u.getId() , lib.getTitolo()}, libro);
            }
            GUI h = new GUI(u);
            frameV.dispose();
        }    
    }
    
    /**
     * Modifica il file della libreria aggiungendo un libro alla libreria esistente.
     *
     * @param nomeFile Il nome del file della libreria da modificare.
     * @param chiavi Chiavi per identificare la libreria.
     * @param book Il libro da aggiungere alla libreria.
     */
    public static void modificaLibreria(String nomeFile, String[] chiavi, Libro book){
        ArrayList<String> righe = new ArrayList<String>();
        boolean rigaModificata = false;
        String nuovaRiga = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))){
            String linea;
            while ((linea = reader.readLine()) != null) {
                if ((linea.contains(chiavi[0])) && (linea.contains(chiavi[1])) && !rigaModificata) {
                    nuovaRiga = linea + book.getTitolo() + ";";
                    righe.add(nuovaRiga); 
                    rigaModificata = true;
                } else {
                    righe.add(linea);
                }
            }
        }catch(FileNotFoundException z){
            z.printStackTrace();
        }catch(IOException p){
            p.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFile))) {
            for (String linea : righe) {
                writer.write(linea);
                writer.newLine();
            }
        }catch(FileNotFoundException z){
            z.printStackTrace();
        }catch(IOException p){
            p.printStackTrace();
        }
    } 

    /**
     * Controlla se un libro con un titolo specifico esiste già nella libreria.
     *
     * @param titolone Il titolo del libro da cercare.
     * @return True se il libro esiste nella libreria, altrimenti false.
     */
    public boolean existence(String titolone){
        Libro[] el = lib.getElencoLibri();
        for(Libro j:el){
            if(j.getTitolo().equals(titolone)){
                return true;
            }
        }
        return false;
    }   
}       