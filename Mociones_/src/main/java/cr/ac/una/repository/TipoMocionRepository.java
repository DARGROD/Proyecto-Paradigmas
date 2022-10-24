package cr.ac.una.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cr.ac.una.entity.Tipo_Mocion;

@Repository
public interface TipoMocionRepository extends CrudRepository<Tipo_Mocion, Long> {
}
