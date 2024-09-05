import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 

public class Login implements ActionListener{
	
	JTextField nome;
	JTextField password;
	JFrame login;
	
	public Login(){
		
		login = new JFrame("Login");
		login.setSize(990, 540);
		login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		JLabel domanda = new JLabel();
		domanda.setFont(new Font("Arial", Font.PLAIN, 15));
		domanda.setText("Inserisci nome utente e password");
		login.add(domanda);
		
		JPanel entrata = new JPanel();
		nome = new JTextField(20);
		password = new JTextField(20);
		entrata.add(nome, BorderLayout.NORTH);
		entrata.add(password, BorderLayout.CENTER);
		login.add(nome);
		login.add(password);
		

		JButton homeL = new JButton("Home");
		JPanel homeP = new JPanel();
		JButton entra = new JButton("Login");
		homeL.addActionListener(this);
		homeP.add(homeL, BorderLayout.CENTER);
		login.add(homeP);
		entra.addActionListener(this);
		entrata.add(entra, BorderLayout.SOUTH);
		login.add(entrata);
		
		login.setVisible(true);
		
	}
	
	public static void main(String[] args){
		Login l = new Login();
	}
	
	@Override
    public void actionPerformed(ActionEvent e){
			JButton pulsante = (JButton)e.getSource();


			if(pulsante.getText().equals("Login")){
				String id = nome.getText();
				String pass = password.getText();
				String filePath = new File("UtentiRegistrati.dati.txt").getAbsolutePath();
				boolean isFirstLine = true;
				
				try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
					String line;
					while ((line = br.readLine()) != null) {
						if (isFirstLine) {
							isFirstLine = false;
							continue;
						}

						String[] columns = line.split(";");
						if((id.equals(columns[4])) && (pass.equals(columns[5]))){
							Utente utente = new Utente(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);
							GUI gui = new GUI(utente);
						}else{
							nome.setText("");
							password.setText("");
							JLabel sbagliato = new JLabel("Nome utente o password errati");
							login.add(sbagliato);
						}
						
					}
				} catch (IOException z) {
					z.printStackTrace();
				} 
			}else{
				GUI homePage = new GUI();
			}

	}
}