package cz.osu.fladlu.be_watchlist.service;

import cz.osu.fladlu.be_watchlist.model.dto.movie.MovieCreateDTO;
import cz.osu.fladlu.be_watchlist.model.dto.movie.MovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {
    MovieDTO getMovieById(Long id);

    Page<MovieDTO> getAllMovies(Pageable pageable);

    MovieDTO createMovie(MovieCreateDTO movieCreateDTO);

    MovieDTO updateMovie(Long id, MovieCreateDTO movieDTO);

    void deleteMovie(Long id);

}
