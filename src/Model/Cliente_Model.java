package Model;

import Data_base.CRUD_CLIENTE;
import Data_base.ConfiDB;
import Entity.Cliente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cliente_Model implements CRUD_CLIENTE {
    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfiDB.openConnection();

        Cliente objCliente = (Cliente) object;

        try{
            String sql = "INSERT INTO cliente (nombre_cliente,apellidos,email) VALUE(?,?,?)";

            PreparedStatement objPrepare = (PreparedStatement)  objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objCliente.getNombre_cliente());
            objPrepare.setString(2,objCliente.getApellidos());
            objPrepare.setString(3,objCliente.getEmail());


            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objCliente.setId_cliente(objResult.getInt(1));

            }
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Cliente agregado correctamente");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al agegar cliente"+ e.getMessage());
            ConfiDB.closeConnection();
        }
        return objCliente;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfiDB.openConnection();

        Cliente objCliente  = (Cliente)  object;
        boolean isEdit = false;

        try{
            String sql = "UPDATE cliente SET nombre_cliente = ?,apellidos = ?,email =? where id_cliente = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);

            objPrepare.setString(1,objCliente.getNombre_cliente());
            objPrepare.setString(2,objCliente.getApellidos());
            objPrepare.setString(3,objCliente.getEmail());
            objPrepare.setInt(4,objCliente.getId_cliente());


            int totalAffectect = objPrepare.executeUpdate();

            if(totalAffectect > 0){
                isEdit = true;
                JOptionPane.showMessageDialog(null,"Cliente editado correctamente");

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

        Cliente objCliente = (Cliente) object;

        boolean isDelete = false;

        try{
            String sql = "DELETE FROM cliente WHERE id_cliente = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.NO_GENERATED_KEYS);
            objPrepare.setInt(1,objCliente.getId_cliente());
            int totalAffect = objPrepare.executeUpdate();

            if(totalAffect > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Cliente eliminado exitosamente");
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return isDelete;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfiDB.openConnection();

        List<Object> listClientes = new ArrayList<>();

        try{
            String sql = "SELECT * FROM cliente ORDER BY cliente.id_cliente ASC;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = (ResultSet)  objPrepare.executeQuery();
            while (objResult.next()){
                Cliente objCliente = new Cliente();

                objCliente.setId_cliente(objResult.getInt("id_cliente"));
                objCliente.setNombre_cliente(objResult.getString("nombre_cliente"));
                objCliente.setApellidos(objResult.getString("apellidos"));
                objCliente.setEmail(objResult.getString("email"));

                listClientes.add(objCliente);

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la carga");
        }
        ConfiDB.closeConnection();
        return listClientes;
    }

    @Override
    public Object findById(int id) {
        Connection objConnection = ConfiDB.openConnection();

        Cliente objCliente = new Cliente();

        try{
            String sql = "SELECT * FROM cliente WHERE id_cliente = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,id);
            objPrepare.executeQuery();
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            boolean resultadoId = objResult.next();

            if(!resultadoId){
                JOptionPane.showMessageDialog(null,"id no encontrado");
                return null;
            }else {
                objCliente.setId_cliente(objResult.getInt("id_cliente"));
                objCliente.setNombre_cliente(objResult.getString("nombre_cliente"));
                objCliente.setApellidos(objResult.getString("apellidos"));
                objCliente.setEmail(objResult.getString("email"));

            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data error");
            System.out.println(e.getMessage());
        }
        ConfiDB.closeConnection();

        return objCliente;
    }

    @Override
    public List<Object> findName(String nombre) {
        Connection objConnection = ConfiDB.openConnection();

        List<Object> listClientes = new ArrayList<>();

        try{
            String sql = "SELECT * FROM cliente WHERE nombre_cliente LIKE '%" + nombre + "%';";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()){
                Cliente objCliente = new Cliente();

                objCliente.setId_cliente(objResult.getInt("id_cliente"));
                objCliente.setNombre_cliente(objResult.getString("nombre_cliente"));
                objCliente.setApellidos(objResult.getString("apellidos"));
                objCliente.setEmail(objResult.getString("email"));

                listClientes.add(objCliente);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error al obtener cliente");
        }
        ConfiDB.closeConnection();
        return listClientes;
    }



}
