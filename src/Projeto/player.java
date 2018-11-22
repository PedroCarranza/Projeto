package Projeto;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class player {
    int px,py;
    Projeto pr;
    BufferedImage sprites;
    int frame = 0, moveSpeed = 5;
    int scaler = 2;
    public player(Projeto p){
        pr = p;
        px = 20;
        py = pr.getHeight()/2;
        try {
            sprites = ImageIO.read(new File("spsheet.png"));
        } catch (IOException e) {
            System.err.println("Imagem não encontrada");
        }
    }
    
    public void updatePlayer(Graphics2D g){
        scaler = pr.getHeight()/360;
        moveSpeed = 3*scaler;
        frame++;
        if (frame > 9) {
            frame = 0;
        }
        if (pr.lis.right && px + 43 * scaler + moveSpeed < pr.getWidth()) {
            px += moveSpeed;
        }
        if (pr.lis.left && px - moveSpeed > 0) {
            px -= moveSpeed;
        }
        if (pr.lis.down && py + 39 * scaler+ moveSpeed < pr.getHeight()) {
            py += moveSpeed;
        }
        if (pr.lis.up && py - moveSpeed > 0) {
            py -= moveSpeed;
        }
        if (pr.lis.up && !pr.lis.down) {
            if (!pr.lis.left) {
                g.drawImage(sprites.getSubimage(229 + 46 * (frame / 5), 5, 43, 35), px, py, 43 * scaler, 35 * scaler, null);
            } else {
                g.drawImage(sprites.getSubimage(321, 5, 36, 35), px + 12, py, 36 * scaler, 35 * scaler, null);
            }
        } else if (!pr.lis.up && pr.lis.down) {
            if (!pr.lis.left) {
                g.drawImage(sprites.getSubimage(229 + 46 * (frame / 5), 89, 43, 39), px, py, 43 * scaler, 39 * scaler, null);
            } else {
                g.drawImage(sprites.getSubimage(321, 89, 36, 39), px + 12, py, 36 * scaler, 39 * scaler, null);
            }
        } else {
            if (!pr.lis.left) {
                g.drawImage(sprites.getSubimage(229 + 46 * (frame / 5), 42, 43, 39), px, py, 43 * scaler, 39 * scaler, null);
            } else {
                g.drawImage(sprites.getSubimage(321, 42, 36, 39), px + 12, py, 36 * scaler, 39 * scaler, null);
            }
        }
    }
}
