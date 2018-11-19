package Projeto;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
    
    BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
    Cursor oldCursor;

    @SuppressWarnings("")

    public Projeto() {
        setSize(1280, 720);

        thread = new Thread(this);

        tela = new Tela(this);

        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            }
            
        });
        
        oldCursor = getContentPane().getCursor();

        lis = new Listeners(this);
        addKeyListener(lis);
        addMouseListener(lis);
        addMouseMotionListener(lis);

        setResizable(false);
        setUndecorated(true);
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
            thread.join();
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
            now = Instant.now();
            if (!lis.stop) {
                delta += Duration.between(last, now).getNano() / ns;
                getContentPane().setCursor(blankCursor);
            }else{
                tela.updatePaused();
                getContentPane().setCursor(oldCursor);
            }
            last = Instant.now();
            while (delta > 0 && !lis.stop) {//garante que roda 60 vezes por segundo
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
