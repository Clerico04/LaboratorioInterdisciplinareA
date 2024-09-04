import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.util.*;

public class LiberiaGUI{
    
    Utente u;
    JFrame frameL;

    public LibreriaGUI(){
        JFrame f = new JFrame();
    }

    public LibreriaGUI (Utente utente){
        u = utente;

        frameL = new JFrame("Cerca Libro");
		frameL.setSize(990, 540);
		frameL.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        

        JButton homeL = new JButton("Home");
        JButton creazione = new JButton("Crea Libreria");
        JPanel panel = new JPanel();
        homeL.addListener(this);

        ArrayList<Libreria> librerie = leggiLibrerie(u);

        JButton bottone;
        for(Libreria l : librerie){
            bottone = new JButton(l.getTitolo());
            bottone.addListener(this);
            frameL.add(bottone);
        }        
        
        panel.add(homeL, BorderLayout.WEST);
        panel.add(creazione, BorderLayout.EAST);
        frameL.add(panel, BorderLayout.NORTH);

        frameL.setVisible(true);
    }
  
    public static void main(String[] args){
        Libreria libreria = new Libreria();
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
		JButton pulsante = (JButton)e.getSource();
        if(pulsante.getText() == "Crea Libreria"){
            CreaLibreria cl = new CreaLibreria(u);
        }else if(pulsante.getText() == "Home"){
            if(u.getRegistrato()){
                GUI home = new GUI(u);
            }else{
                GUI home = new GUI();
            }
        }else{
            VisualizzaLiberia libreriaV = new VisualizzaLiberia(pulsante.getText(), u);
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
                    for(i=0;i<(columns.length-2),i++){
                        libri[i]=columns[i+2];
                    }
					arg.add(new Libreria(columns[1],libri));
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        return arg; 
    }
}