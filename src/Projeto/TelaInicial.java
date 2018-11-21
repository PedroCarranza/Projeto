package Projeto;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TelaInicial {

    Projeto pr;

    Rectangle i1;
    Rectangle i2;
    Rectangle i3;
    Rectangle i4;

    Rectangle u1;
    Rectangle u2;
    Rectangle u3;

    Boolean inicio = true;

    Boolean umjogador = false;

    TelaInicial(Projeto pj) {
        pr = pj;

        i1 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 3 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        i2 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 4 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        i3 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 5 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        i4 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 6 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);

        u1 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 1 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        u2 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 2 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        u3 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 3 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
    }

    public void drawTela(Graphics2D g) {

        g.setBackground(Color.BLACK);

        FontMetrics fonte = g.getFontMetrics(g.getFont());

        if (umjogador) {
            if (u1.contains(pr.lis.mx, pr.lis.my)) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.LIGHT_GRAY);
            }
            g.fill(u1);
            g.setColor(Color.BLACK);
            g.drawString("Novo Jogo", u1.x + (u1.width - fonte.stringWidth("Novo Jogo")) / 2, u1.y + (u1.height - fonte.getHeight()) / 2 + fonte.getAscent());

            if (u2.contains(pr.lis.mx, pr.lis.my)) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.LIGHT_GRAY);
            }
            g.fill(u2);
            g.setColor(Color.BLACK);
            g.drawString("Carregar Jogo", u2.x + (u1.width - fonte.stringWidth("Carregar Jogo")) / 2, u2.y + (u2.height - fonte.getHeight()) / 2 + fonte.getAscent());

            if (u3.contains(pr.lis.mx, pr.lis.my)) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.LIGHT_GRAY);
            }
            g.fill(u3);
            g.setColor(Color.BLACK);
            g.drawString("Voltar", u3.x + (u3.width - fonte.stringWidth("Voltar")) / 2, u3.y + (u3.height - fonte.getHeight()) / 2 + fonte.getAscent());
        } else {
            if (i1.contains(pr.lis.mx, pr.lis.my)) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.LIGHT_GRAY);
            }
            g.fill(i1);
            g.setColor(Color.BLACK);
            g.drawString("Um Jogador", i1.x + (i1.width - fonte.stringWidth("Um Jogador")) / 2, i1.y + (i1.height - fonte.getHeight()) / 2 + fonte.getAscent());

            if (i2.contains(pr.lis.mx, pr.lis.my)) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.LIGHT_GRAY);
            }
            g.fill(i2);
            g.setColor(Color.BLACK);
            g.drawString("LAN", i2.x + (i2.width - fonte.stringWidth("LAN")) / 2, i2.y + (i2.height - fonte.getHeight()) / 2 + fonte.getAscent());

            if (i3.contains(pr.lis.mx, pr.lis.my)) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.LIGHT_GRAY);
            }
            g.fill(i3);
            g.setColor(Color.BLACK);
            g.drawString("Opções", i3.x + (i3.width - fonte.stringWidth("Opcões")) / 2, i3.y + (i3.height - fonte.getHeight()) / 2 + fonte.getAscent());

            if (i4.contains(pr.lis.mx, pr.lis.my)) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.LIGHT_GRAY);
            }
            g.fill(i4);
            g.setColor(Color.BLACK);
            g.drawString("Sair", i4.x + (i4.width - fonte.stringWidth("Sair")) / 2, i4.y + (i4.height - fonte.getHeight()) / 2 + fonte.getAscent());
        }
    }
}
