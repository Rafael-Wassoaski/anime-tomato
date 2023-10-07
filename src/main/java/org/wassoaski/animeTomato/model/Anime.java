package org.wassoaski.animeTomato.model;

import javax.persistence.*;

@Table
@Entity
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column
    private String description;
    @Column
    private float score;
    @Column
    private int numberOfFans;

    public Anime() {}

    public Anime(String name, String description) {
        this.name = name;
        this.description = description;
        this.numberOfFans = 0;
        this.score = 0;
    }

    public Anime(Long id, String name, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.numberOfFans = 0;
        this.score = 0;
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

    public Long getId() {
        return id;
    }

    public void addFan() {
        this.numberOfFans += 1;
    }

    public void giveScore(float score){
        this.score += score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
