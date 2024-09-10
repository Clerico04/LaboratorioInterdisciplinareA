/**
 * La classe Registrazione gestisce l'interfaccia grafica per la registrazione
 * di nuovi utenti. Permette agli utenti di inserire i loro dati personali e 
 * di registrarsi se tutte le informazioni sono valide e non esistono già.
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


public class Registrazione implements ActionListener{

    JTextField nome;
    JTextField cognome;
    JTextField cf;
    JTextField email;
    JTextField username;
    JTextField password;
    JFrame registrazione;
    JLabel esistente ;
    JLabel riempi ;
    
    /**
     * Costruttore che crea la finestra di registrazione con i campi per inserire
     * i dati dell'utente e i pulsanti per inviare i dati o tornare alla schermata principale.
     */
    public Registrazione(){

        registrazione = new JFrame("Registrazione");
        registrazione.setSize(990, 540);
		registrazione.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        registrazione.setLayout(new FlowLayout(FlowLayout.CENTER));
        esistente = new JLabel("");
        riempi = new JLabel();

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
        invia.addActionListener(this);
        registrazione.add(invia);

        JButton homeR = new JButton("Home");
        homeR.addActionListener(this);
        registrazione.add(homeR);
        registrazione.add(esistente);
        registrazione.add(riempi);

        registrazione.setVisible(true);

    }

    /**
     * Gestisce gli eventi dei pulsanti nella finestra di registrazione.
     *
     * @param e L'evento di azione generato dal pulsante premuto.
     */
    @Override
    public void actionPerformed(ActionEvent e){
			JButton pulsante = (JButton)e.getSource();
            if(pulsante.getText().equals("Invio")){
                String name = nome.getText();
                String surname = cognome.getText();
                String codice = cf.getText();
                String mail = email.getText();
                String id = username.getText();
                String pass = password.getText();
                
                try{
                    if((name.length()>0) && (surname.length()>0) && (codice.length()>0) && (mail.length()>0) && (id.length()>0)){
                        if((pass.length()>0) && (!esistenza(id, 4)) && (!esistenza(mail, 3))){
                                File utentiRegistrati = new File("UtentiRegistrati.dati.csv");
                                FileWriter fileout = new FileWriter(utentiRegistrati, true);
                                BufferedWriter bw = new BufferedWriter(fileout);

                                bw.newLine();
                                bw.write(name + ";");
                                bw.write(surname + ";");
                                bw.write(codice + ";");
                                bw.write(mail + ";");
                                bw.write(id + ";");
                                bw.write(pass);

                                bw.flush();
                                bw.close();

                                GUI homePage = new GUI();
                                registrazione.dispose();
                        }else{
                            esistente.setFont(new Font("Arial", Font.PLAIN, 15));
                            esistente.setText("Nome utente o email gia' in uso");
                            registrazione.validate();
                        }
                    }else{
                        riempi.setFont(new Font("Arial", Font.PLAIN, 15));
                        riempi.setText("Riempi tutti i campi");
                        registrazione.validate();
                    }
                    }catch(IOException z){
                        z.printStackTrace();
                    }
                }else{
                    GUI homePage = new GUI();
                    registrazione.dispose();
                }
    }
			
    /**
     * Verifica se un dato identificativo (username o email) esiste già nel file degli utenti registrati.
     *
     * @param id Il dato identificativo da verificare.
     * @param colonna L'indice della colonna nel file CSV da controllare.
     * @return true se il dato esiste già, false altrimenti.
     */
    private static boolean esistenza(String id, int colonna){
        String filePath = new File("UtentiRegistrati.dati.csv").getAbsolutePath();
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
        return false;
    }
    
}