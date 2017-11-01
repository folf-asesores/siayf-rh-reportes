/*
 * JasperReportsGenerador.java
 * Creado el 9/sep/2016 1:37:04 PM
 *
 */

package siayf.rh.reportes.core.jasperreports;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import siayf.rh.reportes.api.Generador;
import net.sf.jasperreports.engine.JasperReport;
import org.jboss.logging.Logger;
import siayf.rh.reportes.util.FechaUtil;

/**
 * @author Eduardo Mex
 *
 */
public class JasperReportsGenerador implements Generador {

    private static final long serialVersionUID = -1351264360707898777L;
    private static final Logger LOGGER = Logger.getLogger(JasperReportsGenerador.class.getName());

    @Override
    public byte[] obtenerReporte(Map<String, String> parametros) {
        AlmacenReportesJasperReports almacen = new AlmacenReportesJasperReports();

        String nombreReporte = parametros.get("REPORTE_NOMBRE");
        JasperReporte reporte = almacen.obtenerReporte(nombreReporte);
        Map<String, Object> parametrosReporte = new HashMap<>();
        JasperReportsCompilador compilador = new JasperReportsCompilador();

        if (reporte.tieneSubreportes()) {
            Map<String, JasperReporte> subreportes = reporte.getSubreportes();

            for (Map.Entry<String, JasperReporte> entry : subreportes.entrySet()) {
                String clave = entry.getKey();
                JasperReporte subreporte = entry.getValue();
                JasperReport subjr = compilador.compilar(subreporte.getInputStream());

                parametrosReporte.put(clave, subjr);
            }
        }

        Map<String, Class<?>> parametrosTipos = reporte.getParametrosTipos();

        for (Map.Entry<String, Class<?>> entry : parametrosTipos.entrySet()) {
            String parametroClave = entry.getKey();
            Class<?> patametroTipo = entry.getValue();
            String parametroValor = parametros.get(parametroClave);

            parametrosReporte.put(parametroClave, obtenerValorCasteado(parametroValor, patametroTipo));
        }

        JasperReport jr = compilador.compilar(reporte.getInputStream());

        return compilador.generarReporte(jr, parametrosReporte, parametros.get("TIPO_REPORTE"));
    }

    public <T> T obtenerValorCasteado(String valor, Class<T> t) {
        if (valor == null) {
            throw new NullPointerException("No se puede castear un valor nulo");
        }

        if (Boolean.class.isAssignableFrom(t)) {
            return (T) Boolean.valueOf(valor);
        } else if (Integer.class.isAssignableFrom(t)) {
            return (T) Integer.valueOf(valor);
        } else if (Long.class.isAssignableFrom(t)) {
            return (T) Long.valueOf(valor);
        } else if (Float.class.isAssignableFrom(t)) {
            return (T) Float.valueOf(valor);
        } else if (Double.class.isAssignableFrom(t)) {
            return (T) Double.valueOf(valor);
        } else if (BigDecimal.class.isAssignableFrom(t)) {
            return (T) new BigDecimal(valor);
        } else if (Date.class.isAssignableFrom(t)) {
            Date date = FechaUtil.comoDate(valor, FechaUtil.PATRON_FECHA_CORTA);
            return (T) date;
        } else if(String.class.isAssignableFrom(t)) {
            return (T) String.valueOf(valor);
        }

        throw new RuntimeException("No se ha logro convertir el valor:" + valor + " en " + t.getCanonicalName());
    }
}
