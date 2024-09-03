import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.util.*;

public class VisualizzaLibro implements ActionListener{

    Utente u;
    Libro libro;
    JLabel dettagli;

    public VisualizzaLibro(){
        JFrame frame = new JFrame();
    }

    public VisualizzaLibro(Utente utente, Libro l){
        u = utente
        dettagli = new JLabel();;
        libro = l;

        JFrame frameV = new JFrame("Cerca Libro");
		frameV.setSize(990, 540);
		frameV.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel informazioni = new JLabel();
        informazioni.setFont(new Font("Arial", Font.PLAIN, 20));
        informazioni.setText(l.StampaLibro());
        frameV.add(informazioni);

        JButton dettagli = new JButton("Dettagli");
        JButton homeV = new JButton("Home");
        JPanel homeVPanel = new JPanel();
        homeV.addListener(this);
        recensioni.addListener(this);
        homeVPanel.add(homeV, BorderLayout.NORTH);
        
        frame.add(dettagli); 
        frame.add(homeVPanel);

        frameV.setVisible(true);

    }

    public static void main(String[] args){
        VisualizzaLibro visualizza = new VisualizzaLibro();
    }


    @Override
    public void actionPerformed(ActionEvent e) throws IOException{
		JButton pulsante = (JButton)e.getSource();  
        if(pulsante.getText().equals("Dettagli")){
            dettagli = new JLabel();
            dettagli.setFont(new Font("Arial", Font.PLAIN, 20));
            if(!(libro.valutato)){
                dettagli.setText("Libro non ancora valutato, sii il primo! ");
            }else{
                dettagli.setText(libro.leggiValutazione());
            }
            if(!(libro.consigliato)){
                dettagli.setText(dettagli.getText() + " I lettori non hanno ancora consigliato nessun libro per questo libro");
            }else{
                dettagli.setText(dettagli.getText() + " Libri Consigliati: " + libro.leggiConsigli())
            }
            dettagli.setText()
            frameV.add(dettagli);
        }else{
            if(u.getRegistrato()){
                GUI g = new GUI(u);            
            }else{
                GUI g = new GUI();
            }
            }        
        }        
}