/*
 * ExcelGenerador.java
 * Creado el 23/sep/2016 7:21:13 PM
 *
 */

package siayf.rh.reportes.core.excel;

import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import siayf.rh.reportes.api.Archivo;
import siayf.rh.reportes.api.Generador;
import siayf.rh.reportes.empleado.detalle.DetalleEmpleado;
import siayf.rh.reportes.empleado.movimiento.cl.ComisionadoLicencia;
import siayf.rh.reportes.nomina.productonomina.ProductoNomina;
import siayf.rh.reportes.util.FechaUtil;
import siayf.rh.reportes.util.PlantillaMensaje;

import static siayf.rh.reportes.util.BeanInjectUtil.getBean;
import static siayf.rh.reportes.util.FechaUtil.PATRON_FECHA_BASE_DE_DATOS;
import static siayf.rh.reportes.util.TipoArchivo.XLSX;

/**
 *
 * @author Freddy Barrera
 * @author Eduardo Chuc Mex
 */
public class ExcelGenerador implements Generador {

    private static final long serialVersionUID = -5384835789936086358L;

    private static final Logger LOGGER = Logger.getLogger(ExcelGenerador.class.getName());

    @Override
    public Archivo obtenerReporte(Map<String, String> parametros) {
        AlmacenReportesExcel almacenReportesExcel = new AlmacenReportesExcel();
        String nombreReporte = parametros.get("REPORTE_NOMBRE");
        byte[] bytes = null;

        if (almacenReportesExcel.extisteReporte(nombreReporte)) {
            switch (nombreReporte) {
                // -------------------
                // Reporte de empleado
                // -------------------
                case "detalle_empleado": {
                    Integer idTipoContratacion = Integer.parseInt(parametros.get("ID_TIPO_CONTRATACION"));
                    DetalleEmpleado detalleEmpleadoBean = getBean(DetalleEmpleado.class);
                    bytes = detalleEmpleadoBean.generarReporte(idTipoContratacion);
                }
                break;

                case "comisionado_licencia": {
                    Date fechaInicial = FechaUtil.comoDate(parametros.get("FECHA_INICIAL"), PATRON_FECHA_BASE_DE_DATOS);
                    Date fechaFinal = FechaUtil.comoDate(parametros.get("FECHA_FINAL"), PATRON_FECHA_BASE_DE_DATOS);
                    ComisionadoLicencia comisionadoLicenciaBean = getBean(ComisionadoLicencia.class);
                    bytes = comisionadoLicenciaBean.generarReporte(fechaInicial, fechaFinal);
                }
                
                case "concentrado_alta_baja": {
                    Integer idTipoContratacionConcentrado = Integer.parseInt(parametros.get("ID_TIPO_CONTRATACION"));
                    Date fechaInicial = FechaUtil.comoDate(parametros.get("FECHA_INICIAL"), PATRON_FECHA_BASE_DE_DATOS);
                    Date fechaFinal = FechaUtil.comoDate(parametros.get("FECHA_FINAL"), PATRON_FECHA_BASE_DE_DATOS);
                /*

                    List<ConsentradoAltaBajaExcelDTO> consentradoAltaBajaExcelDTOs = getMovimientoEmpleadoReporteService()
                            .listaConsultaConsentradoAltaBajaPorRangoFecha(idTipoContratacionConsentrado, fechaInicial,
                                    fechaFinal);

                    if (!consentradoAltaBajaExcelDTOs.isEmpty()) {
                        ConsentradoAltaBajaExcel consentradoAltaBajaExcel = new ConsentradoAltaBajaExcel();

                        bytes = consentradoAltaBajaExcel.generar(consentradoAltaBajaExcelDTOs);
                    } else {
                        throw new ReglaNegocioException("No se encontrarón resultados, intentelo de nuevo.",
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }
                */
                }
                
                // ------------------
                // Reportes de nomina
                // ------------------
                case "producto_nomina_programas": {
                    Integer idProductoNomina = Integer.parseInt(parametros.get("ID_PRODUCTO_NOMINA"));
                    ProductoNomina productoNominaBean = getBean(ProductoNomina.class);
                    bytes = productoNominaBean.generarReporte(idProductoNomina);
                /*

                    List<ProductosNominaProgramasExcelDTO> listaProductoNominaProgramas = getProductoNomina()
                            .obtenerListaProductoNominaProgramasPorIdProducto(idProducto);
                    List<String> listaProgramas = getProductoNomina()
                            .obtenerListaProgramasPorIdProducto(idProducto);
                    ProductoNominaDTO producto = getProductoNomina().obtenerProductoNominaPorIdProducto(idProducto);

                    if (!listaProductoNominaProgramas.isEmpty()) {
                        ProductoNominaProgramasExcel productoNominaProgramasExcel = new ProductoNominaProgramasExcel();
                        bytes = productoNominaProgramasExcel.generar(listaProductoNominaProgramas, listaProgramas, producto);
                    } else {
                        throw new ReglaNegocioException(
                                "No se encontrarón resultados en el producto nomina: ",
                                ReglaNegocioCodigoError.SIN_REGISTRO);
                    }
                */
                }
            }
        }
        
        if (bytes ==  null) {
            LOGGER.log(Level.WARNING, PlantillaMensaje.REPORTE_ERROR_REGRESA_NULL, nombreReporte);
            bytes = ReporteVacio.obtenerBytes();
        }

        String nombre = nombreReporte + XLSX.getExtension(true);
        String mediaType = XLSX.getMIMEType();

        return new Archivo(nombre, mediaType, bytes);
    }

}
