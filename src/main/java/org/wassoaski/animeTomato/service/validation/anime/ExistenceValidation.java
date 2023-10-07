package org.wassoaski.animeTomato.service.validation.anime;

import org.springframework.beans.factory.annotation.Autowired;
import org.wassoaski.animeTomato.exception.InvalidModel;
import org.wassoaski.animeTomato.exception.ModelNotFoundException;
import org.wassoaski.animeTomato.model.Anime;
import org.wassoaski.animeTomato.repository.AnimeRepository;
import org.wassoaski.animeTomato.service.validation.ModelValidationChain;

import java.util.Optional;

public class ExistenceValidation extends ModelValidationChain<Anime> {

    private AnimeRepository animeRepository;

    public ExistenceValidation(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @Override
    protected void isValid(Anime model) throws InvalidModel, ModelNotFoundException {
        Long id = model.getId();
        if (id == null) {
            throw new InvalidModel("O id do modelo não pode ser vazio");
        }

        if(!this.idExists(id)){
            throw new ModelNotFoundException(Anime.class.getName(), id);
        }
    }

    private boolean idExists(Long id){
        Optional<Anime> anime = this.animeRepository.findById(id);

        return anime.isPresent();
    }
}