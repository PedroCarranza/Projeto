package Projeto;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Menu extends JFrame {
    
    JButton resumir = new JButton("Resumir");
    
    JButton opcoes = new JButton("Opções");
    
    JButton salvaresair = new JButton("Salvar e Sair");
    
    JButton sair = new JButton("Sair");
    
    public Menu() {
        setLayout(new GridLayout(1,4));
        add(resumir);
        add(opcoes);
        add(salvaresair);
        add(sair);
    }
    
}
