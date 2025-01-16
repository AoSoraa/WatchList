package cz.osu.fladlu.be_watchlist.service;

import cz.osu.fladlu.be_watchlist.model.dto.director.DirectorCreateDTO;
import cz.osu.fladlu.be_watchlist.model.dto.director.DirectorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DirectorService {
    DirectorDTO getDirectorById(Long id);
    Page<DirectorDTO> getAllDirectors(Pageable pageable);
    DirectorDTO createDirector(DirectorCreateDTO directorCreateDTO);
    DirectorDTO updateDirector(Long id, DirectorDTO directorDTO);
    void deleteDirector(Long id);
}