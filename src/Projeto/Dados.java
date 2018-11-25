package Projeto;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;

public class Dados implements Serializable {

    ArrayList<Inimigo> inis;
    ArrayList<Tiro> tiris;
    int p2x, p2y;
    boolean pUp, pDown, pLeft, pRight, pTiro;
    Projeto pr;

    public Dados(Projeto p) {
        inis = new ArrayList<>();
        tiris = new ArrayList<>();
        pr = p;
    }

    public void getData() {
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

    public void setData(Projeto p) {
        pr = p;
        pr.tela.p2.px = p2x;
        pr.tela.p2.py = p2y;
        pr.tela.p2.up = pUp;
        pr.tela.p2.down = pDown;
        pr.tela.p2.left = pLeft;
        pr.tela.p2.right = pRight;
        pr.tela.p2.tiro = pTiro;
    }

    public void setCliData(Projeto p) {
        setData(p);
        pr.tela.inimigos.clear();
        pr.tela.inimigos.addAll(inis);
        pr.tela.tiros.clear();
        pr.tela.tiros.addAll(tiris);
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(inis);
        out.writeObject(tiris);
        out.writeInt(p2x);
        out.writeInt(p2y);
        out.writeBoolean(pUp);
        out.writeBoolean(pDown);
        out.writeBoolean(pLeft);
        out.writeBoolean(pRight);
        out.writeBoolean(pTiro);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        inis = (ArrayList<Inimigo>) in.readObject();
        tiris = (ArrayList<Tiro>) in.readObject();
        p2x = in.readInt();
        p2y = in.readInt();
        pUp = in.readBoolean();
        pDown = in.readBoolean();
        pLeft = in.readBoolean();
        pRight = in.readBoolean();
        pTiro = in.readBoolean();
    }

    private void readObjectNoData() throws ObjectStreamException {

    }

}
