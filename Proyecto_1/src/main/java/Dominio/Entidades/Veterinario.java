package Dominio.Entidades;

import Dominio.Especialidades.Especialidad;

public class Veterinario {
    private int idVeterinario;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String cedulaProfesional;
    private Especialidad especialidad;
    private String telefono;

    public Veterinario(){

    }

    public Veterinario(int idVeterinario, String nombre, String apellidoP, String apellidoM,
                       String cedulaProfesional, Especialidad especialidad, String telefono) {
        this.idVeterinario = idVeterinario;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.cedulaProfesional = cedulaProfesional;
        this.especialidad = especialidad;
        this.telefono = telefono;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
