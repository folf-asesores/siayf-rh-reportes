/*
 * ProductoNominaProgramaExcel.java
 * Creado el 01/nov/2017 7:18:42 AM
 * 
 */

package siayf.rh.reportes.nomina.productonomina;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import siayf.rh.reportes.core.excel.ExcelPlantillaReporte;
import siayf.rh.reportes.core.excel.ReporteVacio;
import siayf.rh.reportes.util.FechaUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ProductoNominaProgramaExcel extends ExcelPlantillaReporte<ProductoNominaProgramaDto> implements Serializable {

    private static final long serialVersionUID = 9082692081835328085L;
    private static final Logger LOGGER = Logger.getLogger(ProductoNominaProgramaExcel.class.getName());
    
    // Fila en la que inicia el detalle.
    private static final int FILA_INICIO_DETALLE = 8;
    
    // Columna de cada detalle
    private static final int COLUMNA_NUMERO = 0;
    private static final int COLUMNA_RFC = 1;
    private static final int COLUMNA_NOMBRE_EMPLEADO = 2;
    private static final int COULMNA_FECHA_INGRESO = 3;
    private static final int COLUMNA_CENTRO_RESPONSABILIDAD = 4;
    private static final int COLUMNA_PROGRAMA = 5;
    private static final int COLUMNA_FUNCION = 6;
    private static final int COLUMNA_SUELDO = 7;
    private static final int COLUMNA_ISR = 8;
    private static final int COLUMNA_PENSION_ALIMENTICIA = 9;
    private static final int COLUMNA_PRIMA = 10;
    private static final int COLUMNA_AGUINALDO = 11;
    private static final int COLUMNA_TOTAL = 12;

    private static final String PATRON_NOMBRE_HOJA = "Nomina_{0}";
    
    public ProductoNominaProgramaExcel() {
        super("siayf/rh/reportes/nomina/", "producto_nomina_programa.xlsx", "PRODUCTO_NOMINA_PROGRAMAS");
    }

    // Este método lanzará una excepción porque se creo una sobre carga que se adecua al tipo de reporte.
    @Override
    public byte[] generar(List<ProductoNominaProgramaDto> detalles) {
        try {
            cargarPlantilla();

            return obtenerBytes();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Ocurrio un error al cargar la plantilla.\n{0}", ex);
            return ReporteVacio.obtenerBytes();
        }
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public byte[] generar(List<ProductoNominaProgramaDto> detalles, List<String> programas, Date finPeriodo) {
        try {
            cargarPlantilla();
            llenarDetalles(detalles, programas, finPeriodo);

            return obtenerBytes();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Ocurrio un error al cargar la plantilla.\n{0}", ex);
            return ReporteVacio.obtenerBytes();
        }
    }

    // Este método lanzará una excepción porque se creo una sobre carga que se adecua al tipo de reporte.
    @Override
    protected void llenarDetalles(List<ProductoNominaProgramaDto> detalles) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void llenarDetalles(List<ProductoNominaProgramaDto> detalles, List<String> programas, Date finPeriodo) {
        int i;
        int filaTotales;
        int contador;
        int cont = 0;
        String hojaOriginal = getNombreHoja();
        
        for (String nombreProgramaFederal : programas) {
            if (cont < programas.size() && programas.size() != 1) {
                String nombreHojaNueva = String.format(PATRON_NOMBRE_HOJA, nombreProgramaFederal);
                copiarHoja(hojaOriginal, nombreHojaNueva);
            }
            cont++;
        }

        cont = 0;
        for (String programaFed : programas) {

            String nombreHoja = libro.getSheetName(cont + 1);
            hoja = libro.getSheet(nombreHoja);

            Row filaEncabezadoPrograma = hoja.getRow(4);
            Cell cellEncabezado = filaEncabezadoPrograma.getCell(0);
            cellEncabezado.setCellValue("NOMINA DEL PROGRAMA: " + programaFed);

            Row filaEncabezadoProgramaFecha = hoja.getRow(5);
            Cell cellEncabezadoFecha = filaEncabezadoProgramaFecha.getCell(0);
            String encabezado = String.format(FechaUtil.LUGAR_MEXICO, "CORRESPONDIENTE AL MES DE %1$TB del 14tY", finPeriodo);
            cellEncabezadoFecha.setCellValue(encabezado);

            contador = 1;
            i = FILA_INICIO_DETALLE;
            filaTotales = FILA_INICIO_DETALLE;

            // Totales
            BigDecimal totalSueldo = BigDecimal.ZERO;
            BigDecimal totalIsr = BigDecimal.ZERO;
            BigDecimal totalPensionAlimenticia = BigDecimal.ZERO;
            BigDecimal totales = BigDecimal.ZERO;

            for (ProductoNominaProgramaDto detalle : detalles) {

                if (detalle.getPrograma().compareTo(programaFed) == 0) {

                    Row filaDetalle = hoja.createRow(i);

                    Cell celdaNum = filaDetalle.createCell(COLUMNA_NUMERO);
                    celdaNum.setCellValue(contador);
                    contador += 1;
                    Cell celdaRfc = filaDetalle.createCell(COLUMNA_RFC);
                    celdaRfc.setCellValue(detalle.getRfc());
                    Cell celdaNombreEmpleado = filaDetalle.createCell(COLUMNA_NOMBRE_EMPLEADO);
                    celdaNombreEmpleado.setCellValue(detalle.getNombreEmpleado());

                    Cell celdaFechaIngreso = filaDetalle.createCell(COULMNA_FECHA_INGRESO);
                    celdaFechaIngreso.setCellValue(detalle.getFechaIngreso() == null ? ""
                            : FechaUtil.formatearFecha(detalle.getFechaIngreso(), FechaUtil.PATRON_FECHA_BASE_DE_DATOS));

                    Cell celdaCentroResponsabilidad = filaDetalle.createCell(COLUMNA_CENTRO_RESPONSABILIDAD);
                    celdaCentroResponsabilidad.setCellValue(detalle.getCentroResponsabilidad());

                    Cell celdaPrograma = filaDetalle.createCell(COLUMNA_PROGRAMA);
                    celdaPrograma.setCellValue(detalle.getPrograma());

                    Cell celdaFuncion = filaDetalle.createCell(COLUMNA_FUNCION);
                    celdaFuncion.setCellValue(detalle.getFuncion());

                    Cell sueldo = filaDetalle.createCell(COLUMNA_SUELDO, CellType.NUMERIC);
                    sueldo.setCellValue((double) (detalle.getSueldo() == null ? 0 : detalle.getSueldo().doubleValue()));
                    totalSueldo = totalSueldo
                            .add(detalle.getSueldo() == null ? BigDecimal.ZERO : detalle.getSueldo());

                    Cell celdaIsr = filaDetalle.createCell(COLUMNA_ISR);
                    celdaIsr.setCellValue((double) (detalle.getIsr() == null ? 0 : detalle.getIsr().doubleValue()));
                    totalIsr = totalIsr.add(detalle.getIsr() == null ? BigDecimal.ZERO : detalle.getIsr());

                    Cell celdaPensionAlimenticia = filaDetalle.createCell(COLUMNA_PENSION_ALIMENTICIA);
                    celdaPensionAlimenticia.setCellValue((double) (detalle.getPensionAlimenticia() == null ? 0
                            : detalle.getPensionAlimenticia().doubleValue()));
                    totalPensionAlimenticia = totalPensionAlimenticia.add(detalle.getPensionAlimenticia() == null
                            ? BigDecimal.ZERO : detalle.getPensionAlimenticia());

                    Cell celdaPrimaVac = filaDetalle.createCell(COLUMNA_PRIMA);
                    celdaPrimaVac.setCellValue("");

                    Cell celdaAguinaldo = filaDetalle.createCell(COLUMNA_AGUINALDO);
                    celdaAguinaldo.setCellValue("");

                    Cell celdaTotal = filaDetalle.createCell(COLUMNA_TOTAL);
                    celdaTotal.setCellValue((double) (detalle.getTotal() == null ? 0 : detalle.getTotal().doubleValue()));
                    totales = totales.add(detalle.getTotal() == null ? BigDecimal.ZERO : detalle.getTotal());

                    filaTotales++;
                    i++;
                    hoja.shiftRows(i, i + 1, 1);
                }

            }

            if (filaTotales > FILA_INICIO_DETALLE) {
                String total = "TOTAL";

                Row filaDetalle = hoja.createRow(filaTotales);

                Cell celdaGeneral = filaDetalle.createCell(COLUMNA_FUNCION);
                celdaGeneral.setCellValue(total);

                Cell celdaTotalSueldo = filaDetalle.createCell(COLUMNA_SUELDO);
                celdaTotalSueldo.setCellValue(totalSueldo.doubleValue());

                Cell celdaTotalIsr = filaDetalle.createCell(COLUMNA_ISR);
                celdaTotalIsr.setCellValue(totalIsr.doubleValue());

                Cell celdaTotalPensionAlimenticia = filaDetalle.createCell(COLUMNA_PENSION_ALIMENTICIA);
                celdaTotalPensionAlimenticia.setCellValue(totalPensionAlimenticia.doubleValue());

                Cell celdaTotalTotales = filaDetalle.createCell(COLUMNA_TOTAL);
                celdaTotalTotales.setCellValue(totales.doubleValue());
            }

            if (filaTotales > FILA_INICIO_DETALLE) {

                Row filaFirmas1 = hoja.createRow(filaTotales + 5);
                Cell celdaEtiqueta1 = filaFirmas1.createCell(COLUMNA_RFC);
                celdaEtiqueta1.setCellValue("ELABORO");

                Row filaFirmas2 = hoja.createRow(filaTotales + 7);
                Cell celdaNombre1 = filaFirmas2.createCell(COLUMNA_RFC);
                celdaNombre1.setCellValue("C.P. VERONICA MIMIENTZI SALAMANCA");

                Row filaFirmas3 = hoja.createRow(filaTotales + 8);
                Cell celdaPuesto1 = filaFirmas3.createCell(COLUMNA_RFC);
                celdaPuesto1.setCellValue("JEFE DEL OFICINA DE PERSONAL EVENTUAL");

                Row filaFirmas4 = hoja.createRow(filaTotales + 9);
                Cell celdaPuesto11 = filaFirmas4.createCell(COLUMNA_RFC);
                celdaPuesto11.setCellValue("DEL O.P.D. SALUD DE TLAXCALA");

                Cell celdaEtiqueta2 = filaFirmas1.createCell(COLUMNA_FUNCION);
                celdaEtiqueta2.setCellValue("Vo. Bo.");

                Cell celdaNombre2 = filaFirmas2.createCell(COLUMNA_FUNCION);
                celdaNombre2.setCellValue("LIC. MARIO HERNANDEZ RAMIREZ");

                Cell celdaPuesto2 = filaFirmas3.createCell(COLUMNA_FUNCION);
                celdaPuesto2.setCellValue("DIRECTOR   DE  ADMINISTRACIÓN DEL O.P.D.");

                Cell celdaPuesto21 = filaFirmas4.createCell(COLUMNA_FUNCION);
                celdaPuesto21.setCellValue("SALUD DE TLAXCALA");

                Cell celdaEtiqueta3 = filaFirmas1.createCell(COLUMNA_PENSION_ALIMENTICIA);
                celdaEtiqueta3.setCellValue("Vo. Bo.");

                Cell celdaNombre3 = filaFirmas2.createCell(COLUMNA_PENSION_ALIMENTICIA);
                celdaNombre3.setCellValue("BIOL. FRANCISCO MENDEZ GARCIA");

                Cell celdaPuesto3 = filaFirmas3.createCell(COLUMNA_PENSION_ALIMENTICIA);
                celdaPuesto3.setCellValue("JEFE DEL DEPARTAMENTO DE RECURSOS HUMANOS");
            }

            cont++;
        }

        libro.removeSheetAt(0);
    }
    
    public void copiarHoja(String nombreOrigen, String nombreDestino) {
        if (nombreOrigen == null) {
            throw new IllegalArgumentException("nombreOrigen no debe ser null"); //$NON-NLS-1$
        }
        if (nombreDestino == null) {
            throw new IllegalArgumentException("nombreDestino no debe ser null"); //$NON-NLS-1$
        }

        int antiguoIndice = libro.getSheetIndex(nombreOrigen);
        
        if (antiguoIndice < 0) {
            throw new IllegalArgumentException();
        }
        
        Sheet hojaNueva = libro.cloneSheet(antiguoIndice);
        int nuevoIndice = libro.getSheetIndex(hojaNueva);
        libro.setSheetName(nuevoIndice, nombreDestino);
    }
}
