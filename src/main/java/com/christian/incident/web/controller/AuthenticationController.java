package com.christian.incident.web.controller;

import com.christian.incident.Jwt.JwtToken;
import com.christian.incident.Jwt.JwtUserDetailsService;
import com.christian.incident.web.dto.UserLoginDto;
import com.christian.incident.web.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")

public class AuthenticationController {

    private final JwtUserDetailsService detailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> authentication(@RequestBody @Valid UserLoginDto login, HttpServletRequest request){
        log.info("Authentication process login : {}", login);
        try{
            UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(login.username(), login.password());
            authenticationManager.authenticate(authenticationToken);
            JwtToken token = detailsService.getTokenAuthenticated(login.username());
            return ResponseEntity.ok(token);
        }catch (AuthenticationException ex){
            log.warn("Bad credential from username '{}'", login.username());
        }

        return ResponseEntity.
                badRequest()
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credentials invalid."));
    }
}
