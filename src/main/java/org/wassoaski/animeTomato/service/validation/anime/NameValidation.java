package org.wassoaski.animeTomato.service.validation.anime;

import org.springframework.beans.factory.annotation.Autowired;
import org.wassoaski.animeTomato.exception.InvalidModel;
import org.wassoaski.animeTomato.exception.ModelNotFoundException;
import org.wassoaski.animeTomato.model.Anime;
import org.wassoaski.animeTomato.repository.AnimeRepository;
import org.wassoaski.animeTomato.service.validation.ModelValidationChain;

import java.util.Optional;

public class NameValidation extends ModelValidationChain<Anime> {

    private AnimeRepository animeRepository;

    public NameValidation(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @Override
    protected void isValid(Anime model) throws InvalidModel {
        String name = model.getName();
        if (name == null || name.isEmpty()) {
            throw new InvalidModel("O nome do modelo não pode ser vazio");
        }

        if(this.isNameInUse(name)){
            throw new InvalidModel("Nome já em uso");
        }
    }

    private boolean isNameInUse(String name){
        Optional<Anime> anime = this.animeRepository.findByName(name);

        return anime.isPresent();
    }
}
