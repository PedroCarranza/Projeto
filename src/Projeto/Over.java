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

        //Se já tiver um save
        if (temsave) {
            try {
                //Lê o arquivo que tinha os saves
                while (saveantigo.ready()) {
                    nomes.add(saveantigo.readLine());
                    pontos.add(saveantigo.readLine());
                }

                //Se estiver cheio, tenta encaixar
                if (nomes.size() == 10) {
                    for (int i = 0; i < 10; i++) {
                        if (pr.tela.pontuacao > Integer.parseInt(pontos.get(i))) {
                            nomes.add(i, pr.tela.men.salvarnome.toString());
                            pontos.add(i, "" + pr.tela.pontuacao);
                            nomes.remove(10);
                            pontos.remove(10);
                            break;
                        }
                    }
                } else {
                    //Se estiver vazio, coloca na primeira posição
                    if (nomes.size() == 0) {
                        nomes.add(0, pr.tela.men.salvarnome.toString());
                        pontos.add(0, "" + pr.tela.pontuacao);
                    } else {
                        //Se não estiver cheio nem vazio, procura aonde colocar
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

                //Fecha o arquivo
                saveantigo.close();

                //Cria o novo save
                savenovo = new BufferedWriter(new FileWriter("save.txt"));

                //Coloca os nomes e pontos no arquivo, cada um em uma linha
                for (int i = 0; i < nomes.size(); i++) {
                    savenovo.write(nomes.get(i));
                    savenovo.newLine();
                    savenovo.write(pontos.get(i));
                    savenovo.newLine();
                }

                //Fecha o arquivo
                savenovo.close();
            } catch (IOException ex) {
                System.err.println("Problema na hora de salvar o ranking, ja era");
            }
        } else {
            //Se não tiver um save
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

        //Limpa as listas
        nomes.clear();
        pontos.clear();
    }

}
