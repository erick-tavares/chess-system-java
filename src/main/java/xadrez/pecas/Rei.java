package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Rei extends PecaDeXadrez {


    public Rei(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        //King
        return "K";
    }

    private boolean podeMover(Posicao posicao){
        PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().getPeca(posicao);
        return p == null || p.getCor() != getCor();

    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] movimentosVerdadeiros = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        //acima
        p.setValues(posicao.getLinha() -1, posicao.getColuna());
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //abaixo
        p.setValues(posicao.getLinha() +1, posicao.getColuna());
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //esquerda
        p.setValues(posicao.getLinha(), posicao.getColuna() -1);
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //esquerda
        p.setValues(posicao.getLinha(), posicao.getColuna() +1);
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //noroeste
        p.setValues(posicao.getLinha() -1, posicao.getColuna() -1);
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //nordeste
        p.setValues(posicao.getLinha() -1, posicao.getColuna() +1);
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //sudoeste
        p.setValues(posicao.getLinha() +1, posicao.getColuna() -1);
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }

        //sudeste
        p.setValues(posicao.getLinha() +1, posicao.getColuna() +1);
        if(getTabuleiro().posicaoExistente(p) && podeMover(p)){
            movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
        }
        return movimentosVerdadeiros;
    }
}
