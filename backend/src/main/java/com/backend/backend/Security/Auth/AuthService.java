package com.backend.backend.Security.Auth;


import com.backend.backend.Comerciante.domain.Comerciante;
import com.backend.backend.Comerciante.infrastructure.ComercianteRepository;
import com.backend.backend.Security.Auth.Dtos.AuthResponse;
import com.backend.backend.Security.Auth.Dtos.RequestRegisterDTO;
import com.backend.backend.Security.JwtService;
import com.backend.backend.users.domain.TipoUsuario;
import com.backend.backend.users.domain.UserDTO;
import com.backend.backend.users.domain.Usuario;
import com.backend.backend.users.infrastrucure.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final ComercianteRepository comercianteRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, ComercianteRepository comercianteRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.comercianteRepository = comercianteRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }


    public AuthResponse register(RequestRegisterDTO userDTO) throws BadRequestException {
        Optional<Usuario> u = userRepository.findByEmail(userDTO.getEmail());
        if (u.isPresent()){
            throw new BadRequestException("Email already exists");
        }

        Usuario usuario;

        if (userDTO.getTipoUsuario().equalsIgnoreCase(TipoUsuario.COMERCIANTE.name())) {
            if (comercianteRepository.existsByCif(userDTO.getCif())) {
                throw new BadRequestException("CIF already exists");
            }
            usuario = userRepository.save(toUsuario(userDTO));
            comercianteRepository.save(toComerciante(userDTO, usuario));
        } else {
            usuario = userRepository.save(toUsuario(userDTO));
        }

        String token = jwtService.generateToken(usuario);
        return AuthResponse.builder()
                .token(token)
                .usuario(toUserDTO(usuario))
                .build();
    }

    private Usuario toUsuario(RequestRegisterDTO userDTO) {
        return Usuario.builder()
                .nombre(userDTO.getNombre())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .tipoUsuario(TipoUsuario.valueOf(userDTO.getTipoUsuario().toUpperCase()))
                .build();
    }

    private Comerciante toComerciante(RequestRegisterDTO userDTO, Usuario usuario) {
        return Comerciante.builder()
                .usuario(usuario)
                .cif(userDTO.getCif())
                .nombreNegocio(userDTO.getNombreNegocio())
                .direccion(userDTO.getDireccion())
                .descripcion(userDTO.getDescripcion())
                .phoneNumber(userDTO.getPhoneNumber())
                .activo(true)
                .build();
    }
    private UserDTO toUserDTO(Usuario usuario) {
        return UserDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .tipoUsuario(usuario.getTipoUsuario())
                .build();
    }
}
