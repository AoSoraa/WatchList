package cz.osu.fladlu.be_watchlist.model.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserCreateDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
