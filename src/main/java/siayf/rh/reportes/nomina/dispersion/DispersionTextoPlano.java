/*
 * DispersionTextoPlano.java
 * Creado el 07/dic/2016 8:13:31 PM
 *
 */

package siayf.rh.reportes.nomina.dispersion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import siayf.rh.reportes.util.ArchivoUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class DispersionTextoPlano implements Serializable {

    private static final long serialVersionUID = -4661079055687311613L;
    private static final Logger LOGGER = Logger.getLogger(DispersionTextoPlano.class.getName());
    private static final char ESPACIO_EN_BLANCO = ' ';

    private File archivoDispersion;

    protected byte[] obtenerReporte(List<DispersionDto> dispersion) {
        try {
            Path path = Files.createTempFile("dispersion", ".txt");
            archivoDispersion = path.toFile();
            escribirArchivo(dispersion);
            return obternerBytes();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "No fue posible generar el archivo temporal.", ex);
            return null;
        }
    }

    private void escribirArchivo(List<DispersionDto> dispersion) {
        try (FileWriter escritor = new FileWriter(archivoDispersion)) {
            for (DispersionDto dispersionDTO : dispersion) {
                escritor.append(dispersionDTO.getNumeroCuenta());
                escritor.append(ESPACIO_EN_BLANCO);
                escritor.append(dispersionDTO.getNumeroCheque());
                escritor.append(ESPACIO_EN_BLANCO);
                escritor.append(dispersionDTO.getNombreEmpleado());
                escritor.append(ESPACIO_EN_BLANCO);
                escritor.append(dispersionDTO.getMonto().toString());
                escritor.append(ESPACIO_EN_BLANCO);
                escritor.append(dispersionDTO.getNombreProducto());
                escritor.append(ArchivoUtil.SEPARADOR_DE_ARCHIVO);
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "No fue posible escribir/leer el archivo temporal", ex);
        }
    }

    private byte[] obternerBytes() {
        try (FileInputStream fis = new FileInputStream(archivoDispersion);
                ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte [] buffer = new byte[1024];

            for (int readNum; (readNum = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, readNum);
            }

            byte[] bytes = bos.toByteArray();
            archivoDispersion.delete();
            return bytes;
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error escribir/leer archivo final", ex);
            return null;
        }
    }

}
