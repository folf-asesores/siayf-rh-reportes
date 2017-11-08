/*
 * 
 */

package siayf.rh.reportes.empleado.detalle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Eduardo Chuc Mex (Lic.Eduardo_Mex@hotmail.com)
 */
public final class DetalleEmpleadoDto implements Serializable {

    private static final long serialVersionUID = -6035046641674697297L;

    private final String nombreCompleto;
    private final String rfc;
    private final String curp;
    private final Date fechaNacimiento;
    private final String telefono;
    private final Date fechaAlta;
    private final String direccionCompleta;
    private final String tipoSangre;
    private final String nacionalidad;
    private final String estadoCivil;
    private final String sexo;
    private final Float estatura;
    private final Float peso;
    private final String estatus;
    private final String correoElectronico;
    private final Date fechaIngreso;
    private final String tienePersonaDependiente;
    private final String idDatoPersonal;
    private final String numeroConyuge;
    private final Integer numeroPadre;
    private final Integer numeroHijo;
    private final Integer numeroOtroParentesco;
    private final String numeroCuenta;
    private final String banco;
    private final Integer numeroIdentificadorBiometrico;
    private final String tipoEmpleado;
    private final String claveCobro;
    private final String metodoPago;
    private final Integer numeroVacante;
    private final String inventarioVacanteDisponible;
    private final String codigoVacante;
    private final String folioVacante;
    private final String programa;
    private final String adscripcion;
    private final String areaAdscripcion;
    private final String lugarAdscripcion;
    private final String actividad;
    private final String funcion;
    private final String clues;
    private final String seguroPopular;
    private final String tipoJornada;
    private final String proyectoDescripcion;
    private final String dependenciaDescripccion;
    private final String unidadResponsableDescripcion;
    private final String tipoContratacionCodigo;
    private final String puestoGeneralCodigo;
    private final String fuenteFinanciamientoDescripcion;
    private final String subfuenteFinanciamientoDescripcion;
    private final String tipoRecursoDescripcion;
    private final Integer idDatoLaboral;
    private final String centroResponsabilidadDescripcion;
    private final String centroResponsabilidadClave;
    private final Integer cuentaBancariaClaveCuenta;
    private final String cuentaBancariaBanco;
    private final String cuentaBancariaDescripcion;
    private final String configuracionPresupuestalEstado;
    private final String jornadaDescripcion;
    private final String plazaClave;
    private final String plazaNombre;
    private final BigDecimal sueldo;
    private final BigDecimal sueldo01;
    private final BigDecimal sueldo14;
    private final String riesgoPuestoDescripcion;
    private final String tipoTabuladorDescripcion;
    private final String tipoPeriodo;

    public DetalleEmpleadoDto(String nombreCompleto, String rfc, String curp, Date fechaNacimiento, String telefono, Date fechaAlta, String direccionCompleta, String tipoSangre, String nacionalidad, String estadoCivil, String sexo, Float estatura, Float peso, String estatus, String correoElectronico, Date fechaIngreso, String tienePersonaDependiente, String idDatoPersonal, String numeroConyuge, Integer numeroPadre, Integer numeroHijo, Integer numeroOtroParentesco, String numeroCuenta, String banco, Integer numeroIdentificadorBiometrico, String tipoEmpleado, String claveCobro, String metodoPago, Integer numeroVacante, String inventarioVacanteDisponible, String codigoVacante, String folioVacante, String programa, String adscripcion, String areaAdscripcion, String lugarAdscripcion, String actividad, String funcion, String clues, String seguroPopular, String tipoJornada, String proyectoDescripcion, String dependenciaDescripccion, String unidadResponsableDescripcion, String tipoContratacionCodigo, String puestoGeneralCodigo, String fuenteFinanciamientoDescripcion, String subfuenteFinanciamientoDescripcion, String tipoRecursoDescripcion, Integer idDatoLaboral, String centroResponsabilidadDescripcion, String centroResponsabilidadClave, Integer cuentaBancariaClaveCuenta, String cuentaBancariaBanco, String cuentaBancariaDescripcion, String configuracionPresupuestalEstado, String jornadaDescripcion, String plazaClave, String plazaNombre, BigDecimal sueldo, BigDecimal sueldo01, BigDecimal sueldo14, String riesgoPuestoDescripcion, String tipoTabuladorDescripcion, String tipoPeriodo) {
        this.nombreCompleto = nombreCompleto;
        this.rfc = rfc;
        this.curp = curp;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.fechaAlta = fechaAlta;
        this.direccionCompleta = direccionCompleta;
        this.tipoSangre = tipoSangre;
        this.nacionalidad = nacionalidad;
        this.estadoCivil = estadoCivil;
        this.sexo = sexo;
        this.estatura = estatura;
        this.peso = peso;
        this.estatus = estatus;
        this.correoElectronico = correoElectronico;
        this.fechaIngreso = fechaIngreso;
        this.tienePersonaDependiente = tienePersonaDependiente;
        this.idDatoPersonal = idDatoPersonal;
        this.numeroConyuge = numeroConyuge;
        this.numeroPadre = numeroPadre;
        this.numeroHijo = numeroHijo;
        this.numeroOtroParentesco = numeroOtroParentesco;
        this.numeroCuenta = numeroCuenta;
        this.banco = banco;
        this.numeroIdentificadorBiometrico = numeroIdentificadorBiometrico;
        this.tipoEmpleado = tipoEmpleado;
        this.claveCobro = claveCobro;
        this.metodoPago = metodoPago;
        this.numeroVacante = numeroVacante;
        this.inventarioVacanteDisponible = inventarioVacanteDisponible;
        this.codigoVacante = codigoVacante;
        this.folioVacante = folioVacante;
        this.programa = programa;
        this.adscripcion = adscripcion;
        this.areaAdscripcion = areaAdscripcion;
        this.lugarAdscripcion = lugarAdscripcion;
        this.actividad = actividad;
        this.funcion = funcion;
        this.clues = clues;
        this.seguroPopular = seguroPopular;
        this.tipoJornada = tipoJornada;
        this.proyectoDescripcion = proyectoDescripcion;
        this.dependenciaDescripccion = dependenciaDescripccion;
        this.unidadResponsableDescripcion = unidadResponsableDescripcion;
        this.tipoContratacionCodigo = tipoContratacionCodigo;
        this.puestoGeneralCodigo = puestoGeneralCodigo;
        this.fuenteFinanciamientoDescripcion = fuenteFinanciamientoDescripcion;
        this.subfuenteFinanciamientoDescripcion = subfuenteFinanciamientoDescripcion;
        this.tipoRecursoDescripcion = tipoRecursoDescripcion;
        this.idDatoLaboral = idDatoLaboral;
        this.centroResponsabilidadDescripcion = centroResponsabilidadDescripcion;
        this.centroResponsabilidadClave = centroResponsabilidadClave;
        this.cuentaBancariaClaveCuenta = cuentaBancariaClaveCuenta;
        this.cuentaBancariaBanco = cuentaBancariaBanco;
        this.cuentaBancariaDescripcion = cuentaBancariaDescripcion;
        this.configuracionPresupuestalEstado = configuracionPresupuestalEstado;
        this.jornadaDescripcion = jornadaDescripcion;
        this.plazaClave = plazaClave;
        this.plazaNombre = plazaNombre;
        this.sueldo = sueldo;
        this.sueldo01 = sueldo01;
        this.sueldo14 = sueldo14;
        this.riesgoPuestoDescripcion = riesgoPuestoDescripcion;
        this.tipoTabuladorDescripcion = tipoTabuladorDescripcion;
        this.tipoPeriodo = tipoPeriodo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getRfc() {
        return rfc;
    }

    public String getCurp() {
        return curp;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public Float getEstatura() {
        return estatura;
    }

    public Float getPeso() {
        return peso;
    }

    public String getEstatus() {
        return estatus;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public String getTienePersonaDependiente() {
        return tienePersonaDependiente;
    }

    public String getIdDatoPersonal() {
        return idDatoPersonal;
    }

    public String getNumeroConyuge() {
        return numeroConyuge;
    }

    public Integer getNumeroPadre() {
        return numeroPadre;
    }

    public Integer getNumeroHijo() {
        return numeroHijo;
    }

    public Integer getNumeroOtroParentesco() {
        return numeroOtroParentesco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getBanco() {
        return banco;
    }

    public Integer getNumeroIdentificadorBiometrico() {
        return numeroIdentificadorBiometrico;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public String getClaveCobro() {
        return claveCobro;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public Integer getNumeroVacante() {
        return numeroVacante;
    }

    public String getInventarioVacanteDisponible() {
        return inventarioVacanteDisponible;
    }

    public String getCodigoVacante() {
        return codigoVacante;
    }

    public String getFolioVacante() {
        return folioVacante;
    }

    public String getPrograma() {
        return programa;
    }

    public String getAdscripcion() {
        return adscripcion;
    }

    public String getAreaAdscripcion() {
        return areaAdscripcion;
    }

    public String getLugarAdscripcion() {
        return lugarAdscripcion;
    }

    public String getActividad() {
        return actividad;
    }

    public String getFuncion() {
        return funcion;
    }

    public String getClues() {
        return clues;
    }

    public String getSeguroPopular() {
        return seguroPopular;
    }

    public String getTipoJornada() {
        return tipoJornada;
    }

    public String getProyectoDescripcion() {
        return proyectoDescripcion;
    }

    public String getDependenciaDescripccion() {
        return dependenciaDescripccion;
    }

    public String getUnidadResponsableDescripcion() {
        return unidadResponsableDescripcion;
    }

    public String getTipoContratacionCodigo() {
        return tipoContratacionCodigo;
    }

    public String getPuestoGeneralCodigo() {
        return puestoGeneralCodigo;
    }

    public String getFuenteFinanciamientoDescripcion() {
        return fuenteFinanciamientoDescripcion;
    }

    public String getSubfuenteFinanciamientoDescripcion() {
        return subfuenteFinanciamientoDescripcion;
    }

    public String getTipoRecursoDescripcion() {
        return tipoRecursoDescripcion;
    }

    public Integer getIdDatoLaboral() {
        return idDatoLaboral;
    }

    public String getCentroResponsabilidadDescripcion() {
        return centroResponsabilidadDescripcion;
    }

    public String getCentroResponsabilidadClave() {
        return centroResponsabilidadClave;
    }

    public Integer getCuentaBancariaClaveCuenta() {
        return cuentaBancariaClaveCuenta;
    }

    public String getCuentaBancariaBanco() {
        return cuentaBancariaBanco;
    }

    public String getCuentaBancariaDescripcion() {
        return cuentaBancariaDescripcion;
    }

    public String getConfiguracionPresupuestalEstado() {
        return configuracionPresupuestalEstado;
    }

    public String getJornadaDescripcion() {
        return jornadaDescripcion;
    }

    public String getPlazaClave() {
        return plazaClave;
    }

    public String getPlazaNombre() {
        return plazaNombre;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public BigDecimal getSueldo01() {
        return sueldo01;
    }

    public BigDecimal getSueldo14() {
        return sueldo14;
    }

    public String getRiesgoPuestoDescripcion() {
        return riesgoPuestoDescripcion;
    }

    public String getTipoTabuladorDescripcion() {
        return tipoTabuladorDescripcion;
    }

    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    public static final class Builder {

        private String nombreCompleto;
        private String rfc;
        private String curp;
        private Date fechaNacimiento;
        private String telefono;
        private Date fechaAlta;
        private String direccionCompleta;
        private String tipoSangre;
        private String nacionalidad;
        private String estadoCivil;
        private String sexo;
        private Float estatura;
        private Float peso;
        private String estatus;
        private String correoElectronico;
        private Date fechaIngreso;
        private String tienePersonaDependiente;
        private String idDatoPersonal;
        private String numeroConyuge;
        private Integer numeroPadre;
        private Integer numeroHijo;
        private Integer numeroOtroParentesco;
        private String numeroCuenta;
        private String banco;
        private Integer numeroIdentificadorBiometrico;
        private String tipoEmpleado;
        private String claveCobro;
        private String metodoPago;
        private Integer numeroVacante;
        private String inventarioVacanteDisponible;
        private String codigoVacante;
        private String folioVacante;
        private String programa;
        private String adscripcion;
        private String areaAdscripcion;
        private String lugarAdscripcion;
        private String actividad;
        private String funcion;
        private String clues;
        private String seguroPopular;
        private String tipoJornada;
        private String proyectoDescripcion;
        private String dependenciaDescripccion;
        private String unidadResponsableDescripcion;
        private String tipoContratacionCodigo;
        private String puestoGeneralCodigo;
        private String fuenteFinanciamientoDescripcion;
        private String subfuenteFinanciamientoDescripcion;
        private String tipoRecursoDescripcion;
        private Integer idDatoLaboral;
        private String centroResponsabilidadDescripcion;
        private String centroResponsabilidadClave;
        private Integer cuentaBancariaClaveCuenta;
        private String cuentaBancariaBanco;
        private String cuentaBancariaDescripcion;
        private String configuracionPresupuestalEstado;
        private String jornadaDescripcion;
        private String plazaClave;
        private String plazaNombre;
        private BigDecimal sueldo;
        private BigDecimal sueldo01;
        private BigDecimal sueldo14;
        private String riesgoPuestoDescripcion;
        private String tipoTabuladorDescripcion;
        private String tipoPeriodo;

        public Builder(String nombreCompleto, String rfc, String curp, Date fechaNacimiento, String sexo) {
            this.nombreCompleto = nombreCompleto;
            this.rfc = rfc;
            this.curp = curp;
            this.fechaNacimiento = fechaNacimiento;
            this.sexo = sexo;
        }

        public Builder setNombreCompleto(String nombreCompleto) {
            this.nombreCompleto = nombreCompleto;
            return this;
        }

        public Builder setRfc(String rfc) {
            this.rfc = rfc;
            return this;
        }

        public Builder setCurp(String curp) {
            this.curp = curp;
            return this;
        }

        public Builder setFechaNacimiento(Date fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public Builder setTelefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder setFechaAlta(Date fechaAlta) {
            this.fechaAlta = fechaAlta;
            return this;
        }

        public Builder setDireccionCompleta(String direccionCompleta) {
            this.direccionCompleta = direccionCompleta;
            return this;
        }

        public Builder setTipoSangre(String tipoSangre) {
            this.tipoSangre = tipoSangre;
            return this;
        }

        public Builder setNacionalidad(String nacionalidad) {
            this.nacionalidad = nacionalidad;
            return this;
        }

        public Builder setEstadoCivil(String estadoCivil) {
            this.estadoCivil = estadoCivil;
            return this;
        }

        public Builder setSexo(String sexo) {
            this.sexo = sexo;
            return this;
        }

        public Builder setEstatura(Float estatura) {
            this.estatura = estatura;
            return this;
        }

        public Builder setPeso(Float peso) {
            this.peso = peso;
            return this;
        }

        public Builder setEstatus(String estatus) {
            this.estatus = estatus;
            return this;
        }

        public Builder setCorreoElectronico(String correoElectronico) {
            this.correoElectronico = correoElectronico;
            return this;
        }

        public Builder setFechaIngreso(Date fechaIngreso) {
            this.fechaIngreso = fechaIngreso;
            return this;
        }

        public Builder setTienePersonaDependiente(String tienePersonaDependiente) {
            this.tienePersonaDependiente = tienePersonaDependiente;
            return this;
        }

        public Builder setIdDatoPersonal(String idDatoPersonal) {
            this.idDatoPersonal = idDatoPersonal;
            return this;
        }

        public Builder setNumeroConyuge(String numeroConyuge) {
            this.numeroConyuge = numeroConyuge;
            return this;
        }

        public Builder setNumeroPadre(Integer numeroPadre) {
            this.numeroPadre = numeroPadre;
            return this;
        }

        public Builder setNumeroHijo(Integer numeroHijo) {
            this.numeroHijo = numeroHijo;
            return this;
        }

        public Builder setNumeroOtroParentesco(Integer numeroOtroParentesco) {
            this.numeroOtroParentesco = numeroOtroParentesco;
            return this;
        }

        public Builder setNumeroCuenta(String numeroCuenta) {
            this.numeroCuenta = numeroCuenta;
            return this;
        }

        public Builder setBanco(String banco) {
            this.banco = banco;
            return this;
        }

        public Builder setNumeroIdentificadorBiometrico(Integer numeroIdentificadorBiometrico) {
            this.numeroIdentificadorBiometrico = numeroIdentificadorBiometrico;
            return this;
        }

        public Builder setTipoEmpleado(String tipoEmpleado) {
            this.tipoEmpleado = tipoEmpleado;
            return this;
        }

        public Builder setClaveCobro(String claveCobro) {
            this.claveCobro = claveCobro;
            return this;
        }

        public Builder setMetodoPago(String metodoPago) {
            this.metodoPago = metodoPago;
            return this;
        }

        public Builder setNumeroVacante(Integer numeroVacante) {
            this.numeroVacante = numeroVacante;
            return this;
        }

        public Builder setInventarioVacanteDisponible(String inventarioVacanteDisponible) {
            this.inventarioVacanteDisponible = inventarioVacanteDisponible;
            return this;
        }

        public Builder setCodigoVacante(String codigoVacante) {
            this.codigoVacante = codigoVacante;
            return this;
        }

        public Builder setFolioVacante(String folioVacante) {
            this.folioVacante = folioVacante;
            return this;
        }

        public Builder setPrograma(String programa) {
            this.programa = programa;
            return this;
        }

        public Builder setAdscripcion(String adscripcion) {
            this.adscripcion = adscripcion;
            return this;
        }

        public Builder setAreaAdscripcion(String areaAdscripcion) {
            this.areaAdscripcion = areaAdscripcion;
            return this;
        }

        public Builder setLugarAdscripcion(String lugarAdscripcion) {
            this.lugarAdscripcion = lugarAdscripcion;
            return this;
        }

        public Builder setActividad(String actividad) {
            this.actividad = actividad;
            return this;
        }

        public Builder setFuncion(String funcion) {
            this.funcion = funcion;
            return this;
        }

        public Builder setClues(String clues) {
            this.clues = clues;
            return this;
        }

        public Builder setSeguroPopular(String seguroPopular) {
            this.seguroPopular = seguroPopular;
            return this;
        }

        public Builder setTipoJornada(String tipoJornada) {
            this.tipoJornada = tipoJornada;
            return this;
        }

        public Builder setProyectoDescripcion(String proyectoDescripcion) {
            this.proyectoDescripcion = proyectoDescripcion;
            return this;
        }

        public Builder setDependenciaDescripccion(String dependenciaDescripccion) {
            this.dependenciaDescripccion = dependenciaDescripccion;
            return this;
        }

        public Builder setUnidadResponsableDescripcion(String unidadResponsableDescripcion) {
            this.unidadResponsableDescripcion = unidadResponsableDescripcion;
            return this;
        }

        public Builder setTipoContratacionCodigo(String tipoContratacionCodigo) {
            this.tipoContratacionCodigo = tipoContratacionCodigo;
            return this;
        }

        public Builder setPuestoGeneralCodigo(String puestoGeneralCodigo) {
            this.puestoGeneralCodigo = puestoGeneralCodigo;
            return this;
        }

        public Builder setFuenteFinanciamientoDescripcion(String fuenteFinanciamientoDescripcion) {
            this.fuenteFinanciamientoDescripcion = fuenteFinanciamientoDescripcion;
            return this;
        }

        public Builder setSubfuenteFinanciamientoDescripcion(String subfuenteFinanciamientoDescripcion) {
            this.subfuenteFinanciamientoDescripcion = subfuenteFinanciamientoDescripcion;
            return this;
        }

        public Builder setTipoRecursoDescripcion(String tipoRecursoDescripcion) {
            this.tipoRecursoDescripcion = tipoRecursoDescripcion;
            return this;
        }

        public Builder setIdDatoLaboral(Integer idDatoLaboral) {
            this.idDatoLaboral = idDatoLaboral;
            return this;
        }

        public Builder setCentroResponsabilidadDescripcion(String centroResponsabilidadDescripcion) {
            this.centroResponsabilidadDescripcion = centroResponsabilidadDescripcion;
            return this;
        }

        public Builder setCentroResponsabilidadClave(String centroResponsabilidadClave) {
            this.centroResponsabilidadClave = centroResponsabilidadClave;
            return this;
        }

        public Builder setCuentaBancariaClaveCuenta(Integer cuentaBancariaClaveCuenta) {
            this.cuentaBancariaClaveCuenta = cuentaBancariaClaveCuenta;
            return this;
        }

        public Builder setCuentaBancariaBanco(String cuentaBancariaBanco) {
            this.cuentaBancariaBanco = cuentaBancariaBanco;
            return this;
        }

        public Builder setCuentaBancariaDescripcion(String cuentaBancariaDescripcion) {
            this.cuentaBancariaDescripcion = cuentaBancariaDescripcion;
            return this;
        }

        public Builder setConfiguracionPresupuestalEstado(String configuracionPresupuestalEstado) {
            this.configuracionPresupuestalEstado = configuracionPresupuestalEstado;
            return this;
        }

        public Builder setJornadaDescripcion(String jornadaDescripcion) {
            this.jornadaDescripcion = jornadaDescripcion;
            return this;
        }

        public Builder setPlazaClave(String plazaClave) {
            this.plazaClave = plazaClave;
            return this;
        }

        public Builder setPlazaNombre(String plazaNombre) {
            this.plazaNombre = plazaNombre;
            return this;
        }

        public Builder setSueldo(BigDecimal sueldo) {
            this.sueldo = sueldo;
            return this;
        }

        public Builder setSueldo01(BigDecimal sueldo01) {
            this.sueldo01 = sueldo01;
            return this;
        }

        public Builder setSueldo14(BigDecimal sueldo14) {
            this.sueldo14 = sueldo14;
            return this;
        }

        public Builder setRiesgoPuestoDescripcion(String riesgoPuestoDescripcion) {
            this.riesgoPuestoDescripcion = riesgoPuestoDescripcion;
            return this;
        }

        public Builder setTipoTabuladorDescripcion(String tipoTabuladorDescripcion) {
            this.tipoTabuladorDescripcion = tipoTabuladorDescripcion;
            return this;
        }

        public Builder setTipoPeriodo(String tipoPeriodo) {
            this.tipoPeriodo = tipoPeriodo;
            return this;
        }

        public DetalleEmpleadoDto construir() {
            return new DetalleEmpleadoDto(nombreCompleto, rfc, curp, fechaNacimiento, telefono, fechaAlta, direccionCompleta, tipoSangre, nacionalidad, estadoCivil, sexo, estatura, peso, estatus, correoElectronico, fechaIngreso, tienePersonaDependiente, idDatoPersonal, numeroConyuge, numeroPadre, numeroHijo, numeroOtroParentesco, numeroCuenta, banco, numeroIdentificadorBiometrico, tipoEmpleado, claveCobro, metodoPago, numeroVacante, inventarioVacanteDisponible, codigoVacante, folioVacante, programa, adscripcion, areaAdscripcion, lugarAdscripcion, actividad, funcion, clues, seguroPopular, tipoJornada, proyectoDescripcion, dependenciaDescripccion, unidadResponsableDescripcion, tipoContratacionCodigo, puestoGeneralCodigo, fuenteFinanciamientoDescripcion, subfuenteFinanciamientoDescripcion, tipoRecursoDescripcion, idDatoLaboral, centroResponsabilidadDescripcion, centroResponsabilidadClave, cuentaBancariaClaveCuenta, cuentaBancariaBanco, cuentaBancariaDescripcion, configuracionPresupuestalEstado, jornadaDescripcion, plazaClave, plazaNombre, sueldo, sueldo01, sueldo14, riesgoPuestoDescripcion, tipoTabuladorDescripcion, tipoPeriodo);
        }
    }

}
