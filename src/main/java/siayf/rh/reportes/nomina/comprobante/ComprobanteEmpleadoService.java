/*
 * ComprobanteEmpleadoService.java
 * Creado el 22/nov/2016 4:25:46 AM
 * 
 */

package siayf.rh.reportes.nomina.comprobante;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import siayf.rh.reportes.persistencia.consulta.ComprobanteEmpleadoQuery;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ComprobanteEmpleadoService implements Serializable {

    private static final long serialVersionUID = 75388205920079865L;

    @Inject private ComprobanteEmpleadoQuery comprobateEmpleadoRepository;

    protected byte[] generarReporte(Integer idProductoNomina) {
        List<ComprobanteEmpleadoPojo> datosBrutos = comprobateEmpleadoRepository.obtenerDatos(idProductoNomina);
        List<ComprobanteEmpleadoDto> datosTratados = convertir(datosBrutos);
        ComprobanteEmpleadoMotor per = new ComprobanteEmpleadoMotor();

        return per.obtenerArchivo(datosTratados);
    }

    private List<ComprobanteEmpleadoDto> convertir(List<ComprobanteEmpleadoPojo> datosBrutos) {
        Map<String, List<Integer>> iteradores = new HashMap<>();

        for (int i = 0; i < datosBrutos.size(); i++) {
            ComprobanteEmpleadoPojo datoBruto = datosBrutos.get(i);

            if(!iteradores.containsKey(datoBruto.getFiliacion())) {
                List<Integer> indices = new ArrayList<>();
                indices.add(i);
                iteradores.put(datoBruto.getFiliacion(), indices);
            } else {
                List<Integer> indices = iteradores.get(datoBruto.getFiliacion());
                indices.add(i);
            }
        }

        List<ComprobanteEmpleadoDto> datosTratados = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> iterador : iteradores.entrySet()) {
            ComprobanteEmpleadoDto datoTratado = new ComprobanteEmpleadoDto();
            List<Integer> indices = iterador.getValue();
            List<ConceptoComprobanteDto> conceptos = new ArrayList<>();

            for (int i = 0; i < indices.size(); i++) {
                Integer indice = indices.get(i);
                ComprobanteEmpleadoPojo datoBruto = datosBrutos.get(indice);

                if (i == 0) {
                    datoTratado.setNombre(datoBruto.getNombre());
                    datoTratado.setFiliacion(datoBruto.getFiliacion());
                    datoTratado.setFechaPago(datoBruto.getFechaPago());
                    datoTratado.setClaveCentroResponsabilidad(datoBruto.getClaveCentroResposabilidad());
                    datoTratado.setNumeroCheque(datoBruto.getNumeroCheque());
                    datoTratado.setInicioPeriodo(datoBruto.getInicioPeriodo());
                    datoTratado.setFinPeriodo(datoBruto.getFinPeriodo());
                    datoTratado.setPercepciones(datoBruto.getPercepciones());
                    datoTratado.setDeducciones(datoBruto.getDeducciones());
                    datoTratado.setNeto(datoBruto.getNeto());
                    datoTratado.setConceptos(conceptos);

                    conceptos.add(new ConceptoComprobanteDto(Short.valueOf(datoBruto.getClave() == null ? "0" : datoBruto.getClave().trim()), datoBruto.getImporte()));
                } else {
                    conceptos.add(new ConceptoComprobanteDto(Short.valueOf(datoBruto.getClave() == null ? "0" : datoBruto.getClave().trim()), datoBruto.getImporte()));
                }
            }

            Collections.sort(conceptos);
            datosTratados.add(datoTratado);
        }

        Collections.sort(datosTratados);
        return datosTratados;
    }

}
