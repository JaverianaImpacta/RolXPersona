package edu.javeriana.ingenieria.social.rolxpersona.repositorio;

import edu.javeriana.ingenieria.social.rolxpersona.dominio.RolPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioRolPersona extends JpaRepository<RolPersona, Integer> {
    List<RolPersona> findAllByRol(String rol);

    List<RolPersona> findAllByCedula(Integer cedula);
}
