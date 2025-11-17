package com.backend.backend.Security.Auth.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestRegisterDTO {

    private String nombre;
    private String email;
    private String password;
    private String tipoUsuario;
    private String cif; // Para comerciantes
    private String nombreNegocio; // Para comerciantes
    private String direccion; // Para comerciantes
    private String descripcion; // Para comerciantes
    private String phoneNumber; // Para comerciantes
}
