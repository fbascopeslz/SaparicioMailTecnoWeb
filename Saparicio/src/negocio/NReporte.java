/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;
import datos.Ingreso;
import datos.Producto;
import datos.Venta;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.table.DefaultTableModel;
import utils.Utils;


/**
 *
 * @author fbasc
 */
public class NReporte {
    
    private Producto producto;
    private Venta venta;
    private Ingreso ingreso;

    public NReporte() {
        this.producto = new Producto();
        this.venta = new Venta();
        this.ingreso = new Ingreso();        
    }
    
    public void generarPDF(String html) throws FileNotFoundException, IOException {
        InputStream inputHTML = new ByteArrayInputStream(html.getBytes());                                 
        
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(new File("reporte.pdf")));
        pdfDocument.setDefaultPageSize(PageSize.A4.rotate());
        HtmlConverter.convertToPdf(inputHTML, pdfDocument);
        
        System.out.println("PDF reporte generado");
    }
    
    public void reporteProductos() throws IOException {
        String html = Utils.dibujarTablaHTMLForPDF(producto.getProductos(), "LISTADO DE PRODUCTOS");
        generarPDF(html);
    }
    
    public void reporteVentas() throws IOException {
        String html = Utils.dibujarTablaHTMLForPDF(venta.getVentas(), "LISTADO DE VENTAS");                
        generarPDF(html);
    }
    
    public void reporteVentasRangoFechas(String fecha1, String fecha2) throws IOException {        
        String html = Utils.dibujarTablaHTMLForPDF(venta.getVentasRangoFecha(fecha1, fecha2), 
                "LISTADO DE VENTAS");                
        generarPDF(html);               
    }
    
    public void reporteIngresos() throws IOException {
        String html = Utils.dibujarTablaHTMLForPDF(ingreso.getIngresos(), "LISTADO DE INGRESOS");                
        generarPDF(html);
    }
    
    public void reporteIngresosRangoFechas(String fecha1, String fecha2) throws IOException {        
        String html = Utils.dibujarTablaHTMLForPDF(ingreso.getIngresosRangoFecha(fecha1, fecha2), 
                "LISTADO DE INGRESOS");                
        generarPDF(html);               
    }
        
}
