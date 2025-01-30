package cz.osu.fladlu.be_watchlist.controller;

import cz.osu.fladlu.be_watchlist.jwt.JwtResponse;
import cz.osu.fladlu.be_watchlist.jwt.JwtUtil;
import cz.osu.fladlu.be_watchlist.model.dto.user.UserCreateDTO;
import cz.osu.fladlu.be_watchlist.model.dto.user.UserDTO;
import cz.osu.fladlu.be_watchlist.repository.UserRepository;
import cz.osu.fladlu.be_watchlist.service.UserService;
import cz.osu.fladlu.be_watchlist.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public UserDTO signup(@RequestBody UserCreateDTO userCreateDTO) {
        return userService.createUser(userCreateDTO);
    }

    @GetMapping("/validateToken")
    public String validateToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "Token is valid";
        } else {
            return "Token is invalid";
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCreateDTO loginDTO) throws Exception {
        var authenticatedUser = authenticationService.authenticate(loginDTO);
        var token = jwtUtil.generateToken(authenticatedUser);
        return ResponseEntity.ok(token);
    }



}
