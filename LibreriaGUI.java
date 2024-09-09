import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.util.*;

public class LibreriaGUI implements ActionListener{
    
    Utente u;
    JFrame frameL;

    public LibreriaGUI(){
        JFrame f = new JFrame();
    }

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
  
    public static void main(String[] args){
        Libreria libreria = new Libreria();
    }

    
    
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
