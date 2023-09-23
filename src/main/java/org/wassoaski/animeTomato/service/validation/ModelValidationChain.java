package org.wassoaski.animeTomato.service.validation;

public abstract class ModelValidationChain<M> {

    private ModelValidationChain nextValidation;
    public boolean validate(M model){
        if(!this.isValid(model)){
            return false;
        }

        if(nextValidation == null){
            return true;
        }

        return nextValidation.validate(model);
    }

    protected abstract boolean isValid(M model);

    public void setNextValidation(ModelValidationChain nextValidation) {
        this.nextValidation = nextValidation;
    }
}
