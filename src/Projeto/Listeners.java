package Projeto;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Listeners implements MouseListener, KeyListener, MouseMotionListener {

    boolean up = false, down = false, left = false, right = false, stop = false, tiro = false, gotChar = true;

    char ch;

    boolean acaofeita;

    int estadoAnt;

    int moveSpeed = 5, mx = 0, my = 0;

    Projeto pr;

    public Listeners(Projeto p) {
        pr = p;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        Point ponto = e.getPoint();

        for (int i = 0; i < pr.tela.men.btns.size(); i++) {
            if (pr.tela.men.btns.get(i).getRet(i).contains(ponto)) {
                if (pr.tela.men.btns.get(i).getName().equals("Sair")) {
                    System.exit(0);
                }
                if (pr.tela.men.btns.get(i).getName().equals("Jogar")) {
                    pr.tela.estadoTela = 2;
                    break;
                }
                if (pr.tela.men.btns.get(i).getName().equals("Ok")) {
                    pr.tela.estadoTela = 10;
                    break;
                }
                if (pr.tela.men.btns.get(i).getName().equals("Voltar")) {
                    pr.tela.estadoTela = estadoAnt;
                    break;
                }
                if (pr.tela.men.btns.get(i).getName().equals("Menu")) {
                    pr.tela.estadoTela = 0;
                    break;
                }
                if (pr.tela.men.btns.get(i).getName().equals("Resumir")) {
                    pr.tela.estadoTela = 10;
                    break;
                }
                if (pr.tela.men.btns.get(i).getName().equals("Alterar Resolução")) {
                    estadoAnt = pr.tela.estadoTela;
                    pr.tela.estadoTela = 5;
                    break;
                }
                if (pr.tela.men.btns.get(i).getName().equals("720 x 480")) {
                    pr.setSize(720, 480);
                    pr.setLocationRelativeTo(null);
                    break;
                }
                if (pr.tela.men.btns.get(i).getName().equals("800 x 600")) {
                    pr.setSize(800, 600);
                    pr.setLocationRelativeTo(null);
                    break;
                }
                if (pr.tela.men.btns.get(i).getName().equals("1280 x 720")) {
                    pr.setSize(1280, 720);
                    pr.setLocationRelativeTo(null);
                    break;
                }
                if (pr.tela.men.btns.get(i).getName().equals("1920 x 1080")) {
                    pr.setSize(1920, 1080);
                    pr.setLocationRelativeTo(null);
                    break;
                }
                if (pr.tela.men.btns.get(i).getName().equals("Ranking")) {
                    estadoAnt = 0;
                    pr.tela.estadoTela = 6;
                    break;
                }
                if (pr.tela.men.btns.get(i).getName().equals("Adicionar")) {
                    pr.tela.estadoTela = 0;
                    pr.tela.p1.hp = 3;
                    pr.tela.p1.px = 10;
                    pr.tela.p1.py = pr.getHeight() / 2;
                    pr.tela.men.ov.Atualiza();
                    pr.tela.men.salvarnome = new StringBuilder();
                    pr.tela.pontuacao = 0;
                    pr.tela.tiros.clear();
                    pr.tela.inimigos.clear();
                    pr.tela.cont = 0;
                    break;
                }

            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (pr.tela.estadoTela == 10) {
                pr.tela.estadoTela = 1;
            } else if (pr.tela.estadoTela == 1) {
                pr.tela.estadoTela = 10;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            tiro = true;
        }
        if (gotChar && pr.tela.estadoTela == 7) {
            ch = e.getKeyChar();
            gotChar = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            tiro = false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

}
