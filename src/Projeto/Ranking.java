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

public class Ranking {

    Projeto pr;

    ArrayList<String> nomes = new ArrayList<>();

    ArrayList<String> pontos = new ArrayList<>();

    Boolean abriu = false;

    public Ranking(Projeto pj) {
        pr = pj;
    }

    public void DrawRank(Graphics2D g) {
        BufferedReader rank = null;
        g.setColor(Color.yellow);
        g.setFont(new Font(Font.MONOSPACED, Font.ITALIC + Font.BOLD, 20));
        FontMetrics fonte = g.getFontMetrics(g.getFont());
        try {
            rank = new BufferedReader(new FileReader("save.txt"));
            abriu = true;
        } catch (FileNotFoundException e) {
            abriu = false;
        }
        if (abriu) {
            //Desenha a frase do ranking na tela
            g.drawString("Ranking:", pr.getWidth() / 2 - (12 * ("Ranking".length()) / 2), pr.getHeight() / 16 + fonte.getAscent());

            //Lê o arquivo do ranking
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

            //Formata o texto para ter sempre o mesmo espaçamento e desenha os nomes com os pontos
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

            //Tenta fechar o ranking
            try {
                rank.close();
            } catch (IOException ex) {
                System.err.println("Não consegui fechar o ranking, me ajuda");
            }

            //Limpa as listas
            nomes.clear();
            pontos.clear();

        } else {
            g.drawString("Não tem ranking salvo, jogue agora para criar o seu!",
                    pr.getWidth() / 2 - (12 * ("Não tem ranking salvo, jogue agora para criar o seu!".length())) / 2,
                    pr.getHeight() / 8 + fonte.getAscent());
        }
    }

}
