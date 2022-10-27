package cr.ac.una.api;

import cr.ac.una.entity.Mocion;
import cr.ac.una.entity.Persona;
import cr.ac.una.entity.Tipo_Mocion;
import cr.ac.una.repository.MocionRepository;
import cr.ac.una.repository.TipoMocionRepository;
import cr.ac.una.request.TipoMocionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path="/mociones")
public class MocionRest {

    @Autowired
    private MocionRepository mocionRepository;
    @Autowired
    private TipoMocionRepository tipo_MocionRepository;

    @GetMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<List<Mocion>> findAll(){
        List<Mocion> list = new ArrayList<Mocion>();
        mocionRepository.findAll().forEach(e->list.add(e));
        return ResponseEntity.ok(list);
    }



    /* Funciona
    @PostMapping
    @CrossOrigin(origins = "*", maxAge = 3600) // create
    public ResponseEntity<Mocion> create(@RequestBody TipoMocionRequest req_){


        Tipo_Mocion nueva = new Tipo_Mocion();
        nueva.setId_tipo_Mocion(req_.getId_tipo());
        nueva.setDescripcion(req_.getDescripcion());
        nueva = tipo_MocionRepository.save(nueva);

        Mocion mos_ = new Mocion(req_);
        mos_.setId_tipo_Mocion(nueva);
        mos_.setTexto(req_.getTexto());
        mos_ = mocionRepository.save(mos_);
        return ResponseEntity.ok(mocionRepository.save(mos_));

    }*/

    /*     @PostMapping
    @CrossOrigin(origins = "*", maxAge = 3600) // create
    public ResponseEntity<Mocion> create(@RequestBody Mocion objeto){
        return ResponseEntity.ok(mocionRepository.save(objeto));
        // si se devuelve un return ResponseEntity.ok() sin oarametros de hace ->
        // return ResponseEntity.ok().build();
    }*/

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Mocion> findById(@PathVariable Long id) {
        Optional<Mocion> objeto = mocionRepository.findById(id);
        if (!objeto.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(objeto.get());
    }
    @PostMapping
    @CrossOrigin(origins = "*", maxAge = 3600) // create
    public ResponseEntity<Mocion> create(@RequestBody TipoMocionRequest req_){
        Tipo_Mocion tipo = tipo_MocionRepository.findById(req_.getId_tipo()).get();
        Mocion mos_ = new Mocion(req_);
        if(tipo == null){
            ResponseEntity.badRequest().build();
            return ResponseEntity.ok(null);
        }
        mos_.setId_tipo_Mocion(tipo);
        mos_.setTexto(req_.getTexto());
        mos_ = mocionRepository.save(mos_);
        return ResponseEntity.ok(mos_);
    }
    @PutMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Mocion> update(@RequestBody Mocion mos) {
        Tipo_Mocion tipo = tipo_MocionRepository.findById(mos.getId()).get();
        //Mocion mos_ = new Mocion(req);
        if (!mocionRepository.findById(mos.getId()).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        mos.setId_tipo_Mocion(tipo_MocionRepository.findById(tipo.getId()).get());
        //System.out.println((objeto));
       // mos.setId_tipo_Mocion(tipo);
        return ResponseEntity.ok(mocionRepository.save(mos));
    }

   /* @PutMapping("/{id_mocion} ")///{id_tipo}
    // modifica pero se debe indicar el id de la mocion a la que se desea cambiar y el valor del nuevo id tipo mocion
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Mocion> update(@RequestBody Mocion objeto, @PathVariable Long id_mocion, @PathVariable Long id_tipo){//, @PathVariable Long id_tipo
        if (!mocionRepository.findById(objeto.getId()).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(mocionRepository.save(objeto));
        /*objeto.setId_tipo_Mocion(tipo_MocionRepository.findById(id_tipo).get());
        System.out.println((objeto));
        return ResponseEntity.ok(mocionRepository.save(objeto));
    }*/


    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity delete(@PathVariable Long id) {
        if (!mocionRepository.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        mocionRepository.delete(mocionRepository.findById(id).get());
        return ResponseEntity.ok().build();
    }

}
