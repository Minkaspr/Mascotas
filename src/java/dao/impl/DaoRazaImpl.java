
package dao.impl;

import dao.DaoRaza;
import entidad.Raza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConexionBD;

public class DaoRazaImpl implements DaoRaza{
    
    private final ConexionBD conexion;
    private String mensaje;

    public DaoRazaImpl() {
        this.conexion = new ConexionBD();
    }

    @Override
    public List<Raza> razaSel() {
        List<Raza> lista = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT ")
                .append("id_raza, ")
                .append("nombre, ")
                .append("descripcion ")
                .append("FROM raza");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Raza raza = new Raza();
                raza.setId_raza(rs.getInt(1));
                raza.setNombre(rs.getString(2));
                raza.setDescripcion(rs.getString(3));
                lista.add(raza);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return lista;
    }

    // Seleccionamos los datos que se necesita para llenar los combobox - Select Option
    @Override
    public List<Raza> razaCbo() {
        List<Raza> lista = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT ")
                .append("id_raza, ")
                .append("nombre ")
                .append("FROM raza");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Raza raza = new Raza();
                raza.setId_raza(rs.getInt(1));
                raza.setNombre(rs.getString(2));
                lista.add(raza);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return lista;
    }
    
    @Override
    public Raza razaGet(Integer id) {
        Raza raza = new Raza();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_raza, ")
                .append("nombre, ")
                .append("descripcion ")
                .append("FROM raza")
                .append("WHERE id_raza = ?");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    raza.setId_raza(rs.getInt(1));
                    raza.setNombre(rs.getString(2));
                    raza.setDescripcion(rs.getString(3));
                } else {
                    raza = null;
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return raza;
    }

    @Override
    public String razaIns(Raza raza) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO raza( ")
                .append("nombre, ")
                .append("descripcion ")
                .append(") VALUES (?,?) ");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, raza.getNombre());
            ps.setString(2, raza.getDescripcion());
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
    public String razaUpd(Raza raza) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE raza SET ")
                .append("nombre = ?,")
                .append("descripcion = ? ")
                .append("WHERE id_raza = ? ");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, raza.getNombre());
            ps.setString(2, raza.getDescripcion());
            ps.setInt(3, raza.getId_raza());
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
    public String razaDel(List<Integer> ids) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM raza WHERE ")
                .append("id_raza = ? ");
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
