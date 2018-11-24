package Projeto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Over {

    Projeto pr;

    BufferedWriter savenovo;

    BufferedReader saveantigo;

    ArrayList<String> nomes = new ArrayList<>();

    ArrayList<String> pontos = new ArrayList<>();

    Boolean temsave = false;

    public Over(Projeto pj) {
        pr = pj;
    }

    public void Atualiza() {
        try {
            saveantigo = new BufferedReader(new FileReader("save.txt"));
            temsave = true;
        } catch (FileNotFoundException ex) {
            temsave = false;
        }

        if (temsave) {
            try {
                while (saveantigo.ready()) {
                    nomes.add(saveantigo.readLine());
                    pontos.add(saveantigo.readLine());
                }

                if (nomes.size() == 10) {
                    for (int i = 0; i < 10; i++) {
                        if (pr.tela.pontuacao > Integer.parseInt(pontos.get(i))) {
                            for (int j = 9; j > i; j--) {
                                nomes.add(j, nomes.get(j - 1));
                                pontos.add(j, pontos.get(j - 1));
                                nomes.remove(11);
                                pontos.remove(11);
                            }
                            nomes.add(i, pr.tela.men.salvarnome.toString());
                            pontos.add(i, "" + pr.tela.pontuacao);
                            nomes.remove(11);
                            pontos.remove(11);
                            break;
                        }
                    }
                } else {
                    if (nomes.size() == 0) {
                        nomes.add(0, pr.tela.men.salvarnome.toString());
                        pontos.add(0, "" + pr.tela.pontuacao);
                    } else {
                        int i;
                        for (i = 0; i < nomes.size(); i++) {
                            if (pr.tela.pontuacao > Integer.parseInt(pontos.get(i))) {
                                nomes.add(i, pr.tela.men.salvarnome.toString());
                                pontos.add(i, "" + pr.tela.pontuacao);
                                break;
                            }
                        }
                        if (!nomes.contains(pr.tela.men.salvarnome.toString())) {
                            nomes.add(i, pr.tela.men.salvarnome.toString());
                            pontos.add(i, "" + pr.tela.pontuacao);
                        }
                    }
                }
                saveantigo.close();

                savenovo = new BufferedWriter(new FileWriter("save.txt"));

                for (int i = 0; i < nomes.size(); i++) {
                    savenovo.write(nomes.get(i));
                    savenovo.newLine();
                    savenovo.write(pontos.get(i));
                    savenovo.newLine();
                }

                savenovo.close();
            } catch (IOException ex) {
                System.err.println("Problema na hora de salvar o ranking, ja era");
            }
        } else {
            try {
                savenovo = new BufferedWriter(new FileWriter("save.txt"));
                savenovo.write(pr.tela.men.salvarnome.toString());
                savenovo.newLine();
                savenovo.write("" + pr.tela.pontuacao);
                savenovo.newLine();
                savenovo.close();
            } catch (IOException ex) {
                System.err.println("Não consegui salvar, aborta!");
            }
        }
        nomes.clear();
        pontos.clear();
    }

}
