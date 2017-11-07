/*
 * FirmaService.java
 * Creado el 11/sep/2017 12:51:19 PM
 * 
 */

package siayf.rh.reportes.nomina.firma;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import siayf.rh.reportes.persistencia.consulta.FirmaReporteQuery;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class FirmaService {

    @Inject
    private FirmaReporteQuery firmaQuery;

    public FirmaDto obtenerFirmaEmpleado(Integer idProductoNomina) {
        List<FirmaPojo> datos = firmaQuery.obtenerDatos(idProductoNomina);
        FirmaDto firma = convertir(datos);
        
        return firma;
    }
    
    private FirmaDto convertir(List<FirmaPojo> datosBrutos) {
        if (datosBrutos != null && !datosBrutos.isEmpty()) {
            Map<String, UnidadResponsableDto> unidadesResponsables = new TreeMap<>();

            for (FirmaPojo firmaPojo : datosBrutos) {
                String numeroUnidadResponsable = firmaPojo.getClave();
                
                if (!unidadesResponsables.containsKey(numeroUnidadResponsable)) {
                    FirmaEmpleadoDto firmaEmpleado = new FirmaEmpleadoDto.Builder()
                            .setFiliacion(firmaPojo.getFiliacion())
                            .setNombre(firmaPojo.getNombre())
                            .setNumeroCheque(firmaPojo.getNumeroCheque())
                            .setImporte(firmaPojo.getNeto())
                            .construirFirmaEmpleadoDTO();
                    Map<String, FirmaEmpleadoDto> firmasEmpleados = new TreeMap<>();
                    firmasEmpleados.put(firmaEmpleado.getFiliacion(), firmaEmpleado);
                    
                    Map<Integer, ProgramaDto> programas = new TreeMap<>();
                    Integer idPrograma = firmaPojo.getIdPrograma();
                    ProgramaDto programa = new ProgramaDto.Builder(idPrograma, firmaPojo.getPrograma(), firmaPojo.getInicioPeriodo(), firmaPojo.getFinPeriodo())
                            .setFirmasEmpleados(firmasEmpleados)
                            .construirProgramaDTO();
                    programas.put(idPrograma, programa);
                
                    UnidadResponsableDto unidadResponsable = new UnidadResponsableDto.Builder(firmaPojo.getClave(), firmaPojo.getDescripcion())
                            .setProgramas(programas)
                            .construirUnidadResponsableDTO();
                    
                    unidadesResponsables.put(unidadResponsable.getNumeroUnidadResponsable(), unidadResponsable);
                } else {
                    UnidadResponsableDto unidadResponsable = unidadesResponsables.get(numeroUnidadResponsable);
                    Map<Integer, ProgramaDto> programas = unidadResponsable.getProgramas();
                    Integer idPrograma = firmaPojo.getIdPrograma();

                    if (!programas.containsKey(idPrograma)) {
                        FirmaEmpleadoDto firmaEmpleado = new FirmaEmpleadoDto.Builder()
                                .setFiliacion(firmaPojo.getFiliacion())
                                .setNombre(firmaPojo.getNombre())
                                .setNumeroCheque(firmaPojo.getNumeroCheque())
                                .setImporte(firmaPojo.getNeto())
                                .construirFirmaEmpleadoDTO();
                        Map<String, FirmaEmpleadoDto> firmasEmpleados = new TreeMap<>();
                        firmasEmpleados.put(firmaEmpleado.getFiliacion(), firmaEmpleado);
                        
                        ProgramaDto programa = new ProgramaDto.Builder(idPrograma, firmaPojo.getPrograma(), firmaPojo.getInicioPeriodo(), firmaPojo.getFinPeriodo())
                                .setFirmasEmpleados(firmasEmpleados)
                                .construirProgramaDTO();
                        programas.put(idPrograma, programa);
                    } else {
                        ProgramaDto programa = programas.get(idPrograma);
                        Map<String, FirmaEmpleadoDto> firmasEmpleados = programa.getFirmasEmpleados();
                        FirmaEmpleadoDto firmaEmpleado = new FirmaEmpleadoDto.Builder()
                                .setFiliacion(firmaPojo.getFiliacion())
                                .setNombre(firmaPojo.getNombre())
                                .setNumeroCheque(firmaPojo.getNumeroCheque())
                                .setImporte(firmaPojo.getNeto())
                                .construirFirmaEmpleadoDTO();
                        firmasEmpleados.put(firmaEmpleado.getFiliacion(), firmaEmpleado);
                    }
                }
            }

            FirmaPojo firmaPojo = datosBrutos.get(0);
            FirmaDto firma = new FirmaDto.Builder(firmaPojo.getIdProductoNomina(), firmaPojo.getFechaPago(), unidadesResponsables)
                    // Elaboro
                    .setNombreElaboro(firmaPojo.getJefe1Nombre())
                    .setCargoElaboro(firmaPojo.getJefe1Cargo())
                    // Reviso
                    .setNombreReviso(firmaPojo.getJefe2Nombre())
                    .setCargoReviso(firmaPojo.getJefe2Cargo())
                    // Autorizo
                    .setNombreAutorizo(firmaPojo.getJefe3Nombre())
                    .setCargoAutorizo(firmaPojo.getJefe3Cargo())
                    .construirFirmaDTO();

            return firma;
        } else {
            return null;
        }
    }

}
