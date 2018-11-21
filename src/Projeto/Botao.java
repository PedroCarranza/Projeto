/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Henrique
 */
public class Botao {
    private final String nome;
    private final Projeto pr;
    public Botao(String n,Projeto p){
        nome = n;
        pr = p;
    }
    
    public String getName(){
        return nome;
    }
    
    public Rectangle getRet(int i){
        return new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, (i+1) * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
    }
    
    public void drawBtn(Graphics2D g, int i){
        FontMetrics fonte = g.getFontMetrics(g.getFont());
        Rectangle ret = new Rectangle(pr.getWidth() / 2 - pr.getWidth() / 8, (i+1) * pr.getHeight() / 8, pr.getWidth() / 4, pr.getWidth() / 32);
        if (ret.contains(pr.lis.mx, pr.lis.my)) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.LIGHT_GRAY);
            }
            g.fill(ret);
            g.setColor(Color.BLACK);
            g.drawString(nome, ret.x + (ret.width - fonte.stringWidth(nome)) / 2, ret.y + (ret.height - fonte.getHeight()) / 2 + fonte.getAscent());
    }
    
}
