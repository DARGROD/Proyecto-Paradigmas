package cr.ac.una.api;

import cr.ac.una.entity.Mocion;
import cr.ac.una.repository.MocionRepository;
import cr.ac.una.request.TipoMocionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cr.ac.una.entity.Tipo_Mocion;
import cr.ac.una.repository.TipoMocionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path="/tipo_mocion")
public class Tipo_MocionRest {

    @Autowired
    private TipoMocionRepository tipo_MocionRepository;


    @GetMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<List<Tipo_Mocion>> findAll(){
        List<Tipo_Mocion> list = new ArrayList<Tipo_Mocion>();
        tipo_MocionRepository.findAll().forEach(e->list.add(e));
        return ResponseEntity.ok(list);
    }

    @PostMapping
    @CrossOrigin(origins = "*", maxAge = 3600) // create
    public ResponseEntity<Tipo_Mocion> create(@RequestBody Tipo_Mocion objeto){
        return ResponseEntity.ok(tipo_MocionRepository.save(objeto));
        // si se devuelve un return ResponseEntity.ok() sin parametros de hace ->
        // return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Tipo_Mocion> findById(@PathVariable Long id) {
        Optional<Tipo_Mocion> objeto = tipo_MocionRepository.findById(id);
        if (!objeto.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(objeto.get());
    }

    @PutMapping
    // modifica
    // antes de modificarla, hay que hacerle un attach con el findBy ->
    // el isPresent() valida si la persona existe -> es necesaria esa verificacion
    // si no existe, se devuelve el BAD REQUEST -> ResponseEntity.badRequest().build();
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Tipo_Mocion> update(@RequestBody Tipo_Mocion objeto) {
        if (!tipo_MocionRepository.findById(objeto.getId()).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tipo_MocionRepository.save(objeto));
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity delete(@PathVariable Long id) {
        if (!tipo_MocionRepository.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        tipo_MocionRepository.delete(tipo_MocionRepository.findById(id).get());
        return ResponseEntity.ok().build();
    }

}
