package org.wassoaski.animeTomato.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wassoaski.animeTomato.model.Anime;
import org.wassoaski.animeTomato.repository.CriticRepository;

@Service
public class AnimeService {

    @Autowired
    private CriticRepository criticRepository;

    public float calculateScore(Anime anime){
        int numberOfCritics = criticRepository.countByAnime(anime);
        float score = anime.getScore();

        return score / numberOfCritics;
    }
}
