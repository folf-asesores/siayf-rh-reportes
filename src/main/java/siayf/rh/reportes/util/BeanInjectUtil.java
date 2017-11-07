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

    // JNDI para los reportes de empleados. ===================================
    private static final String DETALLE_EMPLEADO_BEAN = "java:module/DetalleEmpleadoBean";
    private static final String COMISIONADO_LICENCIA_BEAN = "java:module/ComisionadoLicenciaBean";
    // JNDI para los reportes de nómina. ======================================
    private static final String PRODUCTO_NOMINA_PROGRAMA_BEAN = "java:module/ProductoNominaProgramaBean";
    private static final String PAGO_GENERAL_BEAN = "java:module/PagoGeneralBean";
    
    private BeanInjectUtil() {
    }

    
    public static <T> T getBean(Class<T> clase) {
        String bean = "";
        try {
            switch (clase.getName()) {
                // Bean para la administración de la bitacora. ================
                case "siayf.rh.reportes.core.BitacoraReporte":
                    bean = BITACORA_REPORTES_BEAN;
                    break;
                // Beans para los reportes de empleados. ======================
                case "siayf.rh.reportes.empleado.detalle.DetalleEmpleado":
                    bean = DETALLE_EMPLEADO_BEAN;
                    break;
                case "siayf.rh.reportes.empleado.movimiento.cl.ComisionadoLicencia":
                    bean = COMISIONADO_LICENCIA_BEAN;
                    break;
                // Beans para los reportes de nómina. =========================
                case "siayf.rh.reportes.nomina.producto.programa.ProductoNominaPrograma":
                    bean = PRODUCTO_NOMINA_PROGRAMA_BEAN;
                    break;
                case "siayf.rh.reportes.nomina.pg.PagoGeneral":
                    bean = PAGO_GENERAL_BEAN;
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
