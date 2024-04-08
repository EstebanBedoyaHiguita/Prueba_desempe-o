package Controller;

import Entity.Cliente;
import Entity.Compra;
import Entity.Producto;
import Entity.Tienda;
import Model.Cliente_Model;
import Model.Compra_Model;
import Model.Producto_Model;
import Model.Tienda_Model;

import javax.swing.*;
import java.util.List;

public class Compra_controller  {

    Compra_Model objCompra_Model = new Compra_Model();
    Cliente_Model objCliente_Model = new Cliente_Model();
    Producto_Model objProducto_Model = new Producto_Model();
    Tienda_Model objTienda_Model = new Tienda_Model();


    public void create (){
        Compra objCompra = new Compra();

        List<Object> listProductos = objProducto_Model.findAll();

        Producto[] listProducto = new Producto[listProductos.size()];
        int id_producto=0;
        int index = 0;
        int id_cliente =0;
        for(Object iterados : listProductos){
            Producto obj = (Producto) iterados;
            listProducto[index] = obj;
            index++;
        }

        Producto productoSelect = (Producto) JOptionPane.showInputDialog(null,
                "select a Producto:\n","Choosing Producto",
                JOptionPane.QUESTION_MESSAGE,null,listProducto,listProducto[0]);

        if (productoSelect != null) {

            id_producto = productoSelect.getId_producto();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un Producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        Cliente_Model objCliente_Model = new Cliente_Model();
        List<Object> listClientes = objCliente_Model.findAll();

        Cliente[] listCliente = new Cliente[listClientes.size()];

        int i = 0;
        for(Object iterados : listClientes){
            Cliente obj = (Cliente) iterados;
            listCliente[i] = obj;
            i++;
        }

        Cliente ClienteSelect = (Cliente) JOptionPane.showInputDialog(null,
                "select a cliente:\n","Choosing cliente",
                JOptionPane.QUESTION_MESSAGE,null,listCliente,listCliente[0]);

        if (ClienteSelect != null) {

            id_cliente = ClienteSelect.getId_cliente();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        String fecha = JOptionPane.showInputDialog("Ingrese la fecha que desea realizar la compra:"+"{aaaa/mm/dd}");
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad que desea comprar")) ;

//        validacion Stock
        int Stock = productoSelect.getStock();
        if(cantidad <= Stock ){



            objCompra.setId_producto(id_producto);
            objCompra.setId_cliente(id_cliente);
            objCompra.setFecha_compra(fecha);
            objCompra.setCantidad(cantidad);

            int nuevoStock = Stock - cantidad;
            productoSelect.setStock(nuevoStock);

        objCompra.setObjCliente(ClienteSelect);
        objCompra.setObjProducto(productoSelect);

            double total = productoSelect.getPrecio() * cantidad;
            double iva = total * 19/100;
            double totalCompra = total + iva;



        objCompra = (Compra) this.objCompra_Model.insert(objCompra);
        JOptionPane.showMessageDialog(null,objCompra.toString()+"Total:"+total);


        }else{
            JOptionPane.showMessageDialog(null,"La cantidad ingresada es mayor a la disponible");
        }
    }

    public void  edit (){
        List<Object> listCompras = objCompra_Model.findAll();


        Compra[] listCompra = new Compra[listCompras.size()];
        int id_compra =0;
        int index = 0;
        for(Object iterados : listCompras){
            Compra obj = (Compra) iterados;
            listCompra[index] = obj;
            index++;
        }

        Compra CompraSelect = (Compra) JOptionPane.showInputDialog(null,
                "select a compra:\n","Choosing compra",
                JOptionPane.QUESTION_MESSAGE,null,listCompra,listCompra[0]);

        if(CompraSelect != null){
            id_compra= CompraSelect.getId_compra();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar una compra.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        Cliente_Model objCliente = new Cliente_Model();
        List<Object> listClientes = objCliente_Model.findAll();

        Cliente[] listcliente = new Cliente[listClientes.size()];
        int id_cliente = 0;
        int i = 0;
        for(Object iterados : listClientes){
            Cliente obj = (Cliente) iterados;
            listcliente[i] = obj;
            i++;
        }

        Cliente clienteSelect = (Cliente) JOptionPane.showInputDialog(null,
                "select a cliente:\n","Choosing cliente",
                JOptionPane.QUESTION_MESSAGE,null,listcliente,listcliente[0]);

        if (clienteSelect != null) {

            id_cliente = clienteSelect.getId_cliente();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Producto_Model objProducto_Model = new Producto_Model();
        List<Object> listProductos = objProducto_Model.findAll();

        Producto[] listProducto = new Producto[listProductos.size()];
        int id_producto = 0;
        int in = 0;
        for(Object iterados : listProductos){
            Producto obj = (Producto) iterados;
            listProducto[in] = obj;
            in++;
        }

        Producto ProductoSelect = (Producto) JOptionPane.showInputDialog(null,
                "select a Producto:\n","Choosing producto",
                JOptionPane.QUESTION_MESSAGE,null,listProducto,listProducto[0]);

        if (ProductoSelect != null) {

            id_producto = ProductoSelect.getId_producto();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        String fecha_compra = JOptionPane.showInputDialog("Ingrese la fecha que desea grabar la compra{aaaa,dd,mm}",CompraSelect.getFecha_compra());
        int cantidad =Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad",CompraSelect.getCantidad()));

        CompraSelect.setId_cliente(id_cliente);
        CompraSelect.setId_producto(id_producto);
        CompraSelect.setFecha_compra(fecha_compra);
        CompraSelect.setCantidad(cantidad);


        boolean editado = this.objCompra_Model.update (CompraSelect);

        if(editado){
            JOptionPane.showMessageDialog(null,"Compra editada correctamente");
        }else {
            JOptionPane.showMessageDialog(null, "Error al editar el Compra");
        }



    }

    public void  getAll(){
        String list = "Lista de Compras \n";

        for  (Object obj : this.objCompra_Model.findAll()){
            Compra objCompra = (Compra) obj;
            list += objCompra.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }

    public void delete(){
        List<Object> listCompras = objCompra_Model.findAll();

        Compra[] listCompra = new Compra[listCompras.size()];

        int index = 0;
        for(Object iterados : listCompras){
            Compra obj = (Compra) iterados;
            listCompra[index] = obj;
            index++;
        }

        Compra CompraSelect = (Compra) JOptionPane.showInputDialog(null,
                "select a compra:\n","Choosing compra",
                JOptionPane.QUESTION_MESSAGE,null,listCompra,listCompra[0]);

        this.objCompra_Model.delete(CompraSelect);
    }

    public void buscarDate(){
        String fecha_compra = JOptionPane.showInputDialog("Ingrese la fecha de la compra"+"{aaaa-mm-dd}");
        List<Object> listCompras = objCompra_Model.findDate(fecha_compra);

        String srtlistaCompras = "";

        if(listCompras.isEmpty()){
            JOptionPane.showMessageDialog(null,"Lista vacia");
        }else {
            for (Object obj:listCompras){
                Compra objCompra = (Compra) obj;
                srtlistaCompras += objCompra.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null,srtlistaCompras);
        }
    }

    public void buscarId(){
        int id_compra = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Id que desea buscar"));
        Compra objCompra= (Compra) this.objCompra_Model.findById(id_compra);

        if(objCompra == null){
            return;
        }else {
            JOptionPane.showMessageDialog(null,objCompra);
        }

    }

}
