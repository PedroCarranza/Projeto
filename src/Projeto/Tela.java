package Projeto;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tela {

    Projeto pr;
    Menu men;
    player p1;

    BufferedImage background;
    int bgdx = 0;
    boolean down = false;

    public Tela(Projeto p) {
        pr = p;
        men = new Menu(pr);
        p1 = new player(p);
        try {
            background = ImageIO.read(new File("back.png"));
        } catch (IOException e) {
            System.err.println("Imagem back não encontrada");
        }

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
        try {
            g.drawImage(background.getSubimage(bgdx, 0, pr.getWidth(), 1080), 0, 0, pr.getWidth(), pr.getHeight(), null);
        } catch (Exception e) {
            bgdx = 0;
            g.drawImage(background.getSubimage(bgdx, 0, pr.getWidth(), 1080), 0, 0, pr.getWidth(), pr.getHeight(), null);
        }
        bgdx += 5;
        //g.setColor(Color.red);
        //g.drawOval(pr.lis.mx - 50, pr.lis.my - 50, 100, 100);

        p1.updatePlayer(g);

        g.dispose();
    }
}
