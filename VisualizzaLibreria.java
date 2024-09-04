import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.util.*;

public class VisualizzaLibreria implements ActionListener{

    Utente u;
    Libro[] libri;
    Libreria libreria;

    public VisualizzaLibreria(){
        JFrame f = new JFrame();
    }

    public VisualizzaLibreria(String nome, Utente utente){
        u = utente;

        JFrame frameV = new JFrame("Visualizza libreria");
		frameV.setSize(990, 540);
		frameV.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        

        JButton aggiungiLibro = new JButton("Aggiungi libro");
        JButton homeV = new JButton("Home");
        JPanel panel = new JPanel();
        aggiungiLibro.addListener(this);
        homeL.addListener(this);

        libreria = Libreria.getLibreria(nome, u.getId());
        libri = libreria.getElencoLibri();

        JButton bottone;
        for(Libro l : libri){
            bottone = new JButton(l.getTitolo());
            bottone.addListener(this);
            frameV.add(bottone);
        }
        
        panel.add(homeV, BorderLayout.NORTH);
        frameV.add(panel, BorderLayout.NORTH);

        frameV.setVisible(true);
    }

    public static void main(String[] args){
        VisualizzaLibreria l = new VisualizzaLibreria();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		JButton pulsante = (JButton)e.getSource();
        if(pulsante.getText().equals("Home")){
            GUI gui = new GUI(u);
        }else if(pulsante.getText().equals("Aggiungi libro")){
            CercaLibro cl = new CercaLibro(u,libreria);
        }else{
            Libro temp;
            String t = pulsante.getText();
            for(Libro l : libri){
                if(l.getTitolo == t){
                    temp = l;
                }
            }
            VisualizzaLibro libroScelto = new VisualizzaLibro(u, temp, libreria);
        }
	}
}