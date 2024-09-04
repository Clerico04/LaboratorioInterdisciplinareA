import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.util.*;

public class cercaLibro implements ActionListener{

	JTextField titolo;
    JTextField autore;
    JTextField autoreA;
    JTextField anno;
    JFrame frame;
    Utente u;
    Libreria libreria;

    public CercaLibro(){
        frame = new JFrame("Cerca Libro");
		frame.setSize(990, 540);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

	public CercaLibro(Utente utente, Libreria l){
		u = utente;
        libreria=l;

        frame = new JFrame("Cerca Libro");
		frame.setSize(990, 540);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        titolo = new JTextField(50);
        autore = new JTextField(50);
        autoreA = new JTextField(50);
        anno = new JTextField(4);

        JPanel piccolo1 = new JPanel();
        JPanel piccolo2 = new JPanel();
        JPanel piccolo3 = new JPanel();
        
        JButton rT = new JButton("RicercaTitolo");
        rt.addListener(this);
        JButton rA = new JButton("RicercaAutore");
        rA.addListener(this);
        JButton rAA = new JButton("RicercaAutAnno");
        rAA.addListener(this);

        piccolo3.add(autoreA, BorderLayout.WEST);
        piccolo3.add(anno, BorderLayout.CENTER);
        piccolo3.add(rAA, BorderLayout.EAST);

        piccolo1.add(titolo, BorderLayout.WEST);
        piccolo1.add(rT, BorderLayout.EAST);

        piccolo2.add(autore, BorderLayout.WEST);
        piccolo2.add(rA, BorderLayout.EAST);

        panel.add(piccolo1, BorderLayout.NORTH);
        panel.add(piccolo2, BorderLayout.CENTER);
        panel.add(piccolo3, BorderLayout.SOUTH);
        
        JButton homeC = new JButton("Home");
        homeC.addListener(this);

        frame.add(panel);
        frame.add(homeC);
        	
		frame.setVisible(true);
		
	}


	public CercaLibro(Utente utente){
		u = utente;
        libreria = null;
        
        frame = new JFrame("Cerca Libro");
		frame.setSize(990, 540);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        titolo = new JTextField(50);
        autore = new JTextField(50);
        autoreA = new JTextField(50);
        anno = new JTextField(4);

        JPanel piccolo1 = new JPanel();
        JPanel piccolo2 = new JPanel();
        JPanel piccolo3 = new JPanel();
        
        JButton rT = new JButton("RicercaTitolo");
        rt.addListener(this);
        JButton rA = new JButton("RicercaAutore");
        rA.addListener(this);
        JButton rAA = new JButton("RicercaAutAnno");
        rAA.addListener(this);

        piccolo3.add(autoreA, BorderLayout.WEST);
        piccolo3.add(anno, BorderLayout.CENTER);
        piccolo3.add(rAA, BorderLayout.EAST);

        piccolo1.add(titolo, BorderLayout.WEST);
        piccolo1.add(rT, BorderLayout.EAST);

        piccolo2.add(autore, BorderLayout.WEST);
        piccolo2.add(rA, BorderLayout.EAST);

        panel.add(piccolo1, BorderLayout.NORTH);
        panel.add(piccolo2, BorderLayout.CENTER);
        panel.add(piccolo3, BorderLayout.SOUTH);
        
        JButton homeC = new JButton("Home");
        homeC.addListener(this);

        frame.add(panel);
        frame.add(homeC);
        	
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args){
		CercaLibro cerca = new CercaLibro();
	}
	
	@Override
    public void actionPerformed(ActionEvent e) throws IOException{
			JButton pulsante = (JButton) e.getSource();
            if(pulsante.getText.equals("RicercaTitolo")){
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
                        Int annoFile = Integer.parseInt(columns[3]);
                        String editore = columns[4];
                        ArrayList<Libro> risultati = new ArrayList<Libro>();
                        Libro libro;
                        if(title.contains(titolo.getText())){
                            libro = new Libro(columns[1], columns[2], Integer.parseInt(columns[3]), columns[4]);
                            risultati.add(libro);
                        }
                    }
                    if(!(risultati.isEmpty())){
                        ListaLibri l = new ListaLibri(risultati, u);                       
                    }else{
                        JLabel label = new JLabel("Nessun risultato prodotto");
                        frame.add(label); 
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(pulsante.getText.equals("RicercaAutore")){
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
                        Int annoFile = Integer.parseInt(columns[3]);
                        String editore = columns[4];
                        ArrayList<Libro> risultati = new ArrayList<Libro>();
                        Libro libro;
                        if(author.contains(autore.getText())){
                            libro = new Libro(columns[1], columns[2], Integer.parseInt(columns[3]), columns[4]);
                            risultati.add(libro);
                        }
                    }
                    if(!(risultati.isEmpty())){
                        ListaLibri l = new ListaLibri(risultati, u);
                    }else{
                        JLabel label = new JLabel("Nessun risultato prodotto");
                        frame.add(label); 
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(pulsante.getText.equals("RicercaAutAnno")){
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
                        Int annoFile = Integer.parseInt(columns[3]);
                        String editore = columns[4];
                        ArrayList<Libro> risultati = new ArrayList<Libro>();
                        Libro libro;
                        if((autoreA.contains(author.getText()) && (annoFile == Integer.parseInt(anno.getText)))){
                            libro = new Libro(columns[1], columns[2], Integer.parseInt(columns[3]), columns[4]);
                            risultati.add(libro);
                        }
                    }
                    if(!(risultati.isEmpty())){
                        ListaLibri l = new ListaLibri(risultati, u);
                    }else{
                        JLabel label = new JLabel("Nessun risultato prodotto");
                        frame.add(label); 
                    }
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                if(utente.getRegistrato()){
                    GUI casa = new GUI(utente);
                }else{
                    GUI casa = new GUI();
                }  
            }
	}
}