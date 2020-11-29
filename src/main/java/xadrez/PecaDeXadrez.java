package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaDeXadrez extends Peca {

    private Cor cor;
    private int moveCount;

    public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    public void incrementaMoveCount(){
        moveCount ++;
    }

    public void decrementaMoveCount(){
        moveCount --;
    }

    protected boolean haPecaAdversariaNaPosicao(Posicao posicao){
        PecaDeXadrez peca = (PecaDeXadrez) getTabuleiro().getPeca(posicao);
        return peca != null && peca.getCor() != cor;
    }

    public PosicaoXadrez getPosicaoXadrez(){
        return PosicaoXadrez.fromPosicao(posicao);
    }

    public int getMoveCount() {
        return moveCount;
    }

}
