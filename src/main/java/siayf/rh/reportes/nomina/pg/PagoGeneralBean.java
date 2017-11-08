/*
 * PagoGeneralBean.java
 * Creado el 15/feb/2017 5:20:53 AM
 * 
 */

package siayf.rh.reportes.nomina.pg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import siayf.rh.reportes.persistencia.consulta.PagoGeneralQuery;
import siayf.rh.reportes.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class PagoGeneralBean implements PagoGeneral {

    private static final long serialVersionUID = 3687555233714779856L;
    private static final Logger LOGGER = Logger.getLogger(PagoGeneralBean.class.getName());

    @Inject
    private PagoGeneralQuery pagoGeneralService;
    @Inject
    private PagoGeneralExcel pagoGeneralExcelService;

    @Override
    public byte [] generarReporte(Object... parametros) {
        Integer idProductoNomina = (Integer) parametros[0];
        
        if(ValidacionUtil.esNumeroNegativo(idProductoNomina)) {
            throw new IllegalArgumentException("El ID del producto de nomina no puede ser cero o menor que uno");
        }

        List<String> titulos = new ArrayList<>();
        List<Object[]> datos = new ArrayList<>();

        try {
            pagoGeneralService.obtenerInformacion(idProductoNomina, titulos, datos);
            return pagoGeneralExcelService.obtenerBytes(titulos, datos);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

}
