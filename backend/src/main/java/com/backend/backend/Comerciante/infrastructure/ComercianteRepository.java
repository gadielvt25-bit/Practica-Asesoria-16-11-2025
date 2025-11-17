package com.backend.backend.Comerciante.infrastructure;


import com.backend.backend.Comerciante.domain.Comerciante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComercianteRepository extends JpaRepository<Comerciante, Long> {

    boolean existsByCif(String cif);
}
