import java.io.*;

class Utente{

    private String nome;
    private String cognome;
    private String cf;
    private String email;
    private String id;
    private String password;

    public Utente(String nome, String cognome, String cf, String email, String id, String password){
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.email = email;
        this.id = id;
        this.password = password;
    }

    public Utente(){
        this.nome = "Guest";
        this.cognome = "";
        this.cf = "";
        this.email = "";
        this.id = "";
        this.password = "";
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
            String line;
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
                }else{
                    if((autore.contains(ricerca[0]))&&((annoFile == annoCercato)))
                        System.out.println("TITOLO: " + titolo + " AUTORE: " + autore + " ANNO PUBBLICAZIONE: " + annoFile + " EDITORE: " + editore);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
}