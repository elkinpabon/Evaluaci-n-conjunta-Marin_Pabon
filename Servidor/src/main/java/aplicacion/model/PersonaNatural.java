package aplicacion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_personasNaturales") 
@Getter
@Setter
@ToString(callSuper = true)
public class PersonaNatural extends Cliente {

    private int edad;
    private double ingresoMensual;

    @Override
    public double getIngresoReferencial() {
        return ingresoMensual;
    }

    @Override
    public boolean esAptoParaCredito() {
        return getPuntajeCrediticio() > 650;
    }
}
