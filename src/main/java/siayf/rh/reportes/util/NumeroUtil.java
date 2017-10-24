/*
 * NumeroUtil.java
 * Creado el 28/jun/2016 2:15:26 PM
 * 
 */

package siayf.rh.reportes.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author Freddy Barrera
 */
public class NumeroUtil {

    public static final String PATRON_NUMERO_DECIMAL = "#0.00";
    public static final String PATRON_NUMERO_DECIMAL_CON_SEPARADOR = "#,##0.00";
    public static final String PATRON_NUMERO_MONEDAD = "¤ #0.00";
    public static final String PATRON_NUMERO_MONEDAD_CON_SEPARADOR = "¤ #,##0.00";

    private NumeroUtil() {
    }

    /**
     * <p>
     * Este método permite convertir en formato decimal, reduciendo la matiza a
     * dos digitos en caso de ser un número decimal; en caso de ser un número
     * entero le añade dos digitos en la matiza.
     * </p>
     *
     * <p>
     * Por ejemplo:<br>
     * <ul>
     * <li>1882 <strong>devuelve</strong> 1882.00</li>
     * <li>18.534534582 <strong>devuelve</strong> 18.53</li>
     * </ul>
     * </p>
     *
     * @param numero un número, incluso numero no decimales.
     * @return una cadena que con la representación del número en formato
     * decimal.
     */
    public static String formatoDecimal(Number numero) {
        return formatoMoneda(numero, false);
    }

    /**
     * <p>
     * Este método permite convertir en formato decimal, reduciendo la matiza a
     * dos digitos en caso de ser un número decimal; en caso de ser un número
     * entero le añade dos digitos en la matiza.
     * </p>
     *
     * <p>
     * Por ejemplo:<br>
     * <ul>
     * <li>1882 <strong>devuelve</strong> 1882.00</li>
     * <li>18.534534582 <strong>devuelve</strong> 18.53</li>
     * </ul>
     * </p>
     * 
     * <p>
     * Cuando se incluye el separador de miles.
     * <ul>
     * <li>1882 <strong>devuelve</strong> 1,882.00</li>
     * <li>18.534534582 <strong>devuelve</strong> 18.53</li>
     * <li>7852318.534534582 <strong>devuelve</strong> 7,852,318.53</li>
     * </ul>
     * </p>
     *
     * @param numero un número, incluso numero no decimales.
     * @param separador si se incluira el separdor de miles.
     * @return una cadena que con la representación del número en formato
     * decimal.
     */
    public static String formatoDecimal(Number numero, boolean separador) {
        String patron = separador ? PATRON_NUMERO_DECIMAL_CON_SEPARADOR : PATRON_NUMERO_DECIMAL;
        DecimalFormat formato = new DecimalFormat(patron);
        formato.setRoundingMode(RoundingMode.HALF_UP);

        return formato.format(numero);
    }

    /**
     * <p>
     * Este método permite convertir en formato moneda, reduciendo la matiza a
     * dos digitos en caso de ser un número decimal; en caso de ser un número
     * entero le añade dos digitos en la matiza.
     * </p>
     *
     * <p>
     * Por ejemplo:<br>
     * <ul>
     * <li>1882 <strong>devuelve</strong> $ 1882.00</li>
     * <li>18.534534582 <strong>devuelve</strong>$ 18.53</li>
     * </ul>
     * </p>
     *
     * @param numero un número, incluso número no decimales.
     * @return una cadena que con la representación del número en formato
     * decimal.
     */
    public static String formatoMoneda(Number numero) {
        return formatoMoneda(numero, false);
    }

    /**
     * <p>
     * Este método permite convertir en formato moneda, reduciendo la matiza a
     * dos digitos en caso de ser un número decimal; en caso de ser un número
     * entero le añade dos digitos en la matiza.
     * </p>
     *
     * <p>
     * Por ejemplo:<br>
     * <ul>
     * <li>1882 <strong>devuelve</strong> $ 1882.00</li>
     * <li>18.534534582 <strong>devuelve</strong>$ 18.53</li>
     * </ul>
     * </p>
     *
     * <p>
     * Cuando se incluye el separador de miles.
     * <ul>
     * <li>1882 <strong>devuelve</strong> $ 1,882.00</li>
     * <li>18.534534582 <strong>devuelve</strong> $ 18.53</li>
     * <li>7852318.534534582 <strong>devuelve</strong> $ 7,852,318.53</li>
     * </ul>
     *
     * @param numero un número, incluso número no decimales.
     * @param separador true para incluir el separador de miles.
     * @return una cadena que con la representación del número en formato
     * decimal.
     */
    public static String formatoMoneda(Number numero, boolean separador) {
        if (numero == null) {
            numero = BigDecimal.ZERO;
        }

        String patron = separador ? PATRON_NUMERO_MONEDAD_CON_SEPARADOR : PATRON_NUMERO_MONEDAD;
        DecimalFormat formato = new DecimalFormat(patron);
        formato.setRoundingMode(RoundingMode.HALF_UP);
        return formato.format(numero);
    }

    /**
     * Retorna un versión en BigDecimal del número que con redondeo.
     * 
     * @param numero el número que será redondeado.
     * @return el número redondeado.
     * 
     * @author Eduardo Chuc Mex
     */
    public static BigDecimal redondear(Number numero) {
        BigDecimal numeroRedondear;
        
        if(numero instanceof BigDecimal) {
            numeroRedondear = (BigDecimal) numero;
        } else if(numero instanceof Double) {
            Double numeroDouble = (Double) numero;
            numeroRedondear = new BigDecimal(numeroDouble);
        } else if(numero instanceof Float) {
            Float numeroFloat = (Float) numero;
            numeroRedondear = new BigDecimal(numeroFloat);
        } else {
            numeroRedondear = new BigDecimal(numero.toString());
        }
        
        return numeroRedondear.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

}
