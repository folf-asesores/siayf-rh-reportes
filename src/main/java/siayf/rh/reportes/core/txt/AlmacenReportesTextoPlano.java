/*
 * AlmacenReportesTextoPlano.java
 * Creado el 07/dic/2016 10:45:36 PM
 *
 */

package siayf.rh.reportes.core.txt;

import java.util.HashMap;
import java.util.Map;

import siayf.rh.reportes.api.AlmacenReportes;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class AlmacenReportesTextoPlano implements AlmacenReportes<TextoPlanoReporte> {
    private static final Map<String, TextoPlanoReporte> REPORTES = new HashMap<>();

    static {
        TextoPlanoReporte dispersionNomina = new TextoPlanoReporte();
        REPORTES.put("dispersion_nomina", dispersionNomina);
        TextoPlanoReporte comprobanteNomina = new TextoPlanoReporte();
        REPORTES.put("comprobante_nomina", comprobanteNomina);
        TextoPlanoReporte prenominaEventuales = new TextoPlanoReporte();
        REPORTES.put("prenomina_eventuales", prenominaEventuales);
        TextoPlanoReporte listadoFirmas = new TextoPlanoReporte();
        REPORTES.put("listado-firmas", listadoFirmas);
    }

    @Override
    public TextoPlanoReporte obtenerReporte(String nombreReporte) {
        return REPORTES.get(nombreReporte);
    }

    @Override
    public boolean extisteReporte(String nombreReporte) {
        return REPORTES.containsKey(nombreReporte);
    }

}
