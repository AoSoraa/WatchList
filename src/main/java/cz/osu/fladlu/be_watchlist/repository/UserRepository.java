package cz.osu.fladlu.be_watchlist.repository;

import cz.osu.fladlu.be_watchlist.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);

}
