
package dao;

import entidad.Raza;
import java.util.List;

public interface DaoRaza {
    List<Raza> razaSel();
    // Para llenar los combobox - Select Option
    List<Raza> razaCbo();
    Raza razaGet(Integer id);
    String razaIns(Raza raza);
    String razaUpd(Raza raza);
    String razaDel(List<Integer> ids);
    String getMessage();
}
