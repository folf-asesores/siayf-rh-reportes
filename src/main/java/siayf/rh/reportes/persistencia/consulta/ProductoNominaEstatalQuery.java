
package siayf.rh.reportes.persistencia.consulta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import siayf.rh.reportes.core.AplicacionConstantes;
import siayf.rh.reportes.nomina.producto.estatal.ProductoNominaEstatalDto;

public class ProductoNominaEstatalQuery {
    @PersistenceContext(unitName = AplicacionConstantes.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    public List<ProductoNominaEstatalDto> obtenerProductoNominaEstatalPorIdProducto(Integer idProductoNomina) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_productos_nominas(:idProducto)");
        query.setParameter("idProducto", idProductoNomina);
        query.setResultTransformer(Transformers.aliasToBean(ProductoNominaEstatalDto.class));

        @SuppressWarnings("unchecked")
        List<ProductoNominaEstatalDto> list = query.list();

        return list;
    }

}