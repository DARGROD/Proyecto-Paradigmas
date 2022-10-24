package cr.ac.una.repository;

import cr.ac.una.entity.Mocion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MocionRepository extends CrudRepository<Mocion, Long> {


}
