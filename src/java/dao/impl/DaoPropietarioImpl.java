package dao.impl;

import dao.DaoPropietario;
import entidad.Propietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConexionBD;

public class DaoPropietarioImpl implements DaoPropietario {

    private final ConexionBD conexion;
    private String mensaje;

    public DaoPropietarioImpl() {
        this.conexion = new ConexionBD();
    }

    @Override
    public List<Propietario> propietarioSel() {
        List<Propietario> lista = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT ")
                .append("id_propietario, ")
                .append("dni, ")
                .append("paterno, ")
                .append("materno, ")
                .append("nombres, ")
                .append("correo, ")
                .append("telefono ")
                .append("FROM propietario");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Propietario propietario = new Propietario();
                propietario.setId_propietario(rs.getInt(1));
                propietario.setDni(rs.getString(2));
                propietario.setPaterno(rs.getString(3));
                propietario.setMaterno(rs.getString(4));
                propietario.setNombres(rs.getString(5));
                propietario.setCorreo(rs.getString(6));
                propietario.setTelefono(rs.getString(7));
                lista.add(propietario);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return lista;
    }

    // Seleccionamos los datos que se necesita para llenar los combobox - Select Option
    @Override
    public List<Propietario> propietarioCbo() {
        List<Propietario> lista = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT ")
                .append("id_propietario, ")
                .append("dni, ")
                .append("nombres, ")
                .append("paterno ")
                .append("FROM propietario");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Propietario propietario = new Propietario();
                propietario.setId_propietario(rs.getInt(1));
                propietario.setDni(rs.getString(2));
                propietario.setNombres(rs.getString(3));
                propietario.setPaterno(rs.getString(4));
                lista.add(propietario);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return lista;
    }
    
    @Override
    public Propietario propietarioGet(Integer id) {
        Propietario propietario = new Propietario();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_propietario, ")
                .append("dni, ")
                .append("paterno, ")
                .append("materno, ")
                .append("nombres, ")
                .append("correo, ")
                .append("telefono ")
                .append("FROM propietario")
                .append("WHERE id_propietario = ?");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    propietario.setId_propietario(rs.getInt(1));
                    propietario.setDni(rs.getString(2));
                    propietario.setPaterno(rs.getString(3));
                    propietario.setMaterno(rs.getString(4));
                    propietario.setNombres(rs.getString(5));
                    propietario.setCorreo(rs.getString(6));
                    propietario.setTelefono(rs.getString(7));
                } else {
                    propietario = null;
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return propietario;
    }

    @Override
    public String propietarioIns(Propietario propietario) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO propietario( ")
                .append("dni, ")
                .append("paterno, ")
                .append("materno, ")
                .append("nombres, ")
                .append("correo, ")
                .append("telefono ")
                .append(") VALUES (?,?,?,?,?) ");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, propietario.getDni());
            ps.setString(2, propietario.getPaterno());
            ps.setString(3, propietario.getMaterno());
            ps.setString(4, propietario.getNombres());
            ps.setString(5, propietario.getCorreo());
            ps.setString(6, propietario.getTelefono());
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
    public String propietarioUpd(Propietario propietario) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE propietario SET ")
                .append("dni = ?,")
                .append("paterno = ?,")
                .append("materno = ?,")
                .append("nombres = ?,")
                .append("correo = ?,")
                .append("telefono = ? ")
                .append("WHERE id_propietario = ? ");
        try (Connection cn = conexion.conexionBD()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, propietario.getDni());
            ps.setString(2, propietario.getPaterno());
            ps.setString(3, propietario.getMaterno());
            ps.setString(4, propietario.getNombres());
            ps.setString(5, propietario.getCorreo());
            ps.setString(6, propietario.getTelefono());
            ps.setInt(7, propietario.getId_propietario());
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
    public String propietarioDel(List<Integer> ids) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM propietario WHERE ")
                .append("id_propietario = ? ");
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
