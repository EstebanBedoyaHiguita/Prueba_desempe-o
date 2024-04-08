import Controller.Cliente_controller;
import Controller.Compra_controller;
import Controller.Producto_Controller;
import Data_base.ConfiDB;
import Entity.Producto;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Cliente_controller objCliente_Controller = new Cliente_controller();
        Producto_Controller objProducto_Controller = new Producto_Controller();
        Compra_controller objCompra_Controller = new Compra_controller();

        String opcion1 = "";

        do{

            opcion1 = JOptionPane.showInputDialog("""
                    1.Cliente
                    2.Producto
                    3.Compra
                    4.Salir
                    """);

            switch (opcion1){
                case "1":
                    String opcion2 = "";

                    do {
                        opcion2 = JOptionPane.showInputDialog("""
                    1.Crear Cliente
                    2.Editar Cliente
                    3.Buscar Cliente
                    4.Eliminar Cliente
                    5.Lista Cliente
                    6.Salir
                    """);

                        switch (opcion2){
                            case "1":
                                objCliente_Controller.create();
                                break;
                            case "2":
                                objCliente_Controller.edit();
                                break;
                            case "3":
                                String opcion3 = "";

                                do {
                                    opcion3 = JOptionPane.showInputDialog("""
                                                1.Buscar por nombre
                                                2.Buscar por id
                                                3.Salir
                                              
                                                """);

                                    switch (opcion3){
                                        case "1":
                                            objCliente_Controller.buscarName();
                                            break;
                                        case "2":
                                            objCliente_Controller.buscarId();
                                            break;
                                    }
                                }while (!opcion3.equals("3"));
                                break;
                            case "4":
                                objCliente_Controller.delete();
                                break;
                            case "5":
                                objCliente_Controller.getAll();
                                break;
                        }
                    }while (!opcion2.equals("6"));
                    break;
                case "2":
                    String opcion5 = "";

                    do {
                        opcion5 = JOptionPane.showInputDialog("""
                                1.Crear Producto
                                2.Editar Producto
                                3.Buscar Producto por id
                                4.Eliminar Producto
                                5.Lista de Productos
                                6.Salir
                                """);

                        switch (opcion5) {
                            case "1":
                                objProducto_Controller.create();
                                break;
                            case "2":
                                objProducto_Controller.edit();
                                break;
                            case "3":
                                objProducto_Controller.buscarId();
                                break;
                            case "4":
                                objProducto_Controller.delete();
                                break;
                            case "5":
                                objProducto_Controller.getAll();
                                break;
                        }
                    }while (!opcion5.equals("6"));
                    break;
                case "3":

                    String opcion7 = "";

                    do {
                        opcion7 = JOptionPane.showInputDialog("""
                                1.Crear Compra
                                2.Editar Compra
                                3.Buscar Compra
                                4.Eliminar Compra
                                5.Lista de Compras
                                6.Salir
                                """);

                        switch (opcion7) {
                            case "1":
                                objCompra_Controller.create();
                                break;
                            case "2":
                                objCompra_Controller.edit();
                                break;
                            case "3":
                                String opcion8 = "";

                                do {
                                    opcion8 = JOptionPane.showInputDialog("""
                                                1.Buscar por fecha
                                                2.Buscar por Id
                                                3.Salir
                                              
                                                """);

                                    switch (opcion8){
                                        case "1":
                                            objCompra_Controller.buscarDate();
                                            break;
                                        case "2":
                                            objCompra_Controller.buscarId();

                                            break;
                                    }
                                }while (!opcion8.equals("3"));
                                break;
                            case "4":
                                objCompra_Controller.delete();

                                break;
                            case "5":
                                objCompra_Controller.getAll();

                                break;
                        }
                    }while (!opcion7.equals("6"));
                    break;

            }

        }while (!opcion1.equals("4"));


    }
}