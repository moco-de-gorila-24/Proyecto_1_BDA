package BO;

import DAO.DuenioDAO;
import DAO.MascotaDAO;
import Dominio.DTO.DTOMascota;
import Dominio.Entidades.Mascota;

public class MascotaBO {

    public MascotaBO() {
    }

    public boolean insertar(DTOMascota dtoMascota){
        Mascota mascota = new Mascota();
        mascota.setNombre(dtoMascota.getNombre());
        mascota.setEspecie(dtoMascota.getEspecie());
        mascota.setFechaNacimiento(dtoMascota.getFechaNacimiento());
        mascota.setSexo(dtoMascota.getSexo());
        mascota.setRaza(dtoMascota.getRaza());
        mascota.setIdDueno(dtoMascota.getIdDueno());

        MascotaDAO mascotaDAO = new MascotaDAO();
        if(mascotaDAO.insertar(mascota)){
            return true;
        }

        return false;
    }

    public boolean actualizar(DTOMascota dtoMascota){
        Mascota mascota = new Mascota();
        mascota.setNombre(dtoMascota.getNombre());
        mascota.setEspecie(dtoMascota.getEspecie());
        mascota.setFechaNacimiento(dtoMascota.getFechaNacimiento());
        mascota.setSexo(dtoMascota.getSexo());
        mascota.setRaza(dtoMascota.getRaza());
        mascota.setIdDueno(dtoMascota.getIdDueno());

        MascotaDAO mascotaDAO = new MascotaDAO();
        if (mascotaDAO.actualizar(mascota)){
            return true;
        }
        return false;
    }

    public boolean eliminar(int id){
        MascotaDAO duenioDAO = new MascotaDAO();

        if(id <= 0){
            return false;
        }
        if(duenioDAO.eliminar(id)){
            return true;
        }
        return false;
    }

    public DTOMascota consultar(int id){
        MascotaDAO mascotaDAO = new MascotaDAO();

        if(id <= 0){
            return null;
        }
        Mascota mascota = mascotaDAO.consultar(id);
        if(mascota != null){
            DTOMascota dtoMascota = new DTOMascota(mascota.getIdMascota(),
                    mascota.getNombre(),
                    mascota.getEspecie(),
                    mascota.getFechaNacimiento(),
                    mascota.getSexo(),
                    mascota.getRaza(),
                    mascota.getIdDueno());
            return dtoMascota;
        }
        return null;
    }
}
