package application;

import exceptions.TabuleiroException;
import exceptions.XadrezException;
import tabuleiro.Tabuleiro;
import xadrez.Partida;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner sc = new Scanner((System.in));

        Partida partida = new Partida();

        while (true) {
            try {
                UI.printTabuleiro(partida.getPecas());
                UI.clearScreen();
                System.out.println();
                System.out.println("Origem");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);

                boolean [][] movimentosPossiveis = partida.movimentosPossiveis(origem);
                UI.clearScreen();
                UI.printTabuleiro(partida.getPecas(),movimentosPossiveis);

                System.out.println();
                System.out.println("Destino");
                PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);

                PecaDeXadrez pecaCapturada = partida.moverPecaDeXadrez(origem, destino);
            }catch (XadrezException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }catch (InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }

        }

    }

}
