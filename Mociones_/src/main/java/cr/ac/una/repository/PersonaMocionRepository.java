package cr.ac.una.repository;

import cr.ac.una.entity.Persona_Mocion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaMocionRepository extends CrudRepository<Persona_Mocion, Long> {
}
