package root.auction.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import root.auction.dto.request.CreateAccountRequest;
import root.auction.dto.response.ApiResponse;
import root.auction.service.AccountService;

import static org.springframework.http.HttpStatus.CREATED;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/account")
public class AccountController {

    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;

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

}
