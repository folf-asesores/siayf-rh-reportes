/*
 * ComprobanteEmpleadoQuery.java
 * Creado el 22/nov/2016 4:36:59 AM
 * 
 */

package siayf.rh.reportes.persistencia.consulta;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import siayf.rh.reportes.core.AplicacionConstantes;
import siayf.rh.reportes.nomina.comprobante.ComprobanteEmpleadoPojo;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ComprobanteEmpleadoQuery implements Serializable {

    private static final long serialVersionUID = -1006356522264912212L;

    private static final String OBTENER_COMPROBANTES
            = "CALL usp_nomina_comprobante_empleado(:idProductoNomina)";

    @PersistenceContext(unitName = AplicacionConstantes.UNIDAD_PERSISTENCIA)
    protected EntityManager em;
    
    public List<ComprobanteEmpleadoPojo> obtenerDatos(Integer idProductoNomina) {
        try {
            Session session = em.unwrap(Session.class);
            Query query = session.createSQLQuery(OBTENER_COMPROBANTES);
            query.setParameter("idProductoNomina", idProductoNomina);
            query.setResultTransformer(Transformers.aliasToBean(ComprobanteEmpleadoPojo.class));
            List<ComprobanteEmpleadoPojo> resultado = (List<ComprobanteEmpleadoPojo>) query.list();

            return resultado;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
}
