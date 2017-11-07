/*
 * DispersionService.java
 * Creado el 07/dic/2016 8:10:11 PM
 *
 */

package siayf.rh.reportes.persistencia.consulta;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import siayf.rh.reportes.core.AplicacionConstantes;
import siayf.rh.reportes.nomina.dispersion.DispersionDto;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class DispersionService implements Serializable {

    private static final long serialVersionUID = 7738321309668550989L;

    private static final String USP_REPORTE_NOMINA_DISPERCION = "CALL usp_reporte_nomina_dispersion(:idPagoNomina)";
    private static final String OBTENER_ID_PAGO_NOMINA
            = "   SELECT n.id_pago_nomina"
            + "     FROM nomina_empleado AS n"
            + "    WHERE n.id_producto_nomina = :idProductoNomina"
            + " GROUP BY n.id_pago_nomina";

    @PersistenceContext(unitName = AplicacionConstantes.UNIDAD_PERSISTENCIA)
    private EntityManager em;

    public List<Integer> obtenerIdPagoNominaList(Integer idProductoNomina) {
        Session sesion = em.unwrap(Session.class);
        Query query = sesion.createSQLQuery(OBTENER_ID_PAGO_NOMINA);
        query.setParameter("idProductoNomina", idProductoNomina);
        List<Integer> idPagoNominaList = (List<Integer>) query.list();

        return idPagoNominaList == null ? Collections.EMPTY_LIST : idPagoNominaList;
    }

    public List<DispersionDto> obtenerInformacion(Integer idPagoNomina) {
        Session sesion = em.unwrap(Session.class);
        Query query = sesion.createSQLQuery(USP_REPORTE_NOMINA_DISPERCION);
        query.setParameter("idPagoNomina", idPagoNomina);
        query.setResultTransformer(Transformers.aliasToBean(DispersionDto.class));
        List<DispersionDto> dispersion = (List<DispersionDto>) query.list();

        return dispersion == null ? Collections.EMPTY_LIST : dispersion;
    }
}
