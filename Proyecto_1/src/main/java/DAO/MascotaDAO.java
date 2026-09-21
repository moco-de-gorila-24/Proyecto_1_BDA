/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.ConexionDB;
import Conexion.IConexion;
import Dominio.Entidades.Mascota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación concreta de {@link IMascotaDAO} que gestiona el acceso a datos
 * de la tabla {@code mascota} en la base de datos veterinaria.
 *
 * @author ACER
 */
public class MascotaDAO implements IMascotaDAO{
    /** Objeto de conexión a la base de datos. */
    private IConexion conexion = new ConexionDB();

    /**
     * Inserta una nueva mascota en la tabla {@code mascota}.
     *
     * @param mascota objeto {@link Mascota} con los datos a registrar.
     * @return {@code true} si la inserción fue exitosa, {@code false} en caso contrario.
     */
    @Override
    public boolean insertar(Mascota mascota) {

        String sql = "INSERT INTO mascota (nombre, especie, fecha_nacimiento, sexo, raza, id_duenio) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){


            cmd.setString(1, mascota.getNombre());
            cmd.setString(2, mascota.getEspecie());
            cmd.setDate(3, mascota.getFechaNacimiento());
            cmd.setString(4, mascota.getSexo());
            cmd.setString(5, mascota.getRaza());
            cmd.setInt(6, mascota.getIdDueno());

            cmd.executeUpdate();

            cmd.close();
            con.close();

            return true;

        } catch (Exception e) {
            System.out.println("Error al insertar mascota: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza los datos de una mascota existente.
     *
     * @param mascota objeto {@link Mascota} con los datos actualizados.
     * @return {@code true} si la actualización fue exitosa, {@code false} en caso contrario.
     */
    @Override
    public boolean actualizar(Mascota mascota) {

        String sql = "UPDATE mascota SET nombre = ?, especie = ?, fecha_nacimiento = ?, sexo = ?, raza = ?, id_duenio = ? WHERE id_mascota = ?";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)){

            cmd.setString(1, mascota.getNombre());
            cmd.setString(2, mascota.getEspecie());
            cmd.setDate(3, mascota.getFechaNacimiento());
            cmd.setString(4, mascota.getSexo());
            cmd.setString(5, mascota.getRaza());
            cmd.setInt(6, mascota.getIdDueno());
            cmd.setInt(7, mascota.getIdMascota());

            cmd.executeUpdate();

            cmd.close();
            con.close();

            return true;

        } catch (Exception e) {
            System.out.println("Error al actualizar mascota: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Elimina una mascota por su identificador.
     *
     * @param id identificador único de la mascota.
     * @return {@code true} si la eliminación fue exitosa, {@code false} en caso contrario.
     */
    @Override
    public boolean eliminar(int id) {

        String sql = "DELETE FROM mascota WHERE id_mascota = ?";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)){

            cmd.setInt(1, id);
            cmd.executeUpdate();

            cmd.close();
            con.close();

            return true;

        } catch (Exception e) {
            System.out.println("Error al eliminar mascota: " + e.getMessage());
            return false;
        }
    }

    /**
     * Consulta una mascota específica por su identificador.
     *
     * @param id identificador único de la mascota.
     * @return el objeto {@link Mascota} encontrado, o {@code null} si no existe.
     */
    @Override
    public Mascota consultar(int id) {

        String sql = "SELECT * FROM mascota WHERE id_mascota = ?";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)){

            cmd.setInt(1, id);

            ResultSet res = cmd.executeQuery();

            if (res.next()) {

                Mascota mascota = new Mascota();

                mascota.setIdMascota(res.getInt("id_mascota"));
                mascota.setNombre(res.getString("nombre"));
                mascota.setEspecie(res.getString("especie"));
                mascota.setFechaNacimiento(res.getDate("fecha_nacimiento"));
                mascota.setSexo(res.getString("sexo"));
                mascota.setRaza(res.getString("raza"));
                mascota.setIdDueno(res.getInt("id_duenio"));

                res.close();
                cmd.close();
                con.close();

                return mascota;
            }

            res.close();
            cmd.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error al consultar mascota: " + e.getMessage());
        }

        return null;
    }

    /**
     * Recupera todas las mascotas registradas en la base de datos.
     *
     * @return una lista con todas las mascotas.
     */
    @Override
    public List<Mascota> consultarTodos() {

        List<Mascota> lista = new ArrayList<>();

        String sql = "SELECT * FROM mascota";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)){

            ResultSet res = cmd.executeQuery();

            while (res.next()) {

                Mascota mascota = new Mascota();

                mascota.setIdMascota(res.getInt("id_mascota"));
                mascota.setNombre(res.getString("nombre"));
                mascota.setEspecie(res.getString("especie"));
                mascota.setFechaNacimiento(res.getDate("fecha_nacimiento"));
                mascota.setSexo(res.getString("sexo"));
                mascota.setRaza(res.getString("raza"));
                mascota.setIdDueno(res.getInt("id_duenio"));

                lista.add(mascota);
            }

            res.close();
            cmd.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error al consultar mascotas: " + e.getMessage());
        }

        return lista;
    }
}
