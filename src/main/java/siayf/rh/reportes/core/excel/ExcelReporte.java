/*
 * ExcelReporte.java
 * Creado el 27/oct/2017 8:18:52 PM
 * 
 */

package siayf.rh.reportes.core.excel;

import java.io.Serializable;
import siayf.rh.reportes.api.Reporte;

/**
 * Esta clase ayuda en la creación de reportes de Excel en los que no se emplea
 * un archivo de Excel como plantilla.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ExcelReporte extends Reporte implements Serializable {

    private static final long serialVersionUID = -6936730041527771980L;

    public ExcelReporte() {
        super(null, null);
    }

}
