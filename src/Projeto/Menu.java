package Projeto;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {

    public static void drawMenu(Graphics2D g, Projeto pr) {
        
        Rectangle b1 = new Rectangle(pr.getWidth()/2 - pr.getWidth()/8, pr.getHeight()/8, pr.getWidth()/4, pr.getWidth()/32);
        Rectangle b2 = new Rectangle(pr.getWidth()/2 - pr.getWidth()/8, 2*pr.getHeight()/8, pr.getWidth()/4, pr.getWidth()/32);
        Rectangle b3 = new Rectangle(pr.getWidth()/2 - pr.getWidth()/8,3* pr.getHeight()/8, pr.getWidth()/4, pr.getWidth()/32);
        Rectangle b4 = new Rectangle(pr.getWidth()/2 - pr.getWidth()/8, 4*pr.getHeight()/8, pr.getWidth()/4, pr.getWidth()/32);
        Rectangle b5 = new Rectangle(pr.getWidth()/2 - pr.getWidth()/8, 5*pr.getHeight()/8, pr.getWidth()/4, pr.getWidth()/32);
        
        if (b1.contains(pr.lis.mx, pr.lis.my)) {
            g.setColor(Color.white);
        }else{
            g.setColor(Color.LIGHT_GRAY);
        }
        g.fill(b1);
        
        if (b2.contains(pr.lis.mx, pr.lis.my)) {
            g.setColor(Color.white);
        }else{
            g.setColor(Color.LIGHT_GRAY);
        }
        g.fill(b2);
        
        if (b3.contains(pr.lis.mx, pr.lis.my)) {
            g.setColor(Color.white);
        }else{
            g.setColor(Color.LIGHT_GRAY);
        }
        g.fill(b3);
        
        if (b4.contains(pr.lis.mx, pr.lis.my)) {
            g.setColor(Color.white);
        }else{
            g.setColor(Color.LIGHT_GRAY);
        }
        g.fill(b4);
        
        if (b5.contains(pr.lis.mx, pr.lis.my)) {
            g.setColor(Color.white);
        }else{
            g.setColor(Color.LIGHT_GRAY);
        }
        g.fill(b5);
        
    }
}
