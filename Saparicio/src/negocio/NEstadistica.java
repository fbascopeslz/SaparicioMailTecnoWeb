/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Cliente;
import datos.Producto;
import datos.Proveedor;
import datos.Venta;
import javax.swing.table.DefaultTableModel;
import utils.Utils;

/**
 *
 * @author fbasc
 */
public class NEstadistica {
    
    private Producto producto;
    private Cliente cliente;
    private Proveedor proveedor;
    private Venta venta;    

    public NEstadistica() {
        this.producto = new Producto();
        this.cliente = new Cliente();
        this.proveedor = new Proveedor();
        this.venta = new Venta();       
    }
    
    
    //Productos mas vendidos en todos los tiempos (Grafica Donut => Producto vs Cantidad)
    public String estadisticasProductosMasVendidos() {
        String html = "";
        DefaultTableModel dtm = producto.estadisticaTopProductosMasVendidos();
        html = Utils.dibujarGraficasHTMLDonut(dtm, "TOP PRODUCTOS MAS VENDIDOS");
        return html;
    }
    
    /*
    //Productos mas vendidos por mes (Grafica Barras => Mes y Producto vs Cantidad)
    public String estadisticasProductosMasVendidosMes(String annio) {
        return null;
    }
    */
    
    //Productos mas vendidos en un dia (Grafica Barras => Producto vs Cantidad)
    public String estadisticasProductosMasVendidosDia(String fecha) {
        
        fecha = Utils.ddMMyyyyToyyyyMMddDate(fecha);
        
        String html = "";
        DefaultTableModel dtm = producto.estadisticaTopProductosMasVendidosDia(fecha);
        html = Utils.dibujarGraficasHTMLBarras(dtm, "PRODUCTOS MAS VENDIDOS DEL DIA " + fecha, "Producto");
        return html;
    }
    
    //Productos mas vendidos en un rango de fechas (Grafica Donut => Producto vs Cantidad)
    public String estadisticasProductosMasVendidosRangoFechas(String fecha1, String fecha2) {
        
        fecha1 = Utils.ddMMyyyyToyyyyMMddDate(fecha1);
        fecha2 = Utils.ddMMyyyyToyyyyMMddDate(fecha2);
        
        String html = "";
        DefaultTableModel dtm = producto.estadisticaTopProductosMasVendidosRangoFechas(fecha1, fecha2);
        html = Utils.dibujarGraficasHTMLDonut(dtm, 
                "PRODUCTOS MAS VENDIDOS ENTRE EL " + fecha1 + " Y EL " + fecha2);
        return html;
    }
    
    
    //Clientes mas fieles (de todos los tiempos) (Grafica Barras => Cliente vs NumeroVentas)
    public String estadisticasClientesFieles() {
        String html = "";
        DefaultTableModel dtm = cliente.estadisticaClientesFieles();
        html = Utils.dibujarGraficasHTMLBarras(dtm, "TOP CLIENTES MAS FIELES", "Cliente");
        return html;
    }
    
    
    //Proveedores mas solicitados (de todos los tiempos) (Grafica Barras => Proveedor vs NumeroCompras)
    public String estadisticasProveedoresMasSolicitados() {
        String html = "";
        DefaultTableModel dtm = proveedor.estadisticaProveedoresMasSolicitados();
        html = Utils.dibujarGraficasHTMLBarras(dtm, "TOP PROVEEDORES MAS SOLICITADOS", "Proveedor");
        return html;
    }
    
    
    //Proveedores mas solicitados (de todos los tiempos) (Grafica Barras => Proveedor vs NumeroCompras)
    public String estadisticasFinancieras(String anio) {
        String html = "";
        DefaultTableModel dtm1 = venta.estadisticaFinancierasMeseVsIngresoGasto(anio);
        DefaultTableModel dtm2 = venta.estadisticaFinancierasIngresosGastosUtilidades(anio);
        
        html = Utils.dibujarGraficasHTMLLineasYTorta(dtm1, dtm2, "ESTADISTICAS FINANCIERAS DE INGRESOS Y GASTOS");
        
        return html;
    }
    
    
}
