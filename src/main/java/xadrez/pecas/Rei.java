package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Partida;
import xadrez.PecaDeXadrez;

public class Rei extends PecaDeXadrez {

    private Partida partida;

    public Rei(Tabuleiro tabuleiro, Cor cor, Partida partida) {
        super(tabuleiro, cor);
        this.partida = partida;
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

    private boolean checakTorreParaRoque(Posicao posicao) {
        PecaDeXadrez p =(PecaDeXadrez) getTabuleiro().getPeca(posicao);
        return p != null && p instanceof Torre && p.getCor() == getCor() && p.getMoveCount() == 0;
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

        //movimento especial roque
        if (getMoveCount() == 0 && !partida.isXequeMate()){
            //lado do rei
            Posicao torreDoRei = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
            if(checakTorreParaRoque(torreDoRei)){
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
                if (getTabuleiro().getPeca(p1) == null && getTabuleiro().getPeca(p2) == null){
                    movimentosVerdadeiros[p.getLinha()][p.getColuna() + 2] = true;
                }
            }

            //lado da rainha
            Posicao torreDaRainha = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
            if(checakTorreParaRoque(torreDaRainha)){
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
                Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
                if (getTabuleiro().getPeca(p1) == null && getTabuleiro().getPeca(p2) == null && getTabuleiro().getPeca(p3) == null){
                    movimentosVerdadeiros[p.getLinha()][p.getColuna() - 2] = true;
                }
            }
        }
        return movimentosVerdadeiros;
    }
}
