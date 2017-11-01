/*
 * Archivo.java
 * Creado el 01/nov/2017 6:51:14 AM
 * 
 */

package siayf.rh.reportes.api;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * Esta clase representa un reporte ya generado. Esta clase es inmutable.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class Archivo implements Serializable {
    
    private static final long serialVersionUID = -8130724848149345878L;
    
    private final String nombre;
    private final String mediaType;
    private final byte [] bytes;

    public Archivo(String nombre, String mediaType, byte[] bytes) {
        this.nombre = nombre;
        this.mediaType = mediaType;
        this.bytes = bytes;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMediaType() {
        return mediaType;
    }

    public byte[] getBytes() {
        return bytes;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(nombre);
        hash = 31 * hash + Objects.hashCode(mediaType);
        hash = 31 * hash + Arrays.hashCode(bytes);
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
        final Archivo other = (Archivo) obj;
        if (!Objects.equals(nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(mediaType, other.mediaType)) {
            return false;
        }
        return Arrays.equals(bytes, other.bytes);
    }

    @Override
    public String toString() {
        return "Archivo{" 
                + "nombre : " + nombre 
                + ", mediaType : " + mediaType 
                + ", bytes : [" + Arrays.toString(bytes) 
                + "]}";
    }

    
}
