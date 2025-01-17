package cz.osu.fladlu.be_watchlist.repository;

import cz.osu.fladlu.be_watchlist.model.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
