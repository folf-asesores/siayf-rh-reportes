/*
 * Reporte.java
 * Creado el 9/sep/2016 1:37:04 PM
 * 
 */

package siayf.rh.reportes.api;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase define a los reportes.
 * 
 * <p>Su función principal es ayudar a cargar la plantilla del reporte.</p>
 * 
 * @author Freddy Barrera
 * @author Eduardo Chuc Mex
 */
public class Reporte {

    private final String nombreArchivo;
    private final String ruta;

    private static final Logger LOGGER = Logger.getLogger(Reporte.class.getName());

    /**
     * Crea una nueva instancia de un reporte.
     * 
     * @param nombreArchivo el nombre del reporte o plantilla.
     * @param ruta la ruta en la que se ubica el reporte o plantilla.
     */
    public Reporte(String ruta, String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
    }

    /**
     * Permite obtener el reporte o la plantilla como un {@link InputStream}.
     * 
     * @return si la ruta es correcta un <code>InputStream</code> con el reporte
     * o plantilla en caso contrario <code>null</code>.
     */
    public InputStream getInputStream() {
        LOGGER.log(Level.FINE,"Cargando archivo: {0}", nombreArchivo);
        InputStream is = getClass().getClassLoader().getResourceAsStream(ruta + nombreArchivo);

        if (is == null) {
            LOGGER.log(Level.SEVERE, "El archivo \"{0}\" no se encontro.", nombreArchivo);
        }

        LOGGER.log(Level.FINE, "El archivo \"{0}\" se ha cargado correctamente.", nombreArchivo);

        return is;
    }

}
