package cz.osu.fladlu.be_watchlist.controller;

import cz.osu.fladlu.be_watchlist.config.JwtResponse;
import cz.osu.fladlu.be_watchlist.model.dto.user.UserCreateDTO;
import cz.osu.fladlu.be_watchlist.model.dto.user.UserDTO;
import cz.osu.fladlu.be_watchlist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private cz.osu.fladlu.be_watchlist.util.JwtUtil jwtUtil;

    @PostMapping("/signup")
    public UserDTO signup(@RequestBody UserCreateDTO userCreateDTO) {
        return userService.createUser(userCreateDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCreateDTO loginDTO) throws Exception {
        System.out.println("Authenticating user: " + loginDTO.getUsername());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

            String jwtToken = jwtUtil.generateToken(loginDTO.getUsername());
            System.out.println("Authentication successful for user: " + loginDTO.getUsername());

            return ResponseEntity.ok(new JwtResponse(jwtToken));
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed for user: " + loginDTO.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

}