/*
 * ProductoNominaProgramaExcel.java
 * Creado el 01/nov/2017 7:18:42 AM
 * 
 */

package siayf.rh.reportes.nomina.producto.estatal;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import siayf.rh.reportes.core.excel.ExcelPlantillaReporte;
import siayf.rh.reportes.core.excel.ReporteVacio;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ProductoNominaEstatalExcel extends ExcelPlantillaReporte<ProductoNominaEstatalDto> implements Serializable {

    private static final long serialVersionUID = 9082692081835328085L;
    private static final Logger LOGGER = Logger.getLogger(ProductoNominaEstatalExcel.class.getName());

    // Fila en la que inicia el detalle.
    private static final int FILA_INICIO_DETALLE = 1;

    /**
     * columna de cada detalles
     */
    private static final int RFC = 0;
    private static final int NOMBRE_EMPLEADO = 1;
    private static final int FECHA_INGRESO = 2;
    private static final int CENTRO_RESPONSABILIDAD = 3;
    private static final int CONCEPTO_CENTRO_RESPONSABILIDAD = 4;
    private static final int FUNCION = 5;
    private static final int PROGRAMA = 6;
    private static final int FUENTE = 7;
    private static final int SUBFUENTE = 8;
    private static final int HONORARIOS_ASIMILABLES = 9;
    private static final int SUPLENCIAS = 10;
    private static final int DIAS_ECONOMICOS = 11;
    private static final int PERCEPCION_COMPLEMENTARIA = 12;
    private static final int BONO = 13;
    private static final int AGUINALDO = 14;
    private static final int SUBSIDIO = 15;
    private static final int PRIMA_VACACIONAL = 16;
    private static final int BONIFICACION_FALTA = 17;
    private static final int RETROACTIVO = 18;
    private static final int OTROS = 19;
    private static final int FALTAS_RETARDOS = 20;
    private static final int ISR = 21;
    private static final int RESPONSABILIDADES = 22;
    private static final int PRESTAMO = 23;
    private static final int JUICIO_MERCANTIL = 24;
    private static final int CUOTA_SINDICAL = 25;
    private static final int PENSION_ALIMENTICIA = 26;
    private static final int COLUMNA_TOTAL = 27;

    /**
     * Totales
     */
    private BigDecimal TOTAL_HONORARIOS_ASIMILABLES = BigDecimal.ZERO;
    private BigDecimal TOTAL_SUPLENCIAS = BigDecimal.ZERO;
    private BigDecimal TOTAL_DIAS_ECONOMICOS = BigDecimal.ZERO;
    private BigDecimal TOTAL_PERCEPCION_COMPLEMENTARIA = BigDecimal.ZERO;
    private BigDecimal TOTAL_BONO = BigDecimal.ZERO;
    private BigDecimal TOTAL_AGUINALDO = BigDecimal.ZERO;
    private BigDecimal TOTAL_SUBSIDIO = BigDecimal.ZERO;
    private BigDecimal TOTAL_PRIMA_VACACIONAL = BigDecimal.ZERO;
    private BigDecimal TOTAL_BONIFICACION_FALTA = BigDecimal.ZERO;
    private BigDecimal TOTAL_RETROACTIVO = BigDecimal.ZERO;
    private BigDecimal TOTAL_OTROS = BigDecimal.ZERO;
    private BigDecimal TOTAL_FALTAS_RETARDOS = BigDecimal.ZERO;
    private BigDecimal TOTAL_ISR = BigDecimal.ZERO;
    private BigDecimal TOTAL_RESPONSABILIDADES = BigDecimal.ZERO;
    private BigDecimal TOTAL_PRESTAMO = BigDecimal.ZERO;
    private BigDecimal TOTAL_JUICIO_MERCANTIL = BigDecimal.ZERO;
    private BigDecimal TOTAL_CUOTA_SINDICAL = BigDecimal.ZERO;
    private BigDecimal TOTAL_PENSION_ALIMENTICIA = BigDecimal.ZERO;
    private BigDecimal TOTALES = BigDecimal.ZERO;

    public ProductoNominaEstatalExcel() {
        super("siayf/rh/reportes/nomina/", "Producto_Nomina.xlsx", "Producto_Nomina");
    }

    // Este método lanzará una excepción porque se creo una sobre carga que se adecua al tipo de reporte.
    @Override
    public byte[] generar(List<ProductoNominaEstatalDto> detalles) {
        try {
            cargarPlantilla();
            llenarDetalles(detalles);
            return obtenerBytes();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Ocurrio un error al cargar la plantilla.\n{0}", ex);
            return ReporteVacio.obtenerBytes();
        }
    }

    // Este método lanzará una excepción porque se creo una sobre carga que se adecua al tipo de reporte.
    @Override
    protected void llenarDetalles(List<ProductoNominaEstatalDto> estructura) {
        int i = FILA_INICIO_DETALLE;
        int filaTotales = FILA_INICIO_DETALLE;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (ProductoNominaEstatalDto detalle : estructura) {

            Row filaDetalle = this.hoja.createRow(i);

            Cell celdaRfc = filaDetalle.createCell(RFC);
            celdaRfc.setCellValue(detalle.getRfc());

            Cell celdaNombreEmpleado = filaDetalle.createCell(NOMBRE_EMPLEADO);
            celdaNombreEmpleado.setCellValue(detalle.getNombreEmpleado());

            Cell celdaFechaIngreso = filaDetalle.createCell(FECHA_INGRESO);
            celdaFechaIngreso.setCellValue(detalle.getFechaIngreso() == null ? "" : simpleDateFormat.format(detalle.getFechaIngreso()));

            Cell celdaCentroResponsabilidad = filaDetalle.createCell(CENTRO_RESPONSABILIDAD);
            celdaCentroResponsabilidad.setCellValue(detalle.getCentroResponsabilidad());

            Cell celdaConceptoCentroResponsabilidad = filaDetalle.createCell(CONCEPTO_CENTRO_RESPONSABILIDAD);
            celdaConceptoCentroResponsabilidad.setCellValue(detalle.getConceptoCentroResponsabilidad());

            Cell celdaFuncion = filaDetalle.createCell(FUNCION);
            celdaFuncion.setCellValue(detalle.getFuncion());

            Cell celdaPrograma = filaDetalle.createCell(PROGRAMA);
            celdaPrograma.setCellValue(detalle.getPrograma());

            Cell celdaFuente = filaDetalle.createCell(FUENTE);
            celdaFuente.setCellValue(detalle.getFuente());

            Cell celdaSubfuente = filaDetalle.createCell(SUBFUENTE);
            celdaSubfuente.setCellValue(detalle.getSubfuente());

            Cell celdaHonorariosAsimilables = filaDetalle.createCell(HONORARIOS_ASIMILABLES, CellType.NUMERIC);
            celdaHonorariosAsimilables.setCellValue(detalle.getHonorariosAsimilables() == null ? 0 : detalle.getHonorariosAsimilables().doubleValue());
            this.TOTAL_HONORARIOS_ASIMILABLES = this.TOTAL_HONORARIOS_ASIMILABLES
                    .add(detalle.getHonorariosAsimilables() == null ? BigDecimal.ZERO : detalle.getHonorariosAsimilables());

            Cell celdaSuplencias = filaDetalle.createCell(SUPLENCIAS);
            celdaSuplencias.setCellValue(detalle.getSuplencias() == null ? 0 : detalle.getSuplencias().doubleValue());
            this.TOTAL_SUPLENCIAS = this.TOTAL_SUPLENCIAS.add(detalle.getSuplencias() == null ? BigDecimal.ZERO : detalle.getSuplencias());

            Cell celdaDiasEconomicos = filaDetalle.createCell(DIAS_ECONOMICOS);
            celdaDiasEconomicos.setCellValue(detalle.getDiasEconomicos() == null ? 0 : detalle.getDiasEconomicos().doubleValue());
            this.TOTAL_DIAS_ECONOMICOS = this.TOTAL_DIAS_ECONOMICOS.add(detalle.getDiasEconomicos() == null ? BigDecimal.ZERO : detalle.getDiasEconomicos());

            Cell celdaPercepcionComplementaria = filaDetalle.createCell(PERCEPCION_COMPLEMENTARIA);
            celdaPercepcionComplementaria.setCellValue(detalle.getPercepcionComplementaria() == null ? 0 : detalle.getPercepcionComplementaria().doubleValue());
            this.TOTAL_PERCEPCION_COMPLEMENTARIA = this.TOTAL_PERCEPCION_COMPLEMENTARIA
                    .add(detalle.getPercepcionComplementaria() == null ? BigDecimal.ZERO : detalle.getPercepcionComplementaria());

            Cell celdaBono = filaDetalle.createCell(BONO);
            celdaBono.setCellValue(detalle.getBono() == null ? 0 : detalle.getBono().doubleValue());
            this.TOTAL_BONO = this.TOTAL_BONO.add(detalle.getBono() == null ? BigDecimal.ZERO : detalle.getBono());

            Cell celdaAguinaldo = filaDetalle.createCell(AGUINALDO);
            celdaAguinaldo.setCellValue(detalle.getAguinaldo() == null ? 0 : detalle.getAguinaldo().doubleValue());
            this.TOTAL_AGUINALDO = this.TOTAL_AGUINALDO.add(detalle.getAguinaldo() == null ? BigDecimal.ZERO : detalle.getAguinaldo());

            Cell celdaSubsidio = filaDetalle.createCell(SUBSIDIO);
            celdaSubsidio.setCellValue(detalle.getSubsidio() == null ? 0 : detalle.getSubsidio().doubleValue());
            this.TOTAL_SUBSIDIO = this.TOTAL_SUBSIDIO.add(detalle.getSubsidio() == null ? BigDecimal.ZERO : detalle.getSubsidio());

            Cell celdaPrimaVacacional = filaDetalle.createCell(PRIMA_VACACIONAL);
            celdaPrimaVacacional.setCellValue(detalle.getPrimaVacacional() == null ? 0 : detalle.getPrimaVacacional().doubleValue());
            this.TOTAL_PRIMA_VACACIONAL = this.TOTAL_PRIMA_VACACIONAL
                    .add(detalle.getPrimaVacacional() == null ? BigDecimal.ZERO : detalle.getPrimaVacacional());

            Cell celdaBonificacionFalta = filaDetalle.createCell(BONIFICACION_FALTA);
            celdaBonificacionFalta.setCellValue(detalle.getBonificacionFalta() == null ? 0 : detalle.getBonificacionFalta().doubleValue());
            this.TOTAL_BONIFICACION_FALTA = this.TOTAL_BONIFICACION_FALTA
                    .add(detalle.getBonificacionFalta() == null ? BigDecimal.ZERO : detalle.getBonificacionFalta());

            Cell celdaRetroactivo = filaDetalle.createCell(RETROACTIVO);
            celdaRetroactivo.setCellValue(detalle.getRetroactivo() == null ? 0 : detalle.getRetroactivo().doubleValue());
            this.TOTAL_RETROACTIVO = this.TOTAL_RETROACTIVO.add(detalle.getRetroactivo() == null ? BigDecimal.ZERO : detalle.getRetroactivo());

            Cell celdaOtros = filaDetalle.createCell(OTROS);
            celdaOtros.setCellValue(detalle.getOtros() == null ? 0 : detalle.getOtros().doubleValue());
            this.TOTAL_OTROS = this.TOTAL_OTROS.add(detalle.getOtros() == null ? BigDecimal.ZERO : detalle.getOtros());

            Cell celdaFaltaRetardo = filaDetalle.createCell(FALTAS_RETARDOS);
            celdaFaltaRetardo.setCellValue(detalle.getFaltasRetardos() == null ? 0 : detalle.getFaltasRetardos().doubleValue());
            this.TOTAL_FALTAS_RETARDOS = this.TOTAL_FALTAS_RETARDOS.add(detalle.getFaltasRetardos() == null ? BigDecimal.ZERO : detalle.getFaltasRetardos());

            Cell celdaIsr = filaDetalle.createCell(ISR);
            celdaIsr.setCellValue(detalle.getIsr() == null ? 0 : detalle.getIsr().doubleValue());
            this.TOTAL_ISR = this.TOTAL_ISR.add(detalle.getIsr() == null ? BigDecimal.ZERO : detalle.getIsr());

            Cell celdaResponsabilidad = filaDetalle.createCell(RESPONSABILIDADES);
            celdaResponsabilidad.setCellValue(detalle.getResponsabilidades() == null ? 0 : detalle.getResponsabilidades().doubleValue());
            this.TOTAL_RESPONSABILIDADES = this.TOTAL_RESPONSABILIDADES
                    .add(detalle.getResponsabilidades() == null ? BigDecimal.ZERO : detalle.getResponsabilidades());

            Cell celdaPrestamo = filaDetalle.createCell(PRESTAMO);
            celdaPrestamo.setCellValue(detalle.getPrestamo() == null ? 0 : detalle.getPrestamo().doubleValue());
            this.TOTAL_PRESTAMO = this.TOTAL_PRESTAMO.add(detalle.getPrestamo() == null ? BigDecimal.ZERO : detalle.getPrestamo());

            Cell celdaJuicioMercantil = filaDetalle.createCell(JUICIO_MERCANTIL);
            celdaJuicioMercantil.setCellValue(detalle.getJuicioMercantil() == null ? 0 : detalle.getJuicioMercantil().doubleValue());
            this.TOTAL_JUICIO_MERCANTIL = this.TOTAL_JUICIO_MERCANTIL
                    .add(detalle.getJuicioMercantil() == null ? BigDecimal.ZERO : detalle.getJuicioMercantil());

            Cell celdaCuotaSindical = filaDetalle.createCell(CUOTA_SINDICAL);
            celdaCuotaSindical.setCellValue(detalle.getCuotaSindical() == null ? 0 : detalle.getCuotaSindical().doubleValue());
            this.TOTAL_CUOTA_SINDICAL = this.TOTAL_CUOTA_SINDICAL.add(detalle.getCuotaSindical() == null ? BigDecimal.ZERO : detalle.getCuotaSindical());

            Cell celdaPensionAlimenticia = filaDetalle.createCell(PENSION_ALIMENTICIA);
            celdaPensionAlimenticia.setCellValue(detalle.getPensionAlimenticia() == null ? 0 : detalle.getPensionAlimenticia().doubleValue());
            this.TOTAL_PENSION_ALIMENTICIA = this.TOTAL_PENSION_ALIMENTICIA
                    .add(detalle.getPensionAlimenticia() == null ? BigDecimal.ZERO : detalle.getPensionAlimenticia());

            Cell celdaTotal = filaDetalle.createCell(COLUMNA_TOTAL);
            celdaTotal.setCellValue(detalle.getTotal() == null ? 0 : detalle.getTotal().doubleValue());
            this.TOTALES = this.TOTALES.add(detalle.getTotal() == null ? BigDecimal.ZERO : detalle.getTotal());

            filaTotales++;
            i++;
            this.hoja.shiftRows(i, i + 1, 1);
        }

        if (filaTotales > FILA_INICIO_DETALLE) {

            String general = "general";

            Row filaDetalle = this.hoja.createRow(filaTotales);

            Cell celdaGeneral = filaDetalle.createCell(RFC);
            celdaGeneral.setCellValue(general);

            Cell celdaTotalHonorarios = filaDetalle.createCell(HONORARIOS_ASIMILABLES);
            celdaTotalHonorarios.setCellValue(this.TOTAL_HONORARIOS_ASIMILABLES.doubleValue());

            Cell celdaTotalSuplencias = filaDetalle.createCell(SUPLENCIAS);
            celdaTotalSuplencias.setCellValue(this.TOTAL_SUPLENCIAS.doubleValue());

            Cell celdaTotalDiasEconomicos = filaDetalle.createCell(DIAS_ECONOMICOS);
            celdaTotalDiasEconomicos.setCellValue(this.TOTAL_DIAS_ECONOMICOS.doubleValue());

            Cell celdaTotalPercepcionComplementaria = filaDetalle.createCell(PERCEPCION_COMPLEMENTARIA);
            celdaTotalPercepcionComplementaria.setCellValue(this.TOTAL_PERCEPCION_COMPLEMENTARIA.doubleValue());

            Cell celdaTotalBono = filaDetalle.createCell(BONO);
            celdaTotalBono.setCellValue(this.TOTAL_BONO.doubleValue());

            Cell celdaTotalAguinaldo = filaDetalle.createCell(AGUINALDO);
            celdaTotalAguinaldo.setCellValue(this.TOTAL_AGUINALDO.doubleValue());

            Cell celdaTotalSubsidio = filaDetalle.createCell(SUBSIDIO);
            celdaTotalSubsidio.setCellValue(this.TOTAL_SUBSIDIO.doubleValue());

            Cell celdaTotalPrimaVacacional = filaDetalle.createCell(PRIMA_VACACIONAL);
            celdaTotalPrimaVacacional.setCellValue(this.TOTAL_PRIMA_VACACIONAL.doubleValue());

            Cell celdaTotalBonificacionFalta = filaDetalle.createCell(BONIFICACION_FALTA);
            celdaTotalBonificacionFalta.setCellValue(this.TOTAL_BONIFICACION_FALTA.doubleValue());

            Cell celdaTotalRetroactivo = filaDetalle.createCell(RETROACTIVO);
            celdaTotalRetroactivo.setCellValue(this.TOTAL_RETROACTIVO.doubleValue());

            Cell celdaTotalOtros = filaDetalle.createCell(OTROS);
            celdaTotalOtros.setCellValue(this.TOTAL_OTROS.doubleValue());

            Cell celdaFaltasRetardos = filaDetalle.createCell(FALTAS_RETARDOS);
            celdaFaltasRetardos.setCellValue(this.TOTAL_FALTAS_RETARDOS.doubleValue());

            Cell celdaTotalIsr = filaDetalle.createCell(ISR);
            celdaTotalIsr.setCellValue(this.TOTAL_ISR.doubleValue());

            Cell celdaTotalResponsabilidades = filaDetalle.createCell(RESPONSABILIDADES);
            celdaTotalResponsabilidades.setCellValue(this.TOTAL_RESPONSABILIDADES.doubleValue());

            Cell celdaTotalPrestamo = filaDetalle.createCell(PRESTAMO);
            celdaTotalPrestamo.setCellValue(this.TOTAL_PRESTAMO.doubleValue());

            Cell celdaTotalJuicioMercantil = filaDetalle.createCell(JUICIO_MERCANTIL);
            celdaTotalJuicioMercantil.setCellValue(this.TOTAL_JUICIO_MERCANTIL.doubleValue());

            Cell celdaTotalCuotaSindical = filaDetalle.createCell(CUOTA_SINDICAL);
            celdaTotalCuotaSindical.setCellValue(this.TOTAL_CUOTA_SINDICAL.doubleValue());

            Cell celdaTotalPensionAlimenticia = filaDetalle.createCell(PENSION_ALIMENTICIA);
            celdaTotalPensionAlimenticia.setCellValue(this.TOTAL_PENSION_ALIMENTICIA.doubleValue());

            Cell celdaTotalTotales = filaDetalle.createCell(COLUMNA_TOTAL);
            celdaTotalTotales.setCellValue(this.TOTALES.doubleValue());

        }
    }

}
