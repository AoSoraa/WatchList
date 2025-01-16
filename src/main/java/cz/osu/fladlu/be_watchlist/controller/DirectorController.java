package cz.osu.fladlu.be_watchlist.controller;

import cz.osu.fladlu.be_watchlist.model.dto.director.DirectorCreateDTO;
import cz.osu.fladlu.be_watchlist.model.dto.director.DirectorDTO;
import cz.osu.fladlu.be_watchlist.model.dto.genre.GenreDTO;
import cz.osu.fladlu.be_watchlist.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping(produces = "application/json")
    public Page<DirectorDTO> getAllDirectors(Pageable pageable) {
        return directorService.getAllDirectors(pageable);
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<DirectorDTO> getDirectorById(@PathVariable Long id) {
        return ResponseEntity.ok(directorService.getDirectorById(id));
    }

    @PostMapping("createDirector")
    public ResponseEntity<DirectorDTO> createDirector(@RequestBody DirectorCreateDTO directorCreateDTO) {
        return ResponseEntity.ok(directorService.createDirector(directorCreateDTO));
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DirectorDTO> updateDirector(@PathVariable Long id, @Valid @RequestBody DirectorDTO directorDTO) {
        return ResponseEntity.ok(directorService.updateDirector(id, directorDTO));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
        return ResponseEntity.noContent().build();
    }
}