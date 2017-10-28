/*
 * ComisionadoLicenciaQuery.java
 * Creado el 27/oct/2017 9:14:22 PM
 * 
 */

package siayf.rh.reportes.persistencia.consulta;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;
import siayf.rh.reportes.core.AplicacionConstantes;
import siayf.rh.reportes.empleado.movimiento.cl.ComisionadoLicenciaDto;
import siayf.rh.reportes.util.FechaUtil;
import siayf.rh.reportes.util.PlantillaMensaje;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ComisionadoLicenciaQuery implements Serializable {

    private static final long serialVersionUID = -9167138009156074828L;
    private static final Logger LOGGER = Logger.getLogger(ComisionadoLicenciaQuery.class.getName());
    
    @Resource(mappedName = AplicacionConstantes.DATASOURCE_ESPEJO)
    private DataSource dataSource;
    
    private static final String COLUMNA_TIPO_MOVIMIENTO = "tipoMovimiento";
    private static final String COLUMNA_APELLIDO_PATERNO = "apellidoPaterno";
    private static final String COLUMNA_APELLIDO_MATERNO = "apellidoMaterno";
    private static final String COLUMNA_NOMBRE_DEL_EMPLEADO = "nombreEmpleado";
    private static final String COLUMNA_TIPO_PLAZA = "tipoPlaza";
    private static final String COLUMNA_NUMERO_DE_HORAS = "numeroHoras";
    private static final String COLUMNA_FUNCIONES_ESPECIFICAS = "funcionesEspecificas";
    private static final String COLUMNA_CLAVE_DE_PAGO = "clavePago";
    private static final String COLUMNA_FECHA_DE_INICIO = "fechaInicio";
    private static final String COLUMNA_FECHA_DE_CONCLUSION = "fechaConclusion";
    private static final String COLUMNA_CENTRO_DE_TRABAJO_ORIGEN = "centroTrabajoOrigen";
    private static final String COLUMNA_CENTRO_DE_TRABAJO_DESTINO = "centroTrabajoDestino";
    
    private static final String COMISIONADOS_O_CON_LICENCIA_POR_RANGO_DE_FECHA =
            "CALL usp_comisionado_licencia(?, ?)";

    public List<ComisionadoLicenciaDto> obtenerEmpleadosComisionadosLicenciaPorRangoFecha(Date fechaInicial, Date fechaFinal) {
        List<ComisionadoLicenciaDto> comisionados = new ArrayList<>();
        
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(COMISIONADOS_O_CON_LICENCIA_POR_RANGO_DE_FECHA);
            prepareStatement.setDate(1, FechaUtil.comoSqlDate(fechaInicial));
            prepareStatement.setDate(2, FechaUtil.comoSqlDate(fechaFinal));
            ResultSet rs = prepareStatement.executeQuery();
            
            while (rs.next()) {
                Integer tipoMovimiento = rs.getInt(COLUMNA_TIPO_MOVIMIENTO);
                String apellidoPaterno = rs.getString(COLUMNA_APELLIDO_PATERNO);
                String apellidoMaterno = rs.getString(COLUMNA_APELLIDO_MATERNO);
                String nombreEmpleado = rs.getString(COLUMNA_NOMBRE_DEL_EMPLEADO);
                String tipoPlaza = rs.getString(COLUMNA_TIPO_PLAZA);
                String numeroHoras = rs.getString(COLUMNA_NUMERO_DE_HORAS);
                String funcionesEspecificas = rs.getString(COLUMNA_FUNCIONES_ESPECIFICAS);
                String clavePago = rs.getString(COLUMNA_CLAVE_DE_PAGO);
                Date fechaInicio = FechaUtil.comoDate(rs.getDate(COLUMNA_FECHA_DE_INICIO));
                Date fechaConclusion = FechaUtil.comoDate(rs.getDate(COLUMNA_FECHA_DE_CONCLUSION));
                String centroTrabajoOrigen = rs.getString(COLUMNA_CENTRO_DE_TRABAJO_ORIGEN);
                String centroTrabajoDestino = rs.getString(COLUMNA_CENTRO_DE_TRABAJO_DESTINO);
                
                ComisionadoLicenciaDto comisionadoLicencia = new ComisionadoLicenciaDto.Builder()
                        .setTipoMovimiento(tipoMovimiento)
                        .setApellidoPaterno(apellidoPaterno)
                        .setApellidoMaterno(apellidoMaterno)
                        .setNombreEmpleado(nombreEmpleado)
                        .setTipoPlaza(tipoPlaza)
                        .setNumeroHoras(numeroHoras)
                        .setFuncionesEspecificas(funcionesEspecificas)
                        .setClavePago(clavePago)
                        .setFechaInicio(fechaInicio)
                        .setFechaConclusion(fechaConclusion)
                        .setCentroTrabajoOrigen(centroTrabajoOrigen)
                        .setCentroTrabajoDestino(centroTrabajoDestino)
                        .construir();

                comisionados.add(comisionadoLicencia);
            }
            
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, PlantillaMensaje.SQL_ERROR_MESSAGE, new Object [] {ex, ex.getSQLState(), ex.getErrorCode()});
        }
        
        return comisionados;
    }

}
