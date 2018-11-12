package Projeto;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;
import javax.swing.JFrame;

public class Projeto extends JFrame implements Runnable {

    boolean running;

    Thread thread;

    BufferedImage image;

    Listeners lis;

    Tela tela;

    @SuppressWarnings("")

    public Projeto() {
        setSize(1280, 720);

        thread = new Thread(this);

        tela = new Tela(this);

        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        lis = new Listeners();
        addKeyListener(lis);
        addMouseListener(lis);
        addMouseMotionListener(lis);

        setResizable(false);
        setDefaultCloseOperation(3); // 3 = JFrame.EXIT_ON_CLOSE
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private synchronized void start() {
        running = true;
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.stop();
        } catch (InterruptedException e) {
            System.err.println("Deu ruim");
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        bs.show();
    }

    @Override
    public void run() {
        Instant last = Instant.now();
        Instant now;
        final double ns = 1000000000.0 / 60.0; //60 frames por segundo
        double delta = 0;
        requestFocus();
        while (running) {
            if (lis.stop) {
                stop();
            }
            now = Instant.now();
            delta += Duration.between(last, now).getNano() / ns;
            last = Instant.now();
            while (delta > 0) {//garante que roda 60 vezes por segundo
                //logica que depende do tempo aqui
                tela.update();
                delta--;
            }
            render();//sempre renderiza na tela
        }
    }

    public static void main(String[] args) {
        Projeto proj = new Projeto();
        proj.start();
    }

}
