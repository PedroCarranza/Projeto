/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import javax.imageio.ImageIO;

/**
 *
 * @author Henrique
 */
public class Inimigo {
    int px,py;
    Projeto pr;
    BufferedImage sprites;
    int frame = 0, moveSpeed = 5;
    int scaler = 2;
    public Inimigo(Projeto p){
        pr = p;
        px = pr.getWidth()-200;
        py = pr.getHeight()-150;
        try {
            sprites = ImageIO.read(new File("nmigo.png"));
        } catch (IOException e) {
            System.err.println("Imagem não encontrada");
        }
    }
    
    public boolean collided(Tiro t){
        return new Rectangle(px, py, 117, 89).intersects(t.ret);
    }
    
    public void update(Graphics2D g){
        scaler = pr.getHeight()/360;
        moveSpeed = 3*scaler;
        frame++;
        if (frame > 14) {
            frame = 0;
        }
        
        if (py + 20*Math.sin(Instant.now().toEpochMilli()/1000) > 0 && py + 20*Math.sin(Instant.now().toEpochMilli()/1000)<pr.getHeight()-50) {
            py += 5*Math.sin(Instant.now().toEpochMilli()/1000);
        }
        
        g.drawImage(sprites.getSubimage(862 + 120 * (frame / 5), 21, 117, 89), px, py, 43 * scaler, 39 * scaler, null);
            
    }
}