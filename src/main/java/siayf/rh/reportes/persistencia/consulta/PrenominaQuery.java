/*
 * PrenominaQuery.java
 * Creado el 05/jul/2017 12:28:37 PM
 *
 */

package siayf.rh.reportes.persistencia.consulta;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import siayf.rh.reportes.core.AplicacionConstantes;
import siayf.rh.reportes.nomina.prenomina.DeduccionDto;
import siayf.rh.reportes.nomina.prenomina.NominaEmpleadoDto;
import siayf.rh.reportes.nomina.prenomina.PercepcionDto;
import siayf.rh.reportes.nomina.prenomina.PrenominaDto;
import siayf.rh.reportes.nomina.prenomina.ProductoNominaDto;
import siayf.rh.reportes.nomina.prenomina.ProgramaDto;
import siayf.rh.reportes.nomina.prenomina.UnidadResponsableDto;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class PrenominaQuery {

    private static final Logger LOGGER = Logger.getLogger(PrenominaQuery.class.getName());

    @PersistenceContext(unitName = AplicacionConstantes.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    private static final String USP_REPORTE_PRENOMINA
            = "CALL usp_reporte_prenomina(:idProductoNomina)";

    public ProductoNominaDto obtenerProductoNomina(Integer idProductoNomina) {
        List<PrenominaDto> prenominas = consultarDatos(idProductoNomina);
        ProductoNominaDto productoNomina = convertirDatos(idProductoNomina, prenominas);
        LOGGER.fine(productoNomina.toString());
        return productoNomina;
    }

    private List<PrenominaDto> consultarDatos(Integer idProductoNomina) {
        Query consulta = entityManager.createNativeQuery(USP_REPORTE_PRENOMINA);
        consulta.setParameter("idProductoNomina", idProductoNomina);

        List<Object[]> resultado = consulta.getResultList();
        List<PrenominaDto> prenominas = new ArrayList<>();

        for (Object[] obj : resultado) {
            PrenominaDto prenomina = new PrenominaDto();

            // Elaboro
            if (obj[0] instanceof String) {
                String elaboroNombre = (String) obj[0];
                prenomina.setElaboroNombre(elaboroNombre);
            }

            if (obj[1] instanceof String) {
                String elaboroCargo = (String) obj[1];
                prenomina.setElaboroCargo(elaboroCargo);
            }

            // Reviso
            if (obj[2] instanceof String) {
                String revisoNombre = (String) obj[2];
                prenomina.setRevisoNombre(revisoNombre);
            }

            if (obj[3] instanceof String) {
                String revisoCargo = (String) obj[3];
                prenomina.setRevisoCargo(revisoCargo);
            }

            // Autorizo
            if (obj[4] instanceof String) {
                String autorizoNombre = (String) obj[4];
                prenomina.setAutorizoNombre(autorizoNombre);
            }

            if (obj[5] instanceof String) {
                String autorizoCargo = (String) obj[5];
                prenomina.setAutorizoCargo(autorizoCargo);
            }

            if (obj[6] instanceof Date) {
                Date fechaPago = (Date) obj[6];
                prenomina.setFechaPago(fechaPago);
            }

            if (obj[7] instanceof Integer) {
                Integer idPro = (Integer) obj[7];
                prenomina.setIdProductoNomina(idPro);
            }

            if (obj[8] instanceof Integer) {
                Integer idPrograma = (Integer) obj[8];
                prenomina.setIdPrograma(idPrograma);
            } else if (obj[8] instanceof BigInteger) {
                Integer idPrograma = ((BigInteger) obj[8]).intValue();
                prenomina.setIdPrograma(idPrograma);
            }

            if (obj[9] instanceof String) {
                String programa = (String) obj[9];
                prenomina.setPrograma(programa);
            }

            if (obj[10] instanceof Date) {
                Date incioPeriodo = (Date) obj[10];
                prenomina.setInicioPeriodo(incioPeriodo);
            }

            if (obj[11] instanceof Date) {
                Date finPeriodo = (Date) obj[11];
                prenomina.setFinPeriodo(finPeriodo);
            }

            if (obj[12] instanceof String) {
                String claveCentroResponsabilidad = (String) obj[12];
                prenomina.setClaveCentroResponsabilidad(claveCentroResponsabilidad);
            }

            if (obj[13] instanceof String) {
                String descripcionCentroResponsabilidad = (String) obj[13];
                prenomina.setDescripcionCentroResponsabilidad(descripcionCentroResponsabilidad);
            }

            if (obj[14] instanceof String) {
                String rfc = (String) obj[14];
                prenomina.setRfc(rfc);
            }

            if (obj[15] instanceof String) {
                String nombre = (String) obj[15];
                prenomina.setNombre(nombre);
            }

            if (obj[16] instanceof String) {
                String claveConcepto = (String) obj[16];
                prenomina.setClaveConcepto(claveConcepto);
            }

            if (obj[17] instanceof String) {
                String descripcionConcepto = (String) obj[17];
                prenomina.setDescripcionConcepto(descripcionConcepto);
            }

            if (obj[18] instanceof BigDecimal) {
                BigDecimal importe = (BigDecimal) obj[18];
                prenomina.setImporte(importe);
            }

            if (obj[19] instanceof BigDecimal) {
                BigDecimal total = (BigDecimal) obj[19];
                prenomina.setTotal(total);
            }

            if (obj[20] instanceof String) {
                String tipo = (String) obj[20];
                prenomina.setTipo(tipo);
            }

            prenominas.add(prenomina);
        }

        for (PrenominaDto prenomina : prenominas) {
            LOGGER.fine(prenomina.toString());
        }

        return prenominas;
    }

    private ProductoNominaDto convertirDatos(Integer idProductoNomina, List<PrenominaDto> prenominas) {
        if (prenominas != null && !prenominas.isEmpty()) {
            Map<Integer, ProgramaDto> programas = new HashMap<>();

            for (PrenominaDto prenomina : prenominas) {
                Integer idPrograma = prenomina.getIdPrograma();
                if (!programas.containsKey(idPrograma)) {

                    NominaEmpleadoDto nominaEmpleado = new NominaEmpleadoDto();
                    nominaEmpleado.setNombre(prenomina.getNombre());
                    nominaEmpleado.setRfc(prenomina.getRfc());

                    if ("PERCEPCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                        Map<String, PercepcionDto> percepciones = new HashMap<>();
                        PercepcionDto percepcion = new PercepcionDto(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                        percepciones.put(prenomina.getClaveConcepto(), percepcion);
                        nominaEmpleado.setPercepciones(percepciones);
                    }

                    if ("DEDUCCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                        DeduccionDto deduccion = new DeduccionDto(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                        Map<String, DeduccionDto> deducciones = new HashMap<>();
                        deducciones.put(prenomina.getClaveConcepto(), deduccion);
                        nominaEmpleado.setDeducciones(deducciones);
                    }

                    Map<String, NominaEmpleadoDto> nominasEmpleados = new HashMap<>();
                    nominasEmpleados.put(nominaEmpleado.getRfc(), nominaEmpleado);
                    
                    UnidadResponsableDto.Builder unidadResponsableBuilder = new UnidadResponsableDto.Builder(prenomina.getClaveCentroResponsabilidad(), prenomina.getDescripcionCentroResponsabilidad())
                            .setNominasEmpleados(nominasEmpleados);
                    UnidadResponsableDto unidadResponsable = unidadResponsableBuilder.createUnidadResponsableDTO();

                    Map<String, UnidadResponsableDto> unidadesResponsables = new HashMap<>();
                    unidadesResponsables.put(unidadResponsable.getNumeroUnidadResponsable(), unidadResponsable);

                    ProgramaDto.Builder programaBuilder = new ProgramaDto.Builder(idPrograma, prenomina.getPrograma(), prenomina.getInicioPeriodo(), prenomina.getFinPeriodo())
                            .setUnidadesResponsables(unidadesResponsables);
                    ProgramaDto programa = programaBuilder.createProgramaDTO();
                    programas.put(idPrograma, programa);
                } else {
                    ProgramaDto programa = programas.get(idPrograma);
                    Map<String, UnidadResponsableDto> unidadesResponsables = programa.getUnidadesResponsables();

                    if (!unidadesResponsables.containsKey(prenomina.getClaveCentroResponsabilidad())) {


                        NominaEmpleadoDto nominaEmpleado = new NominaEmpleadoDto();
                        nominaEmpleado.setNombre(prenomina.getNombre());
                        nominaEmpleado.setRfc(prenomina.getRfc());

                        if ("PERCEPCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                            Map<String, PercepcionDto> percepciones = new HashMap<>();
                            PercepcionDto percepcion = new PercepcionDto(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                            percepciones.put(prenomina.getClaveConcepto(), percepcion);
                            nominaEmpleado.setPercepciones(percepciones);
                        }

                        if ("DEDUCCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                            DeduccionDto deduccion = new DeduccionDto(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                            Map<String, DeduccionDto> deducciones = new HashMap<>();
                            deducciones.put(prenomina.getClaveConcepto(), deduccion);
                            nominaEmpleado.setDeducciones(deducciones);
                        }

                        Map<String, NominaEmpleadoDto> nominasEmpleados = new HashMap<>();
                        nominasEmpleados.put(nominaEmpleado.getRfc(), nominaEmpleado);

                        UnidadResponsableDto.Builder unidadResponsableBuilder = new UnidadResponsableDto.Builder(prenomina.getClaveCentroResponsabilidad(), prenomina.getDescripcionCentroResponsabilidad())
                                .setNominasEmpleados(nominasEmpleados);
                        UnidadResponsableDto unidadResponsable = unidadResponsableBuilder.createUnidadResponsableDTO();
                        unidadesResponsables.put(unidadResponsable.getNumeroUnidadResponsable(), unidadResponsable);
                    } else {
                        UnidadResponsableDto unidadResponsable = unidadesResponsables.get(prenomina.getClaveCentroResponsabilidad());
                        Map<String, NominaEmpleadoDto> nominasEmpleados = unidadResponsable.getNominasEmpleados();

                        if (!nominasEmpleados.containsKey(prenomina.getRfc())) {
                            NominaEmpleadoDto nominaEmpleado = new NominaEmpleadoDto();
                            nominaEmpleado.setNombre(prenomina.getNombre());
                            nominaEmpleado.setRfc(prenomina.getRfc());

                            if ("PERCEPCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                                Map<String, PercepcionDto> percepciones = new HashMap<>();
                                PercepcionDto percepcion = new PercepcionDto(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                                percepciones.put(prenomina.getClaveConcepto(), percepcion);
                                nominaEmpleado.setPercepciones(percepciones);
                            }

                            if ("DEDUCCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                                DeduccionDto deduccion = new DeduccionDto(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                                Map<String, DeduccionDto> deducciones = new HashMap<>();
                                deducciones.put(prenomina.getClaveConcepto(), deduccion);
                                nominaEmpleado.setDeducciones(deducciones);
                            }

                            nominasEmpleados.put(nominaEmpleado.getRfc(), nominaEmpleado);
                        } else {
                            NominaEmpleadoDto nominaEmpleado = nominasEmpleados.get(prenomina.getRfc());

                            if ("PERCEPCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                                if (nominaEmpleado.getPercepciones() != null) {
                                    Map<String, PercepcionDto> percepciones = nominaEmpleado.getPercepciones();
                                    PercepcionDto percepcion = new PercepcionDto(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                                    percepciones.put(prenomina.getClaveConcepto(), percepcion);
                                } else {
                                    Map<String, PercepcionDto> percepciones = new HashMap<>();
                                    PercepcionDto percepcion = new PercepcionDto(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                                    percepciones.put(prenomina.getClaveConcepto(), percepcion);
                                    nominaEmpleado.setPercepciones(percepciones);
                                }

                            }

                            if ("DEDUCCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                                if(nominaEmpleado.getDeducciones() != null) {
                                    Map<String, DeduccionDto> deducciones = nominaEmpleado.getDeducciones();
                                    DeduccionDto deduccion = new DeduccionDto(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                                    deducciones.put(prenomina.getClaveConcepto(), deduccion);
                                } else {
                                    DeduccionDto deduccion = new DeduccionDto(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                                    Map<String, DeduccionDto> deducciones = new HashMap<>();
                                    deducciones.put(prenomina.getClaveConcepto(), deduccion);
                                    nominaEmpleado.setDeducciones(deducciones);
                                }
                            }
                        }
                    }
                }
            }

            PrenominaDto prenominaUno = prenominas.get(0);
            Date fechaPago = prenominaUno.getFechaPago();
            ProductoNominaDto productoNomina;
            ProductoNominaDto.Builder pnb = new ProductoNominaDto.Builder(idProductoNomina, fechaPago, programas)
                    //Firmas
                    .setNombreElaboro(prenominaUno.getElaboroNombre())
                    .setCargoElaboro(prenominaUno.getElaboroCargo())
                    .setNombreReviso(prenominaUno.getRevisoNombre())
                    .setCargoReviso(prenominaUno.getRevisoCargo())
                    .setNombreAutorizo(prenominaUno.getAutorizoNombre())
                    .setCargoAutorizo(prenominaUno.getAutorizoCargo());

            productoNomina = pnb.createProductoNominaDTO();

            return productoNomina;
        } else {
            return null;
        }
    }
}
