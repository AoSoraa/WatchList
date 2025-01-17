package cz.osu.fladlu.be_watchlist.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "director_id", nullable = true)
    private Director director;

    @ManyToMany
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name= "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )

    private Set<Genre> genres = new HashSet<>();

    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }
}