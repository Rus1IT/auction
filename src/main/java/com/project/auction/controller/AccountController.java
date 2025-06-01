package com.project.auction.controller;

import com.project.auction.dto.request.AuthenticateRequest;
import com.project.auction.webtoken.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.auction.dto.request.CreateAccountRequest;
import com.project.auction.dto.response.ApiResponse;
import com.project.auction.service.AccountService;

import static org.springframework.http.HttpStatus.CREATED;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/account")
public class AccountController {

    private final AccountService accountService;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtService jwtService;

//    @PostMapping("/authenticate")
//    public ResponseEntity<?> createAccount(@Valid  @RequestBody CreateAccountRequest request) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                authorizationRequestDto.getAccount(), authorizationRequestDto.getPassword()
//        ));
//        if(authentication.isAuthenticated()){
//            return jwtService.generateToken(userDetailsService.loadUserByUsername(authorizationRequestDto.getAccount()));
//        }
//        throw new NotFoundException(String.format("Username %s not found", authorizationRequestDto.getAccount()),"username");
//    }


    @PostMapping
    public ResponseEntity<?> addAccount(@Valid @RequestBody CreateAccountRequest authorizationRequestDto) {
        accountService.createAccount(authorizationRequestDto);
        return ResponseEntity.status(CREATED).body(ApiResponse.success(null,"Account created successfully"));
    }

    @PostMapping(value = "/authenticate", produces = "text/plain")
    public String authenticate(@RequestBody @Valid AuthenticateRequest req) {

        // 1. Verify credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password()));

        // 2. Build token and return it directly
        return jwtService.generateToken(
                userDetailsService.loadUserByUsername(req.email()));
    }
}
