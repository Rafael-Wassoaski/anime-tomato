package org.wassoaski.animeTomato.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class User {

    @Id
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

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
