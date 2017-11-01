/*
 * ProductoNominaBean.java
 * Creado el 01/nov/2017 6:42:00 AM
 * 
 */
package siayf.rh.reportes.nomina.productonomina;

import javax.ejb.Stateless;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class ProductoNominaBean implements ProductoNomina {

    private static final long serialVersionUID = 1680573382901775168L;

    @Override
    public byte[] generarReporte(Object... parametros) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
