package Projeto;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Tiro {

    Projeto pr;
    int x, y, t;
    Rectangle ret;
    boolean remove;

    public Tiro(int x, int y, Projeto p, int tipo) {
        pr = p;
        remove = false;
        this.x = x;
        this.y = y;
        t = tipo;
        if (t == 0) {
            ret = new Rectangle(x, y, 9, 3);
        }else{
            ret = new Rectangle(x, y, 11, 5);
        }
    }

    public void draw(Graphics2D g) {
        int s = pr.getHeight() / 720;
        if (t == 0) {
            g.drawImage(pr.tela.p1.sprites.getSubimage(217, 7, 9, 3), x, y, s * 15, s * 5, null);
        } else {
            g.drawImage(pr.tela.p1.sprites.getSubimage(67, 235, 11, 5), x, y, s * 15, s * 5, null);
        }
    }

    public void update() {
        if (t == 0) {
            x += 30;
        } else {
            x -= 30;
        }
        ret.setLocation(x, y);
        if (x > pr.getWidth()) {
            remove = true;
        }

    }
}
