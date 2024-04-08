package Controller;

import Entity.Cliente;
import Model.Cliente_Model;

import javax.swing.*;
import java.util.List;

public class Cliente_controller {
    Cliente_Model objCliente_Model;
    public Cliente_controller(){
        this.objCliente_Model = new Cliente_Model();
    }

    public  void create (){
        Cliente objCliente = new Cliente();

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos");
        String email = JOptionPane.showInputDialog("Ingrese el email");

        objCliente.setNombre_cliente(nombre);
        objCliente.setApellidos(apellidos);
        objCliente.setEmail(email);

        objCliente = (Cliente) this.objCliente_Model.insert(objCliente);
        JOptionPane.showMessageDialog(null,objCliente.toString());
    }

    public void  edit (){
        List<Object> ListClientes = objCliente_Model.findAll();

        Cliente[] listaCliente = new Cliente[ListClientes.size()];

        int index = 0;
        for(Object iterados : ListClientes){
            Cliente obj = (Cliente) iterados;
            listaCliente[index] = obj;
            index++;
        }

        Cliente clienteSelect = (Cliente) JOptionPane.showInputDialog(null,
                "select a cliente:\n","Choosing cliente",
                JOptionPane.QUESTION_MESSAGE,null,listaCliente,listaCliente[0]);

        if(clienteSelect != null){

            String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre", clienteSelect.getNombre_cliente());
            String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos", clienteSelect.getApellidos());
            String email = JOptionPane.showInputDialog("Ingrese el nuevo email",clienteSelect.getEmail());

            clienteSelect.setNombre_cliente(nombre);
            clienteSelect.setApellidos(apellidos);
            clienteSelect.setEmail(email);


            boolean editado = this.objCliente_Model.update (clienteSelect);

            if(editado){
                JOptionPane.showMessageDialog(null,"Cliente editado correctamente");
            }else {
                JOptionPane.showMessageDialog(null, "Error al editar el Cliente");
            }

        }

    }

    public void delete(){
        List<Object> ListClientes = objCliente_Model.findAll();

        Cliente[] listCliente = new Cliente[ListClientes.size()];

        int index = 0;
        for(Object iterados : ListClientes){
            Cliente obj = (Cliente) iterados;
            listCliente[index] = obj;
            index++;
        }

        Cliente clienteSelect = (Cliente) JOptionPane.showInputDialog(null,
                "select a cliente:\n","Choosing cliente",
                JOptionPane.QUESTION_MESSAGE,null,listCliente,listCliente[0]);

        this.objCliente_Model.delete(clienteSelect);
    }
    public void  getAll(){
        String list = "Lista de clientes\n";

        for  (Object obj : this.objCliente_Model.findAll()){
            Cliente objCliente = (Cliente) obj;
            list += objCliente.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }

    public void buscarName (){
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre");
        List<Object> listClientes = objCliente_Model.findName(nombre);
        String srtlistClientes = "";

        if(listClientes.isEmpty()){
            JOptionPane.showMessageDialog(null,"La lista esta vacia");

        }else {
            for (Object obj:listClientes){
                Cliente objCliente = (Cliente) obj;
                srtlistClientes += objCliente.toString()+"\n";
            }
            JOptionPane.showMessageDialog(null,"Clientes encontrados:"+srtlistClientes);
        }
    }

    public void buscarId(){
        int id_cliente = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Id que desea buscar"));
        Cliente objCliente = (Cliente) this.objCliente_Model.findById(id_cliente);

        if(objCliente == null){
            return;
        }else {
            JOptionPane.showMessageDialog(null,objCliente);
        }

    }
}
