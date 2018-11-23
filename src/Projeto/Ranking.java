package Projeto;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ranking {

    Projeto pr;

    ArrayList<String> nomes = new ArrayList<>();

    ArrayList<String> pontos = new ArrayList<>();

    BufferedReader rank;

    Boolean abriu = false;

    public Ranking(Projeto pj) {
        pr = pj;

        try {
            rank = new BufferedReader(new FileReader("save.txt"));
            abriu = true;
        } catch (FileNotFoundException ex) {
            System.err.print("NÃ£o tem save nÃ£o");
            abriu = false;
        }
    }

    public void DrawRank(Graphics2D g) {
        g.setColor(Color.yellow);
        g.setFont(new Font(Font.MONOSPACED, Font.ITALIC + Font.BOLD, 20));
        FontMetrics fonte = g.getFontMetrics(g.getFont());
        if (abriu) {
            g.drawString("Ranking:", pr.getWidth() / 2 - (12 * ("Ranking".length()) / 2), pr.getHeight() / 16 + fonte.getAscent());
            try {
                while (rank.ready()) {
                    nomes.add(rank.readLine());
                    pontos.add(rank.readLine());
                }
            } catch (IOException ex) {
                System.err.println("Não deu bom");
            }
            String texto;
            String textoaux;
            for (int i = 0; i < nomes.size(); i++) {
                texto = nomes.get(i);
                while (texto.length() < 30) {
                    texto = texto + " ";
                }
                texto = texto + "     ";
                textoaux = pontos.get(i);
                while (textoaux.length() < 7) {
                    textoaux = '0' + textoaux;
                }
                texto = texto + textoaux;
                g.drawString(texto, pr.getWidth() / 2 - 42 * 6, pr.getHeight() / 16 + fonte.getAscent() + (i + 1) * pr.getHeight() / 16);
            }
            try {
                rank.close();
            } catch (IOException ex) {
                System.err.println("Não consegui fechar o ranking, me ajuda");
            }
        } else {
            g.drawString("Não tem ranking salvo, jogue agora para criar o seu!",
                    pr.getWidth() / 2 - (12 * ("Não tem ranking salvo, jogue agora para criar o seu!".length())) / 2,
                    pr.getHeight() / 8 + fonte.getAscent());
        }
    }

}
