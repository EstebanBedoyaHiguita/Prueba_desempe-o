package Model;

import Data_base.CRUD_COMPRA;
import Data_base.ConfiDB;
import Entity.Cliente;
import Entity.Compra;
import Entity.Producto;
import Entity.Tienda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Compra_Model implements CRUD_COMPRA {
    @Override
    public Object insert(Object object) {

        Connection objConnection = ConfiDB.openConnection();


        Compra objCompra = (Compra) object;



        try{

            String sql = "INSERT INTO compra (id_cliente,id_producto,fecha_compra,cantidad) VALUE(?,?,?,?)";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //Asignar valores
            objPrepare.setInt(1,objCompra.getId_cliente());
            objPrepare.setInt(2,objCompra.getId_producto());
            objPrepare.setString(3,objCompra.getFecha_compra());
            objPrepare.setInt(4,objCompra.getCantidad());



            //Ejecutar el query
            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objCompra.setId_compra(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Compra Exitosa");



        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error realizar compra"+ e.getMessage());
        }
        ConfiDB.closeConnection();

        return objCompra;
    }

    @Override
    public boolean update(Object object) {

        Connection objConnection = ConfiDB.openConnection();

        Compra objCompra  = (Compra)  object;
        boolean isEdit = false;

        try{
            String sql = "UPDATE compra SET id_cliente = ?,id_producto = ?,fecha_compra = ?,cantidad=? where id_compra = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);

            objPrepare.setInt(1,objCompra.getId_cliente());
            objPrepare.setInt(2,objCompra.getId_producto());
            objPrepare.setString(3,objCompra.getFecha_compra());
            objPrepare.setInt(4,objCompra.getCantidad());
            objPrepare.setInt(5,objCompra.getId_compra());



            int totalAffectect = objPrepare.executeUpdate();

            if(totalAffectect > 0){
                isEdit = true;
                JOptionPane.showMessageDialog(null,"Compra editada correctamente");

            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            ConfiDB.closeConnection();
        }
        return isEdit;
    }

    @Override
    public boolean delete(Object object) {
        Connection objConnection = ConfiDB.openConnection();

        Compra objReservacion = (Compra) object;

        boolean isDelete = false;

        try{
            String sql = "DELETE FROM compra WHERE id_compra = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);
            objPrepare.setInt(1,objReservacion.getId_compra());
            int totalAffect = objPrepare.executeUpdate();

            if(totalAffect > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Compra eliminada exitosamente");
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return isDelete;
    }

    @Override
    public List<Object> findAll() {
        Connection objConection = ConfiDB.openConnection();

        List<Object> listaCompras = new ArrayList<>();

        try{
            String sql = "SELECT *\n" +
                    "FROM compra\n" +
                    "INNER JOIN producto ON producto.id_producto = compra.id_producto\n" +
                    "INNER JOIN tienda ON tienda.id_tienda = producto.id_tienda\n" +
                    "INNER JOIN cliente ON cliente.id_cliente = compra.id_cliente;";
            PreparedStatement objPrepare = objConection.prepareStatement(sql);

            ResultSet objResult = (ResultSet)  objPrepare.executeQuery();
            while (objResult.next()){
                Compra objCompra = new Compra();

                objCompra.setId_compra(objResult.getInt("id_compra"));
                objCompra.setId_cliente(objResult.getInt("id_cliente"));
                objCompra.setId_producto(objResult.getInt("id_producto"));
                objCompra.setFecha_compra(objResult.getString("fecha_compra"));
                objCompra.setCantidad(objResult.getInt("cantidad"));



                Cliente objCliente = new Cliente();

                objCliente.setId_cliente(objResult.getInt("id_cliente"));
                objCliente.setNombre_cliente(objResult.getString("nombre_cliente"));
                objCliente.setApellidos(objResult.getString("apellidos"));
                objCliente.setEmail(objResult.getString("email"));

                objCompra.setObjCliente(objCliente);


                Producto objProducto = new Producto();

                objProducto.setId_producto(objResult.getInt("id_producto"));
                objProducto.setNombre_producto(objResult.getString("nombre_producto"));
                objProducto.setPrecio(objResult.getDouble("precio"));
                objProducto.setId_tienda(objResult.getInt("id_tienda"));
                objProducto.setStock(objResult.getInt("Stock"));

                objCompra.setObjProducto(objProducto);

                Tienda objTienda = new Tienda();

                objTienda.setId_tienda(objResult.getInt("id_tienda"));
                objTienda.setNombre_tienda(objResult.getString("nombre_tienda"));
                objTienda.setUbicacion(objResult.getString("ubicacion"));

                objProducto.setObjTienda(objTienda);




                listaCompras.add(objCompra);

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la carga\n" + e.getMessage());
        }
        ConfiDB.closeConnection();
        return listaCompras;
    }

    @Override
    public Object findById(int id) {

        Connection objConection = ConfiDB.openConnection();

        Compra objCompra = new Compra();

        try{
            String sql = "SELECT * FROM compra WHERE id_compra = ?;";

            PreparedStatement objPrepare = objConection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,id);
            objPrepare.executeQuery();
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            boolean resultadoId = objResult.next();

            if(!resultadoId){
                JOptionPane.showMessageDialog(null,"Id no encontrado");
                return null;
            }else {
                objCompra.setId_compra(objResult.getInt("id_compra"));
                objCompra.setId_cliente(objResult.getInt("id_cliente"));
                objCompra.setId_producto(objResult.getInt("id_producto"));
                objCompra.setFecha_compra(objResult.getString("fecha_compra"));
                objCompra.setCantidad(objResult.getInt("cantidad"));

                Cliente_Model objCliente_Model = new Cliente_Model();
                objCompra.setObjCliente((Cliente) objCliente_Model.findById(objCompra.getId_cliente()));

                Producto_Model objProducto_Model = new Producto_Model();
                objCompra.setObjProducto((Producto) objProducto_Model.findById(objCompra.getId_producto()) );



            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data error");
            System.out.println(e.getMessage());
        }
        ConfiDB.closeConnection();

        return objCompra;
    }

    @Override
    public List<Object> findDate(String fecha_reservacion) {
        Connection objConnection = ConfiDB.openConnection();

        List<Object> listCompras = new ArrayList<>();

        try{
            String sql = "SELECT * FROM compra WHERE fecha_compra = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,fecha_reservacion);
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()){
                Compra objCompra = new Compra();

                objCompra.setId_compra(objResult.getInt("id_compra"));
                objCompra.setId_cliente(objResult.getInt("id_cliente"));
                objCompra.setId_producto(objResult.getInt("id_producto"));
                objCompra.setFecha_compra(objResult.getString("fecha_compra"));
                objCompra.setCantidad(objResult.getInt("cantidad"));

                listCompras.add(objCompra);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error al obtener Reservaciones");
        }
        ConfiDB.closeConnection();
        return listCompras;
    }
}
