package br.com.dio.thehangedman.model;

import br.com.dio.thehangedman.exception.GameIsFinishedException;
import br.com.dio.thehangedman.exception.LetterAlreadyInputtedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static br.com.dio.thehangedman.model.TheHangedManGameStatus.LOSE;
import static br.com.dio.thehangedman.model.TheHangedManGameStatus.PENDING;
import static br.com.dio.thehangedman.model.TheHangedManGameStatus.WIN;

public class TheHangedManGame {

    private final static int HANGEDMAN_INITIAL_LINE_LENGTH = 9;
    private final static int HANGEDMAN_INITIAL_LINE_LENGTH_WITH_LINE_SEPARATOR = 10;

    private final int lineSize;
    private final int hangedManInitialSize;
    private final List<TheHangedManChar> hangedManPaths;
    private final List<TheHangedManChar> characters;
    private final List<Character> failAttempts= new ArrayList<>();

    private String hangedMan;
    private TheHangedManGameStatus theHangedManGameStatus;

    public TheHangedManGame(final List<TheHangedManChar> characters) {
        var whiteSpaces = " ".repeat(characters.size());
        var characterSpace = "-".repeat(characters.size());
        this.lineSize = HANGEDMAN_INITIAL_LINE_LENGTH_WITH_LINE_SEPARATOR + whiteSpaces.length();
        this.theHangedManGameStatus = PENDING;
        this.hangedManPaths = buildHangedManPathsPositions();
        buildTheHangedManDesign(whiteSpaces, characterSpace);
        this.characters = setCharacterSpacesPositionInGame(characters, whiteSpaces.length());
        this.hangedManInitialSize = hangedMan.length();
    }

    public TheHangedManGameStatus getTheHangedManGameStatus() {
        return theHangedManGameStatus;
    }

    public void inputCharacter(final char character) {
        if (this.theHangedManGameStatus != PENDING) {
            var message = this.theHangedManGameStatus == WIN ?
                    "Parabéns você ganhou!" :
                    "Você perdeu, tente novamente";
            throw new GameIsFinishedException(message);
        }

        var found = this.characters.stream()
                .filter(c -> c.getCharacter() == character)
                .toList();

        if (this.failAttempts.contains(character)) {
            throw new LetterAlreadyInputtedException("A letra '" + character + "' já foi informada anteriormente");
        }

        if (found.isEmpty()) {
            failAttempts.add(character);
            if (failAttempts.size() >= 6) {
                this.theHangedManGameStatus = LOSE;
            }
            rebuildHangedMan(this.hangedManPaths.removeFirst());
            return;
        }

        if (found.getFirst().isVisible()) {
            throw new LetterAlreadyInputtedException("A letra '" + character + "' já foi informada anteriormente");
        }

        this.characters.forEach(c -> {
            if (c.getCharacter() == found.getFirst().getCharacter()) {
                c.enableVisibility();
            }
        });
        if (this.characters.stream().noneMatch(TheHangedManChar::isInvisible)) {
            this.theHangedManGameStatus = WIN;
        }
        rebuildHangedMan(found.toArray(TheHangedManChar[]::new));
    }

    @Override
    public String toString() {
        return hangedMan;
    }

    private List<TheHangedManChar> buildHangedManPathsPositions() {
        final var HEAD_LINE = 3;
        final var BODY_LINE = 4;
        final var LEGS_LINE = 5;
        return new ArrayList<>(
                List.of(
                        new TheHangedManChar('O', this.lineSize * HEAD_LINE + (-7)),
                        new TheHangedManChar('|', this.lineSize * BODY_LINE + (-6)),
                        new TheHangedManChar('/', this.lineSize * BODY_LINE + (-7)),
                        new TheHangedManChar('\\', this.lineSize * BODY_LINE + (-5)),
                        new TheHangedManChar('/', this.lineSize * LEGS_LINE + (-6)),
                        new TheHangedManChar('\\', this.lineSize * LEGS_LINE + (-4))
                )
        );
    }

    private List<TheHangedManChar> setCharacterSpacesPositionInGame(final List<TheHangedManChar> characters, final int whiteSpacesAmount) {
        final var LINE_LETTER = 6;
        for (int i = 0; i < characters.size(); i++) {
            characters.get(i).setPosition(this.lineSize * LINE_LETTER + HANGEDMAN_INITIAL_LINE_LENGTH + i);
        }
        return characters;
    }

    private void rebuildHangedMan(final TheHangedManChar... theHangedManChars) {
        var hangedManBuilder = new StringBuilder(this.hangedMan);
        Stream.of(theHangedManChars).forEach(
                h -> hangedManBuilder.setCharAt(h.getPosition(), h.getCharacter()
                ));
        var failMessage = this.failAttempts.isEmpty() ? "" : "Tentativas" + this.failAttempts;
        this.hangedMan = hangedManBuilder.substring(0, hangedManInitialSize) + failMessage;
    }

    private void buildTheHangedManDesign(final String whiteSpaces, final String characterSpace) {
        hangedMan = "  -----  " + whiteSpaces + System.lineSeparator() +
                    "  |   |  " + whiteSpaces + System.lineSeparator() +
                    "  |   |  " + whiteSpaces + System.lineSeparator() +
                    "  |      " + whiteSpaces + System.lineSeparator() +
                    "  |      " + whiteSpaces + System.lineSeparator() +
                    "  |      " + whiteSpaces + System.lineSeparator() +
                    "  |      " + whiteSpaces + System.lineSeparator() +
                    "=========" + characterSpace + System.lineSeparator();

    }
}
