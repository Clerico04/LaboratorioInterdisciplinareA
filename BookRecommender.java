import java.io.*;

public class BookRecommender{
    public static void main(String[] args){

    }

    private static boolean esistenza(String id){
        String filePath = new File("UtentiRegistrati.dati.txt").getAbsolutePath();
        boolean isFirstLine = true;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] columns = line.split(";");
                if(id.equals(columns[4])){
                    System.out.println("Id gia utilizzato, usano un altro");
                    return true;
                }   
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public static void registrazione() throws IOException{
        File utentiRegistrati = new File(UtentiRegistrati.dati.txt);
        FileWriter fileout = new FileWriter(utentiRegistrati);
        BufferedWriter bw = new BufferedWriter(fileout);
        System.out.print("Inserisci il nome (vai a capo per terminare):\n");
        String nome = Input.readLine();
        bw.write(nome + ";");
        System.out.print("Inserisci il cognome (vai a capo per terminare): \n");
        String cognome = Input.readLine();
        bw.write(cognome + ";");
        System.out.print("Inserisci il codice fiscale (vai a capo per terminare): \n");
        String cf = Input.readLine();
        bw.write(cf + ";");
        System.out.print("Inserisci il email (vai a capo per terminare): \n");
        String email = Input.readLine();
        bw.write(email + ";");
        do{
            System.out.print("Inserisci il nome utente (vai a capo per terminare): \n");
            String id = Input.readLine();
        }while(esistenza(id));
        bw.write(id + ";");
        System.out.print("Inserisci la tua password (vai a capo per terminare): \n");
        String password = Input.readLine();
        bw.write(password);
        bw.flush();
        bw.close();
    }

    public static Utente login(){
        System.out.print("Inserisci il nome utente (vai a capo per terminare): \n");
        String id = Input.readLine();
        System.out.print("Inserisci la tua password (vai a capo per terminare): \n");
        String password = Input.readLine();
        
        String filePath = new File("UtentiRegistrati.dati.txt").getAbsolutePath();
        boolean isFirstLine = true;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] columns = line.split(";");
                if((id.equals(columns[4])) && (password.equals(columns[5]))){
                   Utente u = new Utente(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);
                   return u;
                }else{
                    Utente u = new Utente();
                    return u;
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}