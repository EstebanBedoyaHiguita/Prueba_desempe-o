package Entity;

public class Cliente {
    private int id_cliente;
    private String nombre_cliente;
    private String apellidos;
    private String email;

    public Cliente() {
    }

    public Cliente(int id_cliente, String nombre_cliente, String apellidos, String email) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.apellidos = apellidos;
        this.email = email;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id_cliente=" + id_cliente +
                ", nombre_cliente='" + nombre_cliente + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
