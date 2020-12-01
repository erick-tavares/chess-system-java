package application;

import exceptions.XadrezException;
import xadrez.Partida;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner sc = new Scanner((System.in));

        Partida partida = new Partida();
        List<PecaDeXadrez> capturadas = new ArrayList<>();

        while (!partida.isXequeMate()) {
            try {
                UI.printPartida(partida, capturadas);
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

                PecaDeXadrez pecaCapturada = partida.executarMovimentoDeXadrez(origem, destino);

                if(pecaCapturada != null){
                    capturadas.add(pecaCapturada);
                }

                if (partida.getPromovida() != null){
                    System.out.println("Escolha a peça para promoção (Q/B/T/H)");
                    String tipo = sc.nextLine();
                    partida.trocarPecaPromovida(tipo);
                }
            }catch (XadrezException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }catch (InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }

        }
        UI.clearScreen();
        UI.printPartida(partida, capturadas);

    }

}
