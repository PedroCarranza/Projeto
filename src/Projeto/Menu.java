package Projeto;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {

    public static void drawMenu(Graphics2D g, Projeto pr) {

        Rectangle b1 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        Rectangle b2 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 2 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        Rectangle b3 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 3 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        Rectangle b4 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 4 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        Rectangle b5 = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, 5 * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);

        FontMetrics fonte = g.getFontMetrics(g.getFont());

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
        g.drawString("Opções", b2.x + (b2.width - fonte.stringWidth("Opções")) / 2, b2.y + (b2.height - fonte.getHeight()) / 2 + fonte.getAscent());

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

        if (b5.contains(pr.lis.mx, pr.lis.my)) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.LIGHT_GRAY);
        }
        g.fill(b5);

    }
}
