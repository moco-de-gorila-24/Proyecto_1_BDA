import Dominio.Entidades.Consulta;
import Dominio.Entidades.Duenio;
import Dominio.Entidades.Mascota;
import Dominio.Entidades.Veterinario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class entidadesTest {
    private Duenio duenio;
    private Mascota mascota;
    private Veterinario veterinario;
    private Consulta consulta;

    //tests duenio
    @BeforeEach
    void setUpDuenio() {
        duenio = new Duenio();
    }
    //en este test se revisa que se inicializen los campos por defecto
    @Test
    void duenioConstructorVacio() {
        assertEquals(0, duenio.getIdDueno());
        assertNull(duenio.getNombre());
        assertNull(duenio.getApellidoP());
        assertNull(duenio.getApellidoM());
        assertNull(duenio.getDireccion());
        assertNull(duenio.getEmail());
        assertNull(duenio.getTelefonos());
    }
    
    //aqui se revisa la asignacion correcta de los campos
    @Test
    void duenioConstructorCompleto() {
        List<String> telefonos = Arrays.asList("6441234567", "6447654321");
        Duenio d = new Duenio(1, "Andres", "Duarte", "Mendez", "Calle Tepeyac", "andres@gmail.com", telefonos);

        assertEquals(1, d.getIdDueno());
        assertEquals("Andres", d.getNombre());
        assertEquals("Duarte", d.getApellidoP());
        assertEquals("Mendez", d.getApellidoM());
        assertEquals("Calle Tepeyac", d.getDireccion());
        assertEquals("andres@gmail.com", d.getEmail());
        assertEquals(2, d.getTelefonos().size());
        assertEquals("6441234567", d.getTelefonos().get(0));
    }

    @Test
    void duenioSettersDeberianModificarLosCampos() {
        duenio.setIdDueno(5);
        duenio.setNombre("Ana");
        duenio.setApellidoP("García");
        duenio.setApellidoM("Ruiz");
        duenio.setDireccion("Av. Obregón");
        duenio.setEmail("ana@mail.com");
        duenio.setTelefonos(Arrays.asList("6441234567"));

        assertEquals(5, duenio.getIdDueno());
        assertEquals("Ana", duenio.getNombre());
        assertEquals("García", duenio.getApellidoP());
        assertEquals("Ruiz", duenio.getApellidoM());
        assertEquals("Av. Obregón", duenio.getDireccion());
        assertEquals("ana@mail.com", duenio.getEmail());
        assertEquals(1, duenio.getTelefonos().size());
    }

    @Test
    void duenioTelefonosListaVacia() {
        duenio.setTelefonos(new ArrayList<>());
        assertNotNull(duenio.getTelefonos());
        assertTrue(duenio.getTelefonos().isEmpty());
    }

    @Test
    void duenioTelefonosAceptaNull() {
        duenio.setTelefonos(null);
        assertNull(duenio.getTelefonos());
    }

    // tests mascotas
    @BeforeEach
    void setUpMascota() {
        mascota = new Mascota(); 
    }
    //en este test se revisa que se inicializen los campos por defecto
    @Test
    void mascotaConstructorVacio() {
        assertEquals(0, mascota.getIdMascota());
        assertNull(mascota.getNombre());
        assertNull(mascota.getEspecie());
        assertNull(mascota.getFechaNacimiento());
        assertNull(mascota.getSexo());
        assertNull(mascota.getRaza());
        assertEquals(0, mascota.getIdDueno());
    }
    //aqui se revisa la asignacion correcta de los campos
    @Test
    void mascotaConstructorCompleto() {
        Date fecha = Date.valueOf("2026-05-12");
        Mascota m = new Mascota(101, "Firulais", "Perro", fecha, "M", "Labrador", 1);

        assertEquals(101, m.getIdMascota());
        assertEquals("Firulais", m.getNombre());
        assertEquals("Perro", m.getEspecie());
        assertEquals(fecha, m.getFechaNacimiento());
        assertEquals("M", m.getSexo());
        assertEquals("Labrador", m.getRaza());
        assertEquals(1, m.getIdDueno());
    }

    @Test
    void mascotaSetters() {
        mascota.setIdMascota(102);
        mascota.setNombre("Michi");
        mascota.setEspecie("Gato");
        mascota.setSexo("F");
        mascota.setRaza("Siamés");
        mascota.setIdDueno(2);

        assertEquals(102, mascota.getIdMascota());
        assertEquals("Michi", mascota.getNombre());
        assertEquals("Gato", mascota.getEspecie());
        assertEquals("F", mascota.getSexo());
        assertEquals("Siamés", mascota.getRaza());
        assertEquals(2, mascota.getIdDueno());
    }

    @Test
    void mascotaFechaNacimientoAceptaNull() {
        mascota.setFechaNacimiento(null);
        assertNull(mascota.getFechaNacimiento());
    }

    // tests veterinario
    @BeforeEach
    void setUpVeterinario() {
        veterinario = new Veterinario();
    }
    //en este test se revisa que se inicializen los campos por defecto
    @Test
    void veterinarioConstructorVacio() {
        assertEquals(0, veterinario.getIdVeterinario());
        assertNull(veterinario.getNombre());
        assertNull(veterinario.getApellidoP());
        assertNull(veterinario.getApellidoM());
        assertNull(veterinario.getCedulaProfesional());
        assertNull(veterinario.getEspecialidad());
        assertNull(veterinario.getTelefono());
    }
    //aqui se revisa la asignacion de los campos
    @Test
    void veterinarioConstructorCompleto() {
        Veterinario v = new Veterinario(201, "Yorsh", "Urbina", "MD123", "12345678", "Diagnostico", "6441234567");

        assertEquals(201, v.getIdVeterinario());
        assertEquals("Yorsh", v.getNombre());
        assertEquals("Urbina", v.getApellidoP());
        assertEquals("MD123", v.getApellidoM());
        assertEquals("12345678", v.getCedulaProfesional());
        assertEquals("Diagnostico", v.getEspecialidad());
        assertEquals("6441234567", v.getTelefono());
    }

    @Test
    void veterinarioSetters() {
        veterinario.setIdVeterinario(202);
        veterinario.setNombre("Lisa");
        veterinario.setApellidoP("Simpson");
        veterinario.setApellidoM("MD456");
        veterinario.setCedulaProfesional("87654321");
        veterinario.setEspecialidad("Cirugía");
        veterinario.setTelefono("6441234567");

        assertEquals(202, veterinario.getIdVeterinario());
        assertEquals("Lisa", veterinario.getNombre());
        assertEquals("Simpson", veterinario.getApellidoP());
        assertEquals("MD456", veterinario.getApellidoM());
        assertEquals("87654321", veterinario.getCedulaProfesional());
        assertEquals("Cirugía", veterinario.getEspecialidad());
        assertEquals("6441234567", veterinario.getTelefono());
    }

    
    //tests consultas
    @BeforeEach
    void setUpConsulta() {
        consulta = new Consulta();
    }
    //en este test se revisa que se inicializen los campos por defecto
    @Test
    void consultaConstructorVacio(){
        assertEquals(0, consulta.getIdConsulta());
        assertNull(consulta.getFechaHora());
        assertNull(consulta.getMotivo());
        assertNull(consulta.getDiagnostico());
        assertNull(consulta.getTratamiento());
        assertNull(consulta.getCosto());
        assertEquals(0, consulta.getIdMascota());
        assertEquals(0, consulta.getIdVeterinario());
    }
    //revisa la asignacion correcta en los campos
    @Test
    void consultaConstructorCompleto() {
        Timestamp fecha = Timestamp.valueOf("2024-03-15 10:30:00");
        BigDecimal costo = new BigDecimal("350.50");

        Consulta c = new Consulta(1, fecha, "Vacunacion", "Sano", "Refuerzo", costo, 101, 201);

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
    void consultaSetters() {
        BigDecimal costo = new BigDecimal("500.00");
        consulta.setIdConsulta(2);
        consulta.setCosto(costo);
        consulta.setMotivo("Pulgas");
        consulta.setIdMascota(102);
        consulta.setIdVeterinario(202);

        assertEquals(2, consulta.getIdConsulta());
        assertEquals(costo, consulta.getCosto());
        assertEquals("Pulgas", consulta.getMotivo());
        assertEquals(102, consulta.getIdMascota());
        assertEquals(202, consulta.getIdVeterinario());
    }

    @Test
    void consultaCostoDeberiaAceptarCero() {
        consulta.setCosto(BigDecimal.ZERO);
        assertEquals(0, consulta.getCosto().compareTo(BigDecimal.ZERO));
    }

    @Test
    void consultaCostoAceptaValoresGrandes() {
        BigDecimal costo = new BigDecimal("99999.99");
        consulta.setCosto(costo);
        assertEquals(costo, consulta.getCosto());
    }
}