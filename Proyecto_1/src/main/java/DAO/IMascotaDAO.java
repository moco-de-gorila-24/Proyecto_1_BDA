/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Dominio.Entidades.Mascota;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface IMascotaDAO {
    boolean insertar(Mascota mascota);

    boolean actualizar(Mascota mascota);

    boolean eliminar(int id);

    Mascota consultar(int id);

    List<Mascota> consultarTodos();
}
