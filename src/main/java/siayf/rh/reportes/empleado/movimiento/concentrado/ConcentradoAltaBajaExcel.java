/*
 * ConcentradoAltaBajaExcel.java
 * Creado el 27/Oct/2017 10:09:29 PM
 * 
 */
package siayf.rh.reportes.empleado.movimiento.concentrado;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import siayf.rh.reportes.core.excel.ExcelPlantillaReporte;
import siayf.rh.reportes.util.FechaUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ConcentradoAltaBajaExcel extends ExcelPlantillaReporte<ConcentradoAltaBajaDto> {

    /**
     * Fila en la que se iniciara a escribir los detalles.
     */
    private static final int FILA_INICIO_DETALLE = 5;

    private static final int COLUMNA_NO = 0;
    private static final int COLUMNA_RFC = 1;
    private static final int COLUMNA_FECHA = 2;
    private static final int COLUMNA_NOMBRE = 3;
    private static final int COLUMNA_CLAVE = 4;
    private static final int COLUMNA_ADSCRIPCION = 5;
    private static final int COLUMNA_TIPO_NOMBRAMIENTO = 6;
    private static final int COLUMNA_MOVIMIENTO = 7;
    private static final int COLUMNA_OBSERVACIONES = 8;

    public ConcentradoAltaBajaExcel() {
        super("/plantillas/concentradoAltaBaja/", "Concentrado_Altas_Bajas.xlsx", "Concentrado_Altas_Bajas");
    }

    @Override
    protected void llenarDetalles(List<ConcentradoAltaBajaDto> detalles) {
        int i = FILA_INICIO_DETALLE;
        int numeroConsecutivo = 1;

        for (ConcentradoAltaBajaDto detalle : detalles) {
            Row filaDetalle = hoja.createRow(i);

            Cell celdaNum = filaDetalle.createCell(COLUMNA_NO);
            celdaNum.setCellValue(numeroConsecutivo);

            Cell celdaRfc = filaDetalle.createCell(COLUMNA_RFC);
            celdaRfc.setCellValue(detalle.getRfc());

            Cell celdaFecha = filaDetalle.createCell(COLUMNA_FECHA);
            celdaFecha.setCellValue(FechaUtil.formatoFecha(detalle.getFecha()));

            Cell celdaNombre = filaDetalle.createCell(COLUMNA_NOMBRE);
            celdaNombre.setCellValue(detalle.getNombreCompleto());

            Cell celdaClave = filaDetalle.createCell(COLUMNA_CLAVE);
            celdaClave.setCellValue(detalle.getClavePresupuestal());

            Cell celdaAdscripcion = filaDetalle.createCell(COLUMNA_ADSCRIPCION);
            celdaAdscripcion.setCellValue(detalle.getAdscripcion());

            Cell celdaTipo = filaDetalle.createCell(COLUMNA_TIPO_NOMBRAMIENTO);
            celdaTipo.setCellValue(detalle.getTipoNombramiento());

            Cell celdaMov = filaDetalle.createCell(COLUMNA_MOVIMIENTO);
            celdaMov.setCellValue(detalle.getMovimiento());

            Cell celdaOb = filaDetalle.createCell(COLUMNA_OBSERVACIONES);
            celdaOb.setCellValue(detalle.getObservaciones());

            numeroConsecutivo++;
            i++;
            hoja.shiftRows(i, i + 1, 1);
        }
    }

}
