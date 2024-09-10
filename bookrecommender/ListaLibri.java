/**
 * La classe ListaLibri gestisce l'interfaccia grafica per visualizzare una lista di libri.
 * Permette di navigare tra i libri con pulsanti per visualizzare il libro corrente,
 * passare al libro successivo o precedente, e tornare alla schermata principale.
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

public class ListaLibri implements ActionListener{
	
    JLabel libretto;
    ArrayList<Libro> listone;
    int i = 0;
    Utente u;
    Libreria libreria;
    JFrame finestra;

    /**
     * Costruttore che crea una finestra di login con dimensioni predefinite.
     */
	public ListaLibri(){
		finestra = new JFrame("Lista Libri");
		finestra.setSize(1920, 1080);
		finestra.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		finestra.setVisible(true);
	}

    /**
     * Costruttore che crea una finestra per visualizzare una lista di libri e permette di navigare tra di essi.
     *
     * @param arg      La lista di libri da visualizzare.
     * @param utente   L'utente attualmente loggato.
     * @param libreria La libreria a cui i libri appartengono.
     */
    public ListaLibri(ArrayList<Libro> arg, Utente utente, Libreria l){
        u = utente;
        libreria = l;

		finestra = new JFrame("ListaLibri");
		finestra.setSize(1920, 1080);
		finestra.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        finestra.setLayout(new FlowLayout(FlowLayout.CENTER));
		
        JPanel bottoni = new JPanel();
        JButton succ = new JButton ("Successivo");
        succ.addActionListener(this);
        JButton ped = new JButton ("Precedente");
        ped.addActionListener(this);
        JButton visa = new JButton ("Visualizza il Libro");
        visa.addActionListener(this);
        JButton homeList = new JButton("Home");
        homeList.addActionListener(this);

        listone = arg;
        Libro libro = listone.get(i);
        libretto = new JLabel(libro.stampaLibro());

        bottoni.add(succ, BorderLayout.EAST);
        bottoni.add(ped, BorderLayout.WEST);
        bottoni.add(visa, BorderLayout.CENTER);

        JPanel grossa = new JPanel();
        grossa.add(libretto, BorderLayout.NORTH);
        grossa.add(bottoni, BorderLayout.CENTER);

        finestra.add(homeList, BorderLayout.NORTH);
        finestra.add(grossa, BorderLayout.CENTER);

		finestra.setVisible(true);
	}

    /**
     * Costruttore che crea una finestra per visualizzare una lista di libri senza libreria associata.
     *
     * @param arg    La lista di libri da visualizzare.
     * @param utente L'utente attualmente loggato.
     */
    public ListaLibri(ArrayList<Libro> arg, Utente utente){
        u = utente;

		finestra = new JFrame("ListaLibri");
		finestra.setSize(1920, 1080);
		finestra.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        finestra.setLayout(new FlowLayout(FlowLayout.CENTER));
		
        JPanel bottoni = new JPanel();
        JButton succ = new JButton ("Successivo");
        succ.addActionListener(this);
        JButton ped = new JButton ("Precedente");
        ped.addActionListener(this);
        JButton visa = new JButton ("Visualizza Libro");
        visa.addActionListener(this);
        JButton homeList = new JButton("Home");
        homeList.addActionListener(this);

        listone = arg;
        Libro libro = listone.get(i);
        libretto = new JLabel(libro.stampaLibro());

        bottoni.add(succ, BorderLayout.EAST);
        bottoni.add(ped, BorderLayout.WEST);
        bottoni.add(visa, BorderLayout.CENTER);

        JPanel grossa = new JPanel();
        grossa.add(libretto, BorderLayout.NORTH);
        grossa.add(bottoni, BorderLayout.CENTER);

        finestra.add(homeList, BorderLayout.NORTH);
        finestra.add(grossa, BorderLayout.CENTER);

		finestra.setVisible(true);
	}
	
    /**
     * Gestisce gli eventi dei pulsanti nella finestra.
     *
     * @param e L'evento di azione generato dal pulsante premuto.
     */
	@Override
    public void actionPerformed(ActionEvent e){
			JButton pulsante = (JButton)e.getSource();
            Libro l;
			if(pulsante.getText().equals("Successivo")){
                if(!(i+2>listone.size())){
                    i++;
                    l=listone.get(i);
                    libretto.setText(l.stampaLibro());
                }
			}else if(pulsante.getText().equals("Precedente")){
                if(!(i-1<0)){
                    i--;
                    l=listone.get(i);
                    libretto.setText(l.stampaLibro());	
			    }
            }else if(pulsante.getText().equals("Visualizza Libro")){
                VisualizzaLibro v = new VisualizzaLibro(u, listone.get(i));
                finestra.dispose();
            }else if(pulsante.getText().equals("Visualizza il Libro")){
                VisualizzaLibro v = new VisualizzaLibro(u, listone.get(i), libreria);
                finestra.dispose();
            }else{
                if(u.getRegistrato()){
                    GUI homePage = new GUI(u);
                    finestra.dispose();
                }else{
                    GUI homePage = new GUI();
                    finestra.dispose();
                }
            }
    }
}