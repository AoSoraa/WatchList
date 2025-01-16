package cz.osu.fladlu.be_watchlist.service.impl;

import cz.osu.fladlu.be_watchlist.model.dto.genre.GenreCreateDTO;
import cz.osu.fladlu.be_watchlist.model.dto.genre.GenreDTO;
import cz.osu.fladlu.be_watchlist.model.entity.Genre;
import cz.osu.fladlu.be_watchlist.repository.GenreRepository;
import cz.osu.fladlu.be_watchlist.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final ModelMapper modelMapper;
    private final GenreRepository genreRepository;

    @Override
    public GenreDTO getGenreById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        return modelMapper.map(genre, GenreDTO.class);
    }

    @Override
    public Page<GenreDTO> getAllGenres(Pageable pageable) {
        return genreRepository.findAll(pageable).map(genre -> modelMapper.map(genre, GenreDTO.class));
    }

    @Override
    public GenreDTO createGenre(GenreCreateDTO genreCreateDTO) {
        var genre = new Genre();
        genre.setName(genreCreateDTO.getName());
        return modelMapper.map(genreRepository.save(genre), GenreDTO.class);
    }

    @Override
    public GenreDTO updateGenre(Long id, GenreDTO genreDTO) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        modelMapper.map(genreDTO, genre);
        genre = genreRepository.save(genre);
        return modelMapper.map(genre, GenreDTO.class);
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}