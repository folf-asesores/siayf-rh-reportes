/*
 * ProductoNominaEntity.java
 * Creado el 07/nov/2017 6:13:08 AM
 * 
 */

package siayf.rh.reportes.persistencia.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "productos_nomina")
public class ProductoNominaEntity implements Serializable {

    private static final long serialVersionUID = -4986769165219343071L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto_nomina")
    private Integer idProductoNomina;
    @Column(name = "id_tipo_periodo")
    private Integer idTipoPeriodo;
    @Column(name = "id_periodo_calendario")
    private Integer idPeriodoCalendario;
    @Column(name = "id_fuente_financiamiento")
    private Integer idFuenteFinanciamiento;
    @Column(name = "id_subfuente_financiamiento")
    private Integer idSubfuenteFinanciamiento;
    @Column(name = "id_tipo_nomina")
    private Integer idTipoNomina;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "id_ejercicio_fiscal")
    private Integer idEjercicioFiscal;
    @Column(name = "id_estatus_producto_nomina")
    private Integer idEstatusProductoNomina;
    @Column(name = "id_banco")
    private Integer idBanco;
    @Column(name = "id_cuenta_bancaria")
    private Integer idCuentaBancaria;
    @Column(name = "id_producto_nomina_parent")
    private Integer idProductoNominaParent;
    @Column(name = "id_tipo_contratacion")
    private Integer idTipoContratacion;
    @Size(max = 32)
    @Column(name = "nombre_producto")
    private String nombreProducto;
    @Column(name = "numero_periodo")
    private Integer numeroPeriodo;
    @Column(name = "ejercicio_fiscal")
    private Integer ejercicioFiscal;
    @Column(name = "inicio_periodo")
    @Temporal(TemporalType.DATE)
    private Date inicioPeriodo;
    @Column(name = "fin_periodo")
    @Temporal(TemporalType.DATE)
    private Date finPeriodo;
    @Column(name = "inicio_rango_faltas")
    @Temporal(TemporalType.DATE)
    private Date inicioRangoFaltas;
    @Column(name = "fin_rango_faltas")
    @Temporal(TemporalType.DATE)
    private Date finRangoFaltas;
    @Column(name = "cambiar_subfuente_financiamiento")
    private Boolean cambiarSubfuenteFinanciamiento;
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @Column(name = "numero_inicio_cheques")
    private Integer numeroInicioCheques;
    @Column(name = "total_percepciones")
    private BigDecimal totalPercepciones;
    @Column(name = "total_deducciones")
    private BigDecimal totalDeducciones;
    @Column(name = "total_neto")
    private BigDecimal totalNeto;
    @Column(name = "total_subsidio")
    private BigDecimal totalSubsidio;
    @Column(name = "total_isr")
    private BigDecimal totalIsr;
    @Column(name = "id_tipo_recurso_sat")
    private Integer idTipoRecursoSat;
    @Column(name = "porcentaje_origen_mixto")
    private Integer porcentajeOrigenMixto;
    @Column(name = "dias_prima_vacasional")
    private BigDecimal diasPrimaVacasional;
    @Column(name = "dias_exento_prima_vacasional")
    private BigDecimal diasExentoPrimaVacasional;
    @Column(name = "dias_aguinaldo")
    private BigDecimal diasAguinaldo;
    @Column(name = "dias_exento_aguinaldo")
    private BigDecimal diasExentoAguinaldo;

    public ProductoNominaEntity() {
    }

    public ProductoNominaEntity(Integer idProductoNomina) {
        this.idProductoNomina = idProductoNomina;
    }

    public Integer getIdProductoNomina() {
        return idProductoNomina;
    }

    public void setIdProductoNomina(Integer idProductoNomina) {
        this.idProductoNomina = idProductoNomina;
    }

    public Integer getIdTipoPeriodo() {
        return idTipoPeriodo;
    }

    public void setIdTipoPeriodo(Integer idTipoPeriodo) {
        this.idTipoPeriodo = idTipoPeriodo;
    }

    public Integer getIdPeriodoCalendario() {
        return idPeriodoCalendario;
    }

    public void setIdPeriodoCalendario(Integer idPeriodoCalendario) {
        this.idPeriodoCalendario = idPeriodoCalendario;
    }

    public Integer getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    public Integer getIdSubfuenteFinanciamiento() {
        return idSubfuenteFinanciamiento;
    }

    public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
    }

    public Integer getIdTipoNomina() {
        return idTipoNomina;
    }

    public void setIdTipoNomina(Integer idTipoNomina) {
        this.idTipoNomina = idTipoNomina;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEjercicioFiscal() {
        return idEjercicioFiscal;
    }

    public void setIdEjercicioFiscal(Integer idEjercicioFiscal) {
        this.idEjercicioFiscal = idEjercicioFiscal;
    }

    public Integer getIdEstatusProductoNomina() {
        return idEstatusProductoNomina;
    }

    public void setIdEstatusProductoNomina(Integer idEstatusProductoNomina) {
        this.idEstatusProductoNomina = idEstatusProductoNomina;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Integer getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(Integer idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public Integer getIdProductoNominaParent() {
        return idProductoNominaParent;
    }

    public void setIdProductoNominaParent(Integer idProductoNominaParent) {
        this.idProductoNominaParent = idProductoNominaParent;
    }

    public Integer getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Integer idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getNumeroPeriodo() {
        return numeroPeriodo;
    }

    public void setNumeroPeriodo(Integer numeroPeriodo) {
        this.numeroPeriodo = numeroPeriodo;
    }

    public Integer getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(Integer ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    public void setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    public Date getFinPeriodo() {
        return finPeriodo;
    }

    public void setFinPeriodo(Date finPeriodo) {
        this.finPeriodo = finPeriodo;
    }

    public Date getInicioRangoFaltas() {
        return inicioRangoFaltas;
    }

    public void setInicioRangoFaltas(Date inicioRangoFaltas) {
        this.inicioRangoFaltas = inicioRangoFaltas;
    }

    public Date getFinRangoFaltas() {
        return finRangoFaltas;
    }

    public void setFinRangoFaltas(Date finRangoFaltas) {
        this.finRangoFaltas = finRangoFaltas;
    }

    public Boolean getCambiarSubfuenteFinanciamiento() {
        return cambiarSubfuenteFinanciamiento;
    }

    public void setCambiarSubfuenteFinanciamiento(Boolean cambiarSubfuenteFinanciamiento) {
        this.cambiarSubfuenteFinanciamiento = cambiarSubfuenteFinanciamiento;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getNumeroInicioCheques() {
        return numeroInicioCheques;
    }

    public void setNumeroInicioCheques(Integer numeroInicioCheques) {
        this.numeroInicioCheques = numeroInicioCheques;
    }

    public BigDecimal getTotalPercepciones() {
        return totalPercepciones;
    }

    public void setTotalPercepciones(BigDecimal totalPercepciones) {
        this.totalPercepciones = totalPercepciones;
    }

    public BigDecimal getTotalDeducciones() {
        return totalDeducciones;
    }

    public void setTotalDeducciones(BigDecimal totalDeducciones) {
        this.totalDeducciones = totalDeducciones;
    }

    public BigDecimal getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(BigDecimal totalNeto) {
        this.totalNeto = totalNeto;
    }

    public BigDecimal getTotalSubsidio() {
        return totalSubsidio;
    }

    public void setTotalSubsidio(BigDecimal totalSubsidio) {
        this.totalSubsidio = totalSubsidio;
    }

    public BigDecimal getTotalIsr() {
        return totalIsr;
    }

    public void setTotalIsr(BigDecimal totalIsr) {
        this.totalIsr = totalIsr;
    }

    public Integer getIdTipoRecursoSat() {
        return idTipoRecursoSat;
    }

    public void setIdTipoRecursoSat(Integer idTipoRecursoSat) {
        this.idTipoRecursoSat = idTipoRecursoSat;
    }

    public Integer getPorcentajeOrigenMixto() {
        return porcentajeOrigenMixto;
    }

    public void setPorcentajeOrigenMixto(Integer porcentajeOrigenMixto) {
        this.porcentajeOrigenMixto = porcentajeOrigenMixto;
    }

    public BigDecimal getDiasPrimaVacasional() {
        return diasPrimaVacasional;
    }

    public void setDiasPrimaVacasional(BigDecimal diasPrimaVacasional) {
        this.diasPrimaVacasional = diasPrimaVacasional;
    }

    public BigDecimal getDiasExentoPrimaVacasional() {
        return diasExentoPrimaVacasional;
    }

    public void setDiasExentoPrimaVacasional(BigDecimal diasExentoPrimaVacasional) {
        this.diasExentoPrimaVacasional = diasExentoPrimaVacasional;
    }

    public BigDecimal getDiasAguinaldo() {
        return diasAguinaldo;
    }

    public void setDiasAguinaldo(BigDecimal diasAguinaldo) {
        this.diasAguinaldo = diasAguinaldo;
    }

    public BigDecimal getDiasExentoAguinaldo() {
        return diasExentoAguinaldo;
    }

    public void setDiasExentoAguinaldo(BigDecimal diasExentoAguinaldo) {
        this.diasExentoAguinaldo = diasExentoAguinaldo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.idProductoNomina);
        hash = 19 * hash + Objects.hashCode(this.idTipoPeriodo);
        hash = 19 * hash + Objects.hashCode(this.idPeriodoCalendario);
        hash = 19 * hash + Objects.hashCode(this.idFuenteFinanciamiento);
        hash = 19 * hash + Objects.hashCode(this.idSubfuenteFinanciamiento);
        hash = 19 * hash + Objects.hashCode(this.idTipoNomina);
        hash = 19 * hash + Objects.hashCode(this.idUsuario);
        hash = 19 * hash + Objects.hashCode(this.idEjercicioFiscal);
        hash = 19 * hash + Objects.hashCode(this.idEstatusProductoNomina);
        hash = 19 * hash + Objects.hashCode(this.idBanco);
        hash = 19 * hash + Objects.hashCode(this.idCuentaBancaria);
        hash = 19 * hash + Objects.hashCode(this.idProductoNominaParent);
        hash = 19 * hash + Objects.hashCode(this.idTipoContratacion);
        hash = 19 * hash + Objects.hashCode(this.nombreProducto);
        hash = 19 * hash + Objects.hashCode(this.numeroPeriodo);
        hash = 19 * hash + Objects.hashCode(this.ejercicioFiscal);
        hash = 19 * hash + Objects.hashCode(this.inicioPeriodo);
        hash = 19 * hash + Objects.hashCode(this.finPeriodo);
        hash = 19 * hash + Objects.hashCode(this.inicioRangoFaltas);
        hash = 19 * hash + Objects.hashCode(this.finRangoFaltas);
        hash = 19 * hash + Objects.hashCode(this.cambiarSubfuenteFinanciamiento);
        hash = 19 * hash + Objects.hashCode(this.fechaPago);
        hash = 19 * hash + Objects.hashCode(this.numeroInicioCheques);
        hash = 19 * hash + Objects.hashCode(this.totalPercepciones);
        hash = 19 * hash + Objects.hashCode(this.totalDeducciones);
        hash = 19 * hash + Objects.hashCode(this.totalNeto);
        hash = 19 * hash + Objects.hashCode(this.totalSubsidio);
        hash = 19 * hash + Objects.hashCode(this.totalIsr);
        hash = 19 * hash + Objects.hashCode(this.idTipoRecursoSat);
        hash = 19 * hash + Objects.hashCode(this.porcentajeOrigenMixto);
        hash = 19 * hash + Objects.hashCode(this.diasPrimaVacasional);
        hash = 19 * hash + Objects.hashCode(this.diasExentoPrimaVacasional);
        hash = 19 * hash + Objects.hashCode(this.diasAguinaldo);
        hash = 19 * hash + Objects.hashCode(this.diasExentoAguinaldo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductoNominaEntity other = (ProductoNominaEntity) obj;
        if (!Objects.equals(this.nombreProducto, other.nombreProducto)) {
            return false;
        }
        if (!Objects.equals(this.idProductoNomina, other.idProductoNomina)) {
            return false;
        }
        if (!Objects.equals(this.idTipoPeriodo, other.idTipoPeriodo)) {
            return false;
        }
        if (!Objects.equals(this.idPeriodoCalendario, other.idPeriodoCalendario)) {
            return false;
        }
        if (!Objects.equals(this.idFuenteFinanciamiento, other.idFuenteFinanciamiento)) {
            return false;
        }
        if (!Objects.equals(this.idSubfuenteFinanciamiento, other.idSubfuenteFinanciamiento)) {
            return false;
        }
        if (!Objects.equals(this.idTipoNomina, other.idTipoNomina)) {
            return false;
        }
        if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }
        if (!Objects.equals(this.idEjercicioFiscal, other.idEjercicioFiscal)) {
            return false;
        }
        if (!Objects.equals(this.idEstatusProductoNomina, other.idEstatusProductoNomina)) {
            return false;
        }
        if (!Objects.equals(this.idBanco, other.idBanco)) {
            return false;
        }
        if (!Objects.equals(this.idCuentaBancaria, other.idCuentaBancaria)) {
            return false;
        }
        if (!Objects.equals(this.idProductoNominaParent, other.idProductoNominaParent)) {
            return false;
        }
        if (!Objects.equals(this.idTipoContratacion, other.idTipoContratacion)) {
            return false;
        }
        if (!Objects.equals(this.numeroPeriodo, other.numeroPeriodo)) {
            return false;
        }
        if (!Objects.equals(this.ejercicioFiscal, other.ejercicioFiscal)) {
            return false;
        }
        if (!Objects.equals(this.inicioPeriodo, other.inicioPeriodo)) {
            return false;
        }
        if (!Objects.equals(this.finPeriodo, other.finPeriodo)) {
            return false;
        }
        if (!Objects.equals(this.inicioRangoFaltas, other.inicioRangoFaltas)) {
            return false;
        }
        if (!Objects.equals(this.finRangoFaltas, other.finRangoFaltas)) {
            return false;
        }
        if (!Objects.equals(this.cambiarSubfuenteFinanciamiento, other.cambiarSubfuenteFinanciamiento)) {
            return false;
        }
        if (!Objects.equals(this.fechaPago, other.fechaPago)) {
            return false;
        }
        if (!Objects.equals(this.numeroInicioCheques, other.numeroInicioCheques)) {
            return false;
        }
        if (!Objects.equals(this.totalPercepciones, other.totalPercepciones)) {
            return false;
        }
        if (!Objects.equals(this.totalDeducciones, other.totalDeducciones)) {
            return false;
        }
        if (!Objects.equals(this.totalNeto, other.totalNeto)) {
            return false;
        }
        if (!Objects.equals(this.totalSubsidio, other.totalSubsidio)) {
            return false;
        }
        if (!Objects.equals(this.totalIsr, other.totalIsr)) {
            return false;
        }
        if (!Objects.equals(this.idTipoRecursoSat, other.idTipoRecursoSat)) {
            return false;
        }
        if (!Objects.equals(this.porcentajeOrigenMixto, other.porcentajeOrigenMixto)) {
            return false;
        }
        if (!Objects.equals(this.diasPrimaVacasional, other.diasPrimaVacasional)) {
            return false;
        }
        if (!Objects.equals(this.diasExentoPrimaVacasional, other.diasExentoPrimaVacasional)) {
            return false;
        }
        if (!Objects.equals(this.diasAguinaldo, other.diasAguinaldo)) {
            return false;
        }
        return Objects.equals(this.diasExentoAguinaldo, other.diasExentoAguinaldo);
    }

    @Override
    public String toString() {
        return "ProductosNominaEntity{"
                + "idProductoNomina : " + idProductoNomina
                + ", idTipoPeriodo : " + idTipoPeriodo
                + ", idPeriodoCalendario : " + idPeriodoCalendario
                + ", idFuenteFinanciamiento : " + idFuenteFinanciamiento
                + ", idSubfuenteFinanciamiento : " + idSubfuenteFinanciamiento
                + ", idTipoNomina : " + idTipoNomina
                + ", idUsuario : " + idUsuario
                + ", idEjercicioFiscal : " + idEjercicioFiscal
                + ", idEstatusProductoNomina : " + idEstatusProductoNomina
                + ", idBanco : " + idBanco
                + ", idCuentaBancaria : " + idCuentaBancaria
                + ", idProductoNominaParent : " + idProductoNominaParent
                + ", idTipoContratacion : " + idTipoContratacion
                + ", nombreProducto : " + nombreProducto
                + ", numeroPeriodo : " + numeroPeriodo
                + ", ejercicioFiscal : " + ejercicioFiscal
                + ", inicioPeriodo : " + inicioPeriodo
                + ", finPeriodo : " + finPeriodo
                + ", inicioRangoFaltas : " + inicioRangoFaltas
                + ", finRangoFaltas : " + finRangoFaltas
                + ", cambiarSubfuenteFinanciamiento : " + cambiarSubfuenteFinanciamiento
                + ", fechaPago : " + fechaPago
                + ", numeroInicioCheques : " + numeroInicioCheques
                + ", totalPercepciones : " + totalPercepciones
                + ", totalDeducciones : " + totalDeducciones
                + ", totalNeto : " + totalNeto
                + ", totalSubsidio : " + totalSubsidio
                + ", totalIsr : " + totalIsr
                + ", idTipoRecursoSat : " + idTipoRecursoSat
                + ", porcentajeOrigenMixto : " + porcentajeOrigenMixto
                + ", diasPrimaVacasional : " + diasPrimaVacasional
                + ", diasExentoPrimaVacasional : " + diasExentoPrimaVacasional
                + ", diasAguinaldo : " + diasAguinaldo
                + ", diasExentoAguinaldo : " + diasExentoAguinaldo
                + '}';
    }

}
