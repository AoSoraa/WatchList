package cz.osu.fladlu.be_watchlist.model.dto.movie;

import lombok.Data;

@Data
public class MovieCreateDTO {
    private String title;
    private String note;
    private Long userId;
    private Long directorId;
    private Long genreId;
}