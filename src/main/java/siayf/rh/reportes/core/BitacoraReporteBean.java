/*
 * BitacoraReporteBean.java
 * Creado el 9/sep/2016 1:37:04 PM
 * 
 */

package siayf.rh.reportes.core;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import siayf.rh.reportes.persistencia.entidad.BitacoraReporteEntity;
import siayf.rh.reportes.persistencia.entidad.ReporteParametroEntity;
import siayf.rh.reportes.persistencia.entidad.UsuarioEntity;
import siayf.rh.reportes.persistencia.repositorio.BitacoraReporteRepository;
import siayf.rh.reportes.persistencia.repositorio.UsuarioRepository;

/**
 * Este EJB ayuda a registrar en la bitácora la información de generación de los
 * reportes.
 * 
 * @author Freddy Barrera
 * @author Eduardo Chuc Mex
 */
@Stateless
public class BitacoraReporteBean implements BitacoraReporte {
    
    private static final Logger LOGGER = Logger.getLogger(BitacoraReporteBean.class.getName());

    @Inject
    private BitacoraReporteRepository bitacoraReporteRepository;
    @Inject
    private UsuarioRepository usuarioRepository;
        
    @Override
    public String obtenerReferencia(Map<String, String> parametros) {
        if(parametros == null || parametros.isEmpty()) {
            throw new NullPointerException("No se puede generar un reporte sin parametros");
        }

        BitacoraReporteEntity entidad = new BitacoraReporteEntity();
        UsuarioEntity  usuario = usuarioRepository.obtenerPorId(Integer.parseInt(parametros.get("ID_USUARIO")));
        String nombreReporte = parametros.get("REPORTE_NOMBRE");

        entidad.setUsuario(usuario);
        entidad.setNombreReporte(nombreReporte);

        parametros.remove("ID_USUARIO");
        parametros.remove("REPORTE_NOMBRE");

        Set<ReporteParametroEntity> reporteParametros = new HashSet<>();

        for (Map.Entry<String, String> entry : parametros.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            ReporteParametroEntity reporteParametro = new ReporteParametroEntity();

            reporteParametro.setIdReporteParametro(null);
            reporteParametro.setClave(key);
            reporteParametro.setValor(value);
            reporteParametro.setBitacoraReporte(entidad);

            reporteParametros.add(reporteParametro);
        }

        String uuid = UUID.randomUUID().toString();
        entidad.setIdReferencia(uuid);
        Date fecha = Calendar.getInstance().getTime();
        entidad.setFechaGeneracion(fecha);
        //entidad.setHoraGeneracion(fecha);

        entidad.setReporteParametros(reporteParametros);

        String id = bitacoraReporteRepository.crear(entidad).getIdReferencia();
        return id;
    }

    @Override
    public Map<String, String> obtenerParametros(String referencia) {
        Map<String, String> parametros = new HashMap<>();
        BitacoraReporteEntity bitacoraReporteEntity = bitacoraReporteRepository.obtenerPorId(referencia);
        
        if (bitacoraReporteEntity == null) {
            LOGGER.warning("No se encontro la entidad");
            return Collections.emptyMap();
        }
        
        parametros.put("ID_USUARIO", String.valueOf(bitacoraReporteEntity.getUsuario().getIdUsuario()));
        parametros.put("REPORTE_NOMBRE", bitacoraReporteEntity.getNombreReporte());
        
        for (ReporteParametroEntity reporteParametroEntity : bitacoraReporteEntity.getReporteParametros()) {
            String key = reporteParametroEntity.getClave();
            String value = reporteParametroEntity.getValor();
            
            parametros.put(key, value);
        }
        
        return parametros;
    }

}
