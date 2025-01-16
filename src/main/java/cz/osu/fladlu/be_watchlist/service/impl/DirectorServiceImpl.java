package cz.osu.fladlu.be_watchlist.service.impl;

import cz.osu.fladlu.be_watchlist.model.dto.director.DirectorCreateDTO;
import cz.osu.fladlu.be_watchlist.model.dto.director.DirectorDTO;
import cz.osu.fladlu.be_watchlist.model.entity.Director;
import cz.osu.fladlu.be_watchlist.repository.DirectorRepository;
import cz.osu.fladlu.be_watchlist.service.DirectorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final ModelMapper modelMapper;
    private final DirectorRepository directorRepository;

    @Autowired
    public DirectorServiceImpl(ModelMapper modelMapper, DirectorRepository directorRepository) {
        this.modelMapper = modelMapper;
        this.directorRepository = directorRepository;
    }

    @Override
    public DirectorDTO getDirectorById(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found"));
        return modelMapper.map(director, DirectorDTO.class);
    }

    @Override
    public Page<DirectorDTO> getAllDirectors(Pageable pageable) {
        return directorRepository.findAll(pageable).map(director -> modelMapper.map(director, DirectorDTO.class));
    }

    @Override
    public DirectorDTO createDirector(DirectorCreateDTO directorCreateDTO) {
        var director = new Director();
        director.setName(directorCreateDTO.getName());
        return modelMapper.map(directorRepository.save(director), DirectorDTO.class);
    }

    @Override
    public DirectorDTO updateDirector(Long id, DirectorDTO directorDTO) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found"));
        modelMapper.map(directorDTO, director);
        director = directorRepository.save(director);
        return modelMapper.map(director, DirectorDTO.class);
    }

    @Override
    public void deleteDirector(Long id) {
        directorRepository.deleteById(id);
    }
}