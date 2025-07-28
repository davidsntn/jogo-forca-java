package br.com.dio.thehangedman.exception;

public class LetterAlreadyInputtedException extends RuntimeException{
    public LetterAlreadyInputtedException(final String message) { super(message); }
}
