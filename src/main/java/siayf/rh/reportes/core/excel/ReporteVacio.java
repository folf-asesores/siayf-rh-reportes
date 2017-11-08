/*
 * ReporteVacio.java
 * Creado el 15/feb/2017 10:00:47 AM
 * 
 */

package siayf.rh.reportes.core.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Esta clase permite crear un archivo de Excel vacio.
 * 
 * <p>Esta clase se ha pensado como una alternariva para cuando ocurra un error
 * poder enviar algo al usuario.</p>
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ReporteVacio implements Serializable {

    private static final long serialVersionUID = 7107911995230489541L;
    private static final Logger LOGGER = Logger.getLogger(ReporteVacio.class.getName());

    private ReporteVacio() {
    }
    
    /**
     * Genera un archivo de Excel sin datos.
     * 
     * @return un arreglo de bytes que contiene un archivo de Excel vacio.
     */
    public static byte[] obtenerBytes() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Workbook libro = new XSSFWorkbook()) {
            String nombreHojaSeguro = WorkbookUtil.createSafeSheetName("Hoja 1", '_');
            libro.createSheet(nombreHojaSeguro);
            libro.write(baos);
            return baos.toByteArray();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

}
