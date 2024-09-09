import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.util.*;

public class CreaLibreria implements ActionListener{

    Utente u;
    JTextField nomeLibreria;
    JFrame frameL;

    public CreaLibreria(){
        JFrame frame = new JFrame();
    }

    public CreaLibreria(Utente utente){
        u = utente;
        
        frameL = new JFrame("Crea Libreria");
		frameL.setSize(990, 540);
		frameL.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        

        JButton homeL = new JButton("Home");
        JPanel panel = new JPanel();
        homeL.addActionListener(this);

        nomeLibreria = new JTextField("nomeLibreria",20);
        JButton creazione = new JButton("Invio");   
        
        panel.add(homeL, BorderLayout.WEST);
        panel.add(creazione, BorderLayout.EAST);
        frameL.add(panel, BorderLayout.NORTH);

        frameL.setVisible(true);
    }

    public static void main(String[] args){
        CreaLibreria cl = new CreaLibreria();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		JButton pulsante = (JButton)e.getSource();
        if(pulsante.getText() == "Home"){
            if(u.getRegistrato()){
                GUI gui = new GUI(u);
            }else{
                GUI gui = new GUI();
            }
        }else{
            if(!(nomeLibreria.getText().equals(""))){
                try{
                    File librerieTxt = new File("Librerie.dati.csv");
                    FileWriter fileout = new FileWriter(librerieTxt);
                    BufferedWriter bw = new BufferedWriter(fileout);
                    bw.write(u.getId() + ";");
                    bw.write(nomeLibreria.getText() + ";");
                    bw.flush();
                    bw.close();
                    GUI homePage = new GUI(); 
                    LibreriaGUI lg = new LibreriaGUI(u);  
                }catch(IOException z){
                    z.printStackTrace();
                }
                        
            }

        }
	}
}