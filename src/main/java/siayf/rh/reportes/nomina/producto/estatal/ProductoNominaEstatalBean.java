/*
 * ProductoNominaProgramaBean.java
 * Creado el 01/nov/2017 6:42:00 AM
 * 
 */

package siayf.rh.reportes.nomina.producto.estatal;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import siayf.rh.reportes.persistencia.consulta.ProductoNominaEstatalQuery;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
@Transactional(TxType.REQUIRES_NEW)
public class ProductoNominaEstatalBean implements ProductoNominaEstatal {

    private static final long serialVersionUID = 1680573382901775168L;

    @Inject
    private ProductoNominaEstatalQuery productoNominaQuery;

    @Override
    public byte[] generarReporte(Object... parametros) {
        Integer idProductoNomina = (Integer) parametros[0];

        ProductoNominaEstatalExcel productoNominaProgramaExcel = new ProductoNominaEstatalExcel();
        List<ProductoNominaEstatalDto> listaProductoNomina = this.productoNominaQuery.obtenerProductoNominaEstatalPorIdProducto(idProductoNomina);
        return productoNominaProgramaExcel.generar(listaProductoNomina);
    }

}
