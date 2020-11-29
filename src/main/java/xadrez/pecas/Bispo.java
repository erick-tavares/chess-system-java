package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Bispo extends PecaDeXadrez {
    public Bispo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        //Bishop
        return "B";
    }

    public boolean[][] movimentosPossiveis() {
        boolean[][] movimentosVerdadeiros = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0,0);
        //noroeste
        p.setValues(p.getLinha() - 1,p.getColuna() - 1);
        while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePecaNaPosicao(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() - 1, p.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExistente(p) && haPecaAdversariaNaPosicao(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //nordeste
        p.setValues(p.getLinha() - 1,p.getColuna() + 1);
        while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePecaNaPosicao(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() - 1, p.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExistente(p) && haPecaAdversariaNaPosicao(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //sudeste
        p.setValues(p.getLinha() + 1,p.getColuna() + 1);
        while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePecaNaPosicao(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() + 1, p.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExistente(p) && haPecaAdversariaNaPosicao(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //sudoeste
        p.setValues(p.getLinha() + 1,p.getColuna() - 1);
        while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePecaNaPosicao(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() + 1, p.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExistente(p) && haPecaAdversariaNaPosicao(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }
        return movimentosVerdadeiros;
    }
}
