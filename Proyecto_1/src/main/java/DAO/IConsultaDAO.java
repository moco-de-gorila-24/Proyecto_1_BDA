/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Consulta;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface IConsultaDAO {
    boolean insertar(Consulta consulta);

    boolean actualizar(Consulta consulta);

    boolean eliminar(int id);

    Consulta consultar(int id);

    List<Consulta> consultarTodos();
}
