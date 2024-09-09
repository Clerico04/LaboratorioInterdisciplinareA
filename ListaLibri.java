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
    Libreria libreria;
    JFrame finestra;

	public ListaLibri(){
		finestra = new JFrame("Login");
		finestra.setSize(1920, 1080);
		finestra.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		finestra.setVisible(true);
	}

    public ListaLibri(ArrayList<Libro> arg, Utente utente, Libreria l){
        u = utente;
        libreria = l;

		finestra = new JFrame("ListaLibri");
		finestra.setSize(1920, 1080);
		finestra.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        finestra.setLayout(new FlowLayout(FlowLayout.CENTER));
		
        JPanel bottoni = new JPanel();
        JButton succ = new JButton ("Successivo");
        succ.addActionListener(this);
        JButton ped = new JButton ("Precedente");
        ped.addActionListener(this);
        JButton visa = new JButton ("Visualizza il Libro");
        visa.addActionListener(this);
        JButton homeList = new JButton("Home");
        homeList.addActionListener(this);

        listone = arg;
        Libro libro = listone.get(i);
        libretto = new JLabel(libro.stampaLibro());

        bottoni.add(succ, BorderLayout.EAST);
        bottoni.add(ped, BorderLayout.WEST);
        bottoni.add(visa, BorderLayout.CENTER);

        JPanel grossa = new JPanel();
        grossa.add(libretto, BorderLayout.NORTH);
        grossa.add(bottoni, BorderLayout.CENTER);

        finestra.add(homeList, BorderLayout.NORTH);
        finestra.add(grossa, BorderLayout.CENTER);

		finestra.setVisible(true);
	}

    public ListaLibri(ArrayList<Libro> arg, Utente utente){
        u = utente;

		finestra = new JFrame("ListaLibri");
		finestra.setSize(1920, 1080);
		finestra.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        finestra.setLayout(new FlowLayout(FlowLayout.CENTER));
		
        JPanel bottoni = new JPanel();
        JButton succ = new JButton ("Successivo");
        succ.addActionListener(this);
        JButton ped = new JButton ("Precedente");
        ped.addActionListener(this);
        JButton visa = new JButton ("Visualizza Libro");
        visa.addActionListener(this);
        JButton homeList = new JButton("Home");
        homeList.addActionListener(this);

        listone = arg;
        Libro libro = listone.get(i);
        libretto = new JLabel(libro.stampaLibro());

        bottoni.add(succ, BorderLayout.EAST);
        bottoni.add(ped, BorderLayout.WEST);
        bottoni.add(visa, BorderLayout.CENTER);

        JPanel grossa = new JPanel();
        grossa.add(libretto, BorderLayout.NORTH);
        grossa.add(bottoni, BorderLayout.CENTER);

        finestra.add(homeList, BorderLayout.NORTH);
        finestra.add(grossa, BorderLayout.CENTER);

		finestra.setVisible(true);
	}
	
	public static void main(String[] args){
		Login ListaLibri = new Login();
	}
	
	@Override
    public void actionPerformed(ActionEvent e){
			JButton pulsante = (JButton)e.getSource();
            Libro l;
			if(pulsante.getText().equals("Successivo")){
                if(!(i+2>listone.size())){
                    i++;
                    l=listone.get(i);
                    libretto.setText(l.stampaLibro());
                }
			}else if(pulsante.getText().equals("Precedente")){
                if(!(i-1<0)){
                    i--;
                    l=listone.get(i);
                    libretto.setText(l.stampaLibro());	
			    }
            }else if(pulsante.getText().equals("Visualizza Libro")){
                VisualizzaLibro v = new VisualizzaLibro(u, listone.get(i));
                finestra.dispose();
            }else if(pulsante.getText().equals("Visualizza il Libro")){
                VisualizzaLibro v = new VisualizzaLibro(u, listone.get(i), libreria);
                finestra.dispose();
            }else{
                if(u.getRegistrato()){
                    GUI homePage = new GUI(u);
                    finestra.dispose();
                }else{
                    GUI homePage = new GUI();
                    finestra.dispose();
                }
            }
    }
}