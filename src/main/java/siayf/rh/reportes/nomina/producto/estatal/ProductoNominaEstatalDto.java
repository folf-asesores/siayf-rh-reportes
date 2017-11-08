/*
 * ProductoNominaProgramaDto.java
 * Creado el
 * 
 */

package siayf.rh.reportes.nomina.producto.estatal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Eduardo Chuc Mex (Lic.Eduardo_Mex@hotmail.com)
 */
public class ProductoNominaEstatalDto implements Serializable {

    private static final long serialVersionUID = -4410052793284349733L;

    private String rfc;
    private String nombreEmpleado;
    private Date fechaIngreso;
    private String centroResponsabilidad;
    private String conceptoCentroResponsabilidad;
    private String funcion;
    private String programa;
    private String fuente;
    private String subfuente;
    private BigDecimal honorariosAsimilables;
    private BigDecimal suplencias;
    private BigDecimal diasEconomicos;
    private BigDecimal percepcionComplementaria;
    private BigDecimal bono;
    private BigDecimal aguinaldo;
    private BigDecimal subsidio;
    private BigDecimal primaVacacional;
    private BigDecimal bonificacionFalta;
    private BigDecimal retroactivo;
    private BigDecimal otros;
    private BigDecimal faltasRetardos;
    private BigDecimal isr;
    private BigDecimal responsabilidades;
    private BigDecimal prestamo;
    private BigDecimal juicioMercantil;
    private BigDecimal cuotaSindical;
    private BigDecimal pensionAlimenticia;

    public ProductoNominaEstatalDto() {
        this.rfc = "";
        this.nombreEmpleado = "";
        this.fechaIngreso = Calendar.getInstance().getTime();
        this.centroResponsabilidad = "";
        this.conceptoCentroResponsabilidad = "";
        this.funcion = "";
        this.programa = "";
        this.honorariosAsimilables = BigDecimal.ZERO;
        this.suplencias = BigDecimal.ZERO;
        this.diasEconomicos = BigDecimal.ZERO;
        this.percepcionComplementaria = BigDecimal.ZERO;
        this.bono = BigDecimal.ZERO;
        this.aguinaldo = BigDecimal.ZERO;
        this.subsidio = BigDecimal.ZERO;
        this.primaVacacional = BigDecimal.ZERO;
        this.bonificacionFalta = BigDecimal.ZERO;
        this.retroactivo = BigDecimal.ZERO;
        this.otros = BigDecimal.ZERO;
        this.faltasRetardos = BigDecimal.ZERO;
        this.isr = BigDecimal.ZERO;
        this.responsabilidades = BigDecimal.ZERO;
        this.prestamo = BigDecimal.ZERO;
        this.juicioMercantil = BigDecimal.ZERO;
        this.cuotaSindical = BigDecimal.ZERO;
        this.pensionAlimenticia = BigDecimal.ZERO;
    }

    // Getters and Setters

    public String getRfc() {
        return this.rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombreEmpleado() {
        return this.nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public Date getFechaIngreso() {
        return this.fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCentroResponsabilidad() {
        return this.centroResponsabilidad;
    }

    public void setCentroResponsabilidad(String centroResponsabilidad) {
        this.centroResponsabilidad = centroResponsabilidad;
    }

    public String getConceptoCentroResponsabilidad() {
        return this.conceptoCentroResponsabilidad;
    }

    public void setConceptoCentroResponsabilidad(String conceptoCentroResponsabilidad) {
        this.conceptoCentroResponsabilidad = conceptoCentroResponsabilidad;
    }

    public String getFuncion() {
        return this.funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getPrograma() {
        return this.programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getFuente() {
        return this.fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getSubfuente() {
        return this.subfuente;
    }

    public void setSubfuente(String subfuente) {
        this.subfuente = subfuente;
    }

    public BigDecimal getHonorariosAsimilables() {
        return this.honorariosAsimilables;
    }

    public void setHonorariosAsimilables(BigDecimal honorariosAsimilables) {
        this.honorariosAsimilables = honorariosAsimilables == null ? BigDecimal.ZERO : honorariosAsimilables;
    }

    public BigDecimal getSuplencias() {
        return this.suplencias;
    }

    public void setSuplencias(BigDecimal suplencias) {
        this.suplencias = suplencias == null ? BigDecimal.ZERO : suplencias;
    }

    public BigDecimal getDiasEconomicos() {
        return this.diasEconomicos;
    }

    public void setDiasEconomicos(BigDecimal diasEconomicos) {
        this.diasEconomicos = diasEconomicos == null ? BigDecimal.ZERO : diasEconomicos;
    }

    public BigDecimal getPercepcionComplementaria() {
        return this.percepcionComplementaria;
    }

    public void setPercepcionComplementaria(BigDecimal percepcionComplementaria) {
        this.percepcionComplementaria = percepcionComplementaria == null ? BigDecimal.ZERO : percepcionComplementaria;
    }

    public BigDecimal getBono() {
        return this.bono;
    }

    public void setBono(BigDecimal bono) {
        this.bono = bono == null ? BigDecimal.ZERO : bono;
    }

    public BigDecimal getAguinaldo() {
        return this.aguinaldo;
    }

    public void setAguinaldo(BigDecimal aguinaldo) {
        this.aguinaldo = aguinaldo == null ? BigDecimal.ZERO : aguinaldo;
    }

    public BigDecimal getSubsidio() {
        return this.subsidio;
    }

    public void setSubsidio(BigDecimal subsidio) {
        this.subsidio = subsidio == null ? BigDecimal.ZERO : subsidio;
    }

    public BigDecimal getPrimaVacacional() {
        return this.primaVacacional;
    }

    public void setPrimaVacacional(BigDecimal primaVacacional) {
        this.primaVacacional = primaVacacional == null ? BigDecimal.ZERO : primaVacacional;
    }

    public BigDecimal getBonificacionFalta() {
        return this.bonificacionFalta;
    }

    public void setBonificacionFalta(BigDecimal bonificacionFalta) {
        this.bonificacionFalta = bonificacionFalta == null ? BigDecimal.ZERO : bonificacionFalta;
    }

    public BigDecimal getRetroactivo() {
        return this.retroactivo;
    }

    public void setRetroactivo(BigDecimal retroactivo) {
        this.retroactivo = retroactivo == null ? BigDecimal.ZERO : retroactivo;
    }

    public BigDecimal getOtros() {
        return this.otros;
    }

    public void setOtros(BigDecimal otros) {
        this.otros = otros == null ? BigDecimal.ZERO : otros;
    }

    public BigDecimal getFaltasRetardos() {
        return this.faltasRetardos;
    }

    public void setFaltasRetardos(BigDecimal faltasRetardos) {
        this.faltasRetardos = faltasRetardos == null ? BigDecimal.ZERO : faltasRetardos;
    }

    public BigDecimal getIsr() {
        return this.isr;
    }

    public void setIsr(BigDecimal isr) {
        this.isr = isr == null ? BigDecimal.ZERO : isr;
    }

    public BigDecimal getResponsabilidades() {
        return this.responsabilidades;
    }

    public void setResponsabilidades(BigDecimal responsabilidades) {
        this.responsabilidades = responsabilidades;
    }

    public BigDecimal getPrestamo() {
        return this.prestamo;
    }

    public void setPrestamo(BigDecimal prestamo) {
        this.prestamo = prestamo == null ? BigDecimal.ZERO : prestamo;
    }

    public BigDecimal getJuicioMercantil() {
        return this.juicioMercantil;
    }

    public void setJuicioMercantil(BigDecimal juicioMercantil) {
        this.juicioMercantil = juicioMercantil == null ? BigDecimal.ZERO : juicioMercantil;
    }

    public BigDecimal getCuotaSindical() {
        return this.cuotaSindical;
    }

    public void setCuotaSindical(BigDecimal cuotaSindical) {
        this.cuotaSindical = cuotaSindical == null ? BigDecimal.ZERO : cuotaSindical;
    }

    public BigDecimal getPensionAlimenticia() {
        return this.pensionAlimenticia;
    }

    public void setPensionAlimenticia(BigDecimal pensionAlimenticia) {
        this.pensionAlimenticia = pensionAlimenticia == null ? BigDecimal.ZERO : pensionAlimenticia;
    }

    public BigDecimal getTotal() {
        BigDecimal percepciones = BigDecimal.ZERO;
        percepciones = percepciones.add(this.honorariosAsimilables == null ? BigDecimal.ZERO : this.honorariosAsimilables);
        percepciones = percepciones.add(this.suplencias == null ? BigDecimal.ZERO : this.suplencias);
        percepciones = percepciones.add(this.diasEconomicos == null ? BigDecimal.ZERO : this.diasEconomicos);
        percepciones = percepciones.add(this.percepcionComplementaria == null ? BigDecimal.ZERO : this.percepcionComplementaria);
        percepciones = percepciones.add(this.bono == null ? BigDecimal.ZERO : this.bono);
        percepciones = percepciones.add(this.aguinaldo == null ? BigDecimal.ZERO : this.aguinaldo);
        percepciones = percepciones.add(this.subsidio == null ? BigDecimal.ZERO : this.subsidio);
        percepciones = percepciones.add(this.primaVacacional == null ? BigDecimal.ZERO : this.primaVacacional);
        percepciones = percepciones.add(this.bonificacionFalta == null ? BigDecimal.ZERO : this.bonificacionFalta);
        percepciones = percepciones.add(this.retroactivo == null ? BigDecimal.ZERO : this.retroactivo);
        percepciones = percepciones.add(this.otros == null ? BigDecimal.ZERO : this.otros);

        BigDecimal deducciones = BigDecimal.ZERO;
        deducciones = deducciones.add(this.faltasRetardos == null ? BigDecimal.ZERO : this.faltasRetardos);
        deducciones = deducciones.add(this.isr == null ? BigDecimal.ZERO : this.isr);
        deducciones = deducciones.add(this.responsabilidades == null ? BigDecimal.ZERO : this.responsabilidades);
        deducciones = deducciones.add(this.prestamo == null ? BigDecimal.ZERO : this.prestamo);
        deducciones = deducciones.add(this.juicioMercantil == null ? BigDecimal.ZERO : this.juicioMercantil);
        deducciones = deducciones.add(this.cuotaSindical == null ? BigDecimal.ZERO : this.cuotaSindical);
        deducciones = deducciones.add(this.pensionAlimenticia == null ? BigDecimal.ZERO : this.pensionAlimenticia);

        return percepciones.subtract(deducciones);
    }

    @Override
    public String toString() {
        return "ProductosNominaExcelDTO[" + "rfc=" + this.rfc + ", nombreEmpleado=" + this.nombreEmpleado + ", fechaIngreso=" + this.fechaIngreso
                + ", centroResponsabilidad=" + this.centroResponsabilidad + ", conceptoCentroResponsabilidad=" + this.conceptoCentroResponsabilidad
                + ", funcion=" + this.funcion + ", programa=" + this.programa + ", honorariosAsimilables=" + this.honorariosAsimilables + ", suplencias="
                + this.suplencias + ", diasEconomicos=" + this.diasEconomicos + ", percepcionComplementaria=" + this.percepcionComplementaria + ", bono="
                + this.bono + ", aguinaldo=" + this.aguinaldo + ", subsidio=" + this.subsidio + ", primaVacacional=" + this.primaVacacional
                + ", bonificacionFalta=" + this.bonificacionFalta + ", retroactivo=" + this.retroactivo + ", otros=" + this.otros + ", faltasRetardos="
                + this.faltasRetardos + ", isr=" + this.isr + ", responsabilidades=" + this.responsabilidades + ", prestamo=" + this.prestamo
                + ", juicioMercantil=" + this.juicioMercantil + ", cuotaSindical=" + this.cuotaSindical + ", pensionAlimenticia=" + this.pensionAlimenticia
                + ", total=" + getTotal() + ']';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.rfc);
        hash = 43 * hash + Objects.hashCode(this.nombreEmpleado);
        hash = 43 * hash + Objects.hashCode(this.fechaIngreso);
        hash = 43 * hash + Objects.hashCode(this.centroResponsabilidad);
        hash = 43 * hash + Objects.hashCode(this.conceptoCentroResponsabilidad);
        hash = 43 * hash + Objects.hashCode(this.funcion);
        hash = 43 * hash + Objects.hashCode(this.programa);
        hash = 43 * hash + Objects.hashCode(this.honorariosAsimilables);
        hash = 43 * hash + Objects.hashCode(this.suplencias);
        hash = 43 * hash + Objects.hashCode(this.diasEconomicos);
        hash = 43 * hash + Objects.hashCode(this.percepcionComplementaria);
        hash = 43 * hash + Objects.hashCode(this.bono);
        hash = 43 * hash + Objects.hashCode(this.aguinaldo);
        hash = 43 * hash + Objects.hashCode(this.subsidio);
        hash = 43 * hash + Objects.hashCode(this.primaVacacional);
        hash = 43 * hash + Objects.hashCode(this.bonificacionFalta);
        hash = 43 * hash + Objects.hashCode(this.retroactivo);
        hash = 43 * hash + Objects.hashCode(this.otros);
        hash = 43 * hash + Objects.hashCode(this.faltasRetardos);
        hash = 43 * hash + Objects.hashCode(this.isr);
        hash = 43 * hash + Objects.hashCode(this.responsabilidades);
        hash = 43 * hash + Objects.hashCode(this.prestamo);
        hash = 43 * hash + Objects.hashCode(this.juicioMercantil);
        hash = 43 * hash + Objects.hashCode(this.cuotaSindical);
        hash = 43 * hash + Objects.hashCode(this.pensionAlimenticia);
        hash = 43 * hash + Objects.hashCode(getTotal());
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
        final ProductoNominaEstatalDto other = (ProductoNominaEstatalDto) obj;
        if (!Objects.equals(this.rfc, other.rfc)) {
            return false;
        }
        if (!Objects.equals(this.nombreEmpleado, other.nombreEmpleado)) {
            return false;
        }
        if (!Objects.equals(this.centroResponsabilidad, other.centroResponsabilidad)) {
            return false;
        }
        if (!Objects.equals(this.conceptoCentroResponsabilidad, other.conceptoCentroResponsabilidad)) {
            return false;
        }
        if (!Objects.equals(this.funcion, other.funcion)) {
            return false;
        }
        if (!Objects.equals(this.programa, other.programa)) {
            return false;
        }
        if (!Objects.equals(this.fechaIngreso, other.fechaIngreso)) {
            return false;
        }
        if (!Objects.equals(this.honorariosAsimilables, other.honorariosAsimilables)) {
            return false;
        }
        if (!Objects.equals(this.suplencias, other.suplencias)) {
            return false;
        }
        if (!Objects.equals(this.diasEconomicos, other.diasEconomicos)) {
            return false;
        }
        if (!Objects.equals(this.percepcionComplementaria, other.percepcionComplementaria)) {
            return false;
        }
        if (!Objects.equals(this.bono, other.bono)) {
            return false;
        }
        if (!Objects.equals(this.aguinaldo, other.aguinaldo)) {
            return false;
        }
        if (!Objects.equals(this.subsidio, other.subsidio)) {
            return false;
        }
        if (!Objects.equals(this.primaVacacional, other.primaVacacional)) {
            return false;
        }
        if (!Objects.equals(this.bonificacionFalta, other.bonificacionFalta)) {
            return false;
        }
        if (!Objects.equals(this.retroactivo, other.retroactivo)) {
            return false;
        }
        if (!Objects.equals(this.otros, other.otros)) {
            return false;
        }
        if (!Objects.equals(this.faltasRetardos, other.faltasRetardos)) {
            return false;
        }
        if (!Objects.equals(this.isr, other.isr)) {
            return false;
        }
        if (!Objects.equals(this.responsabilidades, other.responsabilidades)) {
            return false;
        }
        if (!Objects.equals(this.prestamo, other.prestamo)) {
            return false;
        }
        if (!Objects.equals(this.juicioMercantil, other.juicioMercantil)) {
            return false;
        }
        if (!Objects.equals(this.cuotaSindical, other.cuotaSindical)) {
            return false;
        }
        if (!Objects.equals(this.pensionAlimenticia, other.pensionAlimenticia)) {
            return false;
        }
        return Objects.equals(this.getTotal(), other.getTotal());
    }
}