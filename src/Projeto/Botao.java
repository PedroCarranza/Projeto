package Projeto;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Botao {

    private final String nome;

    private final Projeto pr;

    public Botao(String n, Projeto p) {
        nome = n;
        pr = p;
    }

    public String getName() {
        return nome;
    }

    public Rectangle getRet(int i) {
        return new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, (i + 1) * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
    }

    public void drawBtn(Graphics2D g, int i) {
        if (!nome.equals("")) {
            Rectangle ret = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, (i + 1) * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
            if (ret.contains(pr.lis.mx, pr.lis.my)) {
                g.setPaint(new GradientPaint(ret.x, ret.y, Color.WHITE, ret.x + ret.width, ret.y + ret.height, Color.BLACK));
            } else {
                g.setPaint(new GradientPaint(ret.x, ret.y, Color.BLACK, ret.x + ret.width, ret.y + ret.height, Color.LIGHT_GRAY));
            }
            g.fill(ret);
            g.setColor(Color.BLACK);
            g.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 16));
            FontMetrics fonte = g.getFontMetrics(g.getFont());
            g.drawString(nome, ret.x + (ret.width - fonte.stringWidth(nome)) / 2, ret.y + (ret.height - fonte.getHeight()) / 2 + fonte.getAscent());
        }
    }

}
