package Projeto;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Menu {

    Projeto pr;

    BufferedImage pausebackground;

    BufferedImage menubackground;

    BufferedImage comandos;

    ArrayList<Botao> btns = new ArrayList<>();

    public Menu(Projeto pj) {
        pr = pj;

        try {
            pausebackground = ImageIO.read(new File("pauseback.png"));
            menubackground = ImageIO.read(new File("menuback.png"));
            comandos = ImageIO.read(new File("comandos.png"));
        } catch (IOException e) {
            System.err.println("Imagem back não encontrada");
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
                btns.add(new Botao("Alterar Resolução", pr));
                btns.add(new Botao("Sair", pr));
                break;
            case 1:
                btns.add(new Botao("Resumir", pr));
                btns.add(new Botao("LAN", pr));
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
                //TODO criar LAN
                break;
            case 5:
                // TODO entrar LAN
                break;
            case 6:
                btns.add(new Botao("720 x 480", pr));
                btns.add(new Botao("800 x 600", pr));
                btns.add(new Botao("1280 x 720", pr));
                btns.add(new Botao("1920 x 1080", pr));
                btns.add(new Botao("Voltar", pr));
                break;
        }
        for (int i = 0; i < btns.size(); i++) {
            btns.get(i).drawBtn(g, i);
        }
    }
}
