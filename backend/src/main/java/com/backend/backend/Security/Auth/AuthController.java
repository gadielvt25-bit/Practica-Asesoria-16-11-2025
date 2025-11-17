package com.backend.backend.Security.Auth;

import com.backend.backend.Security.Auth.Dtos.AuthResponse;
import com.backend.backend.Security.Auth.Dtos.RequestRegisterDTO;
import com.backend.backend.users.domain.UserDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RequestRegisterDTO userDTO) throws BadRequestException {
        return ResponseEntity.ok(authService.register(userDTO));
    }

}
