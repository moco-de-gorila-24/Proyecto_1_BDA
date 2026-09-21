package BO;

import DAO.ConsultaDAO;
import DAO.VeterinarioDAO;
import Dominio.DTO.DTOConsulta;
import Dominio.DTO.DTOVeterinario;
import Dominio.Entidades.Consulta;
import Dominio.Entidades.Veterinario;

public class ConsultaBO {

    public ConsultaBO(){

    }

    public boolean instertar(DTOConsulta dtoConsulta){
        if(dtoConsulta.equals(null)){
            return false;
        }
        Consulta consulta = new Consulta();
        consulta.setFechaHora(dtoConsulta.getFechaHora());
        consulta.setMotivo(dtoConsulta.getMotivo());
        consulta.setMotivo(dtoConsulta.getMotivo());
        consulta.setDiagnostico(dtoConsulta.getDiagnostico());
        consulta.setTratamiento(dtoConsulta.getTratamiento());
        consulta.setCosto(dtoConsulta.getCosto());
        consulta.setIdMascota(dtoConsulta.getIdMascota());
        consulta.setIdVeterinario(dtoConsulta.getIdVeterinario());

        ConsultaDAO consultaDAO = new ConsultaDAO();
        if(consultaDAO.insertar(consulta)){
            return true;
        }
        return false;
    }

    public boolean actualizar(DTOConsulta dtoConsulta){
        if(dtoConsulta.equals(null)){
            return false;
        }
        Consulta consulta = new Consulta();
        consulta.setFechaHora(dtoConsulta.getFechaHora());
        consulta.setMotivo(dtoConsulta.getMotivo());
        consulta.setMotivo(dtoConsulta.getMotivo());
        consulta.setDiagnostico(dtoConsulta.getDiagnostico());
        consulta.setTratamiento(dtoConsulta.getTratamiento());
        consulta.setCosto(dtoConsulta.getCosto());
        consulta.setIdMascota(dtoConsulta.getIdMascota());
        consulta.setIdVeterinario(dtoConsulta.getIdVeterinario());

        ConsultaDAO consultaDAO = new ConsultaDAO();
        if(consultaDAO.actualizar(consulta)){
            return true;
        }
        return false;
    }

    public boolean eliminar(int id){
        if(id <= 0){
            return false;
        }

        ConsultaDAO consultaDAO = new ConsultaDAO();
        if(consultaDAO.eliminar(id)){
            return true;
        }
        return false;
    }

    public DTOConsulta consultar(int id){
        if(id <= 0){
            return null;
        }
        ConsultaDAO consultaDAO = new ConsultaDAO();

        Consulta consulta = consultaDAO.consultar(id);
        if(consulta != null){
            DTOConsulta dtoConsulta = new DTOConsulta(consulta.getIdConsulta(),
                    consulta.getFechaHora(),
                    consulta.getMotivo(),
                    consulta.getDiagnostico(),
                    consulta.getTratamiento(),
                    consulta.getCosto(),
                    consulta.getIdMascota(),
                    consulta.getIdVeterinario());
            return dtoConsulta;
        }
        return null;
    }
}
