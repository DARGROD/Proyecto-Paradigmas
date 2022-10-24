package cr.ac.una.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Tipo_Mocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String id_tipo_Mocion;

    private String descripcion;



}
