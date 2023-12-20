
package entidad;

public class Propietario {
    private Integer id_propietario;
    private String dni;
    private String paterno;
    private String materno;
    private String nombres;
    private String correo;
    private String telefono;

    public Propietario() {
    }

    public Propietario(Integer id_propietario, String dni, String paterno, String materno, String nombres, String correo, String telefono) {
        this.id_propietario = id_propietario;
        this.dni = dni;
        this.paterno = paterno;
        this.materno = materno;
        this.nombres = nombres;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Integer getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(Integer id_propietario) {
        this.id_propietario = id_propietario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
