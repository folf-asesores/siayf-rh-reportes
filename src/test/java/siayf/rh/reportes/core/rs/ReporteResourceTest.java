/*
 * ReporteResourceTest.java
 * Creado el 03/nov/2017 10:28:37 AM
 * 
 */

package siayf.rh.reportes.core.rs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import siayf.rh.reportes.api.AlmacenReportes;
import siayf.rh.reportes.api.Archivo;
import siayf.rh.reportes.api.Generador;
import siayf.rh.reportes.api.Reporte;
import siayf.rh.reportes.api.Reporteador;
import siayf.rh.reportes.core.AplicacionConstantes;
import siayf.rh.reportes.core.BitacoraReporte;
import siayf.rh.reportes.core.BitacoraReporteBean;
import siayf.rh.reportes.persistencia.entidad.BitacoraReporteEntity;
import siayf.rh.reportes.persistencia.entidad.PerfilUsuarioEntity;
import siayf.rh.reportes.persistencia.entidad.ReporteParametroEntity;
import siayf.rh.reportes.persistencia.entidad.UsuarioEntity;
import siayf.rh.reportes.persistencia.repositorio.BitacoraReporteRepository;
import siayf.rh.reportes.persistencia.repositorio.GenericRepository;
import siayf.rh.reportes.persistencia.repositorio.Repository;
import siayf.rh.reportes.persistencia.repositorio.UsuarioRepository;
import siayf.rh.reportes.util.BeanInjectUtil;
import siayf.rh.reportes.util.FechaUtil;
import siayf.rh.reportes.util.PlantillaMensaje;

import siayf.rh.reportes.core.excel.AlmacenReportesExcel;
import siayf.rh.reportes.core.excel.ExcelGenerador;
import siayf.rh.reportes.core.excel.ExcelPlantillaReporte;
import siayf.rh.reportes.core.excel.ExcelReporte;
import siayf.rh.reportes.core.excel.ReporteVacio;
import siayf.rh.reportes.empleado.detalle.DetalleEmpleadoExcel;
import siayf.rh.reportes.empleado.movimiento.concentrado.ConcentradoAltaBajaExcel;
import siayf.rh.reportes.nomina.productonomina.ProductoNomina;
import siayf.rh.reportes.nomina.productonomina.ProductoNominaBean;
import siayf.rh.reportes.nomina.productonomina.ProductoNominaProgramaDto;
import siayf.rh.reportes.nomina.productonomina.ProductoNominaProgramaExcel;
import siayf.rh.reportes.util.TipoArchivo;

import static org.junit.Assert.*;

/**
 * Este clase prueba el recurso para el consumo de reportes.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@RunWith(Arquillian.class)
@RunAsClient
public class ReporteResourceTest {

    private static final Logger LOGGER = Logger.getLogger(ReporteResourceTest.class.getName());
    
    private static final String RESOURCE_PREFIX = RestfulConfiguracion.class.getAnnotation(ApplicationPath.class).value();
    private static final String RESOURCE_NAME = ReporteResource.class.getAnnotation(Path.class).value();
    private static final String PATRON_NOMBRE_ARCHIVO = "(?<=filename=\").*?(?=\")";

    public ReporteResourceTest() {
    }

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "sit-api-reportes__test.war");
        war.addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
        war.addAsManifestResource("log4j-jboss.properties", "log4j.properties");
        war.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml");
        war.addAsResource("siayf/rh/reportes/nomina/producto_nomina_programa.xlsx");
        // Clases del paquete API
        war.addClass(AlmacenReportes.class);
        war.addClass(Archivo.class);
        war.addClass(Generador.class);
        war.addClass(Reporte.class);
        war.addClass(Reporteador.class);
        
        // Clases del paquete core
        war.addClass(AplicacionConstantes.class);
        war.addClass(BitacoraReporte.class);
        war.addClass(BitacoraReporteBean.class);
        
        // Clases del paquete core (Excel)
        war.addClass(AlmacenReportesExcel.class);
        war.addClass(ExcelGenerador.class);
        war.addClass(ExcelPlantillaReporte.class);
        war.addClass(ExcelReporte.class);
        war.addClass(ReporteVacio.class);
        
        // Clases del paquete core (RESTful)
        war.addClass(ReporteResource.class);
        war.addClass(RestfulConfiguracion.class);

        // Clases del paquete empleado (detalle)
        war.addClass(DetalleEmpleadoExcel.class);
        // Clases del paquete empleado (movimiento - concentrado)
        war.addClass(ConcentradoAltaBajaExcel.class);
        
        // Clases del paquete nomina (productonomina)
        war.addClass(ProductoNomina.class);
        war.addClass(ProductoNominaBean.class);
        war.addClass(ProductoNominaProgramaDto.class);
        war.addClass(ProductoNominaProgramaExcel.class);
        
        // Clases del paquete persistencia (entidad)
        war.addClass(BitacoraReporteEntity.class);
        war.addClass(PerfilUsuarioEntity.class);
        war.addClass(ReporteParametroEntity.class);
        war.addClass(UsuarioEntity.class);

        // Clases del paquete persistencia (repositorio)
        war.addClass(BitacoraReporteRepository.class);
        war.addClass(GenericRepository.class);
        war.addClass(Repository.class);
        war.addClass(UsuarioRepository.class);

        // Clases del paquete util
        war.addClass(BeanInjectUtil.class);
        war.addClass(FechaUtil.class);
        war.addClass(PlantillaMensaje.class);
        war.addClass(TipoArchivo.class);
            
        File[] dependencies = Maven.resolver()
                .loadPomFromFile("pom.xml")
                .importRuntimeDependencies()
                .resolve()
                .withTransitivity()
                .asFile();
        war.addAsLibraries(dependencies);

        return war;
    }
    
    /**
     * Prueba para el método obtenerReferencia  de la clase ReporteResource.
     * 
     * @param deploymentUrl la URL en la que se está desplegando el WAR de prueba.
     */
    @Test
    public void testObtenerReferencia(@ArquillianResource URL deploymentUrl) {
        String uri = deploymentUrl.toString();
        StringBuilder sb = new StringBuilder(uri);
        sb.deleteCharAt(uri.length() - 1);
        sb.append(RESOURCE_PREFIX);
        // Comentado porque el recurso que se esta probando está en la raíz.
        // sb.append(RESOURCE_NAME);
        uri = sb.toString();
        LOGGER.log(Level.INFO, "URL: {0}", uri);
        Client client = ClientBuilder.newClient();
        String referencia = client.target(uri)
                .queryParam("id-usuario", "33")
                .queryParam("reporte-nombre", "producto_nomina_programas")
                .queryParam("tipo-reporte", "xlsx")
                .queryParam("id-producto-nomina", "2")
                .request(MediaType.TEXT_PLAIN)
                .post(null, String.class);
        LOGGER.log(Level.INFO, "Referencia: {0}", referencia);
        assertNotNull(referencia);
    }

    /**
     * Test of obtenerReporte method, of class ReporteResource.
     * @param deploymentUrl
     * @throws java.io.IOException
     */
    @Test
    public void testObtenerReporte(@ArquillianResource URL deploymentUrl) throws IOException {
        // Referencia 1: 45cf4761-491d-452e-b1b6-e55098b96b90
        // Referencia 2: 9826c7c5-c11a-46e4-8af5-d61247fd565a
        String referencia = "9826c7c5-c11a-46e4-8af5-d61247fd565a";
        String uri = deploymentUrl.toString();
        StringBuilder sb = new StringBuilder(uri);
        sb.deleteCharAt(uri.length() - 1);
        sb.append(RESOURCE_PREFIX);
        // Comentado porque el recurso que se esta probando está en la raíz.
        // sb.append(RESOURCE_NAME);
         sb.append(referencia);
        uri = sb.toString();
        LOGGER.log(Level.INFO, "URL: {0}", uri);
        Client client = ClientBuilder.newClient();
        Response response = client.target(uri)
                .request(MediaType.WILDCARD)
                .post(null);
        InputStream is = response.readEntity(InputStream.class);
        String nombreArchivo = obtenerNombreArchivo(response.getHeaderString("Content-Disposition"));
        LOGGER.log(Level.INFO, "Nombre del archivo: {0}", nombreArchivo);
        File archivo = new File(System.getProperty("user.home") + System.getProperty("file.separator") + nombreArchivo);
        FileUtils.copyInputStreamToFile(is, archivo);
        assertNotNull(is);
    }
    
    private String obtenerNombreArchivo(String contentDisposition) {
        String nombreArchivo = null;
        Pattern pattern = Pattern.compile(PATRON_NOMBRE_ARCHIVO);
        Matcher matcher = pattern.matcher(contentDisposition);

        if (matcher.find()) {
            nombreArchivo = matcher.group();
        }
        
        return nombreArchivo;
    }
    
}
