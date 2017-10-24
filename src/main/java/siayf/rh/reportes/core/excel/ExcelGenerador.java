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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import siayf.rh.reportes.api.Generador;
import siayf.rh.reportes.empleado.detalle.DetalleEmpleado;
import siayf.rh.reportes.util.FechaUtil;
import siayf.rh.reportes.util.PlantillaMensaje;

import static siayf.rh.reportes.util.FechaUtil.PATRON_FECHA_BASE_DE_DATOS;

/**
 *
 * @author Freddy Barrera
 * @author Eduardo Chuc Mex
 */
public class ExcelGenerador implements Generador{

    private static final long serialVersionUID = -5384835789936086358L;

    private static final Logger LOGGER = Logger.getLogger(ExcelGenerador.class.getName());

    private static final String DETALLE_EMPLEADO_BEAN = "java:module/DetalleEmpleadoBean";
    
    private static final String CONSULTA_NOMINA_SERVICE_BEAN = "java:module/ConsultaNominaService";
    private static final String CONSULTA_COMISIONADO_LICENCIA_SERVICE_BEAN = "java:module/MovimientoEmpleadoReporteService";
    private static final String SEGURO_POPULAR_REPORTE_BEAN = "java:module/SeguroPopularReporteEJB";
    private static final String PROYECCIONES_PRESUPUESTALES_BEAN = "java:module/ProyeccionesPresupuestalesEJB";
    private static final String PRODUCTO_NOMINA_BEAN = "java:module/ProductoNominaEJB";
    private static final String HISTORIAL_PAGO_BEAN = "java:module/HistorialPagoEJB";
    private static final String RELACION_PERSONAL_SUPLENTE_BEAN = "java:module/RelacionPersonalSuplenteEJB";
    private static final String DISPERSION_BEAN = "java:module/DispersionEJB";
    private static final String PAGO_GENERAL_BEAN = "java:module/PagoGeneralReporteEJB";
    private static final String DISTRIBUCION_PRESUPUESTO_BEAN = "java:module/DistribucionPresupuestoEJB";
    private static final String PRODUCTO_NOMINA_PROGRAMAS_BEAN = "java:module/ProductoNominaEJB";

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

//                    List<ComisionadoLicenciaExcelDTO> comisionadoLicenciaExcelDTOs = getMovimientoEmpleadoReporteService()
//                            .listaConsultaComisionadoLicenciaPorRangoFecha(fechaInicial, fechaFinal);
//
//                    if (!comisionadoLicenciaExcelDTOs.isEmpty()) {
//                        ComisionadoLicenciaExcel comisionadoLicenciaExcel = new ComisionadoLicenciaExcel();
//
//                        bytes = comisionadoLicenciaExcel.generar(comisionadoLicenciaExcelDTOs);
//                    } else {
//                        throw new ReglaNegocioException("No se encontrarón resultados, intentelo de nuevo.",
//                                ReglaNegocioCodigoError.SIN_REGISTRO);
//                    }

                }
            }
        }
        
        if (bytes ==  null) {
            LOGGER.log(Level.WARNING, PlantillaMensaje.REPORTE_ERROR_REGRESA_NULL, nombreReporte);
            bytes = ReporteVacio.obtenerBytes();
        }

        return bytes;
    }

    private <T> T getBean(Class<T> clase) {
        String bean = "";
        try {
            switch (clase.getName()) {
                case "siayf.rh.reportes.empleado.detalle.DetalleEmpleado":
                    bean = DETALLE_EMPLEADO_BEAN;
                    break;
                case "siayf.rh.reportes.empleado.movimientos.MovimientoEmpleadoReporteService":
                    bean = CONSULTA_COMISIONADO_LICENCIA_SERVICE_BEAN;
                    break;
                case "siayf.rh.reportes.empleado.suplencia.relacionpersonal.RelacionPersonalSuplente":
                    bean = RELACION_PERSONAL_SUPLENTE_BEAN;
                    break;
                case "siayf.rh.reportes.nomina.dispersion.Dispersion":
                    bean = DISPERSION_BEAN;
                    break;
                case "siayf.rh.reportes.nomina.historialpago.HistorialPago":
                    bean = HISTORIAL_PAGO_BEAN;
                    break;
                case "siayf.rh.reportes.nomina.pagogeneral.PagoGeneralReporte":
                    bean = PAGO_GENERAL_BEAN;
                    break;
                case "siayf.rh.reportes.nomina.productosnomina.ProductoNomina":
                    bean = PRODUCTO_NOMINA_BEAN;
                    break;
                case "siayf.rh.reportes.presupuesto.DistribucionPresupuestoEJB":
                    bean = DISTRIBUCION_PRESUPUESTO_BEAN;
                    break;
                case "siayf.rh.reportes.presupuesto.ProyeccionesPresupuestalesEJB":
                    bean = PROYECCIONES_PRESUPUESTALES_BEAN;
                    break;
                case "siayf.rh.reportes.siif.ConsultaNominaService":
                    bean = CONSULTA_NOMINA_SERVICE_BEAN;
                    break;
                case "siayf.rh.reportes.siif.seguropopular.SeguroPopularReporte":
                    bean = SEGURO_POPULAR_REPORTE_BEAN;
                    break;
                default:
                    return null;
            }

            Context initContext = new InitialContext();
            return (T) initContext.lookup(bean);
        } catch (NamingException ex) {
            LOGGER.log(Level.SEVERE, PlantillaMensaje.REPORTE_ERROR_BEAN_NO_ENCONTRADO, new Object [] {bean, ex});
            return null;
        }
    }

}
