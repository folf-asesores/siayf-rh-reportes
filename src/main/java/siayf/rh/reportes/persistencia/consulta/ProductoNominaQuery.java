/*
 * ProductoNominaQuery.java
 * Creado el 05/nov/2017 6:46:31 PM
 * 
 */

package siayf.rh.reportes.persistencia.consulta;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import siayf.rh.reportes.core.AplicacionConstantes;
import siayf.rh.reportes.nomina.producto.estatal.ProductoNominaEstatalDto;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ProductoNominaQuery {
    
    @PersistenceContext(unitName = AplicacionConstantes.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;
    
    private static final String OBTENER_PRODUCTO_NOMINA_PROGRAMAS_POR_ID_PRODUCTO_DE_NOMINA
            = "CALL usp_productos_nominas_programas(:idProducto)";
    private static final String OBTENER_NOMBRE_PROGRAMAS_POR_ID_PRODUCTO_DE_NOMINA
            = "CALL usp_productos_nominas_num_programas(:idProducto)";
    private static final String OBTENER_FIN_PERIODO_POR_ID_PRODUCTO_DE_NOMINA
            = "select productoNomina.finPeriodo"
            + "  from ProductoNominaEntity as productoNomina"
            + " where productoNomina.idProductoNomina = :idProductoNomina";

    public List<ProductoNominaEstatalDto> obtenerProductoNominaProgramasPorIdProducto(Integer idProductoNomina) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(OBTENER_PRODUCTO_NOMINA_PROGRAMAS_POR_ID_PRODUCTO_DE_NOMINA);
        query.setParameter("idProducto", idProductoNomina);
        query.setResultTransformer(Transformers.aliasToBean(ProductoNominaEstatalDto.class));
        List<ProductoNominaEstatalDto> detalles = query.list();

        return detalles;
    }
    
    public List<String> obtenerNombreProgramasPorIdProducto(Integer idProductoNomina) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(OBTENER_NOMBRE_PROGRAMAS_POR_ID_PRODUCTO_DE_NOMINA);
        query.setParameter("idProducto", idProductoNomina);
        List<String> list = query.list();

        return list;
    }
    
    public Date obtenerFinPeridoPorIdProducto(Integer idProductoNomina) {
        TypedQuery<Date> query = entityManager.createQuery(OBTENER_FIN_PERIODO_POR_ID_PRODUCTO_DE_NOMINA, Date.class);
        query.setParameter("idProductoNomina", idProductoNomina);
        query.setMaxResults(1);
        Date result = query.getSingleResult();

        return result;
    }
}
