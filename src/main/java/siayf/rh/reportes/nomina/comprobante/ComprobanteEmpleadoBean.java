/*
 * ComprobanteEmpleadoBean.java
 * Creado el 22/nov/2016 4:20:15 AM
 * 
 */

package siayf.rh.reportes.nomina.comprobante;

import javax.ejb.Stateless;
import javax.inject.Inject;

import siayf.rh.reportes.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class ComprobanteEmpleadoBean implements ComprobanteEmpleado {

    private static final long serialVersionUID = -7029108286383735953L;

    @Inject private ComprobanteEmpleadoService comprobanteEmpleadoService;
    
    /**
     * Permite obtener el reporte de comprobantes de pago (cheques) en formato
     * de texto plano como un arreglo de bytes.
     *
     * @param parametros ID del producto de nómina que se desea.
     * @return el reporte como un arreglo de bytes.
     */
    @Override
    public byte[] generarReporte(Object [] parametros) {
        Integer idProductoNomina = (Integer) parametros[0];

        if(ValidacionUtil.esNumeroNegativo(idProductoNomina)) {
            throw new IllegalArgumentException("El ID del producto no debe ser nulo o menor que uno");
        }

        return comprobanteEmpleadoService.generarReporte(idProductoNomina);
    }

}
