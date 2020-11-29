package xadrez.pecas;

import tabuleiro.Posicao;
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

    private boolean podeMover(Posicao posicao){
        PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().getPeca(posicao);
        return p == null || p.getCor() != getCor();
    }

    public boolean[][] movimentosPossiveis() {
        boolean[][] movimentosVerdadeiros = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        //acima esquerda
        p.setValues(posicao.getLinha() -2, posicao.getColuna() -1);
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //acima direita
        p.setValues(posicao.getLinha() -2, posicao.getColuna() +1);
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //abaixo esquerda
        p.setValues(posicao.getLinha() +2, posicao.getColuna() - 1);
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //abaixo direita
        p.setValues(posicao.getLinha() +2, posicao.getColuna() + 1);
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //esquerda acima
        p.setValues(posicao.getLinha() - 1, posicao.getColuna() -2);
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //esquerda abaixo
        p.setValues(posicao.getLinha() + 1, posicao.getColuna() -2);
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //direita acima
        p.setValues(posicao.getLinha() - 1, posicao.getColuna() +2);
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //direita abaixo
        p.setValues(posicao.getLinha() + 1, posicao.getColuna() +2);
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }
        return movimentosVerdadeiros;
    }
}
