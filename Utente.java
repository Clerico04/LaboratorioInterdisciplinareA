import java.io.*;

public class Utente{

    private String nome;
    private String cognome;
    private String cf;
    private String email;
    private String id;
    private String password;
    private boolean registrato;

    public Utente(String nome, String cognome, String cf, String email, String id, String password){
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.email = email;
        this.id = id;
        this.password = password;
        this.registrato = true;
    }

    public Utente(){
        this.nome = "Guest";
        this.cognome = "";
        this.cf = "";
        this.email = "";
        this.id = "";
        this.password = "";
        this.registrato = false;
    }

    private static void cercaLibro(String[] ricerca, char c){
        String filePath = new File("Libri.dati.csv").getAbsolutePath();
        boolean isFirstLine = true;
        switch(c){
            case 't': 
                int[] colonne = new int[1];
                colonne[0] = 1;
                break;
            case 'a': 
                int[] colonne = new int[1];
                colonne[0] = 2;
                break;
            case 'c':
                int annoCercato = Integer.parseInt(ricerca[1]);
                int[] colonne = new int[2];
                colonne[0] = 2;
                colonne[1] = 3;
                break;
        }
        
        System.out.println("Risultati della ricerca: \n");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] columns = line.split(";");
                String isbn = columns[0];
                String titolo = columns[1];
                String autore = columns[2];
                Int annoFile = Integer.parseInt(columns[3]);
                String editore = columns[4];
                
                if(colonne.length==1){
                    if(columns[colonne[0]].contains(ricerca[0]))
                        System.out.println("TITOLO: " + titolo + " AUTORE: " + autore + " ANNO PUBBLICAZIONE: " + annoFile + " EDITORE: " + editore);
                        Libro libro = new Libro(columns[1], columns[2], Integer.parseInt(columns[3]), columns[4]);
                }else{
                    if((autore.contains(ricerca[0]))&&((annoFile == annoCercato)))
                        System.out.println("TITOLO: " + titolo + " AUTORE: " + autore + " ANNO PUBBLICAZIONE: " + annoFile + " EDITORE: " + editore);
                        Libro libro = new Libro(columns[1], columns[2], Integer.parseInt(columns[3]), columns[4]);
                } 

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void visualizzaLibro(Libro libro){
        System.out.println("TITOLO: " + libro.titolo + " AUTORE: " + libro.autore + " ANNO PUBBLICAZIONE: " + libro.anno + " EDITORE: " + libro.editore + "VALUTAZIONE: " + libro.votoFinale);
        boolean dettagli = false;
        if(!libro.valutato){
            System.out.println("Il libro non ha ancora ricevuto valutazioni");
        }else if(dettagli){
            int[] valutazioni = new int[6];
            valutazioni = libro.leggiValutazione(libro.titolo);
            System.out.println("STILE: " + valutazioni[1] + "CONTENUTO: " + valutazioni[2] + "GRADEVOLEZZA: " + valutazioni[3] + "ORIGINALITA': " + valutazioni[4] + "EDIZIONE: " + valutazioni[5]);
        }
        if(!libro.consigliato){
            System.out.println("I lettori non hanno ancora consigliato nessun libro");
        }else{
            System.out.println("I lettori consigliano: ");
            String[] consigli = libro.leggiConsigli(libro.titolo);
            for(int i=0; i<consigli.length(); i++){
                System.out.println(consigli[i]);
            }
        }
              
    }

    private void registraLibreria(){
        if(this.registrato){
            File librerie = new File(Librerie.dati.csv);
            FileWriter fileout = new FileWriter(librerie);
            BufferedWriter bw = new BufferedWriter(fileout);
            System.out.print("Come vuoi chiamare la libreria? (vai a capo per terminare): \n");
            String nomeLibreria = Input.readLine();
            bw.write(this.id + ";");
            bw.write(nomeLibreria + ";");

            
            bw.flush();
            bw.close();
        }else{
            registrazione();
        }

    private static boolean getRegistrato(){
        return registrato;
    }
    }
}
