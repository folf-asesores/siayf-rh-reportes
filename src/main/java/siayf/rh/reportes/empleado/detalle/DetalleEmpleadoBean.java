/*
 * 
 */

package siayf.rh.reportes.empleado.detalle;

import siayf.rh.reportes.persistencia.consulta.DetalleEmpleadoQuery;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Eduardo Chuc Mex
 */
@Stateless
public class DetalleEmpleadoBean implements DetalleEmpleado {

    private static final long serialVersionUID = 8741572482572801041L;

    @Inject
    private DetalleEmpleadoQuery detalleEmpleadoQuery;

    @Override
    public byte[] generarReporte(Object... parametros) {
        Integer idTipoContratacion = (Integer) parametros[0];
        List<DetalleEmpleadoDto> detalleEmpleado = detalleEmpleadoQuery.detalleEmpleadoPorIdTipoContratacion(idTipoContratacion);
        DetalleEmpleadoExcel detalleEmpleadoExcel = new DetalleEmpleadoExcel();

        return detalleEmpleadoExcel.generar(detalleEmpleado);
    }

}
