/*
 * ProductoNominaFederalBean.java
 * Creado el 16/mar/2017 11:10:35 AM
 * 
 */

package siayf.rh.reportes.nomina.producto.federal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import siayf.rh.reportes.persistencia.consulta.ProductoNominaFederalQuery;
import siayf.rh.reportes.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class ProductoNominaFederalBean implements ProductoNominaFederal {

    private static final long serialVersionUID = -6429903585735894314L;
    private static final Logger LOGGER = Logger.getLogger(ProductoNominaFederalBean.class.getName());
    
    @Inject
    private ProductoNominaFederalQuery service;
    @Inject
    private ProductoNominaFederalExcel excelService;

    @Override
    public byte[] generarReporte(Integer idProductoNomina) {
        if(ValidacionUtil.esNumeroNegativo(idProductoNomina)) {
            throw new IllegalArgumentException("El ID del producto de nómina no puede ser cero o menor que uno.");
        }

        List<String> titulos = new ArrayList<>();
        List<Object[]> datos = new ArrayList<>();

        try {
            service.obtenerInformacion(idProductoNomina, titulos, datos);
            byte [] reporte = excelService.obtenerBytes(titulos, datos);
            titulos = null;
            datos = null;
            return reporte;
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

}
