package cz.osu.fladlu.be_watchlist.controller;

import cz.osu.fladlu.be_watchlist.model.dto.genre.GenreCreateDTO;
import cz.osu.fladlu.be_watchlist.model.dto.genre.GenreDTO;
import cz.osu.fladlu.be_watchlist.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping(produces = "application/json")
    public Page<GenreDTO> getAllGenres(Pageable pageable) {
        return genreService.getAllGenres(pageable);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.getGenreById(id));
    }

    @PostMapping("/createGenre")
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreCreateDTO genreCreateDTO) {
        GenreDTO genre = genreService.createGenre(genreCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genre);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable Long id, @Valid @RequestBody GenreDTO genreDTO) {
        return ResponseEntity.ok(genreService.updateGenre(id, genreDTO));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}