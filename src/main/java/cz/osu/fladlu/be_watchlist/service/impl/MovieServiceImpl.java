package cz.osu.fladlu.be_watchlist.service.impl;

import cz.osu.fladlu.be_watchlist.model.dto.movie.MovieCreateDTO;
import cz.osu.fladlu.be_watchlist.model.dto.movie.MovieDTO;
import cz.osu.fladlu.be_watchlist.model.entity.Director;
import cz.osu.fladlu.be_watchlist.model.entity.Genre;
import cz.osu.fladlu.be_watchlist.model.entity.Movie;
import cz.osu.fladlu.be_watchlist.repository.MovieRepository;
import cz.osu.fladlu.be_watchlist.repository.UserRepository;
import cz.osu.fladlu.be_watchlist.repository.DirectorRepository;
import cz.osu.fladlu.be_watchlist.repository.GenreRepository;
import cz.osu.fladlu.be_watchlist.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final DirectorRepository directorRepository;
    private final GenreRepository genreRepository;

    @Override
    public MovieDTO getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        return modelMapper.map(movie, MovieDTO.class);
    }

    @Override
    public Page<MovieDTO> getAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable).map(movie -> modelMapper.map(movie, MovieDTO.class));
    }

    @Override
    public MovieDTO createMovie(MovieCreateDTO movieCreateDTO) {
        var movie = new Movie();

        movie.setTitle(movieCreateDTO.getTitle());
        movie.setNote(movieCreateDTO.getNote());

        Genre genre = genreRepository.findById(movieCreateDTO.getGenreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        movie.setGenre(genre);

        Director director = directorRepository.findById(movieCreateDTO.getDirectorId())
                .orElseThrow(() -> new RuntimeException("Director not found"));
        movie.setDirector(director);

        return modelMapper.map(movie, MovieDTO.class);
    }

    @Override
    public MovieDTO updateMovie(Long id, MovieCreateDTO movieCreateDTO) {
        var movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        modelMapper.map(movieCreateDTO, movie);
        movie.setUser(userRepository.findById(movieCreateDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        movie.setDirector(directorRepository.findById(movieCreateDTO.getDirectorId())
                .orElseThrow(() -> new RuntimeException("Director not found")));
        movie.setGenre(genreRepository.findById(movieCreateDTO.getGenreId())
                .orElseThrow(() -> new RuntimeException("Genre not found")));
        return modelMapper.map(movieRepository.save(movie), MovieDTO.class);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}