package BO;

import DAO.MascotaDAO;
import DAO.VeterinarioDAO;
import Dominio.DTO.DTOMascota;
import Dominio.DTO.DTOVeterinario;
import Dominio.Entidades.Mascota;
import Dominio.Entidades.Veterinario;

public class VeterinarioBO {

    public VeterinarioBO(){

    }

    public boolean insertar(DTOVeterinario dtoVeterinario){
        Veterinario veterinario = new Veterinario();
        veterinario.setNombre(dtoVeterinario.getNombre());
        veterinario.setApellidoP(dtoVeterinario.getApellidoP());
        veterinario.setApellidoM(dtoVeterinario.getApellidoM());
        veterinario.setCedulaProfesional(dtoVeterinario.getCedulaProfesional());
        veterinario.setEspecialidad(dtoVeterinario.getEspecialidad());
        veterinario.setTelefono(dtoVeterinario.getTelefono());

        VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
        if(veterinarioDAO.insertar(veterinario)){
            return true;
        }
        return false;
    }

    public boolean actualizar(DTOVeterinario dtoVeterinario){
        Veterinario veterinario = new Veterinario();
        veterinario.setNombre(dtoVeterinario.getNombre());
        veterinario.setApellidoP(dtoVeterinario.getApellidoP());
        veterinario.setApellidoM(dtoVeterinario.getApellidoM());
        veterinario.setCedulaProfesional(dtoVeterinario.getCedulaProfesional());
        veterinario.setEspecialidad(dtoVeterinario.getEspecialidad());
        veterinario.setTelefono(dtoVeterinario.getTelefono());

        VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
        if(veterinarioDAO.actualizar(veterinario)){
            return true;
        }
        return false;
    }

    public boolean eliminar(int id){
        if(id <= 0){
            return false;
        }

        VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
        if(veterinarioDAO.eliminar(id)){
            return true;
        }
        return false;

    }

    public DTOVeterinario consultar(int id){
        if(id <= 0){
        return null;
        }
        VeterinarioDAO veterinarioDAO = new VeterinarioDAO();

        Veterinario veterinario = veterinarioDAO.consultar(id);
        if(veterinario != null){
            DTOVeterinario dtoVeterinario = new DTOVeterinario(veterinario.getIdVeterinario(),
                                                               veterinario.getNombre(),
                                                               veterinario.getApellidoP(),
                                                               veterinario.getApellidoM(),
                                                               veterinario.getCedulaProfesional(),
                                                               veterinario.getEspecialidad(),
                                                               veterinario.getTelefono());
        }
        return null;
    }
}
