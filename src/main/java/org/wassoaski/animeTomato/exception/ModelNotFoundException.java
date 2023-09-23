package org.wassoaski.animeTomato.exception;

public class ModelNotFoundException extends Exception{
    public ModelNotFoundException(String model, Long id) {
        super(String.format("Modelo %s não foi localizado com o id %s", model, id));
    }
}
