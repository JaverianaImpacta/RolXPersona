package edu.javeriana.ingenieria.social.rolxpersona.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="rolxpersona")
public class RolPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cedula;
    private String rol;
}
