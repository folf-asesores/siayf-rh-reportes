/*
 * ProductoNominaQuery.java
 * Creado el 05/nov/2017 6:46:31 PM
 * 
 */

package siayf.rh.reportes.persistencia.consulta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import siayf.rh.reportes.core.AplicacionConstantes;
import siayf.rh.reportes.nomina.producto.ProductoNominaDto;
import siayf.rh.reportes.nomina.producto.ProductoNominaProgramaDto;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ProductoNominaQuery {
    
    @PersistenceContext(unitName = AplicacionConstantes.UNIDAD_PERSISTENCIA_ESPEJO)
    private EntityManager entityManager;
    
    private static final String OBTENER_PRODUCTO_NOMINA_PROGRAMAS_POR_ID_PRODUCTO_DE_NOMINA
            = "CALL usp_productos_nominas_programas(:idProducto)";
    private static final String OBTENER_NOMBRE_PROGRAMAS_POR_ID_PRODUCTO_DE_NOMINA
            = "CALL usp_productos_nominas_num_programas(:idProducto)";
    private static final String OBTENER_FIN_PERIODO_Y_EJERCICIO_POR_ID_PRODUCTO_DE_NOMINA
            = "SELECT fin_periodo AS finPeriodo,"
            + "       ejercicio_fiscal AS ejercicioFiscal"
            + "  FROM productos_nomina "
            + "WHERE id_producto_nomina = (:idProducto)";

    public List<ProductoNominaProgramaDto> obtenerProductoNominaProgramasPorIdProducto(Integer idProductoNomina) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(OBTENER_PRODUCTO_NOMINA_PROGRAMAS_POR_ID_PRODUCTO_DE_NOMINA);
        query.setParameter("idProducto", idProductoNomina);
        query.setResultTransformer(Transformers.aliasToBean(ProductoNominaProgramaDto.class));
        List<ProductoNominaProgramaDto> detalles = query.list();

        return detalles;
    }
    
    public List<String> obtenerNombreProgramasPorIdProducto(Integer idProductoNomina) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(OBTENER_NOMBRE_PROGRAMAS_POR_ID_PRODUCTO_DE_NOMINA);
        query.setParameter("idProducto", idProductoNomina);
        List<String> list = query.list();

        return list;
    }
    
    public ProductoNominaDto obtenerFinPeridoEjercicioPorIdProducto(Integer idProductoNomina) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(OBTENER_FIN_PERIODO_Y_EJERCICIO_POR_ID_PRODUCTO_DE_NOMINA);
        query.setParameter("idProducto", idProductoNomina);
        query.setResultTransformer(Transformers.aliasToBean(ProductoNominaDto.class));
        ProductoNominaDto result = (ProductoNominaDto) query.list().get(0);
        return result;
    }
}
