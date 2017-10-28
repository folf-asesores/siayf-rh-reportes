/*
 * ComisionadoLicenciaDto.java
 * Creado el 27/oct/2017 8:46:00 PM
 * 
 */

package siayf.rh.reportes.empleado.movimiento.cl;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class ComisionadoLicenciaDto implements Serializable {

    private static final long serialVersionUID = -7492412549178851521L;

    private final Integer tipoMovimiento;
    private final String apellidoPaterno;
    private final String apellidoMaterno;
    private final String nombreEmpleado;
    private final String tipoPlaza;
    private final String numeroHoras;
    private final String funcionesEspecificas;
    private final String clavePago;
    private final Date fechaInicio;
    private final Date fechaConclusion;
    private final String centroTrabajoOrigen;
    private final String centroTrabajoDestino;

    public ComisionadoLicenciaDto(Integer tipoMovimiento, String apellidoPaterno, String apellidoMaterno, String nombreEmpleado, String tipoPlaza, String numeroHoras, String funcionesEspecificas, String clavePago, Date fechaInicio, Date fechaConclusion, String centroTrabajoOrigen, String centroTrabajoDestino) {
        this.tipoMovimiento = tipoMovimiento;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombreEmpleado = nombreEmpleado;
        this.tipoPlaza = tipoPlaza;
        this.numeroHoras = numeroHoras;
        this.funcionesEspecificas = funcionesEspecificas;
        this.clavePago = clavePago;
        this.fechaInicio = fechaInicio;
        this.fechaConclusion = fechaConclusion;
        this.centroTrabajoOrigen = centroTrabajoOrigen;
        this.centroTrabajoDestino = centroTrabajoDestino;
    }

    public Integer getTipoMovimiento() {
        return tipoMovimiento;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public String getTipoPlaza() {
        return tipoPlaza;
    }

    public String getNumeroHoras() {
        return numeroHoras;
    }

    public String getFuncionesEspecificas() {
        return funcionesEspecificas;
    }

    public String getClavePago() {
        return clavePago;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaConclusion() {
        return fechaConclusion;
    }

    public String getCentroTrabajoOrigen() {
        return centroTrabajoOrigen;
    }

    public String getCentroTrabajoDestino() {
        return centroTrabajoDestino;
    }

    public static final class Builder {

        private Integer tipoMovimiento;
        private String apellidoPaterno;
        private String apellidoMaterno;
        private String nombreEmpleado;
        private String tipoPlaza;
        private String numeroHoras;
        private String funcionesEspecificas;
        private String clavePago;
        private Date fechaInicio;
        private Date fechaConclusion;
        private String centroTrabajoOrigen;
        private String centroTrabajoDestino;

        public Builder() {
        }

        public Builder setTipoMovimiento(Integer tipoMovimiento) {
            this.tipoMovimiento = tipoMovimiento;
            return this;
        }

        public Builder setApellidoPaterno(String apellidoPaterno) {
            this.apellidoPaterno = apellidoPaterno;
            return this;
        }

        public Builder setApellidoMaterno(String apellidoMaterno) {
            this.apellidoMaterno = apellidoMaterno;
            return this;
        }

        public Builder setNombreEmpleado(String nombreEmpleado) {
            this.nombreEmpleado = nombreEmpleado;
            return this;
        }

        public Builder setTipoPlaza(String tipoPlaza) {
            this.tipoPlaza = tipoPlaza;
            return this;
        }

        public Builder setNumeroHoras(String numeroHoras) {
            this.numeroHoras = numeroHoras;
            return this;
        }

        public Builder setFuncionesEspecificas(String funcionesEspecificas) {
            this.funcionesEspecificas = funcionesEspecificas;
            return this;
        }

        public Builder setClavePago(String clavePago) {
            this.clavePago = clavePago;
            return this;
        }

        public Builder setFechaInicio(Date fechaInicio) {
            this.fechaInicio = fechaInicio;
            return this;
        }

        public Builder setFechaConclusion(Date fechaConclusion) {
            this.fechaConclusion = fechaConclusion;
            return this;
        }

        public Builder setCentroTrabajoOrigen(String centroTrabajoOrigen) {
            this.centroTrabajoOrigen = centroTrabajoOrigen;
            return this;
        }

        public Builder setCentroTrabajoDestino(String centroTrabajoDestino) {
            this.centroTrabajoDestino = centroTrabajoDestino;
            return this;
        }
        
        public ComisionadoLicenciaDto construir() {
            return new ComisionadoLicenciaDto(tipoMovimiento, apellidoPaterno, apellidoMaterno, nombreEmpleado, tipoPlaza, numeroHoras, funcionesEspecificas, clavePago, fechaInicio, fechaConclusion, centroTrabajoOrigen, centroTrabajoDestino);
        }

    }

}
