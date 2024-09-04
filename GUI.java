import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.io.*;

public class GUI implements ActionListener{
	
	JLabel titolo;
	JFrame home;
	Utente u;
	
	public GUI(){
		u= new Utente();

		home = new JFrame("Home");
        home.setSize(1920, 1080); 
		home.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        titolo = new JLabel();
        JPanel titoloCentrato = new JPanel();
        titolo.setFont(new Font("Arial", Font.BOLD, 45));
        titolo.setText("Book recommender by Alessando Monaci e Luca Clerici");
        titoloCentrato.add(titolo, BorderLayout.CENTER);
        home.add(titoloCentrato, BorderLayout.APPLET);
		
		JButton login = new JButton("Login");
		JPanel logRegPiccoli = new JPanel();
		login.addActionListener(this);
		logRegPiccoli.add(login, BorderLayout.WEST);

		JButton cercaLibro = new JButton("Cerca Libro");
		cercaLibro.addListener(this);
		home.add(cercaLibro);
		
		JButton registrazione = new JButton("Registrazione");
		registrazione.addActionListener(this);
		logRegPiccoli.add(registrazione, BorderLayout.CENTER);

		JButton library = new JButton("Librerie");
		library.addActionListener(this);
		logRegPiccoli.add(library, BorderLayout.EAST);
		
		
		home.add(logRegPiccoli, BorderLayout.NORTH);
        
        home.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        home.setVisible(true);
	}
	
	public GUI(Utente utente){
		u = utente;
		
		home = new JFrame("Home");
        home.setSize(1920, 1080); 
		home.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        titolo = new JLabel();
        JPanel titoloCentrato = new JPanel();
        titolo.setFont(new Font("Arial", Font.BOLD, 45));
        titolo.setText("Book recommender by Alessando Monaci e Luca Clerici");
        titoloCentrato.add(titolo, BorderLayout.CENTER);
        home.add(titoloCentrato, BorderLayout.NORTH);
		
		JPanel logRegPiccoli = new JPanel();
		
		JButton cercaLibro = new JButton("Cerca Libro");
		cercaLibro.addListener(this);
		logRegPiccoli.add(cercaLibro, BorderLayout.CENTER);
		
		JLabel registrato = new JLabel();
		registrato.setFont(new Font("Arial", Font.PLAIN, 15));
		registrato.setText("Login efettuato con successo!");

		JButton library = new JButton("Librerie");
		library.addActionListener(this);
		logRegPiccoli.add(library, BorderLayout.EAST);
		
		home.add(registrato);
		home.add(logRegPiccoli);
		
        home.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        home.setVisible(true);	
	}

    public static void main(String[] args){
		GUI gui = new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		JButton pulsante = (JButton)e.getSource();
        if(pulsante.getText() == "Login"){
				Login log = new Login();
			}else if (pulsante.getText() == "Registrazione"){
				Registrazione reg = new Registrazione();
			}else if(pulsante.getText() == "Cerca Libro"){
				CercaLibro search = new CercaLibro(u);	
			}else if(pulsante.getText() == "Librerie"){
				if(u.getRegistrato()){
					LibreriaGUI puzzo = new LibreriaGUI(u);
				}else{
					JLabel registrati = new JLabel();
					registrati.setFont(new Font("Arial", Font.ITALIC, 15));
					registrati.setText("Librerie accessibili solo agli utenti registrati e loggati, registrati e accedi per utilizzare questa funzione");
					home.add(registrati);
				}
			}
	}
}