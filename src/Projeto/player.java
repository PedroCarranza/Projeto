package Projeto;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class player {
    int p1Pos[];
    Projeto pr;
    BufferedImage sprites;
    int frame = 0, moveSpeed = 5;
    int scaler = 2;
    public player(Projeto p){
        pr = p;
        p1Pos = new int[2];
        p1Pos[0] = 20;
        p1Pos[1] = 360;
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
        if (pr.lis.right && p1Pos[0] + 100 + moveSpeed < pr.getWidth()) {
            p1Pos[0] += moveSpeed;
        }
        if (pr.lis.left && p1Pos[0] - moveSpeed > 0) {
            p1Pos[0] -= moveSpeed;
        }
        if (pr.lis.down && p1Pos[1] + 78 + moveSpeed < pr.getHeight()) {
            p1Pos[1] += moveSpeed;
        }
        if (pr.lis.up && p1Pos[1] - moveSpeed > 0) {
            p1Pos[1] -= moveSpeed;
        }
        if (pr.lis.up && !pr.lis.down) {
            if (!pr.lis.left) {
                g.drawImage(sprites.getSubimage(229 + 46 * (frame / 5), 5, 43, 35), p1Pos[0], p1Pos[1], 43 * scaler, 35 * scaler, null);
            } else {
                g.drawImage(sprites.getSubimage(321, 5, 36, 35), p1Pos[0] + 12, p1Pos[1], 36 * scaler, 35 * scaler, null);
            }
        } else if (!pr.lis.up && pr.lis.down) {
            if (!pr.lis.left) {
                g.drawImage(sprites.getSubimage(229 + 46 * (frame / 5), 89, 43, 39), p1Pos[0], p1Pos[1], 43 * scaler, 39 * scaler, null);
            } else {
                g.drawImage(sprites.getSubimage(321, 89, 36, 39), p1Pos[0] + 12, p1Pos[1], 36 * scaler, 39 * scaler, null);
            }
        } else {
            if (!pr.lis.left) {
                g.drawImage(sprites.getSubimage(229 + 46 * (frame / 5), 42, 43, 39), p1Pos[0], p1Pos[1], 43 * scaler, 39 * scaler, null);
            } else {
                g.drawImage(sprites.getSubimage(321, 42, 36, 39), p1Pos[0] + 12, p1Pos[1], 36 * scaler, 39 * scaler, null);
            }
        }
    }
}
