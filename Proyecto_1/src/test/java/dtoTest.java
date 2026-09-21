import Dominio.DTO.DTOConsulta;
import Dominio.DTO.DTODuenio;
import Dominio.DTO.DTOMascota;
import Dominio.DTO.DTOVeterinario;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class dtoTest {
    //tests del dtoduenio
    @Test
    void dtoDuenioConstructorVacio() {
        DTODuenio d = new DTODuenio();
        assertEquals(0, d.getIdDueno());
        assertNull(d.getNombre());
        assertNull(d.getTelefonos());
    }

    @Test
    void dtoDuenioConstructorCompleto() {
        List<String> tels = Arrays.asList("6441234567", "6447891234");
        DTODuenio d = new DTODuenio(1, "Juan", "Pérez", "López", "Calle padrisima", "j@m.com", tels);

        assertEquals(1, d.getIdDueno());
        assertEquals("Juan", d.getNombre());
        assertEquals("Pérez", d.getApellidoP());
        assertEquals("López", d.getApellidoM());
        assertEquals("Calle padrisima", d.getDireccion());
        assertEquals("j@m.com", d.getEmail());
        assertEquals(2, d.getTelefonos().size());
    }

    @Test
    void dtoDuenioSetters() {
        DTODuenio d = new DTODuenio();
        d.setIdDueno(7);
        d.setNombre("Lonchito");
        d.setTelefonos(Arrays.asList("6440000000"));

        assertEquals(7, d.getIdDueno());
        assertEquals("Lonchito", d.getNombre());
        assertEquals(1, d.getTelefonos().size());
    }

    //tests del dtoMascota

    @Test
    void dtoMascotaConstructorVacio() {
        DTOMascota m = new DTOMascota();
        assertEquals(0, m.getIdMascota());
        assertNull(m.getNombre());
    }

    @Test
    void dtoMascotaConstructorCompleto() {
        Date fecha = Date.valueOf("2020-01-01");
        DTOMascota m = new DTOMascota(101, "Firulais", "Perro", fecha, "M", "Labrador", 1);

        assertEquals(101, m.getIdMascota());
        assertEquals("Firulais", m.getNombre());
        assertEquals("Perro", m.getEspecie());
        assertEquals(fecha, m.getFechaNacimiento());
        assertEquals("M", m.getSexo());
        assertEquals("Labrador", m.getRaza());
        assertEquals(1, m.getIdDueno());
    }

    @Test
    void dtoMascotaSetters() {
        DTOMascota m = new DTOMascota();
        m.setIdMascota(99);
        m.setNombre("Michi");
        m.setEspecie("Gatito");

        assertEquals(99, m.getIdMascota());
        assertEquals("Michi", m.getNombre());
        assertEquals("Gatito", m.getEspecie());
    }

    // tests del dto veterinario

    @Test
    void dtoVeterinarioConstructorVacio() {
        DTOVeterinario v = new DTOVeterinario();
        assertEquals(0, v.getIdVeterinario());
        assertNull(v.getNombre());
    }
    
    //mi mejor amigo es medico y asi se llama dksadsal
    @Test
    void dtoVeterinarioConstructorCompleto() {
        DTOVeterinario v = new DTOVeterinario(201, "Paul", "Adalberto", "MD123", "12345678", "Diagnostico", "6441234567");

        assertEquals(201, v.getIdVeterinario());
        assertEquals("Paul", v.getNombre());
        assertEquals("Adalberto", v.getApellidoP());
        assertEquals("MD123", v.getApellidoM());
        assertEquals("12345678", v.getCedulaProfesional());
        assertEquals("Diagnostico", v.getEspecialidad());
        assertEquals("6441234567", v.getTelefono());
    }

    @Test
    void dtoVeterinarioToString() {
        DTOVeterinario v = new DTOVeterinario(201, "Paul", "Estrella", "MD23", "12345678", "Diagn0stico", "6441234567");
        String s = v.toString();

        assertTrue(s.contains("idVeterinario=201"));
        assertTrue(s.contains("nombre=Paul"));
    }

    // tests de dtoConsulta

    @Test
    void dtoConsultaConstructorVacio() {
        DTOConsulta c = new DTOConsulta();
        assertEquals(0, c.getIdConsulta());
        assertNull(c.getMotivo());
    }

    @Test
    void dtoConsultaConstructorCompleto() {
        Timestamp fecha = Timestamp.valueOf("2026-03-09 10:30:00");
        BigDecimal costo = new BigDecimal("350.50");
        DTOConsulta c = new DTOConsulta(1, fecha, "Vacunacion", "Sano", "Refuerzo", costo, 101, 201);

        assertEquals(1, c.getIdConsulta());
        assertEquals(fecha, c.getFechaHora());
        assertEquals("Vacunacion", c.getMotivo());
        assertEquals("Sano", c.getDiagnostico());
        assertEquals("Refuerzo", c.getTratamiento());
        assertEquals(costo, c.getCosto());
        assertEquals(101, c.getIdMascota());
        assertEquals(201, c.getIdVeterinario());
    }

    @Test
    void dtoConsultaSetters() {
        DTOConsulta c = new DTOConsulta();
        c.setIdConsulta(5);
        c.setMotivo("enfermo");
        c.setCosto(new BigDecimal("500000"));

        assertEquals(5, c.getIdConsulta());
        assertEquals("enfermo", c.getMotivo());
        assertEquals(0, c.getCosto().compareTo(new BigDecimal("500000")));
    }
}