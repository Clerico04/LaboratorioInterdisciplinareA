/**
 * La classe Libreria rappresenta una collezione di libri e fornisce metodi per gestire e cercare librerie.
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
import java.util.*;

public class Libreria{

    private String titolo;
    private Libro[] libroni;

    /**
     * Costruttore di default che inizializza la libreria con un titolo vuoto e un array di libri nullo.
     */
    public Libreria(){
        titolo="";
        libroni = null;
    }

    /**
     * Costruttore che crea una libreria con un titolo specificato e un array di libri.
     *
     * @param titolo Il titolo della libreria.
     * @param libroni Un array di oggetti Libro che rappresenta i libri della libreria.
     */
    public Libreria(String titolo, Libro[] libroni){
        this.titolo= titolo;
        this.libroni = libroni;
    }

    /**
     * Restituisce il titolo della libreria.
     *
     * @return Il titolo della libreria.
     */
    public String getTitolo(){
        return this.titolo;
    }

    /**
     * Ottiene una libreria specificata dal nome e dall'utente.
     *
     * @param nomeLibreria Il nome della libreria da cercare.
     * @param username Il nome utente dell'utente che possiede la libreria.
     * @return La libreria corrispondente al nome e all'utente specificati, o una libreria vuota se non trovata.
     */
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

    /**
     * Cerca un libro per titolo.
     *
     * @param tit Il titolo del libro da cercare.
     * @return Un oggetto Libro se trovato, altrimenti null.
     */
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

    /**
     * Restituisce l'elenco dei libri presenti nella libreria.
     *
     * @return Un array di oggetti Libro che rappresenta i libri della libreria.
     */
    public Libro[] getElencoLibri(){
        return this.libroni;
    }
}