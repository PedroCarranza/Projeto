package Projeto;

import java.awt.Color;
import java.awt.Graphics2D;

public class Tela {

    Projeto pr;
    Menu men;
    player p1;

    

    public Tela(Projeto p) {
        pr = p;
        men = new Menu(pr);
        p1 = new player(p);
        
    }

    public void updatePaused() {
        Graphics2D g = pr.image.createGraphics();
        men.drawMenu(g);
        g.dispose();
    }

    public void update() {
        Graphics2D g = pr.image.createGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, pr.getWidth(), pr.getHeight());

        g.setColor(Color.red);
        g.drawOval(pr.lis.mx - 50, pr.lis.my - 50, 100, 100);
        
        p1.updatePlayer(g);

        g.dispose();
    }
}
