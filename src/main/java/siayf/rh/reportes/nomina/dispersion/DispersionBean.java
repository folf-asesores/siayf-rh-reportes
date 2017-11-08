/*
 * DispersionBean.java
 * Creado el 07/dic/2016 6:34:40 PM
 *
 */

package siayf.rh.reportes.nomina.dispersion;

import siayf.rh.reportes.persistencia.consulta.DispersionService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import siayf.rh.reportes.util.CadenaUtil;
import siayf.rh.reportes.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class DispersionBean implements Dispersion {

    private static final long serialVersionUID = 1691448290310983411L;
    private static final Logger LOGGER = Logger.getLogger(DispersionBean.class.getName());

    @Inject private DispersionService dispersionService;
    @Inject private DispersionTextoPlano dispersionTxt;
    @Inject private DispersionExcel dispersionExcel;

    @Override
    public byte[] generarReporte(Object ... parametros) {
        if (parametros != null && parametros.length == 1) {
            Integer idProductoNomina = (Integer) parametros[0];

            return generarReporte(idProductoNomina);
        } else if (parametros != null && parametros.length == 2) {
            Integer idProductoNomina = (Integer) parametros[0];
            Boolean excel = (Boolean) parametros[1];

            return generarReporte(idProductoNomina, excel);
        } else {
            return null;
        }
    }
    
    /**
     * Permite generar el reporte de dispersión de nómina apartir del ID del
     * producto de nómina.
     *
     * @param idProductoNomina el ID del producto de nómina.
     * @return un arreglo de bytes que representan el archivo de texto plano con
     * el reporte de dispersión de nómina.
     */
    private byte[] generarReporte(Integer idProductoNomina) {
        return generarReporte(idProductoNomina, false);
    }

    /**
     * Permite generar el reporte de dispersión de nómina apartir del ID del
     * producto de nómina.
     * 
     * @param idProductoNomina el ID del producto de nómina.
     * @param excel si el archivo se generará como un archivo de Excel
     * @return un arreglo de bytes que representan el archivo de Excel con
     *         el reporte de dispersión de nómina.
     */
    private byte[] generarReporte(Integer idProductoNomina, boolean excel) {
        if(ValidacionUtil.esNumeroNegativo(idProductoNomina)) {
            throw new IllegalArgumentException("El ID del producto de nomina no puede ser cero o menor que uno");
        }

        List<Integer> idPagoNominaList = dispersionService.obtenerIdPagoNominaList(idProductoNomina);
        List<List<DispersionDto>> dispersionList = new ArrayList<>();
        for (Integer idPagoNomina :idPagoNominaList) {
	        List<DispersionDto> dispersion = dispersionService.obtenerInformacion(idPagoNomina);
	        for (DispersionDto dispersionDTO : dispersion) {
	            String nombreEmpleado = CadenaUtil.remplazarCaracteresLatinos(dispersionDTO.getNombreEmpleado());
	            dispersionDTO.setNombreEmpleado(nombreEmpleado);
	        }
	        dispersionList.add(dispersion);
        }

        if (excel) {
            try {
                return dispersionExcel.obtenerBytes(dispersionList);
            } catch (IOException ex) {
                LOGGER.severe(ex.getMessage());
                return null;
            }
        } else {
        	for (List<DispersionDto> dispersion: dispersionList) {
        		dispersionTxt.obtenerReporte(dispersion);
        	}
            return null;
        }
    }


}
