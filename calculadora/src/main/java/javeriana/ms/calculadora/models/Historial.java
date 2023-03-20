package javeriana.ms.calculadora.models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "historia")
public class Historial implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public String usuario;
    public String operacion;
    public float operador1;
    public float operador2;
    public float resultado;
    public Date fecha;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public float getOperador1() {
        return operador1;
    }

    public void setOperador1(float operador1) {
        this.operador1 = operador1;
    }

    public float getOperador2() {
        return operador2;
    }

    public void setOperador2(float operador2) {
        this.operador2 = operador2;
    }

    public float getResultado() {
        return resultado;
    }

    public void setResultado(float resultado) {
        this.resultado = resultado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}