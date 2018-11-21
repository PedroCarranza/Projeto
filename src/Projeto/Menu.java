package Projeto;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Menu {

    Projeto pr;

    ArrayList<Botao> btns = new ArrayList<>();

    public Menu(Projeto pj) {
        pr = pj;
    }

    public void drawMenu(Graphics2D g) {
        btns.clear();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, pr.getWidth(), pr.getHeight());
        switch (pr.tela.estadoTela) {
            case 0:
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
                btns.add(new Botao("Ok", pr));
                //TODO: add ibagem
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
