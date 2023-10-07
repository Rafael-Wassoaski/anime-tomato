package org.wassoaski.animeTomato.service.validation.anime;

import org.springframework.beans.factory.annotation.Autowired;
import org.wassoaski.animeTomato.exception.InvalidModel;
import org.wassoaski.animeTomato.model.Anime;
import org.wassoaski.animeTomato.repository.AnimeRepository;
import org.wassoaski.animeTomato.service.validation.ModelValidationChain;

import java.util.Optional;

public class IdValidation extends ModelValidationChain<Anime> {

    @Override
    protected void isValid(Anime model) throws InvalidModel {
        Long id = model.getId();
        if (id != null) {
            throw new InvalidModel("O id do modelo não pode ser vazio");
        }
    }
}
