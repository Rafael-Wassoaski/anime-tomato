package org.wassoaski.animeTomato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wassoaski.animeTomato.model.Anime;
import org.wassoaski.animeTomato.model.Critic;

@Repository
public interface CriticRepository extends JpaRepository<Critic, Long> {

    public int countByAnime(Anime anime);
}
