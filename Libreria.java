import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.util.*;

public class Liberia{
    
    Utente u;

    public Libreria(){
        JFrame f = new JFrame();
    }

    public Libreria (Utente utente){
        u = utente;

        JFrame frameL = new JFrame("Cerca Libro");
		frameL.setSize(990, 540);
		frameL.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        

        JButton homeL = new JButton("Home");
        JButton creazione = new JButton("Crea Libreria");
        JPanel panel = new JPanel();
        homeL.addListener(this);
        panel.add(homeL, BorderLayout.WEST);
        panel.add(creazione, BorderLayout.EAST);
        frameL.add(panel, BorderLayout.NORTH);
        


        frameL.setVisible(true);
    }
  
      
    public static void main(String[] args){
        Libreria libreria = new Libreria();
   }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
		JButton pulsante = (JButton)e.getSource();
        if(pulsante.getText == ){

        }else{
            if(u.getRegistrato()){
                GUI home = new GUI(u);
            }else{
                GUI home = new GUI();
            }
        }
	}
}