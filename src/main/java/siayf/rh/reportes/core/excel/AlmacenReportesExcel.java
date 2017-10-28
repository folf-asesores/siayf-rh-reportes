/*
 * AlmacenReportesExcel.java
 * Creado el 23/sep/2016 7:17:59 PM
 * 
 */

package siayf.rh.reportes.core.excel;

import java.util.HashMap;
import java.util.Map;

import siayf.rh.reportes.api.AlmacenReportes;
import siayf.rh.reportes.empleado.detalle.DetalleEmpleadoExcel;
import siayf.rh.reportes.empleado.movimiento.concentrado.ConcentradoAltaBajaExcel;

/**
 *
 * @author Freddy Barrera
 */
public class AlmacenReportesExcel implements AlmacenReportes<ExcelPlantillaReporte> {

    private static final Map<String, ExcelPlantillaReporte> REPORTES;

    static {
        DetalleEmpleadoExcel detalleEmpleado = new DetalleEmpleadoExcel();
        ConcentradoAltaBajaExcel concentradoAltaBaja = new ConcentradoAltaBajaExcel();

/*
        ExcelPlantillaReporte acumulados = new ExcelPlantillaReporte(
                "acumulados--plantilla.xlsx", "plantillas/acumulados/");

        ExcelPlantillaReporte comisionadoLicencia = new ExcelPlantillaReporte(
                "Comisionado_Licencia.xlsx", "plantillas/comisionadoLicencia/");


        ExcelPlantillaReporte seguroPopular = new ExcelPlantillaReporte(
                "plantilla--seguro-popular.xlsx", "plantillas/siif/");

        ExcelPlantillaReporte seguroPopularReporte = new ExcelPlantillaReporte(
                "plantilla--seguro-popular.xlsx", "plantillas/siif/");

        ExcelPlantillaReporte proyeccionesPresupuestalesContratoPorMes 
                = new ExcelPlantillaReporte("Contrato_Estatal_Federal.xlsx",
                        "plantillas/contrato/");

        ExcelPlantillaReporte proyeccionesPresupuestalesContrato = new ExcelPlantillaReporte(
                "Contrato_Estatal_Federal.xlsx", "plantillas/contrato/");

        ExcelPlantillaReporte productoNominaReporte = new ExcelPlantillaReporte(
                "Producto_Nomina.xlsx", "plantillas/nommina/");
        
        ExcelPlantillaReporte prooductoNominaEstatusReporte = new ExcelPlantillaReporte(
        		"Producto_Nomina.xlsx", "plantillas/nommina/");

        ExcelPlantillaReporte productoNominaSuplenciaReporte = new ExcelPlantillaReporte(
                "Producto_Nomina.xlsx", "plantillas/nommina/");

        ExcelPlantillaReporte historialPagoReporte = new ExcelPlantillaReporte(
                "Historial_Pago.xlsx", "plantillas/nommina/");

        ExcelPlantillaReporte relacionPersonalSuplenteReporte = new ExcelPlantillaReporte(
                "Relacion_Personal_Suplente.xlsx", "plantillas/suplencia/");
        
        ExcelPlantillaReporte productoNominaFederalProgramasReporte = new ExcelPlantillaReporte(
                "Producto_Nomina_Programas.xlsx", "plantillas/nommina/");
        ExcelPlantillaReporte productoNominaProgramasReporte = new ExcelPlantillaReporte(
                "Producto_Nomina_Programas.xlsx", "plantillas/nommina/");

        ExcelPlantillaReporte dispercionReporte = new ExcelPlantillaReporte(null, null); // 1.
        ExcelPlantillaReporte pagoGeneralReporte = new ExcelPlantillaReporte(null, null); // 1.
        ExcelPlantillaReporte distribucionPresupuestal = new ExcelPlantillaReporte(null, null); // 1.
        ExcelPlantillaReporte productoNominaFederalReporteReporte = new ExcelPlantillaReporte(null, null); // 1.
*/

        REPORTES = new HashMap<>();
        REPORTES.put("detalle_empleado", detalleEmpleado);
        REPORTES.put("concentrado_alta_baja", concentradoAltaBaja);

/*
        REPORTES.put("acumulados", acumulados);
        REPORTES.put("comisionado_licencia", comisionadoLicencia);
        REPORTES.put("seguro_popular", seguroPopular);
        REPORTES.put("seguro_popular_reporte", seguroPopularReporte);
        REPORTES.put("contrato_estatal_federal", proyeccionesPresupuestalesContratoPorMes);
        REPORTES.put("contrato_estatal_federal_proyeccion", proyeccionesPresupuestalesContrato);
        REPORTES.put("producto_nomina", productoNominaReporte);
        REPORTES.put("producto_nomina_suplencia", productoNominaSuplenciaReporte);
        REPORTES.put("producto_nomina_estatus",prooductoNominaEstatusReporte);
        REPORTES.put("historial_pago", historialPagoReporte);
        REPORTES.put("relacion_personal_suplente", relacionPersonalSuplenteReporte);
        REPORTES.put("dispersion_nomina", dispercionReporte);
        REPORTES.put("pago_general", pagoGeneralReporte);
        REPORTES.put("reporte_distribucion_presupuestal", distribucionPresupuestal);
        REPORTES.put("producto_nomina_federales", productoNominaFederalReporteReporte);
        REPORTES.put("producto_nomina_programas", productoNominaProgramasReporte);
*/
    }

    @Override
    public ExcelPlantillaReporte obtenerReporte(String nombreReporte) {
        return REPORTES.get(nombreReporte);
    }

    @Override
	public boolean extisteReporte(String nombreReporte) {
        return REPORTES.containsKey(nombreReporte);
    }

}
