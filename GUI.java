import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 

public class GUI implements ActionListener{
    public static void main(String[] args){
        JFrame home = new jFrame("Home");
        home.setSize(1920, 1080); //prova home.pack();
        home.setResizable(true);
        home.setVisible(true);
        
        home.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel titolo = new JLabel("BookRecommender");
        home.add(titolo, BorderLayout.APPLET);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}