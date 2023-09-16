package org.wassoaski.animeTomato.model;

import org.junit.Assert;
import org.junit.Test;

public class CriticTest {

    private final String CRITIC = "This is a critic";

    private User createUser(String name){
        return new User(name, "12345678");
    }

    private Anime createAnime(String name){
        return new Anime(name, "Desciption of Anime");
    }

    @Test
    public void shouldHaveCriticInCritic(){
        Anime anime = this.createAnime("One Piece");
        User user = this.createUser("Rafael");
        Critic critic = new Critic(CRITIC, user, anime);

        Assert.assertEquals(CRITIC, critic.getCritic());
    }
    @Test
    public void shouldHaveUserInCritic(){
        Anime anime = this.createAnime("One Piece");
        User user = this.createUser("Rafael");
        Critic critic = new Critic(CRITIC, user, anime);

        Assert.assertEquals(user, critic.getUser());
    }
}
