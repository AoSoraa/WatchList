package cz.osu.fladlu.be_watchlist.service.impl;

import cz.osu.fladlu.be_watchlist.exception.NotFoundException;
import cz.osu.fladlu.be_watchlist.model.dto.user.UserCreateDTO;
import cz.osu.fladlu.be_watchlist.model.dto.user.UserDTO;
import cz.osu.fladlu.be_watchlist.model.entity.User;
import cz.osu.fladlu.be_watchlist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    public UserDTO signup(UserCreateDTO input) {
        var user = new User();
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        modelMapper.map(input, user);
        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

    public User authenticate(UserCreateDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );
        return userRepository.findByUsername(input.getUsername())
                .orElseThrow( () -> new NotFoundException("User not found"));
    }

    public UserDTO getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) authentication.getPrincipal();
        return modelMapper.map(user, UserDTO.class);
    }

}
