/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Duenio;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface IDuenioDAO {
    boolean actualizar(Duenio duenio);

    boolean insertar(Duenio duenio);

    boolean eliminar(int id);

    Duenio consultar(int id);

    List<Duenio> consultarTodos();
}
