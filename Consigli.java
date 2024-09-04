import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.util.*;

public class Consigli implements ActionListener{

    Utente u;
    JComboBox box1;
    JComboBox box2;
    JComboBox box3;

    public Consigli(){
        JFrame frame = new JFrame();
    }

    public Consigli(Libro libro, Libreria libreria, Utente utente){
        u = utente
        libro = l;

        JFrame frameV = new JFrame("Consigli Libro");
		frameV.setSize(990, 540);
		frameV.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel informazioni = new JLabel();
        informazioni.setFont(new Font("Arial", Font.PLAIN, 20));
        informazioni.setText();
        frameV.add(informazioni);

        JButton dettagli = new JButton("Dettagli");
        JButton homeV = new JButton("Home");
        JPanel homeVPanel = new JPanel();
        homeV.addListener(this);
        recensioni.addListener(this);
        homeVPanel.add(homeV, BorderLayout.NORTH);

        box1 = new ComboBox();
        box2 = new ComboBox();
        box3 = new ComboBox();
        
        frameV.add(dettagli); 
        frameV.add(homeVPanel);

        frameV.setVisible(true);
    }

    public static void main(String[] args){
        Consigli c = new Consigli();
    }

    @Override
    public void actionPerformed(ActionEvent e) throws IOException{
		JButton pulsante = (JButton)e.getSource();  
        if(pulsante.getText() == "Home"){
            if(u.getRegistrato){
                GUI gui = new GUI(u);
            }else{
                GUI gui = new GUI();
            }
        }       
    }  

}