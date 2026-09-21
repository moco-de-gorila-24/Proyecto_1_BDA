/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.ConexionDB;
import Dominio.Entidades.Veterinario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación concreta de {@link IVeterinarioDAO} que gestiona el acceso a datos
 * de la tabla {@code veterinario} en la base de datos veterinaria.
 *
 * @author ACER
 */
public class VeterinarioDAO implements IVeterinarioDAO{
    /** Objeto de conexión a la base de datos. */
    private ConexionDB conexion = new ConexionDB();

    /**
     * Inserta un nuevo veterinario en la tabla {@code veterinario}.
     *
     * @param veterinario objeto {@link Veterinario} con los datos a registrar.
     * @return {@code true} si la inserción fue exitosa, {@code false} en caso contrario.
     */
    @Override
    public boolean insertar(Veterinario veterinario) {

        String sql = "INSERT INTO veterinario (nombre, apellidoP, apellidoM, cedula_profesional, especialidad, telefono) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)){

            cmd.setString(1, veterinario.getNombre());
            cmd.setString(2, veterinario.getApellidoP());
            cmd.setString(3, veterinario.getApellidoM());
            cmd.setString(4, veterinario.getCedulaProfesional());
            cmd.setString(5, veterinario.getEspecialidad());
            cmd.setString(6, veterinario.getTelefono());

            cmd.executeUpdate();

            cmd.close();
            con.close();

            return true;

        } catch (Exception e) {
            System.out.println("Error al insertar veterinario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza los datos de un veterinario existente.
     *
     * @param veterinario objeto {@link Veterinario} con los datos actualizados.
     * @return {@code true} si la actualización fue exitosa, {@code false} en caso contrario.
     */
    @Override
    public boolean actualizar(Veterinario veterinario) {

        String sql = "UPDATE veterinario SET nombre = ?, apellidoP = ?, apellidoM = ?, cedula_profesional = ?, especialidad = ?, telefono = ? WHERE id_veterinario = ?";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)){

            cmd.setString(1, veterinario.getNombre());
            cmd.setString(2, veterinario.getApellidoP());
            cmd.setString(3, veterinario.getApellidoM());
            cmd.setString(4, veterinario.getCedulaProfesional());
            cmd.setString(5, veterinario.getEspecialidad());
            cmd.setString(6, veterinario.getTelefono());
            cmd.setInt(7, veterinario.getIdVeterinario());

            cmd.executeUpdate();

            cmd.close();
            con.close();

            return true;

        } catch (Exception e) {
            System.out.println("Error al actualizar veterinario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un veterinario por su identificador.
     *
     * @param id identificador único del veterinario.
     * @return {@code true} si la eliminación fue exitosa, {@code false} en caso contrario.
     */
    @Override
    public boolean eliminar(int id) {

        String sql = "DELETE FROM veterinario WHERE id_veterinario = ?";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)){

            cmd.setInt(1, id);
            cmd.executeUpdate();

            cmd.close();
            con.close();

            return true;

        } catch (Exception e) {
            System.out.println("Error al eliminar veterinario: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Consulta un veterinario específico por su identificador.
     *
     * @param id identificador único del veterinario.
     * @return el objeto {@link Veterinario} encontrado, o {@code null} si no existe.
     */
    @Override
    public Veterinario consultar(int id) {

        String sql = "SELECT * FROM veterinario WHERE id_veterinario = ?";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)){

            cmd.setInt(1, id);

            ResultSet res = cmd.executeQuery();

            if (res.next()) {

                Veterinario veterinario = new Veterinario();

                veterinario.setIdVeterinario(res.getInt("id_veterinario"));
                veterinario.setNombre(res.getString("nombre"));
                veterinario.setApellidoP(res.getString("apellidoP"));
                veterinario.setApellidoM(res.getString("apellidoM"));
                veterinario.setCedulaProfesional(res.getString("cedula_profesional"));
                veterinario.setEspecialidad(res.getString("especialidad"));
                veterinario.setTelefono(res.getString("telefono"));

                res.close();
                cmd.close();
                con.close();

                return veterinario;
            }

            res.close();
            cmd.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error al consultar veterinario: " + e.getMessage());
        }

        return null;
    }

    /**
     * Recupera todos los veterinarios registrados en la base de datos.
     *
     * @return una lista con todos los veterinarios.
     */
    @Override
    public List<Veterinario> consultarTodos() {

        List<Veterinario> lista = new ArrayList<>();

        String sql = "SELECT * FROM veterinario";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)){

            ResultSet res = cmd.executeQuery();

            while (res.next()) {

                Veterinario veterinario = new Veterinario();

                veterinario.setIdVeterinario(res.getInt("id_veterinario"));
                veterinario.setNombre(res.getString("nombre"));
                veterinario.setApellidoP(res.getString("apellidoP"));
                veterinario.setApellidoM(res.getString("apellidoM"));
                veterinario.setCedulaProfesional(res.getString("cedula_profesional"));
                veterinario.setEspecialidad(res.getString("especialidad"));
                veterinario.setTelefono(res.getString("telefono"));

                lista.add(veterinario);
            }

            res.close();
            cmd.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error al consultar veterinarios: " + e.getMessage());
        }

        return lista;
    }
}
