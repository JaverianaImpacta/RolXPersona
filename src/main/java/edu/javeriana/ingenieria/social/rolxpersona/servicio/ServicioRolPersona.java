package edu.javeriana.ingenieria.social.rolxpersona.servicio;

import edu.javeriana.ingenieria.social.rolxpersona.dominio.RolPersona;
import edu.javeriana.ingenieria.social.rolxpersona.repositorio.RepositorioRolPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioRolPersona {
    @Autowired
    private RepositorioRolPersona repositorio;

    public List<RolPersona> obtenerRolPersonas() {
        return repositorio.findAll();
    }

    public List<RolPersona> obtenerRolPersonas(String rol){
        return repositorio.findAllByRol(rol);
    }

    public List<RolPersona> obtenerRolPersonas(Integer cedula){
        return repositorio.findAllByCedula(cedula);
    }

    public RolPersona obtenerRolPersona(Integer id){
        return repositorio.findById(id).orElse(null);
    }

    public boolean rolPersonaExiste(Integer id){
        return repositorio.existsById(id);
    }

    public boolean rolPersonaExiste(String rol, Integer cedula){
        List<RolPersona> rolPersonas = obtenerRolPersonas(cedula);
        for(RolPersona rolPersona : rolPersonas){
            if(rolPersona.getRol().equals(rol)){
                return true;
            }
        }
        return false;
    }

    public RolPersona crearRolPersona(RolPersona rolPersona){
        return repositorio.save(rolPersona);
    }

    public RolPersona actualizarRolPersona(Integer id, RolPersona rolPersona){
        if(repositorio.findById(id).orElse(null) == null){
            return null;
        }
        rolPersona.setId(id);
        return repositorio.save(rolPersona);
    }

    public RolPersona borrarRolPersona(Integer id){
        RolPersona aux = repositorio.findById(id).orElse(null);
        if(aux == null){
            return aux;
        }
        repositorio.delete(aux);
        return aux;
    }
}
