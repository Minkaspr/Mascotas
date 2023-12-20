
package web.validator;

import dao.DaoMascota;
import dao.DaoPropietario;
import dao.DaoRaza;
import dao.impl.DaoMascotaImpl;
import dao.impl.DaoPropietarioImpl;
import dao.impl.DaoRazaImpl;
import dto.MascotaDTO;
import entidad.Mascota;
import entidad.Propietario;
import entidad.Raza;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import util.DeString;

public class MascotaValidator {
    private final HttpServletRequest request;
    private final DaoMascota daoMascota;

    public MascotaValidator(HttpServletRequest request) {
        this.request = request;
        this.daoMascota = new DaoMascotaImpl();
    }

    public String mascotaSel() {
        String result = null;
        List<MascotaDTO> list = daoMascota.mascotaSel();
        if (list != null) {
            request.setAttribute("mascotas", list);
        } else { 
            result = daoMascota.getMessage();
        }
        return result; 
    }

    public String mascotaCBO() {
        String result = null;
        // Obtenemos la lista de los 'propietarios' y 'razas' para el combobox - Select Option
        DaoPropietario daoPropietario = new DaoPropietarioImpl();
        List<Propietario> propietarios = daoPropietario.propietarioCbo();
        
        DaoRaza daoRaza = new DaoRazaImpl();
        List<Raza> razas = daoRaza.razaCbo();
        
        if (propietarios != null && razas != null) { 
            // Enviamos la lista de 'propietarios' y 'razas' para cargar el combobox - Select Option
            request.setAttribute("razas", razas);
            request.setAttribute("propietarios", propietarios);
        } else {
            result = daoMascota.getMessage();
        }
        return result; 
    }
    
    public String mascotaGet() {
        String result = null;
        Integer id_mascota = DeString.aInteger(request.getParameter("id_mascota"));
        Mascota mascota = daoMascota.mascotaGet(id_mascota);
        // Obtenemos la lista de los 'propietarios' y 'razas' para el combobox - Select Option
        DaoPropietario daoPropietario = new DaoPropietarioImpl();
        List<Propietario> propietarios = daoPropietario.propietarioCbo();
        DaoRaza daoRaza = new DaoRazaImpl();
        List<Raza> razas = daoRaza.razaCbo();
        
        if (mascota != null) { 
            request.setAttribute("mascota", mascota);
            // Enviamos la lista de 'propietarios' y 'razas' para cargar el combobox - Select Option
            request.setAttribute("razas", razas);
            request.setAttribute("propietarios", propietarios);
        } else {
            result = daoMascota.getMessage();
        }
        return result; 
    }

    public String mascotaInsUpd(boolean upd) {
        StringBuilder result = new StringBuilder("<ul>"); // (1)

        Integer id_mascota = DeString.aInteger(request.getParameter("id_mascota"));
        String nombre = request.getParameter("nombre");
        Integer propietario = DeString.aInteger(request.getParameter("propietario"));
        
        Integer  raza= DeString.aInteger(request.getParameter("raza"));
        Float peso = DeString.aFloat(request.getParameter("peso"));

        /*
        LocalDate fecha = null;
        String fecha_str = request.getParameter("fecha");
        if (fecha_str == null || fecha_str.isEmpty()) {
            result.append("<li>Fecha requerida</li>");
        } else {
            fecha = LocalDate.parse(fecha_str);
        }
        */
        String aux = request.getParameter("fecha");
        LocalDate fecha = (aux == null || aux.trim().length() == 0)
                ? null
                : LocalDate.parse(aux);
        if (fecha == null){
            result.append("<li>Fecha requerida</li>");
        } else if (fecha.isAfter(LocalDate.now())){
            result.append("<li>No colocar fechas futuras</li>");
        }
                
        if (upd && id_mascota == null) {
            result.append("<li>Id requerido</li>");
        }

        if (nombre == null || nombre.trim().length() == 0) {
            result.append("<li>Nombre requerido</li>"); 
        } else if (nombre.trim().length() < 3 || nombre.trim().length() > 50) {
            result.append("<li>La dimensión del nombre debe estar entre")
                    .append("3 a 50 caracteres</li>"); 
        }
        
        if (propietario == null) {
            result.append("<li>Propietario requerido</li>");
        }
        
        
        if (raza == null) {
            result.append("<li>Raza requerida</li>");
        }
                
        Mascota mascota = new Mascota();
        mascota.setId_mascota(id_mascota);
        mascota.setNombre(nombre);
        mascota.setPropietario(propietario);
        mascota.setFecha(fecha);
        mascota.setPeso(peso);
        mascota.setRaza(raza);

        if (result.length() == 4) {
            String msg = upd
                    ? daoMascota.mascotaUpd(mascota)
                    : daoMascota.mascotaIns(mascota);
            if (msg != null) {
                result.append("<li>").append(msg).append("</li>"); 
            }
        }
        
        //Si ocurre un error, se vuelve a cargar los valores
        DaoPropietario daoPropietario = new DaoPropietarioImpl();
        List<Propietario> propietarios = daoPropietario.propietarioCbo();
        DaoRaza daoRaza = new DaoRazaImpl();
        List<Raza> razas = daoRaza.razaCbo();
        if (result.length() > 4) {
            request.setAttribute("mascota", mascota);
            request.setAttribute("razas", razas);
            request.setAttribute("propietarios", propietarios);
        }
        return result.length() == 4 ? null : result.append("</ul>").toString();
    }
    
    public String mascotaDel() {
        List<Integer> ids = DeString.ids(request.getParameter("id_mascota"));
        String result = (ids != null)
                ? daoMascota.mascotaDel(ids)
                : "IDs incorrectos";
        return result; 
    }
}
