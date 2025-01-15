package cz.osu.fladlu.be_watchlist.repository;

import cz.osu.fladlu.be_watchlist.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
