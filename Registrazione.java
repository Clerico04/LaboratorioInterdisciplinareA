import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 


public class Registrazione implements ActionListener{

    JTextField nome;
    JTextField cognome;
    JTextField cf;
    JTextField email;
    JTextField username;
    JTextField password;
        
    public Registrazione(){

        JFrame registrazione = new JFrame("Registrazione");
        registrazione.setSize(990, 540);
		registrazione.setDeaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel chiedi = new JLabel();
		chiedi.setFont(new Font("Arial", Font.PLAIN, 15));
		chiedi.setText("Inserisci i tuoi dati");
		registrazione.add(chiedi);

        nome = new JTextField("Nome", 20);
        cognome = new JTextField("Cognome", 20);
        cf = new JTextField("Codice fiscale", 16);
        email = new JTextField("Email", 40);
        username = new JTextField("Username", 20);
        password = new JTextField("Password", 20);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.add(nome, BorderLayout.NORTH);
        panel1.add(cognome, BorderLayout.CENTER);
        panel1.add(cf, BorderLayout.SOUTH);
        panel2.add(email, BorderLayout.NORTH);
        panel2.add(username, BorderLayout.CENTER);
        panel2.add(password, BorderLayout.SOUTH);
        registrazione.add(panel1);
        registrazione.add(panel2);
        
        JButton invia = new JButton("Invio");
        invia.addListener(this);
        registrazione.add(invia);

        JButton homeR = new JButton("Home");
        homeR.addListener(this);
        registrazione.add(homeR);

        registrazione.setVisible(true);

    }

    public static void main(String[] args){
		Registrazione registrazione = new Registrazione();
	}

    @Override
    public void actionPerformed(ActionEvent e) throws IOException{
			JButton pulsante = (JButton)e.getSource();
            if(pulsante.getText().equals("Registrazione")){
                String nome = nome.getText();
                String cognome = cognome.getText();
                String cf = cf.getText();
                String email = email.getText();
                String username = username.getText();
                String password = password.getText();

                if((nome.length()>0) && (cognome.length()>0) && (cf.length()>0) && (email.length()>0) && (username.length()>0)){
                    if((password.length()>0) && (!esistenza(username, 4)) && (!esistenza(email, 3))){
                        File utentiRegistrati = new File("UtentiRegistrati.dati.txt");
                        FileWriter fileout = new FileWriter(utentiRegistrati);
                        BufferedWriter bw = new BufferedWriter(fileout);
                        bw.write(nome + ";");
                        bw.write(cognome + ";");
                        bw.write(cf + ";");
                        bw.write(email + ";");
                        bw.write(username + ";");
                        bw.write(password);
                        bw.flush();
                        bw.close();
                        GUI homePage = new GUI();
                    }else{
                        JLabel esistente = new JLabel();
                        esistente.setFont(new Font("Arial", Font.PLAIN, 15));
                        esistente.setText("Nome utente o email già in uso");
                        registrazione.add(esistente);
                    }
                }else{
                    JLabel riempi = new JLabel();
                    riempi.setFont(new Font("Arial", Font.PLAIN, 15));
                    riempi.setText("Riempi tutti i campi");
                    registrazione.add(riempi);
                }
            }else{
                GUI homePage = new GUI();
            }
			

            
	}

    private static boolean esistenza(String id, int colonna){
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
                if(id.equals(columns[colonna])){
                    return true;
                }   
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
}