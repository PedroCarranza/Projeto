package Projeto;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class Listeners implements MouseListener, KeyListener, MouseMotionListener {

    boolean up = false, down = false, left = false, right = false, stop = false;

    int moveSpeed = 5, mx = 0, my = 0;

    Projeto pr;

    public Listeners(Projeto p) {
        pr = p;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Boolean acaofeita = false;

        Point ponto = e.getPoint();

        if (pr.tela.inicio.i1.contains(ponto) && pr.tela.comeco && !acaofeita) {
            pr.tela.inicio.umjogador = true;
            pr.tela.noComeco();
            acaofeita = true;
        }
        if (pr.tela.inicio.i2.contains(ponto) && pr.tela.comeco && !acaofeita) {

        }
        if (pr.tela.inicio.i3.contains(ponto) && pr.tela.comeco && !acaofeita) {

        }

        if (pr.tela.inicio.u1.contains(ponto) && pr.tela.comeco && !pr.tela.inicio.umjogador && !acaofeita) {

        }

        if (pr.tela.inicio.u2.contains(ponto) && pr.tela.comeco && !pr.tela.inicio.umjogador && !acaofeita) {

        }

        if (pr.tela.men.b1.contains(ponto) && !pr.tela.men.dlan && !pr.tela.men.dopcoes && !acaofeita) {
            stop = !stop;
            pr.tela.men.dlan = false;
            pr.tela.men.dopcoes = false;
            acaofeita = true;
        }
        if (pr.tela.men.b2.contains(ponto) && !pr.tela.men.dlan && !pr.tela.men.dopcoes && !acaofeita) {
            pr.tela.men.dopcoes = true;
            pr.tela.updatePaused();
            acaofeita = true;
        }
        if (pr.tela.men.b3.contains(ponto) && !pr.tela.men.dlan && !pr.tela.men.dopcoes && !acaofeita) {
            pr.tela.men.dlan = true;
            pr.tela.updatePaused();
            acaofeita = true;
        }
        if ((pr.tela.men.b4.contains(ponto) || pr.tela.inicio.i4.contains(ponto)) && !pr.tela.men.dlan && !pr.tela.men.dopcoes && !acaofeita) {
            System.exit(0);
        }

        if (pr.tela.men.c1.contains(ponto) && pr.tela.men.dlan && !acaofeita) {
            acaofeita = true;
        }
        if (pr.tela.men.c2.contains(ponto) && pr.tela.men.dlan && !acaofeita) {
            pr.tela.men.dlan = false;
            pr.tela.updatePaused();
            acaofeita = true;
        }

        if (pr.tela.men.d1.contains(ponto) && pr.tela.men.dopcoes && !acaofeita) {
            pr.image = new BufferedImage(720, 480, BufferedImage.TYPE_INT_RGB);
            pr.setSize(720, 480);
            pr.tela.men.dopcoes = false;
            acaofeita = true;
            pr.tela.men.updateMenu();
            pr.setLocationRelativeTo(null);
            pr.tela.updatePaused();
        }
        if (pr.tela.men.d2.contains(ponto) && pr.tela.men.dopcoes && !acaofeita) {
            pr.image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
            pr.setSize(800, 600);
            pr.tela.men.dopcoes = false;
            acaofeita = true;
            pr.tela.men.updateMenu();
            pr.setLocationRelativeTo(null);
            pr.tela.updatePaused();
        }
        if (pr.tela.men.d3.contains(ponto) && pr.tela.men.dopcoes && !acaofeita) {
            pr.image = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_RGB);
            pr.setSize(1280, 720);
            pr.tela.men.dopcoes = false;
            acaofeita = true;
            pr.tela.men.updateMenu();
            pr.setLocationRelativeTo(null);
            pr.tela.updatePaused();
        }
        if (pr.tela.men.d4.contains(ponto) && pr.tela.men.dopcoes && !acaofeita) {
            pr.image = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
            pr.setSize(1920, 1080);
            pr.tela.men.dopcoes = false;
            acaofeita = true;
            pr.tela.men.updateMenu();
            pr.setLocationRelativeTo(null);
            pr.tela.updatePaused();
        }
        if (pr.tela.men.d5.contains(ponto) && pr.tela.men.dopcoes && !acaofeita) {
            pr.tela.men.dopcoes = false;
            pr.tela.updatePaused();
            acaofeita = true;
        }

        if (pr.tela.inicio.inicio) {
            pr.tela.noComeco();
        } else {
            pr.tela.update();
        }

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
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            stop = !stop;
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
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
    }

}
