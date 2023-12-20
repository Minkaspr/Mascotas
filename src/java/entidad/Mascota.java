
package entidad;

import java.time.LocalDate;

public class Mascota {
    private Integer id_mascota;
    private String nombre;
    private Integer propietario;
    private LocalDate fecha;
    private Float peso;
    private Integer raza;

    public Mascota() {
    }
  
    public Mascota(Integer id_mascota, String nombre, Integer propietario, LocalDate fecha, Float peso, Integer raza) {
        this.id_mascota = id_mascota;
        this.nombre = nombre;
        this.propietario = propietario;
        this.fecha = fecha;
        this.peso = peso;
        this.raza = raza;
    }

    public Integer getRaza() {
        return raza;
    }

    public void setRaza(Integer raza) {
        this.raza = raza;
    }

    public Integer getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(Integer id_mascota) {
        this.id_mascota = id_mascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPropietario() {
        return propietario;
    }

    public void setPropietario(Integer propietario) {
        this.propietario = propietario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }
    
    
}
