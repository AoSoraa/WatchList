package cz.osu.fladlu.be_watchlist.controller;

import cz.osu.fladlu.be_watchlist.model.dto.movie.MovieCreateDTO;
import cz.osu.fladlu.be_watchlist.model.dto.movie.MovieDTO;
import cz.osu.fladlu.be_watchlist.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
        MovieDTO movieDTO = movieService.getMovieById(id);
        return ResponseEntity.ok(movieDTO);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<MovieDTO>> getAllMovies(Pageable pageable) {
        Page<MovieDTO> movies = movieService.getAllMovies(pageable);
        return ResponseEntity.ok(movies);
    }

    @PostMapping("createMovie")
    public ResponseEntity<MovieDTO> createMovie(@Valid @RequestBody MovieCreateDTO movieCreateDTO) {
        return ResponseEntity.ok(movieService.createMovie(movieCreateDTO));
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieCreateDTO movieDTO) {
        MovieDTO updatedMovie = movieService.updateMovie(id, movieDTO);
        return ResponseEntity.ok(updatedMovie);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}