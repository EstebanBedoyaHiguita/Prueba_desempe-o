package Entity;

public class Producto {

    private int id_producto;
    private String nombre_producto;
    private Double precio;
    private int id_tienda;
    private int Stock;

    public Tienda objTienda;

    public Producto() {
    }

    public Producto(int id_producto, String nombre_producto, Double precio, int id_tienda, int stock, Tienda objTienda) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.id_tienda = id_tienda;
        Stock = stock;
        this.objTienda = objTienda;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(int id_tienda) {
        this.id_tienda = id_tienda;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public Tienda getObjTienda() {
        return objTienda;
    }

    public void setObjTienda(Tienda objTienda) {
        this.objTienda = objTienda;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id_producto=" + id_producto +
                ", nombre_producto='" + nombre_producto + '\'' +
                ", precio=" + precio +
                ", id_tienda=" + id_tienda +
                ", Stock=" + Stock +
                ", objTienda=" + objTienda +
                '}';
    }
}
