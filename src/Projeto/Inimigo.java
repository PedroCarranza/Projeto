package Projeto;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import javax.imageio.ImageIO;

public class Inimigo {

    int px, py, w, h, ix, iy, passo;
    int hp;
    int pontos;
    Rectangle ret;
    Instant last = Instant.now();
    Projeto pr;
    BufferedImage sprites;
    int frame = 0, moveSpeed = 5;
    int scaler = 2;
    int tipo;
    Random r = new Random();
    int mov;

    public Inimigo(Projeto p, int t) {
        pr = p;
        tipo = t;
        switch (t) {
            case 0:
                ret = new Rectangle(117, 89);
                w = 117;
                h = 89;
                ix = 862;
                iy = 21;
                passo = 120;
                hp = 4;
                pontos = 100;
                mov = 0;
                break;
            case 1:
                ret = new Rectangle(73, 69);
                w = 73;
                h = 69;
                ix = 805;
                iy = 142;
                passo = 77;
                hp = 2;
                pontos = 300;
                mov = 1;
                break;
            case 2:
                ret = new Rectangle(78, 75);
                w = 78;
                h = 75;
                ix = 66;
                iy = 368;
                passo = 81;
                hp = 3;
                pontos = 200;
                mov = 2;
                break;
            case 3:
                ret = new Rectangle(337, 267);
                w = 337;
                h = 267;
                ix = 1668;
                iy = 17;
                passo = 0;
                hp = 10;
                pontos = 1000;
                mov = 0;
                break;
        }
        px = pr.getWidth() - r.nextInt(pr.getWidth() / 2) - w;
        py = pr.getHeight() - r.nextInt(pr.getHeight() - h) - h;
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
            if (tipo == 3) {
                pr.tela.boss = false;
            }
            pr.tela.inimigos.remove(this);
        }
    }

    public void draw(Graphics2D g) {
        scaler = pr.getHeight() / 360;
        g.drawImage(sprites.getSubimage(ix + passo * (frame / 5), iy, w, h), px, py, w / 3 * scaler, h / 3 * scaler, null);
    }

    public boolean collide(Rectangle rec) {
        return ret.intersects(rec);
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
            pr.tela.tiros.add(new Tiro(px, py + scaler * h / 6, pr, 1));
        }
        int movIni = (int) (5 * Math.sin(Instant.now().toEpochMilli() / 1000));
        switch (mov) {
            case 0:
                if (py + movIni > 0 && py + movIni < pr.getHeight() - h / 3 * scaler) {
                    py += movIni;
                }
                break;
            case 1:
                if (py + movIni > 0 && py + movIni < pr.getHeight() - h / 3 * scaler) {
                    py += movIni;
                }
                if (px + movIni > 0 && px + movIni < pr.getWidth() - w / 3 * scaler) {
                    px += movIni;
                }
                break;
            case 2:
                if (px + movIni > 0 && px + movIni < pr.getWidth() - w / 3 * scaler) {
                    px += movIni;
                }
                break;
        }
        
        ret.setLocation(px, py);

    }
}
