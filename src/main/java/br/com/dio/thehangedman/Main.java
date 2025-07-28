package br.com.dio.thehangedman;

import br.com.dio.thehangedman.exception.GameIsFinishedException;
import br.com.dio.thehangedman.exception.LetterAlreadyInputtedException;
import br.com.dio.thehangedman.model.TheHangedManChar;
import br.com.dio.thehangedman.model.TheHangedManGame;

import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String... args) {
        var characters = Stream.of(args)
                .map(a -> a.toLowerCase().charAt(0))
                .map(TheHangedManChar::new).toList();
        System.out.println(characters);
        var hangedManGame = new TheHangedManGame(characters);
        System.out.println("\nBem-vindo ao jogo do Enforcado, tente adivinhar a palavra correta.");
        System.out.println("AVISO: as palavras que você terá que adivinhar, fazem parte do universo das cartas de tarô.");
        System.out.println("Boa Sorte!\n");
        System.out.println(hangedManGame);
        var option = -1;
        while (true) {
            System.out.println("Selecione uma das opções:");
            System.out.println("1 - Informar uma letra");
            System.out.println("2 - Verificar status do jogo");
            System.out.println("3 - Sair do jogo");
            option = scanner.nextInt();
            switch (option) {
                case 1 -> inputCharacter(hangedManGame);
                case 2 -> showGameStatus(hangedManGame);
                case 3 -> System.exit(0);
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private static void showGameStatus(TheHangedManGame hangedManGame) {
        System.out.println(hangedManGame.getTheHangedManGameStatus());
        System.out.println(hangedManGame);
    }

    private static void inputCharacter(TheHangedManGame hangedManGame) {
        System.out.println("Informe uma letra:");
        var character = scanner.next().charAt(0);
        try {
            hangedManGame.inputCharacter(character);
        } catch (LetterAlreadyInputtedException ex) {
            System.out.println(ex.getMessage());
        } catch (GameIsFinishedException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        System.out.println(hangedManGame);
    }
}
