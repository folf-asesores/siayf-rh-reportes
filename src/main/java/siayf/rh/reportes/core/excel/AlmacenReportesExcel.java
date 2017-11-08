/*
 * AlmacenReportesExcel.java
 * Creado el 23/sep/2016 7:17:59 PM
 * 
 */

package siayf.rh.reportes.core.excel;

import java.util.HashMap;
import java.util.Map;

import siayf.rh.reportes.api.AlmacenReportes;
import siayf.rh.reportes.api.Reporte;
import siayf.rh.reportes.empleado.detalle.DetalleEmpleadoExcel;
import siayf.rh.reportes.empleado.movimiento.concentrado.ConcentradoAltaBajaExcel;
import siayf.rh.reportes.nomina.pg.PagoGeneralExcel;
import siayf.rh.reportes.nomina.producto.programa.ProductoNominaProgramaExcel;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class AlmacenReportesExcel implements AlmacenReportes<Reporte> {

    private static final Map<String, Reporte> REPORTES;

    static {
        REPORTES = new HashMap<>();
        
        // Reportes de empleado ===============================================
        DetalleEmpleadoExcel detalleEmpleado = new DetalleEmpleadoExcel();
        REPORTES.put("detalle_empleado", detalleEmpleado);
        
        ConcentradoAltaBajaExcel concentradoAltaBaja = new ConcentradoAltaBajaExcel();
        REPORTES.put("concentrado_alta_baja", concentradoAltaBaja);

        // Reportes de nómina =================================================
        ProductoNominaProgramaExcel productoNominaPrograma = new ProductoNominaProgramaExcel();
        REPORTES.put("producto_nomina_programas", productoNominaPrograma);
        
        PagoGeneralExcel pagoGeneralExcel = new PagoGeneralExcel();
        REPORTES.put("pago_general", pagoGeneralExcel);


    }

    @Override
    public Reporte obtenerReporte(String nombreReporte) {
        return REPORTES.get(nombreReporte);
    }

    @Override
	public boolean extisteReporte(String nombreReporte) {
        return REPORTES.containsKey(nombreReporte);
    }

}
