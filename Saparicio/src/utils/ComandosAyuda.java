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
            + "                 1.  ***MODULO INVENTARIO***\n\n"
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
            + "                            ESTADISTICASPROVEEDORESMASSOLICITADOS\n\n"
            
            + "-----------------------------------------------------------------------------------------------\n\n";
    
    
    
//----------------------------------PAQUETE USUARIO----------------------------//
    
    //GESTIONAR PRODUCTO
    public static final String AYUDA_REGISTRARPRODUCTO = "Lista\n\n" 
            +"Registrar Producto\n\n"
            +"Función:Permite registrar un Producto\n\n"  
            +"Parámetros:\n\n"
            +"- Codigo:(String con comillas dobles)\n"
            +"- Nombre:(String con comillas dobles)\n"
            +"- Descripcion:(String con comillas dobles)\n"
            +"- Precio:(String con comillas dobles)\n"
            +"- Stock:(Integer)\n"
            +"Ejemplo:\n\n"
            + "REGISTRARPRODUCTO {\"Codigo\", \"Nombre\", \"Descripcion\", \"Precio\", Stock}\n\n";    
    
    public static final String AYUDA_MODIFICARPRODUCTO =  "Lista\n\n" 
            +"Modificar Producto\n\n"
            +"Función:Permite modificar un Producto por su ID\n\n"  
            +"Parámetros:\n\n"
            +"- ID:(Integer)\n"
            +"- Codigo:(String con comillas dobles)\n"
            +"- Nombre:(String con comillas dobles)\n"
            +"- Descripcion:(String con comillas dobles)\n"
            +"- Estado:(String con comillas dobles)\n"
            +"- Precio:(String con comillas dobles)\n"
            +"- Stock:(Integer)\n"
            +"Ejemplo:\n\n"
            + "MODIFICARPRODUCTO {ID, \"Codigo\", \"Nombre\", \"Descripcion\", \"Estado\", \"Precio\", Stock}\n\n"
            + "En caso de mantener un valor por defecto: Colocar guion bajo en el parametro \"_\" (EL PARAMETRO ID ES OBLIGATORIO)\n\n";
    
    public static final String AYUDA_ELIMINARPRODUCTO ="Lista\n\n" 
            +"Eliminar Producto\n\n"
            +"Función:Permite eliminar un Producto por su ID\n\n"  
            +"Parámetros:\n\n"
            +"- ID:(Integer)\n"
            +"Ejemplo:\n\n"
            + "ELIMINARPRODUCTO {ID}\n\n";
    
    public static final String AYUDA_OBTENERPRODUCTO ="Lista\n\n" 
            +"Obtener Producto\n\n"
            +"Función: Permite obtener un Producto por su ID\n\n"  
            +"Parámetros:\n\n"
            +"- ID:(Integer)\n"
            +"ejemplo:\n\n"
            + "OBTENERPRODUCTO {ID}\n\n";
    
    public static final String AYUDA_MOSTRARPRODUCTOS = "Función: Muestra todos los Productos registrados (COMANDO SIN PARAMETROS)\n"
            + "MOSTRARPRODUCTO \n\n";
    
    
    
    //GESTIONAR PROVEEDOR
    public static final String AYUDA_REGISTRARPROVEEDOR = "Lista\n\n" 
            +"Registrar Proveedor\n\n"
            +"Función:Permite registrar un Proveedor\n\n"  
            +"Parámetros:\n\n"
            +"- Nombre:(String con comillas dobles)\n"
            +"- Tipo de documento:(String con comillas dobles)\n"
            +"- Numero de documento:(String con comillas dobles)\n"
            +"- Telefono:(String con comillas dobles)\n"
            +"- Direccion:(String con comillas dobles)\n"
            +"- Contacto:(String con comillas dobles)\n"
            +"- Telefono del contacto:(String con comillas dobles)\n"
            +"Ejemplo:\n\n"
            + "REGISTRARPROVEEDOR {\"Nombre\", \"TipoDocumento\", \"NumDocumento\", \"Telefono\", \"Direccion\", \"Contacto\", \"Telefono Contacto\"}\n\n";    
    
    public static final String AYUDA_MODIFICARPROVEEDOR =  "Lista\n\n" 
            +"Modificar Proveedor\n\n"
            +"Función:Permite modificar un Proveedor por su ID\n\n"  
            +"Parámetros:\n\n"
            +"- ID:(Integer)\n"
            +"- Nombre:(String con comillas dobles)\n"
            +"- Tipo de documento:(String con comillas dobles)\n"
            +"- Numero de documento:(String con comillas dobles)\n"
            +"- Telefono:(String con comillas dobles)\n"
            +"- Direccion:(String con comillas dobles)\n"
            +"- Contacto:(String con comillas dobles)\n"
            +"- Telefono del contacto:(String con comillas dobles)\n"
            +"- Estado del Proveedor:(String con comillas dobles)\n"
            +"Ejemplo:\n\n"
            + "MODIFICARPROVEEDOR {ID, \"Nombre\", \"TipoDocumento\", \"NumDocumento\", \"Telefono\", \"Direccion\", \"Contacto\", \"Telefono Contacto\", \"Estado\"}\n\n"
            + "En caso de mantener un valor por defecto: Colocar guion bajo en el parametro \"_\" (EL PARAMETRO ID ES OBLIGATORIO)\n\n";
    
    public static final String AYUDA_ELIMINARPROVEEDOR ="Lista\n\n" 
            +"Eliminar Proveedor\n\n"
            +"Función:Permite eliminar un Proveedor por su ID\n\n"  
            +"Parámetros:\n\n"
            +"- ID:(Integer)\n"
            +"Ejemplo:\n\n"
            + "ELIMINARPROVEEDOR {ID}\n\n";
    
    public static final String AYUDA_OBTENERPROVEEDOR ="Lista\n\n" 
            +"Obtener Proveedor\n\n"
            +"Función: Permite obtener un Proveedor por su ID\n\n"  
            +"Parámetros:\n\n"
            +"- ID:(Integer)\n"
            +"ejemplo:\n\n"
            + "OBTENERPROVEEDOR {ID}\n\n";
    
    public static final String AYUDA_MOSTRARPROVEEDORES = "Función: Muestra todos los Proveedores registrados (COMANDO SIN PARAMETROS)\n"
            + "MOSTRARPROVEEDORES \n\n";
    
    
    //GESTIONAR USUARIO
    public static final String AYUDA_REGISTRARUSUARIO = "Lista\n\n" 
            +"Registrar Usuario\n\n"
            +"Función:Permite registrar un Usuario\n\n"  
            +"Parámetros:\n\n"
            +"- CI:(String con comillas dobles)\n"
            +"- Nombres:(String con comillas dobles)\n"
            +"- Apellido Paterno:(String con comillas dobles)\n"
            +"- Apellido Materno:(String con comillas dobles)\n"
            +"- Fecha de Nacimiento:(String con comillas dobles, en formato dd/mm/AAAA)\n"
            +"- Telefono:(String con comillas dobles)\n"
            +"- Email:(String con comillas dobles, debe contener un @)\n"
            +"- Direccion:(String con comillas dobles)\n"
            +"- Usuario:(String con comillas dobles)\n"
            +"- Contraseña:(String con comillas dobles)\n"
            +"- ID del Rol de Usuario:(Integer)\n"
            
            +"Ejemplo:\n\n"
            + "REGISTRARPROVEEDOR {\"CI\", \"Nombres\", \"Paterno\", \"Materno\", "
            + "\"FechaNac\", \"Telefono\", \"Email\", \"Direccion\", \"Usuario\", "
            + " \"Contraseña\", iRol}\n\n";    
    
    public static final String AYUDA_MODIFICARUSUARIO =  "Lista\n\n" 
            +"Modificar Usuario\n\n"
            +"Función:Permite modificar un Usuario por su ID\n\n"  
            +"Parámetros:\n\n"
            +"- ID:(Integer)\n"
            +"- CI:(String con comillas dobles)\n"
            +"- Nombres:(String con comillas dobles)\n"
            +"- Apellido Paterno:(String con comillas dobles)\n"
            +"- Apellido Materno:(String con comillas dobles)\n"
            +"- Fecha de Nacimiento:(String con comillas dobles, en formato dd/mm/AAAA)\n"
            +"- Telefono:(String con comillas dobles)\n"
            +"- Email:(String con comillas dobles, debe contener un @)\n"
            +"- Direccion:(String con comillas dobles)\n"
            +"- Usuario:(String con comillas dobles)\n"
            +"- Contraseña:(String con comillas dobles)\n"
            +"- Estado del Usuario:(String con comillas dobles, solos dos valores: true o false)\n"
            +"- ID del Rol de Usuario:(Integer)\n"            
            
            +"Ejemplo:\n\n"
            + "MODIFICARUSUARIO {ID, \"CI\", \"Nombres\", \"Paterno\", \"Materno\", "
            + "\"FechaNac\", \"Telefono\", \"Email\", \"Direccion\", \"Usuario\", "
            + " \"Contraseña\", \"Estado\", iRol}\n\n"
            + "En caso de mantener un valor por defecto: Colocar guion bajo en el parametro \"_\" (EL PARAMETRO ID ES OBLIGATORIO)\n\n";
    
    public static final String AYUDA_ELIMINARUSUARIO ="Lista\n\n" 
            +"Eliminar Usuario\n\n"
            +"Función:Permite eliminar un Usuario por su ID\n\n"  
            +"Parámetros:\n\n"
            +"- ID:(Integer)\n"
            +"Ejemplo:\n\n"
            + "ELIMINARUSUARIO {ID}\n\n";
    
    public static final String AYUDA_OBTENERUSUARIO ="Lista\n\n" 
            +"Obtener Usuario\n\n"
            +"Función: Permite obtener un Usuario por su ID\n\n"  
            +"Parámetros:\n\n"
            +"- ID:(Integer)\n"
            +"ejemplo:\n\n"
            + "OBTENERUSUARIO {ID}\n\n";
    
    public static final String AYUDA_MOSTRARUSUARIOS = "Función: Muestra todos los Usuarios registrados (COMANDO SIN PARAMETROS)\n"
            + "MOSTRARUSUARIOS \n\n";
    
        
    //GESTIONAR CLIENTE
    public static final String AYUDA_REGISTRARCLIENTE = "Lista\n\n" 
            +"Registrar Cliente\n\n"
            +"Función:Permite registrar un Cliente\n\n"  
            +"Parámetros:\n\n"
            +"- CI:(String con comillas dobles)\n"
            +"- Nombres:(String con comillas dobles)\n"
            +"- Apellido Paterno:(String con comillas dobles)\n"
            +"- Apellido Materno:(String con comillas dobles)\n"
            +"- Fecha de Nacimiento:(String con comillas dobles, en formato dd/mm/AAAA)\n"
            +"- Telefono:(String con comillas dobles)\n"
            +"- Email:(String con comillas dobles, debe contener un @)\n"
            +"- Direccion:(String con comillas dobles)\n"
            
            +"Ejemplo:\n\n"
            + "REGISTRARCLIENTE {\"CI\", \"Nombres\", \"Paterno\", \"Materno\", "
            + "\"FechaNac\", \"Telefono\", \"Email\", \"Direccion\"}\n\n";    
    
    public static final String AYUDA_MODIFICARCLIENTE =  "Lista\n\n" 
            +"Modificar Usuario\n\n"
            +"Función:Permite modificar un Usuario por su ID\n\n"  
            +"Parámetros:\n\n"
            +"- ID:(Integer)\n"
            +"- CI:(String con comillas dobles)\n"
            +"- Nombres:(String con comillas dobles)\n"
            +"- Apellido Paterno:(String con comillas dobles)\n"
            +"- Apellido Materno:(String con comillas dobles)\n"
            +"- Fecha de Nacimiento:(String con comillas dobles, en formato dd/mm/AAAA)\n"
            +"- Telefono:(String con comillas dobles)\n"
            +"- Email:(String con comillas dobles, debe contener un @)\n"
            +"- Direccion:(String con comillas dobles)\n"            
            +"- Estado del Cliente:(String con comillas dobles, solos dos valores: true o false)\n"             
            
            +"Ejemplo:\n\n"
            + "MODIFICARCLIENTE {ID, \"CI\", \"Nombres\", \"Paterno\", \"Materno\", "
            + "\"FechaNac\", \"Telefono\", \"Email\", \"Direccion\", \"Estado\"}\n\n"            
            + "En caso de mantener un valor por defecto: Colocar guion bajo en el parametro \"_\" (EL PARAMETRO ID ES OBLIGATORIO)\n\n";
    
    public static final String AYUDA_ELIMINARCLIENTE ="Lista\n\n" 
            +"Eliminar Cliente\n\n"
            +"Función:Permite eliminar un Cliente por su ID\n\n"  
            +"Parámetros:\n\n"
            +"- ID:(Integer)\n"
            +"Ejemplo:\n\n"
            + "ELIMINARCLIENTE {ID}\n\n";
    
    public static final String AYUDA_OBTENERCLIENTE ="Lista\n\n" 
            +"Obtener Cliente\n\n"
            +"Función: Permite obtener un Cliente por su ID\n\n"  
            +"Parámetros:\n\n"
            +"- ID:(Integer)\n"
            +"ejemplo:\n\n"
            + "OBTENERCLIENTE {ID}\n\n";
    
    public static final String AYUDA_MOSTRARCLIENTES = "Función: Muestra todos los Clientes registrados (COMANDO SIN PARAMETROS)\n"
            + "MOSTRARCLIENTES \n\n";
    
    
    //REALIZAR VENTA
    public static final String AYUDA_REGISTRARVENTA = "Lista\n\n" 
            +"Registrar Venta\n\n"
            +"Función:Permite registrar una Venta\n\n"  
            +"Parámetros:\n\n"
            +"- Total:(String con comillas dobles)\n"
            +"- Estado de la Venta:(String con comillas dobles)\n"
            +"- Numero de comprobante:(String con comillas dobles)\n"
            +"- ID del Cliente:(Integer)\n"
            +"- ID del Usuario:(Integer)\n"      
            
            +"Ejemplo:\n\n"
            + "REGISTRARVENTA {\"Total\", \"Estado\", \"numComprobante\", idCliente, idUsuario}\n\n";           
    
    public static final String AYUDA_OBTENERVENTAYDETALLES ="Lista\n\n" 
            +"Obtener Venta\n\n"
            +"Función: Permite obtener una Venta con sus Detalles por el ID\n\n"  
            +"Parámetros:\n\n"
            +"- ID:(Integer)\n"
            +"ejemplo:\n\n"
            + "OBTENERVENTAYDETALLES {ID}\n\n";
    
    public static final String AYUDA_MOSTRARVENTAS = "Función: Muestra todos las Ventas registradas (COMANDO SIN PARAMETROS)\n"
            + "MOSTRARVENTAS \n\n";
            
 
    //REGISTRAR INGRESO
    public static final String AYUDA_REGISTRARINGRESO = "Lista\n\n" 
            +"Registrar Ingreso\n\n"
            +"Función:Permite registrar un Ingreso\n\n"  
            +"Parámetros:\n\n"
            +"- Total de la Compra:(String con comillas dobles)\n"
            +"- Tipo de comprobante:(String con comillas dobles)\n"
            +"- Numero de comprobante:(String con comillas dobles)\n"
            +"- Estado de la Compra:(String con comillas dobles)\n"            
            +"- ID del Proveedor:(Integer)\n"
            +"- ID del Usuario:(Integer)\n"      
            
            +"Ejemplo:\n\n"
            + "REGISTRARINGRESO {\"Total\", \"tipoComprobante\", \"numComprobante\", \"estado\", idCliente, idUsuario}\n\n";
    
    public static final String AYUDA_OBTENERINGRESOYDETALLES ="Lista\n\n" 
            +"Obtener Ingreso\n\n"
            +"Función: Permite obtener un Ingreso con sus Detalles por el ID\n\n"  
            +"Parámetros:\n\n"
            +"- ID:(Integer)\n"
            +"ejemplo:\n\n"
            + "OBTENERINGRESOYDETALLES {ID}\n\n";
    
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
            +"Parámetros:\n\n"
            +"- Fecha Inicio:(String con comillas dobles, en formato AAAA-mm-dd)\n"
            +"- Fecha Fin:(String con comillas dobles, en formato AAAA-mm-dd)\n"
            +"Ejemplos:\n\n"
            + "REPORTEVENTAS => Sin Parametros para visualizar todas las Ventas\n"
            + "REPORTEVENTAS {\"FechaInicio\", \"FechaFin\"} => Rango de fechas\n\n";
    
    //REPORTE VENTAS
    public static final String AYUDA_REPORTEINGRESOS = "Reporte de Ingresos\n\n"
            +"Función: Muestra un archivo PDF con todas los Ingresos\n\n"
            +"OPCIONAL: Si se desea ver los Ingresos en un rango de fechas enviar los siguientes parametros: \n\n"
            +"Parámetros:\n\n"
            +"- Fecha Inicio:(String con comillas dobles, en formato AAAA-mm-dd)\n"
            +"- Fecha Fin:(String con comillas dobles, en formato AAAA-mm-dd)\n"
            +"Ejemplos:\n\n"
            + "REPORTEINGRESOS => Sin Parametros para visualizar todos los Ingresos\n"
            + "REPORTEINGRESOS {\"FechaInicio\", \"FechaFin\"} => Rango de fechas\n\n";       
        
    
    //VISUALIZAR ESTADISTICAS
    //ESTADISTICAS PRODUCTOS
    public static final String AYUDA_ESTADISTICASPRODUCTOSMASVENDIDOS = "Estadistica de los Productos mas vendidos\n\n"
            +"Función: Muestra una grafica con los Productos mas vendidos\n\n"
            +"NOTA: El comando funciona de acuerdo al numero de parametros enviados: \n\n"
            +"Ejemplo 1:\n"
            +"ESTADISTICASPRODUCTOSMASVENDIDOS => Sin Parametros para visualizar los productos mas vendidos tomando en cuenta todos los datos\n\n"
            +"Ejemplo 2:\n"
            +"ESTADISTICASPRODUCTOSMASVENDIDOS {\"Año\"} => De acuerdo a un año en particular\n"
            +"Parámetros:\n"
            +"- Año:(String con comillas dobles, en formato AAAA)\n\n"
            +"Ejemplo 3:\n"
            +"ESTADISTICASPRODUCTOSMASVENDIDOS {\"Fecha\"} => De acuerdo a un fecha en concreto\n"
            +"Parámetros:\n"
            +"- Fecha:(String con comillas dobles, en formato AAAA-mm-dd)\n\n"
            +"Ejemplo 4:\n"
            +"ESTADISTICASPRODUCTOSMASVENDIDOS {\"FechaInicio\", \"FechaFin\"} => Rango de fechas\n"
            +"Parámetros:\n"
            +"- Fecha Inicio:(String con comillas dobles, en formato AAAA-mm-dd)\n"
            +"- Fecha Fin:(String con comillas dobles, en formato AAAA-mm-dd)\n\n";
            
    public static final String AYUDA_ESTADISTICASCLIENTESFIELES = "Función: Muestra una grafica con los Clientes mas fieles (COMANDO SIN PARAMETROS)\n"
            + "ESTADISTICASCLIENTESFIELES \n\n";
    
    public static final String AYUDA_ESTADISTICASPROVEEDORESMASSOLICITADOS = "Función: Muestra una grafica con los Proveedores mas solicitados (COMANDO SIN PARAMETROS)\n"
            + "ESTADISTICASPROVEEDORESMASSOLICITADOS \n\n"; 
            
    public static final String AYUDA_ESTADISTICASFINANCIERAS = "Estadisticas Financieras\n\n"
            +"Función: Muestra graficas de las Finanzas de Ventas e Ingresos\n\n"            
            +"Parámetros:\n"
            +"- Año:(String con comillas dobles, en formato AAAA)\n\n"
            +"ESTADISTICASFINANCIERAS {\"Año\"}\n";
}
