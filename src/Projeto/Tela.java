package Projeto;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Tela {

    Projeto pr;

    Menu men;

    Player p1;
    Player2 p2;

    BufferedImage background;

    int bgdx = 0;

    boolean down = false;

    int estadoTela = 0;

    ArrayList<Tiro> tiros;

    ArrayList<Inimigo> inimigos;

    Instant last = Instant.now();

    Timer t;

    int pontuacao = 0;

    public Tela(Projeto p) {

        pr = p;

        men = new Menu(pr);

        p1 = new Player(pr);

        tiros = new ArrayList<>();

        inimigos = new ArrayList<>();

        t = new Timer(10000, e -> {
            inimigos.add(new Inimigo(p));
        });

        try {
            background = ImageIO.read(new File("back.png"));
        } catch (IOException e) {
            System.err.println("Imagem back não encontrada");
        }

    }

    public void updatePaused() {
        Graphics2D g = pr.image.createGraphics();
        men.drawMenu(g);
        t.stop();
        g.dispose();
    }

    public void update() {
        Graphics2D g = pr.image.createGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, pr.getWidth(), pr.getHeight());

        t.start();

        try {
            g.drawImage(background.getSubimage(bgdx, 0, 1920, 1080), 0, 0, pr.getWidth(), pr.getHeight(), null);
        } catch (Exception e) {
            bgdx = 0;
            g.drawImage(background.getSubimage(bgdx, 0, 1920, 1080), 0, 0, pr.getWidth(), pr.getHeight(), null);
        }
        bgdx += 8;

        if (pr.lis.tiro && Duration.between(last, Instant.now()).toMillis() > 800) {
            last = Instant.now();
            pr.tela.tiros.add(new Tiro(pr.tela.p1.px + 32, pr.tela.p1.py + 19 * pr.getHeight() / 360, pr));
        }
        
        if (pr.lis.tiro && Duration.between(last, Instant.now()).toMillis() > 800) {
            last = Instant.now();
            pr.tela.tiros.add(new Tiro(pr.tela.p1.px + 32, pr.tela.p1.py + 19 * pr.getHeight() / 360, pr));
        }
        
        if (p2 != null && p2.tiro && Duration.between(last, Instant.now()).toMillis() > 800) {
            last = Instant.now();
            pr.tela.tiros.add(new Tiro(p2.px + 32, p2.py + 19 * pr.getHeight() / 360, pr));
        }

        for (int i = 0; i < tiros.size(); i++) {
            tiros.get(i).drawNUpdate(g);
        }

        for (int i = 0; i < inimigos.size(); i++) {
            inimigos.get(i).update(g);
            for (int j = 0; j < tiros.size(); j++) {
                if (inimigos.get(i).collided(tiros.get(j))) {
                    inimigos.get(i).dano(1);
                    tiros.remove(j);
                    break;
                }
            }
        }

        int digitos = String.valueOf(pontuacao).length();
        String texto = Integer.toString(pontuacao);
        while (texto.length() < 7) {
            texto = '0' + texto;
        }
        g.setColor(Color.YELLOW);
        g.setFont(new Font(Font.MONOSPACED, Font.ITALIC + Font.BOLD, 24));
        FontMetrics fonte = g.getFontMetrics(g.getFont());
        g.drawString("Pontuação: " + texto, pr.getWidth() - (16 * (texto.length() + 10)), fonte.getAscent());

        p1.updatePlayer(g);
        if(p2!=null){
            p2.updatePlayer(g);
        }

        g.dispose();
    }
}
