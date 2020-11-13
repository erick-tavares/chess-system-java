package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;

public class Partida {

    private Tabuleiro tabuleiro;

    public Partida() {
        tabuleiro = new Tabuleiro(8,8);
        iniciarPartida();
    }

    public PecaDeXadrez[][] getPecas(){
        PecaDeXadrez[][] pecaDoTabuleiro = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for(int i = 0;i < tabuleiro.getLinhas();i++  ){
            for(int j = 0; j < tabuleiro.getColunas(); j ++){
                pecaDoTabuleiro[i][j] = (PecaDeXadrez) tabuleiro.setPosicaoDaPeca(i,j);
            }
        }
        return pecaDoTabuleiro;
    }

    private void iniciarPartida(){
        tabuleiro.posicionarPeca(new Rei(this.tabuleiro,Cor.BRANCO),new Posicao(0,0));


    }
}
