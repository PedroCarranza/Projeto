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
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.Timer;

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

    Instant last;

    Timer t;
    int cont = 0;
    Random r = new Random();

    boolean boss = false;

    int pontuacao = 0;
    String texto = "";

    public Tela(Projeto p) {

        pr = p;

        men = new Menu(pr);

        p1 = new Player(pr);

        tiros = new ArrayList<>();

        inimigos = new ArrayList<>();

        last = Instant.now();

        t = new Timer(10000, e -> {
            if (cont >= 10) {
                cont = 0;
                inimigos.add(new Inimigo(p, 3));
                boss = true;
            } else if (!boss) {
                inimigos.add(new Inimigo(p, r.nextInt(3)));
                cont++;
            }
        });

        try {
            background = ImageIO.read(new File("back.png"));
        } catch (IOException e) {
            System.err.println("Imagem back não encontrada");
        }

    }

    public void update() {
        //Começa o timer se tiver parado
        if (estadoTela == 10) {

            t.start();
            //incrementa a posição do background
            bgdx += 8;

            //Testa se deve atirar e se passou tempo sufisciente para sair o tiro
            if (pr.lis.tiro && Duration.between(last, Instant.now()).toMillis() > 500) {
                last = Instant.now();
                pr.tela.tiros.add(new Tiro(pr.tela.p1.px + 32, pr.tela.p1.py + 19 * pr.getHeight() / 360, pr, 0));
            }

            //Atualiza as posições dos tiros e verifica colisões
            for (int i = 0; i < tiros.size(); i++) {
                tiros.get(i).update();
                if (tiros.get(i).t == 0) {
                    for (int j = 0; j < inimigos.size(); j++) {
                        if (inimigos.get(j).collided(tiros.get(i))) {
                            inimigos.get(j).dano(1);
                            tiros.get(i).remove = true;
                            break;
                        }
                    }
                } else if (tiros.get(i).t == 1) {
                    if (p1.collide(tiros.get(i))) {
                        p1.dano();
                        tiros.get(i).remove = true;
                    }
                }
            }

            //Remove os tiros que devem ser removidos
            for (int i = 0; i < tiros.size(); i++) {
                if (tiros.get(i).remove) {
                    tiros.remove(i);
                }
            }

            //Atualiza os inimigos
            for (int i = 0; i < inimigos.size(); i++) {
                if (inimigos.get(i).collide(p1.getRet())) {
                    estadoTela = 7;
                }
                inimigos.get(i).update();
            }

            //Atualiza a pontuação
            texto = Integer.toString(pontuacao);
            while (texto.length() < 7) {
                texto = '0' + texto;
            }

            //Atualiza o jogador
            p1.updatePlayer();
        } else {
            t.stop();
        }

    }

    public void draw() {
        //Pega o gráfico da BufferedImage
        Graphics2D g = pr.image.createGraphics();

        //Se não estiver pausado
        if (estadoTela == 10) {

            //Desenha background
            try {
                g.drawImage(background.getSubimage(bgdx, 0, 1920, 1080), 0, 0, pr.getWidth(), pr.getHeight(), null);
            } catch (Exception e) {
                bgdx = 0;
                g.drawImage(background.getSubimage(bgdx, 0, 1920, 1080), 0, 0, pr.getWidth(), pr.getHeight(), null);
            }

            try {
                //Desenha os tiros na tela
                for (int i = 0; i < tiros.size(); i++) {
                    tiros.get(i).draw(g);
                }

                //Desenha os inimigos na tela
                for (int i = 0; i < inimigos.size(); i++) {
                    inimigos.get(i).draw(g);
                }
            } catch (Exception e) {

            }

            //Desenha o jogador
            p1.drawPlayer(g);

            //Desenha a pontuação
            g.setColor(Color.YELLOW);
            g.setFont(new Font(Font.MONOSPACED, Font.ITALIC + Font.BOLD, 24));
            FontMetrics fonte = g.getFontMetrics(g.getFont());
            g.drawString("Pontuação: " + texto, pr.getWidth() - (16 * (texto.length() + 10)), fonte.getAscent());

        } else {
            men.drawMenu(g);
        }
        //Limpa
        g.dispose();
    }
}
