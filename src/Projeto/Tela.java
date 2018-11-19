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
    BufferedImage sprites;
    int p1Pos[];
    int frame = 0, moveSpeed = 5;

    public Tela(Projeto p) {
        pr = p;
        p1Pos = new int[2];
        p1Pos[0] = 20;
        p1Pos[1] = 360;
        men = new Menu(pr);
        try {
            sprites = ImageIO.read(new File("spsheet.png"));
        } catch (IOException e) {
            System.err.println("Imagem não encontrada");
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
        if (pr.lis.right && p1Pos[0] + 100 + moveSpeed < pr.getWidth()) {
            p1Pos[0] += moveSpeed;
        }
        if (pr.lis.left && p1Pos[0] - moveSpeed > 0) {
            p1Pos[0] -= moveSpeed;
        }
        if (pr.lis.down && p1Pos[1] + 39 + moveSpeed < pr.getHeight()) {
            p1Pos[1] += moveSpeed;
        }
        if (pr.lis.up && p1Pos[1] - moveSpeed > 0) {
            p1Pos[1] -= moveSpeed;
        }

        frame++;
        if (frame > 9) {
            frame = 0;
        }

        g.setColor(Color.red);
        g.drawOval(pr.lis.mx - 50, pr.lis.my - 50, 100, 100);
        if (pr.lis.up && !pr.lis.down) {
            if (!pr.lis.left) {
                g.drawImage(sprites.getSubimage(229 + 46 * (frame / 5), 5, 43, 35), p1Pos[0], p1Pos[1], 43 * 2, 35 * 2, null);
            } else {
                g.drawImage(sprites.getSubimage(321, 5, 36, 35), p1Pos[0] + 12, p1Pos[1], 36 * 2, 35 * 2, null);
            }
        } else if (!pr.lis.up && pr.lis.down) {
            if (!pr.lis.left) {
                g.drawImage(sprites.getSubimage(229 + 46 * (frame / 5), 89, 43, 39), p1Pos[0], p1Pos[1], 43 * 2, 39 * 2, null);
            } else {
                g.drawImage(sprites.getSubimage(321, 89, 36, 39), p1Pos[0] + 12, p1Pos[1], 36 * 2, 39 * 2, null);
            }
        } else {
            if (!pr.lis.left) {
                g.drawImage(sprites.getSubimage(229 + 46 * (frame / 5), 42, 43, 39), p1Pos[0], p1Pos[1], 43 * 2, 39 * 2, null);
            } else {
                g.drawImage(sprites.getSubimage(321, 42, 36, 39), p1Pos[0] + 12, p1Pos[1], 36 * 2, 39 * 2, null);
            }
        }

        g.dispose();
    }
}
