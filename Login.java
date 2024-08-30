import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 

public class Login implements ActionListener{
	
	JTextField nome;
	JTextField password;
	
	public Login(){
		
		JFrame login = new JFrame("Login");
		login.setSize(990, 540);
		login.setDeaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		JLabel domanda = new JLabel();
		domanda.setFont(new Font("Arial", Font.PLAIN, 15));
		domanda.setText("Inserisci nome utente e password");
		login.add(domanda);
		
		JPanel entrata = new JPanel();
		nome = new JTextField();
		password = new JTextField();
		entrata.add(nome, BorderLayout.NORTH);
		entrata.add(password, BorderLayout.CENTER);
		login.add(nome);
		login.add(password);
		
		JButton entra = new JButton("Login");
		entra.addListener(this);
		entrata.add(entra, BorderLayout.SOUTH);
		login.add(entrata);
		
		login.setVisible(true);
		
	}
	
	public static void main(String[] args){
		Login login = new Login();
	}
	
	@Override
    public void actionPerformed(ActionEvent e) throws IOException{
			JButton pulsante = (JButton)e.getSource();
			String id = nome.getText();
			String password = password.getText();
			
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
					if((id.equals(columns[4])) && (password.equals(columns[5]))){
					   Utente utente = new Utente(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);
					   GUI gui = new GUI(utente);
					}else{
						DUE BOTTONI, RITENTA LOGIN O REGISTRATI
					}
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
	}
}