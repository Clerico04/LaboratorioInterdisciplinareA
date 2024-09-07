import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.util.*;

public class VisualizzaLibro implements ActionListener{

    Utente u;
    Libro libro;
    JLabel dettagli;
    Libreria lib;
    JFrame frameV;

    public VisualizzaLibro(){
        JFrame frame = new JFrame();
    }

    public VisualizzaLibro(Utente utente, Libro l){
        u = utente;
        dettagli = new JLabel();
        libro = l;

        frameV = new JFrame("Cerca Libro");
		frameV.setSize(990, 540);
		frameV.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel informazioni = new JLabel();
        informazioni.setFont(new Font("Arial", Font.PLAIN, 20));
        informazioni.setText(libro.StampaLibro());
        frameV.add(informazioni);

        JButton dettagli = new JButton("Dettagli");
        JButton homeV = new JButton("Home");
        JPanel homeVPanel = new JPanel();
        homeV.addActionListener(this);
        recensioni.addActionListener(this);
        homeVPanel.add(homeV, BorderLayout.EAST);
        
        frameV.add(dettagli); 
        frameV.add(homeVPanel, BorderLayout.NORTH);

        frameV.setVisible(true);
    }


    public VisualizzaLibro(Utente utente, Libro l, Libreria libreria){
        u = utente;
        dettagli = new JLabel();;
        libro = l;
        lib = libreria;

        frameV = new JFrame("Visualizza Libro");
		frameV.setSize(990, 540);
		frameV.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel informazioni = new JLabel();
        informazioni.setFont(new Font("Arial", Font.PLAIN, 20));
        informazioni.setText(libro.StampaLibro());
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
        frameV.add(homeVPanel, BorderLayout.NORTH);

        frameV.setVisible(true);
    }

    public static void main(String[] args){
        VisualizzaLibro visualizza = new VisualizzaLibro();
    }


    @Override
    public void actionPerformed(ActionEvent e){
		JButton pulsante = (JButton)e.getSource();  
        if(pulsante.getText().equals("Dettagli")){
            dettagli = new JLabel();
            dettagli.setFont(new Font("Arial", Font.PLAIN, 20));
            if(!(libro.valutato)){
                dettagli.setText("Libro non ancora valutato, sii il primo! ");
            }else{
                dettagli.setText(Libro.leggiValutazione(libro));
            }
            if(!(libro.consigliato)){
                dettagli.setText(dettagli.getText() + " I lettori non hanno ancora consigliato nessun libro per questo libro");
            }else{
                dettagli.setText(dettagli.getText() + " Libri Consigliati: " + Libro.leggiConsigli(libro));
            }
            dettagli.setText("");
            frameV.add(dettagli);
        }else if(pulsante.getText().equals("Home")){
            if(u.getRegistrato()){
                GUI g = new GUI(u);            
            }else{
                GUI g = new GUI();
            }
        }else if(pulsante.getText().equals("Inserisci consigli")){
            if(u.getRegistrato()){
                Consigli c = new Consigli(libro,lib,u);
            }else{
                JLabel noRegistrato = new JLabel();
                noRegistrato.setFont(new Font("Arial", Font.PLAIN, 15));
                noRegistrato.setText("Solo gli utenti registrati possono inserire valutazioni");
                frameV.add(noRegistrato);
            } 
        }else if (pulsante.getText().equals("Inserisci valutazioni")){
            if(U.getRegistrato()){
                Valutazioni v = new Valutazioni(libro, u);
            }else{
                JLabel noRegistrato = new JLabel();
                noRegistrato.setFont(new Font("Arial", Font.PLAIN, 15));
                noRegistrato.setText("Solo gli utenti registrati possono inserire valutazioni");
                frameV.add(noRegistrato);
            }
        }else{
            modificaLibreria("Librerie.dati.csv", [utente.getId(), lib.getTitolo()]);
        }
    }    

    public static void modificaLibreria(String nomeFile, String[] chiavi) throws IOException {
        ArrayList<String> righe = new ArrayList<String>();
        boolean rigaModificata = false;
        String nuovaRiga = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if ((linea.contains(chiavi[0])) && (linea.contains(chiavi[1])) && !rigaModificata) {
                    nuovaRiga = linea + libro.getTitolo() + ";";
                    righe.add(nuovaRiga); 
                    rigaModificata = true;
                } else {
                    righe.add(linea);
                }
            }
        }

        if (!rigaModificata) {
            JLabel label = new JLabel();
            label.setFont(new Font("Arial", Font.PLAIN, 15));
            label.setText("Nessuna riga contenente la parola chiave \"" + parolaChiave + "\" è stata trovata.")
            frameV.add(label);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFile))) {
            for (String linea : righe) {
                writer.write(linea);
                writer.newLine();
            }
        }
    }    
}        