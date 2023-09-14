package org.wassoaski.animeTomato.model;

public class Anime {

    private String name;
    private String description;

    private float score;
    private int critcs;

    private int numberOfFans;

    public Anime(String name, String description) {
        this.name = name;
        this.description = description;
        this.numberOfFans = 0;
        this.score = 0;
        this.critcs = 0;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfFans() {
        return numberOfFans;
    }

    public float getScore() {
        return score;
    }

    public void addFan() {
        this.numberOfFans += 1;
    }

    public void giveScore(float score){
        this.score += score;
        this.critcs += 1;
    }

    public float calculateScore() {
        return score / this.critcs;
    }
}
