package org.wassoaski.animeTomato.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wassoaski.animeTomato.exception.InvalidModel;
import org.wassoaski.animeTomato.exception.ModelNotFoundException;
import org.wassoaski.animeTomato.model.Anime;
import org.wassoaski.animeTomato.repository.AnimeRepository;
import org.wassoaski.animeTomato.repository.CriticRepository;
import org.wassoaski.animeTomato.service.validation.ModelValidationChain;
import org.wassoaski.animeTomato.service.validation.anime.ExistenceValidation;
import org.wassoaski.animeTomato.service.validation.anime.IdValidation;
import org.wassoaski.animeTomato.service.validation.anime.NameValidation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {

    @Autowired
    private CriticRepository criticRepository;

    @Autowired
    private AnimeRepository animeRepository;


    public Anime createAnime(Anime dtoAnime) throws Exception {
        List<ModelValidationChain> validations = Arrays.asList(
                new NameValidation(this.animeRepository),
                new IdValidation()
        );

        isAnimeValid(dtoAnime, validations);

        return animeRepository.save(dtoAnime);
    }

    private void isAnimeValid(Anime anime, List<ModelValidationChain> validationChain) throws Exception{
        ModelValidationChain modelValidationChain = validationChain.get(0);

        for(int index = 1; index < validationChain.size(); index++){
            ModelValidationChain modelValidationNextChain = validationChain.get(index);
            modelValidationChain.setNextValidation(modelValidationNextChain);
            modelValidationChain = modelValidationNextChain;
        }

        modelValidationChain = validationChain.get(0);
        modelValidationChain.validate(anime);
    }

    public Anime updateAnime(Anime dtoAnime) throws Exception {
        List<ModelValidationChain> validations = Arrays.asList(
                new NameValidation(this.animeRepository),
                new ExistenceValidation(this.animeRepository)
        );

        isAnimeValid(dtoAnime, validations);
        return animeRepository.save(dtoAnime);
    }

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
}
