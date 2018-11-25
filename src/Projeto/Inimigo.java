package Projeto;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import javax.imageio.ImageIO;

public class Inimigo{

    int px, py;
    int hp = 3;
    int pontos = 100;
    Instant last = Instant.now();
    Projeto pr;
    BufferedImage sprites;
    int frame = 0, moveSpeed = 5;
    int scaler = 2;
    int tipo;

    public Inimigo(Projeto p, int t) {
        pr = p;
        px = pr.getWidth() - 200;
        py = pr.getHeight() - 150;
        tipo = t;
        try {
            sprites = ImageIO.read(new File("nmigo.png"));
        } catch (IOException e) {
            System.err.println("Imagem não encontrada");
        }
    }

    public boolean collided(Tiro t) {
        return new Rectangle(px, py, 117, 89).intersects(t.ret);
    }

    public void dano(int d) {
        hp -= d;
        if (hp < 0) {
            pr.tela.pontuacao += pontos;
            pr.tela.inimigos.remove(this);
        }
    }

    public void draw(Graphics2D g) {
        scaler = pr.getHeight() / 360;
        g.drawImage(sprites.getSubimage(862 + 120 * (frame / 5), 21, 117, 89), px, py, 43 * scaler, 39 * scaler, null);
    }

    public void update() {
        scaler = pr.getHeight() / 360;
        moveSpeed = 3 * scaler;
        frame++;
        if (frame > 14) {
            frame = 0;
        }
        int fire = (int) (10 * Math.random());
        if (fire > 8 && Duration.between(last, Instant.now()).toMillis() > 600) {
            last = Instant.now();
            pr.tela.tiros.add(new Tiro(px, py+scaler*19, pr, 1));
        }
        int movIni = (int) (5 * Math.sin(Instant.now().toEpochMilli() / 1000));
        if (py + movIni > 0 && py + movIni < pr.getHeight() - 89) {
            py += movIni;
        }
    }
}
