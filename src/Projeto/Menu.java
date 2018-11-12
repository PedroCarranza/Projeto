package Projeto;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Menu extends JFrame {
    
    JButton resumir = new JButton("Resumir");
    
    JButton opcoes = new JButton("Opções");
    
    JButton menu = new JButton("Menu");
    
    JButton salvaresair = new JButton("Salvar e Sair");
    
    JButton sair = new JButton("Sair");
    
    public Menu() {
        setSize(200, 100);

        
        
        setLayout(new GridLayout(1,5));
        add(resumir);
        add(opcoes);
        add(menu);
        add(salvaresair);
        add(sair);
        
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    public void main() {
        Menu teste = new Menu();
        
    }
    
}
