/*
 * ProductoNominaBean.java
 * Creado el 01/nov/2017 6:42:00 AM
 * 
 */

package siayf.rh.reportes.nomina.producto;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import siayf.rh.reportes.persistencia.consulta.ProductoNominaQuery;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class ProductoNominaBean implements ProductoNomina {

    private static final long serialVersionUID = 1680573382901775168L;
    
    @Inject
    private ProductoNominaQuery productoNominaQuery;

    @Override
    public byte[] generarReporte(Object... parametros) {
        Integer idProductoNomina = (Integer) parametros[0];
        ProductoNominaProgramaExcel productoNominaProgramaExcel = new ProductoNominaProgramaExcel();
        List<ProductoNominaProgramaDto> detalles = productoNominaQuery.obtenerProductoNominaProgramasPorIdProducto(idProductoNomina);
        List<String> nombresProgramas = productoNominaQuery.obtenerNombreProgramasPorIdProducto(idProductoNomina);
        ProductoNominaDto fe = productoNominaQuery.obtenerFinPeridoEjercicioPorIdProducto(idProductoNomina);
        
        return productoNominaProgramaExcel.generar(detalles, nombresProgramas, fe.getFinPeriodo());
    }

}
