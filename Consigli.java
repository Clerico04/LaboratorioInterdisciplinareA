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
    Libreria libreria; 
    JFrame frame;
    Libro l;

    public Consigli(){
        JFrame frame = new JFrame();
    }

    public Consigli(Libro libro, Libreria bookshelf, Utente utente){
        u = utente;
        l = libro;
        libreria=bookshelf;

        frame = new JFrame("Consigli Libro");
		frame.setSize(990, 540);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel informazioni = new JLabel();
        informazioni.setFont(new Font("Arial", Font.PLAIN, 20));
        informazioni.setText("3 Libri consigliati");
        frame.add(informazioni);

        JButton conferma = new JButton("Conferma");
        JButton homeV = new JButton("Home");
        JPanel homeVPanel = new JPanel();
        homeV.addActionListener(this);
        conferma.addActionListener(this);
        homeVPanel.add(homeV, BorderLayout.NORTH);

        JPanel combini = new JPanel();
        box1 = new JComboBox();
        box2 = new JComboBox();
        box3 = new JComboBox();
        combini.add(box1);
        combini.add(box2);
        combini.add(box3);

        riempiCombo(box1);
        riempiCombo(box2);
        riempiCombo(box3);
        
        frame.add(homeVPanel);
        frame.add(combini);
        frame.add(conferma);

        frame.setVisible(true);
    }

    public static void main(String[] args){
        Consigli c = new Consigli();
    }

    @Override
    public void actionPerformed(ActionEvent e){
		JButton pulsante = (JButton)e.getSource();  
        if(pulsante.getText() == "Home"){
            if(u.getRegistrato()){
                GUI gui = new GUI(u);
            }else{
                GUI gui = new GUI();
            }
        }else if(pulsante.getText() == "Conferma"){
            try{
                inserisciConsigli(new String[]{(String)box1.getSelectedItem() , (String)box2.getSelectedItem(), (String)box3.getSelectedItem()});
            }catch(IOException z){
                z.printStackTrace();
            }
        }       
    }
    
    public void riempiCombo(JComboBox boxino){
        Libro[] elementi = libreria.getElencoLibri();
        boxino.addItem(" ");
        for(Libro t:elementi){
            boxino.addItem(t.getTitolo());
        }
    }

    public void inserisciConsigli(String[] advice) throws IOException{
        File utentiRegistrati = new File("ConsigliLibri.dati.csv");
        try{
            FileWriter fileout = new FileWriter(utentiRegistrati);
        BufferedWriter bw = new BufferedWriter(fileout);
        String s1 = advice[0];
        String s2 = advice[1];
        String s3 = advice[2];
        bw.write(l.getTitolo() + ";");
        if((!(s1.equals(" "))) && (!(s1.equals(s2))) && (!(s1.equals(s3)))){
            bw.write(s1 + ";");
        }else{
            bw.write("Nessun consiglio" + ";");
        }
        if((!(s2.equals(" "))) && (!(s2.equals(s1))) && (!(s2.equals(s3)))){
            bw.write(s2 + ";");
        }else{
            bw.write("Nessun consiglio" + ";");
        } 
        if((!(s3.equals(" "))) && (!(s3.equals(s2))) && (!(s3.equals(s1)))){
            bw.write(s3 + ";");
        }else{
            bw.write("Nessun consiglio" + ";");
        }   
        bw.flush();
        bw.close();
        }catch(IOException z){
            z.printStackTrace();
        }
    }  
}