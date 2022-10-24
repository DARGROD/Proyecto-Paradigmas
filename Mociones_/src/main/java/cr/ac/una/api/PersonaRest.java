package cr.ac.una.api;

import cr.ac.una.entity.Persona;
import cr.ac.una.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/personas")
public class PersonaRest {

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<List<Persona>> findAll(){
        List<Persona> list = new ArrayList<Persona>();
        personaRepository.findAll().forEach(e->list.add(e));
        return ResponseEntity.ok(list);
    }

    @PostMapping
    @CrossOrigin(origins = "*", maxAge = 3600) // create
    public ResponseEntity create(@RequestBody Persona persona){
        return ResponseEntity.ok(personaRepository.save(persona));
        // si se devuelve un return ResponseEntity.ok() sin oarametros de hace ->
        // return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Persona> findById(@PathVariable Long id) {
        Optional<Persona> persona = personaRepository.findById(id);
        if (!persona.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(persona.get());
    }

    @PutMapping
    // modifica
    // antes de modificarla, hay que hacerle un attach con el findBy ->
    // el isPresent() valida si la persona existe -> es necesaria esa verificacion
    // si no existe, se devuelve el BAD REQUEST -> ResponseEntity.badRequest().build();
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Persona> update(@RequestBody Persona persona) {
        if (!personaRepository.findById(persona.getId()).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(personaRepository.save(persona));
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity delete(@PathVariable Long id) {
        if (!personaRepository.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        personaRepository.delete(personaRepository.findById(id).get());
        return ResponseEntity.ok().build();
    }

}
