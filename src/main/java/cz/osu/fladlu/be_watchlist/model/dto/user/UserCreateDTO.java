package cz.osu.fladlu.be_watchlist.model.dto.user;

import lombok.Data;

@Data
public class UserCreateDTO {
    private String username;
    private String password;
}
