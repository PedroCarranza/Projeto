/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto;

import java.awt.Graphics2D;

/**
 *
 * @author Henrique
 */
public class Player2 extends Player{
    boolean up,down,right,left,tiro;
    public Player2(Projeto p) {
        super(p);
    }
    
    @Override
    public void updatePlayer(Graphics2D g){
        scaler = pr.getHeight()/360;
        if (up && !down) {
            if (!left) {
                g.drawImage(sprites.getSubimage(229 + 46 * (frame / 5), 5, 43, 35), px, py, 43 * scaler, 35 * scaler, null);
            } else {
                g.drawImage(sprites.getSubimage(321, 5, 36, 35), px + 12, py, 36 * scaler, 35 * scaler, null);
            }
        } else if (!up && down) {
            if (!left) {
                g.drawImage(sprites.getSubimage(229 + 46 * (frame / 5), 89, 43, 39), px, py, 43 * scaler, 39 * scaler, null);
            } else {
                g.drawImage(sprites.getSubimage(321, 89, 36, 39), px + 12, py, 36 * scaler, 39 * scaler, null);
            }
        } else {
            if (!left) {
                g.drawImage(sprites.getSubimage(229 + 46 * (frame / 5), 42, 43, 39), px, py, 43 * scaler, 39 * scaler, null);
            } else {
                g.drawImage(sprites.getSubimage(321, 42, 36, 39), px + 12, py, 36 * scaler, 39 * scaler, null);
            }
        }
    }
    
}
