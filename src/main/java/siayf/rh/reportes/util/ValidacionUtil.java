/*
 * ValidacionUtil.java
 * Creado el 18/mar/2016 11:43:25 AM
 */
package siayf.rh.reportes.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

/**
 * @author Eduardo Chuc Mex (Lic.Eduardo_Mex@hotmail.com)
 */
public class ValidacionUtil {
    
    private static final String PATRON_CURP = "[A-Z][A,E,I,O,U,X][A-Z]{2}[0-9]{2}[0-1][0-9][0-3][0-9][M,H](AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B,C,D,F,G,H,J,K,L,M,N,Ñ,P,Q,R,S,T,V,W,X,Y,Z]{3}[0-9,A-Z][0-9]";
    private static final String PATRON_NUMERO = "\\d*";
    private static final String PATRON_RFC = "[A-Z,Ñ,&]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z,0-9]?[A-Z,0-9]?[0-9,A-Z]?";
    private static final String PATRON_URL = "(((http|https):\\/{2})?(([0-9a-z_-]+\\.)+(aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cu|cv|cx|cy|cz|cz|de|dj|dk|dm|do|dz|ec|ee|eg|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mn|mn|mo|mp|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|nom|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ra|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sj|sk|sl|sm|sn|so|sr|st|su|sv|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|zw|arpa)(:[0-9]+)?((\\/([~0-9a-zA-Z\\#\\+\\%@\\.\\/_-]+))?(\\?[0-9a-zA-Z\\+\\%@\\/&\\[\\];=_-]+)?)?))\\S";

    private ValidacionUtil() {
    }

    // Validación para cadenas. ===============================================

    /**
     * <p>
     * Valida si la cadena está vacia.</p>
     * <p>
     * En caso de que la cadena sea null se devolvera <code>true</code></p>
     *
     * @param cadena la cadena que será validada
     * @return <code>true</code> si la cadena está vacia, <code>false</code> si
     * la cadena no está vacia.
     */
    public static boolean esCadenaVacia(String cadena) {
        return cadena == null || cadena.trim().isEmpty();
    }

    /**
     * Valida que la cadena solo contenga números.
     * 
     * @param cadena la cadena que será validada.
     * @return <code>true</code> 
     */
    public static boolean esNumero(String cadena) {
        return cadena.matches(PATRON_NUMERO);
    }

    // Validación de números. =================================================

    /**
     * Valida si el número no es nulo y es mayor que cero.
     * @param numero
     * @return 
     */
    public static boolean esNumeroPositivo(Number numero) {

        if (numero == null) {
            return false;
        }
        
        if (numero instanceof Short) {
            Short valor = (Short) numero;
            short cero = 0;
            return valor.compareTo(cero) > 0;
        } else if (numero instanceof Integer) {
            Integer valor = (Integer) numero;
            return valor > 0;
        } else if (numero instanceof Long) {
            Long valor = (Long) numero;
            return valor > 0;
        } else if (numero instanceof Float) {
            Float valor = (Float) numero;
            return valor.compareTo(0.0f) > 0;
        } else if (numero instanceof Double) {
            Double valor = (Double) numero;
            return valor.compareTo(0.0) > 0;
        } else if (numero instanceof BigDecimal) {
            BigDecimal valor = (BigDecimal) numero;
            return valor.compareTo(BigDecimal.ZERO) > 0;
        } else {
            return false;
        }
    }

    /**
     * Permite saber si el número que recibe es menor que uno. En caso de que el
     * valor sea nulo se devolvera falso.
     *
     * @param numero un número a identificar.
     * @return verdad sí y sólo sí el numero es menor que uno.
     *
     * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
     */
    public static boolean esNumeroNegativo(Number numero) {

        if (numero == null) {
            return false;
        }
        
        if (numero instanceof Short) {
            Short valor = (Short) numero;
            short cero = 0;
            return valor.compareTo(cero) == -1;
        } else if (numero instanceof Integer) {
            Integer valor = (Integer) numero;
            return valor == -1;
        } else if (numero instanceof Long) {
            Long valor = (Long) numero;
            return valor == -1;
        } else if (numero instanceof Float) {
            Float valor = (Float) numero;
            return valor.compareTo(0.0f) == -1;
        } else if (numero instanceof Double) {
            Double valor = (Double) numero;
            return valor.compareTo(0.0) == -1;
        } else if (numero instanceof BigDecimal) {
            BigDecimal valor = (BigDecimal) numero;
            return valor.compareTo(BigDecimal.ZERO) == -1;
        } else {
            return false;
        }
    }

    /**
     * Valida si un número es nulo.
     * 
     * @param numero el número a validar.
     * @return devuelve <code>true</code> sí y solo sí es nulo. 
     * 
     * @author Pablo Guevara
     */
    public static boolean esNulo(Number numero) {
        return numero == null;
    }
    
    /**
     * Valida que ambos números sean iguales. Se compara el primer valor con el
     * segundo.
     * 
     * @param numero1 el primer número a comparar.
     * @param numero2 el segundo número a comparar.
     * @return <code>true</code> sí y solo sí ambios números son iguales.
     */
    public static boolean sonIguales(Number numero1,
            Number numero2) {
        if (numero1 == null && numero2 == null) {
            return true;
        } else if(numero1 == null && numero2 != null) {
            return false;
        } else if(numero2 == null && numero1 != null) {
            return false;
        } else {
            return numero1.equals(numero2);
        }
    }

    // Validación para las fechas. ============================================

    /**
     * Valida si una fecha es nula.
     * @param fecha la fecha que será validad.
     * @return devuelve <code>true</code> sí y solo sí es nulo.      
     * @author Pablo Guevara
     */
    public static boolean esNulo(Date fecha) {
        boolean esValido = false;
        if (fecha == null) {
            esValido = true;
        }
        return esValido;
    }

    /**
     * Valida que la fecha no sea mayor a la fecha actual.
     * @param fecha la fecha que será validada.
     * @return <code>true</code> si la fecha es mayor que la actual en caso 
     * contrario <code>false</code>.
     */
    public static boolean esFechaFutura(Date fecha) {
        boolean esValida = false;
        DateTime fechaValidada = new DateTime(fecha);
        fechaValidada.withHourOfDay(0);
        fechaValidada.withMinuteOfHour(0);
        fechaValidada.withSecondOfMinute(0);
        fechaValidada.withMillisOfSecond(0);
        fechaValidada.withMillis(0);

        DateTime fechaActual = new DateTime();
        fechaActual.withHourOfDay(0);
        fechaActual.withMinuteOfHour(0);
        fechaActual.withSecondOfMinute(0);
        fechaActual.withMillisOfSecond(0);
        fechaActual.withMillis(0);

        if (fechaValidada.isAfter(fechaActual)) {
            esValida = true;
        }

        return esValida;
    }

    // Otras validaciones. ====================================================
    
    /**
     * Valida que el formato del RFC sea correcto.
     * <p>
     * En caso de que el RFC sea nulo o una cadena vacia se devolvera <code>false</code></p>
     *
     * @param rfc el RFC que será validado.
     * @return <code>true</code> si el formato del RFC y <code>false</code> en
     * caso de que el formato no se valido.
     */
    public static boolean validarRfc(String rfc) {
        if (esCadenaVacia(rfc)) {
            return false;
        }
        
        rfc = rfc.toUpperCase().trim();
        return rfc.matches(PATRON_RFC);
    }

    /**
     * Valida que el formato del CURP sea correcto.
     * <p>
     * En caso de que el CURP sea nulo o una cadena vacia se devolvera <code>false</code></p>
     *
     * @param curp el CURP que será validado.
     * @return <code>true</code> si el formato del CURP y <code>false</code> en
     * caso de que el formato no se valido.
     */
    public static boolean validarCurp(String curp) {
        if (esCadenaVacia(curp)) {
            return false;
        }

        curp = curp.toUpperCase().trim();
        return curp.matches(PATRON_CURP);
    }

    /**
     * Permite saber si el formato de una URL es valido. En caso de recibir una
     * cadena vacia o nula se devuelve <code>false</code>.
     *
     * @param url la URL que será validada.
     * @return true si y solo sí la URL es valida.
     * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
     */
    public static boolean esUrlValida(String url) {
        if (esCadenaVacia(url)) {
            return false;
        }

        Pattern pattern = Pattern.compile(PATRON_URL);
        Matcher matcher = pattern.matcher(url);

        return matcher.matches();
    }

}
