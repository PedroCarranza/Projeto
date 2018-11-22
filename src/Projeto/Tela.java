package Projeto;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Tela {

    Projeto pr;

    Menu men;

    Player p1;

    BufferedImage background;

    int bgdx = 0;

    boolean down = false;

    int estadoTela = 0;

    ArrayList<Tiro> tiros;
    
    ArrayList<Inimigo> inimigos;
    
    Instant last = Instant.now();

    public Tela(Projeto p) {

        pr = p;

        men = new Menu(pr);

        p1 = new Player(pr);

        tiros = new ArrayList<>();
        
        inimigos = new ArrayList<>();
        
        inimigos.add(new Inimigo(p));

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
            g.drawImage(background.getSubimage(bgdx, 0, 1920, 1080), 0, 0, pr.getWidth(), pr.getHeight(), null);
        } catch (Exception e) {
            bgdx = 0;
            g.drawImage(background.getSubimage(bgdx, 0, 1920, 1080), 0, 0, pr.getWidth(), pr.getHeight(), null);
        }
        bgdx += 8;
        //g.setColor(Color.red);
        //g.drawOval(pr.lis.mx - 50, pr.lis.my - 50, 100, 100);

        if (pr.lis.tiro && Duration.between(last, Instant.now()).toMillis() > 800) {
            last = Instant.now();
            pr.tela.tiros.add(new Tiro(pr.tela.p1.px + 32, pr.tela.p1.py + 36, pr));
        }

        for (int i = 0; i < tiros.size(); i++) {
            tiros.get(i).drawNUpdate(g);
        }
        
        for (int i = 0; i < inimigos.size(); i++) {
            inimigos.get(i).update(g);
            for (int j = 0; j < tiros.size(); j++) {
                if(inimigos.get(i).collided(tiros.get(j))){
                    inimigos.remove(i);
                    break;
                }
            }
        }

        p1.updatePlayer(g);
        

        g.dispose();
    }
}
