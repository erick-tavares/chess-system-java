package appliacation;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Partida;

public class Application {

    public static void main(String[] args) {

        Partida partida = new Partida();
        UI.printTabuleiro(partida.getPecas());
    }
}
