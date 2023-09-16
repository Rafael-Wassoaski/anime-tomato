package org.wassoaski.animeTomato.model;

import org.junit.Assert;
import org.junit.Test;

public class AnimeTest {
    private final String NAME = "One Piece";
    private final String DESCRIPTION = "Anime com mais de 1000 episódios, sobre piratas";

    @Test
    public void shouldCreateAnimeWithName(){
        Anime anime = new Anime(NAME, DESCRIPTION);

        Assert.assertEquals(NAME, anime.getName());
    }

    @Test
    public void shouldCreateAnimeWithDescriptions(){
        Anime anime = new Anime(NAME, DESCRIPTION);

        Assert.assertEquals(DESCRIPTION, anime.getDescription());
    }

    @Test
    public void shouldStartNumberOfFansInZero(){
        Anime anime = new Anime(NAME, DESCRIPTION);

        Assert.assertEquals(0, anime.getNumberOfFans());
    }

    @Test
    public void shouldIncrementNumberOfFans(){
        Anime anime = new Anime(NAME, DESCRIPTION);
        anime.addFan();

        Assert.assertEquals(1, anime.getNumberOfFans());
    }

    @Test
    public void shouldStartStarsAtZero(){
        Anime anime = new Anime(NAME, DESCRIPTION);

        Assert.assertEquals(0.0f, anime.getScore(), 0.0f);
    }
}
