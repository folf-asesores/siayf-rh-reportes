/*
 * ExcelGenerador.java
 * Creado el 23/sep/2016 7:21:13 PM
 *
 */

package siayf.rh.reportes.core.excel;

import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import siayf.rh.reportes.api.Generador;
import siayf.rh.reportes.empleado.detalle.DetalleEmpleado;
import siayf.rh.reportes.empleado.movimiento.cl.ComisionadoLicencia;
import siayf.rh.reportes.util.FechaUtil;
import siayf.rh.reportes.util.PlantillaMensaje;

import static siayf.rh.reportes.util.FechaUtil.PATRON_FECHA_BASE_DE_DATOS;
import static siayf.rh.reportes.util.BeanInjectUtil.getBean;

/**
 *
 * @author Freddy Barrera
 * @author Eduardo Chuc Mex
 */
public class ExcelGenerador implements Generador{

    private static final long serialVersionUID = -5384835789936086358L;

    private static final Logger LOGGER = Logger.getLogger(ExcelGenerador.class.getName());

    @Override
    public byte[] obtenerReporte(Map<String, String> parametros) {
        AlmacenReportesExcel almacenReportesExcel = new AlmacenReportesExcel();
        String nombreReporte = parametros.get("REPORTE_NOMBRE");
        byte[] bytes = null;

        if (almacenReportesExcel.extisteReporte(nombreReporte)) {
            switch (nombreReporte) {
                // Reporte de empleados
                case "detalle_empleado": {
                    Integer idTipoContratacion = Integer.parseInt(parametros.get("ID_TIPO_CONTRATACION"));
                    DetalleEmpleado detalleEmpleadoBean = getBean(DetalleEmpleado.class);
                    bytes = detalleEmpleadoBean.generarReporte(idTipoContratacion);
                }
                break;

                case "comisionado_licencia": {
                    Date fechaInicial = FechaUtil.comoDate(parametros.get("FECHA_INICIAL"), PATRON_FECHA_BASE_DE_DATOS);
                    Date fechaFinal = FechaUtil.comoDate(parametros.get("FECHA_FINAL"), PATRON_FECHA_BASE_DE_DATOS);
                    ComisionadoLicencia comisionadoLicenciaBean = getBean(ComisionadoLicencia.class);
                    bytes = comisionadoLicenciaBean.generarReporte(fechaInicial, fechaFinal);
                }
                
                case "concentrado_alta_baja": {
                    Integer idTipoContratacionConcentrado = Integer.parseInt(parametros.get("ID_TIPO_CONTRATACION"));
                    Date fechaInicial = FechaUtil.comoDate(parametros.get("FECHA_INICIAL"), PATRON_FECHA_BASE_DE_DATOS);
                    Date fechaFinal = FechaUtil.comoDate(parametros.get("FECHA_FINAL"), PATRON_FECHA_BASE_DE_DATOS);
                /*

                    List<ConsentradoAltaBajaExcelDTO> consentradoAltaBajaExcelDTOs = getMovimientoEmpleadoReporteService()
                            .listaConsultaConsentradoAltaBajaPorRangoFecha(idTipoContratacionConsentrado, fechaInicial,
                                    fechaFinal);

                    if (!consentradoAltaBajaExcelDTOs.isEmpty()) {
                        ConsentradoAltaBajaExcel consentradoAltaBajaExcel = new ConsentradoAltaBajaExcel();

                        bytes = consentradoAltaBajaExcel.generar(consentradoAltaBajaExcelDTOs);
                    } else {
                        throw new ReglaNegocioException("No se encontrarón resultados, intentelo de nuevo.",
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }
                */
                }
            }
        }
        
        if (bytes ==  null) {
            LOGGER.log(Level.WARNING, PlantillaMensaje.REPORTE_ERROR_REGRESA_NULL, nombreReporte);
            bytes = ReporteVacio.obtenerBytes();
        }

        return bytes;
    }

}
