/*
 * ComisionadoLicenciaBean.java
 * Creado el 27/oct/2017 8:33:46 PM
 * 
 */

package siayf.rh.reportes.empleado.movimiento.cl;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import siayf.rh.reportes.persistencia.consulta.ComisionadoLicenciaQuery;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ComisionadoLicenciaBean implements ComisionadoLicencia {

    private static final long serialVersionUID = -3402267732093491073L;
    
    @Inject
    private ComisionadoLicenciaQuery ComisionadoLicenciaQuery;

    @Override
    public byte[] generarReporte(Object ... parametros) {
        Date fechaInicial = (Date) parametros[0];
        Date fechaFinal = (Date) parametros[1];
        
        List<ComisionadoLicenciaDto> empleadosComisionadosLicencia = ComisionadoLicenciaQuery.obtenerEmpleadosComisionadosLicenciaPorRangoFecha(fechaInicial, fechaFinal);
        ComisionadoLicenciaExcel comisionadoLicenciaExcel = new ComisionadoLicenciaExcel();
        
        return comisionadoLicenciaExcel.generar(empleadosComisionadosLicencia);
    }

}
