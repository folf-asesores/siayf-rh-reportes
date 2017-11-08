/*
 * FirmaBean.java
 * Creado el 11/sep/2017 11:05:31 AM
 * 
 */

package siayf.rh.reportes.nomina.firma;

import javax.ejb.Stateless;
import javax.inject.Inject;

import siayf.rh.reportes.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class FirmaBean implements Firma {

    private static final long serialVersionUID = -7969962371656776453L;
    
    @Inject
    private FirmaService firmaService; 

    @Override
    public byte[] generarReporte(Object ... parametros) {
        Integer idProductoNomina = (Integer) parametros[0];

        if (ValidacionUtil.esNumeroNegativo(idProductoNomina)) {
            throw new IllegalArgumentException("El ID del producto no debe ser nulo o menor que uno.");
        }
        
        FirmaDto firmasEmpleados = firmaService.obtenerFirmaEmpleado(idProductoNomina);
        FirmaMotor firmaMotor = new FirmaMotor();
        byte[] reporte = firmaMotor.obtenerArchivo(firmasEmpleados);

        return reporte;
    }
}
