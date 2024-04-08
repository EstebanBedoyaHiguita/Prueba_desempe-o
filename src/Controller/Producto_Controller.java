package Controller;

import Entity.Producto;
import Entity.Tienda;
import Model.Producto_Model;
import Model.Tienda_Model;

import javax.swing.*;
import java.util.List;

public class Producto_Controller {
    Producto_Model objProducto_Model = new Producto_Model();

    Tienda_Model objTienda_Model = new Tienda_Model();

    public void create (){
        Producto objProducto = new Producto();

        List<Object> listTiendas = objTienda_Model.findAll();

        Tienda[] listTienda = new Tienda[listTiendas.size()];
        int id_tienda =0;
        int index = 0;

        for(Object iterados : listTiendas){
            Tienda obj = (Tienda) iterados;
            listTienda[index] = obj;
            index++;
        }

        Tienda tiendaSelect = (Tienda) JOptionPane.showInputDialog(null,
                "select a Tienda:\n","Choosing Tienda",
                JOptionPane.QUESTION_MESSAGE,null,listTienda,listTienda[0]);

        if (tiendaSelect != null) {

            id_tienda = tiendaSelect.getId_tienda();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar una tienda.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto"));
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad"));

        objProducto.setId_tienda(id_tienda);
        objProducto.setNombre_producto(nombre);
        objProducto.setPrecio(precio);
        objProducto.setStock(stock);

        objProducto.setObjTienda(tiendaSelect);






        objProducto = (Producto) this.objProducto_Model.insert(objProducto);
        JOptionPane.showMessageDialog(null,objProducto.toString());
    }

    public void  edit (){
        List<Object> listProductos = objProducto_Model.findAll();


        Producto[] listproducto = new Producto[listProductos.size()];
        int id_producto =0;
        int index = 0;
        for(Object iterados : listProductos){
            Producto obj = (Producto) iterados;
            listproducto[index] = obj;
            index++;
        }

        Producto ProductoSelect = (Producto) JOptionPane.showInputDialog(null,
                "select a producto:\n","Choosing producto",
                JOptionPane.QUESTION_MESSAGE,null,listproducto,listproducto[0]);

        if(ProductoSelect != null){
            id_producto = ProductoSelect.getId_producto();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        Tienda_Model objTienda_model = new Tienda_Model();
        List<Object> listTiendas = objTienda_model.findAll();

        Tienda[] listTienda = new Tienda[listTiendas.size()];
        int id_tienda = 0;
        int i = 0;
        for(Object iterados : listTiendas){
            Tienda obj = (Tienda) iterados;
            listTienda[i] = obj;
            i++;
        }

        Tienda tiendaSelect = (Tienda) JOptionPane.showInputDialog(null,
                "select a tienda:\n","Choosing tienda",
                JOptionPane.QUESTION_MESSAGE,null,listTienda,listTienda[0]);

        if (tiendaSelect != null) {

            id_tienda= tiendaSelect.getId_tienda();

        } else {

            JOptionPane.showMessageDialog(null, "Debe seleccionar una tienda", "Error", JOptionPane.ERROR_MESSAGE);
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del producto",ProductoSelect.getNombre_producto());
        double precio = Double.parseDouble(
                JOptionPane.showInputDialog("Ingrese el nuevo precio del producto",ProductoSelect.getPrecio())
        );
        int cantidad = Integer.parseInt(
                JOptionPane.showInputDialog("Ingrese la nueva cantidad",ProductoSelect.getStock())
        ) ;

        ProductoSelect.setId_tienda(id_tienda);

        ProductoSelect.setId_producto(id_producto);
        ProductoSelect.setNombre_producto(nombre);
        ProductoSelect.setStock(cantidad);


        boolean editado = this.objProducto_Model.update (ProductoSelect);

        if(editado){
            JOptionPane.showMessageDialog(null,"Producto editado correctamente");
        }else {
            JOptionPane.showMessageDialog(null, "Error al editar el producto");
        }



    }

    public void  getAll(){
        String list = "Lista de productos \n";

        for  (Object obj : this.objProducto_Model.findAll()){
            Producto objProducto = (Producto) obj;
            list += objProducto.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }

    public void delete(){
        List<Object> listProductos = objProducto_Model.findAll();

        Producto[] listproducto = new Producto[listProductos.size()];

        int index = 0;
        for(Object iterados : listProductos){
            Producto obj = (Producto) iterados;
            listproducto[index] = obj;
            index++;
        }

        Producto ProductoSelect = (Producto) JOptionPane.showInputDialog(null,
                "select a Producto:\n","Choosing producto",
                JOptionPane.QUESTION_MESSAGE,null,listproducto,listproducto[0]);

        this.objProducto_Model.delete(ProductoSelect);
    }
    public void buscarId(){
        int id_producto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Id que desea buscar"));
        Producto objProducto = (Producto) this.objProducto_Model.findById(id_producto);

        if(objProducto == null){
            return;
        }else {
            JOptionPane.showMessageDialog(null,objProducto);
        }

    }

}
