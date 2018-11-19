package Projeto;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tela {

    Projeto pr;
    Menu men;
    BufferedImage sprites;
    Point p1Pos;
    int frame = 0;

    public Tela(Projeto p) {
        pr = p;
        p1Pos = new Point(20, pr.getHeight() / 2);
        men = new Menu(pr);
        try {
            sprites = ImageIO.read(new File("spsheet.png"));
        } catch (IOException e) {
            System.err.println("Imagem não encontrada");
        }
    }

    public void update() {
        Graphics2D g = pr.image.createGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, pr.getWidth(), pr.getHeight());

        frame++;
        if (frame > 1) {
            frame = 0;
        }

        if (pr.lis.stop) {
            men.drawMenu(g);
        } else {
            g.setColor(Color.red);
            g.drawOval(pr.lis.mx - 50, pr.lis.my - 50, 100, 100);
            if (pr.lis.up && !pr.lis.down) {
                if (pr.lis.right) {

                } else {

                }
            } else if (!pr.lis.up && pr.lis.down) {
                if (pr.lis.right) {

                } else {

                }
            } else {
                if (pr.lis.right) {
                    g.drawImage(sprites.getSubimage(232 + 44*frame, 43, 40, 39), p1Pos.x, p1Pos.y, null);
                } else {

                }
            }
        }
        g.dispose();
    }
}
