package Projeto;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Ranking {

    Projeto pr;

    BufferedReader rank;

    Boolean abriu = false;

    public Ranking(Projeto pj) {
        pr = pj;

        try {
            rank = new BufferedReader(new FileReader("save.txt"));
            abriu = true;
        } catch (FileNotFoundException ex) {
            System.err.print("Não tem save não");
            abriu = false;
        }
    }

    public void DrawRank(Graphics2D g) {
        if (abriu) {

        } else {
            g.setColor(Color.yellow);
            g.setFont(new Font(Font.MONOSPACED, Font.ITALIC + Font.BOLD, 24));
            FontMetrics fonte = g.getFontMetrics(g.getFont());
            g.drawString("Não tem ranking salvo, jogue agora para criar o seu!", pr.getWidth() / 3 - pr.getWidth() / 8, pr.getHeight() / 8);
        }
    }

}
