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

    public VisualizzaLibro(){
        JFrame frame = new JFrame();
    }

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

    public static void main(String[] args){
        VisualizzaLibro visualizza = new VisualizzaLibro();
    }


    @Override
    public void actionPerformed(ActionEvent e){
		JButton pulsante = (JButton)e.getSource();  
        if(pulsante.getText().equals("Dettagli")){
           /*det.setFont(new Font("Arial", Font.PLAIN, 20));
            det.setText("");
            det.setText(Libro.leggiValutazione(libro));
            det.setText(det.getText() + " Libri Consigliati: " + Libro.leggiConsigli(libro));*/
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