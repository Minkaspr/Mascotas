
package entidad;

public class Raza {
    private Integer id_raza;
    private String nombre;
    private String descripcion;

    public Raza() {
    }

    public Raza(Integer id_raza, String nombre, String descripcion) {
        this.id_raza = id_raza;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId_raza() {
        return id_raza;
    }

    public void setId_raza(Integer id_raza) {
        this.id_raza = id_raza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
