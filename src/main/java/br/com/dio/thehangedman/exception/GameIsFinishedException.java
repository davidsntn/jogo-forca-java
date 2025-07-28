package br.com.dio.thehangedman.exception;

public class GameIsFinishedException extends RuntimeException{
    public GameIsFinishedException(final String message) { super(message); }
}
