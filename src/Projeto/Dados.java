package Projeto;

import java.io.Serializable;
import java.util.ArrayList;

public class Dados implements Serializable{
    ArrayList<Inimigo> inis;
    ArrayList<Tiro> tiris;
    int p2x,p2y;
    boolean pUp,pDown,pLeft,pRight,pTiro;
    Projeto pr;
    
    public  Dados(Projeto p){
        inis = new ArrayList<>();
        tiris = new ArrayList<>();
        pr = p;
    }
    
    public void getData(){
        inis.clear();
        inis.addAll(pr.tela.inimigos);
        tiris.clear();
        tiris.addAll(pr.tela.tiros);
        p2x = pr.tela.p1.px;
        p2y = pr.tela.p1.py;
        pUp = pr.lis.up;
        pDown = pr.lis.down;
        pLeft = pr.lis.left;
        pRight = pr.lis.right;
        pTiro = pr.lis.tiro;
    }
    
    public void setData(){
        pr.tela.p2.px = p2x;
        pr.tela.p2.py = p2y;
        pr.tela.p2.up = pUp;
        pr.tela.p2.down = pDown;
        pr.tela.p2.left = pLeft;
        pr.tela.p2.right = pRight;
        pr.tela.p2.tiro = pTiro;
    }
    
    public void setCliData(){
        setData();
        pr.tela.inimigos.clear();
        pr.tela.inimigos.addAll(inis);
        pr.tela.tiros.clear();
        pr.tela.tiros.addAll(tiris);
    }
}
