package org.wassoaski.animeTomato.exception;

public class InvalidModel extends Exception{
    public InvalidModel(String model) {
        super(String.format("Modelo %s é invalido", model));
    }
}
