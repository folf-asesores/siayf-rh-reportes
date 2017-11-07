/*
 * PagoGeneral.java
 * Creado el 15/feb/2017 5:20:54 AM
 * 
 */
package siayf.rh.reportes.nomina.pagogeneral;

import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface PagoGeneral extends Serializable {

    byte [] generarReporte(Integer idProductoNomina);

}
