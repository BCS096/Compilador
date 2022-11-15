/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.view;

import compilador.lexic.Scanner;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 *
 * @author emanu
 */
public class NombreCompilador extends JFrame{
    
    /**
     * The variable where all lexic will be stored for further display
     */
    //OBVIAMENTE usar String[] o similares para guardar individual e imprimir individual
    public static StringBuilder lexicTxt = new StringBuilder("Lexic:\n");

    public NombreCompilador(){
        String[] pesao = new String[1];
        pesao[0] = "gramatica.txt";
        try {
            compilador.lexic.Scanner scan = new Scanner(new FileReader(new File("gramatica.txt")));
            scan.main(pesao);
            this.setSize(800,600);
            this.add(tabs());
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
        } catch (FileNotFoundException ex) {
            System.out.println("NO!");
            Logger.getLogger(NombreCompilador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private JPanel tabs(){
        JPanel tabs = new JPanel();
        
        JTabbedPane lexic = new JTabbedPane();
        buildLexic(lexic);
        JTabbedPane sintactic = new JTabbedPane();
        JTabbedPane semantic = new JTabbedPane();
        
        tabs.add(lexic);
        tabs.add(sintactic);
        tabs.add(semantic);
        
        return tabs;
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NombreCompilador API = new NombreCompilador();
    }
    
    public static void add(String forAdding){
        lexicTxt.append(forAdding);
    }

    private void buildLexic(JTabbedPane lexic) {
        JTextArea a = new JTextArea();
        a.setText(lexicTxt.toString());
        lexic.add(a);
    }
    
}
