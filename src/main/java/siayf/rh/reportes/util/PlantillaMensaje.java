/*
 * PlantillaMensaje.java
 * Creado el 16/mar/2017 11:58:39 AM
 * 
 */

package siayf.rh.reportes.util;

import java.text.MessageFormat;
import java.util.logging.Logger;

/**
 * Esta clase contiene las plantillas de mensajes que principalmente se emplean
 * para enviar al log.
 * 
 * @see MessageFormat
 * @see Logger#log(java.util.logging.Level, java.lang.String, java.lang.Object[])
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class PlantillaMensaje {
    
    public static final String SQL_ERROR_MESSAGE =
        "\n\tSQLException: {0}\n\tSQLState: {1}\n\tVendorError: {2}";
    public static String REPORTE_ERROR_BEAN_NO_ENCONTRADO = 
            "Error al buscar el bean: {0}\t{1}";
    public static String REPORTE_ERROR_REGRESA_NULL = 
            "El reporte {0} no contiene datos, se devolver un reporte vacio.";

    private PlantillaMensaje () {
    }
    
}
