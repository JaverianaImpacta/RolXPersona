package edu.javeriana.ingenieria.social.rolxpersona.controlador;

import edu.javeriana.ingenieria.social.rolxpersona.dominio.RolPersona;
import edu.javeriana.ingenieria.social.rolxpersona.servicio.ServicioRolPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rolPersonas")
@CrossOrigin(origins="http://localhost:4200")
public class ControladorRolPersona {
    @Autowired
    private ServicioRolPersona servicio;

    @GetMapping("listar")
    public List<RolPersona> obtenerRolPersonas() {
        return servicio.obtenerRolPersonas();
    }

    @GetMapping("obtener")
    public ResponseEntity<RolPersona> obtenerRolPersona(@RequestParam Integer id) {
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerRolPersona(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerRolPersona(id), HttpStatus.OK);
    }

    @GetMapping("obtenerCedula")
    public ResponseEntity<List<RolPersona>> obtenerRolPersonas(@RequestParam Integer cedula) {
        if(cedula == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerRolPersonas(cedula).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerRolPersonas(cedula), HttpStatus.OK);
    }

    @GetMapping("obtenerRol")
    public ResponseEntity<List<RolPersona>> obtenerRolPersonas(@RequestParam String rol) {
        if(rol == null || rol.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerRolPersonas(rol).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerRolPersonas(rol), HttpStatus.OK);
    }

    @PostMapping("crear")
    public ResponseEntity<RolPersona> crearRolPersona(@RequestBody RolPersona rolPersona) {
        if(rolPersona == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.rolPersonaExiste(rolPersona.getId()) || servicio.rolPersonaExiste(rolPersona.getRol(), rolPersona.getCedula())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(servicio.crearRolPersona(rolPersona), HttpStatus.CREATED);
    }

    @PutMapping("actualizar")
    public ResponseEntity<RolPersona> actualizarRolPersona(@RequestParam Integer id, @RequestBody RolPersona rolPersona){
        if(id == null || rolPersona == null || !id.equals(rolPersona.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.actualizarRolPersona(id, rolPersona) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rolPersona, HttpStatus.OK);
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<HttpStatus> borrarRolPersona(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.borrarRolPersona(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
