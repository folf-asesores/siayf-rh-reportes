/*
 * NominaEmpleadoDto.java
 * Creado el 27/jun/2017 3:28:25 PM
 *
 */

package siayf.rh.reportes.nomina.prenomina;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class NominaEmpleadoDto {

    private String rfc;
    private String nombre;
    private Map<String, PercepcionDto> percepciones;
    private Map<String, DeduccionDto> deducciones;

    public Map<String, PercepcionDto> getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(Map<String, PercepcionDto> percepciones) {
        this.percepciones = percepciones;
    }

    public Map<String, DeduccionDto> getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(Map<String, DeduccionDto> deducciones) {
        this.deducciones = deducciones;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.rfc);
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + Objects.hashCode(this.percepciones);
        hash = 29 * hash + Objects.hashCode(this.deducciones);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NominaEmpleadoDto other = (NominaEmpleadoDto) obj;
        if (!Objects.equals(this.rfc, other.rfc)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.percepciones, other.percepciones)) {
            return false;
        }
        return Objects.equals(this.deducciones, other.deducciones);
    }

    @Override
    public String toString() {
        return "NominaEmpleadoDTO{" + "rfc : " + rfc
                + ", nombre : " + nombre
                + ", percepciones : " + percepciones
                + ", deducciones : " + deducciones + '}';
    }

}
