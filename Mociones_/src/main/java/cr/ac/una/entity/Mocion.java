package cr.ac.una.entity;

import cr.ac.una.request.TipoMocionRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Mocion {

    public Mocion(TipoMocionRequest req){
        this.id_Mocion = req.getId_Mocion();
        this.texto = req.getTexto();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String id_Mocion;
    private String texto;

    //@CreationTimestamp


    @ManyToOne
    @JoinColumn(name = "tipo_Mocion_id")
    private Tipo_Mocion id_tipo_Mocion;


    @CreationTimestamp
    @Column(name="fecha", nullable=false, updatable=false)
    Date fecha;

}
