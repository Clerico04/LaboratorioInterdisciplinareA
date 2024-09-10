/**
 * La classe Login gestisce l'interfaccia grafica per il login degli utenti.
 * Fornisce un modulo per inserire nome utente e password e verifica
 * le credenziali contro un file di utenti registrati.
 * 
 * @author LucaClerici756176CO
 * @author AlessandroMonaci757003CO
 * @version 1.0
 */

package bookrecommender;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 

public class Login implements ActionListener{
	
	JTextField nome;
	JTextField password;
	JFrame login;
	
	/**
     * Costruttore che crea la finestra di login con i campi per inserire
     * nome utente e password e i pulsanti per effettuare il login o tornare alla schermata principale.
     */
	public Login(){
		
		login = new JFrame("Login");
		login.setSize(1920, 1080);
		login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		login.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		JLabel domanda = new JLabel();
		domanda.setFont(new Font("Arial", Font.PLAIN, 15));
		domanda.setText("Inserisci nome utente e password");
		login.add(domanda);
		
		JPanel entrata = new JPanel();
		nome = new JTextField(20);
		password = new JTextField(20);
		entrata.add(nome, BorderLayout.NORTH);
		entrata.add(password, BorderLayout.CENTER);	

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
	
	/**
     * Gestisce gli eventi dei pulsanti nella finestra di login.
     *
     * @param e L'evento di azione generato dal pulsante premuto.
     */
	@Override
    public void actionPerformed(ActionEvent e){
			JButton pulsante = (JButton)e.getSource();

			if(pulsante.getText().equals("Login")){
				String id = nome.getText();
				String pass = password.getText();
				String filePath = new File("../data/UtentiRegistrati.dati.csv").getAbsolutePath();
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
							login.dispose();
						}else{
							// If no valid user found
							nome.setText("");
							password.setText("");
							JLabel sbagliato = new JLabel("");
							sbagliato.setFont(new Font("Arial", Font.PLAIN, 15));
							sbagliato.setText("Nome utente o password errati");
							login.add(sbagliato);
							login.validate();
						}
						
					}
				} catch (IOException z) {
					z.printStackTrace();
				} 
			}else{
				GUI homePage = new GUI();
				login.dispose();
			}

	}
}