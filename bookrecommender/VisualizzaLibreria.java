/**
 * La classe VisualizzaLibreria fornisce un'interfaccia utente per visualizzare e gestire i libri all'interno di una libreria.
 * Gli utenti possono vedere un elenco dei libri, aggiungere nuovi libri e visualizzare i dettagli di un libro selezionato.
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

public class VisualizzaLibreria implements ActionListener{

    Utente u;
    Libro[] libri;
    Libreria libreria;
    JFrame frameV;

     /**
     * Costruttore di default che inizializza una finestra di visualizzazione della libreria senza parametri.
     */
    public VisualizzaLibreria(){
        JFrame f = new JFrame();
    }

    /**
     * Costruttore che inizializza la finestra per visualizzare i libri di una libreria specifica per un utente specifico.
     *
     * @param nome Il nome della libreria da visualizzare.
     * @param utente L'utente che sta visualizzando la libreria.
     */
    public VisualizzaLibreria(String nome, Utente utente){
        u = utente;

        frameV = new JFrame("Visualizza libreria");
		frameV.setSize(1920, 1080);
		frameV.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameV.setLayout(new FlowLayout(FlowLayout.CENTER));
        

        JButton aggiungiLibro = new JButton("Aggiungi libro");
        JButton homeV = new JButton("Home");
        JPanel panel = new JPanel();
        aggiungiLibro.addActionListener(this);
        homeV.addActionListener(this);

        libreria = Libreria.getLibreria(nome, u.getId());
        libri = libreria.getElencoLibri();

        panel.add(aggiungiLibro, BorderLayout.CENTER);
        panel.add(homeV, BorderLayout.WEST);
        frameV.add(panel);

        JButton bottone;
        for(Libro l : libri){
            bottone = new JButton(l.getTitolo());
            bottone.addActionListener(this);
            frameV.add(bottone);
        }
        
        frameV.setVisible(true);
    }

    /**
     * Gestisce gli eventi generati dai pulsanti. Permette di tornare alla schermata principale, aggiungere un nuovo libro
     * o visualizzare i dettagli di un libro selezionato.
     *
     * @param e L'evento generato dal pulsante cliccato.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
		JButton pulsante = (JButton)e.getSource();
        if(pulsante.getText().equals("Home")){
            GUI gui = new GUI(u);
            frameV.dispose();
        }else if(pulsante.getText().equals("Aggiungi libro")){
            CercaLibro cl = new CercaLibro(u, libreria);
            frameV.dispose();
        }else{
            Libro temp = new Libro();
            String t = pulsante.getText();
            for(Libro l : libri){
                if(l.getTitolo() == t){
                    temp = l;
                }
            }
            VisualizzaLibro libroScelto = new VisualizzaLibro(u, temp, libreria);
            frameV.dispose();
        }
	}
}