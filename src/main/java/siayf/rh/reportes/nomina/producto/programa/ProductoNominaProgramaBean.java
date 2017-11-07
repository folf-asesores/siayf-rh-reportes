/*
 * ProductoNominaProgramaBean.java
 * Creado el 01/nov/2017 6:42:00 AM
 * 
 */

package siayf.rh.reportes.nomina.producto.programa;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import siayf.rh.reportes.persistencia.consulta.ProductoNominaProgramaQuery;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class ProductoNominaProgramaBean implements ProductoNominaPrograma {

    private static final long serialVersionUID = 1680573382901775168L;
    
    @Inject
    private ProductoNominaProgramaQuery productoNominaQuery;

    @Override
    public byte[] generarReporte(Object... parametros) {
        Integer idProductoNomina = (Integer) parametros[0];

        ProductoNominaProgramaExcel productoNominaProgramaExcel = new ProductoNominaProgramaExcel();
        List<ProductoNominaProgramaDto> detalles = productoNominaQuery.obtenerProductoNominaProgramasPorIdProducto(idProductoNomina);
        List<String> nombresProgramas = productoNominaQuery.obtenerNombreProgramasPorIdProducto(idProductoNomina);
        Date fe = productoNominaQuery.obtenerFinPeridoPorIdProducto(idProductoNomina);
        
        return productoNominaProgramaExcel.generar(detalles, nombresProgramas, fe);
    }

}
