package presentacion;


import correo.ClienteSMTP;
import correo.mimeMail;
import javax.swing.table.DefaultTableModel;
import negocio.NCliente;
import negocio.NEstadistica;
import negocio.NIngreso;
import negocio.NProducto;
import negocio.NProveedor;
import negocio.NReporte;
import negocio.NUsuario;
import negocio.NVenta;
import procesador.Analex;
import procesador.Cinta;
import procesador.Parser;
import procesador.Token;
import utils.ComandosAyuda;
import utils.Constante;
import utils.Utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fbasc
 */
public class Saparicio {
    
    public void procesarMensaje(String Mensaje) {
        String destinatario = Utils.getDestinatario(Mensaje);
        System.out.println("Destinatario: " + destinatario);
        String content = Utils.getSubjectOrden(Mensaje);
        //String content = Herramientas.obtSubject(Mensaje);
        System.out.println("Sistema de la distribuidora 'APARICIO' " + content);

        //Usuario user = new Usuario();
        //Verificamos si el usuario esta registrado en el sistema

        //Quitamos formato extra en caso de reenvio o de respuesta de mensajes
        content = Utils.quitar_formato_mail(content);
        
        System.out.println("Contenido es: " + content);
        
        Cinta cinta = new Cinta(content);
        Analex analex = new Analex(cinta);
        Parser parser = new Parser(analex);
        
        // Verificar Orden
        parser.Expresion(); //verifica la estructura

        if (parser.errorFlag) {
            // Enviar Correo de Error
            ClienteSMTP.sendMail(destinatario, 
                    "Error de Comando", 
                    "El comando:  " + analex.M.texto + 
                            "\n es incorrecto!, Porfavor consulte los comandos disponibles a continuacion: \n\n" + 
                            ComandosAyuda.AYUDA_GENERAL);
            return;
        }

        // Si todo va bien, procesar el Comando
        analex.Init();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {
            // Mostrar Ayudas
            ClienteSMTP.sendMail(destinatario, Constante.msgAyudaPropietario, ComandosAyuda.AYUDA_GENERAL);
            return;
        }

        int val = 0;
        if (val == 0) {
            // Sino es HELP, es una funcionalidad
            switch (token.getAtributo()) {
                                
                case Token.REGISTRARPRODUCTO: {
                    registrarProducto(analex, destinatario);
                }
                break;
                case Token.MODIFICARPRODUCTO: {
                    modificarProducto(analex, destinatario);
                }
                break;
                case Token.ELIMINARPRODUCTO: {                    
                    eliminarProducto(analex, destinatario);
                }
                break;
                case Token.OBTENERPRODUCTO: {
                    obtenerProducto(analex, destinatario);
                }
                break;
                case Token.MOSTRARPRODUCTOS: {
                    mostrarProductos(analex, destinatario);
                }
                break;
                
                
                case Token.REGISTRARPROVEEDOR: {
                    registrarProveedor(analex, destinatario);
                }
                break;
                case Token.MODIFICARPROVEEDOR: {
                    modificarProveedor(analex, destinatario);
                }
                break;
                case Token.ELIMINARPROVEEDOR: {                    
                    eliminarProveedor(analex, destinatario);
                }
                break;
                case Token.OBTENERPROVEEDOR: {
                    obtenerProveedor(analex, destinatario);
                }
                break;
                case Token.MOSTRARPROVEEDORES: {
                    mostrarProveedores(analex, destinatario);
                }
                break;
                
                
                case Token.REGISTRARUSUARIO: {
                    registrarUsuario(analex, destinatario);
                }
                break;
                case Token.MODIFICARUSUARIO: {
                    modificarUsuario(analex, destinatario);
                }
                break;
                case Token.ELIMINARUSUARIO: {                    
                    eliminarUsuario(analex, destinatario);
                }
                break;
                case Token.OBTENERUSUARIO: {
                    obtenerUsuario(analex, destinatario);
                }
                break;
                case Token.MOSTRARUSUARIOS: {
                    mostrarUsuarios(analex, destinatario);
                }
                break;
                
                
                case Token.REGISTRARCLIENTE: {
                    registrarCliente(analex, destinatario);
                }
                break;
                case Token.MODIFICARCLIENTE: {
                    modificarCliente(analex, destinatario);
                }
                break;
                case Token.ELIMINARCLIENTE: {                    
                    eliminarCliente(analex, destinatario);
                }
                break;
                case Token.OBTENERCLIENTE: {
                    obtenerCliente(analex, destinatario);
                }
                break;
                case Token.MOSTRARCLIENTES: {
                    mostrarClientes(analex, destinatario);
                }
                break;
                
                
                case Token.REGISTRARVENTA: {
                    realizarVenta(analex, destinatario);
                }
                break;
                case Token.OBTENERVENTAYDETALLES: {
                    obtenerVentaYDetalles(analex, destinatario);
                }
                break;
                case Token.MOSTRARVENTAS: {
                    mostrarVentas(analex, destinatario);
                }
                break;
                
                
                case Token.REGISTRARINGRESO: {
                    registrarIngreso(analex, destinatario);
                }
                break;
                case Token.OBTENERINGRESOYDETALLES: {
                    obtenerIngresoYDetalles(analex, destinatario);
                }
                break;
                case Token.MOSTRARINGRESOS: {
                    mostrarIngresos(analex, destinatario);
                }
                break;
                
                
                case Token.REPORTEPRODUCTOS: {
                    reporteProductos(analex, destinatario);
                }
                break;
                case Token.REPORTEVENTAS: {
                    reporteVentas(analex, destinatario);
                }
                break;
                case Token.REPORTEINGRESOS: {
                    reporteIngresos(analex, destinatario);
                }
                break;
                
                
                case Token.ESTADISTICASPRODUCTOSMASVENDIDOS: {
                    estadisticasProductoMasVendidos(analex, destinatario);
                }
                break;
                case Token.ESTADISTICASCLIENTESFIELES: {
                    estadisticasClientesFieles(analex, destinatario);
                }
                break;
                case Token.ESTADISTICASPROVEEDORESMASSOLICITADOS: {
                    estadisticasProveedoresMasSolicitados(analex, destinatario);
                }
                break;
                case Token.ESTADISTICASFINANCIERAS: {
                    estadisticasFinancieras(analex, destinatario);
                }
                break;
                                
            }

        } else {
            ClienteSMTP.sendMail(destinatario, "No se encuentra registrado!", "El sistema no puede atender sus peticiones dado que no se encuentra registrado, favor comuniquese con el administrador, Gracias... :)");
        }
    }//end procesarMensaje
    
    //-----------------------------------GESTIONAR PRODUCTO---------------------------------------------------     
    
    //OK
    private void registrarProducto(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //clienteNegocio clienteNegocio = new clienteNegocio();
            //String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());
            ClienteSMTP.sendMail(correoDest, 
                    Constante.msgAyudaPropietario + "\n\n", 
                    ComandosAyuda.AYUDA_REGISTRARPRODUCTO);
            return;
        }
        try {
            // Sino, ejecutar el comando
            NProducto producto = new NProducto();
            analex.Avanzar();
            
            // Atributos                                                                
            String codigo = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String descripcion = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String precio = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            int stock = analex.Preanalisis().getAtributo();                       

            producto.registrarProducto(codigo, nombre, descripcion, precio, stock);

            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Registrar Producto", 
                    Constante.IngresoPositivoR + "\n\n" + 
                            Utils.dibujarTablawithHTMLwithoutOpciones(producto.mostrarProductos()));

        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Registrar Producto", 
                    Constante.IngresoErrorR + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    //OK
    private void modificarProducto(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, ComandosAyuda.AYUDA_MODIFICARPRODUCTO);
            return;
        }

        // Sino, ejecutar el comando
        NProducto producto = new NProducto();
        analex.Avanzar();

        //sacar el id de 
        int id = analex.Preanalisis().getAtributo();
        if (producto.ExisteProducto(id)) {
            try {
                //analex.Preanalisis().setAtributo(Math.round(analex.Preanalisis().getAtributo()));
                //int id= clienteNegocio.obtenerId(email);
                DefaultTableModel productot = producto.mostrarProductosId(id);

                // Revisar los GuionBajo
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String codigo = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(productot.getValueAt(0, 1));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String nombre = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(productot.getValueAt(0, 2));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String descripcion = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(productot.getValueAt(0, 3));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String estado = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(productot.getValueAt(0, 4));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String precio = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(productot.getValueAt(0, 5));
                                
                analex.Avanzar();
                analex.Avanzar();
                int stock = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? analex.Preanalisis().getAtributo()
                        : Integer.parseInt(String.valueOf(productot.getValueAt(0, 6)));
                

                producto.modificarProducto(id, codigo, nombre, descripcion, estado, precio, stock);
                //clienteNegocio.modificarCliente(id,nombre,apellido,telefono, direccion, email);
                // ClienteSMTP.sendMail(correoDest, "Modificar Cliente", Constante.IngresoPositivoM);
                mimeMail mimemailer = new mimeMail();
                mimemailer.sendHtmlEmail(correoDest, "Modificar Producto", Constante.IngresoPositivoM + "\n\n" + Utils.dibujarTablawithHTMLwithoutOpciones(producto.mostrarProductos()));
            } catch (Exception e) {
                ClienteSMTP.sendMail(correoDest, "Modificar Producto", Constante.IngresoErrorM + "\n" + "Mensaje enviado: " + analex.M.texto);
            }

        } else {
            String msg = ComandosAyuda.AYUDA_MODIFICARPRODUCTO;
            ClienteSMTP.sendMail(correoDest, Constante.AsuntoError, "El Producto no se encuentra registrado!\n\n" + "Mensaje enviado: " + analex.M.texto + msg);
        }

    }

    //OK
    private void eliminarProducto(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, ComandosAyuda.AYUDA_ELIMINARPRODUCTO);
            return;
        }
        try {
            // Sino, ejecutar el comando
            NProducto producto = new NProducto();
            analex.Avanzar();  // avanzar del (
            //String email = Herramientas.quitarComillas(analex.Preanalisis().getToStr());
            int id = analex.Preanalisis().getAtributo();

            if (id != 0 && producto.ExisteProducto(id)) {
                producto.eliminarProducto(id);
                //ClienteSMTP.sendMail(correoDest, "Eliminar CLiente", Constante.IngresoPositivoE); 
                mimeMail mimemailer = new mimeMail();
                mimemailer.sendHtmlEmail(correoDest, "Eliminar Producto", Constante.IngresoPositivoE + "\n\n" + Utils.dibujarTablawithHTMLwithoutOpciones(producto.mostrarProductos()));

            } else {
                ClienteSMTP.sendMail(correoDest, Constante.AsuntoError, Constante.NoExisteProducto + "\n" + "Mensaje enviado: " + analex.M.texto);
            }
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Eliminar Producto", Constante.IngresoErrorE + "\n" + "Mensaje enviado: " + analex.M.texto);
        }

    }

    //OK
    private void obtenerProducto(Analex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, Constante.msgAyudaPropietario, ComandosAyuda.AYUDA_OBTENERPRODUCTO);
            return;
        }

        // Sino, ejecutar el comando
        NProducto producto = new NProducto();
        analex.Avanzar();
        //String email = Herramientas.quitarComillas(analex.Preanalisis().getToStr());        
        int id = analex.Preanalisis().getAtributo();
        //int id= clienteNegocio.obtenerId(email);
        
        if (id != 0 && producto.ExisteProducto(id)) {
            //String mensaje = Herramientas.dibujarTabla(clienteNegocio.obtenerCliente(id));  
            String mensaje = Utils.dibujarTablawithHTMLwithoutOpciones(producto.mostrarProductosId(id));
            mimeMail mailer = new mimeMail();
            try {
                mailer.sendHtmlEmail(destinatario, "Obtener datos", "<h1>Obtener Producto</h1> \n\n" + mensaje);
            } catch (Exception e) {
                ClienteSMTP.sendMail(destinatario, Constante.AsuntoError, "No se pudo obtener el Producto" + "\n" + "Mensaje enviado: " + analex.M.texto);
            }
            // ClienteSMTP.sendMail(destinatario, "Obtener Cliente\n\n", mensaje);          
        } else {
            ClienteSMTP.sendMail(destinatario, Constante.AsuntoError, "No se pudo obtener el Producto" + "\n" + "Mensaje enviado: " + analex.M.texto);
        }

    }

    //OK
    private void mostrarProductos(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, ComandosAyuda.AYUDA_MOSTRARPRODUCTOS);
            return;
        }
        // Sino, ejecutar el comando
        NProducto producto = new NProducto();

        try {
            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Mostrar Productos", 
                    "Lista de Productos\n" + 
                            Utils.dibujarTablawithHTMLwithoutOpciones(producto.mostrarProductos()));
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Mostrar Productos", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }
    }
    
    
    //-----------------------------------GESTIONAR PROVEEDOR---------------------------------------------------     
    
    //OK
    private void registrarProveedor(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //clienteNegocio clienteNegocio = new clienteNegocio();
            //String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario + "\n\n", ComandosAyuda.AYUDA_REGISTRARPROVEEDOR);
            return;
        }
        try {
            // Sino, ejecutar el comando
            NProveedor proveedor = new NProveedor();
            analex.Avanzar();
            
            // Atributos                                                                
            String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String tipoDocumento = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String numDocumento = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String telefono = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String direccion = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String contacto = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String telefonoContacto = Utils.quitarComillas(analex.Preanalisis().getToStr());            

            proveedor.registrarProveedor(nombre, tipoDocumento, numDocumento, telefono, direccion, contacto, telefonoContacto);

            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Registrar Proveedor", Constante.IngresoPositivoR + "\n\n" + Utils.dibujarTablawithHTMLwithoutOpciones(proveedor.mostrarProveedores()));

        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Registrar Proveedor", Constante.IngresoErrorR + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    //OK
    private void modificarProveedor(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, ComandosAyuda.AYUDA_MODIFICARPROVEEDOR);
            return;
        }

        // Sino, ejecutar el comando
        NProveedor proveedor = new NProveedor();
        analex.Avanzar();

        //sacar el id de 
        int id = analex.Preanalisis().getAtributo();
        if (proveedor.existeProveedor(id)) {
            try {
                //analex.Preanalisis().setAtributo(Math.round(analex.Preanalisis().getAtributo()));
                //int id= clienteNegocio.obtenerId(email);
                DefaultTableModel proveedort = proveedor.mostrarProveedorId(id);

                // Revisar los GuionBajo
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String nombre = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(proveedort.getValueAt(0, 1));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String tipoDocumento = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(proveedort.getValueAt(0, 2));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String numDocumento = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(proveedort.getValueAt(0, 3));                                
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String telefono = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(proveedort.getValueAt(0, 4));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String direccion = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(proveedort.getValueAt(0, 5));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String contacto = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(proveedort.getValueAt(0, 6));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String telefonoContacto = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(proveedort.getValueAt(0, 7));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String estado = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(proveedort.getValueAt(0, 8));
                

                proveedor.modificarProveedor(id, nombre, tipoDocumento, numDocumento, 
                        telefono, direccion, contacto, telefonoContacto, estado);
                //clienteNegocio.modificarCliente(id,nombre,apellido,telefono, direccion, email);
                // ClienteSMTP.sendMail(correoDest, "Modificar Cliente", Constante.IngresoPositivoM);
                mimeMail mimemailer = new mimeMail();
                mimemailer.sendHtmlEmail(correoDest, "Modificar Proveedor", Constante.IngresoPositivoM + "\n\n" + Utils.dibujarTablawithHTMLwithoutOpciones(proveedor.mostrarProveedores()));
            } catch (Exception e) {
                ClienteSMTP.sendMail(correoDest, "Modificar Proveedor", Constante.IngresoErrorM + "\n" + "Mensaje enviado: " + analex.M.texto);
            }

        } else {
            String msg = ComandosAyuda.AYUDA_MODIFICARPROVEEDOR;
            ClienteSMTP.sendMail(correoDest, Constante.AsuntoError, "El Proveedor no se encuentra registrado!\n\n" + "Mensaje enviado: " + analex.M.texto + msg);
        }

    }

    //OK
    private void eliminarProveedor(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, ComandosAyuda.AYUDA_ELIMINARPROVEEDOR);
            return;
        }
        try {
            // Sino, ejecutar el comando
            NProveedor proveedor = new NProveedor();
            analex.Avanzar();  // avanzar del (
            //String email = Herramientas.quitarComillas(analex.Preanalisis().getToStr());
            int id = analex.Preanalisis().getAtributo();

            if (id != 0 && proveedor.existeProveedor(id)) {
                proveedor.eliminarProveedor(id);
                //ClienteSMTP.sendMail(correoDest, "Eliminar CLiente", Constante.IngresoPositivoE); 
                mimeMail mimemailer = new mimeMail();
                mimemailer.sendHtmlEmail(correoDest, "Eliminar Proveedor", Constante.IngresoPositivoE + "\n\n" + Utils.dibujarTablawithHTMLwithoutOpciones(proveedor.mostrarProveedores()));

            } else {
                ClienteSMTP.sendMail(correoDest, Constante.AsuntoError, Constante.NoExisteProveedor + "\n" + "Mensaje enviado: " + analex.M.texto);
            }
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Eliminar Proveedor", Constante.IngresoErrorE + "\n" + "Mensaje enviado: " + analex.M.texto);
        }

    }

    //OK
    private void obtenerProveedor(Analex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, Constante.msgAyudaPropietario, ComandosAyuda.AYUDA_OBTENERPROVEEDOR);
            return;
        }

        // Sino, ejecutar el comando
        NProveedor proveedor = new NProveedor();
        analex.Avanzar();
        //String email = Herramientas.quitarComillas(analex.Preanalisis().getToStr());        
        int id = analex.Preanalisis().getAtributo();
        //int id= clienteNegocio.obtenerId(email);
        
        if (id != 0 && proveedor.existeProveedor(id)) {
            //String mensaje = Herramientas.dibujarTabla(clienteNegocio.obtenerCliente(id));  
            String mensaje = Utils.dibujarTablawithHTMLwithoutOpciones(proveedor.mostrarProveedorId(id));
            mimeMail mailer = new mimeMail();
            try {
                mailer.sendHtmlEmail(destinatario, "Obtener datos", "<h1>Obtener Proveedor</h1> \n\n" + mensaje);
            } catch (Exception e) {
                ClienteSMTP.sendMail(destinatario, Constante.AsuntoError, "No se pudo obtener el Proveedor" + "\n" + "Mensaje enviado: " + analex.M.texto);
            }
            // ClienteSMTP.sendMail(destinatario, "Obtener Cliente\n\n", mensaje);          
        } else {
            ClienteSMTP.sendMail(destinatario, Constante.AsuntoError, "No se pudo obtener el Proveedor" + "\n" + "Mensaje enviado: " + analex.M.texto);
        }

    }

    //OK
    private void mostrarProveedores(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, ComandosAyuda.AYUDA_MOSTRARPROVEEDORES);
            return;
        }
        // Sino, ejecutar el comando
        NProveedor proveedor = new NProveedor();

        try {
            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Mostrar Proveedores", "Lista de Proveedores\n" + Utils.dibujarTablawithHTMLwithoutOpciones(proveedor.mostrarProveedores()));
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Mostrar Proveedores", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }
    }


    //-----------------------------------GESTIONAR USUARIO---------------------------------------------------     
    
    //OK
    private void registrarUsuario(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //clienteNegocio clienteNegocio = new clienteNegocio();
            //String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario + "\n\n", ComandosAyuda.AYUDA_REGISTRARUSUARIO);
            return;
        }
        try {
            // Sino, ejecutar el comando
            NUsuario usuario = new NUsuario();
            analex.Avanzar();
            
            // Atributos                                                                
            String ci = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String nombres = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String paterno = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String materno = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String fechaNac = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String telefono = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String email = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String direccion = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String user = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String password = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            int idRol = analex.Preanalisis().getAtributo();

            usuario.registrarUsuario(ci, 
                                    nombres, 
                                    paterno, 
                                    materno, 
                                    Utils.ddMMyyyyToyyyyMMddDate(fechaNac), 
                                    telefono, 
                                    email, 
                                    direccion, 
                                    user, 
                                    password, 
                                    idRol);

            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Registrar Usuario", Constante.IngresoPositivoR + 
                    "\n\n" + Utils.dibujarTablawithHTMLwithoutOpciones(usuario.mostrarUsuarios()));

        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Registrar Proveedor", Constante.IngresoErrorR + 
                    "\n" + "Mensaje enviado: " + analex.M.texto);
            e.printStackTrace();
            
        }
    }

    //OK
    private void modificarUsuario(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, ComandosAyuda.AYUDA_MODIFICARUSUARIO);
            return;
        }

        // Sino, ejecutar el comando
        NUsuario usuario = new NUsuario();
        analex.Avanzar();

        //sacar el id de 
        int id = analex.Preanalisis().getAtributo();
        if (usuario.existeUsuario(id)) {
            try {
                //analex.Preanalisis().setAtributo(Math.round(analex.Preanalisis().getAtributo()));
                //int id= clienteNegocio.obtenerId(email);
                DefaultTableModel usuariot = usuario.mostrarUsuarioId(id);

                // Revisar los GuionBajo
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String ci = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(usuariot.getValueAt(0, 1));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String nombres = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(usuariot.getValueAt(0, 2));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String paterno = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(usuariot.getValueAt(0, 3));                                
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String materno = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(usuariot.getValueAt(0, 4));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String fechaNac = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.ddMMyyyyToyyyyMMddDate(Utils.quitarComillas(analex.Preanalisis().getToStr())) //cambiar formato fecha si es nueva
                        : String.valueOf(usuariot.getValueAt(0, 5));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String telefono = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(usuariot.getValueAt(0, 6));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String email = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(usuariot.getValueAt(0, 7));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String direccion = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(usuariot.getValueAt(0, 8));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String user = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(usuariot.getValueAt(0, 9));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String password = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.md5(Utils.quitarComillas(analex.Preanalisis().getToStr())) //cifrar a md5 si es nueva
                        : String.valueOf(usuariot.getValueAt(0, 10));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String estado = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(usuariot.getValueAt(0, 11));
                
                analex.Avanzar();
                analex.Avanzar();
                int idRol = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? analex.Preanalisis().getAtributo()
                        : Integer.parseInt(String.valueOf(usuariot.getValueAt(0, 12)));
                

                usuario.modificarUsuario(id, 
                                        ci, 
                                        nombres, 
                                        paterno, 
                                        materno, 
                                        fechaNac, 
                                        telefono, 
                                        email, 
                                        direccion,
                                        user, 
                                        password, 
                                        estado, 
                                        idRol);
                
                //clienteNegocio.modificarCliente(id,nombre,apellido,telefono, direccion, email);
                // ClienteSMTP.sendMail(correoDest, "Modificar Cliente", Constante.IngresoPositivoM);
                mimeMail mimemailer = new mimeMail();
                mimemailer.sendHtmlEmail(correoDest, "Modificar Usuario", Constante.IngresoPositivoM + 
                        "\n\n" + Utils.dibujarTablawithHTMLwithoutOpciones(usuario.mostrarUsuarios()));
                
            } catch (Exception e) {
                ClienteSMTP.sendMail(correoDest, "Modificar Usuario", Constante.IngresoErrorM + 
                        "\n" + "Mensaje enviado: " + analex.M.texto);
            }

        } else {
            String msg = ComandosAyuda.AYUDA_MODIFICARUSUARIO;
            ClienteSMTP.sendMail(correoDest, Constante.AsuntoError, "El Usuario no se encuentra registrado!\n\n" 
                    + "Mensaje enviado: " + analex.M.texto + msg);
        }

    }

    //OK
    private void eliminarUsuario(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, ComandosAyuda.AYUDA_ELIMINARUSUARIO);
            return;
        }
        try {
            // Sino, ejecutar el comando
            NUsuario usuario = new NUsuario();
            analex.Avanzar();  // avanzar del (
            //String email = Herramientas.quitarComillas(analex.Preanalisis().getToStr());
            int id = analex.Preanalisis().getAtributo();

            if (id != 0 && usuario.existeUsuario(id)) {
                usuario.eliminarUsuario(id);
                //ClienteSMTP.sendMail(correoDest, "Eliminar CLiente", Constante.IngresoPositivoE); 
                mimeMail mimemailer = new mimeMail();
                mimemailer.sendHtmlEmail(correoDest, "Eliminar Usuario", Constante.IngresoPositivoE + 
                        "\n\n" + Utils.dibujarTablawithHTMLwithoutOpciones(usuario.mostrarUsuarios()));

            } else {
                ClienteSMTP.sendMail(correoDest, Constante.AsuntoError, Constante.NoExisteUsuario + 
                        "\n" + "Mensaje enviado: " + analex.M.texto);
            }
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Eliminar Usuario", Constante.IngresoErrorE + 
                    "\n" + "Mensaje enviado: " + analex.M.texto);
        }

    }

    //OK
    private void obtenerUsuario(Analex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_OBTENERUSUARIO);
            return;
        }

        // Sino, ejecutar el comando
        NUsuario usuario = new NUsuario();
        analex.Avanzar();
        //String email = Herramientas.quitarComillas(analex.Preanalisis().getToStr());        
        int id = analex.Preanalisis().getAtributo();
        //int id= clienteNegocio.obtenerId(email);
        
        if (id != 0 && usuario.existeUsuario(id)) {
            //String mensaje = Herramientas.dibujarTabla(clienteNegocio.obtenerCliente(id));  
            String mensaje = Utils.dibujarTablawithHTMLwithoutOpciones(usuario.mostrarUsuarioId(id));
            mimeMail mailer = new mimeMail();
            try {
                mailer.sendHtmlEmail(destinatario, "Obtener datos", "<h1>Obtener Usuario</h1> \n\n" + mensaje);
            } catch (Exception e) {
                ClienteSMTP.sendMail(destinatario, Constante.AsuntoError, "No se pudo obtener el Usuario" + 
                        "\n" + "Mensaje enviado: " + analex.M.texto);
            }
            // ClienteSMTP.sendMail(destinatario, "Obtener Cliente\n\n", mensaje);          
        } else {
            ClienteSMTP.sendMail(destinatario, Constante.AsuntoError, "No se pudo obtener el Usuario" + 
                    "\n" + "Mensaje enviado: " + analex.M.texto);
        }

    }

    //OK
    private void mostrarUsuarios(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_MOSTRARUSUARIOS);
            return;
        }
        // Sino, ejecutar el comando
        NUsuario usuario = new NUsuario();

        try {
            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Mostrar Usuarios", "Lista de Usuarios\n" 
                    + Utils.dibujarTablawithHTMLwithoutOpciones(usuario.mostrarUsuarios()));
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Mostrar Usuarios", 
                    "error durante la obtencion de la tabla, verifique con el comando HELP");

        }
    }
    
    
    //-----------------------------------GESTIONAR CLIENTE---------------------------------------------------     
    
    //OK
    private void registrarCliente(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //clienteNegocio clienteNegocio = new clienteNegocio();
            //String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario + 
                    "\n\n", ComandosAyuda.AYUDA_REGISTRARCLIENTE);
            return;
        }
        try {
            // Sino, ejecutar el comando
            NCliente cliente = new NCliente();
            analex.Avanzar();
            
            // Atributos                                                                
            String ci = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String nombres = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String paterno = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String materno = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String fechaNac = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String telefono = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String email = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String direccion = Utils.quitarComillas(analex.Preanalisis().getToStr());
            
            cliente.registrarCliente(ci, 
                                    nombres, 
                                    paterno, 
                                    materno, 
                                    Utils.ddMMyyyyToyyyyMMddDate(fechaNac), 
                                    telefono,
                                    email, 
                                    direccion);

            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Registrar Cliente", Constante.IngresoPositivoR + 
                    "\n\n" + Utils.dibujarTablawithHTMLwithoutOpciones(cliente.mostrarClientes()));

        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Registrar Cliente", Constante.IngresoErrorR + 
                    "\n" + "Mensaje enviado: " + analex.M.texto);
            e.printStackTrace();
            
        }
    }

    //OK
    private void modificarCliente(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_MODIFICARCLIENTE);
            return;
        }

        // Sino, ejecutar el comando
        NCliente cliente = new NCliente();
        analex.Avanzar();

        //sacar el id de 
        int id = analex.Preanalisis().getAtributo();
        if (cliente.existeCliente(id)) {
            try {
                //analex.Preanalisis().setAtributo(Math.round(analex.Preanalisis().getAtributo()));
                //int id= clienteNegocio.obtenerId(email);
                DefaultTableModel clientet = cliente.mostrarClienteId(id);

                // Revisar los GuionBajo
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String ci = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(clientet.getValueAt(0, 1));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String nombres = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(clientet.getValueAt(0, 2));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String paterno = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(clientet.getValueAt(0, 3));                                
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String materno = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(clientet.getValueAt(0, 4));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String fechaNac = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.ddMMyyyyToyyyyMMddDate(Utils.quitarComillas(analex.Preanalisis().getToStr())) //cambiar formato fecha si es nueva
                        : String.valueOf(clientet.getValueAt(0, 5));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String telefono = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(clientet.getValueAt(0, 6));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String email = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(clientet.getValueAt(0, 7));
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String direccion = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(clientet.getValueAt(0, 8));                                
                
                analex.Avanzar(); // por la ,
                analex.Avanzar(); // por la /"
                String estado = (analex.Preanalisis().getNombre() != Token.GB) // si hay guion bajo "_"
                        ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                        : String.valueOf(clientet.getValueAt(0, 9));
                                                

                cliente.modificarCliente(id, 
                                        ci, 
                                        nombres, 
                                        paterno, 
                                        materno, 
                                        fechaNac, 
                                        telefono, 
                                        email, 
                                        direccion, 
                                        estado);
                
                //clienteNegocio.modificarCliente(id,nombre,apellido,telefono, direccion, email);
                // ClienteSMTP.sendMail(correoDest, "Modificar Cliente", Constante.IngresoPositivoM);
                mimeMail mimemailer = new mimeMail();
                mimemailer.sendHtmlEmail(correoDest, "Modificar Cliente", Constante.IngresoPositivoM + 
                        "\n\n" + Utils.dibujarTablawithHTMLwithoutOpciones(cliente.mostrarClientes()));
                
            } catch (Exception e) {
                ClienteSMTP.sendMail(correoDest, "Modificar Cliente", Constante.IngresoErrorM + 
                        "\n" + "Mensaje enviado: " + analex.M.texto);
            }

        } else {
            String msg = ComandosAyuda.AYUDA_MODIFICARCLIENTE;
            ClienteSMTP.sendMail(correoDest, Constante.AsuntoError, "El Cliente no se encuentra registrado!\n\n" 
                    + "Mensaje enviado: " + analex.M.texto + msg);
        }

    }

    //OK
    private void eliminarCliente(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_ELIMINARCLIENTE);
            return;
        }
        try {
            // Sino, ejecutar el comando
            NCliente cliente = new NCliente();
            analex.Avanzar();  // avanzar del (
            //String email = Herramientas.quitarComillas(analex.Preanalisis().getToStr());
            int id = analex.Preanalisis().getAtributo();

            if (id != 0 && cliente.existeCliente(id)) {
                cliente.eliminarCliente(id);
                //ClienteSMTP.sendMail(correoDest, "Eliminar CLiente", Constante.IngresoPositivoE); 
                mimeMail mimemailer = new mimeMail();
                mimemailer.sendHtmlEmail(correoDest, "Eliminar Cliente", Constante.IngresoPositivoE + 
                        "\n\n" + Utils.dibujarTablawithHTMLwithoutOpciones(cliente.mostrarClientes()));

            } else {
                ClienteSMTP.sendMail(correoDest, Constante.AsuntoError, Constante.NoExisteCliente + 
                        "\n" + "Mensaje enviado: " + analex.M.texto);
            }
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Eliminar Cliente", Constante.IngresoErrorE + 
                    "\n" + "Mensaje enviado: " + analex.M.texto);
        }

    }

    //OK
    private void obtenerCliente(Analex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_OBTENERCLIENTE);
            return;
        }

        // Sino, ejecutar el comando
        NCliente cliente = new NCliente();
        analex.Avanzar();
        //String email = Herramientas.quitarComillas(analex.Preanalisis().getToStr());        
        int id = analex.Preanalisis().getAtributo();
        //int id= clienteNegocio.obtenerId(email);
        
        if (id != 0 && cliente.existeCliente(id)) {
            //String mensaje = Herramientas.dibujarTabla(clienteNegocio.obtenerCliente(id));  
            String mensaje = Utils.dibujarTablawithHTMLwithoutOpciones(cliente.mostrarClienteId(id));
            mimeMail mailer = new mimeMail();
            try {
                mailer.sendHtmlEmail(destinatario, "Obtener datos", "<h1>Obtener Cliente</h1> \n\n" + mensaje);
            } catch (Exception e) {
                ClienteSMTP.sendMail(destinatario, Constante.AsuntoError, "No se pudo obtener el Cliente" + 
                        "\n" + "Mensaje enviado: " + analex.M.texto);
            }
            // ClienteSMTP.sendMail(destinatario, "Obtener Cliente\n\n", mensaje);          
        } else {
            ClienteSMTP.sendMail(destinatario, Constante.AsuntoError, "No se pudo obtener el Cliente" + 
                    "\n" + "Mensaje enviado: " + analex.M.texto);
        }

    }

    //OK
    private void mostrarClientes(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_MOSTRARCLIENTES);
            return;
        }
        // Sino, ejecutar el comando
        NCliente cliente = new NCliente();

        try {
            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" 
                    + Utils.dibujarTablawithHTMLwithoutOpciones(cliente.mostrarClientes()));
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Mostrar Clientes", 
                    "error durante la obtencion de la tabla, verifique con el comando HELP");

        }
    }
    
    
    //-----------------------------------REALIZAR VENTA---------------------------------------------------     
    
    //
    private void realizarVenta(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //clienteNegocio clienteNegocio = new clienteNegocio();
            //String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario + 
                    "\n\n", ComandosAyuda.AYUDA_REGISTRARVENTA);
            return;
        }
        try {
            // Sino, ejecutar el comando
            NVenta venta = new NVenta();
            analex.Avanzar();
            
            // Atributos                                                                
            String total = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String estado = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String numcomprobante = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            int idCliente = analex.Preanalisis().getAtributo();
            analex.Avanzar();
            analex.Avanzar();
            int idUsuario = analex.Preanalisis().getAtributo();
            
            analex.Avanzar();
            analex.Avanzar();
            String detalleVenta = Utils.quitarComillas(analex.Preanalisis().getToStr());                                  
            
            venta.registrarVenta(total, 
                                estado, 
                                numcomprobante, 
                                idCliente, 
                                idUsuario, 
                                detalleVenta);

            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Registrar Venta", Constante.IngresoPositivoR + 
                    "\n\n" + Utils.dibujarTablawithHTMLwithoutOpciones(venta.mostrarVentas()));

        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Registrar Venta", Constante.IngresoErrorR + 
                    "\n" + "Mensaje enviado: " + analex.M.texto);
            
        }
    }
    
    //
    private void obtenerVentaYDetalles(Analex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_OBTENERVENTAYDETALLES);
            return;
        }

        // Sino, ejecutar el comando
        NVenta venta = new NVenta();
        analex.Avanzar();
        //String email = Herramientas.quitarComillas(analex.Preanalisis().getToStr());        
        int id = analex.Preanalisis().getAtributo();
        //int id= clienteNegocio.obtenerId(email);
        
        if (id != 0 && venta.existeVenta(id)) {
            //String mensaje = Herramientas.dibujarTabla(clienteNegocio.obtenerCliente(id));  
                        
            String mensaje = Utils.dibujarTablawithHTMLVenta(venta.mostrarVentaId(id), 
                    venta.mostrarDetallesVentaId(id));
                        
            mimeMail mailer = new mimeMail();
            try {
                mailer.sendHtmlEmail(destinatario, "Obtener datos", 
                        "<h1>Obtener Venta y Detalles</h1> \n\n" + mensaje);
            } catch (Exception e) {
                ClienteSMTP.sendMail(destinatario, Constante.AsuntoError, "No se pudo obtener la Venta" + 
                        "\n" + "Mensaje enviado: " + analex.M.texto);
            }
            // ClienteSMTP.sendMail(destinatario, "Obtener Cliente\n\n", mensaje);          
        } else {
            ClienteSMTP.sendMail(destinatario, Constante.AsuntoError, "No se pudo obtener la Venta" + 
                    "\n" + "Mensaje enviado: " + analex.M.texto);
        }

    }
    
    //
    private void mostrarVentas(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_MOSTRARVENTAS);
            return;
        }
        // Sino, ejecutar el comando
        NVenta venta = new NVenta();

        try {
            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Mostrar Ventas", "Listado de Ventas\n" 
                    + Utils.dibujarTablawithHTMLwithoutOpciones(venta.mostrarVentas()));
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Mostrar Ventas", 
                    "error durante la obtencion de la tabla, verifique con el comando HELP");

        }
    }
    
    
    //-----------------------------------REGISTRAR INGRESO---------------------------------------------------     
    
    //
    private void registrarIngreso(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //clienteNegocio clienteNegocio = new clienteNegocio();
            //String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario + 
                    "\n\n", ComandosAyuda.AYUDA_REGISTRARINGRESO);
            return;
        }
        try {
            // Sino, ejecutar el comando
            NIngreso ingreso = new NIngreso();
            analex.Avanzar();
            
            // Atributos                                                                
            String totalCompra = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String tipoComprobante = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String numComprobante = Utils.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String estado = Utils.quitarComillas(analex.Preanalisis().getToStr());            
            analex.Avanzar();
            analex.Avanzar();
            int idProveedor = analex.Preanalisis().getAtributo();
            analex.Avanzar();
            analex.Avanzar();
            int idUsuario = analex.Preanalisis().getAtributo();
            
            analex.Avanzar();
            analex.Avanzar();
            String detalleIngreso = Utils.quitarComillas(analex.Preanalisis().getToStr());
            
            
            /*
            ClienteSMTP.sendMail(correoDest, "Error de Comando", "El comando:  " + 
                    analex.M.texto + 
                    "\n es incorrecto!, para consultar los comandos disponibles envie la palabra HELP en el asunto del correo");
            */
            
            ingreso.registrarIngreso(totalCompra, tipoComprobante, numComprobante, 
                    estado, idProveedor, idUsuario, detalleIngreso);

            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Registrar Ingreso", Constante.IngresoPositivoR + 
                    "\n\n" + Utils.dibujarTablawithHTMLwithoutOpciones(ingreso.mostrarIngresos()));

        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Registrar Ingreso", Constante.IngresoErrorR + 
                    "\n" + "Mensaje enviado: " + analex.M.texto);
            
        }
    }
    
    //
    private void obtenerIngresoYDetalles(Analex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_OBTENERINGRESOYDETALLES);
            return;
        }

        // Sino, ejecutar el comando
        NIngreso ingreso = new NIngreso();
        analex.Avanzar();
        //String email = Herramientas.quitarComillas(analex.Preanalisis().getToStr());        
        int id = analex.Preanalisis().getAtributo();
        //int id= clienteNegocio.obtenerId(email);
        
        if (id != 0 && ingreso.existeVenta(id)) {
            //String mensaje = Herramientas.dibujarTabla(clienteNegocio.obtenerCliente(id));  
            
            
            String mensaje = Utils.dibujarTablawithHTMLIngreso(ingreso.mostrarIngresoId(id), 
                    ingreso.mostrarDetallesIngresoId(id));
            
            
            mimeMail mailer = new mimeMail();
            try {
                mailer.sendHtmlEmail(destinatario, "Obtener datos", 
                        "<h1>Obtener Ingreso y Detalles</h1> \n\n" + mensaje);
            } catch (Exception e) {
                ClienteSMTP.sendMail(destinatario, Constante.AsuntoError, "No se pudo obtener el Ingreso" + 
                        "\n" + "Mensaje enviado: " + analex.M.texto);
            }
            // ClienteSMTP.sendMail(destinatario, "Obtener Cliente\n\n", mensaje);          
        } else {
            ClienteSMTP.sendMail(destinatario, Constante.AsuntoError, "No se pudo obtener el Ingreso" + 
                    "\n" + "Mensaje enviado: " + analex.M.texto);
        }

    }
    
    //
    private void mostrarIngresos(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_MOSTRARINGRESOS);
            return;
        }
        // Sino, ejecutar el comando
        NIngreso ingreso = new NIngreso();

        try {
            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Mostrar Ingresos", "Listado de Ingresos\n" 
                    + Utils.dibujarTablawithHTMLwithoutOpciones(ingreso.mostrarIngresos()));
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Mostrar Ingresos", 
                    "error durante la obtencion de la tabla, verifique con el comando HELP");

        }
    }
    
    
    //-----------------------------------REPORTES---------------------------------------------------     
        
    private void reporteProductos(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_REPORTEPRODUCTOS);
            return;
        }
        
        // Sino, ejecutar el comando
        NReporte reporte = new NReporte();
        
        try {                                                            
            //Generar el PDF de los productos
            reporte.reporteProductos();
                        
            mimeMail mimemailer = new mimeMail();
            mimemailer.sendPDFEmail(correoDest, "Reporte Productos", "Listado en PDF de los Producto");
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Reporte Productos", 
                    "Error durante la obtencion del PDF, porfavor vuelva a intentarlo");
        }
    }
    
    private void reporteVentas(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_REPORTEVENTAS);
            return;
        }        

        try {            
            NReporte reporte = new NReporte();
            analex.Avanzar();
            
            //Si envio parametros
            if (!analex.Preanalisis().getToStr().equals("EOF")) {
                // Atributos                                                                
                String fecha1 = Utils.quitarComillas(analex.Preanalisis().getToStr());
                analex.Avanzar();
                analex.Avanzar();
                String fecha2 = Utils.quitarComillas(analex.Preanalisis().getToStr());
                
                //Generar el PDF de las ventas con un rango de fecha
                reporte.reporteVentasRangoFechas(fecha1, fecha2);
                
            } else {//Si no envio parametros
                //Generar el PDF de las ventas sin rango de fecha
                reporte.reporteVentas();      
            }
            
            mimeMail mimemailer = new mimeMail();
            mimemailer.sendPDFEmail(correoDest, "Reporte Ventas", "Listado en PDF de las Ventas");
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Reporte Ventas", 
                    "Error durante la obtencion del PDF, porfavor vuelva a intentarlo");
        }
    }
    
    private void reporteIngresos(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_REPORTEINGRESOS);
            return;
        }        

        try {            
            NReporte reporte = new NReporte();
            analex.Avanzar();
            
            //Si envio parametros
            if (!analex.Preanalisis().getToStr().equals("EOF")) {
                // Atributos                                                                
                String fecha1 = Utils.quitarComillas(analex.Preanalisis().getToStr());
                analex.Avanzar();
                analex.Avanzar();
                String fecha2 = Utils.quitarComillas(analex.Preanalisis().getToStr());
                
                //Generar el PDF de las ventas con un rango de fecha
                reporte.reporteIngresosRangoFechas(fecha1, fecha2);
                
            } else {//Si no envio parametros
                //Generar el PDF de las ventas sin rango de fecha
                reporte.reporteIngresos();      
            }
            
            mimeMail mimemailer = new mimeMail();
            mimemailer.sendPDFEmail(correoDest, "Reporte Ingresos", "Listado en PDF de los Ingresos");
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Reporte Ingresos", 
                    "Error durante la obtencion del PDF, porfavor vuelva a intentarlo");
        }
    }
    
    
    //-----------------------------------ESTADISTICAS------------------------------------------------   
    
    
    private void estadisticasProductoMasVendidos(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_ESTADISTICASPRODUCTOSMASVENDIDOS);
            return;
        }
                               
        try {         
            NEstadistica estadistica = new NEstadistica();
            analex.Avanzar();
            
            String html = "";
            
            //Si envio parametros
            if (!analex.Preanalisis().getToStr().equals("EOF")) {//Envio parametros
                // Atributos                                                                
                String parametro1 = Utils.quitarComillas(analex.Preanalisis().getToStr());
                analex.Avanzar();
                analex.Avanzar();
                
                if (analex.Preanalisis().getToStr().equals("EOF")) {//Solo es un parametro (End Of the Line)
                    //Si el parametro es un fecha AAAA-mm-dd
                    if (parametro1.contains("-")) {
                        
                        html = estadistica.estadisticasProductosMasVendidosDia(parametro1);
                    } else {//Entonces es un Annio
                        
                        //html = estadistica.estadisticasProductosMasVendidosMes(parametro1);
                    }
                } else {//Son 2 parametros
                    String parametro2 = Utils.quitarComillas(analex.Preanalisis().getToStr());
                    //Enviamos las 2 fechas
                    html = estadistica.estadisticasProductosMasVendidosRangoFechas(parametro1, parametro2);                                                            
                }                                                                
                
            } else {//Si no envio parametros                
                html = estadistica.estadisticasProductosMasVendidos();
            }
                        
            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Productos mas vendidos", html);
            
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Reporte Productos", 
                    "Error durante la obtencion de la Grafica, verifique con el comando HELP");
        }
    }
    
    
    private void estadisticasClientesFieles(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_ESTADISTICASCLIENTESFIELES);
            return;
        }        

        try {            
            NEstadistica estadistica = new NEstadistica();
            analex.Avanzar();
            
            String html = estadistica.estadisticasClientesFieles();
            
            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Clientes mas fieles", html);
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Estadisticas Clientes Mas Fieles",
                    "Error durante la obtencion de la Grafica, porfavor vuelva a intentarlo");
        }
    }
    
    
    private void estadisticasProveedoresMasSolicitados(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_ESTADISTICASPROVEEDORESMASSOLICITADOS);
            return;
        }        

        try {            
            NEstadistica estadistica = new NEstadistica();
            analex.Avanzar();
            
            String html = estadistica.estadisticasProveedoresMasSolicitados();
            
            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Proveedores mas solicitados", html);
        } catch (Exception e) {
            ClienteSMTP.sendMail(correoDest, "Estadisticas Proveedores Mas Solicitados",
                    "Error durante la obtencion de la Grafica, porfavor vuelva a intentarlo");
        }
    }
    
    
    private void estadisticasFinancieras(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, Constante.msgAyudaPropietario, 
                    ComandosAyuda.AYUDA_ESTADISTICASFINANCIERAS);
            return;
        }        

        try {            
            NEstadistica estadistica = new NEstadistica();
            analex.Avanzar();
            
            String anio = Utils.quitarComillas(analex.Preanalisis().getToStr());
            
            String html = estadistica.estadisticasFinancieras(anio);
            
            mimeMail mimemailer = new mimeMail();
            mimemailer.sendHtmlEmail(correoDest, "Estadisticas Financieras", html);
        } catch (Exception e) {
            e.printStackTrace();
            ClienteSMTP.sendMail(correoDest, "Estadisticas Financieras",
                    "Error durante la obtencion de la Grafica, porfavor vuelva a intentarlo");
        }
    }
    
    
}//end class
