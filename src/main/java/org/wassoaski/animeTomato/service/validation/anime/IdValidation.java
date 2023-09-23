package org.wassoaski.animeTomato.service.validation.anime;

import org.springframework.beans.factory.annotation.Autowired;
import org.wassoaski.animeTomato.model.Anime;
import org.wassoaski.animeTomato.repository.AnimeRepository;
import org.wassoaski.animeTomato.service.validation.ModelValidationChain;

import java.util.Optional;

public class IdValidation extends ModelValidationChain<Anime> {

    @Override
    protected boolean isValid(Anime model) {
        Long id = model.getId();
        if (id != null) {
            return false;
        }
        return true;
    }
}
