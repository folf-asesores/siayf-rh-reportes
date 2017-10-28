/*
 * ComisionadoLicenciaExcel.java
 * Creado el 27/oct/2017 8:45:48 PM
 * 
 */
package siayf.rh.reportes.empleado.movimiento.cl;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import siayf.rh.reportes.core.excel.ExcelPlantillaReporte;
import siayf.rh.reportes.util.FechaUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ComisionadoLicenciaExcel extends ExcelPlantillaReporte<ComisionadoLicenciaDto> {

    /**
     * Fila en la que se iniciara a escribir los detalles.
     */
    private static final int FILA_INICIO_DETALLE = 5;

    private static final int COLUMNA_TIPO_MOVIMIENTO = 0;
    private static final int COLUMNA_APELLIDO_PATERNO = 1;
    private static final int COLUMNA_APELLIDO_MATERNO = 2;
    private static final int COLUMNA_NOMBRE_EMPLEADO = 3;
    private static final int COLUMNA_TIPO_PLAZA = 4;
    private static final int COLUMNA_NUMERO_HORAS = 5;
    private static final int COLUMNA_FUNCIONES_ESPECIFICAS = 6;
    private static final int COLUMNA_CLAVE_PAGO = 7;
    private static final int COLUMNA_FECHA_INICIO = 8;
    private static final int COLUMNA_FECHA_CONCLUSION = 9;
    private static final int COLUMNA_CENTRO_TRABAJO_ORIGEN = 10;
    private static final int COLUMNA_CENTRO_TRABAJO_DESTINO = 11;

    public ComisionadoLicenciaExcel() {
        super("/plantillas/comisionadoLicencia/", "comisionado-licencia.xlsx", "Comisionado_Licencia");
    }

    @Override
    protected void llenarDetalles(List<ComisionadoLicenciaDto> detalles) {
        int i = FILA_INICIO_DETALLE;

        for (ComisionadoLicenciaDto detalle : detalles) {
            Row filaDetalle = hoja.createRow(i);

            Cell celdaTipoMov = filaDetalle.createCell(COLUMNA_TIPO_MOVIMIENTO);
            celdaTipoMov.setCellValue(detalle.getTipoMovimiento());

            Cell celdaApA = filaDetalle.createCell(COLUMNA_APELLIDO_PATERNO);
            celdaApA.setCellValue(detalle.getApellidoPaterno());

            Cell celdaApM = filaDetalle.createCell(COLUMNA_APELLIDO_MATERNO);
            celdaApM.setCellValue(detalle.getApellidoMaterno());

            Cell celdaNombreE = filaDetalle.createCell(COLUMNA_NOMBRE_EMPLEADO);
            celdaNombreE.setCellValue(detalle.getNombreEmpleado());

            Cell celdaTipoPlaza = filaDetalle.createCell(COLUMNA_TIPO_PLAZA);
            celdaTipoPlaza.setCellValue(detalle.getTipoPlaza());

            // Están por definir las horas (por el momento lo trae vacio)
            Cell celdaNumeroHr = filaDetalle.createCell(COLUMNA_NUMERO_HORAS);
            celdaNumeroHr.setCellValue(detalle.getNumeroHoras());
            
            // Cell celdaNumeroHr =
            // filaDetalle.createCell(COLUMNA_NUMERO_HORAS);
            // celdaNumeroHr.setCellValue(FechaUtil.formatoHora(detalle.getNumeroHoras()));

            Cell celdaFnE = filaDetalle.createCell(COLUMNA_FUNCIONES_ESPECIFICAS);
            celdaFnE.setCellValue(detalle.getFuncionesEspecificas());

            Cell celdaCP = filaDetalle.createCell(COLUMNA_CLAVE_PAGO);
            celdaCP.setCellValue(detalle.getClavePago());

            Cell celdaFI = filaDetalle.createCell(COLUMNA_FECHA_INICIO);
            celdaFI.setCellValue(FechaUtil.formatoFecha(detalle.getFechaInicio()));

            Cell celdaFC = filaDetalle.createCell(COLUMNA_FECHA_CONCLUSION);
            celdaFC.setCellValue(FechaUtil.formatoFecha(detalle.getFechaConclusion()));

            Cell celdaCTO = filaDetalle.createCell(COLUMNA_CENTRO_TRABAJO_ORIGEN);
            celdaCTO.setCellValue(detalle.getCentroTrabajoOrigen());

            Cell celdaCTD = filaDetalle.createCell(COLUMNA_CENTRO_TRABAJO_DESTINO);
            celdaCTD.setCellValue(detalle.getCentroTrabajoDestino());

            i++;
            hoja.shiftRows(i, i + 1, 1);
        }
    }

}
