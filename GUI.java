import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.io.*;

public class GUI implements ActionListener{
	
	JLabel titolo;
	
	public GUI(){
		JFrame home = new JFrame("Home");
        home.setSize(1920, 1080); 
        
        titolo = new JLabel();
        JPanel titoloCentrato = new JPanel();
        titolo.setFont(new Font("Arial", Font.BOLD, 45));
        titolo.setText("Book recommender by Alessando Monaci e Luca Clerici");
        titoloCentrato.add(titolo, BorderLayout.CENTER);
        home.add(titoloCentrato, BorderLayout.NORTH);
		
		JButton login = new JButton("Login");
		JPanel logRegPiccoli = new JPanel();
		login.addActionListener(this);
		logRegPiccoli.add(login, BorderLayout.NORTH);

		
		JButton registrazione = new JButton("Registrazione");
		registrazione.addActionListener(this);
		logRegPiccoli.add(registrazione, BorderLayout.SOUTH);
		home.add(logRegPiccoli);
        
        home.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        home.setVisible(true);
	}
	
	public GUI(Utente utente){
		JFrame home = new JFrame("Home");
        home.setSize(1920, 1080); 
        
        titolo = new JLabel();
        JPanel titoloCentrato = new JPanel();
        titolo.setFont(new Font("Arial", Font.BOLD, 45));
        titolo.setText("Book recommender by Alessando Monaci e Luca Clerici");
        titoloCentrato.add(titolo, BorderLayout.CENTER);
        home.add(titoloCentrato, BorderLayout.NORTH);
		
		JLabel registrato = new JLabel();
		registrato.setFont(new Font("Arial", Font.PLAIN, 15));
		registrato.setText("Login efettuato con successo!");
		home.add(registrato);
		
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
				System.out.println("Cazzo");
			}else if (pulsante.getText() == "Registrazione"){
				System.out.println("Culo");
			}
	}
}