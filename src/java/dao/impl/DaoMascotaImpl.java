package dao.impl;

import dao.DaoMascota;
import dto.MascotaDTO;
import entidad.Mascota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import util.ConexionBD;

public class DaoMascotaImpl implements DaoMascota {

    private final ConexionBD conexion;
    private String mensaje;

    public DaoMascotaImpl() {
        this.conexion = new ConexionBD();
    }

    @Override
    public List<MascotaDTO> mascotaSel() {
        List<MascotaDTO> lista = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT ")
                .append("id_mascota, ")
                .append("nombre, ")
                .append("propietario, ")
                .append("fecha, ")
                .append("peso, ")
                .append("raza ")
                .append("FROM mascota_view");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                MascotaDTO mascotaDTO = new MascotaDTO();
                mascotaDTO.setId_mascota(rs.getInt(1));
                mascotaDTO.setNombre(rs.getString(2));
                mascotaDTO.setPropietario(rs.getString(3));
                mascotaDTO.setFecha(LocalDate.parse(rs.getString(4)));
                mascotaDTO.setPeso(rs.getFloat(5));
                mascotaDTO.setRaza(rs.getString(6));
                lista.add(mascotaDTO);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return lista;
    }
    
    @Override
    public MascotaDTO mascotaDat(Integer id) {
        MascotaDTO mascotaDTO = new MascotaDTO();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_mascota, ")
                .append("nombre, ")
                .append("propietario, ")
                .append("fecha, ")
                .append("peso, ")
                .append("raza ")
                .append("FROM mascota_view ")
                .append("WHERE id_mascota = ?");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mascotaDTO.setId_mascota(rs.getInt(1));
                    mascotaDTO.setNombre(rs.getString(2));
                    mascotaDTO.setPropietario(rs.getString(3));
                    mascotaDTO.setFecha(LocalDate.parse(rs.getString(4)));
                    mascotaDTO.setPeso(rs.getFloat(5));
                    mascotaDTO.setRaza(rs.getString(6));
                } else {
                    mascotaDTO = null;
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mascotaDTO;
    }

    @Override
    public Mascota mascotaGet(Integer id) {
        Mascota mascota = new Mascota();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_mascota, ")
                .append("nombre, ")
                .append("propietario, ")
                .append("fecha, ")
                .append("peso, ")
                .append("raza ")
                .append("FROM mascota ")
                .append("WHERE id_mascota = ?");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mascota.setId_mascota(rs.getInt(1));
                    mascota.setNombre(rs.getString(2));
                    mascota.setPropietario(rs.getInt(3));
                    mascota.setFecha(LocalDate.parse(rs.getString(4)));
                    mascota.setPeso(rs.getFloat(5));
                    mascota.setRaza(rs.getInt(6));
                } else {
                    mascota = null;
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mascota;
    }

    @Override
    public String mascotaIns(Mascota mascota) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO mascota( ")
                .append("nombre, ")
                .append("propietario, ")
                .append("fecha, ")
                .append("peso, ")
                .append("raza ")
                .append(") VALUES (?,?,?,?,?) ");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, mascota.getNombre());
            ps.setInt(2, mascota.getPropietario());
            ps.setString(3, mascota.getFecha().toString());
            ps.setFloat(4, mascota.getPeso());
            ps.setInt(5, mascota.getRaza());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                mensaje = "cero filas insertadas";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    @Override
    public String mascotaUpd(Mascota mascota) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE mascota SET ")
                .append("nombre = ?,")
                .append("propietario = ?,")
                .append("fecha = ?,")
                .append("peso = ?,")
                .append("raza = ? ")
                .append("WHERE id_mascota = ? ");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, mascota.getNombre());
            ps.setInt(2, mascota.getPropietario());
            ps.setString(3, mascota.getFecha().toString());
            ps.setFloat(4, mascota.getPeso());
            ps.setInt(5, mascota.getRaza());
            ps.setInt(6, mascota.getId_mascota());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                mensaje = "No se pudo actualizar"; 
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    @Override
    public String mascotaDel(List<Integer> ids) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM mascota WHERE ")
                .append("id_mascota = ? ");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            cn.setAutoCommit(false);
            boolean ok = true;
            for (int id = 0; id < ids.size(); id++) {
                ps.setInt(1, ids.get(id));
                int ctos = ps.executeUpdate();
                if (ctos == 0) {
                    ok = false;
                    mensaje = "ID: " + ids.get(id) + " no existe";
                }
            }
            if (ok) {
                cn.commit();
            } else {
                cn.rollback();
            }
            cn.setAutoCommit(true);
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
