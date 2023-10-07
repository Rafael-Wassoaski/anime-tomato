package org.wassoaski.animeTomato.model;

import javax.persistence.*;

@Table
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String password;

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User() {}

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
