import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.util.*;

public class CercaLibro implements ActionListener{

	JTextField titolo;
    JTextField autore;
    JTextField autoreA;
    JTextField anno;
    JFrame frame;
    Utente u;
    Libreria libreria;
    JLabel label;

    public CercaLibro(){
        frame = new JFrame("Cerca Libro");
		frame.setSize(1920, 1080);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

	public CercaLibro(Utente utente, Libreria l){
		u = utente;
        libreria=l;

        frame = new JFrame("Cerca Libro");
		frame.setSize(1920, 1080);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));

        JPanel panel = new JPanel();


        titolo = new JTextField(50);
        autore = new JTextField(50);
        autoreA = new JTextField(50);
        anno = new JTextField(4);

        JPanel piccolo1 = new JPanel();
        JPanel piccolo2 = new JPanel();
        JPanel piccolo3 = new JPanel();
        
        JButton rT = new JButton("RicercaTitolo");
        rT.addActionListener(this);
        JButton rA = new JButton("RicercaAutore");
        rA.addActionListener(this);
        JButton rAA = new JButton("RicercaAutAnno");
        rAA.addActionListener(this);

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
        homeC.addActionListener(this);

        label = new JLabel("");

        frame.add(panel);
        frame.add(homeC);
        frame.add(label);
        	
		frame.setVisible(true);
		
	}


	public CercaLibro(Utente utente){
		u = utente;
        libreria = null;
        
        frame = new JFrame("Cerca Libro");
		frame.setSize(1920, 1080);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));

        JPanel panel = new JPanel();

        titolo = new JTextField(50);
        autore = new JTextField(50);
        autoreA = new JTextField(50);
        anno = new JTextField(4);

        JPanel piccolo1 = new JPanel();
        JPanel piccolo2 = new JPanel();
        JPanel piccolo3 = new JPanel();
        
        JButton rT = new JButton("RicercaPerTitolo");
        rT.addActionListener(this);
        JButton rA = new JButton("RicercaPerAutore");
        rA.addActionListener(this);
        JButton rAA = new JButton("RicercaPerAutAnno");
        rAA.addActionListener(this);

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
        homeC.addActionListener(this);

        label = new JLabel("");

        frame.add(panel);
        frame.add(homeC, BorderLayout.EAST);
        frame.add(label);
        	
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args){
		CercaLibro cerca = new CercaLibro();
	}
	
	@Override
    public void actionPerformed(ActionEvent e){
			JButton pulsante = (JButton) e.getSource();
             if(pulsante.getText().equals("RicercaTitolo")){
                String filePath = new File("Libri.dati.csv").getAbsolutePath();
                boolean isFirstLine = true;
                ArrayList<Libro> risultati = new ArrayList<Libro>();
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
                        Libro libro;
                        if(title.contains(titolo.getText())){
                            libro = new Libro(columns[1], columns[2], annoFileStr, columns[4]);
                            risultati.add(libro);
                        }
                    }
                    if(!(risultati.isEmpty())){
                        ListaLibri l = new ListaLibri(risultati, u, libreria);      
                        frame.dispose();                 
                    }else{
                        label.setFont(new Font("Arial", Font.PLAIN, 15));
                        label.setText("Nessun risultato prodotto");
                        frame.revalidate();
                        frame.repaint(); 
                    }
                } catch (IOException z) {
                    z.printStackTrace();
                }
            }else if(pulsante.getText().equals("RicercaAutore")){
                String filePath = new File("Libri.dati.csv").getAbsolutePath();
                boolean isFirstLine = true;
                ArrayList<Libro> risultati = new ArrayList<Libro>();
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
                        Libro libro;
                        if(author.contains(autore.getText())){
                            libro = new Libro(columns[1], columns[2], annoFileStr, columns[4]);
                            risultati.add(libro);
                        }
                    }
                    if(!(risultati.isEmpty())){
                        ListaLibri l = new ListaLibri(risultati, u, libreria);
                        frame.dispose();
                    }else{
                        label.setFont(new Font("Arial", Font.PLAIN, 15));
                        label.setText("Nessun Risultato Prodotto");
                        frame.revalidate();
                        frame.repaint(); 
                    }
                } catch (IOException z) {
                    z.printStackTrace();
                }
            }else if(pulsante.getText().equals("RicercaAutAnno")){
                String filePath = new File("Libri.dati.csv").getAbsolutePath();
                boolean isFirstLine = true;
                ArrayList<Libro> risultati = new ArrayList<Libro>();
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
                        Libro libro;
                        if((author.contains(autoreA.getText()) && (annoFileStr.equals(anno.getText())))){
                            libro = new Libro(columns[1], columns[2], annoFileStr, columns[4]);
                            risultati.add(libro);
                        }
                    }
                    if(!(risultati.isEmpty())){
                        ListaLibri l = new ListaLibri(risultati, u, libreria);
                        frame.dispose();
                    }else{
                        label.setFont(new Font("Arial", Font.PLAIN, 15));
                        label.setText("Nessun Risultato Prodotto");
                        frame.revalidate();
                        frame.repaint(); 
                    }
                    
                } catch (IOException z) {
                    z.printStackTrace();
                }
           }else if(pulsante.getText().equals("RicercaPerTitolo")){
                String filePath = new File("Libri.dati.csv").getAbsolutePath();
                boolean isFirstLine = true;
                ArrayList<Libro> risultati = new ArrayList<Libro>();
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
                        Libro libro;
                        if(title.contains(titolo.getText())){
                            libro = new Libro(columns[1], columns[2], annoFileStr, columns[4]);
                            risultati.add(libro);
                        }
                    }
                    if(!(risultati.isEmpty())){
                        ListaLibri l = new ListaLibri(risultati, u); 
                        frame.dispose();                      
                    }else{
                        label.setFont(new Font("Arial", Font.PLAIN, 15));
                        label.setText("Nessun risultato prodotto");
                        frame.revalidate();
                    }
                } catch (IOException z) {
                    z.printStackTrace();
                }
            }else if(pulsante.getText().equals("RicercaPerAutore")){
                String filePath = new File("Libri.dati.csv").getAbsolutePath();
                boolean isFirstLine = true;
                ArrayList<Libro> risultati = new ArrayList<Libro>();
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
                        Libro libro;
                        if(author.contains(autore.getText())){
                            libro = new Libro(columns[1], columns[2], annoFileStr, columns[4]);
                            risultati.add(libro);
                        }
                    }
                    if(!(risultati.isEmpty())){
                        ListaLibri l = new ListaLibri(risultati, u);
                        frame.dispose();
                    }else{
                        label.setFont(new Font("Arial", Font.PLAIN, 15));
                        label.setText("Nessun Risultato Prodotto");
                        frame.revalidate();
                        frame.repaint();
                    }
                } catch (IOException z) {
                    z.printStackTrace();
                }
            }else if(pulsante.getText().equals("RicercaPerAutAnno")){
                String filePath = new File("Libri.dati.csv").getAbsolutePath();
                boolean isFirstLine = true;
                ArrayList<Libro> risultati = new ArrayList<Libro>();
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
                        Libro libro;
                        if((author.contains(autoreA.getText()) && (annoFileStr.equals(anno.getText())))){
                            libro = new Libro(columns[1], columns[2], annoFileStr, columns[4]);
                            risultati.add(libro);
                        }
                    }
                    if(!(risultati.isEmpty())){
                        ListaLibri l = new ListaLibri(risultati, u);
                        frame.dispose();
                    }else{
                        label.setFont(new Font("Arial", Font.PLAIN, 15));
                        label.setText("Nessun Risultato Prodotto");
                        frame.revalidate();
                        frame.repaint();
                    }
                    
                } catch (IOException z) {
                    z.printStackTrace();
                }
            }else if(u.getRegistrato()){
                    GUI casa = new GUI(u);
                    frame.dispose();
                }else{
                    GUI casa = new GUI();
                    frame.dispose();
                }  
            }  
	}