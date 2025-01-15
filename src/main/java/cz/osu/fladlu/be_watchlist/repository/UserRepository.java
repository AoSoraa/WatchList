package cz.osu.fladlu.be_watchlist.repository;

import cz.osu.fladlu.be_watchlist.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

}
