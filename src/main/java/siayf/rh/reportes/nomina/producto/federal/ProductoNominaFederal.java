/*
 * ProductoNominaFederal.java
 * Creado el 16/mar/2017 11:10:36 AM
 * 
 */

package siayf.rh.reportes.nomina.producto.federal;

import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface ProductoNominaFederal extends Serializable {
    
    /**
     * Permite obtener el reporte de producto de nómina, como un arreglo de bytes.
     * 
     * @param idProductoNomina el ID del producto de nómina.
     * @return Un arreglo de bytes que representa el un archivo de Excel con el
     * reporte de producto de nóminas de empleados federales.
     */
    byte [] generarReporte(Integer idProductoNomina);    
}
