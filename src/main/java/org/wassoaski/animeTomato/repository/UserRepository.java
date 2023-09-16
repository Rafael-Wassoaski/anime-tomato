package org.wassoaski.animeTomato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wassoaski.animeTomato.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
