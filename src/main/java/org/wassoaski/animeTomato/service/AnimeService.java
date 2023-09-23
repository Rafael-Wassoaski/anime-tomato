package org.wassoaski.animeTomato.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wassoaski.animeTomato.exception.InvalidModel;
import org.wassoaski.animeTomato.exception.ModelNotFoundException;
import org.wassoaski.animeTomato.model.Anime;
import org.wassoaski.animeTomato.repository.AnimeRepository;
import org.wassoaski.animeTomato.repository.CriticRepository;
import org.wassoaski.animeTomato.service.validation.anime.IdValidation;
import org.wassoaski.animeTomato.service.validation.anime.NameValidation;

import java.util.Optional;

@Service
public class AnimeService {

    @Autowired
    private CriticRepository criticRepository;

    @Autowired
    private AnimeRepository animeRepository;

    public Anime getAnimeById(Long id) throws ModelNotFoundException {
        Optional<Anime> animeOptional = animeRepository.findById(id);

        if(animeOptional.isPresent()){
            return animeOptional.get();
        }

        throw new ModelNotFoundException(Anime.class.getName(), id);
    }

    public float calculateScoreById(Long id) throws ModelNotFoundException {
        Anime anime = this.getAnimeById(id);
        return this.calculateScore(anime);
    }

    private float calculateScore(Anime anime){
        int numberOfCritics = criticRepository.countByAnime(anime);
        float score = anime.getScore();

        return score / numberOfCritics;
    }

    public Anime createAnime(Anime dtoAnime) throws InvalidModel {
        if(!isAnimeValid(dtoAnime)){
            throw new InvalidModel(Anime.class.getName());
        }

        return animeRepository.save(dtoAnime);
    }


    private boolean isAnimeValid(Anime anime){
        NameValidation animeNameValidation = new NameValidation(this.animeRepository);
        IdValidation animeIdValidation = new IdValidation();

        animeNameValidation.setNextValidation(animeIdValidation);

        return animeNameValidation.validate(anime);

    }
}
