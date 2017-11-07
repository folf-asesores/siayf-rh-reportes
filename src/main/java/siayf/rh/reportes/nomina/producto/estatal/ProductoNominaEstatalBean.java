/*
 * ProductoNominaEstatalBean.java
 * Creado el 01/nov/2017 6:42:00 AM
 * 
 */

package siayf.rh.reportes.nomina.producto.estatal;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import siayf.rh.reportes.persistencia.consulta.ProductoNominaQuery;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class ProductoNominaEstatalBean implements ProductoNominaEstatal {

    private static final long serialVersionUID = 1680573382901775168L;
    
    @Inject
    private ProductoNominaQuery productoNominaQuery;

    @Override
    public byte[] generarReporte(Object... parametros) {
        Boolean esPrograma = (Boolean) parametros[0];
        Integer idProductoNomina = (Integer) parametros[1];
        
        if (!esPrograma) {
            return generarReporteProductoNomina(idProductoNomina);
        } else {
            return generarReporteProductoNominaPrograma(idProductoNomina);
        }
    }
    
    private byte[] generarReporteProductoNomina(Integer idProductoNomina) {
        return null;
    }
    
    private byte[] generarReporteProductoNominaPrograma(Integer idProductoNomina) {
        ProductoNominaEstatalExcel productoNominaProgramaExcel = new ProductoNominaEstatalExcel();
        List<ProductoNominaEstatalDto> detalles = productoNominaQuery.obtenerProductoNominaProgramasPorIdProducto(idProductoNomina);
        List<String> nombresProgramas = productoNominaQuery.obtenerNombreProgramasPorIdProducto(idProductoNomina);
        Date fe = productoNominaQuery.obtenerFinPeridoPorIdProducto(idProductoNomina);
        
        return productoNominaProgramaExcel.generar(detalles, nombresProgramas, fe);
    }

}
