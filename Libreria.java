import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.util.*;

public class Libreria{

    private String titolo;
    private Libro[] libroni;

    public Libreria(){
        titolo="";
        libroni = null;
    }

    public Libreria(String titolo, Libro[] libroni){
        this.titolo= titolo;
        this.libroni = libroni;
    }

    public String getTitolo(){
        return this.titolo;
    }

    public static Libreria getLibreria(String nomeLibreria, String username){
        String filePath = new File("Librerie.dati.csv").getAbsolutePath();
		boolean isFirstLine = true;
        Libro[] libri;
        Libreria l;
				
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}

				String[] columns = line.split(";");
				if((columns[0].equals(username))&&(columns[1].equals(nomeLibreria))){
                    libri = new Libro[columns.length-2];
                    for(int i=0; i<(columns.length-2); i++){
                        libri[i]=columns[i+2];
                    }
					l = new Libreria(nomeLibreria, libri);
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        return l; 
    }

    public Libro[] getElencoLibri(){
        return this.libroni;
    }
}