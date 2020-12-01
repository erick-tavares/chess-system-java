package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Partida;
import xadrez.PecaDeXadrez;

public class Peao extends PecaDeXadrez {

    private Partida partida;

    public Peao(Tabuleiro tabuleiro, Cor cor, Partida partida) {
        super(tabuleiro, cor);
        this.partida = partida;
    }

    @Override
    public String toString() {
        //Pawn
        return "p";
    }

    public boolean[][] movimentosPossiveis() {
        boolean[][] movimentosVerdadeiros = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        if (getCor() == Cor.BRANCA) {
            //acima peao branco
            p.setValues(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePecaNaPosicao(p)) {
                movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
            }
            p.setValues(posicao.getLinha() - 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePecaNaPosicao(p) &&
                    getTabuleiro().posicaoExistente(p2) && !getTabuleiro().existePecaNaPosicao(p2) && getMoveCount() == 0) {

                movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
            }
            //peao branco diagonal
            p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExistente(p) && haPecaAdversariaNaPosicao(p)) {
                movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
            }
            p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExistente(p) && haPecaAdversariaNaPosicao(p)) {
                movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
            }
            //movimento especial en passant branco
            if (posicao.getLinha() == 3) {
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if(getTabuleiro().posicaoExistente(esquerda) && haPecaAdversariaNaPosicao(esquerda) && getTabuleiro().getPeca(esquerda)== partida.getVulneravelAEnPassant()){
                    movimentosVerdadeiros[esquerda.getLinha() -1][esquerda.getColuna()] = true;
                }

                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if(getTabuleiro().posicaoExistente(direita) && haPecaAdversariaNaPosicao(direita) && getTabuleiro().getPeca(direita)== partida.getVulneravelAEnPassant()){
                    movimentosVerdadeiros[esquerda.getLinha() -1][esquerda.getColuna()] = true;
                }
            }

            //acima peao preto
        } else {
            p.setValues(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePecaNaPosicao(p)) {
                movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
            }
            p.setValues(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePecaNaPosicao(p) &&
                    getTabuleiro().posicaoExistente(p2) && !getTabuleiro().existePecaNaPosicao(p2) && getMoveCount() == 0) {

                movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
            }
            //peao preto diagonal
            p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExistente(p) && haPecaAdversariaNaPosicao(p)) {
                movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
            }
            p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExistente(p) && haPecaAdversariaNaPosicao(p)) {
                movimentosVerdadeiros[p.getLinha()][p.getColuna()] = true;
            }
            //movimento especial en passant preto
            if (posicao.getLinha() == 4) {
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if(getTabuleiro().posicaoExistente(esquerda) && haPecaAdversariaNaPosicao(esquerda) && getTabuleiro().getPeca(esquerda)== partida.getVulneravelAEnPassant()){
                    movimentosVerdadeiros[esquerda.getLinha() +1][esquerda.getColuna()] = true;
                }

                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if(getTabuleiro().posicaoExistente(direita) && haPecaAdversariaNaPosicao(direita) && getTabuleiro().getPeca(direita)== partida.getVulneravelAEnPassant()){
                    movimentosVerdadeiros[esquerda.getLinha() +1][esquerda.getColuna()] = true;
                }
            }
        }

        //movimento especial en passant
        return movimentosVerdadeiros;
    }
}
