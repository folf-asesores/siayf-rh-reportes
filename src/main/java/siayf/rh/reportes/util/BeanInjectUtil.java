/*
 * BeanInjectUtil.java
 * Creado el 28/oct/2017 7:36:20 AM
 * 
 */

package siayf.rh.reportes.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class BeanInjectUtil {

    private static final Logger LOGGER = Logger.getLogger(BeanInjectUtil.class.getName());
    
    private static final String BITACORA_REPORTES_BEAN = "java:module/BitacoraReporteBean";

    private static final String DETALLE_EMPLEADO_BEAN = "java:module/DetalleEmpleadoBean";
    private static final String COMISIONADO_LICENCIA_BEAN = "java:module/ComisionadoLicenciaBean";
    
    private static final String CONSULTA_NOMINA_SERVICE_BEAN = "java:module/ConsultaNominaService";
    private static final String SEGURO_POPULAR_REPORTE_BEAN = "java:module/SeguroPopularReporteEJB";
    private static final String PROYECCIONES_PRESUPUESTALES_BEAN = "java:module/ProyeccionesPresupuestalesEJB";
    private static final String PRODUCTO_NOMINA_BEAN = "java:module/ProductoNominaEJB";
    private static final String HISTORIAL_PAGO_BEAN = "java:module/HistorialPagoEJB";
    private static final String RELACION_PERSONAL_SUPLENTE_BEAN = "java:module/RelacionPersonalSuplenteEJB";
    private static final String DISPERSION_BEAN = "java:module/DispersionEJB";
    private static final String PAGO_GENERAL_BEAN = "java:module/PagoGeneralReporteEJB";
    private static final String DISTRIBUCION_PRESUPUESTO_BEAN = "java:module/DistribucionPresupuestoEJB";
    private static final String PRODUCTO_NOMINA_PROGRAMAS_BEAN = "java:module/ProductoNominaEJB";
    
    private BeanInjectUtil() {
    }

    
    public static <T> T getBean(Class<T> clase) {
        String bean = "";
        try {
            switch (clase.getName()) {
                // Bean para la administración de la bitacora.
                case "siayf.rh.reportes.core.BitacoraReporte":
                    bean = BITACORA_REPORTES_BEAN;
                    break;
                case "siayf.rh.reportes.empleado.detalle.DetalleEmpleado":
                    bean = DETALLE_EMPLEADO_BEAN;
                    break;
                case "siayf.rh.reportes.empleado.movimiento.cl.ComisionadoLicencia":
                    bean = COMISIONADO_LICENCIA_BEAN;
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
