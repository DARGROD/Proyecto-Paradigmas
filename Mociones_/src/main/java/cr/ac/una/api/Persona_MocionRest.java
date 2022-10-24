package cr.ac.una.api;

import cr.ac.una.entity.Mocion;
import cr.ac.una.entity.Persona;
import cr.ac.una.entity.Persona_Mocion;
import cr.ac.una.entity.Tipo_Mocion;
import cr.ac.una.repository.MocionRepository;
import cr.ac.una.repository.PersonaMocionRepository;
import cr.ac.una.repository.PersonaRepository;
import cr.ac.una.request.Persona_MocionRequest;
import cr.ac.una.request.TipoMocionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/mociones_persona")
public class Persona_MocionRest {

    @Autowired
    private PersonaMocionRepository persona_nocionRepository;

    @Autowired
    private MocionRepository mocionRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<List<Persona_Mocion>> findAll(){
        List<Persona_Mocion> list = new ArrayList<Persona_Mocion>();
        persona_nocionRepository.findAll().forEach(e->list.add(e));
        return ResponseEntity.ok(list);
    }


    @PostMapping
    @CrossOrigin(origins = "*", maxAge = 3600) // create
    public ResponseEntity<Persona_Mocion> create(@RequestBody Persona_MocionRequest req_){
        Mocion mosion = mocionRepository.findById(req_.getId_mocion()).get();
        Persona persona = personaRepository.findById(req_.getId_persona()).get();
        Persona_Mocion objeto = new Persona_Mocion(req_);
        if(mosion != null && persona != null){
            objeto.setPersona(persona);
            objeto.setMocion(mosion);
            if (contarPersonasporMocion(objeto)>=3) {
                ResponseEntity.badRequest().build();
                return ResponseEntity.ok(null);
            }
            System.out.println("mociones al dia por persona:");
            System.out.println(MosionesXdia(objeto));
            if(MosionesXdia(objeto)>=5){
                ResponseEntity.badRequest().build();
                return ResponseEntity.ok(null);
            }

            objeto = persona_nocionRepository.save(objeto);
        }
        return ResponseEntity.ok(objeto);
    }


    /*@CrossOrigin(origins = "*", maxAge = 3600) // create
    public ResponseEntity<Persona_Mocion> create(@RequestBody Persona_Mocion objeto){
        return ResponseEntity.ok(persona_nocionRepository.save(objeto));

    }*/

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Persona_Mocion> findById(@PathVariable Long id) {
        Optional<Persona_Mocion> objeto = persona_nocionRepository.findById(id);
        if (!objeto.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(objeto.get());
    }

    @PutMapping("/{id_PersonaMocion}/{id_persona}/{id_mocion}")
    // modifica
    // antes de modificarla, hay que hacerle un attach con el findBy ->
    // el isPresent() valida si la persona existe -> es necesaria esa verificacion
    // si no existe, se devuelve el BAD REQUEST -> ResponseEntity.badRequest().build();
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<Persona_Mocion> update(
            @RequestBody Persona_Mocion objeto, @PathVariable Long id_PersonaMocion
            , @PathVariable Long id_persona  , @PathVariable Long id_mocion) {
        if (!persona_nocionRepository.findById(objeto.getId()).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        objeto.setMocion(mocionRepository.findById(id_mocion).get());
        objeto.setPersona(personaRepository.findById(id_persona).get());

        return ResponseEntity.ok(persona_nocionRepository.save(objeto));
    }


    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity delete(@PathVariable Long id) {
        if (!persona_nocionRepository.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        persona_nocionRepository.delete(persona_nocionRepository.findById(id).get());
        return ResponseEntity.ok().build();
    }


    public int contarPersonasporMocion(Persona_Mocion objeto){
        List<Persona_Mocion> list = new ArrayList<Persona_Mocion>();
        persona_nocionRepository.findAll().forEach(e->list.add(e));

        int personasnocion = 0;
        int len = list.size();

        for (int i = 1; i <= len; i++){
            Persona_Mocion pn = persona_nocionRepository.findById((long) i).get();
            if (pn.getMocion() == objeto.getMocion()) {
                personasnocion ++;
            }
        }
        return personasnocion;
    }

    public int MosionesXdia(Persona_Mocion objeto){
        List<Persona_Mocion> list = new ArrayList<Persona_Mocion>();
        persona_nocionRepository.findAll().forEach(e->list.add(e));

        int personasXdia = 0;
        int len = list.size();

        Date fechaObjeto = objeto.getMocion().getFecha();
        System.out.println(fechaObjeto);

        SimpleDateFormat formatter1 = new SimpleDateFormat("dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("MM");
        SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy");

        String fechaObjetoDia = formatter1.format(fechaObjeto);
        String fechaObjetoMes = formatter2.format(fechaObjeto);
        String fechaObjetoAnno = formatter3.format(fechaObjeto);
        int diaObjeto = Integer.parseInt(fechaObjetoDia);
        int mesObjeto = Integer.parseInt(fechaObjetoMes);
        int annoObjeto = Integer.parseInt(fechaObjetoAnno);

        for (int i = 1; i <= len; i++){
            Persona_Mocion pn = persona_nocionRepository.findById((long) i).get();

            Date fechaActual = pn.getMocion().getFecha();
            System.out.println(fechaActual);

            String fechaPNDia = formatter1.format(fechaObjeto);
            String fechaPNMes = formatter2.format(fechaObjeto);
            String fechaPNAnno = formatter3.format(fechaObjeto);
            int diaPN = Integer.parseInt(fechaPNDia);
            int mesPN = Integer.parseInt(fechaPNMes);
            int annoPN = Integer.parseInt(fechaPNAnno);

            if (annoPN == annoObjeto){
                if (mesPN == mesObjeto){
                    if (diaPN == diaObjeto){
                        if (pn.getPersona() == objeto.getPersona()) {
                            personasXdia ++;
                        }
                    }
                }
            }
        }
        return personasXdia;
    }



}
