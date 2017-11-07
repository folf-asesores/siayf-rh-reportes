/*
 * ReporteResource.java
 * Creado el 9/sep/2016 1:37:04 PM
 *
 */

package siayf.rh.reportes.core.rs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import siayf.rh.reportes.api.Archivo;
import siayf.rh.reportes.api.Generador;
import siayf.rh.reportes.core.BitacoraReporte;
import siayf.rh.reportes.core.excel.AlmacenReportesExcel;
import siayf.rh.reportes.core.excel.ExcelGenerador;
import siayf.rh.reportes.core.jasperreports.AlmacenReportesJasperReports;
import siayf.rh.reportes.core.jasperreports.JasperReportsGenerador;
import siayf.rh.reportes.core.txt.AlmacenReportesTxt;
import siayf.rh.reportes.core.txt.TxtGenerador;
import siayf.rh.reportes.core.word.AlmacenReporteWord;
import siayf.rh.reportes.core.word.WordGenerador;

import static siayf.rh.reportes.util.BeanInjectUtil.getBean;

/**
 * <p>
 * Esta clase tiene como objetivo llevar una bitácora de los reportes que se
 * generan en el sistema así como ayudar en la generación de los reportes del
 * sistema.</p>
 *
 * <p>
 * La generación del reporte se realiza en dos pasos.</p>
 *
 * <ol>
 * <li>Obtener el ID de referencia, para ello se requiere de una lista de
 * parametros.</li>
 * <li>Generar el reporte, para ello se requiere del ID de referencia del paso
 * anterior.</li>
 * </ol>
 *
 * @author Freddy Barrera
 * @author Eduardo Chuc Mex
 */
@Path("/")
public class ReporteResource {

    private static final Logger LOGGER = Logger.getLogger(ReporteResource.class.getName());
    
    @Context
    private UriInfo uriInfo;

    private static final String ETAPA_PERSISTENCIA_DE_DATOS = "A guardar en la base de datos";
    private static final String ETAPA_RECUPERACION_DE_DATOS = "Almacenados en la base de datos";

    private final BitacoraReporte bitacoraReporte;

    /**
     * Crea una nueva instancia del administrador de reportes.
     */
    public ReporteResource() {
        bitacoraReporte = getBean(BitacoraReporte.class);
    }

    /**
     * Permite obtener una referencia que se empleará para la generación del
     * reporte apartir de un arreglo de parámetros.
     *
     * <p>
     * Ejemplo de parametros para obtener la referencia.
     * <code>
     *  <pre>
     * String[] parametros = new String[] {
     *      "id-usuario", String.valueOf(usuario.getIdUsuario()),
     *      "reporte-nombre", nombreReporte,
     *      "tipo-reporte",  "pdf",
     *      ...
     * };
     *  </pre>
     * </code>
     * </p>
     *
     * @return una cadena con la referencia para generar el reporte.
     * @throws IllegalArgumentException Si los parametros no son pares.
     * @throws NullPointerException Si los paramtros están nulos o vacios.
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String obtenerReferencia() throws NullPointerException, IllegalArgumentException {
        Map<String, String> mapaParametros = separarClaveValor(uriInfo.getQueryParameters());
        imprimirParametros(mapaParametros, ETAPA_PERSISTENCIA_DE_DATOS);

        return bitacoraReporte.obtenerReferencia(mapaParametros);
    }

    /**
     * Genera un reporte del cual previamente se han almacenado los parámetros
     * en la base de datos.
     *
     * @param referencia cadena que permite recuperar los parametros almacenados
     * en la base de datos.
     * @return los bytes que representa el archivo.
     * @throws NullPointerException si la es nula o vacia.
     * @throws IllegalArgumentException si la cadena no tiene los 36 cáracteres.
     */
    @POST
    @Path("{referencia}")
    @Produces(MediaType.WILDCARD)
    public Response obtenerReporte(@PathParam("referencia") String referencia) throws NullPointerException, IllegalArgumentException {
        if (referencia == null || referencia.trim().isEmpty()) {
            throw new NullPointerException("La referencia está vacia.");
        }

        if ((referencia.length() < 36) || (referencia.length() > 36)) {
            throw new IllegalArgumentException("La referencia es debe ser de 36 cárcteres.");
        }

        Map<String, String> parametros = bitacoraReporte.obtenerParametros(referencia);
        imprimirParametros(parametros, ETAPA_RECUPERACION_DE_DATOS);

        String nombreReporte = parametros.get("REPORTE_NOMBRE");
        String tipoReporte = parametros.get("TIPO_REPORTE");
        Generador generador = null;

        switch (tipoReporte) {
            case "docx":
                if (new AlmacenReporteWord().extisteReporte(nombreReporte)) {
                    generador = new WordGenerador();
                }
                break;
            case "pdf":
                if (new AlmacenReportesJasperReports().extisteReporte(nombreReporte)) {
                    generador = new JasperReportsGenerador();
                }
                break;
            case "txt":
                if (new AlmacenReportesTxt().extisteReporte(nombreReporte)) {
                    generador = new TxtGenerador();
                }
                break;
            case "xlsx":
                if (new AlmacenReportesExcel().extisteReporte(nombreReporte)) {
                    generador = new ExcelGenerador();
                }
                break;
        }

        if (generador != null) {
            Archivo reporte = generador.obtenerReporte(parametros);
            return Response.ok()
                    .entity(reporte.getBytes())
                    .header("Content-Disposition", "attachment; filename=\"" + reporte.getNombre() + "\"")
                    .header("Content-Type", reporte.getMediaType())
                    .build();
        } else {
            LOGGER.log(Level.WARNING, "El reporte {0} de tipo {1} no se ha encontrado.", new Object[]{nombreReporte, tipoReporte});
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    /**
     * Convierte multiples parametros en un par de parametros (en un
     * <code>Map</code> de clave - valor).
     *
     * @param parametros los parametros del reporte a separar.
     * @return los paramtros separados.
     */
    private Map<String, String> separarClaveValor(MultivaluedMap<String, String> parametros)
            throws NullPointerException, IllegalArgumentException {

        if (parametros == null) {
            throw new NullPointerException("No se ha encontrado ningún parametro.");
        }

        Map<String, String> map = new HashMap<>();
        
        for (Map.Entry<String, List<String>> parametro : parametros.entrySet()) {
            String clave = parametro.getKey();
            String primerValor = parametros.getFirst(clave);

            if (clave != null) {
                clave = clave.trim().toUpperCase().replace('-', '_');
                map.put(clave, primerValor);
            }            
        }

        return map;
    }
    
    /**
     * Imprime los detalles de los parametros que del reporte en la bitacora del
     * servidor.
     *
     * @param parametros los parametros a imprimir.
     * @param etapa representa la etapa en la que está la generación del
     * reporte.
     */
    private void imprimirParametros(Map<String, String> parametros,
            String etapa) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================================");
        sb.append("========================================\n");
        sb.append("Parametros del reporte (");
        sb.append(etapa);
        sb.append(")\n");
        sb.append("========================================");
        sb.append("========================================\n");
        sb.append("Clave\t\tValor\n");
        sb.append("========================================");
        sb.append("========================================\n");

        for (Map.Entry<String, String> entry : parametros.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            sb.append(key);
            sb.append("\t\t");
            sb.append(value);
            sb.append('\n');
        }

        LOGGER.fine(sb.toString());
    }

}
