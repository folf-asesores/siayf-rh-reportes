/*
 * ArchivoUtil.java
 * Creado el 30/06/2016 11:35:45 AM
 *
 */
package siayf.rh.reportes.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ArchivoUtil {

    private static final Logger LOGGER = Logger.getLogger(ArchivoUtil.class.getName());

    private static final String PATRON_ESPACIOS_EN_BLANCO_AL_FINAL = "(\\s+)$";
    private static final String CARPETA_USUARIO = System.getProperty("user.home");
    public static final String SEPARADOR_DE_ARCHIVO = System.getProperty("file.separator");
    public static final String SEPARADOR_DE_ARCHIVO_UNIX = "\n";
    public static final String SEPARADOR_DE_ARCHIVO_WINDOWS = "\r\n";
    public static final Charset WINDOWS_LATIN_CHARSET = Charset.forName("windows-1252");
    public static final Charset MS_DOS_LATIN_CHARSET = Charset.forName("Cp850");
    public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    /**
     * Por ser una utilería no se permite la creación de instancias de esta
     * clase.
     */
    private ArchivoUtil() {

    }
    /**
     * Permite obtener el nombre del archivo sin extensión además de que
     * determina la extesión del archivo según el contenido del mismo. Su
     * función la realiza escribiendo en la carpeta temporal el archivo.
     *
     * @param nombreArchivo el nombre del archivo (incluso sin extensión).
     * @param ext la extensión que se desea probar.
     * @param bytes los datos del archivo.
     * @return
     */
    public static Map<String, Object> validarArchivo(String nombreArchivo,
            TipoArchivo ext, byte[] bytes) {
        Map<String, Object> mapa = new HashMap<>();

        try {
            String nombreSinExtension = obtenerNombreSinExtension(nombreArchivo);
            Path archivoTemporal = Files
                    .createTempFile(nombreSinExtension + '_', ".tmp");
            Files.write(archivoTemporal, bytes);
            String contentType
                    = (Files.probeContentType(archivoTemporal) == null)
                    ? ext.getMIMEType() : Files.probeContentType(archivoTemporal);
            LOGGER.log(Level.FINE, "contentType: {0}", contentType);
            TipoArchivo tipoReal = TipoArchivo
                    .getTipoArchivoPorMIMEType(contentType);
            Files.delete(archivoTemporal);

            mapa.put("NOMBRE_DE_ARCHIVO", nombreSinExtension);
            mapa.put("EXTENSION", tipoReal);
            return mapa;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return mapa;
        }
    }

    /**
     *
     * @param nombreArchivo
     * @param contentType
     * @param contenido
     * @param contentTypeDeseado
     * @param extensionDeseada
     * @return
     */
    public static boolean validarTipoArchivo(String nombreArchivo, String contentType, byte[] contenido, String contentTypeDeseado, String extensionDeseada) {
        String contentTypeReal = obtenerMIMEType(contenido);

        LOGGER.log(Level.FINE, "Nombre del archivo: {0} Content Type del archivo: {1} Content Type deseado: {2} Extensión deseada: {3} Content Type real: {4}", new Object[]{nombreArchivo, contentType, contentTypeDeseado, extensionDeseada, contentTypeReal});

        if (!contentTypeDeseado.equals(contentTypeReal)) {
            return false;
        }

        if (!(contentType != null && !contentType.equals(contentTypeDeseado))) {
            return false;
        }

        return nombreArchivo.endsWith(extensionDeseada);
    }

    /**
     *
     *
     * @param nombreArchivo
     * @param contentType
     * @param contenido
     * @param tipoDeseado
     * @return
     */
    public static boolean validarTipoArchivo(String nombreArchivo, String contentType, byte[] contenido, TipoArchivo tipoDeseado) {
        String contentTypeReal = obtenerMIMEType(contenido);

        if (!tipoDeseado.getMIMEType().equals(contentTypeReal)) {
            return false;
        }

        if (contentType != null && !contentType.equals(tipoDeseado.getMIMEType())) {
            return false;
        }

        return nombreArchivo.endsWith(tipoDeseado.getExtension());
    }

    /**
     * Este método permite conocer el MIME Type de un archivo.
     *
     * @param contenido un arreglo de bytes con el contenido del archivo a nivel
     * de bytes.
     * @return el MIME Type.
     * @throws NullPointerException si el contenido es nulo.
     */
    private static String obtenerMIMEType(byte[] contenido) {
        try {
            if (contenido == null) {
                throw new NullPointerException("No se puede obtener el MIMEType de un null");
            }
            
            Path archivoTemporal = Files
                    .createTempFile(UUID.randomUUID().toString(), ".tmp");
            Files.write(archivoTemporal, contenido);

            String contentType = Files.probeContentType(archivoTemporal);
            Files.delete(archivoTemporal);

            return contentType;
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Permite obtener un archivo guardado en el disco.
     *
     * @param ruta la ruta en la que se leera el archivo.
     * @param nombreArchivo el nombre del archivo, que debe incluir la extesión.
     * @return los bytes que representan el archivo.
     * @throws java.io.IOException si ocurre de lectura o escritura al guardar
     * el archivo.
     */
    public static byte[] leerArchivo(String ruta, String nombreArchivo) throws IOException {
        return leerArchivo(ruta, nombreArchivo, false);
    }

    /**
     * Permite obtener un archivo guardado en el disco.
     *
     * @param ruta la ruta en la que se leera el archivo.
     * @param nombreArchivo el nombre del archivo, que debe incluir la extesión.
     * @param usarCarpetaUsuario si la ruta se tomara desde la carpeta del
     * usuario.
     * @return los bytes que representan el archivo.
     * @throws IOException si ocurre de lectura o escritura al guardar el
     * archivo.
     */
    public static byte[] leerArchivo(String ruta, String nombreArchivo, boolean usarCarpetaUsuario) throws IOException {
        if (ruta == null) {
            ruta = "";
        }

        Path rutaReal = usarCarpetaUsuario ? Paths.get(CARPETA_USUARIO, ruta) : Paths.get(ruta);
        Path rutaArchivo = Paths.get(rutaReal.toString(), nombreArchivo);

        return Files.readAllBytes(rutaArchivo);
    }

    /**
     * Permite guardar un archivo en la carpeta principal (user home) del
     * usuario.
     *
     * @param file los bytes que representan el archivo.
     * @param fileName el nombre del archivo, incluyendo la extensión.
     * @throws IOException si ocurre alguna excepción de escritura o lectura.
     */
    public static void guardarEnCarpetaUsuario(byte[] file, String fileName) throws IOException {
        if (file == null) {
            LOGGER.severe("No se puede guardar un archivo nulo");
        } else if (ValidacionUtil.esCadenaVacia(fileName)) {
            LOGGER.severe("El nombre del archivo a guardar esta vacio.");
        } else {
            String filePath = CARPETA_USUARIO + SEPARADOR_DE_ARCHIVO + fileName;
            Path path = Paths.get(filePath);
            Files.write(path, file);
        }
    }

    /**
     * Permite guardar un archivo.
     *
     * @param ruta la ruta en la que se guardará el archivo.
     * @param nombreArchivo el nombre del archivo, que debe incluir la extesión.
     * @param archivo los bytes que representan el archivo.
     * @param usarCarpetaUsuario usar como raíz la carpeta principal del
     * usuario.
     * @throws java.io.IOException si ocurre de lectura o escritura al guardar
     * el archivo.
     */
    public static void guardarArchivo(String ruta, String nombreArchivo, byte[] archivo, boolean usarCarpetaUsuario) throws IOException {
        guardarArchivo(ruta, nombreArchivo, archivo, true, usarCarpetaUsuario);
    }

    /**
     * Permite guardar un archivo.
     *
     * @param ruta la ruta en la que se guardará el archivo.
     * @param nombreArchivo el nombre del archivo, que debe incluir la extesión.
     * @param archivo los bytes que representan el archivo.
     * @param sobreescribir permite sobre escribir el archivo si existe.
     * @param usarCarpetaUsuario usar como raíz la carpeta principal del
     * usuario.
     * @throws java.io.IOException si ocurre de lectura o escritura al guardar
     * el archivo.
     */
    public static void guardarArchivo(String ruta, String nombreArchivo,
            byte[] archivo, boolean sobreescribir, boolean usarCarpetaUsuario)
            throws IOException {

        Path rutaReal = usarCarpetaUsuario ? Paths.get(CARPETA_USUARIO, ruta) : Paths.get(ruta);
        Path rutaArchivo = Paths.get(rutaReal.toString(), nombreArchivo);

        if (Files.notExists(rutaReal)) {
            Files.createDirectories(rutaReal);
        }

        if (sobreescribir) {
            Files.deleteIfExists(rutaArchivo);
            Files.createFile(rutaArchivo);
        } else if (Files.notExists(rutaArchivo)) {
            Files.createFile(rutaArchivo);
        }

        Files.write(rutaArchivo, archivo);
    }

    /**
     * Permite mover un archivo.
     *
     * @param origen la ruta de origen.
     * @param destino la ruta destino.
     * @param usarCarpetaUsuario si se usará la carpeta personal del usuario.
     */
    public static void moverArchivo(String origen, String destino,
            boolean usarCarpetaUsuario) {
        try {
            Path rutaOrigen = usarCarpetaUsuario ? Paths.get(CARPETA_USUARIO, origen) : Paths.get(origen);
            Path rutaDestino = usarCarpetaUsuario ? Paths.get(CARPETA_USUARIO, destino) : Paths.get(destino);

            Files.move(rutaOrigen, rutaDestino);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * Permite eliminar un archivo del disco.
     *
     * @param ruta la ruta en la que se encuentra el archivo que será eliminado.
     * @param nombreArchivo el nombre del archivo que será eliminado.
     * @throws IOException si ocurre un error de lectura o escritura al eliminar
     * el archivo.
     */
    public static void eliminarArchivo(String ruta, String nombreArchivo) throws IOException {
        Path path = Paths.get(ruta, nombreArchivo);
        Files.deleteIfExists(path);
    }

    /**
     * Permite eliminar archivos ignorando la extensión de estos, basandose tan
     * sólo en nombre del archivo.
     *
     * @param ruta la ruta en la que se encuentra el archivo que será eliminado.
     * @param nombreArchivo el nombre del archivo que será eliminado, sin
     * extensión.
     * @param usarCarpetaUsuario usar como raíz la carpeta principal del
     * usuario.
     * @throws IOException
     */
    public static void eliminarArchivoSoloConNombre(String ruta, String nombreArchivo, boolean usarCarpetaUsuario) throws IOException {
        List<Path> archivosPorEliminar = new ArrayList<>();

        Path rutaReal = usarCarpetaUsuario ? Paths.get(CARPETA_USUARIO, ruta) : Paths.get(ruta);

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(rutaReal)) {
            for (Path path : directoryStream) {
                String archivoSinExtension = obtenerNombreSinExtension(path.toString().replace(rutaReal.toString() + SEPARADOR_DE_ARCHIVO, ""));

                if (nombreArchivo.equals(archivoSinExtension)) {
                    archivosPorEliminar.add(path);
                }
            }
        }

        for (Path archivoPorEliminar : archivosPorEliminar) {
            Files.deleteIfExists(archivoPorEliminar);
        }
    }

    /**
     * Permite convertir un archivo de texto plano con códificación UTF-8 y
     * carácteres de fin de línea tipo UNIX a formato de Windows.
     *
     * @param archivo un arreglo de bytes que representa un archivo de texto
     * plano.
     * @return un arreglo de bytes que representa un archivo de texto plano con
     * códificación de Windows.
     * @throws IOException en caso de que haya error de lectura o escritura al
     * crear los archivos temporales.
     */
    public static byte[] codificarComoWindows(final byte[] archivo) throws IOException {
        if (archivo == null) {
            throw new NullPointerException("El archivo no debe se nulo para poder realizar conversión.");
        }

        LOGGER.info("Iniciando la conversión a formato de Windows.");
        Path archivoTemporal = Files.createTempFile("origen", ".txt");
        Files.write(archivoTemporal, archivo);

        if (!SEPARADOR_DE_ARCHIVO_WINDOWS.equals(SEPARADOR_DE_ARCHIVO)) {
            System.setProperty("line.separator", SEPARADOR_DE_ARCHIVO_WINDOWS);
        }

        List<String> lineas = Files.readAllLines(archivoTemporal, UTF8_CHARSET);
        List<String> lineasNuevas = new ArrayList<>();
        for (String linea : lineas) {
            String nuevaLinea = linea.replaceAll(PATRON_ESPACIOS_EN_BLANCO_AL_FINAL, "");
            lineasNuevas.add(nuevaLinea);
        }

        Path archivoTemporalWin = Files.createTempFile("destino", ".txt");
        Files.write(archivoTemporalWin, lineasNuevas, WINDOWS_LATIN_CHARSET);
        System.setProperty("line.separator", SEPARADOR_DE_ARCHIVO);

        byte[] archivoWindows = Files.readAllBytes(archivoTemporalWin);
        LOGGER.info("La conversión en formato de Windows se ha completado correctamente.");
        Files.delete(archivoTemporal);
        Files.delete(archivoTemporalWin);
        return archivoWindows;
    }

    /**
     * Permite convertir un archivo de texto plano con códificación UTF-8 y
     * caracteres de fin de línea tipo UNIX a formato de MS-DOS.
     *
     * @param archivo un arreglo de bytes que representa un archivo de texto
     * plano.
     * @return un arreglo de bytes que representa un archivo de texto plano con
     * códificación de Windows.
     * @throws IOException en caso de que haya error de lectura o escritura al
     * crear los archivos temporales.
     */
    public static byte[] codificarComoMsDos(final byte[] archivo) throws IOException {
        if (archivo == null) {
            throw new NullPointerException("El archivo no debe se nulo para poder realizar conversión.");
        }

        LOGGER.info("Iniciando la conversión a formato de MS-DOS.");
        Path archivoTemporal = Files.createTempFile("origen", ".txt");
        Files.write(archivoTemporal, archivo);

        if (!SEPARADOR_DE_ARCHIVO_WINDOWS.equals(SEPARADOR_DE_ARCHIVO)) {
            System.setProperty("line.separator", SEPARADOR_DE_ARCHIVO_WINDOWS);
        }

        List<String> lineas = Files.readAllLines(archivoTemporal, UTF8_CHARSET);
        List<String> lineasNuevas = new ArrayList<>();

        for (int i = 0; i < lineas.size(); i++) {
            String lineaAnterior = lineas.get(i);
            String nuevaLineaAnterior = lineaAnterior.replaceAll(PATRON_ESPACIOS_EN_BLANCO_AL_FINAL, "");

            lineasNuevas.add(nuevaLineaAnterior);
        }

        Path archivoTemporalWin = Files.createTempFile("destino", ".txt");
        Files.write(archivoTemporalWin, lineasNuevas, MS_DOS_LATIN_CHARSET);
        System.setProperty("line.separator", SEPARADOR_DE_ARCHIVO);

        byte[] archivoWindows = Files.readAllBytes(archivoTemporalWin);
        LOGGER.info("La conversión en formato de MS-DOS se ha completado correctamente.");
        Files.delete(archivoTemporal);
        Files.delete(archivoTemporalWin);

        return archivoWindows;
    }

    /**
     * Permite obtener el nombre del archivo sin extensión y estandariza el
     * nombre del archivo eliminando carácteres especiales además de que
     * transforma las letras en minúsculas.
     *
     * @param nombreArchivo el nombre del archivo con extensión o sin ella.
     * @return el nombre del archivo sin extensión.
     */
    public static String obtenerNombreSinExtension(String nombreArchivo) {
        String nombreSinExtension = FilenameUtils.removeExtension(nombreArchivo);
        nombreSinExtension = nombreSinExtension.toLowerCase();
        nombreSinExtension = CadenaUtil.remplazarCaracteresLatinos(nombreSinExtension);
        nombreSinExtension = nombreSinExtension.replace('.', '_');

        return nombreSinExtension;
    }

    /**
     * Elimina los espacios al final del archivo.
     * @param ruta la ruta del archivo del cual se eliminaran los espacio en blanco.
     * @param charset el juego de caracteres con los que se abrirá el archivo.
     * @throws IOException en caso de ocurrir un error de lectura o escritura.
     */
    public static void eliminarEspaciosAlFinalLinea(Path ruta, Charset charset) throws IOException {
        List<String> lineas = Files.readAllLines(ruta, charset);
        List<String> lineasNuevas = new ArrayList<>();

        for (String linea : lineas) {
            String nuevaLinea = linea.replaceAll(PATRON_ESPACIOS_EN_BLANCO_AL_FINAL, "");
            lineasNuevas.add(nuevaLinea);
        }

        Files.write(ruta, lineasNuevas, charset);
    }

}
