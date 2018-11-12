package Projeto;

import java.awt.Color;
import java.awt.Graphics2D;

public class Tela {

    Projeto pr;

    public Tela(Projeto p) {
        pr = p;
    }

    public void update() {
        Graphics2D g = pr.image.createGraphics();
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, pr.getWidth(), pr.getHeight());
        
        g.setColor(Color.red);
        g.drawOval(pr.lis.mx - 50, pr.lis.my - 50, 100, 100);
        
        g.dispose();
    }
}
