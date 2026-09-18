/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.ConexionDB;
import Entidades.Consulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class ConsultaDAO implements IConsultaDAO {
    private ConexionDB conexion = new ConexionDB();

    @Override
    public boolean insertar(Consulta consulta) {

        String sql = "INSERT INTO consulta (fecha_hora, motivo, diagnostico, tratamiento, costo, id_mascota, id_veterinario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = conexion.crearConexionBD();
            PreparedStatement cmd = con.prepareStatement(sql);

            cmd.setTimestamp(1, consulta.getFechaHora());
            cmd.setString(2, consulta.getMotivo());
            cmd.setString(3, consulta.getDiagnostico());
            cmd.setString(4, consulta.getTratamiento());
            cmd.setBigDecimal(5, consulta.getCosto());
            cmd.setInt(6, consulta.getIdMascota());
            cmd.setInt(7, consulta.getIdVeterinario());

            cmd.executeUpdate();

            cmd.close();
            con.close();

            return true;

        } catch (Exception e) {
            System.out.println("Error al insertar consulta: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Consulta consulta) {

        String sql = "UPDATE consulta SET fecha_hora = ?, motivo = ?, diagnostico = ?, tratamiento = ?, costo = ?, id_mascota = ?, id_veterinario = ? WHERE id_consulta = ?";

        try {
            Connection con = conexion.crearConexionBD();
            PreparedStatement cmd = con.prepareStatement(sql);

            cmd.setTimestamp(1, consulta.getFechaHora());
            cmd.setString(2, consulta.getMotivo());
            cmd.setString(3, consulta.getDiagnostico());
            cmd.setString(4, consulta.getTratamiento());
            cmd.setBigDecimal(5, consulta.getCosto());
            cmd.setInt(6, consulta.getIdMascota());
            cmd.setInt(7, consulta.getIdVeterinario());
            cmd.setInt(8, consulta.getIdConsulta());

            cmd.executeUpdate();

            cmd.close();
            con.close();

            return true;

        } catch (Exception e) {
            System.out.println("Error al actualizar consulta: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {

        String sql = "DELETE FROM consulta WHERE id_consulta = ?";

        try {
            Connection con = conexion.crearConexionBD();
            PreparedStatement cmd = con.prepareStatement(sql);

            cmd.setInt(1, id);
            cmd.executeUpdate();

            cmd.close();
            con.close();

            return true;

        } catch (Exception e) {
            System.out.println("Error al eliminar consulta: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Consulta consultar(int id) {

        String sql = "SELECT * FROM consulta WHERE id_consulta = ?";

        try {
            Connection con = conexion.crearConexionBD();
            PreparedStatement cmd = con.prepareStatement(sql);

            cmd.setInt(1, id);

            ResultSet res = cmd.executeQuery();

            if (res.next()) {

                Consulta consulta = new Consulta();

                consulta.setIdConsulta(res.getInt("id_consulta"));
                consulta.setFechaHora(res.getTimestamp("fecha_hora"));
                consulta.setMotivo(res.getString("motivo"));
                consulta.setDiagnostico(res.getString("diagnostico"));
                consulta.setTratamiento(res.getString("tratamiento"));
                consulta.setCosto(res.getBigDecimal("costo"));
                consulta.setIdMascota(res.getInt("id_mascota"));
                consulta.setIdVeterinario(res.getInt("id_veterinario"));

                res.close();
                cmd.close();
                con.close();

                return consulta;
            }

            res.close();
            cmd.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error al consultar consulta: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Consulta> consultarTodos() {

        List<Consulta> lista = new ArrayList<>();

        String sql = "SELECT * FROM consulta";

        try {
            Connection con = conexion.crearConexionBD();
            PreparedStatement cmd = con.prepareStatement(sql);

            ResultSet res = cmd.executeQuery();

            while (res.next()) {

                Consulta consulta = new Consulta();

                consulta.setIdConsulta(res.getInt("id_consulta"));
                consulta.setFechaHora(res.getTimestamp("fecha_hora"));
                consulta.setMotivo(res.getString("motivo"));
                consulta.setDiagnostico(res.getString("diagnostico"));
                consulta.setTratamiento(res.getString("tratamiento"));
                consulta.setCosto(res.getBigDecimal("costo"));
                consulta.setIdMascota(res.getInt("id_mascota"));
                consulta.setIdVeterinario(res.getInt("id_veterinario"));

                lista.add(consulta);
            }

            res.close();
            cmd.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error al consultar consultas: " + e.getMessage());
        }

        return lista;
    }
}
