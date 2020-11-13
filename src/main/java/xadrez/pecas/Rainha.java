package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Rainha extends PecaDeXadrez {

    public Rainha(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        //Queen
        return "Q";
    }
}
