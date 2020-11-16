package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Torre extends PecaDeXadrez {

    public Torre(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        //Tower
        return "T";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        return new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
    }
}
