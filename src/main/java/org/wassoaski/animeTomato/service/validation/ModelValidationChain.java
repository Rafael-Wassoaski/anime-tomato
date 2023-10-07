package org.wassoaski.animeTomato.service.validation;

public abstract class ModelValidationChain<M> {

    private ModelValidationChain nextValidation;
    public void validate(M model) throws Exception{
        this.isValid(model);

        if(nextValidation != null){
            nextValidation.validate(model);
        }
    }

    protected abstract void isValid(M model) throws Exception;

    public void setNextValidation(ModelValidationChain nextValidation){
        this.nextValidation = nextValidation;
    }
}
