package org.wassoaski.animeTomato.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.wassoaski.animeTomato.App;
import org.wassoaski.animeTomato.model.Anime;
import org.wassoaski.animeTomato.model.Critic;
import org.wassoaski.animeTomato.model.User;
import org.wassoaski.animeTomato.repository.AnimeRepository;
import org.wassoaski.animeTomato.repository.CriticRepository;
import org.wassoaski.animeTomato.repository.UserRepository;

@SpringBootTest(classes = App.class)
@ActiveProfiles("test")
@WebAppConfiguration
public class AnimeServiceTest {

    @Autowired
    private AnimeService animeService;
    @Autowired
    private CriticRepository criticRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnimeRepository animeRepository;

    private Critic createCritic(Anime anime, User user){
        Critic critic = new Critic("Critic", user, anime);

        return criticRepository.save(critic);
    }

    private User createUser(String name){
        User user = new User(name, "12345678");
        return userRepository.save(user);
    }

    private Anime createAnime(String name){
        Anime anime = new Anime(name, "Desciption of Anime");
        return animeRepository.save(anime);
    }

    @Test
    public void shouldCalculateAnimeScore(){
        System.out.println(animeRepository);
        System.out.println(criticRepository);
        System.out.println(userRepository);

        Anime anime = this.createAnime("One Piece");
        User user = this.createUser("Rafael");
        this.createCritic(anime, user);
        this.createCritic(anime, user);
        this.createCritic(anime, user);

        float score = animeService.calculateScore(anime);

    }
}
