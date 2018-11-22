package Projeto;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Tiro {

    Projeto pr;
    int x, y;
    Rectangle ret;

    public Tiro(int x, int y, Projeto p) {
        pr = p;
        this.x = x;
        this.y = y;
        ret = new Rectangle(x, y, 9, 3);
    }

    public void drawNUpdate(Graphics2D g) {
        int s = pr.getHeight()/720;
        x += 30;
        ret.setLocation(x, y);
        g.drawImage(pr.tela.p1.sprites.getSubimage(217, 7, 9, 3), x, y, s*15, s*5, null);
        if(x > pr.getWidth()){
            pr.tela.tiros.remove(this);
        }

    }
}
