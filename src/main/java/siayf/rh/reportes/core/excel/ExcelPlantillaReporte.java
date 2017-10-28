/*
 * ExcelPlantillaReporte.java
 * Creado el 23/sep/2016 7:18:25 PM
 * 
 */

package siayf.rh.reportes.core.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import siayf.rh.reportes.api.Reporte;

/**
 * Esta clase ayuda en la creación de reportes de Excel en los que se emplea un 
 * archivo de Excel como plantilla.
 * 
 * @param <T> el tipo que tendrán los detalles.
 * 
 * @author Freddy Barrera
 */
public abstract class ExcelPlantillaReporte<T> extends Reporte {

    private static final Logger LOGGER = Logger.getLogger(ExcelPlantillaReporte.class.getName());
    
    /** El nombre de la hoja donde se encuentra el detalle. */
    private final String nombreHoja;
    /** Instancia de la plantilla de Excel en memoria. */
    protected Workbook libro;
    /** Instancia que representa la hoja de Excel en la que se esta trabajando. */
    protected Sheet hoja;
    
    public ExcelPlantillaReporte(String ruta, String nombreArchivo, String nombreHoja) {
        super(ruta, nombreArchivo);
        this.nombreHoja = nombreHoja;
    }
    
    /**
     * Este método carga de la plantilla y prepara el libro y la hoja que se
     * pueda usaer.
     *
     * @throws IOException en caso de que no se encuentre el archivo o este
     * dañado lanzara esta excepción.
     */
    protected void cargarPlantilla() throws IOException {
        libro = new XSSFWorkbook(getInputStream());
        hoja = libro.getSheet(nombreHoja);
    }

    /**
     * Devuelve el contenido del archivo cargado como un arreglo de bytes.
     *
     * @return los bytes que representan el archivo.
     * @throws IOException en caso de no poder tener acceso al archivo se
     * lanzara esta excepción.
     */
    protected byte[] obtenerBytes() throws IOException {
        byte[] bytes;

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            libro.write(byteArrayOutputStream);
            bytes = byteArrayOutputStream.toByteArray();
            libro.close();
            getInputStream().close();
        }

        return bytes;
    }

    /**
     * Permite crear un reporte con los detalle con una lista de acumulados.
     *
     * @param detalles una lista de comisionado o licencia.
     * @return un arreglo de bytes que representa el archivo de excel.
     */
    public byte[] generar(List<T> detalles) {
        try {
            cargarPlantilla();
            llenarDetalles(detalles);

            return obtenerBytes();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Ocurrio un error al cargar la plantilla.\n{0}", ex);
            return ReporteVacio.obtenerBytes();
        }
    }
    
    protected abstract void llenarDetalles(List<T> detalles);

}
