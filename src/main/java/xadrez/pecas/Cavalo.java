package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Cavalo extends PecaDeXadrez {

    public Cavalo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        //Horse
        return "H";
    }
}
