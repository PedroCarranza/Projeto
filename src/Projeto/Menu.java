package Projeto;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {

    Projeto pr;

    Rectangle b1;
    Rectangle b2;
    Rectangle b3;
    Rectangle b4;

    Rectangle c1;
    Rectangle c2;

    Rectangle d1;
    Rectangle d2;
    Rectangle d3;
    Rectangle d4;
    Rectangle d5;

    Boolean dlan = false, dopcoes = false;

    public Menu(Projeto pj) {
        pr = pj;

        b1 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        b2 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 2 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        b3 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 3 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        b4 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 4 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);

        c1 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        c2 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 2 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);

        d1 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        d2 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 2 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        d3 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 3 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        d4 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 4 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        d5 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 5 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
    }

    public void updateMenu() {
        b1 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        b2 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 2 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        b3 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 3 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        b4 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 4 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);

        c1 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        c2 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 2 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);

        d1 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        d2 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 2 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        d3 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 3 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        d4 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 4 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        d5 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 5 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
    }

    public void drawMenu(Graphics2D g) {

        FontMetrics fonte = g.getFontMetrics(g.getFont());

        if (dlan) {
            if (c1.contains(pr.lis.mx, pr.lis.my)) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.LIGHT_GRAY);
            }
            g.fill(c1);
            g.setColor(Color.BLACK);
            g.drawString("Criar", c1.x + (c1.width - fonte.stringWidth("Criar")) / 2, c1.y + (c1.height - fonte.getHeight()) / 2 + fonte.getAscent());

            if (c2.contains(pr.lis.mx, pr.lis.my)) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.LIGHT_GRAY);
            }
            g.fill(c2);
            g.setColor(Color.BLACK);
            g.drawString("Voltar", c2.x + (c2.width - fonte.stringWidth("LAN")) / 2, c2.y + (c2.height - fonte.getHeight()) / 2 + fonte.getAscent());
        } else {
            if (dopcoes) {
                if (d1.contains(pr.lis.mx, pr.lis.my)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }
                g.fill(d1);
                g.setColor(Color.BLACK);
                g.drawString("720 x 480", d1.x + (d1.width - fonte.stringWidth("720 x 480")) / 2, d1.y + (d1.height - fonte.getHeight()) / 2 + fonte.getAscent());

                if (d2.contains(pr.lis.mx, pr.lis.my)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }
                g.fill(d2);
                g.setColor(Color.BLACK);
                g.drawString("800 x 600", d2.x + (d2.width - fonte.stringWidth("800 x 600")) / 2, d2.y + (d2.height - fonte.getHeight()) / 2 + fonte.getAscent());

                if (d3.contains(pr.lis.mx, pr.lis.my)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }
                g.fill(d3);
                g.setColor(Color.BLACK);
                g.drawString("1280 x 720", d3.x + (d3.width - fonte.stringWidth("1280 x 720")) / 2, d3.y + (d3.height - fonte.getHeight()) / 2 + fonte.getAscent());

                if (d4.contains(pr.lis.mx, pr.lis.my)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }
                g.fill(d4);
                g.setColor(Color.BLACK);
                g.drawString("1920 x 1080", d4.x + (d4.width - fonte.stringWidth("1920 x 1080")) / 2, d4.y + (d4.height - fonte.getHeight()) / 2 + fonte.getAscent());

                if (d5.contains(pr.lis.mx, pr.lis.my)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }
                g.fill(d5);
                g.setColor(Color.BLACK);
                g.drawString("Voltar", d5.x + (d5.width - fonte.stringWidth("Voltar")) / 2, d5.y + (d5.height - fonte.getHeight()) / 2 + fonte.getAscent());
            } else {
                if (b1.contains(pr.lis.mx, pr.lis.my)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }
                g.fill(b1);
                g.setColor(Color.BLACK);
                g.drawString("Resumir", b1.x + (b1.width - fonte.stringWidth("Resumir")) / 2, b1.y + (b1.height - fonte.getHeight()) / 2 + fonte.getAscent());

                if (b2.contains(pr.lis.mx, pr.lis.my)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }
                g.fill(b2);
                g.setColor(Color.BLACK);
                g.drawString("Alterar Resolução", b2.x + (b2.width - fonte.stringWidth("Alterar Resulução")) / 2, b2.y + (b2.height - fonte.getHeight()) / 2 + fonte.getAscent());

                if (b3.contains(pr.lis.mx, pr.lis.my)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }
                g.fill(b3);
                g.setColor(Color.BLACK);
                g.drawString("LAN", b3.x + (b3.width - fonte.stringWidth("LAN")) / 2, b3.y + (b3.height - fonte.getHeight()) / 2 + fonte.getAscent());

                if (b4.contains(pr.lis.mx, pr.lis.my)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }
                g.fill(b4);
                g.setColor(Color.BLACK);
                g.drawString("Sair", b4.x + (b4.width - fonte.stringWidth("Sair")) / 2, b4.y + (b4.height - fonte.getHeight()) / 2 + fonte.getAscent());
            }
        }
    }
}
