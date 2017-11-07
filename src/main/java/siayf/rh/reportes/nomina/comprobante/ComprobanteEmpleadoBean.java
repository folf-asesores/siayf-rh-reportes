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
    
    @Override
    public byte[] generarReporte(Integer idProductoNomina) {
        if(ValidacionUtil.esNumeroNegativo(idProductoNomina)) {
            throw new IllegalArgumentException("El ID del producto no debe ser nulo o menor que uno");
        }

        return comprobanteEmpleadoService.generarReporte(idProductoNomina);
    }

}
