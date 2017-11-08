/*
 * TextoPlanoGenerador.java
 * Creado el 07/dic/2016 10:53:53 PM
 *
 */

package siayf.rh.reportes.core.txt;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

import siayf.rh.reportes.api.Archivo;
import siayf.rh.reportes.api.Generador;
import siayf.rh.reportes.nomina.comprobante.ComprobanteEmpleado;
import siayf.rh.reportes.nomina.dispersion.Dispersion;
import siayf.rh.reportes.nomina.firma.Firma;
import siayf.rh.reportes.nomina.prenomina.Prenomina;
import siayf.rh.reportes.util.TipoArchivo;

import static siayf.rh.reportes.util.BeanInjectUtil.getBean;

/**
 * Esta clase se encarga de generar los reporte de texto plano.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class TextoPlanoGenerador implements Generador {

    private static final Logger LOGGER = Logger.getLogger(TextoPlanoGenerador.class.getName());

    private static final long serialVersionUID = -763514407303196779L;

    @Override
    public Archivo obtenerReporte(Map<String, String> parametros) {
        AlmacenReportesTextoPlano almacen = new AlmacenReportesTextoPlano();
        String nombreReporte = parametros.get("REPORTE_NOMBRE");

        byte[] bytes = null;

        if (almacen.extisteReporte(nombreReporte)) {
            switch (nombreReporte) {
                case "comprobante_nomina": {
                    Integer idProductoNomina = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    ComprobanteEmpleado comprobanteEmpleadoBean = getBean(ComprobanteEmpleado.class);
                    bytes = comprobanteEmpleadoBean.generarReporte(idProductoNomina);
                }
                break;
                case "dispersion_nomina": {
                    Integer idProductoNomina = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    Dispersion dispersionBean = getBean(Dispersion.class);
                    bytes = dispersionBean.generarReporte(idProductoNomina);
                }
                break;
                case "listado_firmas": {
                    Integer idProductoNomina = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    Firma firmaBean = getBean(Firma.class);
                    bytes = firmaBean.generarReporte(idProductoNomina);
                }
                break;
                case "prenomina_eventuales": {
                    Integer idProductoNomina = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    Prenomina prenominaReporteBean = getBean(Prenomina.class);
                    bytes = prenominaReporteBean.generarReporte(idProductoNomina);
                }
            }
        }
        
        if (bytes == null) {
            try {
                Path path = Files.createTempFile(nombreReporte, ".txt");
                FileInputStream fileStream = new FileInputStream(path.toFile());
                bytes = IOUtils.toByteArray(fileStream);
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

        String nombre = nombreReporte + TipoArchivo.TXT.getExtension(true);
        String mediaType = TipoArchivo.TXT.getMIMEType();
        
        return new Archivo(nombre, mediaType, bytes);
    }

}
