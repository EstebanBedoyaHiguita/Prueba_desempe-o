package Model;

import Data_base.CRUD_PRODUCTO;
import Data_base.ConfiDB;
import Entity.Producto;
import Entity.Tienda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Producto_Model  implements CRUD_PRODUCTO {


    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfiDB.openConnection();

        Producto objProducto = (Producto) object;



        try{

            String sql = "INSERT INTO producto (nombre_producto,precio,id_tienda,Stock) VALUE(?,?,?,?)";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);



            objPrepare.setString(1,objProducto.getNombre_producto());
            objPrepare.setDouble(2,objProducto.getPrecio());
            objPrepare.setInt(3,objProducto.getId_tienda());
            objPrepare.setInt(4,objProducto.getStock());





            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objProducto.setId_producto(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Producto creado correctamente");



        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al agregar Producto"+ e.getMessage());
        }
        ConfiDB.closeConnection();

        return objProducto;
    }

    @Override
    public boolean update(Object object) {

        Connection objConnection = ConfiDB.openConnection();

        Producto objProducto  = (Producto)  object;
        boolean isEdit = false;

        try{
            String sql = "UPDATE producto SET nombre_producto = ?,precio = ?,id_tienda =?,Stock=? where id_producto = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);


            objPrepare.setString(1,objProducto.getNombre_producto());
            objPrepare.setDouble(2,objProducto.getPrecio());
            objPrepare.setInt(3,objProducto.getId_tienda());
            objPrepare.setInt(4,objProducto.getStock());
            objPrepare.setInt(5,objProducto.getId_producto());


            int totalAffectect = objPrepare.executeUpdate();

            if(totalAffectect > 0){
                isEdit = true;
                JOptionPane.showMessageDialog(null,"Producto editado correctamente");

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

        Producto objProducto = (Producto) object;

        boolean isDelete = false;

        try{
            String sql = "DELETE FROM producto WHERE id_producto = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);
            objPrepare.setInt(1,objProducto.getId_producto());
            int totalAffect = objPrepare.executeUpdate();

            if(totalAffect > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Producto eliminado exitosamente");
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return isDelete;
    }

    @Override
    public List<Object> findAll() {
        Connection objConection = ConfiDB.openConnection();

        List<Object> listaProductos = new ArrayList<>();

        try{
            String sql = "SELECT *\n" +
                    "FROM producto\n" +
                    "INNER JOIN tienda ON tienda.id_tienda = producto.id_tienda;";
            PreparedStatement objPrepare = objConection.prepareStatement(sql);

            ResultSet objResult = (ResultSet)  objPrepare.executeQuery();
            while (objResult.next()){
                Producto objProducto = new Producto();

                objProducto.setId_producto(objResult.getInt("id_producto"));
                objProducto.setId_tienda(objResult.getInt("id_tienda"));
                objProducto.setNombre_producto(objResult.getString("nombre_producto"));
                objProducto.setPrecio(objResult.getDouble("precio"));
                objProducto.setStock(objResult.getInt("Stock"));

                Tienda objTienda = new Tienda();

                objTienda.setId_tienda(objResult.getInt("id_tienda"));
                objTienda.setNombre_tienda(objResult.getString("nombre_tienda"));
                objTienda.setUbicacion(objResult.getString("ubicacion"));

                objProducto.setObjTienda(objTienda);






                listaProductos.add(objProducto);

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la carga\n" + e.getMessage());
        }
        ConfiDB.closeConnection();
        return listaProductos;
    }

    @Override
    public Object findById(int id) {

        Connection objConection = ConfiDB.openConnection();

        Producto objProducto = new Producto();

        try{
            String sql = "SELECT * FROM producto WHERE id_producto = ?;";

            PreparedStatement objPrepare = objConection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,id);
            objPrepare.executeQuery();
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            boolean resultadoId = objResult.next();

            if(!resultadoId){
                JOptionPane.showMessageDialog(null,"Id no encontrado");
                return null;
            }else {
                objProducto.setId_producto(objResult.getInt("id_producto"));
                objProducto.setNombre_producto(objResult.getString("nombre_producto"));
                objProducto.setPrecio(objResult.getDouble("precio"));
                objProducto.setId_tienda(objResult.getInt("id_tienda"));
                objProducto.setStock(objResult.getInt("Stock"));

            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data error");
            System.out.println(e.getMessage());
        }
        ConfiDB.closeConnection();

        return objProducto;
    }

    @Override
    public Object ActualizarStock(int cantidad) {
        return null;
    }


}
