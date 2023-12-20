
package dao;

import entidad.Propietario;
import java.util.List;

public interface DaoPropietario {
    List<Propietario> propietarioSel();
    // Para llenar los combobox - Select Option
    List<Propietario> propietarioCbo();
    Propietario propietarioGet(Integer id);
    String propietarioIns(Propietario propietario);
    String propietarioUpd(Propietario propietario);
    String propietarioDel(List<Integer> ids);
    String getMessage();
}
