package org.wassoaski.animeTomato.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.wassoaski.animeTomato.App;
import org.wassoaski.animeTomato.exception.InvalidModel;
import org.wassoaski.animeTomato.exception.ModelNotFoundException;
import org.wassoaski.animeTomato.model.Anime;
import org.wassoaski.animeTomato.model.Critic;
import org.wassoaski.animeTomato.model.User;
import org.wassoaski.animeTomato.repository.AnimeRepository;
import org.wassoaski.animeTomato.repository.CriticRepository;
import org.wassoaski.animeTomato.repository.UserRepository;

@SpringBootTest(classes = App.class)
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class AnimeServiceTest {

    @Autowired
    private AnimeService animeService;
    @Autowired
    private CriticRepository criticRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnimeRepository animeRepository;

    private final String ANIME_NAME = "One Piece";
    private final String ANIME_DESCRIPTION = "This is a description";

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

    @AfterEach
    public void setup(){
        this.animeRepository.deleteAll();
        this.userRepository.deleteAll();
        this.criticRepository.deleteAll();
        System.out.println("DELETADOS");
    }

    @Test
    public void shouldCalculateAnimeScoreByAnimeId() throws ModelNotFoundException {
        Anime anime = this.createAnime("One Piece");
        User user = this.createUser("Rafael");
        this.createCritic(anime, user);
        this.createCritic(anime, user);
        this.createCritic(anime, user);
        anime.giveScore(5);
        anime.giveScore(4);
        anime.giveScore(3);
        animeRepository.save(anime);

        float score = animeService.calculateScoreById(anime.getId());
        Assert.assertEquals(4, score, 0.0f);
    }

    @Test
    public void shouldThrowModelNotFoundWhenCalculatingAnimeThatDoesNotExist() throws ModelNotFoundException {
        Assert.assertThrows(ModelNotFoundException.class, () -> {
            animeService.calculateScoreById(0L);
        });
    }

    @Test
    public void shouldGetAnimeByAnimeId() throws ModelNotFoundException {
        Anime anime = this.createAnime("One Piece");

        Anime animeById = animeService.getAnimeById(anime.getId());
        Assert.assertSame(anime.getId(), animeById.getId());
    }

    @Test
    public void shouldThrowModelNotFoundWhenGettingAnimeThatDoesNotExist() throws ModelNotFoundException {
        Assert.assertThrows(ModelNotFoundException.class, () -> {
            animeService.getAnimeById(0L);
        });
    }

    @Test
    public void shouldCreateAnimeWithNameAndDescription() throws InvalidModel {
        Anime dtoAnime = new Anime(ANIME_NAME, ANIME_DESCRIPTION);

        Anime anime = animeService.createAnime(dtoAnime);

        Assert.assertEquals(dtoAnime.getName(), anime.getName());
        Assert.assertEquals(dtoAnime.getDescription(), anime.getDescription());
        Assert.assertNotNull(anime.getId());
    }

    @Test
    public void shouldThrowExceptionWhenCreatingAnimeWithNameThatAlreadyExists() throws InvalidModel {
        Anime baseAnime = this.createAnime("One Piece");

        Anime dtoAnime = new Anime(baseAnime.getName(), baseAnime.getDescription());

        Assert.assertThrows(InvalidModel.class, ()->animeService.createAnime(dtoAnime));
    }

    @Test
    public void shouldThrowExceptionWhenCreatingAnimeWithId() throws InvalidModel {

        Anime dtoAnime = new Anime(1L, ANIME_NAME, ANIME_DESCRIPTION);

        Assert.assertThrows(InvalidModel.class, ()->animeService.createAnime(dtoAnime));
    }
}
