package cz.osu.fladlu.be_watchlist.service;

import cz.osu.fladlu.be_watchlist.model.dto.genre.GenreCreateDTO;
import cz.osu.fladlu.be_watchlist.model.dto.genre.GenreDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreService {
    GenreDTO getGenreById(Long id);
    Page<GenreDTO> getAllGenres(Pageable pageable);
    GenreDTO createGenre(GenreCreateDTO genreCreateDTO);
    GenreDTO updateGenre(Long id, GenreDTO genreDTO);
    void deleteGenre(Long id);
}