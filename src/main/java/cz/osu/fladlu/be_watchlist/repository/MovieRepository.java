package cz.osu.fladlu.be_watchlist.repository;

import cz.osu.fladlu.be_watchlist.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
