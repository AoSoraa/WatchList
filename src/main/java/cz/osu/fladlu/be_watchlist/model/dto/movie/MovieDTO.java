package cz.osu.fladlu.be_watchlist.model.dto.movie;

import cz.osu.fladlu.be_watchlist.model.entity.Director;
import cz.osu.fladlu.be_watchlist.model.entity.Genre;
import cz.osu.fladlu.be_watchlist.model.entity.Movie;
import cz.osu.fladlu.be_watchlist.model.entity.User;
import lombok.Data;

import java.io.DataInput;

@Data
public class MovieDTO {
    private Long id;
    private String title;
    private String note;
    private User user;
    private Director director;
    private Genre genre;

}