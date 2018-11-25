package Projeto;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Tiro{

    Projeto pr;
    int x, y, t;
    int s;
    Rectangle ret;
    boolean remove;

    public Tiro(int x, int y, Projeto p, int tipo){
        pr = p;
        remove = false;
        this.x = x;
        this.y = y;
        t = tipo;
        ret = new Rectangle();
       
    }

    public void draw(Graphics2D g) {
        s = pr.getHeight() / 720;
        if (t == 0) {
            g.drawImage(pr.tela.p1.sprites.getSubimage(217, 7, 9, 3), x, y, s * 25, s * 13, null);
        } else {
            g.drawImage(pr.tela.p1.sprites.getSubimage(67, 235, 11, 5), x, y, s * 25, s * 13, null);
        }
    }

    public void update() {
        s = pr.getHeight() / 720;
        ret.setBounds(x, y, s*25, s*13);
        if (t == 0) {
            x += 30;
        } else {
            x -= 30;
        }
        if (x > pr.getWidth() || x<0) {
            remove = true;
        }

    }
}
