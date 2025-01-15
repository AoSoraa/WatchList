package cz.osu.fladlu.be_watchlist.service;

import cz.osu.fladlu.be_watchlist.model.dto.user.UserCreateDTO;
import cz.osu.fladlu.be_watchlist.model.dto.user.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDTO getUserById(Long id);

    Page<UserDTO> getAllUsers(Pageable pageable);

    UserDTO createUser(UserCreateDTO userCreateDTO);

    UserDTO updateUser(Long id,UserCreateDTO userDTO);

    void deleteUser(Long id);

}