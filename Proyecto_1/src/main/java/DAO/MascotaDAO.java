/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.ConexionDB;
import Entidades.Mascota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class MascotaDAO implements IMascotaDAO{
    private ConexionDB conexion = new ConexionDB();

    @Override
    public boolean insertar(Mascota mascota) {

        String sql = "INSERT INTO mascota (nombre, especie, fecha_nacimiento, sexo, raza, id_dueno) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection con = conexion.crearConexionBD();
            PreparedStatement cmd = con.prepareStatement(sql);

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

    @Override
    public boolean actualizar(Mascota mascota) {

        String sql = "UPDATE mascota SET nombre = ?, especie = ?, fecha_nacimiento = ?, sexo = ?, raza = ?, id_dueno = ? WHERE id_mascota = ?";

        try {
            Connection con = conexion.crearConexionBD();
            PreparedStatement cmd = con.prepareStatement(sql);

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

    @Override
    public boolean eliminar(int id) {

        String sql = "DELETE FROM mascota WHERE id_mascota = ?";

        try {
            Connection con = conexion.crearConexionBD();
            PreparedStatement cmd = con.prepareStatement(sql);

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

    @Override
    public Mascota consultar(int id) {

        String sql = "SELECT * FROM mascota WHERE id_mascota = ?";

        try {
            Connection con = conexion.crearConexionBD();
            PreparedStatement cmd = con.prepareStatement(sql);

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
                mascota.setIdDueno(res.getInt("id_dueno"));

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

    @Override
    public List<Mascota> consultarTodos() {

        List<Mascota> lista = new ArrayList<>();

        String sql = "SELECT * FROM mascota";

        try {
            Connection con = conexion.crearConexionBD();
            PreparedStatement cmd = con.prepareStatement(sql);

            ResultSet res = cmd.executeQuery();

            while (res.next()) {

                Mascota mascota = new Mascota();

                mascota.setIdMascota(res.getInt("id_mascota"));
                mascota.setNombre(res.getString("nombre"));
                mascota.setEspecie(res.getString("especie"));
                mascota.setFechaNacimiento(res.getDate("fecha_nacimiento"));
                mascota.setSexo(res.getString("sexo"));
                mascota.setRaza(res.getString("raza"));
                mascota.setIdDueno(res.getInt("id_dueno"));

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
