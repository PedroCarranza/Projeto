package Projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JTextField;

public class Menu {

    Projeto pr;

    BufferedImage pausebackground;

    BufferedImage menubackground;

    BufferedImage comandos;

    BufferedImage nome;

    BufferedImage over;

    ArrayList<Botao> btns = new ArrayList<>();

    Ranking rank;

    StringBuilder salvarnome = new StringBuilder();

    Over ov;

    public Menu(Projeto pj) {
        pr = pj;

        rank = new Ranking(pr);

        ov = new Over(pr);

        try {
            pausebackground = ImageIO.read(new File("pauseback.png"));
            menubackground = ImageIO.read(new File("menuback.png"));
            comandos = ImageIO.read(new File("comandos.png"));
            nome = ImageIO.read(new File("logo.png"));
            over = ImageIO.read(new File("over.png"));
        } catch (IOException e) {
            System.err.println("Imagem não encontrada");
        }
    }

    public void drawMenu(Graphics2D g) {
        btns.clear();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, pr.getWidth(), pr.getHeight());
        if (pr.tela.estadoTela == 0) {
            g.drawImage(menubackground, 0, 0, pr.getWidth(), pr.getHeight(), pr);
        } else {
            g.drawImage(pausebackground, 0, 0, pr.getWidth(), pr.getHeight(), pr);
        }
        switch (pr.tela.estadoTela) {
            case 0:
                btns.add(new Botao("", pr));
                btns.add(new Botao("", pr));
                btns.add(new Botao("Um Jogador", pr));
                btns.add(new Botao("LAN", pr));
                btns.add(new Botao("Ranking", pr));
                btns.add(new Botao("Alterar Resolução", pr));
                btns.add(new Botao("Sair", pr));
                g.drawImage(nome, pr.getWidth() / 17 - pr.getWidth() / 17, pr.getHeight() / 80, pr.getWidth(), pr.getWidth() / 5, pr);
                break;
            case 1:
                btns.add(new Botao("Resumir", pr));
                btns.add(new Botao("Alterar Resolução", pr));
                btns.add(new Botao("Menu", pr));
                btns.add(new Botao("Sair", pr));
                break;
            case 2:
                btns.add(new Botao("", pr));
                btns.add(new Botao("", pr));
                btns.add(new Botao("", pr));
                btns.add(new Botao("Ok", pr));
                g.drawImage(comandos, pr.getWidth() / 2 - pr.getWidth() / 6, pr.getHeight() / 8, pr.getWidth() / 3, pr.getWidth() / 5, pr);
                break;
            case 3:
                btns.add(new Botao("Criar", pr));
                btns.add(new Botao("Entrar", pr));
                btns.add(new Botao("Voltar", pr));
                break;
            case 4:
                g.setColor(Color.yellow);
                g.setFont(new Font(Font.MONOSPACED, Font.ITALIC + Font.BOLD, 24));
                g.drawString("Conectando...", pr.getWidth() / 3 - pr.getWidth() / 8, pr.getHeight() / 8);
                btns.add(new Botao("", pr));
                btns.add(new Botao("", pr));
                btns.add(new Botao("", pr));
                btns.add(new Botao("", pr));
                btns.add(new Botao("Voltar", pr));
                break;
            case 5:
                btns.add(new Botao("720 x 480", pr));
                btns.add(new Botao("800 x 600", pr));
                btns.add(new Botao("1280 x 720", pr));
                btns.add(new Botao("1920 x 1080", pr));
                btns.add(new Botao("Voltar", pr));
                break;
            case 6:
                btns.add(new Botao("", pr));
                btns.add(new Botao("", pr));
                btns.add(new Botao("", pr));
                btns.add(new Botao("", pr));
                btns.add(new Botao("", pr));
                btns.add(new Botao("Voltar", pr));
                rank.DrawRank(g);
                break;
            case 7:
                if (!pr.lis.gotChar) {
                    if ((pr.lis.ch > 64 && pr.lis.ch < 91) || (pr.lis.ch > 96 && pr.lis.ch < 123) || pr.lis.ch == ' ') {
                        salvarnome.append(pr.lis.ch);
                    } else {
                        if (salvarnome.length() > 0 && pr.lis.ch == 8) {
                            salvarnome.deleteCharAt(salvarnome.length() - 1);
                        }
                    }
                }
                pr.lis.gotChar = true;
                Rectangle ret = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
                g.setColor(Color.WHITE);
                g.draw(ret);
                g.drawString(salvarnome.toString(), pr.getWidth() / 2 - pr.getWidth() / 8, 10 + pr.getHeight() / 8);
                btns.add(new Botao("", pr));
                btns.add(new Botao("Adicionar", pr));
                break;
        }

        for (int i = 0; i < btns.size(); i++) {
            btns.get(i).drawBtn(g, i);
        }
    }
}
