/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author fbasc
 */
public class ComandosAyuda {
    
    public static final String AYUDA_GENERAL = "Sistema de Informacion para la Distribuidora 'APARICIO'\n\n"
            + "Tome en cuenta los comandos disponibles para interactuar con el Sistema\n"
            + "Para ver los parametros de un comando, envíe el nombre del comando seguido de la palabra HELP\n"
            + "\"Ejemplo: MOSTRARPRODUCTOS HELP\"\n\n"            
            + "NOTA: TODAS LAS PETICIONES HACIA EL SISTEMA DEBEN SER ENVIADAS EN EL CAMPO \"ASUNTO\"\n\n"            
            + "------------------------- LISTA DE COMANDOS -----------------------\n\n"
            + "                      -CU1.Gestionar Producto: \n"
            + "                            REGISTRARPRODUCTO\n"
            + "                            MODIFICARPRODUCTO\n"
            + "                            ELIMINARPRODUCTO\n"
            + "                            OBTENERPRODUCTO\n"
            + "                            MOSTRARPRODUCTOS\n\n"
            + "                      -CU2.Gestionar Proveedor: \n"
            + "                            REGISTRARPROVEEDOR\n"
            + "                            MODIFICARPROVEEDOR\n"
            + "                            ELIMINARPROVEEDOR\n"
            + "                            OBTENERPROVEEDOR\n"
            + "                            MOSTRARPROVEEDORES\n\n"
            + "                      -CU3.Gestionar Usuario: \n"
            + "                            REGISTRARUSUARIO\n"
            + "                            MODIFICARUSUARIO\n"
            + "                            ELIMINARUSUARIO\n"
            + "                            OBTENERUSUARIO\n"
            + "                            MOSTRARUSUARIOS\n\n"
            + "                      -CU4.Gestionar Cliente: \n"
            + "                            REGISTRARCLIENTE\n"
            + "                            MODIFICARCLIENTE\n"
            + "                            ELIMINARCLIENTE\n"
            + "                            OBTENERCLIENTE\n"
            + "                            MOSTRARCLIENTES\n\n"
            + "                      -CU5.Realizar Venta: \n"
            + "                            REGISTRARVENTA\n"
            + "                            OBTENERVENTAYDETALLES\n"
            + "                            MOSTRARVENTAS\n\n"
            + "                      -CU6.Registrar Ingreso: \n"
            + "                            REGISTRARINGRESO\n"
            + "                            OBTENERINGRESOYDETALLES\n"
            + "                            MOSTRARINGRESOS\n\n"
            + "                      -CU7.Visualizar Reportes: \n"
            + "                            REPORTEPRODUCTOS\n"
            + "                            REPORTEVENTAS\n"
            + "                            REPORTEINGRESOS\n\n"
            + "                      -CU8.Visualizar Estadisticas: \n"
            + "                            ESTADISTICASPRODUCTOSMASVENDIDOS\n"
            + "                            ESTADISTICASCLIENTESFIELES\n"
            + "                            ESTADISTICASPROVEEDORESMASSOLICITADOS\n"
            + "                            ESTADISTICASFINANCIERAS\n\n"
            
            + "-----------------------------------------------------------------------------------------------\n\n";
    
    
    
//----------------------------------PAQUETE USUARIO----------------------------//
    
    //GESTIONAR PRODUCTO
    public static final String AYUDA_REGISTRARPRODUCTO = 
            "Registrar Producto\n\n"
            +"Función: Permite registrar un Producto\n\n"  
            +"Parámetros:\n"
            +"- Codigo:(String con comillas dobles)\n"
            +"- Nombre:(String con comillas dobles)\n"
            +"- Descripcion:(String con comillas dobles)\n"
            +"- Precio:(Double con comillas dobles)\n"
            +"- Stock:(Integer)\n\n"
            +"Ejemplo:\n"
            + "REGISTRARPRODUCTO {\"12345\", \"Coca Cola 3lt\", \"Pack de 6 botellas\", \"53.8\", 15}\n\n";    
    
    public static final String AYUDA_MODIFICARPRODUCTO =
            "Modificar Producto\n\n"
            +"Función: Permite modificar un Producto por su ID\n\n"  
            +"Parámetros:\n"
            +"- ID:(Integer)\n"
            +"- Codigo:(String con comillas dobles)\n"
            +"- Nombre:(String con comillas dobles)\n"
            +"- Descripcion:(String con comillas dobles)\n"
            +"- Estado:(Boolean con comillas dobles, solos dos valores: true o false)\n"
            +"- Precio:(Double con comillas dobles)\n"
            +"- Stock:(Integer)\n\n"
            +"Ejemplo:\n"
            + "MODIFICARPRODUCTO {3, \"123\", \"Coca Cola 3lt\", \"Pack de 6 botellas\", \"false\", \"53.8\", 8}\n\n"
            + "NOTA: En caso de mantener un valor por defecto: Colocar guion en el parametro \"_\" (EL PARAMETRO ID ES OBLIGATORIO)\n\n";
    
    public static final String AYUDA_ELIMINARPRODUCTO =
            "Eliminar Producto\n\n"
            +"Función: Permite eliminar un Producto por su ID\n\n"  
            +"Parámetros:\n"
            +"- ID:(Integer)\n\n"
            +"Ejemplo:\n"
            + "ELIMINARPRODUCTO {2}\n\n";
    
    public static final String AYUDA_OBTENERPRODUCTO =
            "Obtener Producto\n\n"
            +"Función: Permite obtener un Producto por su ID\n\n"  
            +"Parámetros:\n"
            +"- ID:(Integer)\n\n"
            +"Ejemplo:\n"
            + "OBTENERPRODUCTO {5}\n\n";
    
    public static final String AYUDA_MOSTRARPRODUCTOS = "Función: Muestra todos los Productos registrados (COMANDO SIN PARAMETROS)\n"
            + "MOSTRARPRODUCTOS \n\n";
    
    
    
    //GESTIONAR PROVEEDOR
    public static final String AYUDA_REGISTRARPROVEEDOR =
            "Registrar Proveedor\n\n"
            +"Función: Permite registrar un Proveedor\n\n"  
            +"Parámetros:\n"
            +"- Nombre:(String con comillas dobles)\n"
            +"- Tipo de documento:(String con comillas dobles)\n"
            +"- Numero de documento:(String con comillas dobles)\n"
            +"- Telefono:(String con comillas dobles)\n"
            +"- Direccion:(String con comillas dobles)\n"
            +"- Contacto:(String con comillas dobles)\n"
            +"- Telefono del contacto:(String con comillas dobles)\n\n"
            +"Ejemplo:\n"
            + "REGISTRARPROVEEDOR {\"Coca Cola SRL\", \"NIT\", \"12345678\", \"3457290\", \"Calle S/N\", \"Pedro Sanchez\", \"7635269\"}\n\n";    
    
    public static final String AYUDA_MODIFICARPROVEEDOR =
            "Modificar Proveedor\n\n"
            +"Función: Permite modificar un Proveedor por su ID\n\n"  
            +"Parámetros:\n"
            +"- ID:(Integer)\n"
            +"- Nombre:(String con comillas dobles)\n"
            +"- Tipo de documento:(String con comillas dobles)\n"
            +"- Numero de documento:(String con comillas dobles)\n"
            +"- Telefono:(String con comillas dobles)\n"
            +"- Direccion:(String con comillas dobles)\n"
            +"- Contacto:(String con comillas dobles)\n"
            +"- Telefono del contacto:(String con comillas dobles)\n"
            +"- Estado del Proveedor:(Boolean con comillas dobles, solos dos valores: true o false)\n\n"
            +"Ejemplo:\n"
            + "MODIFICARPROVEEDOR {4, \"Coca Cola\", \"NIT\", \"546372819\", \"3214567\", \"S/N\", \"Pablo Mendoza\", \"7658942\", \"false\"}\n"
            + "En caso de mantener un valor por defecto: Colocar guion bajo en el parametro \"_\" (EL PARAMETRO ID ES OBLIGATORIO)\n\n";
    
    public static final String AYUDA_ELIMINARPROVEEDOR = 
            "Eliminar Proveedor\n\n"
            +"Función: Permite eliminar un Proveedor por su ID\n\n"  
            +"Parámetros:\n"
            +"- ID:(Integer)\n\n"
            +"Ejemplo:\n"
            + "ELIMINARPROVEEDOR {5}\n\n";
    
    public static final String AYUDA_OBTENERPROVEEDOR =
            "Obtener Proveedor\n\n"
            +"Función: Permite obtener un Proveedor por su ID\n\n"  
            +"Parámetros:\n"
            +"- ID:(Integer)\n\n"
            +"Ejemplo:\n"
            + "OBTENERPROVEEDOR {2}\n\n";
    
    public static final String AYUDA_MOSTRARPROVEEDORES = "Función: Muestra todos los Proveedores registrados (COMANDO SIN PARAMETROS)\n"
            + "MOSTRARPROVEEDORES \n\n";
    
    
    //GESTIONAR USUARIO
    public static final String AYUDA_REGISTRARUSUARIO =
            "Registrar Usuario\n\n"
            +"Función: Permite registrar un Usuario\n\n"  
            +"Parámetros:\n"
            +"- CI:(String con comillas dobles)\n"
            +"- Nombres:(String con comillas dobles)\n"
            +"- Apellido Paterno:(String con comillas dobles)\n"
            +"- Apellido Materno:(String con comillas dobles)\n"
            +"- Fecha de Nacimiento:(String con comillas dobles, en formato dd-mm-AAAA)\n"
            +"- Telefono:(String con comillas dobles)\n"
            +"- Email:(String con comillas dobles, debe contener un @)\n"
            +"- Direccion:(String con comillas dobles)\n"
            +"- Usuario:(String con comillas dobles)\n"
            +"- Contraseña:(String con comillas dobles)\n"
            +"- ID del Rol de Usuario:(Integer)\n\n"            
            +"Ejemplo:\n"
            + "REGISTRARUSUARIO {\"7359284\", \"Ricardo\", \"Trujillo\", \"Choque\", "
            + "\"21-03-1993\", \"72361072\", \"ricardo@gmail.com\", \"Calle S/N\", "
            + "\"ricardo123\", \"123456\", 1}\n\n";    
    
    public static final String AYUDA_MODIFICARUSUARIO =
            "Modificar Usuario\n\n"
            +"Función: Permite modificar un Usuario por su ID\n\n"  
            +"Parámetros:\n"
            +"- ID:(Integer)\n"
            +"- CI:(String con comillas dobles)\n"
            +"- Nombres:(String con comillas dobles)\n"
            +"- Apellido Paterno:(String con comillas dobles)\n"
            +"- Apellido Materno:(String con comillas dobles)\n"
            +"- Fecha de Nacimiento:(String con comillas dobles, en formato dd-mm-AAAA)\n"
            +"- Telefono:(String con comillas dobles)\n"
            +"- Email:(String con comillas dobles, debe contener un @)\n"
            +"- Direccion:(String con comillas dobles)\n"
            +"- Usuario:(String con comillas dobles)\n"
            +"- Contraseña:(String con comillas dobles)\n"
            +"- Estado del Usuario:(Boolean con comillas dobles, solos dos valores: true o false)\n"
            +"- ID del Rol de Usuario:(Integer)\n\n"            
            +"Ejemplo:\n"
            + "MODIFICARUSUARIO {2, \"7392864\", \"Ricardo\", \"Trujillo\", \"Carrasco\", "
            + "\"15-07-1995\", \"6139764\", \"ricardo@gmail.com\", \"Calle S/N\", \"ricardo123\", "
            + " \"123456\", \"false\", 2}\n\n"
            + "En caso de mantener un valor por defecto: Colocar guion bajo en el parametro \"_\" (EL PARAMETRO ID ES OBLIGATORIO)\n\n";
    
    public static final String AYUDA_ELIMINARUSUARIO =
            "Eliminar Usuario\n\n"
            +"Función: Permite eliminar un Usuario por su ID\n\n"  
            +"Parámetros:\n"
            +"- ID:(Integer)\n\n"
            +"Ejemplo:\n"
            + "ELIMINARUSUARIO {3}\n\n";
    
    public static final String AYUDA_OBTENERUSUARIO =
            "Obtener Usuario\n\n"
            +"Función: Permite obtener un Usuario por su ID\n\n"  
            +"Parámetros:\n"
            +"- ID:(Integer)\n\n"
            +"Ejemplo:\n"
            + "OBTENERUSUARIO {1}\n\n";
    
    public static final String AYUDA_MOSTRARUSUARIOS = "Función: Muestra todos los Usuarios registrados (COMANDO SIN PARAMETROS)\n"
            + "MOSTRARUSUARIOS \n\n";
    
        
    //GESTIONAR CLIENTE
    public static final String AYUDA_REGISTRARCLIENTE = 
            "Registrar Cliente\n\n"
            +"Función: Permite registrar un Cliente\n\n"  
            +"Parámetros:\n"
            +"- CI:(String con comillas dobles)\n"
            +"- Nombres:(String con comillas dobles)\n"
            +"- Apellido Paterno:(String con comillas dobles)\n"
            +"- Apellido Materno:(String con comillas dobles)\n"
            +"- Fecha de Nacimiento:(String con comillas dobles, en formato dd-mm-AAAA)\n"
            +"- Telefono:(String con comillas dobles)\n"
            +"- Email:(String con comillas dobles, debe contener un @)\n"
            +"- Direccion:(String con comillas dobles)\n\n"            
            +"Ejemplo:\n"
            + "REGISTRARCLIENTE {\"1234567\", \"Benito\", \"Gonzales\", \"Mamani\", "
            + "\"23-12-1990\", \"78286485\", \"benito@gmail.com\", \"Calle S/N\"}\n\n";    
    
    public static final String AYUDA_MODIFICARCLIENTE =
            "Modificar Usuario\n\n"
            +"Función: Permite modificar un Usuario por su ID\n\n"  
            +"Parámetros:\n"
            +"- ID:(Integer)\n"
            +"- CI:(String con comillas dobles)\n"
            +"- Nombres:(String con comillas dobles)\n"
            +"- Apellido Paterno:(String con comillas dobles)\n"
            +"- Apellido Materno:(String con comillas dobles)\n"
            +"- Fecha de Nacimiento:(String con comillas dobles, en formato dd-mm-AAAA)\n"
            +"- Telefono:(String con comillas dobles)\n"
            +"- Email:(String con comillas dobles, debe contener un @)\n"
            +"- Direccion:(String con comillas dobles)\n"            
            +"- Estado del Cliente:(Booleano con comillas dobles, solos dos valores: true o false)\n\n"                         
            +"Ejemplo:\n\n"
            + "MODIFICARCLIENTE {3, \"7654321\", \"Benito\", \"Gonzales\", \"Mamani\", "
            + "\"23-12-1990\", \"7839764\", \"benito@gmail.com\", \"Calle S/N\", \"false\"}\n\n"            
            + "En caso de mantener un valor por defecto: Colocar guion bajo en el parametro \"_\" (EL PARAMETRO ID ES OBLIGATORIO)\n\n";
    
    public static final String AYUDA_ELIMINARCLIENTE = 
            "Eliminar Cliente\n\n"
            +"Función: Permite eliminar un Cliente por su ID\n\n"  
            +"Parámetros:\n"
            +"- ID:(Integer)\n\n"
            +"Ejemplo:\n"
            + "ELIMINARCLIENTE {3}\n\n";
    
    public static final String AYUDA_OBTENERCLIENTE =
            "Obtener Cliente\n\n"
            +"Función: Permite obtener un Cliente por su ID\n\n"  
            +"Parámetros:\n"
            +"- ID:(Integer)\n\n"
            +"Ejemplo:\n"
            + "OBTENERCLIENTE {3}\n\n";
    
    public static final String AYUDA_MOSTRARCLIENTES = "Función: Muestra todos los Clientes registrados (COMANDO SIN PARAMETROS)\n"
            + "MOSTRARCLIENTES \n\n";
    
    
    //REALIZAR VENTA
    public static final String AYUDA_REGISTRARVENTA =
            "Registrar Venta\n\n"
            +"Función: Permite registrar una Venta\n\n"  
            +"Parámetros:\n"
            +"- Total:(Double con comillas dobles)\n"
            +"- Estado de la Venta:(String con comillas dobles)\n"
            +"- Numero de comprobante:(String con comillas dobles)\n"
            +"- ID del Cliente:(Integer)\n"
            +"- ID del Usuario:(Integer)\n"
            +"- Detalle de la Venta: Se enviara en el siguiente formato => \n"
            + "[{idProducto:(Integer), cantidad:(Integer), precio(Double), descuento:(Double)} | {} | ...(n detalles)] (Entre comillas dobles) \n\n"
            +"Ejemplo:\n"
            + "REGISTRARVENTA {\"150.5\", \"Pagado\", \"0456721\", 11, 1, "
            + "\"[{ 3, 5, 10.5, 0.5 } | { 1, 2, 3.00, 0.0 }]\"}\n\n";      
    
    public static final String AYUDA_OBTENERVENTAYDETALLES =
            "Obtener Venta\n\n"
            +"Función: Permite obtener una Venta con sus Detalles por el ID\n\n"  
            +"Parámetros:\n"
            +"- ID:(Integer)\n\n"
            +"Ejemplo:\n"
            + "OBTENERVENTAYDETALLES {2}\n\n";
    
    public static final String AYUDA_MOSTRARVENTAS = "Función: Muestra todos las Ventas registradas (COMANDO SIN PARAMETROS)\n"
            + "MOSTRARVENTAS \n\n";
            
 
    //REGISTRAR INGRESO
    public static final String AYUDA_REGISTRARINGRESO =
            "Registrar Ingreso\n\n"
            +"Función: Permite registrar un Ingreso\n\n"  
            +"Parámetros:\n"
            +"- Total de la Compra:(Double con comillas dobles)\n"
            +"- Tipo de comprobante:(String con comillas dobles)\n"
            +"- Numero de comprobante:(String con comillas dobles)\n"
            +"- Estado de la Compra:(String con comillas dobles)\n"            
            +"- ID del Proveedor:(Integer)\n"
            +"- ID del Usuario:(Integer)\n"      
            +"- Detalle del Ingreso: Se enviara en el siguiente formato => \n"
            + "[{idProducto:(Integer), cantidad:(Integer), precioCompra(Double)} | {} | ...(n detalles)] (Entre comillas dobles) \n\n"
            +"Ejemplo:\n"
            + "REGISTRARINGRESO {\"150.5\", \"Factura\", \"12345678\", \"Pagado\", 1, 1,"
            + "\"[{ 1, 15, 10.5 } | { 2, 5, 3.00 }]\"}\n\n";;
    
    public static final String AYUDA_OBTENERINGRESOYDETALLES =
            "Obtener Ingreso\n\n"
            +"Función: Permite obtener un Ingreso con sus Detalles por el ID\n\n"  
            +"Parámetros:\n"
            +"- ID:(Integer)\n\n"
            +"Ejemplo:\n"
            + "OBTENERINGRESOYDETALLES {2}\n\n";
    
    public static final String AYUDA_MOSTRARINGRESOS = "Función: Muestra todos los Ingresos registrados (COMANDO SIN PARAMETROS)\n"
            + "MOSTRARINGRESOS \n\n";
    
    
    //VISUALIZAR REPORTES    
    //REPORTE PRODUCTOS
    public static final String AYUDA_REPORTEPRODUCTOS = "Función: Muestra un archivo PDF con todos los Productos (COMANDO SIN PARAMETROS)\n"
            + "REPORTEPRODUCTOS \n\n";
    
    //REPORTE VENTAS
    public static final String AYUDA_REPORTEVENTAS = "Reporte de Ventas\n\n"
            +"Función: Muestra un archivo PDF con todas las Ventas\n\n"
            +"OPCIONAL: Si se desea ver las Ventas en un rango de fechas enviar los siguientes parametros: \n\n"
            +"Parámetros:\n"
            +"- Fecha Inicio:(String con comillas dobles, en formato dd-mm-AAAA)\n"
            +"- Fecha Fin:(String con comillas dobles, en formato dd-mm-AAAA)\n\n"
            +"Ejemplos:\n"
            + "REPORTEVENTAS => Sin Parametros para visualizar todas las Ventas\n"
            + "REPORTEVENTAS {\"01-01-2020\", \"31-12-2020\"} => Rango de fechas\n\n";
    
    //REPORTE VENTAS
    public static final String AYUDA_REPORTEINGRESOS = "Reporte de Ingresos\n\n"
            +"Función: Muestra un archivo PDF con todas los Ingresos\n\n"
            +"OPCIONAL: Si se desea ver los Ingresos en un rango de fechas enviar los siguientes parametros: \n\n"
            +"Parámetros:\n"
            +"- Fecha Inicio:(String con comillas dobles, en formato dd-mm-AAAA)\n"
            +"- Fecha Fin:(String con comillas dobles, en formato dd-mm-AAAA)\n\n"
            +"Ejemplos:\n"
            + "REPORTEINGRESOS => Sin Parametros para visualizar todos los Ingresos\n"
            + "REPORTEINGRESOS {\"01-01-2020\", \"31-12-2020\"} => Rango de fechas\n\n";       
        
    
    //VISUALIZAR ESTADISTICAS
    //ESTADISTICAS PRODUCTOS
    public static final String AYUDA_ESTADISTICASPRODUCTOSMASVENDIDOS = "Estadistica de los Productos mas vendidos\n\n"
            +"Función: Muestra una grafica con los Productos mas vendidos\n\n"
            +"NOTA: El comando funciona de acuerdo al numero de parametros enviados: \n\n"
            +"Ejemplo 1:\n"
            +"ESTADISTICASPRODUCTOSMASVENDIDOS => Sin Parametros para visualizar los productos mas vendidos tomando en cuenta todos los datos\n\n"
            +"Ejemplo 2:\n"
            +"ESTADISTICASPRODUCTOSMASVENDIDOS {\"2020\"} => De acuerdo a un año en particular\n"
            +"Parámetros:\n"
            +"- Año:(String con comillas dobles, en formato AAAA)\n\n"
            +"Ejemplo 3:\n"
            +"ESTADISTICASPRODUCTOSMASVENDIDOS {\"01-01-2020\"} => De acuerdo a un fecha en concreto\n"
            +"Parámetros:\n"
            +"- Fecha:(String con comillas dobles, en formato dd-mm-AAAA)\n\n"
            +"Ejemplo 4:\n"
            +"ESTADISTICASPRODUCTOSMASVENDIDOS {\"01-01-2020\", \"31-12-2020\"} => Rango de fechas\n"
            +"Parámetros:\n"
            +"- Fecha Inicio:(String con comillas dobles, en formato dd-mm-AAAA)\n"
            +"- Fecha Fin:(String con comillas dobles, en formato dd-mm-AAAA)\n\n";
            
    public static final String AYUDA_ESTADISTICASCLIENTESFIELES = "Función: Muestra una grafica con los Clientes mas fieles (COMANDO SIN PARAMETROS)\n"
            + "ESTADISTICASCLIENTESFIELES \n\n";
    
    public static final String AYUDA_ESTADISTICASPROVEEDORESMASSOLICITADOS = "Función: Muestra una grafica con los Proveedores mas solicitados (COMANDO SIN PARAMETROS)\n"
            + "ESTADISTICASPROVEEDORESMASSOLICITADOS \n\n"; 
            
    public static final String AYUDA_ESTADISTICASFINANCIERAS = "Estadisticas Financieras\n\n"
            +"Función: Muestra graficas de las Finanzas de Ventas e Ingresos\n\n"            
            +"Parámetros:\n"
            +"- Año:(String con comillas dobles, en formato AAAA)\n\n"
            +"ESTADISTICASFINANCIERAS {\"2020\"}\n";
    
}
