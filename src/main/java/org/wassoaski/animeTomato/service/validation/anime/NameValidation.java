package org.wassoaski.animeTomato.service.validation.anime;

import org.springframework.beans.factory.annotation.Autowired;
import org.wassoaski.animeTomato.model.Anime;
import org.wassoaski.animeTomato.repository.AnimeRepository;
import org.wassoaski.animeTomato.service.validation.ModelValidationChain;

import java.util.Optional;

public class NameValidation extends ModelValidationChain<Anime> {

    @Autowired
    private AnimeRepository animeRepository;

    public NameValidation(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @Override
    protected boolean isValid(Anime model) {
        String name = model.getName();
        if (name.isEmpty()) {
            return false;
        }

        if(this.isNameInUse(name)){
            return false;
        }

        return true;
    }

    private boolean isNameInUse(String name){
        Optional<Anime> anime = this.animeRepository.findByName(name);

        return anime.isPresent();
    }
}
