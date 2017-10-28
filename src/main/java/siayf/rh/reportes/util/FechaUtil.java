/*
 * FechaUtil.java
 * Creado el 07/mar/2016 3:12:52 PM
 *
 */

package siayf.rh.reportes.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

/**
 * Esta clase es una utilería que permite trabajar con la fecha y la hora, tanto
 * formateo como conversión.
 *
 * @author Leila Schiaffini Ehuan
 * @author Freddy Barrera
 */
public class FechaUtil {

    private static final Logger LOGGER = Logger.getLogger(FechaUtil.class.getName());
    public static final Locale LUGAR_MEXICO = new Locale("es", "MX");
    public static final int FECHAS_POR_MES = 0;
    public static final int FECHAS_POR_QUINCENA = 1;
    public static final int FECHAS_POR_SEMANA = 2;
    public static final String PATRON_FECHA_CORTA = "dd/MM/yyyy";
    public static final String PATRON_FECHA_HORA_CORTA = "dd/MM/yyyy HH:mm:ss";
    public static final String PATRON_HORA_12 = "HH:mm:ss";
    public static final String PATRON_HORA_24 = "hh:mm:ss aa";
    public static final String PATRON_FECHA_BASE_DE_DATOS = "yyyy-MM-dd";

    private FechaUtil() {
    }

    /**
     * Retorna la fecha actual en el sistema.
     *
     * @return
     */
    public static Date obtenerFechaActual() {
        LocalDateTime fechaActual = LocalDateTime.now();
        return comoDate(fechaActual);
    }

    public static Date obtenerFechaActualSinHora() {
        LocalDateTime fechaActual = LocalDateTime.now();
        return comoDate(fechaActual);
    }

    public static int obtenerMesActual() {
        LocalDate fechaActual = LocalDate.now();
        return fechaActual.getMonthOfYear();
    }

    public static int obtenerAnyoActual() {
        LocalDate fechaActual = LocalDate.now();
        return fechaActual.getYear();
    }

    public static LocalTime obtenerHoraActual() {
        LocalTime horaActual = LocalTime.now();
        return horaActual;
    }

    public static Date obtenerUltimoDiaMes(int mes, int anyo) {
        LocalDate fecha = new LocalDate(anyo, mes, 3);
        fecha = fecha.plusMonths(1);
        fecha = fecha.minusDays(1);
        return comoDate(fecha);
    }
    
    /**
     * Este método devuelve el primer día del año.
     *
     * @param anyo
     * @return
     */
    public static Date obtenerPrimerDiaDelAnyo(Integer anyo) {
        LocalDate fecha = new LocalDate(anyo, 1, 1);
        return comoDate(fecha);
    }

    /**
     * Este método devuelve el último día del año.
     *
     * @param anyo
     * @return
     */
    public static Date obtenerUltimoDiaDelAnyo(Integer anyo) {
        LocalDate fecha = new LocalDate(anyo, 12, 31);
        return comoDate(fecha);
    }

    /**
     * Calcula la edad de la persona.
     *
     * @param fechaNacimiento la fecha de nacimiento.
     * @return la edad.
     */
    public static int calcularEdad(Date fechaNacimiento) {
        if (fechaNacimiento != null) {
            LocalDate fechaNac = comoLocalDate(fechaNacimiento);
            LocalDate fechaActual = LocalDate.now();
            int anyoNacimiento = fechaNac.getYear();
            int anyoActual = fechaActual.getYear();
            int edad = anyoActual - anyoNacimiento;

            if (fechaActual.getMonthOfYear() < fechaNac.getMonthOfYear()) {
                edad = edad - 1;
            } else if (fechaActual.getYear() == fechaNac.getYear()) { // Si son el mismo mes
                // Si aun no cumple años
                if (fechaActual.getDayOfMonth() > fechaNac.getDayOfMonth()) {
                    edad = edad - 1;
                }
            }
            return edad;
        } else {
            return 0;
        }
    }
    
    /**
     * Este método devuelve la fecha en formato: dd/MM/yyyy.
     *
     * @param fecha una fecha.
     * @return la fecha formateada.
     */
    public static String formatoFecha(Date fecha) {
        return formatearFecha(fecha, PATRON_FECHA_CORTA);
    }

    /**
     * Este método devuelve la fecha y la hora en formato: dd/MM/yyyy HH:mm:ss
     *
     * @param fechaHora una fecha.
     * @return la fecha formateada.
     */
    public static String formatoFechaHora(Date fechaHora) {
        return formatearFecha(fechaHora, PATRON_FECHA_HORA_CORTA);
    }
    
    /**
     * Este método devuelve la hora en formato de 24 horas: HH:mm:ss.
     * 
     * @param hora
     * @return 
     */
    public static String formatoHora(Date hora) {
        return formatearFecha(hora, PATRON_HORA_24);
    }

    /**
     * Este método permite convertir una fecha según el patrón que recibe
     * siempre que el patrón sea valido.
     *
     * @param patronFecha el patrón que se seguirá para formatear la fecha.
     * @param fecha una fecha.
     * @return la fecha formateada.
     */
    public static String formatearFecha(Date fecha, String patronFecha) {
        if (fecha == null) {
            LOGGER.warning("No se puede formatear una fecha nula");
            return "";
        }

        if (patronFecha == null || patronFecha.trim().isEmpty()) {
            LOGGER.log(Level.WARNING,
                    "No se ha recibido un patrón por tanto se usar el patrón: \"{0}\" para transformar la fecha.", PATRON_FECHA_CORTA);
            patronFecha = PATRON_FECHA_CORTA;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(patronFecha, LUGAR_MEXICO);
        return dateFormat.format(fecha);
    }
    
    /**
     * Este método analiza una cadena y la transforma a un tipo fecha si el
     * formato es valido.
     *
     * @param fecha una cadena que representa una fecha u hora.
     * @param patronFecha el patrón (formato) que tiene la fecha.
     * @return si el formato es correcto una instancia de tipo fecha, en caso
     * contrario retorna null.
     */
    public static Date comoDate(String fecha, String patronFecha) {
        if (fecha == null || patronFecha == null || fecha.trim().isEmpty() || patronFecha.trim().isEmpty()) {
            return null;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(patronFecha, LUGAR_MEXICO);
            return dateFormat.parse(fecha);
        } catch (ParseException ex) {
            LOGGER.log(Level.WARNING, "Problemas al transformar la fecha \"{0}\" usando el patrón \"{1}\": {2}", new Object [] {fecha, patronFecha, ex});
            return null;
        }
    }
    
    public static Date comoDate(java.sql.Date sqlDate) {
        Date date = new Date(sqlDate.getTime());
        return date;
    }

    public static Date comoDate(LocalDate localDate) {
        return localDate.toDate();
    }

    public static Date comoDate(LocalDateTime localDateTime) {
        return localDateTime.toDate();
    }

    public static java.sql.Date comoSqlDate(Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }
    
    public static LocalDate comoLocalDate(Date date) {
        return LocalDate.fromDateFields(date);
    }

    public static LocalDateTime comoLocalDateTime(Date date) {
        return LocalDateTime.fromDateFields(date);
    }

}
