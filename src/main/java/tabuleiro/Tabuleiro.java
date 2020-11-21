package tabuleiro;

import exceptions.TabuleiroException;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
        if(linhas < 1 || colunas < 1){
            throw new TabuleiroException("Erro ao criar tabuleiro: o tamanho de linhas e colunas não pode ser menor do que 1");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public Peca getPeca(int linha, int coluna) {
        return pecas[linha][coluna];
    }

    public Peca getPeca(Posicao posicao) {
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }

    public void posicionarPeca(Peca peca, Posicao posicao) {
        if(!posicaoExistente(posicao.getLinha(),posicao.getColuna())){
            throw new TabuleiroException("Posição fora do tabuleiro");
        }
        this.pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;

    }

    public Peca removerPeca(Posicao posicao) {
        if(!posicaoExistente(posicao)){
            throw new TabuleiroException("Posição fora do tabuleiro");
        }
        if (getPeca(posicao) == null){
            return null;
        }
        Peca pecaNoTabuleiro = getPeca(posicao);
        pecaNoTabuleiro.posicao = null;
        pecas[posicao.getLinha()][posicao.getColuna()] = null;

        return pecaNoTabuleiro;

    }

    public boolean posicaoExistente(int linha, int coluna){
    return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    public boolean posicaoExistente(Posicao posicao){
        return posicaoExistente(posicao.getLinha(),posicao.getColuna());
    }

    public boolean existePecaNaPosicao(Posicao posicao){
        if (!posicaoExistente(posicao)){
            throw new TabuleiroException("Posição não existe no tabuleiro");
        }
        return getPeca(posicao) != null;
    }
}
