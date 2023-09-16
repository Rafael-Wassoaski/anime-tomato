package org.wassoaski.animeTomato.model;

import javax.persistence.*;


@Table
@Entity
public class Critic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String critic;
    @OneToOne
    private User user;
    @OneToOne
    private Anime anime;

    public Critic(String critic, User user, Anime anime) {
        this.critic = critic;
        this.user = user;
        this.anime = anime;
    }

    public Critic(Long id, String critic, User user, Anime anime) {
        this.id = id;
        this.critic = critic;
        this.user = user;
        this.anime = anime;
    }

    public String getCritic() {
        return critic;
    }

    public User getUser() {
        return user;
    }
}
