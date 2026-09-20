/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Dominio.Entidades.Veterinario;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface IVeterinarioDAO {
    boolean insertar(Veterinario veterinario);

    boolean actualizar(Veterinario veterinario);

    boolean eliminar(int id);

    Veterinario consultar(int id);

    List<Veterinario> consultarTodos();
}
