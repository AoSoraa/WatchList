package cz.osu.fladlu.be_watchlist.config;

import cz.osu.fladlu.be_watchlist.model.dto.movie.MovieCreateDTO;
import cz.osu.fladlu.be_watchlist.model.entity.Movie;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        // Custom mapping for MovieCreateDTO to Movie
        modelMapper.addMappings(new PropertyMap<MovieCreateDTO, Movie>() {
            @Override
            protected void configure() {
                map().getUser().setId(source.getUserId());
                map().getDirector().setId(source.getDirectorId());
                map().getGenre().setId(source.getGenreId());
            }
        });

        return modelMapper;
    }
}