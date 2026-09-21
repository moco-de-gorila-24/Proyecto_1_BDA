/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import Conexion.ConexionDB;
import Conexion.IConexion;
import Dominio.Entidades.Consulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación concreta de {@link IConsultaDAO} que gestiona el acceso a datos
 * de la tabla {@code consulta} en la base de datos veterinaria.
 * <p>
 * Cada método abre y cierra su propia conexión JDBC mediante {@link ConexionDB}.
 * </p>
 *
 * @author Jorge
 */
public class ConsultaDAO implements IConsultaDAO {
    /** Objeto de conexión a la base de datos. */
    private IConexion conexion = new ConexionDB();
    
    /**
     * Inserta una nueva consulta en la tabla {@code consulta}.
     *
     * @param consulta objeto {@link Consulta} con los datos a registrar.
     * @return {@code true} si la inserción fue exitosa, {@code false} en caso contrario.
     */
    @Override
    public boolean insertar(Consulta consulta) {

        String sql = "INSERT INTO consulta (fecha_hora, motivo, diagnostico, tratamiento, costo, id_mascota, id_veterinario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(Connection con = this.conexion.crearConexionBD();
            PreparedStatement cmd = con.prepareStatement(sql)) {

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
    
    /**
     * Actualiza los datos de una consulta existente identificada por {@code id_consulta}.
     *
     * @param consulta objeto {@link Consulta} con los datos actualizados.
     * @return {@code true} si la actualización fue exitosa, {@code false} en caso contrario.
     */
    @Override
    public boolean actualizar(Consulta consulta) {

        String sql = "UPDATE consulta SET fecha_hora = ?, motivo = ?, diagnostico = ?, tratamiento = ?, costo = ?, id_mascota = ?, id_veterinario = ? WHERE id_consulta = ?";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)){

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
    /**
     * Elimina una consulta de la tabla {@code consulta} por su identificador.
     *
     * @param id identificador único de la consulta.
     * @return {@code true} si la eliminación fue exitosa, {@code false} en caso contrario.
     */
    @Override
    public boolean eliminar(int id) {

        String sql = "DELETE FROM consulta WHERE id_consulta = ?";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)){

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
     /**
     * Consulta una consulta específica por su identificador.
     *
     * @param id identificador único de la consulta.
     * @return el objeto {@link Consulta} encontrado, o {@code null} si no existe.
     */
    @Override
    public Consulta consultar(int id) {

        String sql = "SELECT * FROM consulta WHERE id_consulta = ?";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)){

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
     /**
     * Recupera todas las consultas registradas en la base de datos.
     *
     * @return una lista con todas las consultas.
     */
    @Override
    public List<Consulta> consultarTodos() {

        List<Consulta> lista = new ArrayList<>();

        String sql = "SELECT * FROM consulta";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)){

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
