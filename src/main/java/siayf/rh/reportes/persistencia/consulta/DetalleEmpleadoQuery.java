/*
 * 
 */

package siayf.rh.reportes.persistencia.consulta;

import java.io.Serializable;
import java.math.BigDecimal;
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
import siayf.rh.reportes.empleado.detalle.DetalleEmpleadoDto;
import siayf.rh.reportes.util.PlantillaMensaje;

/**
 * @author Eduardo Chuc Mex
 *
 */
public class DetalleEmpleadoQuery implements Serializable {

    private static final long serialVersionUID = 4968811924541393668L;
    private static final Logger LOGGER = Logger.getLogger(DetalleEmpleadoQuery.class.getName());

    @Resource(mappedName = AplicacionConstantes.DATASOURCE)
    private DataSource dataSource;

    private static final String COLUMNA_NOMBRE_COMPLETO = "nombreCompleto";
    private static final String COLUMNA_RFC = "rfc";
    private static final String COLUMNA_CURP = "curp";
    private static final String COLUMNA_FECHA_DE_NACIMIENTO = "fechaNacimiento";
    private static final String COLUMNA_TELEFONO = "telefono";
    private static final String COLUMNA_FECHA_ALTA = "fechaAlta";
    private static final String COLUMNA_DIRECCION_COMPLETA = "direccionCompleta";
    private static final String COLUMNA_TIPO_SANGRE = "tipoSangre";
    private static final String COLUMNA_NACIONALIDAD = "nacionalidad";
    private static final String COLUMNA_ESTADO_CIVIL = "estadoCivil";
    private static final String COLUMNA_SEXO = "sexo";
    private static final String COLUMNA_ESTATURA = "estatura";
    private static final String COLUMNA_PESO = "peso";
    private static final String COLUMNA_ESTADO_EMPLEADO = "estatus";
    private static final String COLUMNA_CORREO_ELECTRONICO = "correoElectronico";
    private static final String COLUMNA_FECHA_DE_INGRESO = "fechaIngreso";
    private static final String COLUMNA_TIENE_PERSONA_DEPENDIENTE = "tienePersonaDependiente";
    private static final String COLUMNA_ID_DATO_PERSONAL = "idDatoPersonal";
    private static final String COLUMNA_NUMERO_CONYUGE = "numeroConyuge";
    private static final String COLUMNA_NUMERO_PADRE = "numeroPadre";
    private static final String COLUMNA_NUMERO_HIJOS = "numeroHijo";
    private static final String COLUMNA_OTRO_PARENTESCO = "numeroOtroParentesco";
    private static final String COLUMNA_NUMERO_DE_CUENTA = "numeroCuenta";
    private static final String COLUMNA_BANCO = "banco";
    private static final String COLUMNA_NUMERO_IDENTIFICADOR_BIOMETRICO = "numeroIdentificadorBiometrico";
    private static final String COLUMNA_TIPO_EMPLEADO = "tipoEmpleado";
    private static final String COLUMNA_CLAVE_COBRO = "claveCobro";
    private static final String COLUMNA_METODO_PAGO = "metodoPago";
    private static final String COLUMNA_NUMERO_VACANTE = "numeroVacante";
    private static final String COLUMNA_INVENTARIO_VACANTE_DISPONIBLE = "inventarioVacanteDisponible";
    private static final String COLUMNA_CODIGO_VACANTE = "codigoVacante";
    private static final String COLUMNA_FOLIO_VACANTE = "folioVacante";
    private static final String COLUMNA_PROGRAMA = "programa";
    private static final String COLUMNA_ADSCRIPCION = "adscripcion";
    private static final String COLUMNA_AREA_ADSCRIPCION = "areaAdscripcion";
    private static final String COLUMNA_LUGAR_ADSCRIPCION = "lugarAdscripcion";
    private static final String COLUMNA_ACTIVIDAD = "actividad";
    private static final String COLUMNA_FUNCION = "funcion";
    private static final String COLUMNA_CLUES = "clues";
    private static final String COLUMNA_SEGURO_POPULAR = "seguroPopular";
    private static final String COLUMNA_TIPO_JORNADA = "tipoJornada";
    private static final String COLUMNA_PROYECTO_DESCRIPCION = "proyectoDescripcion";
    private static final String COLUMNA_DEPENDENCIA_DESCRIPCION = "dependenciaDescripccion";
    private static final String COLUMNA_UNIDAD_RESPONSABLE_DESCRIPCION = "unidadResponsableDescripcion";
    private static final String COLUMNA_TIPO_CONTRATACION_CODIGO = "tipoContratacionCodigo";
    private static final String COLUMNA_PUESTO_GENERAL_CODIGO = "puestoGeneralCodigo";
    private static final String COLUMNA_FUENTE_FINANCIAMIENTO_DESCRIPCION = "fuenteFinanciamientoDescripcion";
    private static final String COLUMNA_SUBFUENTE_FINANCIAMIENTO_DESCRIPCION = "subfuenteFinanciamientoDescripcion";
    private static final String COLUMNA_TIPO_RECURSO_DESCRIPCION = "tipoRecursoDescripcion";
    private static final String COLUMNA_ID_DATO_LABORAL = "idDatoLaboral";
    private static final String COLUMNA_CENTRO_RESPOSABILIDAD_DESCRIPCION = "centroResponsabilidadDescripcion";
    private static final String COLUMNA_CENTRO_RESPONSABILIDAD_CLAVE = "centroResponsabilidadClave";
    private static final String COLUMNA_CUENTA_BANCARIA_CLAVE_CUENTA = "cuentaBancariaClaveCuenta";
    private static final String COLUMNA_CUENTA_BANCARIA_BANCO = "cuentaBancariaBanco";
    private static final String COLUMNA_CUENTA_BANCARIA_DESCRIPCION = "cuentaBancariaDescripcion";
    private static final String COLUMNA_CONFIGURACION_PRESUPUESTAL_ESTADO = "configuracionPresupuestalEstado";
    private static final String COLUMNA_JORNADA_DESCRIPCION = "jornadaDescripcion";
    private static final String COLUMNA_PLAZA_CLAVE = "plazaClave";
    private static final String COLUMNA_PLAZA_NOMBRE = "plazaNombre";
    private static final String COLUMNA_SUELDO = "sueldo";
    private static final String COLUMNA_SUELDO_01 = "sueldo01";
    private static final String COLUMNA_SUELDO_14 = "sueldo14";
    private static final String COLUMNA_RIESGO_PUESTO_DESCRIPCION = "riesgoPuestoDescripcion";
    private static final String COLUMNA_TIPO_TABULADOR_DESCRIPCION = "tipoTabuladorDescripcion";
    private static final String COLUMNA_TIPO_PERIODO = "tipoPeriodo";
    
    private static final String DETALLES_DEL_EMPLEADO 
            = "CALL usp_detalles_empleados(?)";

    public List<DetalleEmpleadoDto> detalleEmpleadoPorIdTipoContratacion(Integer idTipoContratacion) {
        List<DetalleEmpleadoDto> detallesEmpleados = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(DETALLES_DEL_EMPLEADO);
            prepareStatement.setInt(1, idTipoContratacion);
            ResultSet rs = prepareStatement.executeQuery();
            
            while (rs.next()) {
                String nombreCompleto = rs.getString(COLUMNA_NOMBRE_COMPLETO);
                String rfc = rs.getString(COLUMNA_RFC);
                String curp = rs.getString(COLUMNA_CURP);
                Date fechaNacimiento = rs.getDate(COLUMNA_FECHA_DE_NACIMIENTO);
                String teefono = rs.getString(COLUMNA_TELEFONO);
                Date fechaAlta = rs.getDate(COLUMNA_FECHA_ALTA);
                String direccionCompleta = rs.getString(COLUMNA_DIRECCION_COMPLETA);
                String tipoSangre = rs.getString(COLUMNA_TIPO_SANGRE);
                String nacionalidad = rs.getString(COLUMNA_NACIONALIDAD);
                String estadoCivil = rs.getString(COLUMNA_ESTADO_CIVIL);
                String sexo = rs.getString(COLUMNA_SEXO);
                Float estatura = rs.getFloat(COLUMNA_ESTATURA);
                Float peso = rs.getFloat(COLUMNA_PESO);
                String estadoEmpleado = rs.getString(COLUMNA_ESTADO_EMPLEADO);
                String correoElectronico = rs.getString(COLUMNA_CORREO_ELECTRONICO);
                Date fechaIngreso = rs.getDate(COLUMNA_FECHA_DE_INGRESO);
                String tienePersonaDependiente = rs.getString(COLUMNA_TIENE_PERSONA_DEPENDIENTE);
                String idDatoPersonal = rs.getString(COLUMNA_ID_DATO_PERSONAL);
                String numeroConyuge = rs.getString(COLUMNA_NUMERO_CONYUGE);
                Integer numeroPadre = rs.getInt(COLUMNA_NUMERO_PADRE);
                Integer numeroHijo = rs.getInt(COLUMNA_NUMERO_HIJOS);
                Integer numeroOtroParentesco = rs.getInt(COLUMNA_OTRO_PARENTESCO);
                String numeroCuenta = rs.getString(COLUMNA_NUMERO_DE_CUENTA);
                String banco = rs.getString(COLUMNA_BANCO);
                Integer numeroIdentificadorBiometrico = rs.getInt(COLUMNA_NUMERO_IDENTIFICADOR_BIOMETRICO);
                String tipoEmpleado = rs.getString(COLUMNA_TIPO_EMPLEADO);
                String claveCobro = rs.getString(COLUMNA_CLAVE_COBRO);
                String metodoPago = rs.getString(COLUMNA_METODO_PAGO);
                Integer numeroVacante = rs.getInt(COLUMNA_NUMERO_VACANTE);
                String inventarioVacanteDisponible = rs.getString(COLUMNA_INVENTARIO_VACANTE_DISPONIBLE);
                String codigoVacante = rs.getString(COLUMNA_CODIGO_VACANTE);
                String folioVacante = rs.getString(COLUMNA_FOLIO_VACANTE);
                String programa = rs.getString(COLUMNA_PROGRAMA);
                String adscripcion = rs.getString(COLUMNA_ADSCRIPCION);
                String areaAdscripcion = rs.getString(COLUMNA_AREA_ADSCRIPCION);
                String lugarAdscripcion = rs.getString(COLUMNA_LUGAR_ADSCRIPCION);
                String actividad = rs.getString(COLUMNA_ACTIVIDAD);
                String funcion = rs.getString(COLUMNA_FUNCION);
                String clues = rs.getString(COLUMNA_CLUES);
                String seguroPopular = rs.getString(COLUMNA_SEGURO_POPULAR);
                String tipoJornada = rs.getString(COLUMNA_TIPO_JORNADA);
                String proyectoDescripcion = rs.getString(COLUMNA_PROYECTO_DESCRIPCION);
                String dependenciaDescripccion = rs.getString(COLUMNA_DEPENDENCIA_DESCRIPCION);
                String unidadResponsableDescripcion = rs.getString(COLUMNA_UNIDAD_RESPONSABLE_DESCRIPCION);
                String tipoContratacionCodigo = rs.getString(COLUMNA_TIPO_CONTRATACION_CODIGO);
                String puestoGeneralCodigo = rs.getString(COLUMNA_PUESTO_GENERAL_CODIGO);
                String fuenteFinanciamientoDescripcion = rs.getString(COLUMNA_FUENTE_FINANCIAMIENTO_DESCRIPCION);
                String subfuenteFinanciamientoDescripcion = rs.getString(COLUMNA_SUBFUENTE_FINANCIAMIENTO_DESCRIPCION);
                String tipoRecursoDescripcion =  rs.getString(COLUMNA_TIPO_RECURSO_DESCRIPCION);
                Integer idDatoLaboral = rs.getInt(COLUMNA_ID_DATO_LABORAL);
                String centroResponsabilidadDescripcion = rs.getString(COLUMNA_CENTRO_RESPOSABILIDAD_DESCRIPCION);
                String centroResponsabilidadClave = rs.getString(COLUMNA_CENTRO_RESPONSABILIDAD_CLAVE);
                Integer cuentaBancariaClaveCuenta = rs.getInt(COLUMNA_CUENTA_BANCARIA_CLAVE_CUENTA);
                String cuentaBancariaBanco = rs.getString(COLUMNA_CUENTA_BANCARIA_BANCO);
                String cuentaBancariaDescripcion = rs.getString(COLUMNA_CUENTA_BANCARIA_DESCRIPCION);
                String configuracionPresupuestalEstado =  rs.getString(COLUMNA_CONFIGURACION_PRESUPUESTAL_ESTADO);
                String jornadaDescripcion = rs.getString(COLUMNA_JORNADA_DESCRIPCION);
                String plazaClave = rs.getString(COLUMNA_PLAZA_CLAVE);
                String plazaNombre = rs.getString(COLUMNA_PLAZA_NOMBRE);
                BigDecimal sueldo = rs.getBigDecimal(COLUMNA_SUELDO);
                BigDecimal sueldo01 = rs.getBigDecimal(COLUMNA_SUELDO_01);
                BigDecimal sueldo14 = rs.getBigDecimal(COLUMNA_SUELDO_14);
                String riesgoPuestoDescripcion = rs.getString(COLUMNA_RIESGO_PUESTO_DESCRIPCION);
                String tipoTabuladorDescripcion = rs.getString(COLUMNA_TIPO_TABULADOR_DESCRIPCION);
                String tipoPeriodo = rs.getString(COLUMNA_TIPO_PERIODO);
                
                DetalleEmpleadoDto detalleEmpleado = new DetalleEmpleadoDto.Builder(nombreCompleto, rfc, curp, fechaNacimiento, sexo)
                        .setTelefono(teefono)
                        .setFechaAlta(fechaAlta)
                        .setDireccionCompleta(direccionCompleta)
                        .setTipoSangre(tipoSangre)
                        .setNacionalidad(nacionalidad)
                        .setEstadoCivil(estadoCivil)
                        .setEstatura(estatura)
                        .setPeso(peso)
                        .setEstatus(estadoEmpleado)
                        .setCorreoElectronico(correoElectronico)
                        .setFechaIngreso(fechaIngreso)
                        .setTienePersonaDependiente(tienePersonaDependiente)
                        .setIdDatoPersonal(idDatoPersonal)
                        .setNumeroConyuge(numeroConyuge)
                        .setNumeroPadre(numeroPadre)
                        .setNumeroHijo(numeroHijo)
                        .setNumeroOtroParentesco(numeroOtroParentesco)
                        .setNumeroCuenta(numeroCuenta)
                        .setBanco(banco)
                        .setNumeroIdentificadorBiometrico(numeroIdentificadorBiometrico)
                        .setTipoEmpleado(tipoEmpleado)
                        .setClaveCobro(claveCobro)
                        .setMetodoPago(metodoPago)
                        .setNumeroVacante(numeroVacante)
                        .setInventarioVacanteDisponible(inventarioVacanteDisponible)
                        .setCodigoVacante(codigoVacante)
                        .setFolioVacante(folioVacante)
                        .setPrograma(programa)
                        .setAdscripcion(adscripcion)
                        .setAreaAdscripcion(areaAdscripcion)
                        .setLugarAdscripcion(lugarAdscripcion)
                        .setActividad(actividad)
                        .setFuncion(funcion)
                        .setClues(clues)
                        .setSeguroPopular(seguroPopular)
                        .setTipoJornada(tipoJornada)
                        .setProyectoDescripcion(proyectoDescripcion)
                        .setDependenciaDescripccion(dependenciaDescripccion)
                        .setUnidadResponsableDescripcion(unidadResponsableDescripcion)
                        .setTipoContratacionCodigo(tipoContratacionCodigo)
                        .setPuestoGeneralCodigo(puestoGeneralCodigo)
                        .setFuenteFinanciamientoDescripcion(fuenteFinanciamientoDescripcion)
                        .setSubfuenteFinanciamientoDescripcion(subfuenteFinanciamientoDescripcion)
                        .setTipoRecursoDescripcion(tipoRecursoDescripcion)
                        .setIdDatoLaboral(idDatoLaboral)
                        .setCentroResponsabilidadDescripcion(centroResponsabilidadDescripcion)
                        .setCentroResponsabilidadClave(centroResponsabilidadClave)
                        .setCuentaBancariaClaveCuenta(cuentaBancariaClaveCuenta)
                        .setCuentaBancariaBanco(cuentaBancariaBanco)
                        .setCuentaBancariaDescripcion(cuentaBancariaDescripcion)
                        .setConfiguracionPresupuestalEstado(configuracionPresupuestalEstado)
                        .setJornadaDescripcion(jornadaDescripcion)
                        .setPlazaClave(plazaClave)
                        .setPlazaNombre(plazaNombre)
                        .setSueldo(sueldo)
                        .setSueldo01(sueldo01)
                        .setSueldo14(sueldo14)
                        .setRiesgoPuestoDescripcion(riesgoPuestoDescripcion)
                        .setTipoTabuladorDescripcion(tipoTabuladorDescripcion)
                        .setTipoPeriodo(tipoPeriodo)
                        .construir();
                
                detallesEmpleados.add(detalleEmpleado);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, PlantillaMensaje.SQL_ERROR_MESSAGE, new Object [] {ex, ex.getSQLState(), ex.getErrorCode()});
        }

        return detallesEmpleados;
    }

}
