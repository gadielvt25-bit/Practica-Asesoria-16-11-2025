package com.backend.backend.Security.Auth;

import com.backend.backend.Security.Auth.dto.AuthResponseDTO;
import com.backend.backend.Security.Auth.dto.LoginRequestDTO;
import com.backend.backend.Security.Auth.dto.RegisterRequestDTO;
import com.backend.backend.common.dto.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControllerImpl {

}
