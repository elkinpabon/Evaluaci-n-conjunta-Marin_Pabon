package aplicacion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_personasJuridicas")
@Getter
@Setter
@ToString(callSuper = true)
public class PersonaJuridica extends Cliente {

    private int antiguedadAnios;
    private double ingresoAnual;
    private int empleados;

    @Override
    public double getIngresoReferencial() {
        return ingresoAnual;
    }

    @Override
    public boolean esAptoParaCredito() {
        return antiguedadAnios >= 3;
    }
}
