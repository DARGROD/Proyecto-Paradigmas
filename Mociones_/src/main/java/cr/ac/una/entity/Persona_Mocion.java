package cr.ac.una.entity;

import cr.ac.una.request.Persona_MocionRequest;
import cr.ac.una.request.TipoMocionRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Persona_Mocion {

    public Persona_Mocion(Persona_MocionRequest req){
        this.id_Persona_Mocion = req.getId_Persona_Mocion();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String id_Persona_Mocion;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "nocion_id")
    private Mocion mocion;

}
