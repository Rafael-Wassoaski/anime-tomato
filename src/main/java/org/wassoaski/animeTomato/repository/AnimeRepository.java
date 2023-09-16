package org.wassoaski.animeTomato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wassoaski.animeTomato.model.Anime;
import org.wassoaski.animeTomato.model.User;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
}
