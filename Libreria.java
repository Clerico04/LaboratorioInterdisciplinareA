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
        Libreria l = new Libreria();
				
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
                        libri[i] = searchBookTitle(columns[i+2]);
                    }
					l = new Libreria(nomeLibreria, libri);
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        return l; 
    }

    public static Libro searchBookTitle (String tit){
        String filePath = new File("Libri.dati.csv").getAbsolutePath();
        boolean isFirstLine = true;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] columns = line.split(";");
                String isbn = columns[0];
                String title = columns[1];
                String author = columns[2];
                String annoFileStr = columns[3].replace("\"", "");
                String editore = columns[4];
                Libro cercato;
                if(title.equals(tit)){
                    cercato = new Libro(columns[1], columns[2], annoFileStr, columns[4]);
                    return cercato;
                }
            }
        } catch (IOException z) {
            z.printStackTrace();
        }
        return null;
    }

    public Libro[] getElencoLibri(){
        return this.libroni;
    }
}