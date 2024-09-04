import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.util.*;

public class ListaLibri implements ActionListener{
	
    JLabel libretto;
    ArrayList<Libro> listone;
    int i = 0;
    Utente u;

	public ListaLibri(){
		JFrame finestra = new JFrame("Login");
		finestra.setSize(990, 540);
		finestra.setDeaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		finestra.setVisible(true);
	}
    
    public ListaLibri(ArrayList<Libro> arg, Utente utente){
        u = utente;

		JFrame finestra = new JFrame("Login");
		finestra.setSize(990, 540);
		finestra.setDeaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
        JPanel bottoni = JPanel();
        JButton succ = new JButton ("Successivo");
        succ.addListener(this);
        JButton ped = new JButton ("Precedente");
        ped.addListener(this);
        JButton visa = new JButton ("Visualizza Libro");
        visa.addListener(this);
        JButton homeList = new JButton("Home");
        homeList.addListener(this);

        listone = arg;
        Libro libro = listone.get(i);
        libretto = new JLabel(libro.stampaLibro());

        bottoni.add(succ, BorderLayout.EAST);
        bottoni.add(ped, BorderLayout.WEST);
        bottoni.add(visa, BorderLayout.CENTER);

        JPanel grossa = new JPanel();
        grossa.add(libretto, BorderLayout.NORTH);
        grossa.add(bottoni, BorderLayout.CENTER);

        finestra.add(homeList, BorderLayout.APPLET);
        finestra.add(grossa, BorderLayout.CENTER);

		finestra.setVisible(true);
	}
	
	public static void main(String[] args){
		Login ListaLibri = new ListaLibri();
	}
	
	@Override
    public void actionPerformed(ActionEvent e) throws IOException{
			JButton pulsante = (JButton)e.getSource();
            Libro l;
			if(pulsante.getText().equals("Successivo")){
                if(!(i+2>listone.getSize())){
                    i++;
                    l=listone.get(i);
                    libretto.setText(l.stampaLibro);
                }
			}else if(pulsante.getText().equals("Precedente")){
                if(!(i-1<0)){
                    i--;
                    l=listone.get(i);
                    libretto.setText(l.stampaLibro);	
			    }
            }else if(pulsante.getText().equals("Visualizza Libro")){
                VisualizzaLibro v = new VisualizzaLibro(u, listone.get(i));
            }else{
                if(u.getRegistrato()){
                    GUI homePage = new GUI(u);
                }else{
                    GUI homePage = new GUI();
                }
            }
    }
}