package xadrez;

import exceptions.XadrezException;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.*;

import java.util.ArrayList;
import java.util.List;

public class Partida {

    private Tabuleiro tabuleiro;
    private int turno;
    private Cor jogadorAtual;
    private boolean xeque;
    private boolean xequeMate;
    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();

    public Partida() {
        tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Cor.BRANCA;
        iniciarPartida();
    }

    public boolean isXeque() {
        return xeque;
    }

    public boolean isXequeMate() {
        return xequeMate;
    }

    public PecaDeXadrez[][] getPecas() {
        PecaDeXadrez[][] pecaDoTabuleiro = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                pecaDoTabuleiro[i][j] = (PecaDeXadrez) tabuleiro.getPeca(i, j);
            }
        }
        return pecaDoTabuleiro;
    }

    private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
        tabuleiro.posicionarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
        pecasNoTabuleiro.add(peca);
    }

    private void proximoTurno() {
        turno++;
        jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
    }

    private void iniciarPartida() {

        colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('e', 1, new Rainha(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCA));

        colocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCA));


        colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETA));
        colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETA));
        colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETA));
        colocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETA));
        colocarNovaPeca('e', 8, new Rainha(tabuleiro, Cor.PRETA));
        colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETA));
        colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETA));
        colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETA));

        colocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETA));
        colocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETA));
        colocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETA));
        colocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETA));
        colocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETA));
        colocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETA));
        colocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETA));
        colocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETA));

        colocarNovaPeca('d', 4, new Rei(tabuleiro, Cor.BRANCA));

    }

    public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
        Posicao posicao = posicaoOrigem.toPosicao();
        validarPosicaoDeOrigem(posicao);
        return tabuleiro.getPeca(posicao).movimentosPossiveis();
    }

    public PecaDeXadrez executarMovimentoDeXadrez(PosicaoXadrez posicaoDeOrigem, PosicaoXadrez posicaoDeDestino) {
        Posicao origem = posicaoDeOrigem.toPosicao();
        Posicao destino = posicaoDeDestino.toPosicao();
        validarPosicaoDeOrigem(origem);
        validarPosicaoDedestino(origem, destino);
        Peca pecaCapturada = fazerMovimento(origem, destino);

        if (estaEmXeque(jogadorAtual)) {
            desfazerMovimento(origem, destino, pecaCapturada);
            throw new XadrezException("Você não pode se colocar em xeque");
        }

         xeque = (estaEmXeque(oponente(jogadorAtual))) ? true : false;

         if (estaEmXequeMate(oponente(jogadorAtual))) {
             xequeMate = true;
         }

        proximoTurno();

        return (PecaDeXadrez) pecaCapturada;

    }

    private Peca fazerMovimento(Posicao origem, Posicao destino) {
        PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removerPeca(origem);
        p.incrementaMoveCount();
        Peca pecaCapturada = tabuleiro.removerPeca(destino);
        tabuleiro.posicionarPeca(p, destino);

        if (pecaCapturada != null) {
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }
        return pecaCapturada;
    }

    private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
        PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removerPeca(destino);
        p.decrementaMoveCount();
        tabuleiro.posicionarPeca(p, origem);

        if (pecaCapturada != null) {
            tabuleiro.posicionarPeca(pecaCapturada, destino);
            pecasNoTabuleiro.add(pecaCapturada);
            pecasCapturadas.remove(pecaCapturada);
        }
    }

    private void validarPosicaoDeOrigem(Posicao posicao) {
        if (!tabuleiro.existePecaNaPosicao(posicao)) {
            throw new XadrezException("Não existe peça na posição de origem");
        }
        if (jogadorAtual != ((PecaDeXadrez) tabuleiro.getPeca(posicao)).getCor()) {
            throw new XadrezException("A peça escolhida não é sua");
        }
        if (!tabuleiro.getPeca(posicao).existeAlgumMovimentoPossivel()) {
            throw new XadrezException("Não há movimentos possíveis para está peça");
        }
    }

    private void validarPosicaoDedestino(Posicao origem, Posicao destino) {
        if (!tabuleiro.getPeca(origem).movimentoPossivel(destino)) {
            throw new XadrezException("A peça escolhida não pode se mover para o destino selecionado");
        }
    }

    private Cor oponente(Cor cor) {
        return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
    }

    private PecaDeXadrez localizaRei(Cor cor) {
        //lambda
        //List<Peca> pecaList = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
        List<Peca> pecaDaCor = filtraPecasPorCor(cor);
        for (Peca peca : pecaDaCor) {
            if (peca instanceof Rei) {
                return ((PecaDeXadrez) peca);
            }
        }
        throw new IllegalStateException("Não existe o rei da cor " + cor + " no tabuleiro");
    }

    private boolean estaEmXeque(Cor cor) {
        Posicao posicaoDoRei = localizaRei(cor).getPosicaoXadrez().toPosicao();
        List<Peca> pecaDoOponente = filtraPecasPorCor(oponente(cor));
        for (Peca peca : pecaDoOponente) {
            boolean[][] mat = peca.movimentosPossiveis();
            if (mat[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]) {
                return true;
            }
        }
        return false;
    }

    private boolean estaEmXequeMate(Cor cor) {
        if (!estaEmXeque(cor)) {
            return false;
        }
        List<Peca> pecaDaCor = filtraPecasPorCor(cor);
        for (Peca peca : pecaDaCor) {
            boolean[][] mat = peca.movimentosPossiveis();
            for (int i = 0; i < tabuleiro.getLinhas(); i++) {
                for (int j = 0; j < tabuleiro.getColunas(); j++) {
                    if (mat[i][j]) {
                        Posicao origem = ((PecaDeXadrez) peca).getPosicaoXadrez().toPosicao();
                        Posicao destino = new Posicao(i, j);
                        Peca pecaCapturada = fazerMovimento(origem, destino);
                        boolean estaEmXeque = estaEmXeque(cor);
                        desfazerMovimento(origem, destino, pecaCapturada);

                        if (!estaEmXeque) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private List<Peca> filtraPecasPorCor(Cor cor) {
        List<Peca> pecaList = pecasNoTabuleiro;
        List<Peca> pecaDaCor = new ArrayList<>();
        for (Peca peca : pecaList) {
            if (((PecaDeXadrez) peca).getCor() == cor) {
                pecaDaCor.add(peca);
            }
        }
        return pecaDaCor;
    }

    public int getTurno() {
        return turno;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }
}

