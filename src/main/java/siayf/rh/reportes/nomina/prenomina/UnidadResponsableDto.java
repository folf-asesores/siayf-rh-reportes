/*
 * UnidadResponsableDto.java
 * Creado el 27/jun/2017 3:16:41 PM
 *
 */

package siayf.rh.reportes.nomina.prenomina;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class UnidadResponsableDto implements Iterable<NominaEmpleadoDto> {

    private final String numeroUnidadResponsable;
    private final String unidadResponsable;
    private final Map<String, NominaEmpleadoDto> nominasEmpleados;

    public UnidadResponsableDto(String numeroUnidadResponsable, String unidadResponsable, Map<String, NominaEmpleadoDto> nominasEmpleados) {
        this.unidadResponsable = unidadResponsable;
        this.numeroUnidadResponsable = numeroUnidadResponsable;
        this.nominasEmpleados = nominasEmpleados;
    }

    public String getUnidadResponsable() {
        return unidadResponsable;
    }

    public String getNumeroUnidadResponsable() {
        return numeroUnidadResponsable;
    }

    public Map<String, NominaEmpleadoDto> getNominasEmpleados() {
        return nominasEmpleados;
    }

    @Override
    public Iterator<NominaEmpleadoDto> iterator() {
        return nominasEmpleados.values().iterator();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.unidadResponsable);
        hash = 47 * hash + Objects.hashCode(this.numeroUnidadResponsable);
        hash = 47 * hash + Objects.hashCode(this.nominasEmpleados);
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
        final UnidadResponsableDto other = (UnidadResponsableDto) obj;
        if (!Objects.equals(this.unidadResponsable, other.unidadResponsable)) {
            return false;
        }
        if (!Objects.equals(this.numeroUnidadResponsable, other.numeroUnidadResponsable)) {
            return false;
        }
        return Objects.equals(this.nominasEmpleados, other.nominasEmpleados);
    }

    @Override
    public String toString() {
        return "UnidadResponsableDTO{"
                + "numeroUnidadResponsable : " + numeroUnidadResponsable
                + ", unidadResponsable : " + unidadResponsable
                + ", nominasEmpleados : " + nominasEmpleados
                + '}';
    }

    public static final class Builder {

        private String unidadResponsable;
        private String numeroUnidadResponsable;
        private Map<String, NominaEmpleadoDto> nominasEmpleados;

        public Builder(String numeroUnidadResponsable, String unidadResponsable) {
            this.unidadResponsable = unidadResponsable;
            this.numeroUnidadResponsable = numeroUnidadResponsable;
            nominasEmpleados = null;
        }

        public Builder setUnidadResponsable(String unidadResponsable) {
            this.unidadResponsable = unidadResponsable;
            return this;
        }

        public Builder setNumeroUnidadResponsable(String numeroUnidadResponsable) {
            this.numeroUnidadResponsable = numeroUnidadResponsable;
            return this;
        }

        public Builder setNominasEmpleados(Map<String, NominaEmpleadoDto> nominasEmpleados) {
            this.nominasEmpleados = nominasEmpleados;
            return this;
        }

        public UnidadResponsableDto createUnidadResponsableDTO() {
            return new UnidadResponsableDto(numeroUnidadResponsable, unidadResponsable, nominasEmpleados);
        }

    }

}
