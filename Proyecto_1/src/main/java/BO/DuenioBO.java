package BO;


import DAO.DuenioDAO;
import Dominio.DTO.DTODuenio;
import Dominio.Entidades.Duenio;

public class DuenioBO {

    public DuenioBO(){

    }

    public boolean insertarDuenio(DTODuenio DTODuenio){
        Duenio duenio = new Duenio();
        duenio.setNombre(DTODuenio.getNombre());
        duenio.setApellidoP(DTODuenio.getApellidoP());
        duenio.setApellidoM(DTODuenio.getApellidoM());
        duenio.setDireccion(DTODuenio.getDireccion());
        duenio.setEmail(DTODuenio.getEmail());
        duenio.setTelefonos(DTODuenio.getTelefonos());

        DuenioDAO duenioDAO = new DuenioDAO();

        if(duenioDAO.insertar(duenio)){
            return true;
        }
        return false;
    }

    public boolean actualizarDuenio(DTODuenio DTODuenio){
        Duenio duenio = new Duenio();
        duenio.setNombre(DTODuenio.getNombre());
        duenio.setApellidoP(DTODuenio.getApellidoP());
        duenio.setApellidoM(DTODuenio.getApellidoM());
        duenio.setDireccion(DTODuenio.getDireccion());
        duenio.setEmail(DTODuenio.getEmail());
        duenio.setTelefonos(DTODuenio.getTelefonos());

        DuenioDAO duenioDAO = new DuenioDAO();

        if(duenioDAO.actualizar(duenio)){
            return true;
        }
        return false;
    }

    public boolean eliminar(int id){
        DuenioDAO duenioDAO = new DuenioDAO();

        if(id <= 0){
            return false;
        }
        if(duenioDAO.eliminar(id)){
            return true;
        }
        return false;
    }

    public DTODuenio consultar(int id){
        DuenioDAO duenioDAO = new DuenioDAO();

        if(id <= 0){
            return null;
        }
        Duenio duenio1 = duenioDAO.consultar(id);

        if(duenio1 != null){
            DTODuenio dtoDuenio = new DTODuenio(duenio1.getIdDueno(),
                    duenio1.getNombre(),
                    duenio1.getApellidoP(),
                    duenio1.getApellidoM(),
                    duenio1.getDireccion(),
                    duenio1.getEmail(),
                    duenio1.getTelefonos());

            return dtoDuenio;
        }
        return null;
    }


}
