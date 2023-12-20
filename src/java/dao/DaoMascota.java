
package dao;

import dto.MascotaDTO;
import entidad.Mascota;
import java.util.List;

public interface DaoMascota {
    List<MascotaDTO> mascotaSel();
    MascotaDTO mascotaDat(Integer id);
    Mascota mascotaGet(Integer id);
    String mascotaIns(Mascota mascota);
    String mascotaUpd(Mascota mascota);
    String mascotaDel(List<Integer> ids);
    String getMessage();
}
