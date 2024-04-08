package Model;

import Data_base.ConfiDB;
import Entity.Tienda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tienda_Model {


    public List<Object> findAll() {
        Connection objConnection = ConfiDB.openConnection();

        List<Object> listTiendas = new ArrayList<>();

        try{
            String sql = "SELECT * FROM tienda ORDER BY tienda.id_tienda ASC;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = (ResultSet)  objPrepare.executeQuery();
            while (objResult.next()){
                Tienda objTienda = new Tienda();

                objTienda.setId_tienda(objResult.getInt("id_tienda"));
                objTienda.setNombre_tienda(objResult.getString("nombre_tienda"));
                objTienda.setUbicacion(objResult.getString("ubicacion"));


                listTiendas.add(objTienda);

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la carga");
        }
        ConfiDB.closeConnection();
        return listTiendas;
    }

    public Object findById(int id) {
        Connection objConnection = ConfiDB.openConnection();

        Tienda objTienda = new Tienda();

        try{
            String sql = "SELECT * FROM tienda WHERE id_tienda = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,id);
            objPrepare.executeQuery();
            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            boolean resultadoId = objResult.next();

            if(!resultadoId){
                JOptionPane.showMessageDialog(null,"id no encontrado");
                return null;
            }else {
                objTienda.setId_tienda(objResult.getInt("id_tienda"));
                objTienda.setNombre_tienda(objResult.getString("nombre_tienda"));
                objTienda.setUbicacion(objResult.getString("ubicacion"));


            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data error");
            System.out.println(e.getMessage());
        }
        ConfiDB.closeConnection();

        return objTienda;
    }
}
