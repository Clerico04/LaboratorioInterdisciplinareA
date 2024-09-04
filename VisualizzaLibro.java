import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.util.*;

public class VisualizzaLibro implements ActionListener{

    Utente u;
    Libro libro;
    JLabel dettagli;
    Libreria omosessuale;

    public VisualizzaLibro(){
        JFrame frame = new JFrame();
    }

    public VisualizzaLibro(Utente utente, Libro l){
        u = utente
        dettagli = new JLabel();
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
        JButton aggiungi = new JButton("Aggiungi alla libreria");
        aggiungi.addListener(this);
        JPanel homeVPanel = new JPanel();
        homeV.addListener(this);
        recensioni.addListener(this);
        homeVPanel.add(homeV, BorderLayout.EAST);
        homeVPanel.add(aggiungi, BorderLayout.CENTER);
        
        frameV.add(dettagli); 
        frameV.add(homeVPanel, BorderLayout.APPLET);

        frameV.setVisible(true);
    }


    public VisualizzaLibro(Utente utente, Libro l, Libreria libreria){
        u = utente
        dettagli = new JLabel();;
        libro = l;
        omosessuale0 libreria;

        JFrame frameV = new JFrame("Visualizza Libro");
		frameV.setSize(990, 540);
		frameV.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel informazioni = new JLabel();
        informazioni.setFont(new Font("Arial", Font.PLAIN, 20));
        informazioni.setText(l.StampaLibro());
        frameV.add(informazioni);

        JButton dettagli = new JButton("Dettagli");
        JButton homeV = new JButton("Home");
        JButton consigli = new JButton("Inserisci consigli");
        consigli.addListener(this);
        JButton valutazioni = new JButton("Inserisci valutazioni");
        valutazioni.addListener(this);
        JPanel homeVPanel = new JPanel();
        homeV.addListener(this);
        dettagli.addListener(this);
        
        homeVPanel.add(homeV, BorderLayout.WEST);
        homeVPanel.add(valutazioni, BorderLayout.CENTER);
        homeVPanel.add(consigli, BorderLayout.EAST);
        
        frameV.add(dettagli); 
        frameV.add(homeVPanel, BorderLayout.APPLET);

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
        }else if(pulsante.getText().equals("Home")){
            if(u.getRegistrato()){
                GUI g = new GUI(u);            
            }else{
                GUI g = new GUI();
            }
        }else if(pulsante.getText().equals("Inserisci consigli")){
            Consigli c = new Consigli(libro,omosessuale,u);
        }else if (pulsante.getText().equals("Inserisci valutazioni")){
            Valutazione v = new Valutazione(libro,omosessuale,u);
        }else{

        

        }
        }    

    public static void modificaLibreria(String nomeFile, String[] chiavi) throws IOException {
        List<String> righe = new ArrayList<>();
        boolean rigaModificata = false; 

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Cerca la parola chiave nella riga corrente
                if ((linea.contains(chiavi[0])) && (linea.contains(chiavi[1])) && !rigaModificata) {
                    // Se la parola chiave è trovata e la riga non è ancora stata modificata
                    righe.add(nuovaRiga); // Aggiungi la nuova riga al posto di quella originale
                    rigaModificata = true; // Segna che la modifica è stata effettuata
                } else {
                    righe.add(linea); // Aggiungi la riga originale
                }
            }
        }

        if (!rigaModificata) {
            System.out.println("Nessuna riga contenente la parola chiave \"" + parolaChiave + "\" è stata trovata.");
            return;
        }

        // Scrive nuovamente tutte le righe nel file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFile))) {
            for (String linea : righe) {
                writer.write(linea);
                writer.newLine();
            }
        }
    }    
}        